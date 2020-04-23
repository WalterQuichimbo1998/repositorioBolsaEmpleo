/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "postulante", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postulante.findAll", query = "SELECT p FROM Postulante p")
    , @NamedQuery(name = "Postulante.findByIdPostulante", query = "SELECT p FROM Postulante p WHERE p.idPostulante = :idPostulante"),
     @NamedQuery(name = "Postulante.buscarIdPostulante", query = "SELECT p FROM Postulante p WHERE p.ofertaLaboralIdOferta = :idO AND p.personaIdPersona = :idP")

})
public class Postulante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_postulante")
    private Integer idPostulante;
    @JoinColumn(name = "oferta_laboral_id_oferta", referencedColumnName = "id_oferta")
    @ManyToOne(optional = false)
    private OfertaLaboral ofertaLaboralIdOferta;
    @JoinColumn(name = "persona_id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Persona personaIdPersona;
    @Column(name = "estado_postulante")
    private Boolean estado_postulante;
    @Column(name = "confirmacion")
    private Boolean confirmacion;
    @Column(name = "fecha_postulante")
    @Temporal(TemporalType.DATE)
    private Date fechaPostulante;
    @Column(name = "fecha_confirmacion")
    @Temporal(TemporalType.DATE)
    private Date fechaConfirmacion;

    public Postulante() {
    }

    public Postulante(Integer idPostulante) {
        this.idPostulante = idPostulante;
    }

    public Integer getIdPostulante() {
        return idPostulante;
    }

    public void setIdPostulante(Integer idPostulante) {
        this.idPostulante = idPostulante;
    }

    public OfertaLaboral getOfertaLaboralIdOferta() {
        return ofertaLaboralIdOferta;
    }

    public void setOfertaLaboralIdOferta(OfertaLaboral ofertaLaboralIdOferta) {
        this.ofertaLaboralIdOferta = ofertaLaboralIdOferta;
    }

    public Persona getPersonaIdPersona() {
        return personaIdPersona;
    }

    public void setPersonaIdPersona(Persona personaIdPersona) {
        this.personaIdPersona = personaIdPersona;
    }

    public Boolean getEstado_postulante() {
        return estado_postulante;
    }

    public void setEstado_postulante(Boolean estado_postulante) {
        this.estado_postulante = estado_postulante;
    }

    public Date getFechaPostulante() {
        return fechaPostulante;
    }

    public void setFechaPostulante(Date fechaPostulante) {
        this.fechaPostulante = fechaPostulante;
    }

    public Boolean getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(Boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }
    
    
 
   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPostulante != null ? idPostulante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postulante)) {
            return false;
        }
        Postulante other = (Postulante) object;
        if ((this.idPostulante == null && other.idPostulante != null) || (this.idPostulante != null && !this.idPostulante.equals(other.idPostulante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Postulante[ idPostulante=" + idPostulante + " ]";
    }
    
}
