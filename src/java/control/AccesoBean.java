package control;

import modelo.HojaVidaEstudiante;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import sessions.beans.HojaVidaEstudianteFacade;
import sessions.beans.UsuarioFacade;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author WALTER QUICHIMBO
 */
@Named(value = "accesoBean")
@SessionScoped
public class AccesoBean implements Serializable {  

    public final static String USER_KEY = "auth_user";
    @EJB
    private UsuarioFacade ejbFacade;
    @EJB
    private HojaVidaEstudianteFacade hojaFacade;
    private String user;
    private String pass;
    private final String key = "empleo";
   
    public AccesoBean() {
    }

    public UsuarioFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsuarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public void login() {
        if (user.trim() == null || "".equals(user.trim()) || pass.trim() == null || "".equals(pass.trim())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Complete los campos correspondientes", ""));
        } else {
            Usuario us = getEjbFacade().validarUsuario2(user.trim(), encriptar(pass.trim()));
            if (us != null) {
                if ("ADMINISTRADOR".equals(us.getRol())) {
                    try {
                        asignarRecursoWeb("/administrador/administrador.xhtml", us);
                    } catch (IOException ex) {
                        Logger.getLogger(AccesoBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                }
                if ("ESTUDIANTE".equals(us.getRol())) {
                    HojaVidaEstudiante hv = null;
                    try {
                        hv = hojaFacade.buscarIdPersona(us.getIdPersona());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        hv = null;
                    }
                    if (hv != null) {
                        try {
                            asignarRecursoWeb("/estudiante/Estudiante.xhtml", us);
                        } catch (IOException ex) {
                            Logger.getLogger(AccesoBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            asignarRecursoWeb("/estudiante2/Estudiante_D.xhtml", us);
                        } catch (IOException ex) {
                            Logger.getLogger(AccesoBean.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("hojaVida", hv);
                }
                if ("EMPLEADOR".equals(us.getRol())) {
                    try {
                        asignarRecursoWeb("/empleador/Empleador.xhtml", us);
                    } catch (IOException ex) {
                        Logger.getLogger(AccesoBean.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Credenciales incorrectas", ""));
            }
        }
    }

    public void asignarRecursoWeb(String path, Usuario us) throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        String url = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, path));
        extContext.getSessionMap().put(USER_KEY, us);
        extContext.redirect(url);
    }

    public void logoutAdmin() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        extContext.getSessionMap().remove(USER_KEY);
         HttpSession session = SessionUtils.getSession();
        session.invalidate();
        String url = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/index.xhtml"));
        extContext.redirect(url);
       
    }

    public Usuario userAdmin() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us;
    }
    public String usuarioLogueado() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getIdPersona().getNombre() + " " + us.getIdPersona().getApellido();
    }
    public String usuarioNavegacion() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getRol();
    }
  public  HojaVidaEstudiante hojaVida() {
        HojaVidaEstudiante hv = null;
             Usuario uus = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
                if("ESTUDIANTE".equals(uus.getRol())){
                   hv = (HojaVidaEstudiante) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hojaVida");
                }else{
                    hv=null;
                }
        return hv;
    }
    public String usuarioLogueado2() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getUsuario();
    }
    public String userFoto() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getIdPersona().getFoto();
    }

    public static String usuarioLogueadoClave() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getClaveCifrada();
    }

    public static Usuario obtenerIdPersona() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us;
    }

    public static HojaVidaEstudiante obtenerIdHojaVida() {
        HojaVidaEstudiante hv = null;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hojaVida") != null) {
            hv = (HojaVidaEstudiante) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hojaVida");
        }
        return hv;
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

}
