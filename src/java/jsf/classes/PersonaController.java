package jsf.classes;

import control.AccesoBean;
import control.UtilPath;
import controller.Canton;
import controller.HojaVidaEstudiante;
import controller.Parroquia;
import controller.Persona;
import controller.Provincia;
import controller.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.PersonaFacade;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Base64;
import org.primefaces.model.UploadedFile;

@Named("personaController")
@SessionScoped
public class PersonaController implements Serializable {

    @EJB
    private sessions.beans.PersonaFacade ejbFacade;
    @EJB
    private sessions.beans.ProvinciaFacade ejbFacadeP;
    @EJB
    private sessions.beans.CantonFacade ejbFacadeC;
    @EJB
    private sessions.beans.ParroquiaFacade ejbFacadePa;
    private List<Persona> items = null;
    private List<Persona> lista = null;
    private List<Persona> listaPersona = null;
    private Persona selected;
    private Integer id;
    private Integer id2;
    private UploadedFile file;
    private String destino;
    private List<Provincia> listaProvincias = null;
    private List<Canton> listaCanton = null;
    private List<Parroquia> listaParroquia = null;
    private String nombre = "";
    private String correo;
    private String cedula;
    private String nom_user;
    private String m_u;
    private String mensaje;
    private boolean cc;
    private boolean cc_2;
    private boolean cc_3;
    private Persona per = null;
    private final String key = "empleo";

    public PersonaController() {
    }

    public Persona getSelected() {
        if ("ESTUDIANTE".equals(AccesoBean.obtenerIdPersona().getIdRol().getRol())) {
            this.selected = AccesoBean.obtenerIdPersona().getIdPersona();
        }
        if ("EMPRESA".equals(AccesoBean.obtenerIdPersona().getIdRol().getRol())) {
            this.selected = AccesoBean.obtenerIdPersona().getIdPersona();
        }
        return selected;
    }

    public void setSelected(Persona selected) {
        this.selected = selected;

    }

    public Integer getId2() {
        return id2;
    }

