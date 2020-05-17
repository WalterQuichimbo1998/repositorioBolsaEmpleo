package jsf.classes;

import control.UtilPath;
import controller.ImagenPortada;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.ImagenPortadaFacade;

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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.primefaces.model.UploadedFile;

@Named("imagenPortadaController")
@SessionScoped
public class ImagenPortadaController implements Serializable {

    @EJB
    private sessions.beans.ImagenPortadaFacade ejbFacade;
    private List<ImagenPortada> items = null;
    private ImagenPortada selected;
    private UploadedFile file;
    private String nombre = "";
    private int width;
    private int height;

    public ImagenPortadaController() {
    }

    public ImagenPortada getSelected() {
        return selected;
    }

    public void setSelected(ImagenPortada selected) {
        this.selected = selected;

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ImagenPortadaFacade getFacade() {
        return ejbFacade;
    }

    public ImagenPortada prepareCreate() {
        selected = new ImagenPortada();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, "Imagen súbida con éxito.");
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Imagen actualizada con éxito");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Imagen eliminada");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ImagenPortada> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle2").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ImagenPortada getImagenPortada(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ImagenPortada> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ImagenPortada> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ImagenPortada.class)
    public static class ImagenPortadaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImagenPortadaController controller = (ImagenPortadaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imagenPortadaController");
            return controller.getImagenPortada(getKey(value));
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
            if (object instanceof ImagenPortada) {
                ImagenPortada o = (ImagenPortada) object;
                return getStringKey(o.getIdImagen());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ImagenPortada.class.getName()});
                return null;
            }
        }

    }

    public void subirImagen() throws IOException {
        if (file == null || file.getSize() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione una Imagen", ""));
        } else {
            String ex = file.getContentType();
            if ("image/jpeg".equals(ex) || "image/jpg".equals(ex) || "image/png".equals(ex)) {
                if (file.getSize() <= 2048000) {
                    InputStream in2 = file.getInputstream();
                    BufferedImage image = ImageIO.read(in2);
                    width = image.getWidth();
                    height = image.getHeight();
                    if (width == 1300 && height == 500) {
                        try {
                            nombre = file.getFileName();
                            this.selected.setImagen(nombre);
                            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                            String realPath = UtilPath.getPathDefinida(ec.getRealPath("/"));
                            String pathDefinition = realPath + File.separator + "web" + File.separator + "resources" + File.separator + "portada" + File.separator + nombre;
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
                                create();
                                file = null;
                            }
                        } catch (IOException e) {
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Los píxeles de la imagen no son los requeridos. - Tamaño permitido 1300 x 500", ""));
                    }
                } else {
                    this.setFile(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Imagen muy pesada elija otro. Tamaño máximo: 2Mb", ""));
                }
            } else {
                this.setFile(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Imagen no válido. Use formato .png o .jpg", ""));
            }
        }
    }

    public void subirImagen2() throws IOException {
        if (file == null || file.getSize() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione una Imagen", ""));
        } else {
            String ex = file.getContentType();
            if ("image/jpeg".equals(ex) || "image/jpg".equals(ex) || "image/png".equals(ex)) {
               if (file.getSize() <= 2048000) {
                    InputStream in2 = file.getInputstream();
                    BufferedImage image = ImageIO.read(in2);
                    width = image.getWidth();
                    height = image.getHeight();
                    if (width == 1300 && height == 500) {
                    try {
                        nombre = selected.getImagen();
                        this.selected.setImagen(nombre);
                        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                        String realPath = UtilPath.getPathDefinida(ec.getRealPath("/"));
                        String pathDefinition = realPath + File.separator + "web" + File.separator + "resources" + File.separator + "portada" + File.separator + nombre;
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
                            update();
                            file = null;
                            items=null;
                             items = getFacade().findAll();
                        }
                    } catch (IOException e) {
                    }
                      } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Los píxeles de la imagen no son los requeridos. - Tamaño permitido 1600 x 500", ""));
                    }
                } else {
                    this.setFile(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Imagen muy pesada elija otro. Tamaño máximo: 4Mb", ""));
                }
            } else {
                this.setFile(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Imagen no válido. Use formato .png o .jpg", ""));
            }
        }
    }

    public void eliminarImagen() {
        ExternalContext ecDelete = FacesContext.getCurrentInstance().getExternalContext();
        String realPathDelete = UtilPath.getPathDefinida(ecDelete.getRealPath("/"));
        String pathDefinitionDelete = realPathDelete + File.separator + "web" + File.separator + "resources" + File.separator + "portada" + File.separator + selected.getImagen();
        File archivo = new File(pathDefinitionDelete);
        archivo.delete();
        destroy();
    }

    public boolean limite(Integer n) {
        boolean l = true;
        if (n <= 7) {
            l = true;
        } else {
            l = false;
        }
        return l;
    }
}
