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
@Table(name = "instruccion_formal", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstruccionFormal.findAll", query = "SELECT i FROM InstruccionFormal i")
    , @NamedQuery(name = "InstruccionFormal.findByIdInstruccion", query = "SELECT i FROM InstruccionFormal i WHERE i.idInstruccion = :idInstruccion")
    , @NamedQuery(name = "InstruccionFormal.findByInstitucion", query = "SELECT i FROM InstruccionFormal i WHERE i.institucion = :institucion")
    , @NamedQuery(name = "InstruccionFormal.findByCodigoSenescyt", query = "SELECT i FROM InstruccionFormal i WHERE i.codigoSenescyt = :codigoSenescyt")})
public class InstruccionFormal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_instruccion")
    private Integer idInstruccion;
    @Size(max = 70)
    @Column(name = "institucion")
    private String institucion;
    @Size(max = 100)
    @Column(name = "codigo_senescyt")
    private String codigoSenescyt;
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;
    @JoinColumn(name = "id_nivel_academico", referencedColumnName = "id_nivel_academico")
    @ManyToOne
    private NivelAcademico idNivelAcademico;

    public InstruccionFormal() {
    }

    public InstruccionFormal(Integer idInstruccion) {
        this.idInstruccion = idInstruccion;
    }

    public Integer getIdInstruccion() {
        return idInstruccion;
    }

    public void setIdInstruccion(Integer idInstruccion) {
        this.idInstruccion = idInstruccion;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCodigoSenescyt() {
        return codigoSenescyt;
    }

    public void setCodigoSenescyt(String codigoSenescyt) {
        this.codigoSenescyt = codigoSenescyt;
    }

    public HojaVidaEstudiante getIdHojaVidaEstudiante() {
        return idHojaVidaEstudiante;
    }

    public void setIdHojaVidaEstudiante(HojaVidaEstudiante idHojaVidaEstudiante) {
        this.idHojaVidaEstudiante = idHojaVidaEstudiante;
    }

    public NivelAcademico getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(NivelAcademico idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInstruccion != null ? idInstruccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstruccionFormal)) {
            return false;
        }
        InstruccionFormal other = (InstruccionFormal) object;
        if ((this.idInstruccion == null && other.idInstruccion != null) || (this.idInstruccion != null && !this.idInstruccion.equals(other.idInstruccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.InstruccionFormal[ idInstruccion=" + idInstruccion + " ]";
    }
    
}