    public void setId2(Integer id2) {
        this.id2 = id2;
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

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonaFacade getFacade() {
        return ejbFacade;
    }

    public void setLista(List<Persona> lista) {
        this.lista = lista;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    /*--------Metodos accesores para la busqueda*/
    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public boolean isCc() {
        return cc;
    }

    public void setCc(boolean cc) {
        this.cc = cc;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getM_u() {
        return m_u;
    }

    public void setM_u(String m_u) {
        this.m_u = m_u;
    }

    public boolean isCc_2() {
        return cc_2;
    }

    public void setCc_2(boolean cc_2) {
        this.cc_2 = cc_2;
    }

    public boolean isCc_3() {
        return cc_3;
    }

    public void setCc_3(boolean cc_3) {
        this.cc_3 = cc_3;
    }

    public Persona prepareCreate() {
        selected = new Persona();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {

        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PersonaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, "Persona actualizada correctamente.");
    }

    public void datosActualizados() {
        persist(PersistAction.UPDATE, "Datos actualizados con éxito.");
    }

    public void actualizarFoto() {
        persist(PersistAction.UPDATE, "Foto subida con éxito.");
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PersonaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Persona> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Persona> getLista() {
        lista = getFacade().listaPersona(id);
        return lista;
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

    public Persona getPersona(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Persona> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Persona> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Persona> getListaEstudiante() {
        return getFacade().listaEstudiantes();
    }

    @FacesConverter(forClass = Persona.class)
    public static class PersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaController controller = (PersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaController");
            return controller.getPersona(getKey(value));
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
            if (object instanceof Persona) {
                Persona o = (Persona) object;
                return getStringKey(o.getIdPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Persona.class.getName()});
                return null;
            }
        }

    }

    public String fecha(Date d) {
        String resultado = "";
        if (d != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            resultado = formato.format(d);
        } else {
            resultado = "";
        }
        return resultado;
    }

    public String fecha2(Date d) {
        String resultado = "";
        if (d != null) {
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            resultado = formato.format(d);
        } else {
            resultado = "";
        }
        return resultado;
    }

    public String existePerfilPersona(Persona id) {
        HojaVidaEstudiante h = getFacade().buscarIdPersona(id);
        String m = "";
        if (h != null) {
            m = "Si";
        } else {
            m = "No";
        }
        return m;
    }

    public Integer existePerfilPersona2(Persona id) {
        HojaVidaEstudiante h = getFacade().buscarIdPersona(id);
        Integer n = null;
        if (h != null) {
            n = 1;
        } else {
            n = null;
        }
        return n;
    }
//Administrador

    public void existePersona() {
        Persona p = getFacade().existePersonaRegistrada(selected.getCedula(), selected.getCorreo());
        if (p != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "La cédula o correo ya se encuentra registrada", ""));
        } else {
            create();
        }
    }

    boolean f = false;

    public void subirFoto() {
        String extension = "";
        if (file == null || file.getSize() == 0) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione una Foto", ""));

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
                if (file.getSize() <= 3072000) {
                    try {
                        if (selected.getNombre() == null || "".equals(selected.getNombre())) {
                            nombre = getSelected().getIdPersona() + "_Usuario" + extension;
                        } else {
                            nombre = getSelected().getIdPersona() + "_" + selected.getNombre() + extension;
                        }
                        selected.setFoto("foto/" + nombre);
                        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                        String realPath = UtilPath.getPathDefinida(ec.getRealPath("/"));
                        String pathDefinition = realPath + File.separator + "web" + File.separator + "resources" + File.separator + "foto" + File.separator + nombre;
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
                            actualizarFoto();
                        }

                    } catch (IOException e) {
                    }
                } else {
                    this.setFile(null);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Foto muy pesada elija otra. Tamaño máximo: 3Mb", ""));
                }
            } else {
                this.setFile(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Formato no válido. Use formato .png o .jpg", ""));
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

    public void recuperarClave() {
        if(correo==null || "".equals(correo) || cedula==null || "".equals(cedula)){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Complete los campos", ""));
        }else{
        per = getFacade().buscarCorreo(getCedula(), getCorreo());
        mensaje = "";
        cc = false;
        if (per != null) {
            cc = true;
            buscarUser();
        } else {
            cc = false;
            mensaje = "Correo electrónico o Cédula no registrado";
            tiempoLimpiar();
        }
        }
    }

    public void buscarUser() {
        Usuario user = getFacade().buscarUser(per);
        if (user != null) {
            mensaje = "Revise su correo electrónico";
            enviarCorreo(user.getClave());
            tiempoLimpiar();
        } else {
            cc = false;
            mensaje = "Usuario no encontrado";
            tiempoLimpiar();
        }
    }

    public void tiempoLimpiar() {
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                mensaje = "";
                cc = false;
                per = null;
                correo = null;
                cedula = null;
            } catch (InterruptedException ex) {
            }
        }).start();
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

    public void enviarCorreo(String clave) {

        final String remitente = "empleoistl2020@gmail.com";
        final String r_clave = "@empleo_istl_2020";
        String receptor = getCorreo();
        Properties prop = new Properties();

        try {
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.from", "empleoistl2020@gmail.com");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.setProperty("mail.debug", "true");

            Session session = Session.getInstance(prop, null);
            MimeMessage msg = new MimeMessage(session);

            msg.setRecipients(Message.RecipientType.TO, receptor);
            msg.setSubject("Clave de acceso recuperada del sistema de Bolsa de Empleo");
            msg.setSentDate(new Date());
            msg.setContent("<p>Su clave de acceso es: <h2>" + clave + "</h2></p>", "text/html");
            Transport trans = session.getTransport("smtp");
            trans.connect(remitente, r_clave);
            trans.sendMessage(msg, msg.getAllRecipients());
            trans.close();
        } catch (MessagingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
