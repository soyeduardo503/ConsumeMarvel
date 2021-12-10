package sv.com.marvel.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author 50364
 */
@Entity
@Table(name = "token")
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    
    @Column(name = "idtoken")
    private Integer idtoken;
    
    
    @Column(name = "idusuario")
    private Integer idusuario;
    
    @Column(name = "token")
    private String token;
    
    
    public Token() {
    }

    public Token(Integer idToken) {
        this.idtoken = idToken;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    

    public Integer getIdtoken() {
		return idtoken;
	}

	public void setIdtoken(Integer idtoken) {
		this.idtoken = idtoken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idtoken != null ? idtoken.hashCode() : 0);
        return hash;
    }

   
    @Override
    public String toString() {
        return "sv.com.marvel.ws.domain.Token[ idtoken=" + idtoken + " ]";
    }
    
}
