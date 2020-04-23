package control;

import controller.Persona;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Walter Quichimbo
 */
@Named(value = "exportar")
@SessionScoped
public class Exportar implements Serializable {

    Conexion con = new Conexion();

    public void reporteEmpresas(Integer id) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/empresaTodo.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Empresas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteIndividual_Empresa(Integer id, String logotipo) throws JRException, IOException {
        String relativePath = "";
        if (logotipo.equals("") || logotipo == null) {
            relativePath = "/resources/logotipo/empresa.png";
        } else {
            relativePath = "/resources/" + logotipo;
        }
        String absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
        InputStream in = new FileInputStream(absolutePath);
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        parametro.put("logotipo", in);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/empresa.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Empresa.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteOfertas(Integer id) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/ofertaTodo.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Ofertas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reporteIndividual_Oferta(Integer id, String logotipo) throws JRException, IOException {
        String relativePath = "";
        if (logotipo.equals("") || logotipo == null) {
            relativePath = "/resources/logotipo/empresa.png";
        } else {
            relativePath = "/resources/" + logotipo;
        }
        String absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
        InputStream in = new FileInputStream(absolutePath);
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        parametro.put("logotipo", in);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/oferta.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Oferta.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exportarHojaVida(Integer id, String foto) throws JRException, IOException {
        String relativePath = "";
        if (foto.equals("") || foto == null) {
            relativePath = "/resources/foto/sin_foto.jpg";
        } else {
            relativePath = "/resources/" + foto;
        }
        String absolutePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
        InputStream in = new FileInputStream(absolutePath);
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        parametro.put("foto", in);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/persona.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Hoja_de_vida.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);

                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException e) {
        }
    }

    public void reportePersonas() throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reportePersonas.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Personas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException e) {
        }
    }

    public void reportePerfil() throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reportePerfil.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Perfiles.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException e) {
        }
    }

    public void reporteUsuarios() throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporteUsuarios.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Usuarios.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException e) {
        }
    }

    public void reportePostulantes(Integer id) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reportePostulante.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Postulantes.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException e) {
        }
    }

    public void reporteEmpresasFechas(Integer id, String fecha1, String fecha2) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        parametro.put("fecha1", fecha1);
        parametro.put("fecha2", fecha2);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporteEmpresasFechas.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Empresas_fechas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reporteOfertasFechas(Integer id, String empresa, String fecha1, String fecha2) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        parametro.put("fecha1", fecha1);
        parametro.put("fecha2", fecha2);
        parametro.put("empresa", empresa);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporteOfertasFechas.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Ofertas_fechas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void reportePostulanteFechas(Integer id, String nombre, String fecha1, String fecha2) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        parametro.put("fecha1", fecha1);
        parametro.put("fecha2", fecha2);
        parametro.put("nombre", nombre);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reportePostulantesFechas.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Postulante_fechas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportePostulanteEmpresaFechas(Integer id, String fecha1, String fecha2) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        parametro.put("fecha1", fecha1);
        parametro.put("fecha2", fecha2);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reportePostulantesEmpersaFechas.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Postulante_Empresa_fechas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportePostulanteEmpresa() throws JRException, IOException {
        Integer id = AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona();
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reportePostulantesEmpresa.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Postulante_Empresa.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteEmpresasAdmin() throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporteEmpresaAdmin.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Empresa_Admin.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reporteEmpresasAdminFechas(String fecha1, String fecha2) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("fecha1", fecha1);
        parametro.put("fecha2", fecha2);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporteEmpresaAdminFechas.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Empresa_Admin_Fechas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportePostulanteTodo() throws JRException, IOException {
        Integer id = AccesoBean.obtenerIdPersona().getIdPersona().getIdPersona();
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reportePostulanteTodo.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Postulante.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reportePostulanteEmpresaFechas2(Integer id, String fecha1, String fecha2) throws JRException, IOException {
        Map<String, Object> parametro = new HashMap<>();
        parametro.put("id", id);
        parametro.put("fecha1", fecha1);
        parametro.put("fecha2", fecha2);
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reportePostulanteEmpresaFechas2.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametro, con.conexion());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=Reporte_Postulante_Empresa_Fechas.pdf");
            try (ServletOutputStream stream = response.getOutputStream()) {
                JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
                stream.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            con.conexion().close();
        } catch (IOException | SQLException | JRException ex) {
            Logger.getLogger(Exportar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
