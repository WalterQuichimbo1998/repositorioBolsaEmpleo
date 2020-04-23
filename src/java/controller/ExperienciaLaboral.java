/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "experiencia_laboral", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExperienciaLaboral.findAll", query = "SELECT e FROM ExperienciaLaboral e")
    , @NamedQuery(name = "ExperienciaLaboral.findByIdExperiencia", query = "SELECT e FROM ExperienciaLaboral e WHERE e.idExperiencia = :idExperiencia")
    , @NamedQuery(name = "ExperienciaLaboral.findByInstitucion", query = "SELECT e FROM ExperienciaLaboral e WHERE e.institucion = :institucion")
    , @NamedQuery(name = "ExperienciaLaboral.findByPuesto", query = "SELECT e FROM ExperienciaLaboral e WHERE e.puesto = :puesto")
    , @NamedQuery(name = "ExperienciaLaboral.findByFechaInicio", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "ExperienciaLaboral.findByFechaFin", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaFin = :fechaFin")
    , @NamedQuery(name = "ExperienciaLaboral.findByActividades", query = "SELECT e FROM ExperienciaLaboral e WHERE e.actividades = :actividades")
    , @NamedQuery(name = "ExperienciaLaboral.findByTrabajaActualmente", query = "SELECT e FROM ExperienciaLaboral e WHERE e.trabajaActualmente = :trabajaActualmente")})
public class ExperienciaLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_experiencia")
    private Integer idExperiencia;
    @Size(max = 45)
    @Column(name = "institucion")
    private String institucion;
    @Size(max = 100)
    @Column(name = "puesto")
    private String puesto;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Size(max = 500)
    @Column(name = "actividades")
    private String actividades;
    @Column(name = "trabaja_actualmente")
    private Integer trabajaActualmente;
    @JoinColumn(name = "id_area_trabajo", referencedColumnName = "id_area_trabajo")
    @ManyToOne
    private AreaTrabajo idAreaTrabajo;
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(Integer idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public Integer getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(Integer idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
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

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public Integer getTrabajaActualmente() {
        return trabajaActualmente;
    }

    public void setTrabajaActualmente(Integer trabajaActualmente) {
        this.trabajaActualmente = trabajaActualmente;
    }

    public AreaTrabajo getIdAreaTrabajo() {
        return idAreaTrabajo;
    }

    public void setIdAreaTrabajo(AreaTrabajo idAreaTrabajo) {
        this.idAreaTrabajo = idAreaTrabajo;
    }

    public HojaVidaEstudiante getIdHojaVidaEstudiante() {
        return idHojaVidaEstudiante;
    }

    public void setIdHojaVidaEstudiante(HojaVidaEstudiante idHojaVidaEstudiante) {
        this.idHojaVidaEstudiante = idHojaVidaEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExperiencia != null ? idExperiencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienciaLaboral)) {
            return false;
        }
        ExperienciaLaboral other = (ExperienciaLaboral) object;
        if ((this.idExperiencia == null && other.idExperiencia != null) || (this.idExperiencia != null && !this.idExperiencia.equals(other.idExperiencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.ExperienciaLaboral[ idExperiencia=" + idExperiencia + " ]";
    }
    
}
