package es.alex.futvre.DTO;

public class TrackListaReproduccionDTO {

	private String id;
	private String nombre;
	private String album;
	private String artista;
	private String duracion;	
	private String reproduciendo;
	private String idYoutube;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAlbum() {
		return album;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getReproduciendo() {
		return reproduciendo;
	}
	public void setReproduciendo(String reproduciendo) {
		this.reproduciendo = reproduciendo;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getIdYoutube() {
		return idYoutube;
	}
	public void setIdYoutube(String idYoutube) {
		this.idYoutube = idYoutube;
	}
	
}
