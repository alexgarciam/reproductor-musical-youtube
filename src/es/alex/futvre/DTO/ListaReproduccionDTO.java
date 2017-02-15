package es.alex.futvre.DTO;

import java.util.Collection;

public class ListaReproduccionDTO {
	
	private String nombre;
	private Collection<TrackListaReproduccionDTO> temas; 
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Collection<TrackListaReproduccionDTO> getTemas() {
		return temas;
	}
	public void setTemas(Collection<TrackListaReproduccionDTO> temas) {
		this.temas = temas;
	}
}
