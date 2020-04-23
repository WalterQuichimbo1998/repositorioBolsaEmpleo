/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "logro_personal", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogroPersonal.findAll", query = "SELECT l FROM LogroPersonal l")
    , @NamedQuery(name = "LogroPersonal.findByIdLogroPersonal", query = "SELECT l FROM LogroPersonal l WHERE l.idLogroPersonal = :idLogroPersonal")
    , @NamedQuery(name = "LogroPersonal.findByDescripcion", query = "SELECT l FROM LogroPersonal l WHERE l.descripcion = :descripcion")})
public class LogroPersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_logro_personal")
    private Integer idLogroPersonal;
    @Size(max = 1000)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;
    @JoinColumn(name = "id_tipo_logro", referencedColumnName = "id_tipo_logro")
    @ManyToOne
    private TipoLogro idTipoLogro;

    public LogroPersonal() {
    }

    public LogroPersonal(Integer idLogroPersonal) {
        this.idLogroPersonal = idLogroPersonal;
    }

    public Integer getIdLogroPersonal() {
        return idLogroPersonal;
    }

    public void setIdLogroPersonal(Integer idLogroPersonal) {
        this.idLogroPersonal = idLogroPersonal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HojaVidaEstudiante getIdHojaVidaEstudiante() {
        return idHojaVidaEstudiante;
    }

    public void setIdHojaVidaEstudiante(HojaVidaEstudiante idHojaVidaEstudiante) {
        this.idHojaVidaEstudiante = idHojaVidaEstudiante;
    }

    public TipoLogro getIdTipoLogro() {
        return idTipoLogro;
    }

    public void setIdTipoLogro(TipoLogro idTipoLogro) {
        this.idTipoLogro = idTipoLogro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogroPersonal != null ? idLogroPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogroPersonal)) {
            return false;
        }
        LogroPersonal other = (LogroPersonal) object;
        if ((this.idLogroPersonal == null && other.idLogroPersonal != null) || (this.idLogroPersonal != null && !this.idLogroPersonal.equals(other.idLogroPersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.LogroPersonal[ idLogroPersonal=" + idLogroPersonal + " ]";
    }
    
}
