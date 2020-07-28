/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "baul_recomendaciones", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaulRecomendaciones.findAll", query = "SELECT b FROM BaulRecomendaciones b")
    , @NamedQuery(name = "BaulRecomendaciones.findByIdBaulRecomendaciones", query = "SELECT b FROM BaulRecomendaciones b WHERE b.idBaulRecomendaciones = :idBaulRecomendaciones")
    , @NamedQuery(name = "BaulRecomendaciones.findByRecomendacion", query = "SELECT b FROM BaulRecomendaciones b WHERE b.recomendacion = :recomendacion")})
public class BaulRecomendaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_baul_recomendaciones")
    private Integer idBaulRecomendaciones;
    @Size(max = 200)
    @Column(name = "recomendacion")
    private String recomendacion;
   
    @JoinColumn(name = "id_hoja_vida_estudiante", referencedColumnName = "id_hoja_vida_estudiante")
    @ManyToOne(optional = false)
    private HojaVidaEstudiante idHojaVidaEstudiante;
    
    public BaulRecomendaciones() {
    }

    public BaulRecomendaciones(Integer idBaulRecomendaciones) {
        this.idBaulRecomendaciones = idBaulRecomendaciones;
    }

    public Integer getIdBaulRecomendaciones() {
        return idBaulRecomendaciones;
    }
    
    public void setIdBaulRecomendaciones(Integer idBaulRecomendaciones) {
        this.idBaulRecomendaciones = idBaulRecomendaciones;
    }
    
    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
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
        hash += (idBaulRecomendaciones != null ? idBaulRecomendaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaulRecomendaciones)) {
            return false;
        }
        BaulRecomendaciones other = (BaulRecomendaciones) object;
        if ((this.idBaulRecomendaciones == null && other.idBaulRecomendaciones != null) || (this.idBaulRecomendaciones != null && !this.idBaulRecomendaciones.equals(other.idBaulRecomendaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.BaulRecomendaciones[ idBaulRecomendaciones=" + idBaulRecomendaciones + " ]";
    }
    
}
