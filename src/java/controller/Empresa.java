/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "empresa", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "Empresa.findByRucCedula", query = "SELECT e FROM Empresa e WHERE e.rucCedula = :rucCedula")
    , @NamedQuery(name = "Empresa.findByRazonSocial", query = "SELECT e FROM Empresa e WHERE e.razonSocial = :razonSocial")
    , @NamedQuery(name = "Empresa.findByNombreComercial", query = "SELECT e FROM Empresa e WHERE e.nombreComercial = :nombreComercial")
    , @NamedQuery(name = "Empresa.findByPaginaWeb", query = "SELECT e FROM Empresa e WHERE e.paginaWeb = :paginaWeb")
    , @NamedQuery(name = "Empresa.findByCorreo", query = "SELECT e FROM Empresa e WHERE e.correo = :correo")
    , @NamedQuery(name = "Empresa.findByCiudad", query = "SELECT e FROM Empresa e WHERE e.ciudad = :ciudad")
    , @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empresa.findByTelefonoOficina", query = "SELECT e FROM Empresa e WHERE e.telefonoOficina = :telefonoOficina")
    , @NamedQuery(name = "Empresa.findByTelefonoParticular", query = "SELECT e FROM Empresa e WHERE e.telefonoParticular = :telefonoParticular")
    , @NamedQuery(name = "Empresa.findByCelular", query = "SELECT e FROM Empresa e WHERE e.celular = :celular")
    , @NamedQuery(name = "Empresa.findByServicios", query = "SELECT e FROM Empresa e WHERE e.servicios = :servicios")
    , @NamedQuery(name = "Empresa.findByExperiencias", query = "SELECT e FROM Empresa e WHERE e.experiencias = :experiencias")
    , @NamedQuery(name = "Empresa.findByActividades", query = "SELECT e FROM Empresa e WHERE e.actividades = :actividades")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private Integer idEmpresa;
    @Size(max = 45)
    @Column(name = "ruc_cedula")
    private String rucCedula;
    @Size(max = 45)
    @Column(name = "razon_social")
    private String razonSocial;
    @Size(max = 60)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Size(max = 45)
    @Column(name = "pagina_web")
    private String paginaWeb;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @Size(max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "telefono_oficina")
    private String telefonoOficina;
    @Size(max = 45)
    @Column(name = "telefono_particular")
    private String telefonoParticular;
    @Size(max = 45)
    @Column(name = "celular")
    private String celular;
   @Size(max = 45)
    @Column(name = "logotipo")
    private String logotipo;
    @Size(max = 500)
    @Column(name = "servicios")
    private String servicios;
    @Size(max = 500)
    @Column(name = "experiencias")
    private String experiencias;
    @Size(max = 500)
    @Column(name = "actividades")
    private String actividades;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<OfertaLaboral> ofertaLaboralList;
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton")
    @ManyToOne
    private Canton idCanton;
    @JoinColumn(name = "id_parroquia", referencedColumnName = "id_parroquia")
    @ManyToOne
    private Parroquia idParroquia;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Persona idPersona;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia")
    @ManyToOne
    private Provincia idProvincia;
    @JoinColumn(name = "id_tipo_empresa", referencedColumnName = "id_tipo_empresa")
    @ManyToOne
    private TipoEmpresa idTipoEmpresa;
    @JoinColumn(name = "id_tipo_persona", referencedColumnName = "id_tipo_persona")
    @ManyToOne
    private TipoPersona idTipoPersona;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRucCedula() {
        return rucCedula;
    }

    public void setRucCedula(String rucCedula) {
        this.rucCedula = rucCedula;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    public String getTelefonoParticular() {
        return telefonoParticular;
    }

    public void setTelefonoParticular(String telefonoParticular) {
        this.telefonoParticular = telefonoParticular;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    

    public String getServicios() {
        return servicios;
    }

    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    public String getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(String experiencias) {
        this.experiencias = experiencias;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    @XmlTransient
    public List<OfertaLaboral> getOfertaLaboralList() {
        return ofertaLaboralList;
    }

    public void setOfertaLaboralList(List<OfertaLaboral> ofertaLaboralList) {
        this.ofertaLaboralList = ofertaLaboralList;
    }

    public Canton getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Canton idCanton) {
        this.idCanton = idCanton;
    }

    public Parroquia getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Parroquia idParroquia) {
        this.idParroquia = idParroquia;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Provincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincia idProvincia) {
        this.idProvincia = idProvincia;
    }

    public TipoEmpresa getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(TipoEmpresa idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    public TipoPersona getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(TipoPersona idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreComercial;
    }
    
}
