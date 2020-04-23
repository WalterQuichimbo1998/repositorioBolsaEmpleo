package jsf.classes;

import control.Exportar;
import controller.Empresa;
import controller.OfertaLaboral;
import java.io.IOException;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.OfertaLaboralFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import net.sf.jasperreports.engine.JRException;

@Named("ofertaLaboralController")
@SessionScoped
public class OfertaLaboralController implements Serializable {

    @EJB
    private sessions.beans.OfertaLaboralFacade ejbFacade;
    private List<OfertaLaboral> items = null;
    private List<OfertaLaboral> lista = null;
    private List<OfertaLaboral> listaOferta = null;
    private List<OfertaLaboral> filtroOfertas;
    private OfertaLaboral selected;
    private Empresa empresa;
    private Integer id;
    private Date fecha_inicio;
    private Date fecha_fin;
    private List<OfertaLaboral> listaE = null;
    private String mensaje;
    private Boolean v = false;

    public OfertaLaboralController() {
    }

    public OfertaLaboral getSelected() {
        return selected;
    }

    public void setSelected(OfertaLaboral selected) {
        this.selected = selected;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
        lista = null;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OfertaLaboralFacade getFacade() {
        return ejbFacade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setListaOferta(List<OfertaLaboral> listaOferta) {
        this.listaOferta = listaOferta;
    }

    public List<OfertaLaboral> getFiltroOfertas() {
        return filtroOfertas;
    }

    public void setFiltroOfertas(List<OfertaLaboral> filtroOfertas) {
        this.filtroOfertas = filtroOfertas;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public List<OfertaLaboral> getListaE() {
        return listaE;
    }

    public void setListaE(List<OfertaLaboral> listaE) {
        this.listaE = listaE;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Boolean getV() {
        return v;
    }

    public void setV(Boolean v) {
        this.v = v;
    }

    public OfertaLaboral prepareCreate() {
        selected = new OfertaLaboral();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdEmpresa(empresa);
        this.selected.setFechaCreacion(new Date());
        this.selected.setEstado(true);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OfertaLaboralCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().listaOferta(empresa.getIdEmpresa());

        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OfertaLaboralUpdated"));
    }

    public void update2() {
        if (selected.getEstado() == true) {
            this.selected.setFechaCreacion(new Date());
            persist(PersistAction.UPDATE, "Habilitación éxitosa.");
        } else {
            persist(PersistAction.UPDATE, "Deshabilitación correcta.");
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OfertaLaboralDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().listaOferta(empresa.getIdEmpresa());

        }
    }

    public List<OfertaLaboral> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<OfertaLaboral> listaO() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<OfertaLaboral> getLista() {
        if (lista == null) {
            lista = getFacade().listaOferta(empresa.getIdEmpresa());
        }
        return lista;
    }

    public List<OfertaLaboral> getListaOferta() {
        listaOferta = getFacade().listaOferta2(selected.getIdOferta());
        return listaOferta;
    }

    public void update3() {

        persist2(PersistAction.UPDATE);

    }

    public void update4() {
        this.selected.setEstado(true);
        persist2(PersistAction.UPDATE);
        items = getFacade().findAll();

    }

    private void persist2(PersistAction persistAction) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }

            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {

                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
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

    public OfertaLaboral getOfertaLaboral(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<OfertaLaboral> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<OfertaLaboral> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = OfertaLaboral.class)
    public static class OfertaLaboralControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OfertaLaboralController controller = (OfertaLaboralController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ofertaLaboralController");
            return controller.getOfertaLaboral(getKey(value));
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
            if (object instanceof OfertaLaboral) {
                OfertaLaboral o = (OfertaLaboral) object;
                return getStringKey(o.getIdOferta());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), OfertaLaboral.class.getName()});
                return null;
            }
        }

    }

    public String fecha(Date d) {
        String resultado = "";
        if (d != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            resultado = formato.format(d);
        } else {
            resultado = "";
        }
        return resultado;
    }

    public void mensaje() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Escriba un CARGO SOLICITADO para guardar", ""));
    }

    public Boolean provincia(String p) {
        boolean r;
        if (p != null) {
            r = true;
        } else {
            r = false;
        }
        return r;
    }

    public void verificarReporte() {
        v = false;
        mensaje = "";
        if (getFecha_inicio() == null && getFecha_fin() == null) {
            mensaje = "Seleccione las fechas.";
            v = false;
        } else {
            if (getFecha_inicio() == null || getFecha_fin() == null) {
                mensaje = "Falta selecionar una fecha.";
                v = false;
            } else {
                if (getFecha_inicio().getMonth() <= getFecha_fin().getMonth()) {
                    listaE = getFacade().comprobarOfertas(empresa.getIdEmpresa(), getFecha_inicio(), getFecha_fin());
                    if (listaE.size() != 0) {
                        SimpleDateFormat f = new SimpleDateFormat("dd-MM-YYYY");
                        mensaje = "Si hay registro con estas fechas: " + f.format(getFecha_inicio()) + " - " + f.format(getFecha_fin());
                        v = true;
                    } else {
                        mensaje = "No hay registros. Intente con otras fechas.";
                        v = false;
                    }
                } else {
                    mensaje = "Seleccione bien las fechas.";
                    v = false;
                }
            }
        }
    }

    public void descargarReporte() {
        v = false;
        mensaje = "";
        if (getFecha_inicio() == null && getFecha_fin() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione las fechas.", ""));
            mensaje = "Seleccione las fechas. ¡Para poder descargar!";
            v = false;
        } else {
            if (getFecha_inicio() == null || getFecha_fin() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falta selecionar una fecha.", ""));
                mensaje = "Falta selecionar una fecha. ¡Para poder descargar!";
                v = false;
            } else {
                if (getFecha_inicio().getMonth() <= getFecha_fin().getMonth()) {
                    listaE = getFacade().comprobarOfertas(empresa.getIdEmpresa(), getFecha_inicio(), getFecha_fin());
                    if (listaE.size() != 0) {
                        mensaje = "";
                        v = false;
                        try {
                            Exportar exportar = new Exportar();
                            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                            String f1 = formato.format(getFecha_inicio());
                            String f2 = formato.format(getFecha_fin());
                            exportar.reporteOfertasFechas(empresa.getIdEmpresa(), empresa.getNombreComercial(), f1, f2);
                        } catch (JRException | IOException ex) {
                            Logger.getLogger(PostulanteController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No hay registros. Intente con otras fechas.", ""));
                        mensaje = "No hay registros. Intente con otras fechas. ¡Para poder descargar!";
                        v = false;
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione bien las fechas.", ""));
                    mensaje = "Seleccione bien las fechas. ¡Para poder descargar!";
                    v = false;
                }
            }

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
    
     public void fecha1(){
         this.setFecha_inicio(new Date());
         this.setFecha_fin(new Date());
         mensaje="";
         v = false;
     }
     public void fecha2(){
         Date d=new Date();
         this.setFecha_inicio(new Date(d.getYear(), d.getMonth(), d.getDate()-1));
         this.setFecha_fin(new Date(d.getYear(), d.getMonth(), d.getDate()-1));
         mensaje="";
         v = false;
     }
}
