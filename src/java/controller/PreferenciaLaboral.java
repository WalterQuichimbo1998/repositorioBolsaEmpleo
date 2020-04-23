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
@Table(name = "preferencia_laboral", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PreferenciaLaboral.findAll", query = "SELECT p FROM PreferenciaLaboral p")
    , @NamedQuery(name = "PreferenciaLaboral.findByIdPreferenciaLaboral", query = "SELECT p FROM PreferenciaLaboral p WHERE p.idPreferenciaLaboral = :idPreferenciaLaboral")
    , @NamedQuery(name = "PreferenciaLaboral.findBySectorPublico", query = "SELECT p FROM PreferenciaLaboral p WHERE p.sectorPublico = :sectorPublico")
    , @NamedQuery(name = "PreferenciaLaboral.findBySectorPrvado", query = "SELECT p FROM PreferenciaLaboral p WHERE p.sectorPrvado = :sectorPrvado")
    , @NamedQuery(name = "PreferenciaLaboral.findByAspiracionSalarial", query = "SELECT p FROM PreferenciaLaboral p WHERE p.aspiracionSalarial = :aspiracionSalarial")
    , @NamedQuery(name = "PreferenciaLaboral.findByTrabajarResidencia", query = "SELECT p FROM PreferenciaLaboral p WHERE p.trabajarResidencia = :trabajarResidencia")})
public class PreferenciaLaboral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_preferencia_laboral")
    private Integer idPreferenciaLaboral;
    @Column(name = "sector_publico")
    private Integer sectorPublico;
    @Column(name = "sector_prvado")
    private Integer sectorPrvado;
    @Size(max = 45)
    @Column(name = "aspiracion_salarial")
    private String aspiracionSalarial;
    @Column(name = "trabajar_residencia")
    private Integer trabajarResidencia;
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;

    public PreferenciaLaboral() {
    }

    public PreferenciaLaboral(Integer idPreferenciaLaboral) {
        this.idPreferenciaLaboral = idPreferenciaLaboral;
    }

    public Integer getIdPreferenciaLaboral() {
        return idPreferenciaLaboral;
    }

    public void setIdPreferenciaLaboral(Integer idPreferenciaLaboral) {
        this.idPreferenciaLaboral = idPreferenciaLaboral;
    }

    public Integer getSectorPublico() {
        return sectorPublico;
    }

    public void setSectorPublico(Integer sectorPublico) {
        this.sectorPublico = sectorPublico;
    }

    public Integer getSectorPrvado() {
        return sectorPrvado;
    }

    public void setSectorPrvado(Integer sectorPrvado) {
        this.sectorPrvado = sectorPrvado;
    }

    public String getAspiracionSalarial() {
        return aspiracionSalarial;
    }

    public void setAspiracionSalarial(String aspiracionSalarial) {
        this.aspiracionSalarial = aspiracionSalarial;
    }

    public Integer getTrabajarResidencia() {
        return trabajarResidencia;
    }

    public void setTrabajarResidencia(Integer trabajarResidencia) {
        this.trabajarResidencia = trabajarResidencia;
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
        hash += (idPreferenciaLaboral != null ? idPreferenciaLaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreferenciaLaboral)) {
            return false;
        }
        PreferenciaLaboral other = (PreferenciaLaboral) object;
        if ((this.idPreferenciaLaboral == null && other.idPreferenciaLaboral != null) || (this.idPreferenciaLaboral != null && !this.idPreferenciaLaboral.equals(other.idPreferenciaLaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.PreferenciaLaboral[ idPreferenciaLaboral=" + idPreferenciaLaboral + " ]";
    }

   
}
