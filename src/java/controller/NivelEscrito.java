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
@Table(name = "nivel_escrito", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelEscrito.findAll", query = "SELECT n FROM NivelEscrito n")
    , @NamedQuery(name = "NivelEscrito.findByIdNivelEscrito", query = "SELECT n FROM NivelEscrito n WHERE n.idNivelEscrito = :idNivelEscrito")
    , @NamedQuery(name = "NivelEscrito.findByNivelEscrito", query = "SELECT n FROM NivelEscrito n WHERE n.nivelEscrito = :nivelEscrito")})
public class NivelEscrito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel_escrito")
    private Integer idNivelEscrito;
    @Size(max = 45)
    @Column(name = "nivel_escrito")
    private String nivelEscrito;
    @OneToMany(mappedBy = "idNivelEscrito")
    private List<Idioma> idiomaList;

    public NivelEscrito() {
    }

    public NivelEscrito(Integer idNivelEscrito) {
        this.idNivelEscrito = idNivelEscrito;
    }

    public Integer getIdNivelEscrito() {
        return idNivelEscrito;
    }

    public void setIdNivelEscrito(Integer idNivelEscrito) {
        this.idNivelEscrito = idNivelEscrito;
    }

    public String getNivelEscrito() {
        return nivelEscrito;
    }

    public void setNivelEscrito(String nivelEscrito) {
        this.nivelEscrito = nivelEscrito;
    }

    @XmlTransient
    public List<Idioma> getIdiomaList() {
        return idiomaList;
    }

    public void setIdiomaList(List<Idioma> idiomaList) {
        this.idiomaList = idiomaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNivelEscrito != null ? idNivelEscrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelEscrito)) {
            return false;
        }
        NivelEscrito other = (NivelEscrito) object;
        if ((this.idNivelEscrito == null && other.idNivelEscrito != null) || (this.idNivelEscrito != null && !this.idNivelEscrito.equals(other.idNivelEscrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nivelEscrito;
    }
    
}
