package jsf.classes;

import com.lowagie.text.Cell;
import com.lowagie.text.Row;
import control.AccesoBean;
import control.Conexion;
import controller.Capacitacion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import jsf.classes.util.JsfUtil;
import jsf.classes.util.JsfUtil.PersistAction;
import sessions.beans.CapacitacionFacade;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.primefaces.model.UploadedFile;

@Named("capacitacionController")
@SessionScoped
public class CapacitacionController implements Serializable {

    @EJB
    private sessions.beans.CapacitacionFacade ejbFacade;
    private List<Capacitacion> items = null;
    private List<Capacitacion> lista = null;
    private List<Capacitacion> listaCapacitaciones = null;
    private Capacitacion selected;
    private UploadedFile file;

    public CapacitacionController() {
    }

    public Capacitacion getSelected() {
        return selected;
    }

    public void setSelected(Capacitacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CapacitacionFacade getFacade() {
        return ejbFacade;
    }

    public List<Capacitacion> getLista() {
        if (lista == null) {
            lista = getFacade().findAll();
        }
        return lista;
    }

    public void setLista(List<Capacitacion> lista) {
        this.lista = lista;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
//metodo para realizar la busqueda

    public List<Capacitacion> getListaCapacitaciones() {
        return listaCapacitaciones;
    }

    public void setListaCapacitaciones(List<Capacitacion> listaCapacitaciones) {
        this.listaCapacitaciones = listaCapacitaciones;
    }

    public Capacitacion prepareCreate() {
        selected = new Capacitacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        this.selected.setIdHojaVidaEstudiante(AccesoBean.obtenerIdHojaVida());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CapacitacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public void create2() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CapacitacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().findAll();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CapacitacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CapacitacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items = getFacade().lista();
        }
    }

    public void destroy2() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CapacitacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            lista = null;    // Invalidate list of items to trigger re-query.
            lista = getFacade().findAll();
        }
    }

    public List<Capacitacion> getItems() {
        if (items == null) {
            items = getFacade().lista();
        }
        return items;
    }

    public List<Capacitacion> llenarCapacitacion() {
        if (items == null) {
            items = getFacade().lista();
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

    public Capacitacion getCapacitacion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Capacitacion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Capacitacion> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Capacitacion.class)
    public static class CapacitacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CapacitacionController controller = (CapacitacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "capacitacionController");
            return controller.getCapacitacion(getKey(value));
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
            if (object instanceof Capacitacion) {
                Capacitacion o = (Capacitacion) object;
                return getStringKey(o.getIdCapacitacion());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Capacitacion.class.getName()});
                return null;
            }
        }

    }

    public void subirExcel() {
        if (file == null || file.getSize() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Seleccione un archivo", ""));
        } else {
            String archivo = file.getContentType();
            if ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(archivo)) {
                Conexion con = new Conexion();
                PreparedStatement ps;
                try {
                    FileInputStream file2 = (FileInputStream) file.getInputstream();
                    XSSFWorkbook wb = new XSSFWorkbook(file2);
                    XSSFSheet sheet = wb.getSheetAt(0);
                    int numFilas = sheet.getLastRowNum();
                    for (int i = 1; i <= numFilas; i++) {
                        XSSFRow fila = sheet.getRow(i);
                        if (fila.getCell(0).getNumericCellValue()==0 || "".equals(fila.getCell(1).getStringCellValue()) || "".equals(fila.getCell(2).getStringCellValue()) || fila.getCell(3).getNumericCellValue()==0 ||
                               fila.getCell(4).getNumericCellValue()==0 || fila.getCell(5).getNumericCellValue()==0 || fila.getCell(6).getNumericCellValue()==0 || fila.getCell(7).getNumericCellValue()==0 ) {
                            System.out.println("No se subieron algunos datos porque tenia columnas vacias, Fila "+i);
                        }else{
                        ps = con.conexion().prepareStatement("INSERT INTO capacitacion (id_hoja_vida_estudiante,institucion,nombre_evento,dias,id_tipo_evento,id_area_estudio,id_horas_capacitaciones,id_tipo_certificado) VALUES(?,?,?,?,?,?,?,?)");
                        ps.setInt(1, (int) fila.getCell(0).getNumericCellValue());
                        ps.setString(2, fila.getCell(1).getStringCellValue());
                        ps.setString(3, fila.getCell(2).getStringCellValue());
                        ps.setInt(4, (int) fila.getCell(3).getNumericCellValue());
                        ps.setInt(5, (int) fila.getCell(4).getNumericCellValue());
                        ps.setInt(6, (int) fila.getCell(5).getNumericCellValue());
                        ps.setInt(7, (int) fila.getCell(6).getNumericCellValue());
                        ps.setInt(8, (int) fila.getCell(7).getNumericCellValue());
                        ps.execute();
                    }
                    }
                    con.conexion().close();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro subido correctamente.", ""));
                    lista = getFacade().findAll();
                    file=null;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(CapacitacionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Archivo no válido.", ""));
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
}
