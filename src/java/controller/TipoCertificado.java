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
@Table(name = "tipo_certificado", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCertificado.findAll", query = "SELECT t FROM TipoCertificado t")
    , @NamedQuery(name = "TipoCertificado.findByIdTipoCertificado", query = "SELECT t FROM TipoCertificado t WHERE t.idTipoCertificado = :idTipoCertificado")
    , @NamedQuery(name = "TipoCertificado.findByTipoCertificado", query = "SELECT t FROM TipoCertificado t WHERE t.tipoCertificado = :tipoCertificado")})
public class TipoCertificado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_certificado")
    private Integer idTipoCertificado;
    @Size(max = 45)
    @Column(name = "tipo_certificado")
    private String tipoCertificado;
    @OneToMany(mappedBy = "idTipoCertificado")
    private List<Capacitacion> capacitacionList;

    public TipoCertificado() {
    }

    public TipoCertificado(Integer idTipoCertificado) {
        this.idTipoCertificado = idTipoCertificado;
    }

    public Integer getIdTipoCertificado() {
        return idTipoCertificado;
    }

    public void setIdTipoCertificado(Integer idTipoCertificado) {
        this.idTipoCertificado = idTipoCertificado;
    }

    public String getTipoCertificado() {
        return tipoCertificado;
    }

    public void setTipoCertificado(String tipoCertificado) {
        this.tipoCertificado = tipoCertificado;
    }

    @XmlTransient
    public List<Capacitacion> getCapacitacionList() {
        return capacitacionList;
    }

    public void setCapacitacionList(List<Capacitacion> capacitacionList) {
        this.capacitacionList = capacitacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoCertificado != null ? idTipoCertificado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCertificado)) {
            return false;
        }
        TipoCertificado other = (TipoCertificado) object;
        if ((this.idTipoCertificado == null && other.idTipoCertificado != null) || (this.idTipoCertificado != null && !this.idTipoCertificado.equals(other.idTipoCertificado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipoCertificado;
    }
    
}
