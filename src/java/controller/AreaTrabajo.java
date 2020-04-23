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
@Table(name = "area_trabajo", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaTrabajo.findAll", query = "SELECT a FROM AreaTrabajo a")
    , @NamedQuery(name = "AreaTrabajo.findByIdAreaTrabajo", query = "SELECT a FROM AreaTrabajo a WHERE a.idAreaTrabajo = :idAreaTrabajo")
    , @NamedQuery(name = "AreaTrabajo.findByAreaTrabajo", query = "SELECT a FROM AreaTrabajo a WHERE a.areaTrabajo = :areaTrabajo")})
public class AreaTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area_trabajo")
    private Integer idAreaTrabajo;
    @Size(max = 45)
    @Column(name = "area_trabajo")
    private String areaTrabajo;
    @OneToMany(mappedBy = "idAreaTrabajo")
    private List<ExperienciaLaboral> experienciaLaboralList;

    public AreaTrabajo() {
    }

    public AreaTrabajo(Integer idAreaTrabajo) {
        this.idAreaTrabajo = idAreaTrabajo;
    }

    public Integer getIdAreaTrabajo() {
        return idAreaTrabajo;
    }

    public void setIdAreaTrabajo(Integer idAreaTrabajo) {
        this.idAreaTrabajo = idAreaTrabajo;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    @XmlTransient
    public List<ExperienciaLaboral> getExperienciaLaboralList() {
        return experienciaLaboralList;
    }

    public void setExperienciaLaboralList(List<ExperienciaLaboral> experienciaLaboralList) {
        this.experienciaLaboralList = experienciaLaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAreaTrabajo != null ? idAreaTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaTrabajo)) {
            return false;
        }
        AreaTrabajo other = (AreaTrabajo) object;
        if ((this.idAreaTrabajo == null && other.idAreaTrabajo != null) || (this.idAreaTrabajo != null && !this.idAreaTrabajo.equals(other.idAreaTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.areaTrabajo;
    }
    
}
