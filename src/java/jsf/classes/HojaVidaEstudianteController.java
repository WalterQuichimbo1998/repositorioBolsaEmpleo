package jsf.classes;

import control.Exportar;
import java.io.IOException;
import modelo.HojaVidaEstudiante;
import modelo.Persona;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.HojaVidaEstudianteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Carrera;
import modelo.Promocion;
import net.sf.jasperreports.engine.JRException;

@Named("hojaVidaEstudianteController")
@SessionScoped
public class HojaVidaEstudianteController implements Serializable {

    @EJB
    private sessions.beans.HojaVidaEstudianteFacade ejbFacade;
    private List<HojaVidaEstudiante> items = null;
    private List<HojaVidaEstudiante> listaPerfil = null;
    private HojaVidaEstudiante selected;
    private Persona selectedPer;
    private Persona persona;
    private Carrera carrera;
    private Promocion promocion;
    private Carrera carrera2;
    private Promocion promocion2;
    private List<HojaVidaEstudiante> hojaVida = null;

    public HojaVidaEstudianteController() {
    }

    public HojaVidaEstudiante getSelected() {
        return selected;
    }

    public void setSelected(HojaVidaEstudiante selected) {
        this.selected = selected;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public Carrera getCarrera2() {
        return carrera2;
    }

    public void setCarrera2(Carrera carrera2) {
        this.carrera2 = carrera2;
    }

    public Promocion getPromocion2() {
        return promocion2;
    }

    public void setPromocion2(Promocion promocion2) {
        this.promocion2 = promocion2;
    }
    

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HojaVidaEstudianteFacade getFacade() {
        return ejbFacade;
    }

    public List<HojaVidaEstudiante> getListaPerfil() {
        return listaPerfil;
    }

    public void setListaPerfil(List<HojaVidaEstudiante> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<HojaVidaEstudiante> getHojaVida() {
        if (persona != null) {
            hojaVida = getFacade().listaHojaEstudiante(persona.getIdPersona());
        }
        return hojaVida;
    }

    public void setHojaVida(List<HojaVidaEstudiante> hojaVida) {
        this.hojaVida = hojaVida;
    }

    public HojaVidaEstudiante prepareCreate() {
        selected = new HojaVidaEstudiante();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HojaVidaEstudianteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        items = getFacade().findAll();     
  
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HojaVidaEstudianteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HojaVidaEstudianteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void resetea() {
        items = null;
    }

    public List<HojaVidaEstudiante> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public HojaVidaEstudiante getHojaVidaEstudiante(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<HojaVidaEstudiante> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<HojaVidaEstudiante> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = HojaVidaEstudiante.class)
    public static class HojaVidaEstudianteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HojaVidaEstudianteController controller = (HojaVidaEstudianteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "hojaVidaEstudianteController");
            return controller.getHojaVidaEstudiante(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof HojaVidaEstudiante) {
                HojaVidaEstudiante o = (HojaVidaEstudiante) object;
                return getStringKey(o.getIdHojaVidaEstudiante());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), HojaVidaEstudiante.class.getName()});
                return null;
            }
        }

    }

    public void existePerfil() {
        HojaVidaEstudiante h = getFacade().buscarIdPersonaEstado(selected.getIdPersona());
        if (h != null) {
            items = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este estudiante ya tiene el perfil habilitado", ""));
        } else {
            create();
        }
    }

    public void existePerfil2() {
        HojaVidaEstudiante h = getFacade().buscarIdPersonaEstado2(selected.getIdPersona(),selected.getEstado()); 
        if (h != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este estudiante ya tiene el perfil habilitado", ""));
            items = null;
        } else {
            update();
        }
    }

    public Boolean existeFoto(String f) {
        boolean r = true;
        if ("".equals(f)) {
            r = false;
        }
        if (f.isEmpty()) {
            r = false;
        }
        return r;
    }

    public void descargarReporte() {
        Exportar exportar = new Exportar();
        if(promocion!=null && carrera!=null){
            try {
                 exportar.reporteExperienciaEstudiante1(carrera.getIdCarrera(),promocion.getIdPromocion());
            } catch (IOException | JRException e) {
                System.out.println("Error: "+e);
            }
        }else if(promocion!=null && carrera==null){
           try {
                 exportar.reporteExperienciaEstudiante2(promocion.getIdPromocion());
            } catch (IOException | JRException e) {
                 System.out.println("Error: "+e);
            }
        }else if(promocion==null && carrera!=null){
           try {
                 exportar.reporteExperienciaEstudiante3(carrera.getIdCarrera());
            } catch (IOException | JRException e) {
                 System.out.println("Error: "+e);
            }
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione almenos una opción", ""));
        }
    }
    public void descargarReporteReco() {
        Exportar exportar = new Exportar();
        if(promocion2!=null && carrera2!=null){
            try {
                 exportar.reporteRecomendacionEstudiante1(carrera2.getIdCarrera(),promocion2.getIdPromocion());
            } catch (IOException | JRException e) {
                System.out.println("Error: "+e);
            }
        }else if(promocion2!=null && carrera2==null){
           try {
                 exportar.reporteRecomendacionEstudiante2(promocion2.getIdPromocion());
            } catch (IOException | JRException e) {
                 System.out.println("Error: "+e);
            }
        }else if(promocion2==null && carrera2!=null){
           try {
                 exportar.reporteRecomendacionEstudiante3(carrera2.getIdCarrera());
            } catch (IOException | JRException e) {
                 System.out.println("Error: "+e);
            }
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione almenos una opción", ""));
        }
    }
}
