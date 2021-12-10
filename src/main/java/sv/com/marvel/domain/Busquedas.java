package sv.com.marvel.domain;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 50364
 */
@Entity
@Table(name = "busquedas")
public class Busquedas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbusquedas")
    private Integer idbusquedas;
   
    @Column(name = "busqueda")
    private String busqueda;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")    
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Busquedas() {
    }

    public Busquedas(Integer idbusquedas) {
        this.idbusquedas = idbusquedas;
    }

    public Busquedas(Integer idbusquedas, String busqueda) {
        this.idbusquedas = idbusquedas;
        this.busqueda = busqueda;
    }

    public Integer getIdbusquedas() {
        return idbusquedas;
    }

    public void setIdbusquedas(Integer idbusquedas) {
        this.idbusquedas = idbusquedas;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbusquedas != null ? idbusquedas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Busquedas)) {
            return false;
        }
        Busquedas other = (Busquedas) object;
        if ((this.idbusquedas == null && other.idbusquedas != null) || (this.idbusquedas != null && !this.idbusquedas.equals(other.idbusquedas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.com.marvel.ws.domain.Busquedas[ idbusquedas=" + idbusquedas + " ]";
    }
    
}
