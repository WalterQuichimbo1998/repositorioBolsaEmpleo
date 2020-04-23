package jsf.classes;

import control.AccesoBean;
import control.Exportar;
import controller.HojaVidaEstudiante;
import controller.OfertaLaboral;
import controller.Postulante;
import java.io.IOException;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.PostulanteFacade;

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

@Named("postulanteController")
@SessionScoped
public class PostulanteController implements Serializable {

    @EJB
    private sessions.beans.PostulanteFacade ejbFacade;
    @EJB
    private sessions.beans.HojaVidaEstudianteFacade ejbFacadeHoja;

    private List<Postulante> items = null;
    private List<Postulante> lista = null;
    private List<Postulante> lista_2 = null;
    private List<Postulante> listaP = null;  
    private List<Postulante> listaPostulantes = null;
    private List<Postulante> totalP = null;
    private List<Postulante> masPostulaciones = null;
    private List<Postulante> listaBuscar = null;
    private List<Postulante> listaBuscarAdmin = null;
    private List<HojaVidaEstudiante> hojaVida = null;
    private Postulante selected;
    private OfertaLaboral ofertaLista;
    private Integer id;
    private OfertaLaboral ofertaPostular;
    private OfertaLaboral ofertaVer;
    private Date fecha_inicio;
    private Date fecha_fin;
    private List<Postulante> listaE = null;
    private String mensaje;
    private Boolean v = false;
    Integer id_per;

    public PostulanteController() {
    }

    public Postulante getSelected() {
        return selected;
    }

