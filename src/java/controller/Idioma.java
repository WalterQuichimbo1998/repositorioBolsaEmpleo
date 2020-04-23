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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "idioma", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Idioma.findAll", query = "SELECT i FROM Idioma i")
    , @NamedQuery(name = "Idioma.findByIdIdioma", query = "SELECT i FROM Idioma i WHERE i.idIdioma = :idIdioma")
    , @NamedQuery(name = "Idioma.findByIdIdioma2", query = "SELECT i FROM Idioma i WHERE i.idHojaVidaEstudiante = :id")
})
public class Idioma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_idioma")
    private Integer idIdioma;
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;
    @JoinColumn(name = "id_lista_idiomas", referencedColumnName = "id_lista_idiomas")
    @ManyToOne
    private ListaIdiomas idListaIdiomas;
    @JoinColumn(name = "id_nivel_escrito", referencedColumnName = "id_nivel_escrito")
    @ManyToOne
    private NivelEscrito idNivelEscrito;
    @JoinColumn(name = "id_nivel_hablado", referencedColumnName = "id_nivel_hablado")
    @ManyToOne
    private NivelHablado idNivelHablado;

    public Idioma() {
    }

    public Idioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public HojaVidaEstudiante getIdHojaVidaEstudiante() {
        return idHojaVidaEstudiante;
    }

    public void setIdHojaVidaEstudiante(HojaVidaEstudiante idHojaVidaEstudiante) {
        this.idHojaVidaEstudiante = idHojaVidaEstudiante;
    }

    public ListaIdiomas getIdListaIdiomas() {
        return idListaIdiomas;
    }

    public void setIdListaIdiomas(ListaIdiomas idListaIdiomas) {
        this.idListaIdiomas = idListaIdiomas;
    }

    public NivelEscrito getIdNivelEscrito() {
        return idNivelEscrito;
    }

    public void setIdNivelEscrito(NivelEscrito idNivelEscrito) {
        this.idNivelEscrito = idNivelEscrito;
    }

    public NivelHablado getIdNivelHablado() {
        return idNivelHablado;
    }

    public void setIdNivelHablado(NivelHablado idNivelHablado) {
        this.idNivelHablado = idNivelHablado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) object;
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.Idioma[ idIdioma=" + idIdioma + " ]";
    }
    
}
