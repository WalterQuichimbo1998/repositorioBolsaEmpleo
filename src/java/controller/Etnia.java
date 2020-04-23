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
@Table(name = "etnia", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etnia.findAll", query = "SELECT e FROM Etnia e")
    , @NamedQuery(name = "Etnia.findByIdEtnia", query = "SELECT e FROM Etnia e WHERE e.idEtnia = :idEtnia")
    , @NamedQuery(name = "Etnia.findByEtnia", query = "SELECT e FROM Etnia e WHERE e.etnia = :etnia")})
public class Etnia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etnia")
    private Integer idEtnia;
    @Size(max = 45)
    @Column(name = "etnia")
    private String etnia;
    @OneToMany(mappedBy = "idEtnia")
    private List<Persona> personaList;

    public Etnia() {
    }

    public Etnia(Integer idEtnia) {
        this.idEtnia = idEtnia;
    }

    public Integer getIdEtnia() {
        return idEtnia;
    }

    public void setIdEtnia(Integer idEtnia) {
        this.idEtnia = idEtnia;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtnia != null ? idEtnia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etnia)) {
            return false;
        }
        Etnia other = (Etnia) object;
        if ((this.idEtnia == null && other.idEtnia != null) || (this.idEtnia != null && !this.idEtnia.equals(other.idEtnia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.etnia;
    }
    
}
