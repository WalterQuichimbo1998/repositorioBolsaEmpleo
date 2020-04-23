package jsf.classes;

import control.AccesoBean;
import controller.Usuario;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.UsuarioFacade;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.ValidatorException;
import org.apache.commons.codec.binary.Base64;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private sessions.beans.UsuarioFacade ejbFacade;
    private List<Usuario> items = null;
    private List<Usuario> listaUsuario = null;
    private Usuario selected;
    private String claveAntigua = "";
    private final String key = "empleo";
    private String claveNueva = "";
    private String mensaje = "";
    private boolean ver;
    private String mensaje2 = "";
    private boolean ver2;

    public UsuarioController() {
    }

    public Usuario getSelected() {
        if ("ESTUDIANTE".equals(AccesoBean.obtenerIdPersona().getIdRol().getRol())) {
            this.selected = AccesoBean.obtenerIdPersona();
        }
        if ("EMPRESA".equals(AccesoBean.obtenerIdPersona().getIdRol().getRol())) {
            this.selected = AccesoBean.obtenerIdPersona();
        }
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getClaveNueva() {
        return claveNueva;
    }

    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer(boolean ver) {
        this.ver = ver;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }

    public boolean isVer2() {
        return ver2;
    }

    public void setVer2(boolean ver2) {
        this.ver2 = ver2;
    }

    public String getClaveAntigua() {
        return claveAntigua;
    }

    public void setClaveAntigua(String claveAntigua) {
        this.claveAntigua = claveAntigua;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setClaveCifrada(encriptar(selected.getClave()));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        this.selected.setClaveCifrada(encriptar(selected.getClave()));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void update2() {
        this.selected.setClaveCifrada(encriptar(selected.getClave()));
        persist(PersistAction.UPDATE, "Clave actualizada con éxito.");
    }

    public void update3() {
        persist(PersistAction.UPDATE, "Usuario actualizado con éxito.");
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getIdUsuario());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
        if (((String) arg2).length() < 5) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Ingrese al menos 5 caracteres", ""));
        }
    }

    public void existeUsuario() {
        Usuario u = getFacade().existeUsuarioRegistrado(selected.getUsuario());
        if (u != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario ya existe.Ingrese otro.", ""));
        } else {
            create();
        }
    }

    public void existeUsuario2() {
        Usuario u = getFacade().existeUsuarioRegistrado(selected.getUsuario());
        if (u != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario ya existe.Ingrese otro.", ""));
        } else {
            update();
        }
    }

    public void verificarClave() {
        mensaje = "";
        ver = false;
        if (selected.getClave() == null || "".equals(selected.getClave().trim())) {
            ver = false;
            mensaje = "Complete el campo clave. Al menos 6 caracteres";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Complete el campo clave.", ""));
            limpiar1();
        } else {
            if (selected.getClave().length() <= 5) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Clave nueva muy corta. Ingrese al menos 6 caracteres", ""));
            } else {
                if (!encriptar(claveAntigua).equals(AccesoBean.usuarioLogueadoClave())) {
                    ver = false;
                    mensaje = "Clave actual incorrecta.";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Clave actual incorrecta", ""));
                    limpiar1();
                } else {
                    ver = true;
                    mensaje = "Clave actualizada con éxito.";
                    update2();
                    limpiar1();
                }
            }
        }
    }

    public void limpiar1() {
        new Thread(() -> {
            try {
                Thread.sleep(500);
                mensaje = "";
                ver = false;
                claveAntigua = null;
            } catch (InterruptedException ex) {
            }
        }).start();
    }

    public void verficarUsuario() {
        mensaje2 = "";
        ver2 = false;
        if (selected.getUsuario() == null || "".equals(selected.getUsuario().trim())) {
            ver2 = false;
            mensaje2 = "Complete el campo usuario. Al menos 6 caracteres";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Complete el campo usuario.", ""));
            limpiar2();
        } else {
            if (selected.getUsuario().length() <= 5) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario muy corto. Ingrese al menos 6 caracteres", ""));
            } else {
                Usuario u = getFacade().existeUsuarioRegistrado(selected.getUsuario());
                if (u != null) {
                    ver2 = false;
                    mensaje2 = "El usuario ya existe. Ingrese otro.";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario ya existe. Ingrese otro.", ""));
                    limpiar2();
                } else {
                    ver2 = true;
                    mensaje2 = "Usuario actualizado con éxito";
                    update3();
                    limpiar2();
                }
            }
        }
    }

    public void limpiar2() {
        new Thread(() -> {
            try {
                Thread.sleep(500);
                mensaje2 = "";
                ver2 = false;
            } catch (InterruptedException ex) {
            }
        }).start();
    }

    public String encriptar(String pass) {
        String encri = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] llavePass = md.digest(key.getBytes("utf-8"));
            byte[] bytesPass = Arrays.copyOf(llavePass, 24);
            SecretKey secretKey = new SecretKeySpec(bytesPass, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] plainTextBytes = pass.getBytes("utf-8");
            byte[] buf = cifrado.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            encri = new String(base64Bytes);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println("Error: " + e);
        }
        return encri;
    }

    public String descencriptar(String pass) {
        String descencri = "";
        try {
            byte[] m = Base64.decodeBase64(pass.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestPass = md.digest(key.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestPass, 24);
            SecretKey secretKey = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] plainText = decipher.doFinal(m);
            descencri = new String(plainText, "UTF-8");

        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            System.out.println("Error: " + e);
        }
        return descencri;
    }
}
