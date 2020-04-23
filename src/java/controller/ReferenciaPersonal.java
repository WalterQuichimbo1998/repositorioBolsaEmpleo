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
@Table(name = "referencia_personal", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReferenciaPersonal.findAll", query = "SELECT r FROM ReferenciaPersonal r")
    , @NamedQuery(name = "ReferenciaPersonal.findByIdReferenciaPersonal", query = "SELECT r FROM ReferenciaPersonal r WHERE r.idReferenciaPersonal = :idReferenciaPersonal")
    , @NamedQuery(name = "ReferenciaPersonal.findByNombre", query = "SELECT r FROM ReferenciaPersonal r WHERE r.nombre = :nombre")
    , @NamedQuery(name = "ReferenciaPersonal.findByApellido", query = "SELECT r FROM ReferenciaPersonal r WHERE r.apellido = :apellido")
    , @NamedQuery(name = "ReferenciaPersonal.findByCorreoElectrocino", query = "SELECT r FROM ReferenciaPersonal r WHERE r.correoElectronico = :correoElectronico")
    , @NamedQuery(name = "ReferenciaPersonal.findByTelefono", query = "SELECT r FROM ReferenciaPersonal r WHERE r.telefono = :telefono")})
public class ReferenciaPersonal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_referencia_personal")
    private Integer idReferenciaPersonal;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 45)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;

    public ReferenciaPersonal() {
    }

    public ReferenciaPersonal(Integer idReferenciaPersonal) {
        this.idReferenciaPersonal = idReferenciaPersonal;
    }

    public Integer getIdReferenciaPersonal() {
        return idReferenciaPersonal;
    }

    public void setIdReferenciaPersonal(Integer idReferenciaPersonal) {
        this.idReferenciaPersonal = idReferenciaPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        hash += (idReferenciaPersonal != null ? idReferenciaPersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReferenciaPersonal)) {
            return false;
        }
        ReferenciaPersonal other = (ReferenciaPersonal) object;
        if ((this.idReferenciaPersonal == null && other.idReferenciaPersonal != null) || (this.idReferenciaPersonal != null && !this.idReferenciaPersonal.equals(other.idReferenciaPersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.ReferenciaPersonal[ idReferenciaPersonal=" + idReferenciaPersonal + " ]";
    }
    
}
