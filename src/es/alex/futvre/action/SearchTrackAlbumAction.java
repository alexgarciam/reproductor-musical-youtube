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

import es.alex.futvre.DTO.LastFMAlbumDTO;
import es.alex.futvre.DTO.LastFMArtistDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class SearchTrackAlbumAction  extends DispatchAction {

	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String q=request.getParameter("q");
		String tamanyoPantalla = request.getParameter("tamPantalla");
		request.setAttribute("tamanyoPantalla", tamanyoPantalla);
		request.getSession().setAttribute("tamanyoPantalla", tamanyoPantalla);
		
		registro = Utils.configurarLog(SearchTrackAlbumAction.class);
		
		registro.warn("se ha iniciado la busqueda de albumss");
		
		List<LastFMAlbumDTO> albums = null;		
		
		if(q!=null)
		{
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			
			ILastFMService lastFMService =null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
			
			
			try{
				albums=lastFMService.searchAlbum(q);
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
				albums=new ArrayList<LastFMAlbumDTO>();
				request.setAttribute("albums",albums);
				request.getSession().setAttribute("albums",albums);
				List<LastFMAlbumDTO> albumsCaratulas=new ArrayList<LastFMAlbumDTO>();
				request.setAttribute("albumsCaratulas",albumsCaratulas);
				request.getSession().setAttribute("albumsCaratulas",albumsCaratulas);
				
				return mapping.findForward("success");
			}
			
			//tomamos los diez primeros porque el componente de coverFlow falla!!
			List<LastFMAlbumDTO> albumsCaratulas=new ArrayList<LastFMAlbumDTO>();
			
			
			//si alguna imagen está a null la cambiamos por la imagen por defecto
			//si alguno viene a null
			for (LastFMAlbumDTO lastFMAlbumDTO : albums) {				
				
				if(lastFMAlbumDTO.getName()==null ||lastFMAlbumDTO.getName().equals(""))		
					lastFMAlbumDTO.setName(q);
				if(lastFMAlbumDTO.getUrlImage()==null ||lastFMAlbumDTO.getUrlImage().equals(""))	
					lastFMAlbumDTO.setUrlImage("http://userserve-ak.last.fm/serve/252/18201619.jpg");	
				
			}	
			
			int contador=0;
			for (LastFMAlbumDTO lastFMAlbumDTO : albums) {
				if(contador<10){
				LastFMAlbumDTO  album=new LastFMAlbumDTO();
				album.setArtist(lastFMAlbumDTO.getArtist());
				album.setName(lastFMAlbumDTO.getName());
				if(lastFMAlbumDTO.getName()==null ||lastFMAlbumDTO.getName().equals(""))		
					lastFMAlbumDTO.setName(q);
				album.setUrlImage(lastFMAlbumDTO.getUrlImage());
				if(lastFMAlbumDTO.getUrlImage()==null ||lastFMAlbumDTO.getUrlImage().equals(""))	
					lastFMAlbumDTO.setUrlImage("http://userserve-ak.last.fm/serve/252/18201619.jpg");	
				albumsCaratulas.add(album);
				contador++;
				}
			}					
				
			request.setAttribute("albums",albums);
			request.getSession().setAttribute("albums",albums);
			
			request.setAttribute("albumsCaratulas",albumsCaratulas);
			request.getSession().setAttribute("albumsCaratulas",albumsCaratulas);
		
		}
		
		return mapping.findForward("success");
	}
		
}
