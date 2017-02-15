package es.alex.futvre.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import es.alex.futvre.DTO.CancionesDeListasUsuarioDto;
import es.alex.futvre.DTO.ListaUsuarioInformacionDTO;
import es.alex.futvre.persistence.Cancion;
import es.alex.futvre.persistence.Lista;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.service.persistence.listas.IListasPersistenceService;
import es.alex.futvre.utils.Utils;

public class GetCancionesFromUserList  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		registro = Utils.configurarLog(GetCancionesFromUserList.class);		
		ApplicationContext ctx;
		ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.getServlet()
						.getServletContext());
		
		String idLista = request.getParameter("idLista");
		String nombreLista = request.getParameter("nombreLista");
		
		ListaUsuarioInformacionDTO dto=new ListaUsuarioInformacionDTO();
		dto.setNombreLista(nombreLista);
		dto.setIdLista(Integer.parseInt(idLista));
		
		IListasPersistenceService listasService=null;
		listasService = (IListasPersistenceService) ctx.getBean("listasPersistenceService");
		
		boolean done=false;
		List<Cancion> canciones=new ArrayList<Cancion>();
		List<String> artistas=new ArrayList<String>();
		List<String> imagenesArtistas=new ArrayList<String>();
		
		int duracionTotalLista=0;
		int numeroCanciones=0;
		try{
			if(nombreLista.equals("undefined")){
				Lista lista=listasService.getListaUsuario(Integer.parseInt(idLista));
				dto.setNombreLista(lista.getNombre());
			}
						
			canciones=listasService.getCancionesFromLista(new Integer(idLista));			 							 
			request.setAttribute("listasUsuario", done);	
			
			
			List<CancionesDeListasUsuarioDto> misCanciones=new ArrayList<CancionesDeListasUsuarioDto>();
			for (Cancion cancion : canciones) {
				CancionesDeListasUsuarioDto cancionesDeListasUsuarioDto=new CancionesDeListasUsuarioDto();
				cancionesDeListasUsuarioDto.setNombre(cancion.getNombre());
				cancionesDeListasUsuarioDto.setAlbum(cancion.getAlbum());
				cancionesDeListasUsuarioDto.setArtista(cancion.getArtista());
				cancionesDeListasUsuarioDto.setId_youtube(cancion.getId_youtube());
				cancionesDeListasUsuarioDto.setDuracion(cancion.getDuracion());
				cancionesDeListasUsuarioDto.setIdCancion(cancion.getIdCancion());
				cancionesDeListasUsuarioDto.setLista(cancion.getLista().getIdLista());
				misCanciones.add(cancionesDeListasUsuarioDto);
				duracionTotalLista+=cancion.getDuracion();
				if(!artistas.contains(cancion.getArtista())){
					artistas.add(cancion.getArtista());
				}
				numeroCanciones++;
			}		
			
			//Ahora cogemos las imagenes de los artistas
			ILastFMService lastFMService = null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			if(artistas.size()<4){
				for (String artista : artistas) {
					imagenesArtistas.add(lastFMService.getArtistImageSmall(artista));
				}
			}
			else{
				for (String artista : artistas) {
					imagenesArtistas.add(lastFMService.getArtistImage(artista));
				}
			}
			
			//guardamos
			dto.setCanciones(misCanciones);
			dto.setImgArtistas(imagenesArtistas);
			dto.setDuracion(duracionTotalLista);
			dto.setNumeroCanciones(numeroCanciones);
			
			Gson gson = new Gson();			
			String jsonOutput = null;
			try{
				jsonOutput = gson.toJson(dto);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
			}
			
			System.out.println("json: "+jsonOutput);			
			request.setAttribute("listaRepUser", jsonOutput);
			 
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			done=false;
			return mapping.findForward("success");
		}		
		
		return mapping.findForward("success");
	}
}
