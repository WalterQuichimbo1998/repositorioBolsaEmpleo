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
@Table(name = "nivel_hablado", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelHablado.findAll", query = "SELECT n FROM NivelHablado n")
    , @NamedQuery(name = "NivelHablado.findByIdNivelHablado", query = "SELECT n FROM NivelHablado n WHERE n.idNivelHablado = :idNivelHablado")
    , @NamedQuery(name = "NivelHablado.findByNivelHablado", query = "SELECT n FROM NivelHablado n WHERE n.nivelHablado = :nivelHablado")})
public class NivelHablado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_nivel_hablado")
    private Integer idNivelHablado;
    @Size(max = 45)
    @Column(name = "nivel_hablado")
    private String nivelHablado;
    @OneToMany(mappedBy = "idNivelHablado")
    private List<Idioma> idiomaList;

    public NivelHablado() {
    }

    public NivelHablado(Integer idNivelHablado) {
        this.idNivelHablado = idNivelHablado;
    }

    public Integer getIdNivelHablado() {
        return idNivelHablado;
    }

    public void setIdNivelHablado(Integer idNivelHablado) {
        this.idNivelHablado = idNivelHablado;
    }

    public String getNivelHablado() {
        return nivelHablado;
    }

    public void setNivelHablado(String nivelHablado) {
        this.nivelHablado = nivelHablado;
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
        hash += (idNivelHablado != null ? idNivelHablado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelHablado)) {
            return false;
        }
        NivelHablado other = (NivelHablado) object;
        if ((this.idNivelHablado == null && other.idNivelHablado != null) || (this.idNivelHablado != null && !this.idNivelHablado.equals(other.idNivelHablado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nivelHablado;
    }
    
}
