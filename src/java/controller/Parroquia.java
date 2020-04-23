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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "parroquia", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parroquia.findAll", query = "SELECT p FROM Parroquia p")
    , @NamedQuery(name = "Parroquia.findByIdParroquia", query = "SELECT p FROM Parroquia p WHERE p.idParroquia = :idParroquia")
    , @NamedQuery(name = "Parroquia.findByParroquia", query = "SELECT p FROM Parroquia p WHERE p.parroquia = :parroquia")})
public class Parroquia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_parroquia")
    private Integer idParroquia;
    @Size(max = 45)
    @Column(name = "parroquia")
    private String parroquia;
//    @OneToMany(mappedBy = "idParroquia")
//    private List<OfertaLaboral> ofertaLaboralList;
    @OneToMany(mappedBy = "idParroquia")
    private List<Persona> personaList;
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton")
    @ManyToOne
    private Canton idCanton;
    @OneToMany(mappedBy = "idParroquia")
    private List<Empresa> empresaList;

    public Parroquia() {
    }

    public Parroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
    }

    public Integer getIdParroquia() {
        return idParroquia;
    }

    public void setIdParroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
    }

    public String getParroquia() {
        return parroquia;
    }

    public void setParroquia(String parroquia) {
        this.parroquia = parroquia;
    }

//    @XmlTransient
//    public List<OfertaLaboral> getOfertaLaboralList() {
//        return ofertaLaboralList;
//    }
//
//    public void setOfertaLaboralList(List<OfertaLaboral> ofertaLaboralList) {
//        this.ofertaLaboralList = ofertaLaboralList;
//    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    public Canton getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Canton idCanton) {
        this.idCanton = idCanton;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParroquia != null ? idParroquia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parroquia)) {
            return false;
        }
        Parroquia other = (Parroquia) object;
        if ((this.idParroquia == null && other.idParroquia != null) || (this.idParroquia != null && !this.idParroquia.equals(other.idParroquia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.parroquia;
    }
    
}
