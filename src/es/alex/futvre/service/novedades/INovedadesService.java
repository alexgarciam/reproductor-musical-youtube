package es.alex.futvre.service.novedades;

import java.util.List;

import es.alex.futvre.DTO.EmisionesDTO;
import es.alex.futvre.DTO.NoticiaDTO;
import es.alex.futvre.DTO.PatrocinadosDTO;

public interface INovedadesService {
	
	/**
	 * Obtiene las novedades
	 * @return
	 * @throws NovedadesServiceException
	 */
	@SuppressWarnings("unchecked")
	public List getNovedades() throws NovedadesServiceException;
	
	/**
	 * Obtiene las noticias
	 * @return
	 * @throws NovedadesServiceException
	 */
	public List<NoticiaDTO> getNocitias() throws NovedadesServiceException;
	
	
	/**
	 * Obtiene las emisiones especiales
	 * @return
	 * @throws NovedadesServiceException
	 */
	public List<EmisionesDTO>  getEmisiones() throws NovedadesServiceException;
	
	
	/**
	 * Obtiene los patrocinados
	 * @return
	 * @throws NovedadesServiceException
	 */
	public List<PatrocinadosDTO> getPatrocinados() throws NovedadesServiceException;
	
	

}
