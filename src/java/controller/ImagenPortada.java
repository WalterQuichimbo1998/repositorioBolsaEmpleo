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
@Table(name = "imagen_portada", catalog = "bolsa_empleo_istl", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImagenPortada.findAll", query = "SELECT i FROM ImagenPortada i")
    , @NamedQuery(name = "ImagenPortada.findByIdImagen", query = "SELECT i FROM ImagenPortada i WHERE i.idImagen = :idImagen")
    , @NamedQuery(name = "ImagenPortada.findByImagen", query = "SELECT i FROM ImagenPortada i WHERE i.imagen = :imagen")})
public class ImagenPortada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_imagen")
    private Integer idImagen;
    @Size(max = 45)
    @Column(name = "imagen")
    private String imagen;

    public ImagenPortada() {
    }

    public ImagenPortada(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImagenPortada)) {
            return false;
        }
        ImagenPortada other = (ImagenPortada) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "controller.ImagenPortada[ idImagen=" + idImagen + " ]";
    }
    
}
