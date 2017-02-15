package es.alex.futvre.DTO;

import java.util.List;

public class FavoritosUsuarioDTO {
	
	private int duracion;
	private int numeroCanciones;
	private List<CancionesDeListasUsuarioDto> canciones;
	private List<String> imgArtistas;
		
	
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
	
	public int getNumeroCanciones() {
		return numeroCanciones;
	}
	public void setNumeroCanciones(int numeroCanciones) {
		this.numeroCanciones = numeroCanciones;
	}
	
	
}
