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
@Table(name = "horas_capacitaciones", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorasCapacitaciones.findAll", query = "SELECT h FROM HorasCapacitaciones h")
    , @NamedQuery(name = "HorasCapacitaciones.findByIdHorasCapacitaciones", query = "SELECT h FROM HorasCapacitaciones h WHERE h.idHorasCapacitaciones = :idHorasCapacitaciones")
    , @NamedQuery(name = "HorasCapacitaciones.findByHorasCapacitaciones", query = "SELECT h FROM HorasCapacitaciones h WHERE h.horasCapacitaciones = :horasCapacitaciones")})
public class HorasCapacitaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_horas_capacitaciones")
    private Integer idHorasCapacitaciones;
    @Size(max = 45)
    @Column(name = "horas_capacitaciones")
    private String horasCapacitaciones;
    @OneToMany(mappedBy = "idHorasCapacitaciones")
    private List<Capacitacion> capacitacionList;
    @OneToMany(mappedBy = "idHorasCapacitaciones")
    private List<OfertaLaboral> ofertaLaboralList;

    public HorasCapacitaciones() {
    }

    public HorasCapacitaciones(Integer idHorasCapacitaciones) {
        this.idHorasCapacitaciones = idHorasCapacitaciones;
    }

    public Integer getIdHorasCapacitaciones() {
        return idHorasCapacitaciones;
    }

    public void setIdHorasCapacitaciones(Integer idHorasCapacitaciones) {
        this.idHorasCapacitaciones = idHorasCapacitaciones;
    }

    public String getHorasCapacitaciones() {
        return horasCapacitaciones;
    }

    public void setHorasCapacitaciones(String horasCapacitaciones) {
        this.horasCapacitaciones = horasCapacitaciones;
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
        hash += (idHorasCapacitaciones != null ? idHorasCapacitaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorasCapacitaciones)) {
            return false;
        }
        HorasCapacitaciones other = (HorasCapacitaciones) object;
        if ((this.idHorasCapacitaciones == null && other.idHorasCapacitaciones != null) || (this.idHorasCapacitaciones != null && !this.idHorasCapacitaciones.equals(other.idHorasCapacitaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.horasCapacitaciones;
    }
    
}
