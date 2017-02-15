package es.alex.futvre.service.novedades;

import java.util.ArrayList;
import java.util.List;

import es.alex.futvre.DTO.EmisionesDTO;
import es.alex.futvre.DTO.NoticiaDTO;
import es.alex.futvre.DTO.PatrocinadosDTO;
import es.alex.futvre.action.SearchTrackAlbumAction;
import es.alex.futvre.utils.Utils;

public class NovedadesServiceImpl implements INovedadesService {

	private static org.apache.log4j.Logger registro;

	public List getNovedades() throws NovedadesServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EmisionesDTO> getEmisiones() throws NovedadesServiceException {

		registro = Utils.configurarLog(SearchTrackAlbumAction.class);
		ArrayList<EmisionesDTO> lista = new ArrayList<EmisionesDTO>();

		try {

			EmisionesDTO emisiones1 = new EmisionesDTO();
			emisiones1.setTitulo("Primera emision");
			lista.add(emisiones1);

			for (int i = 1; i < 4; i++) {
				EmisionesDTO noticia = new EmisionesDTO();
				noticia.setTitulo("EmisionesDTO numero " + i);
				lista.add(noticia);
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());			throw new NovedadesServiceException(e.getMessage());
		}

		return lista;
	}

	public List<NoticiaDTO> getNocitias() throws NovedadesServiceException {
		registro = Utils.configurarLog(SearchTrackAlbumAction.class);
		// TODO Auto-generated method stub
		System.out.println("recogiendo noticias");
		ArrayList<NoticiaDTO> lista = new ArrayList<NoticiaDTO>();
		try {
			NoticiaDTO noticia1 = new NoticiaDTO();
			noticia1.setTitulo("Primera noticia");
			lista.add(noticia1);

			for (int i = 1; i < 4; i++) {
				NoticiaDTO noticia = new NoticiaDTO();
				noticia.setTitulo("noticia numero " + i);
				lista.add(noticia);
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new NovedadesServiceException(e.getMessage());
		}
		return lista;
	}

	public List<PatrocinadosDTO> getPatrocinados()
			throws NovedadesServiceException {
		registro = Utils.configurarLog(SearchTrackAlbumAction.class);

		ArrayList<PatrocinadosDTO> lista = new ArrayList<PatrocinadosDTO>();
		try {
			PatrocinadosDTO patrocinado1 = new PatrocinadosDTO();
			patrocinado1.setTitulo("Primer patrocinado");
			lista.add(patrocinado1);
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());			throw new NovedadesServiceException(e.getMessage());
		}
		return lista;
	}

}
