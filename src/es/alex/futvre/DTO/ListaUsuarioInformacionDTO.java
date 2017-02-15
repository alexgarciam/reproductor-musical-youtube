package es.alex.futvre.DTO;

import java.util.List;

import es.alex.futvre.persistence.Cancion;

public class ListaUsuarioInformacionDTO {

	private String nombreLista;
	private int idLista;
	private int duracion;
	private int numeroCanciones;
	private List<CancionesDeListasUsuarioDto> canciones;
	private List<String> imgArtistas;
		
	public String getNombreLista() {
		return nombreLista;
	}
	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public List<CancionesDeListasUsuarioDto> getCanciones() {
		return canciones;
	}
	public void setCanciones(List<CancionesDeListasUsuarioDto> canciones) {
		this.canciones = canciones;
	}
	public List<String> getImgArtistas() {
		return imgArtistas;
	}
	public void setImgArtistas(List<String> imgArtistas) {
		this.imgArtistas = imgArtistas;
	}	
	public int getIdLista() {
		return idLista;
	}
	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}
	public int getNumeroCanciones() {
		return numeroCanciones;
	}
	public void setNumeroCanciones(int numeroCanciones) {
		this.numeroCanciones = numeroCanciones;
	}
	
	
}
