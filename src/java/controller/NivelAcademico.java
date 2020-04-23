/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "nivel_academico", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelAcademico.findAll", query = "SELECT n FROM NivelAcademico n")
    , @NamedQuery(name = "NivelAcademico.findByIdNivelAcademico", query = "SELECT n FROM NivelAcademico n WHERE n.idNivelAcademico = :idNivelAcademico")
    , @NamedQuery(name = "NivelAcademico.findByNivelAcademico", query = "SELECT n FROM NivelAcademico n WHERE n.nivelAcademico = :nivelAcademico")})
public class NivelAcademico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel_academico")
    private Integer idNivelAcademico;
    @Size(max = 45)
    @Column(name = "nivel_academico")
    private String nivelAcademico;
    @OneToMany(mappedBy = "idNivelAcademico")
    private List<InstruccionFormal> instruccionFormalList;
    @OneToMany(mappedBy = "idNivelAcademico")
    private List<OfertaLaboral> ofertaLaboralList;

    public NivelAcademico() {
    }

    public NivelAcademico(Integer idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public Integer getIdNivelAcademico() {
        return idNivelAcademico;
    }

    public void setIdNivelAcademico(Integer idNivelAcademico) {
        this.idNivelAcademico = idNivelAcademico;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    @XmlTransient
    public List<InstruccionFormal> getInstruccionFormalList() {
        return instruccionFormalList;
    }

    public void setInstruccionFormalList(List<InstruccionFormal> instruccionFormalList) {
        this.instruccionFormalList = instruccionFormalList;
    }

    @XmlTransient
    public List<OfertaLaboral> getOfertaLaboralList() {
        return ofertaLaboralList;
    }

    public void setOfertaLaboralList(List<OfertaLaboral> ofertaLaboralList) {
        this.ofertaLaboralList = ofertaLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivelAcademico != null ? idNivelAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelAcademico)) {
            return false;
        }
        NivelAcademico other = (NivelAcademico) object;
        if ((this.idNivelAcademico == null && other.idNivelAcademico != null) || (this.idNivelAcademico != null && !this.idNivelAcademico.equals(other.idNivelAcademico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nivelAcademico;
    }
    
}
