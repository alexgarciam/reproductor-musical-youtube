package es.alex.futvre.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity  
@Table(name = "CANCIONES" ,uniqueConstraints=@UniqueConstraint(columnNames={"idCancion"}))
public class Cancion {
 
	@Id
    @Column(name = "idCancion")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idCancion;
	
	@Column(name = "id_youtube")
	private String id_youtube; 
	
    @Column(name = "nombre")
    private String nombre; 
    
    @Column(name = "artista")
    private String artista; 
    
    @Column(name = "album")
    private String album; 
    
    @Column(name = "duracion")
	private Integer duracion; 
   
	@ManyToOne
    @JoinColumn(name = "idLista", nullable=false)
    private Lista lista;
		
	public int getIdCancion() {
		return idCancion;
	}

	public void setIdCancion(int idCancion) {
		this.idCancion = idCancion;
	}

	public String getId_youtube() {
		return id_youtube;
	}

	public void setId_youtube(String id_youtube) {
		this.id_youtube = id_youtube;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}    
	 
    public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	

}