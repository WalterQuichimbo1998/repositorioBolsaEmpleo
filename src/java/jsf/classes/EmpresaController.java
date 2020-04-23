package jsf.classes;

import control.AccesoBean;
import control.Exportar;
import control.UtilPath;
import controller.Canton;
import controller.Empresa;
import controller.Parroquia;
import controller.Provincia;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.EmpresaFacade;

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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.model.UploadedFile;

@Named("empresaController")
@SessionScoped
public class EmpresaController implements Serializable {

    @EJB
    private sessions.beans.EmpresaFacade ejbFacade;
    @EJB
    private sessions.beans.ProvinciaFacade ejbFacadeP;
    @EJB
    private sessions.beans.CantonFacade ejbFacadeC;
    @EJB
    private sessions.beans.ParroquiaFacade ejbFacadePa;
    private List<Empresa> items = null;
    private List<Empresa> lista = null;
    private List<Empresa> listaBuscar = null;
    private Empresa selected;
    private UploadedFile file;
    private String destino;
    private List<Provincia> listaProvincias = null;
    private List<Canton> listaCanton = null;
    private List<Parroquia> listaParroquia = null;
    private String nombre = "";
    private Date fecha_inicio;
    private Date fecha_fin;
    private List<Empresa> listaE = null;
    private String mensaje;
    private Boolean v = false;

    public EmpresaController() {
    }

    public Empresa getSelected() {
        return selected;
    }

