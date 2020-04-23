/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "oficio", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oficio.findAll", query = "SELECT o FROM Oficio o")
    , @NamedQuery(name = "Oficio.findByIdOficio", query = "SELECT o FROM Oficio o WHERE o.idOficio = :idOficio")
    , @NamedQuery(name = "Oficio.findByOficio", query = "SELECT o FROM Oficio o WHERE o.oficio = :oficio")
    , @NamedQuery(name = "Oficio.findByDescripcion", query = "SELECT o FROM Oficio o WHERE o.descripcion = :descripcion")})
public class Oficio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_oficio")
    private Integer idOficio;
    @Size(max = 45)
    @Column(name = "oficio")
    private String oficio;
    @Size(max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;

    public Oficio() {
    }

    public Oficio(Integer idOficio) {
        this.idOficio = idOficio;
    }

    public Integer getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(Integer idOficio) {
        this.idOficio = idOficio;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HojaVidaEstudiante getIdHojaVidaEstudiante() {
        return idHojaVidaEstudiante;
    }

    public void setIdHojaVidaEstudiante(HojaVidaEstudiante idHojaVidaEstudiante) {
        this.idHojaVidaEstudiante = idHojaVidaEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOficio != null ? idOficio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oficio)) {
            return false;
        }
        Oficio other = (Oficio) object;
        if ((this.idOficio == null && other.idOficio != null) || (this.idOficio != null && !this.idOficio.equals(other.idOficio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Oficio[ idOficio=" + idOficio + " ]";
    }
    
}
