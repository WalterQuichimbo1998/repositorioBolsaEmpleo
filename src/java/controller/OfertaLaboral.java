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
@Table(name = "oferta_laboral", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OfertaLaboral.findAll", query = "SELECT o FROM OfertaLaboral o")
    , @NamedQuery(name = "OfertaLaboral.findByIdOferta", query = "SELECT o FROM OfertaLaboral o WHERE o.idOferta = :idOferta")
    , @NamedQuery(name = "OfertaLaboral.findByCargoSolicitado", query = "SELECT o FROM OfertaLaboral o WHERE o.cargoSolicitado = :cargoSolicitado")
    , @NamedQuery(name = "OfertaLaboral.findByFechaInicio", query = "SELECT o FROM OfertaLaboral o WHERE o.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "OfertaLaboral.findByFehcaFin", query = "SELECT o FROM OfertaLaboral o WHERE o.fechaFin = :fechaFin")
    , @NamedQuery(name = "OfertaLaboral.findByJornadaTrabajo", query = "SELECT o FROM OfertaLaboral o WHERE o.jornadaTrabajo = :jornadaTrabajo")
    , @NamedQuery(name = "OfertaLaboral.findByRemuneracion", query = "SELECT o FROM OfertaLaboral o WHERE o.remuneracion = :remuneracion")
    , @NamedQuery(name = "OfertaLaboral.findByExperiencia", query = "SELECT o FROM OfertaLaboral o WHERE o.experiencia = :experiencia")
    , @NamedQuery(name = "OfertaLaboral.findByConocimientoCargo", query = "SELECT o FROM OfertaLaboral o WHERE o.conocimientoCargo = :conocimientoCargo")
    , @NamedQuery(name = "OfertaLaboral.findByActividades", query = "SELECT o FROM OfertaLaboral o WHERE o.actividades = :actividades")
    , @NamedQuery(name = "OfertaLaboral.findByDescripcion", query = "SELECT o FROM OfertaLaboral o WHERE o.descripcion = :descripcion")})
public class OfertaLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_oferta")
    private Integer idOferta;
    @Size(max = 45)
    @Column(name = "cargo_solicitado")
    private String cargoSolicitado;
    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 45)
    @Column(name = "jornada_trabajo")
    private String jornadaTrabajo;
    @Size(max = 45)
    @Column(name = "remuneracion")
    private String remuneracion;
    @Size(max = 500)
    @Column(name = "experiencia")
    private String experiencia;
    @Size(max = 500)
    @Column(name = "conocimiento_cargo")
    private String conocimientoCargo;
    @Size(max = 500)
    @Column(name = "actividades")
    private String actividades;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "tiempo_experiencia")
    private String tiempo_experiencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ofertaLaboralIdOferta")
    private List<Postulante> postulanteList;
    @JoinColumn(name = "id_area_estudio", referencedColumnName = "id_area_estudio")
    @ManyToOne
    private AreaEstudio idAreaEstudio;
  
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne(optional = false)
    private Empresa idEmpresa;
   
    @JoinColumn(name = "id_horas_capacitaciones", referencedColumnName = "id_horas_capacitaciones")
    @ManyToOne
    private HorasCapacitaciones idHorasCapacitaciones;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;
  
    @JoinColumn(name = "id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne
    private TipoContrato idTipoContrato;
      @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;
      @Column(name = "estado")
    private Boolean estado;
    public OfertaLaboral() {
    }

    public OfertaLaboral(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public String getCargoSolicitado() {
        return cargoSolicitado;
    }

    public void setCargoSolicitado(String cargoSolicitado) {
        this.cargoSolicitado = cargoSolicitado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

   

    public String getJornadaTrabajo() {
        return jornadaTrabajo;
    }

    public void setJornadaTrabajo(String jornadaTrabajo) {
        this.jornadaTrabajo = jornadaTrabajo;
    }

    public String getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(String remuneracion) {
        this.remuneracion = remuneracion;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getConocimientoCargo() {
        return conocimientoCargo;
    }

    public void setConocimientoCargo(String conocimientoCargo) {
        this.conocimientoCargo = conocimientoCargo;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTiempo_experiencia() {
        return tiempo_experiencia;
    }

    public void setTiempo_experiencia(String tiempo_experiencia) {
        this.tiempo_experiencia = tiempo_experiencia;
    }
    

    @XmlTransient
    public List<Postulante> getPostulanteList() {
        return postulanteList;
    }

    public void setPostulanteList(List<Postulante> postulanteList) {
        this.postulanteList = postulanteList;
    }

    public AreaEstudio getIdAreaEstudio() {
        return idAreaEstudio;
    }

    public void setIdAreaEstudio(AreaEstudio idAreaEstudio) {
        this.idAreaEstudio = idAreaEstudio;
    }


    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


    public HorasCapacitaciones getIdHorasCapacitaciones() {
        return idHorasCapacitaciones;
    }

    public void setIdHorasCapacitaciones(HorasCapacitaciones idHorasCapacitaciones) {
        this.idHorasCapacitaciones = idHorasCapacitaciones;
    }

    public NivelAcademico getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(NivelAcademico idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public TipoContrato getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(TipoContrato idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOferta != null ? idOferta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OfertaLaboral)) {
            return false;
        }
        OfertaLaboral other = (OfertaLaboral) object;
        if ((this.idOferta == null && other.idOferta != null) || (this.idOferta != null && !this.idOferta.equals(other.idOferta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.cargoSolicitado;
    }
    
}
