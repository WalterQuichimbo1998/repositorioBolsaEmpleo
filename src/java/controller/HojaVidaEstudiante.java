/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "hoja_vida_estudiante", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HojaVidaEstudiante.findAll", query = "SELECT h FROM HojaVidaEstudiante h")
    , @NamedQuery(name = "HojaVidaEstudiante.findByIdHojaVidaEstudiante", query = "SELECT h FROM HojaVidaEstudiante h WHERE h.idHojaVidaEstudiante = :idHojaVidaEstudiante")
     ,@NamedQuery(name = "HojaVidaEstudiante.findByIdPersona", query = "SELECT h FROM HojaVidaEstudiante h WHERE h.idPersona = :id")

})
public class HojaVidaEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_hoja_vida_estudiante")
    private Integer idHojaVidaEstudiante;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHojaVidaEstudiante")
    private List<Capacitacion> capacitacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHojaVidaEstudiante")
    private List<InstruccionFormal> instruccionFormalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHojaVidaEstudiante")
    private List<Idioma> idiomaList;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Persona idPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHojaVidaEstudiante")
    private List<ReferenciaPersonal> referenciaPersonalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHojaVidaEstudiante")
    private List<LogroPersonal> logroPersonalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHojaVidaEstudiante")
    private List<Oficio> oficioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHojaVidaEstudiante")
    private List<PreferenciaLaboral> preferenciaLaboralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHojaVidaEstudiante")
    private List<ExperienciaLaboral> experienciaLaboralList;

    public HojaVidaEstudiante() {
    }

    public HojaVidaEstudiante(Integer idHojaVidaEstudiante) {
        this.idHojaVidaEstudiante = idHojaVidaEstudiante;
    }

    public Integer getIdHojaVidaEstudiante() {
        return idHojaVidaEstudiante;
    }

    public void setIdHojaVidaEstudiante(Integer idHojaVidaEstudiante) {
        this.idHojaVidaEstudiante = idHojaVidaEstudiante;
    }

    @XmlTransient
    public List<Capacitacion> getCapacitacionList() {
        return capacitacionList;
    }

    public void setCapacitacionList(List<Capacitacion> capacitacionList) {
        this.capacitacionList = capacitacionList;
    }

    @XmlTransient
    public List<InstruccionFormal> getInstruccionFormalList() {
        return instruccionFormalList;
    }

    public void setInstruccionFormalList(List<InstruccionFormal> instruccionFormalList) {
        this.instruccionFormalList = instruccionFormalList;
    }

    @XmlTransient
    public List<Idioma> getIdiomaList() {
        return idiomaList;
    }

    public void setIdiomaList(List<Idioma> idiomaList) {
        this.idiomaList = idiomaList;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    @XmlTransient
    public List<ReferenciaPersonal> getReferenciaPersonalList() {
        return referenciaPersonalList;
    }

    public void setReferenciaPersonalList(List<ReferenciaPersonal> referenciaPersonalList) {
        this.referenciaPersonalList = referenciaPersonalList;
    }

    @XmlTransient
    public List<LogroPersonal> getLogroPersonalList() {
        return logroPersonalList;
    }

    public void setLogroPersonalList(List<LogroPersonal> logroPersonalList) {
        this.logroPersonalList = logroPersonalList;
    }

    @XmlTransient
    public List<Oficio> getOficioList() {
        return oficioList;
    }

    public void setOficioList(List<Oficio> oficioList) {
        this.oficioList = oficioList;
    }

    @XmlTransient
    public List<PreferenciaLaboral> getPreferenciaLaboralList() {
        return preferenciaLaboralList;
    }

    public void setPreferenciaLaboralList(List<PreferenciaLaboral> preferenciaLaboralList) {
        this.preferenciaLaboralList = preferenciaLaboralList;
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
        hash += (idHojaVidaEstudiante != null ? idHojaVidaEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HojaVidaEstudiante)) {
            return false;
        }
        HojaVidaEstudiante other = (HojaVidaEstudiante) object;
        if ((this.idHojaVidaEstudiante == null && other.idHojaVidaEstudiante != null) || (this.idHojaVidaEstudiante != null && !this.idHojaVidaEstudiante.equals(other.idHojaVidaEstudiante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idPersona.getNombre()+" "+idPersona.getApellido();
    }
    
}
