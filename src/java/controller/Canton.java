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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "canton", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canton.findAll", query = "SELECT c FROM Canton c")
    , @NamedQuery(name = "Canton.findByIdCanton", query = "SELECT c FROM Canton c WHERE c.idCanton = :idCanton")
    , @NamedQuery(name = "Canton.findByCanton", query = "SELECT c FROM Canton c WHERE c.canton = :canton")})
public class Canton implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_canton")
    private Integer idCanton;
    @Size(max = 45)
    @Column(name = "canton")
    private String canton;
//    @OneToMany(mappedBy = "idCanton")
//    private List<OfertaLaboral> ofertaLaboralList;
    @OneToMany(mappedBy = "idCanton")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "idCanton")
    private List<Parroquia> parroquiaList;
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia")
    @ManyToOne
    private Provincia idProvincia;
    @OneToMany(mappedBy = "idCanton")
    private List<Empresa> empresaList;

    public Canton() {
    }

    public Canton(Integer idCanton) {
        this.idCanton = idCanton;
    }

    public Integer getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Integer idCanton) {
        this.idCanton = idCanton;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }
//
//    @XmlTransient
//    public List<OfertaLaboral> getOfertaLaboralList() {
//        return ofertaLaboralList;
//    }
//
//    public void setOfertaLaboralList(List<OfertaLaboral> ofertaLaboralList) {
//        this.ofertaLaboralList = ofertaLaboralList;
//    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }
//
    @XmlTransient
    public List<Parroquia> getParroquiaList() {
        return parroquiaList;
    }

    public void setParroquiaList(List<Parroquia> parroquiaList) {
        this.parroquiaList = parroquiaList;
    }
//
    public Provincia getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Provincia idProvincia) {
        this.idProvincia = idProvincia;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCanton != null ? idCanton.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Canton)) {
            return false;
        }
        Canton other = (Canton) object;
        if ((this.idCanton == null && other.idCanton != null) || (this.idCanton != null && !this.idCanton.equals(other.idCanton))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.canton;
    }
    
}