    public void setSelected(Empresa selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EmpresaFacade getFacade() {
        return ejbFacade;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<Provincia> getListaProvincias() {
        return ejbFacadeP.findAll();
    }

    public void setListaProvincias(List<Provincia> listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List<Canton> getListaCanton() {
        if (selected.getIdProvincia() != null) {
            return listaCanton = ejbFacadeC.listaCanton(selected.getIdProvincia().getIdProvincia());
        } else {
            return null;
        }
    }

    public void setListaCanton(List<Canton> listaCanton) {
        this.listaCanton = listaCanton;
    }

    public List<Parroquia> getListaParroquia() {
        if (selected.getIdProvincia() != null) {
            if (getListaCanton().contains(selected.getIdCanton()) == true) {
                return listaParroquia = ejbFacadePa.listaParroquia(selected.getIdCanton().getIdCanton());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void setListaParroquia(List<Parroquia> listaParroquia) {
        this.listaParroquia = listaParroquia;
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

    public List<Empresa> getListaE() {
        return listaE;
    }

    public void setListaE(List<Empresa> listaE) {
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

    public List<Empresa> getListaBuscar() {
        return listaBuscar;
    }

    public void setListaBuscar(List<Empresa> listaBuscar) {
        this.listaBuscar = listaBuscar;
    }

    public Empresa prepareCreate() {
        selected = new Empresa();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdPersona(AccesoBean.obtenerIdPersona().getIdPersona());
        this.selected.setFechaCreacion(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EmpresaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().listaEmpresa();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EmpresaUpdated"));
    }

    public void actualizarLogo() {
        persist(PersistAction.UPDATE, "Logotipo subido con éxito.");
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EmpresaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().listaEmpresa();
        }
    }

    public List<Empresa> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Empresa> getLista() {
        if (lista == null) {
            lista = getFacade().listaEmpresa();
        }
        return lista;
    }

    public void setLista(List<Empresa> lista) {
        this.lista = lista;
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

    public Empresa getEmpresa(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Empresa> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Empresa> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Empresa> getItemsEmpresas() {
        return getFacade().listaEmpresa();
    }

    @FacesConverter(forClass = Empresa.class)
    public static class EmpresaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmpresaController controller = (EmpresaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "empresaController");
            return controller.getEmpresa(getKey(value));
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
            if (object instanceof Empresa) {
                Empresa o = (Empresa) object;
                return getStringKey(o.getIdEmpresa());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Empresa.class.getName()});
                return null;
            }
        }

    }

    public void mensaje() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Escriba un NOMBRE COMERCIAL para guardar", ""));
    }

    public void limpiarSeleccion() {
        selected = null;
    }

    boolean f = false;

    public void subirLogo() {
        String extension = "";
        if (file == null || file.getSize() == 0) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione un logotipo", ""));
        } else {
            String ex = file.getContentType();
            if ("image/jpeg".equals(ex) || "image/jpg".equals(ex) || "image/png".equals(ex)) {
                if (null != ex) switch (ex) {
                    case "image/jpeg":
                        extension = ".jpeg";
                        break;
                    case "image/jpg":
                        extension = ".jpg";
                        break;
                    case "image/png":
                        extension = ".png";
                        break;
                    default:
                        break;
                }
                if (file.getSize() <= 2048000) {
                    try {
                        if (selected.getNombreComercial() == null || "".equals(selected.getNombreComercial())) {
                            nombre = getSelected().getIdEmpresa() + "_Empresa" + extension;
                        } else {
                            nombre = getSelected().getIdEmpresa() + "_" + selected.getNombreComercial() + extension;
                        }
                        selected.setLogotipo("logotipo/" + nombre);
                        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                        String realPath = UtilPath.getPathDefinida(ec.getRealPath("/"));
                        String pathDefinition = realPath + File.separator + "web" + File.separator + "resources" + File.separator + "logotipo" + File.separator + nombre;
                        FileOutputStream out = new FileOutputStream(pathDefinition);
                        InputStream in = file.getInputstream();
                        if ((in != null)) {
                            int read = 0;
                            byte[] bytes = new byte[1024];
                            while ((read = in.read(bytes)) != -1) {
                                out.write(bytes, 0, read);
                            }
                            in.close();
                            out.flush();
                            out.close();
                            actualizarLogo();
                        }
                    } catch (Exception e) {
                    }
                } else {
                    this.setFile(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Logotipo muy pesado elija otro. Tamaño máximo: 2Mb", ""));
                }
            } else {
                this.setFile(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Formato no válido. Use formato .png o .jpg", ""));
            }
        }
    }

    public void verificarReporte() {
        v = false;
        mensaje = "";
        listaE = null;
        if (getFecha_inicio() == null && getFecha_fin() == null) {
            mensaje = "Seleccione las fechas.";
            v = false;
        } else {
            if (getFecha_inicio() == null || getFecha_fin() == null) {
                mensaje = "Falta selecionar una fecha.";
                v = false;
            } else {
                if (getFecha_inicio().getMonth() <= getFecha_fin().getMonth()) {
                    listaE = getFacade().comprobarEmpresas(AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona(), getFecha_inicio(), getFecha_fin());
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
        listaE = null;
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
                    listaE = getFacade().comprobarEmpresas(AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona(), getFecha_inicio(), getFecha_fin());
                    if (listaE.size() != 0) {
                        mensaje = "";
                        v = false;
                        try {
                            Exportar exportar = new Exportar();
                            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                            String f1 = formato.format(getFecha_inicio());
                            String f2 = formato.format(getFecha_fin());
                            exportar.reporteEmpresasFechas(AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona(), f1, f2);
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

    public void verificarReporteAdminFechas() {
        v = false;
        mensaje = "";
        listaE = null;
        if (getFecha_inicio() == null && getFecha_fin() == null) {
            mensaje = "Seleccione las fechas.";
            v = false;
        } else {
            if (getFecha_inicio() == null || getFecha_fin() == null) {
                mensaje = "Falta selecionar una fecha.";
                v = false;
            } else {
                if (getFecha_inicio().getMonth() <= getFecha_fin().getMonth()) {
                    listaE = getFacade().comprobarEmpresasAdmin(getFecha_inicio(), getFecha_fin());
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

    public void descargarReporteAdminFechas() {
        v = false;
        mensaje = "";
        listaE = null;
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
                    listaE = getFacade().comprobarEmpresasAdmin(getFecha_inicio(), getFecha_fin());
                    if (listaE.size() != 0) {
                        mensaje = "";
                        v = false;
                        try {
                            Exportar exportar = new Exportar();
                            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                            String f1 = formato.format(getFecha_inicio());
                            String f2 = formato.format(getFecha_fin());
                            exportar.reporteEmpresasAdminFechas(f1, f2);
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

    public String fecha(Date f) {
        String d = "";
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        d = formato.format(f);
        return d;
    }

    public void fecha1() {
        this.setFecha_inicio(new Date());
        this.setFecha_fin(new Date());
        mensaje = "";
        v = false;
    }

    public void fecha2() {
        Date d = new Date();
        this.setFecha_inicio(new Date(d.getYear(), d.getMonth(), d.getDate() - 1));
        this.setFecha_fin(new Date(d.getYear(), d.getMonth(), d.getDate() - 1));
        mensaje = "";
        v = false;
    }
}
