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
@Table(name = "area_estudio", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaEstudio.findAll", query = "SELECT a FROM AreaEstudio a")
    , @NamedQuery(name = "AreaEstudio.findByIdAreaEstudio", query = "SELECT a FROM AreaEstudio a WHERE a.idAreaEstudio = :idAreaEstudio")
    , @NamedQuery(name = "AreaEstudio.findByAreaEstudio", query = "SELECT a FROM AreaEstudio a WHERE a.areaEstudio = :areaEstudio")})
public class AreaEstudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area_estudio")
    private Integer idAreaEstudio;
    @Size(max = 45)
    @Column(name = "area_estudio")
    private String areaEstudio;
    @OneToMany(mappedBy = "idAreaEstudio")
    private List<Capacitacion> capacitacionList;
    @OneToMany(mappedBy = "idAreaEstudio")
    private List<OfertaLaboral> ofertaLaboralList;

    public AreaEstudio() {
    }

    public AreaEstudio(Integer idAreaEstudio) {
        this.idAreaEstudio = idAreaEstudio;
    }

    public Integer getIdAreaEstudio() {
        return idAreaEstudio;
    }

    public void setIdAreaEstudio(Integer idAreaEstudio) {
        this.idAreaEstudio = idAreaEstudio;
    }

    public String getAreaEstudio() {
        return areaEstudio;
    }

    public void setAreaEstudio(String areaEstudio) {
        this.areaEstudio = areaEstudio;
    }

    @XmlTransient
    public List<Capacitacion> getCapacitacionList() {
        return capacitacionList;
    }

    public void setCapacitacionList(List<Capacitacion> capacitacionList) {
        this.capacitacionList = capacitacionList;
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
        hash += (idAreaEstudio != null ? idAreaEstudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaEstudio)) {
            return false;
        }
        AreaEstudio other = (AreaEstudio) object;
        if ((this.idAreaEstudio == null && other.idAreaEstudio != null) || (this.idAreaEstudio != null && !this.idAreaEstudio.equals(other.idAreaEstudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.areaEstudio;
    }
    
}
