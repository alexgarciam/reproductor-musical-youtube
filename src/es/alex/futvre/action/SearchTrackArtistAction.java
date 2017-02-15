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

import es.alex.futvre.DTO.LastFMArtistDTO;
import es.alex.futvre.service.lastFM.ILastFMService;
import es.alex.futvre.utils.Utils;

public class SearchTrackArtistAction  extends DispatchAction {
	private static org.apache.log4j.Logger registro;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		registro = Utils.configurarLog(SearchTrackArtistAction.class);
		String q=request.getParameter("q");
			
		String tamanyoPantalla = request.getParameter("tamPantalla");
		request.setAttribute("tamanyoPantalla", tamanyoPantalla);
		request.getSession().setAttribute("tamanyoPantalla", tamanyoPantalla);
		
		
		List<LastFMArtistDTO> artistas = null;
		
		if(q!=null)
		{
			ApplicationContext ctx;
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServlet()
							.getServletContext());
			
			ILastFMService lastFMService =null;
			lastFMService = (ILastFMService) ctx.getBean("lastFMService");
						
						
			try{
				artistas=lastFMService.searchArtist(q);	
			}catch(Exception e){
				registro.error(this.getClass()+"-Error: " + e.getMessage());
				
				request.setAttribute("artistas",artistas);
				request.getSession().setAttribute("artistas",artistas);
				List<LastFMArtistDTO> albumsCaratulas=new ArrayList<LastFMArtistDTO>();
				request.setAttribute("artistasCaratulas",albumsCaratulas);
				request.getSession().setAttribute("artistasCaratulas",albumsCaratulas);
				
				return mapping.findForward("success");
			}		
			
			List<LastFMArtistDTO> albumsCaratulas=new ArrayList<LastFMArtistDTO>();

			
			//si alguno viene a null
			for (LastFMArtistDTO lastFMArtistDTO : artistas) {				
				
				if(lastFMArtistDTO.getName()==null ||lastFMArtistDTO.getName().equals(""))		
					lastFMArtistDTO.setName(q);
				if(lastFMArtistDTO.getImageURL()==null ||lastFMArtistDTO.getImageURL().equals(""))	
					lastFMArtistDTO.setImageURL("http://userserve-ak.last.fm/serve/252/18201619.jpg");	
				
			}		
			
			
			int contador=0;
			for (LastFMArtistDTO lastFMArtistDTO : artistas) {
				if(contador<10){
				LastFMArtistDTO  artist=new LastFMArtistDTO();	
				if(lastFMArtistDTO.getName()!=null ||lastFMArtistDTO.getName().equals("") )					
					artist.setName(lastFMArtistDTO.getName());
				else //imagen no encontrada
					artist.setName(q);
				if(lastFMArtistDTO.getImageURL()!=null ||lastFMArtistDTO.getImageURL().equals(""))					
					artist.setImageURL(lastFMArtistDTO.getImageURL());
				else //imagen no encontrada
					artist.setImageURL("http://userserve-ak.last.fm/serve/252/18201619.jpg");				
				albumsCaratulas.add(artist);
				contador++;
				}
			}					
			
			request.setAttribute("artistas",artistas);
			request.getSession().setAttribute("artistas",artistas);

			request.setAttribute("artistasCaratulas",albumsCaratulas);
			request.getSession().setAttribute("artistasCaratulas",albumsCaratulas);
			
			
			String artistasJson= null;
			Gson gson = new Gson();
			artistasJson = gson.toJson(artistas);
			
			request.setAttribute("artistasJson",artistasJson);
			request.getSession().setAttribute("artistasJson",artistasJson);
			
			String albumsCaratulasJson = null;
			Gson gson2 = new Gson();
			albumsCaratulasJson = gson2.toJson(albumsCaratulas);
			
			request.setAttribute("albumsCaratulasJson",albumsCaratulasJson);
			request.getSession().setAttribute("albumsCaratulasJson",albumsCaratulasJson);
			
			
		}
		
		return mapping.findForward("success");
	}
		
}
