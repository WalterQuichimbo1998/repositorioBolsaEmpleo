/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import modelo.Carrera;
import modelo.Persona;
import modelo.Promocion;

/**
 *
 * @author Walter Quichimbo
 */
@Named(value = "informacion")
@SessionScoped
public class informacion implements Serializable {

    private Carrera carrera = null;
    private Promocion promocion = null;
    private String mensaje;
    private String link;
    @EJB
    private sessions.beans.PersonaFacade facadePersona;

    public informacion() {
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
//    public void mandarCorreo() throws IOException {
//  // El correo gmail de envío
//  String correoEnvia = "empleoistl2020@gmail.com";
//  String claveCorreo = "awtzsoyoljctflic";
// 
//  // La configuración para enviar correo
//  Properties properties = new Properties();
//  properties.put("mail.smtp.host", "smtp.gmail.com");
//  properties.put("mail.smtp.starttls.enable", "true");
//  properties.put("mail.smtp.port", "587");
//  properties.put("mail.smtp.auth", "true");
//  properties.put("mail.user", correoEnvia);
//  properties.put("mail.password", claveCorreo);
// 
//  // Obtener la sesion
//  Session session = Session.getInstance(properties, null);
// 
//  try {
//   // Crear el cuerpo del mensaje
//   MimeMessage mimeMessage = new MimeMessage(session);
// 
//   // Agregar quien envía el correo
//   mimeMessage.setFrom(new InternetAddress(correoEnvia, "Sistema de Seguimiento a egresados y graduados ISTL"));
//    
//   // Los destinatarios
//   InternetAddress[] internetAddresses = {
//     new InternetAddress("walterquichimbo@gmail.com")};
// 
//   // Agregar los destinatarios al mensaje
//   mimeMessage.setRecipients(Message.RecipientType.TO,
//     internetAddresses);
// 
//   // Agregar el asunto al correo
//   mimeMessage.setSubject("Enviando Correo.");
// 
//   // Creo la parte del mensaje
//   MimeBodyPart mimeBodyPart = new MimeBodyPart();
//   mimeBodyPart.setText("Envío el correo.");
// 
//   // Crear el multipart para agregar la parte del mensaje anterior
//   Multipart multipart = new MimeMultipart();
//   multipart.addBodyPart(mimeBodyPart);
// 
//   // Agregar el multipart al cuerpo del mensaje
//   mimeMessage.setContent(multipart);
//   MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
//mimeBodyPartAdjunto.attachFile("C:\\Users\\Cyber Más\\Desktop\\FICHA_ENCUENTROS.xlsx");
//// Resto del codigo...
////
////
//// Agregarlo al MultiPart
//multipart.addBodyPart(mimeBodyPartAdjunto);
// 
//   // Enviar el mensaje
//   Transport transport = session.getTransport("smtp");
//   transport.connect(correoEnvia, claveCorreo);
//   transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
//   transport.close();
// 
//  } catch (UnsupportedEncodingException | MessagingException ex) {
//  }
//  System.out.println("Correo enviado");
// }
// 
    public void mandarCorreoInformativo1(InternetAddress[] address) {
        // El correo gmail de envío
        String correoEnvia = "empleoistl2020@gmail.com";
        String claveCorreo = "awtzsoyoljctflic";
        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);
        // Obtener la sesion
        Session session = Session.getInstance(properties, null);
        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);
            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "ISTL"));
