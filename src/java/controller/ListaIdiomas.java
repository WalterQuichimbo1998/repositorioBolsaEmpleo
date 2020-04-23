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
@Table(name = "lista_idiomas", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaIdiomas.findAll", query = "SELECT l FROM ListaIdiomas l")
    , @NamedQuery(name = "ListaIdiomas.findByIdListaIdiomas", query = "SELECT l FROM ListaIdiomas l WHERE l.idListaIdiomas = :idListaIdiomas")
    , @NamedQuery(name = "ListaIdiomas.findByNombreIdioma", query = "SELECT l FROM ListaIdiomas l WHERE l.nombreIdioma = :nombreIdioma")})
public class ListaIdiomas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_lista_idiomas")
    private Integer idListaIdiomas;
    @Size(max = 45)
    @Column(name = "nombre_idioma")
    private String nombreIdioma;
    @OneToMany(mappedBy = "idListaIdiomas")
    private List<Idioma> idiomaList;

    public ListaIdiomas() {
    }

    public ListaIdiomas(Integer idListaIdiomas) {
        this.idListaIdiomas = idListaIdiomas;
    }

    public Integer getIdListaIdiomas() {
        return idListaIdiomas;
    }

    public void setIdListaIdiomas(Integer idListaIdiomas) {
        this.idListaIdiomas = idListaIdiomas;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
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
        hash += (idListaIdiomas != null ? idListaIdiomas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaIdiomas)) {
            return false;
        }
        ListaIdiomas other = (ListaIdiomas) object;
        if ((this.idListaIdiomas == null && other.idListaIdiomas != null) || (this.idListaIdiomas != null && !this.idListaIdiomas.equals(other.idListaIdiomas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreIdioma;
    }
    
}
