package jsf.classes;

import controller.HojaVidaEstudiante;
import controller.Persona;
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
     private List<HojaVidaEstudiante> hojaVida = null;


    public HojaVidaEstudianteController() {
    }

    public HojaVidaEstudiante getSelected() {
        return selected;
    }

    public void setSelected(HojaVidaEstudiante selected) {
        this.selected = selected;
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
        hojaVida=getFacade().listaHojaEstudiante(persona.getIdPersona());
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
        HojaVidaEstudiante h = getFacade().buscarIdPersona(selected.getIdPersona());
        if (h != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Está persona ya cuenta con perfil", "Está persona ya cuenta con perfil"));

        } else {
            create();
        }
    }

    public void existePerfil2() {
        HojaVidaEstudiante h = getFacade().buscarIdPersona(selected.getIdPersona());
        if (h != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Está persona ya cuenta con perfil", "Está persona ya cuenta con perfil"));
            items = null;
            getItems();
        } else {
            update();
        }
    }
public Boolean existeFoto(String f){
        boolean r=true;
        if("".equals(f)){
            r=false;
        }
        if(f.isEmpty()){
            r=false;
        }       
        return r;
    }
}