//            // Los destinatarios
//            InternetAddress[] internetAddresses = {new InternetAddress(
//                "walterquichimbo@gmail.com")};
            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO, address);
            // Agregar el asunto al correo
            mimeMessage.setSubject("Sistema Bolsa Empleo ISTL");
            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            String cuerpo = cuerpoMensaje();
            String absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/logo_istl/");
            // Url del directorio donde estan las imagenes
            String urlImagenes = absolutePath + "/";
            File directorio = new File(urlImagenes);
            // Obtener los nombres de las imagenes en el directorio
            String[] imagenesDirectorio = directorio.list();
            // Creo la parte del mensaje HTML
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(cuerpo, "text/html;charset=ISO-8859-1");
            // Validar que el directorio si tenga las imagenes
            if (imagenesDirectorio != null) {
                for (int count = 0; count < imagenesDirectorio.length; count++) {
                    MimeBodyPart imagePart = new MimeBodyPart();
                    imagePart.setHeader("Content-ID", "<"
                            + imagenesDirectorio[count] + ">");
                    imagePart.setDisposition(MimeBodyPart.INLINE);
                    imagePart.attachFile(urlImagenes
                            + imagenesDirectorio[count]);
                    multipart.addBodyPart(imagePart);
                }
            } else {
                System.out.println("No hay imagenes en el directorio");
            }
            // Agregar la parte del mensaje HTML al multiPart
            multipart.addBodyPart(mimeBodyPart);
            mimeMessage.setContent(multipart);
            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

        } catch (IOException | MessagingException ex) {
            System.out.println("error: " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al enviar la iformación.", ""));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Envió de información exitoso.", ""));
    }

    public void mandarCorreoInformativo2(InternetAddress[] address) {
        // El correo gmail de envío
        String correoEnvia = "empleoistl2020@gmail.com";
        String claveCorreo = "awtzsoyoljctflic";
        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);
        // Obtener la sesion
        Session session = Session.getInstance(properties, null);
        try {
            // Crear el cuerpo del mensaje
            MimeMessage mimeMessage = new MimeMessage(session);
            // Agregar quien envía el correo
            mimeMessage.setFrom(new InternetAddress(correoEnvia, "ISTL"));
//            // Los destinatarios
//            InternetAddress[] internetAddresses = {new InternetAddress(
//                "walterquichimbo@gmail.com")};
            // Agregar los destinatarios al mensaje
            mimeMessage.setRecipients(Message.RecipientType.TO, address);
            // Agregar el asunto al correo
            mimeMessage.setSubject("Sistema Bolsa Empleo ISTL");
            // Crear el multipart para agregar la parte del mensaje anterior
            Multipart multipart = new MimeMultipart();
            String cuerpo = cuerpoMensajeLink();
            String absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/logo_istl/");
            // Url del directorio donde estan las imagenes
            String urlImagenes = absolutePath + "/";
            File directorio = new File(urlImagenes);
            // Obtener los nombres de las imagenes en el directorio
            String[] imagenesDirectorio = directorio.list();
            // Creo la parte del mensaje HTML
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(cuerpo, "text/html;charset=ISO-8859-1");
            // Validar que el directorio si tenga las imagenes
            if (imagenesDirectorio != null) {
                for (int count = 0; count < imagenesDirectorio.length; count++) {
                    MimeBodyPart imagePart = new MimeBodyPart();
                    imagePart.setHeader("Content-ID", "<"
                            + imagenesDirectorio[count] + ">");
                    imagePart.setDisposition(MimeBodyPart.INLINE);
                    imagePart.attachFile(urlImagenes
                            + imagenesDirectorio[count]);
                    multipart.addBodyPart(imagePart);
                }
            } else {
                System.out.println("No hay imagenes en el directorio");
            }
            // Agregar la parte del mensaje HTML al multiPart
            multipart.addBodyPart(mimeBodyPart);
            mimeMessage.setContent(multipart);
            // Enviar el mensaje
            Transport transport = session.getTransport("smtp");
            transport.connect(correoEnvia, claveCorreo);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();

        } catch (IOException | MessagingException ex) {
            System.out.println("error: " + ex.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ocurrio un error al enviar la iformación.", ""));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Envió de información exitoso.", ""));
    }

    public void enviarCorreo() throws AddressException {
        if (carrera != null && promocion != null) {
//            List<Persona> lista = new ArrayList<>();
        List<String> lista = new ArrayList<>();
        lista.add("walterquichimbo@gmail.com");
//            lista = facadePersona.listaPersonasCarreraPromocion(carrera.getIdCarrera(), promocion.getIdPromocion());
            if(lista!=null){
                InternetAddress[] address = new InternetAddress[lista.size()];
            for (int i = 0; i < lista.size(); i++) {
                    address[i] = new InternetAddress(lista.get(i));
            }
//            if(lista!=null){
//                InternetAddress[] address = new InternetAddress[lista.size()];
//            for (int i = 0; i < lista.size(); i++) {
//                if (lista.get(i).getCorreo() != null || !"".equals(lista.get(i).getCorreo())) {
//                    address[i] = new InternetAddress(lista.get(i).getCorreo());
//                }
//            }
            if (link == null || "".equals(link)) {
                mandarCorreoInformativo1(address);
            } else {
                mandarCorreoInformativo2(address);
            }
            this.setCarrera(null);
            this.setPromocion(null);
            this.setMensaje("");
            this.setLink(""); 
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No hay destinatarios para enviar la información", ""));
            }
           
        }

    }

    public String cuerpoMensaje() {
        String cuerpo = "<!DOCTYPE html\n"
                + "  PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n"
                + "  <style type=\"text/css\">\n"
                + "    img {\n"
                + "      max-width: 600px;outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;\n"
                + "    }\n"
                + "    a img {\n"
                + "      border: none;\n"
                + "    }\n"
                + "    table {\n"
                + "      border-collapse: collapse !important;\n"
                + "    }\n"
                + "    #outlook a {\n"
                + "      padding: 0;\n"
                + "    }\n"
                + "    .ReadMsgBody {\n"
                + "      width: 100%;\n"
                + "    }\n"
                + "    .ExternalClass {\n"
                + "      width: 100%;\n"
                + "    }\n"
                + "    .backgroundTable {\n"
                + "      margin: 0 auto;padding: 0;width: 100% !important;\n"
                + "    }\n"
                + "    table td {\n"
                + "      border-collapse: collapse;\n"
                + "    }\n"
                + "    .ExternalClass * {\n"
                + "      line-height: 115%;\n"
                + "    }\n"
                + "    .container-for-gmail-android {\n"
                + "      min-width: 600px;\n"
                + "    }\n"
                + "    /* General styling */\n"
                + "    * {\n"
                + "      font-family: Helvetica, Arial, sans-serif;\n"
                + "    }\n"
                + "    body {\n"
                + "      -webkit-font-smoothing: antialiased;-webkit-text-size-adjust: none;width: 100% !important;margin: 0 !important;height: 100%;color: #676767;\n"
                + "    }\n"
                + "    td {\n"
                + "      font-family: Helvetica, Arial, sans-serif;font-size: 14px;color: #777777;text-align: center;line-height: 21px;\n"
                + "    }\n"
                + "    a {\n"
                + "      color: #ff6f6f;font-weight: bold;text-decoration: none !important;\n"
                + "    }\n"
                + "    .pull-left {\n"
                + "      text-align: left;\n"
                + "    }\n"
                + "    .pull-right {\n"
                + "      text-align: right;\n"
                + "    }\n"
                + "    .header-lg,\n"
                + "    .header-md,\n"
                + "    .header-sm {\n"
                + "      font-size: 22px;font-weight: 700;line-height: normal;padding: 10px 0 0;color: #4d4d4d;\n"
                + "    }\n"
                + "    .header-md {\n"
                + "      font-size: 24px;\n"
                + "    }\n"
                + "    .header-sm {\n"
                + "      padding: 5px 0;font-size: 18px;line-height: 1.3;\n"
                + "    }\n"
                + "    .content-padding {\n"
                + "      padding: 20px 0 30px;\n"
                + "    }\n"
                + "    .mobile-header-padding-right {\n"
                + "      width: 290px;text-align: right;padding-left: 10px;\n"
                + "    }\n"
                + "    .mobile-header-padding-left {\n"
                + "      width: 290px;text-align: left;padding-left: 10px;\n"
                + "    }\n"
                + "    .free-text {\n"
                + "      width: 100% !important;padding: 10px 60px 0px;\n"
                + "    }\n"
                + "    .block-rounded {\n"
                + "      border-radius: 5px;border: 1px solid #e5e5e5;vertical-align: top;\n"
                + "    }\n"
                + "    .button {\n"
                + "      padding: 30px 0 0;\n"
                + "    }\n"
                + "    .info-block {\n"
                + "      padding: 0 20px;width: 260px;\n"
                + "    }\n"
                + "    .mini-block-container {\n"
                + "      padding: 30px 50px;width: 500px;\n"
                + "    }\n"
                + "    .mini-block {\n"
                + "      background-color: #ffffff;width: 498px;border: 1px solid #cccccc;border-radius: 5px;padding: 45px 45px;\n"
                + "    }\n"
                + "    .block-rounded {\n"
                + "      width: 260px;\n"
                + "    }\n"
                + "    .info-img {\n"
                + "      width: 258px;border-radius: 5px 5px 0 0;\n"
                + "    }\n"
                + "    .force-width-img {\n"
                + "      width: 480px;height: 1px !important;\n"
                + "    }\n"
                + "    .force-width-full {\n"
                + "      width: 600px;height: 1px !important;\n"
                + "    }\n"
                + "    .user-img img {\n"
                + "      width: 130px;border-radius: 5px;border: 1px solid #cccccc;\n"
                + "    }\n"
                + "    .user-img {\n"
                + "      text-align: center;border-radius: 100px;color: #ff6f6f;font-weight: 700;\n"
                + "    }\n"
                + "    .user-msg {\n"
                + "      padding-top: 10px;font-size: 14px;text-align: center;font-style: italic;\n"
                + "    }\n"
                + "    .mini-img {\n"
                + "      padding: 5px;width: 140px;\n"
                + "    }\n"
                + "    .mini-img img {\n"
                + "      border-radius: 5px;width: 140px;\n"
                + "    }\n"
                + "    .force-width-gmail {\n"
                + "      min-width: 600px;height: 0px !important;line-height: 1px !important;font-size: 1px !important;\n"
                + "    }\n"
                + "    .mini-imgs {\n"
                + "      padding: 25px 0 30px;\n"
                + "    }\n"
                + "  </style>\n"
                + "  <style type=\"text/css\" media=\"screen\">\n"
                + "    @import url(http://fonts.googleapis.com/css?family=Oxygen:400,700);\n"
                + "  </style>\n"
                + "  <style type=\"text/css\" media=\"screen\">\n"
                + "    @media screen {\n"
                + "      /* Thanks Outlook 2013! */\n"
                + "      * {\n"
                + "        font-family: 'Oxygen', 'Helvetica Neue', 'Arial', 'sans-serif' !important;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "  <style type=\"text/css\" media=\"only screen and (max-width: 480px)\">\n"
                + "    /* Mobile styles */\n"
                + "    @media only screen and (max-width: 480px) {\n"
                + "      table[class*=\"container-for-gmail-android\"] {\n"
                + "        min-width: 290px !important;width: 100% !important;\n"
                + "      }\n"
                + "      table[class=\"w320\"] {\n"
                + "        width: 320px !important;\n"
                + "      }\n"
                + "      img[class=\"force-width-gmail\"] {\n"
                + "        display: none !important;width: 0 !important;height: 0 !important;\n"
                + "      }\n"
                + "      td[class*=\"mobile-header-padding-left\"] {\n"
                + "        width: 160px !important;padding-left: 0 !important;\n"
                + "      }\n"
                + "      td[class*=\"mobile-header-padding-right\"] {\n"
                + "        width: 160px !important;padding-right: 0 !important;\n"
                + "      }\n"
                + "      td[class=\"mobile-block\"] {\n"
                + "        display: block !important;\n"
                + "      }\n"
                + "      td[class=\"mini-img\"],\n"
                + "      td[class=\"mini-img\"] img {\n"
                + "        width: 150px !important;\n"
                + "      }\n"
                + "      td[class=\"header-lg\"] {\n"
                + "        font-size: 22px !important;padding-bottom: 5px !important;\n"
                + "      }\n"
                + "      td[class=\"header-md\"] {\n"
                + "        font-size: 18px !important;padding-bottom: 5px !important;\n"
                + "      }\n"
                + "      td[class=\"content-padding\"] {\n"
                + "        padding: 5px 0 30px !important;\n"
                + "      }\n"
                + "      td[class=\"button\"] {\n"
                + "        padding: 5px !important;\n"
                + "      }\n"
                + "      td[class*=\"free-text\"] {\n"
                + "        padding: 10px 18px 30px !important;\n"
                + "      }\n"
                + "      img[class=\"force-width-img\"],\n"
                + "      img[class=\"force-width-full\"] {\n"
                + "        display: none !important;\n"
                + "      }\n"
                + "      td[class=\"info-block\"] {\n"
                + "        display: block !important;width: 280px !important;padding-bottom: 40px !important;\n"
                + "      }\n"
                + "      td[class=\"info-img\"],\n"
                + "      img[class=\"info-img\"] {\n"
                + "        width: 278px !important;\n"
                + "      }\n"
                + "      td[class=\"mini-block-container\"] {\n"
                + "        padding: 8px 20px !important;\n"
                + "        width: 280px !important;\n"
                + "      }\n"
                + "      td[class=\"mini-block\"] {\n"
                + "        padding: 20px !important;\n"
                + "      }\n"
                + "      td[class=\"user-img\"] {\n"
                + "        display: block !important;text-align: center !important;width: 100% !important;padding-bottom: 10px;\n"
                + "      }\n"
                + "      td[class=\"user-msg\"] {\n"
                + "        display: block !important;padding-bottom: 20px;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "</head>\n"
                + "<body bgcolor=\"#f7f7f7\">\n"
                + "  <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"container-for-gmail-android\" width=\"100%\">\n"
                + "    <tr>\n"
                + "      <td align=\"center\" valign=\"top\" width=\"100%\" style=\"background-color: #f7f7f7;\" class=\"content-padding\">\n"
                + "        <center>\n"
                + "          <table cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"w320\">\n"
                + "            <tr>\n"
                + "              <td class=\"mini-block-container\">\n"
                + "                <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"\n"
                + "                  style=\"border-collapse:separate !important;\">\n"
                + "                  <tr>\n"
                + "                    <td class=\"mini-block\">\n"
                + "                      <table cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">\n"
                + "                        <tr>\n"
                + "                          <td class=\"header-lg\">\n"
                + "                            INSTITUTO SUPERIOR TECNOLÓGICO \"LIMÓN\"\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                            <tr>\n"
                + "                              <td>\n"
                + "                                <br/>\n"
                + "                            <table cellspacing=\"1\" cellpadding=\"1\" width=\"100%\">\n"
                + "                              <tr style=\"text-align: center\">\n"
                + "                                <img class=\"user-img\" src=\"cid:logo.png\" alt=\"user img\" style=\"width: 150px;\" />\n"
                + "                            \n"
                + "                                <br /><p style=\"color: teal;text-transform: uppercase;\">Sistema Bolsa Empleo</p>\n"
                + "                              </tr>\n"
                + "                                   \n"
                + "                              <tr>\n"
                + "                                <td style=\"text-align: justify;\">\n"
                + "                                  <p>\n" + mensaje + "\n"
                + "                                  </p>\n"
                + "                                </td>\n"
                + "                              </tr>\n"
                + "                            </table>\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "              </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "        </center>\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </table>\n"
                + "</body>\n"
                + "</html>";
        return cuerpo;
    }

    public String cuerpoMensajeLink() {
        String cuerpo = "<!DOCTYPE html\n"
                + "  PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n"
                + "  <style type=\"text/css\">\n"
                + "    img {\n"
                + "      max-width: 600px;outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;\n"
                + "    }\n"
                + "    a img {\n"
                + "      border: none;\n"
                + "    }\n"
                + "    table {\n"
                + "      border-collapse: collapse !important;\n"
                + "    }\n"
                + "    #outlook a {\n"
                + "      padding: 0;\n"
                + "    }\n"
                + "    .ReadMsgBody {\n"
                + "      width: 100%;\n"
                + "    }\n"
                + "    .ExternalClass {\n"
                + "      width: 100%;\n"
                + "    }\n"
                + "    .backgroundTable {\n"
                + "      margin: 0 auto;padding: 0;width: 100% !important;\n"
                + "    }\n"
                + "    table td {\n"
                + "      border-collapse: collapse;\n"
                + "    }\n"
                + "    .ExternalClass * {\n"
                + "      line-height: 115%;\n"
                + "    }\n"
                + "    .container-for-gmail-android {\n"
                + "      min-width: 600px;\n"
                + "    }\n"
                + "    /* General styling */\n"
                + "    * {\n"
                + "      font-family: Helvetica, Arial, sans-serif;\n"
                + "    }\n"
                + "    body {\n"
                + "      -webkit-font-smoothing: antialiased;-webkit-text-size-adjust: none;width: 100% !important;margin: 0 !important;height: 100%;color: #676767;\n"
                + "    }\n"
                + "    td {\n"
                + "      font-family: Helvetica, Arial, sans-serif;font-size: 14px;color: #777777;text-align: center;line-height: 21px;\n"
                + "    }\n"
                + "    a {\n"
                + "      color: #ff6f6f;font-weight: bold;text-decoration: none !important;\n"
                + "    }\n"
                + "    .pull-left {\n"
                + "      text-align: left;\n"
                + "    }\n"
                + "    .pull-right {\n"
                + "      text-align: right;\n"
                + "    }\n"
                + "    .header-lg,\n"
                + "    .header-md,\n"
                + "    .header-sm {\n"
                + "      font-size: 22px;font-weight: 700;line-height: normal;padding: 10px 0 0;color: #4d4d4d;\n"
                + "    }\n"
                + "    .header-md {\n"
                + "      font-size: 24px;\n"
                + "    }\n"
                + "    .header-sm {\n"
                + "      padding: 5px 0;font-size: 18px;line-height: 1.3;\n"
                + "    }\n"
                + "    .content-padding {\n"
                + "      padding: 20px 0 30px;\n"
                + "    }\n"
                + "    .mobile-header-padding-right {\n"
                + "      width: 290px;text-align: right;padding-left: 10px;\n"
                + "    }\n"
                + "    .mobile-header-padding-left {\n"
                + "      width: 290px;text-align: left;padding-left: 10px;\n"
                + "    }\n"
                + "    .free-text {\n"
                + "      width: 100% !important;padding: 10px 60px 0px;\n"
                + "    }\n"
                + "    .block-rounded {\n"
                + "      border-radius: 5px;border: 1px solid #e5e5e5;vertical-align: top;\n"
                + "    }\n"
                + "    .button {\n"
                + "      padding: 30px 0 0;\n"
                + "    }\n"
                + "    .info-block {\n"
                + "      padding: 0 20px;width: 260px;\n"
                + "    }\n"
                + "    .mini-block-container {\n"
                + "      padding: 30px 50px;width: 500px;\n"
                + "    }\n"
                + "    .mini-block {\n"
                + "      background-color: #ffffff;width: 498px;border: 1px solid #cccccc;border-radius: 5px;padding: 45px 45px;\n"
                + "    }\n"
                + "    .block-rounded {\n"
                + "      width: 260px;\n"
                + "    }\n"
                + "    .info-img {\n"
                + "      width: 258px;border-radius: 5px 5px 0 0;\n"
                + "    }\n"
                + "    .force-width-img {\n"
                + "      width: 480px;height: 1px !important;\n"
                + "    }\n"
                + "    .force-width-full {\n"
                + "      width: 600px;height: 1px !important;\n"
                + "    }\n"
                + "    .user-img img {\n"
                + "      width: 130px;border-radius: 5px;border: 1px solid #cccccc;\n"
                + "    }\n"
                + "    .user-img {\n"
                + "      text-align: center;border-radius: 100px;color: #ff6f6f;font-weight: 700;\n"
                + "    }\n"
                + "    .user-msg {\n"
                + "      padding-top: 10px;font-size: 14px;text-align: center;font-style: italic;\n"
                + "    }\n"
                + "    .mini-img {\n"
                + "      padding: 5px;width: 140px;\n"
                + "    }\n"
                + "    .mini-img img {\n"
                + "      border-radius: 5px;width: 140px;\n"
                + "    }\n"
                + "    .force-width-gmail {\n"
                + "      min-width: 600px;height: 0px !important;line-height: 1px !important;font-size: 1px !important;\n"
                + "    }\n"
                + "    .mini-imgs {\n"
                + "      padding: 25px 0 30px;\n"
                + "    }\n"
                + "  </style>\n"
                + "  <style type=\"text/css\" media=\"screen\">\n"
                + "    @import url(http://fonts.googleapis.com/css?family=Oxygen:400,700);\n"
                + "  </style>\n"
                + "  <style type=\"text/css\" media=\"screen\">\n"
                + "    @media screen {\n"
                + "      /* Thanks Outlook 2013! */\n"
                + "      * {\n"
                + "        font-family: 'Oxygen', 'Helvetica Neue', 'Arial', 'sans-serif' !important;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "  <style type=\"text/css\" media=\"only screen and (max-width: 480px)\">\n"
                + "    /* Mobile styles */\n"
                + "    @media only screen and (max-width: 480px) {\n"
                + "      table[class*=\"container-for-gmail-android\"] {\n"
                + "        min-width: 290px !important;width: 100% !important;\n"
                + "      }\n"
                + "      table[class=\"w320\"] {\n"
                + "        width: 320px !important;\n"
                + "      }\n"
                + "      img[class=\"force-width-gmail\"] {\n"
                + "        display: none !important;width: 0 !important;height: 0 !important;\n"
                + "      }\n"
                + "      td[class*=\"mobile-header-padding-left\"] {\n"
                + "        width: 160px !important;padding-left: 0 !important;\n"
                + "      }\n"
                + "      td[class*=\"mobile-header-padding-right\"] {\n"
                + "        width: 160px !important;padding-right: 0 !important;\n"
                + "      }\n"
                + "      td[class=\"mobile-block\"] {\n"
                + "        display: block !important;\n"
                + "      }\n"
                + "      td[class=\"mini-img\"],\n"
                + "      td[class=\"mini-img\"] img {\n"
                + "        width: 150px !important;\n"
                + "      }\n"
                + "      td[class=\"header-lg\"] {\n"
                + "        font-size: 22px !important;padding-bottom: 5px !important;\n"
                + "      }\n"
                + "      td[class=\"header-md\"] {\n"
                + "        font-size: 18px !important;padding-bottom: 5px !important;\n"
                + "      }\n"
                + "      td[class=\"content-padding\"] {\n"
                + "        padding: 5px 0 30px !important;\n"
                + "      }\n"
                + "      td[class=\"button\"] {\n"
                + "        padding: 5px !important;\n"
                + "      }\n"
                + "      td[class*=\"free-text\"] {\n"
                + "        padding: 10px 18px 30px !important;\n"
                + "      }\n"
                + "      img[class=\"force-width-img\"],\n"
                + "      img[class=\"force-width-full\"] {\n"
                + "        display: none !important;\n"
                + "      }\n"
                + "      td[class=\"info-block\"] {\n"
                + "        display: block !important;width: 280px !important;padding-bottom: 40px !important;\n"
                + "      }\n"
                + "      td[class=\"info-img\"],\n"
                + "      img[class=\"info-img\"] {\n"
                + "        width: 278px !important;\n"
                + "      }\n"
                + "      td[class=\"mini-block-container\"] {\n"
                + "        padding: 8px 20px !important;\n"
                + "        width: 280px !important;\n"
                + "      }\n"
                + "      td[class=\"mini-block\"] {\n"
                + "        padding: 20px !important;\n"
                + "      }\n"
                + "      td[class=\"user-img\"] {\n"
                + "        display: block !important;text-align: center !important;width: 100% !important;padding-bottom: 10px;\n"
                + "      }\n"
                + "      td[class=\"user-msg\"] {\n"
                + "        display: block !important;padding-bottom: 20px;\n"
                + "      }\n"
                + "    }\n"
                + "  </style>\n"
                + "</head>\n"
                + "<body bgcolor=\"#f7f7f7\">\n"
                + "  <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"container-for-gmail-android\" width=\"100%\">\n"
                + "    <tr>\n"
                + "      <td align=\"center\" valign=\"top\" width=\"100%\" style=\"background-color: #f7f7f7;\" class=\"content-padding\">\n"
                + "        <center>\n"
                + "          <table cellspacing=\"0\" cellpadding=\"0\" width=\"600\" class=\"w320\">\n"
                + "            <tr>\n"
                + "              <td class=\"mini-block-container\">\n"
                + "                <table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"\n"
                + "                  style=\"border-collapse:separate !important;\">\n"
                + "                  <tr>\n"
                + "                    <td class=\"mini-block\">\n"
                + "                      <table cellpadding=\"1\" cellspacing=\"1\" width=\"100%\">\n"
                + "                        <tr>\n"
                + "                          <td class=\"header-lg\">\n"
                + "                            INSTITUTO SUPERIOR TECNOLÓGICO \"LIMÓN\"\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                            <tr>\n"
                + "                              <td>\n"
                + "                                <br/>\n"
                + "                            <table cellspacing=\"1\" cellpadding=\"1\" width=\"100%\">\n"
                + "                              <tr style=\"text-align: center\">\n"
                + "                                <img class=\"user-img\" src=\"cid:logo.png\" alt=\"user img\" style=\"width: 150px;\" />\n"
                + "                            \n"
                + "                                <br /><p style=\"color: teal;text-transform: uppercase;\">Sistema Bolsa Empleo</p>\n"
                + "                              </tr>\n"
                + "                                   \n"
                + "                              <tr>\n"
                + "                                <td style=\"text-align: justify;\">\n"
                + "                                  <p>\n" + mensaje + "\n"
                + "                                  </p>\n"
                + "                                </td>\n"
                + "                              </tr>\n"
                + "                              <tr>\n"
                + "                                <td><a href=\"" + link + "\" target=\"_blank\">" + link + "</a></td>\n"
                + "                              </tr>\n"
                + "                            </table>\n"
                + "                          </td>\n"
                + "                        </tr>\n"
                + "                      </table>\n"
                + "                    </td>\n"
                + "                  </tr>\n"
                + "                </table>\n"
                + "              </td>\n"
                + "            </tr>\n"
                + "          </table>\n"
                + "        </center>\n"
                + "      </td>\n"
                + "    </tr>\n"
                + "  </table>\n"
                + "</body>\n"
                + "</html>";
        return cuerpo;
    }
}
