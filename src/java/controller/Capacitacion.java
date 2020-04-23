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
@Table(name = "capacitacion", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Capacitacion.findAll", query = "SELECT c FROM Capacitacion c")
    , @NamedQuery(name = "Capacitacion.findByIdCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.idCapacitacion = :idCapacitacion")
    , @NamedQuery(name = "Capacitacion.findByInstitucion", query = "SELECT c FROM Capacitacion c WHERE c.institucion = :institucion")
    , @NamedQuery(name = "Capacitacion.findByNombreEvento", query = "SELECT c FROM Capacitacion c WHERE c.nombreEvento = :nombreEvento")
    , @NamedQuery(name = "Capacitacion.findByFechaInicio", query = "SELECT c FROM Capacitacion c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Capacitacion.findByFechaFin", query = "SELECT c FROM Capacitacion c WHERE c.fechaFin = :fechaFin")
    , @NamedQuery(name = "Capacitacion.findByDias", query = "SELECT c FROM Capacitacion c WHERE c.dias = :dias")})
public class Capacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_capacitacion")
    private Integer idCapacitacion;
    @Size(max = 100)
    @Column(name = "institucion")
    private String institucion;
    @Size(max = 100)
    @Column(name = "nombre_evento")
    private String nombreEvento;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "dias")
    private Integer dias;
    @JoinColumn(name = "id_area_estudio", referencedColumnName = "id_area_estudio")
    @ManyToOne
    private AreaEstudio idAreaEstudio;
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;
    @JoinColumn(name = "id_horas_capacitaciones", referencedColumnName = "id_horas_capacitaciones")
    @ManyToOne
    private HorasCapacitaciones idHorasCapacitaciones;
    @JoinColumn(name = "id_tipo_certificado", referencedColumnName = "id_tipo_certificado")
    @ManyToOne
    private TipoCertificado idTipoCertificado;
    @JoinColumn(name = "id_tipo_evento", referencedColumnName = "id_tipo_evento")
    @ManyToOne
    private TipoEvento idTipoEvento;

    public Capacitacion() {
    }

    public Capacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
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

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public AreaEstudio getIdAreaEstudio() {
        return idAreaEstudio;
    }

    public void setIdAreaEstudio(AreaEstudio idAreaEstudio) {
        this.idAreaEstudio = idAreaEstudio;
    }

    public HojaVidaEstudiante getIdHojaVidaEstudiante() {
        return idHojaVidaEstudiante;
    }

    public void setIdHojaVidaEstudiante(HojaVidaEstudiante idHojaVidaEstudiante) {
        this.idHojaVidaEstudiante = idHojaVidaEstudiante;
    }

    public HorasCapacitaciones getIdHorasCapacitaciones() {
        return idHorasCapacitaciones;
    }

    public void setIdHorasCapacitaciones(HorasCapacitaciones idHorasCapacitaciones) {
        this.idHorasCapacitaciones = idHorasCapacitaciones;
    }

    public TipoCertificado getIdTipoCertificado() {
        return idTipoCertificado;
    }

    public void setIdTipoCertificado(TipoCertificado idTipoCertificado) {
        this.idTipoCertificado = idTipoCertificado;
    }

    public TipoEvento getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(TipoEvento idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapacitacion != null ? idCapacitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitacion)) {
            return false;
        }
        Capacitacion other = (Capacitacion) object;
        if ((this.idCapacitacion == null && other.idCapacitacion != null) || (this.idCapacitacion != null && !this.idCapacitacion.equals(other.idCapacitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Capacitacion[ idCapacitacion=" + idCapacitacion + " ]";
    }
    
}
