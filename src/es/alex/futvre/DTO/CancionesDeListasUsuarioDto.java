package es.alex.futvre.DTO;

public class CancionesDeListasUsuarioDto {

	private int idCancion;

	private String id_youtube;

	private String nombre;

	private String artista;

	private String album;

	private int duracion;
	
	private int lista;

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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getLista() {
		return lista;
	}

	public void setLista(int lista) {
		this.lista = lista;
	}

}
