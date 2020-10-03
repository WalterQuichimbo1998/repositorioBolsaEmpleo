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
    private boolean isLoggedIn;
//    private final String key = "@ISTL_2020";

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
            Usuario us = getEjbFacade().validarUsuarioSesion(user.trim());
            if (us != null) {
                if (us.getUsuario().equals(user.trim())) {
                    if (us.getClave().equals(pass.trim())) {
                        if ("ADMINISTRADOR".equals(us.getRol())) {
                            isLoggedIn = true;
                            try {
                                asignarRecursoWeb("/administrador/administrador.xhtml", us);
                            } catch (IOException ex) {
                                Logger.getLogger(AccesoBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                        }
                        if ("ESTUDIANTE".equals(us.getRol())) {
                            isLoggedIn = true;
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
                            isLoggedIn = true;
                            try {
                                asignarRecursoWeb("/empleador/Empleador.xhtml", us);
                            } catch (IOException ex) {
                                Logger.getLogger(AccesoBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                        }
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Clave incorrecta", ""));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario incorrecto", ""));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Usuario no existe", ""));
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
        isLoggedIn = false;
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        extContext.getSessionMap().remove(USER_KEY);
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        String url = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/index.xhtml"));
        extContext.redirect(url);

    }

    public void isLogged() throws IOException {
        if (isLoggedIn) {
              FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if ("ESTUDIANTE".equals(us.getRol())) {
                 extContext.redirect("/estudiante/Estudiante.xhtml");
                /*asignarRecursoWeb("/estudiante/Estudiante.xhtml", us);*/
            }
            if ("EMPLEADOR".equals(us.getRol())) {
                extContext.redirect("/empleador/Empleador.xhtml");
            }
            if ("ADMINISTRADOR".equals(us.getRol())) {
                extContext.redirect("/administrador/administrador.xhtml");
            }
        }
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

    public HojaVidaEstudiante hojaVida() {
        HojaVidaEstudiante hv = null;
        Usuario uus = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if ("ESTUDIANTE".equals(uus.getRol())) {
            hv = (HojaVidaEstudiante) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("hojaVida");
        } else {
            hv = null;
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
        return us.getClave();
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
