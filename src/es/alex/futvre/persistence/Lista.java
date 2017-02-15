package es.alex.futvre.persistence;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity  
@Table(name = "LISTAS" ,uniqueConstraints=@UniqueConstraint(columnNames={"usuario","nombre"}))
public class Lista {
 
	@Id
    @Column(name = "idLista")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idLista;
	
    @Column(name = "nombre")
    private String nombre; 
    
    @Column(name = "fecha")
    private Date fecha; 
    
    @ManyToOne
    @JoinColumn(name = "usuario", nullable=false)
    private Usuario user;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lista")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Cancion> canciones;
    
    public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Collection<Cancion> getCanciones() {
		return canciones;
	}

	public void setCanciones(Collection<Cancion> canciones) {
		this.canciones = canciones;
	}

	
 


}