    public void setSelected(Postulante selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PostulanteFacade getFacade() {
        return ejbFacade;
    }

    public List<Postulante> getLista() {
        if (lista == null) {
            lista = getFacade().lista();
        }
        return lista;
    }

    public void setLista(List<Postulante> lista) {
        this.lista = lista;
    }

    public Integer getId_per() {
        return id_per;
    }

    public void setId_per(Integer id_per) {
        this.id_per = id_per;
        lista_2 = null;
    }
    
    public List<Postulante> getLista_2() {
        if (lista_2 == null) {
            lista_2 = getFacade().lista_2(getId_per());
        }
        return lista_2;
    }

    public void setLista_2(List<Postulante> lista_2) {
        this.lista_2 = lista_2;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OfertaLaboral getOfertaPostular() {
        return ofertaPostular;
    }

    public void setOfertaPostular(OfertaLaboral ofertaPostular) {
        this.ofertaPostular = ofertaPostular;

    }

    public OfertaLaboral getOfertaLista() {
        return ofertaLista;
    }

    public void setOfertaLista(OfertaLaboral ofertaLista) {
        this.ofertaLista = ofertaLista;
        listaP = null;
    }

    public List<Postulante> getListaP() {
        if (listaP == null) {
            listaP = getFacade().listaPostulantes(ofertaLista.getIdOferta());
        }

        return listaP;
    }

    public List<Postulante> getListaPostulantes() {

        if (listaPostulantes == null) {
            listaPostulantes = getFacade().listaPostulantes2();
        }
        return listaPostulantes;
    }

    public void setListaPostulantes(List<Postulante> listaPostulantes) {
        this.listaPostulantes = listaPostulantes;
    }

    public List<HojaVidaEstudiante> getHojaVida() {
        hojaVida = ejbFacadeHoja.listaHojaEstudiante(selected.getPersonaIdPersona().getIdPersona());
        return hojaVida;
    }

    public void setHojaVida(List<HojaVidaEstudiante> hojaVida) {
        this.hojaVida = hojaVida;
    }

    public List<Postulante> getTotalP() {
        return totalP;
    }

    public void setTotalP(List<Postulante> totalP) {
        this.totalP = totalP;
    }

    public List<Postulante> getMasPostulaciones() {
        if (masPostulaciones == null) {
            masPostulaciones = getFacade().masPostulaciones();
        }
        return masPostulaciones;
    }

    public void setMasPostulaciones(List<Postulante> masPostulaciones) {
        this.masPostulaciones = masPostulaciones;
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

    public List<Postulante> getListaE() {
        return listaE;
    }

    public void setListaE(List<Postulante> listaE) {
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

    public List<Postulante> getListaBuscar() {
        return listaBuscar;
    }

    public void setListaBuscar(List<Postulante> listaBuscar) {
        this.listaBuscar = listaBuscar;
    }

    public OfertaLaboral getOfertaVer() {
        return ofertaVer;
    }

    public void setOfertaVer(OfertaLaboral ofertaVer) {
        this.ofertaVer = ofertaVer;
        listaBuscarAdmin=null;
    }
    
  
    public List<Postulante> getListaBuscarAdmin() {

        if (listaBuscarAdmin == null) {            
            listaBuscarAdmin = getFacade().buscarPostulantes(ofertaVer.getIdOferta());
        }
        return listaBuscarAdmin;
    }

    public void setListaBuscarAdmin(List<Postulante> listaBuscarAdmin) {
        this.listaBuscarAdmin = listaBuscarAdmin;
    }
    

    public Postulante prepareCreate() {
        selected = new Postulante();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setPersonaIdPersona(AccesoBean.obtenerIdPersona().getIdPersona());
        this.selected.setOfertaLaboralIdOferta(getOfertaPostular());
        this.selected.setFechaPostulante(new Date());
        this.selected.setConfirmacion(false);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PostulanteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.

        }
    }

    public void update() {
        if (selected.getEstado_postulante() == true) {
            this.selected.setConfirmacion(false);
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PostulanteAceptado"));
            lista = null;
            lista = getFacade().lista();
            listaPostulantes = null;
            listaPostulantes = getFacade().listaPostulantes2();
        } else {
            this.selected.setConfirmacion(false);
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PostulanteDenegado"));
            lista = null;
            lista = getFacade().lista();
            listaPostulantes = null;
            listaPostulantes = getFacade().listaPostulantes2();
        }
    }

    public void update2() {
        this.selected.setConfirmacion(true);
        this.selected.setFechaConfirmacion(new Date());
        persist(PersistAction.UPDATE, "Confirmación éxitosa.");

    }

    public void update3() {
        this.selected.setConfirmacion(false);
        persist(PersistAction.UPDATE, "Cancelación exitosa.");
    }

    public void update4() {
        this.selected.setEstado_postulante(false);
        this.selected.setConfirmacion(false);
        persist(PersistAction.UPDATE, "Cancelación exitosa.");
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PostulanteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;
            lista = getFacade().lista();
        }
    }

    public List<Postulante> getItems() {
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

    public Postulante getPostulante(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Postulante> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Postulante> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }


    @FacesConverter(forClass = Postulante.class)
    public static class PostulanteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PostulanteController controller = (PostulanteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "postulanteController");
            return controller.getPostulante(getKey(value));
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
            if (object instanceof Postulante) {
                Postulante o = (Postulante) object;
                return getStringKey(o.getIdPostulante());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Postulante.class.getName()});
                return null;
            }
        }

    }

    public String pasarTexto(boolean n) {
        String t = "";
        if (n == true) {
            t = "ACEPTADO";
        } else {
            t = "NO ACEPTADO";
        }

        return t;
    }

    public String pasarTexto2(boolean n) {
        String t = "";
        if (n == true) {
            t = "ACEPTADO";
        } else {
            t = "";
        }
        return t;
    }

    public String fecha(Date f) {
        String d = "";
        if (f != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            d = formato.format(f);
        } else {
            d = "";
        }
        return d;
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

    public void existePos() {
        Postulante p = getFacade().existePostulante(ofertaPostular, AccesoBean.obtenerIdPersona().getIdPersona());
        if (p != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ya estas postulado a esta oferta", "Ya estas postulado a esta oferta"));

        } else {
            create();
        }
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
                    listaE = getFacade().comprobarRegistro(getFecha_inicio(), getFecha_fin());
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
                    listaE = getFacade().comprobarRegistro(getFecha_inicio(), getFecha_fin());
                    if (listaE.size() != 0) {
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        String d1 = formato.format(getFecha_inicio());
                        String d2 = formato.format(getFecha_fin());
                        Integer id = AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona();
                        String nombre = AccesoBean.obtenerIdPersona().getIdPersona().getNombre() + " " + AccesoBean.obtenerIdPersona().getIdPersona().getNombre();
                        mensaje = "";
                        v = false;

                        try {
                            Exportar exportar = new Exportar();
                            exportar.reportePostulanteFechas(id, nombre, d1, d2);
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

    public void verificarReporteEmpresa() {
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
                    listaE = getFacade().listaPostulantesEmpresa(getFecha_inicio(), getFecha_fin());
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

    public void descargarReporteEmpresa() {
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
                    listaE = getFacade().listaPostulantesEmpresa(getFecha_inicio(), getFecha_fin());
                    if (listaE.size() != 0) {
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        String d1 = formato.format(getFecha_inicio());
                        String d2 = formato.format(getFecha_fin());
                        Integer idP = AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona();
                        mensaje = "";
                        v = false;

                        try {
                            Exportar exportar = new Exportar();
                            exportar.reportePostulanteEmpresaFechas(idP, d1, d2);
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
    public void verificarReportePostulantes() {
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
                    listaE = getFacade().listaPostulantesOferta(getOfertaLista().getIdOferta(), getFecha_inicio(), getFecha_fin());
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

    public void descargarReportePostulantes() {
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
                    listaE = getFacade().listaPostulantesOferta(getOfertaLista().getIdOferta(),getFecha_inicio(), getFecha_fin());
                    if (listaE.size() != 0) {
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                        String d1 = formato.format(getFecha_inicio());
                        String d2 = formato.format(getFecha_fin());
                        
                        mensaje = "";
                        v = false;

                        try {
                            Exportar exportar = new Exportar();
                           exportar.reportePostulanteEmpresaFechas2(getOfertaLista().getIdOferta(), d1, d2);
                        } catch (IOException ex) {
                            Logger.getLogger(PostulanteController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (JRException ex) {
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

    public Boolean aceptacion(boolean a, boolean c) {
        boolean r = false;
        if (a == true && c == false) {
            r = true;
        } else {
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
