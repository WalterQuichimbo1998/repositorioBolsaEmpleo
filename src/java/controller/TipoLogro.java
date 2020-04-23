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
@Table(name = "tipo_logro", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLogro.findAll", query = "SELECT t FROM TipoLogro t")
    , @NamedQuery(name = "TipoLogro.findByIdTipoLogro", query = "SELECT t FROM TipoLogro t WHERE t.idTipoLogro = :idTipoLogro")
    , @NamedQuery(name = "TipoLogro.findByTipoLogro", query = "SELECT t FROM TipoLogro t WHERE t.tipoLogro = :tipoLogro")})
public class TipoLogro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_logro")
    private Integer idTipoLogro;
    @Size(max = 45)
    @Column(name = "tipo_logro")
    private String tipoLogro;
    @OneToMany(mappedBy = "idTipoLogro")
    private List<LogroPersonal> logroPersonalList;

    public TipoLogro() {
    }

    public TipoLogro(Integer idTipoLogro) {
        this.idTipoLogro = idTipoLogro;
    }

    public Integer getIdTipoLogro() {
        return idTipoLogro;
    }

    public void setIdTipoLogro(Integer idTipoLogro) {
        this.idTipoLogro = idTipoLogro;
    }

    public String getTipoLogro() {
        return tipoLogro;
    }

    public void setTipoLogro(String tipoLogro) {
        this.tipoLogro = tipoLogro;
    }

    @XmlTransient
    public List<LogroPersonal> getLogroPersonalList() {
        return logroPersonalList;
    }

    public void setLogroPersonalList(List<LogroPersonal> logroPersonalList) {
        this.logroPersonalList = logroPersonalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoLogro != null ? idTipoLogro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoLogro)) {
            return false;
        }
        TipoLogro other = (TipoLogro) object;
        if ((this.idTipoLogro == null && other.idTipoLogro != null) || (this.idTipoLogro != null && !this.idTipoLogro.equals(other.idTipoLogro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipoLogro;
    }
    
}
