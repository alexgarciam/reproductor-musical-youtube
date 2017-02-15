package es.alex.futvre.service.youtube;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gdata.client.Query;
import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.media.mediarss.MediaThumbnail;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;

import es.alex.futvre.DTO.YoutubeVideoDTO;
import es.alex.futvre.utils.Utils;

public class YoutubeServiceImpl implements IYoutubeService {

	private static org.apache.log4j.Logger registro;
	
	public YoutubeVideoDTO getYoutubeVideo(String idVideo)
			throws YoutubeServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<YoutubeVideoDTO> searchYoutubeVideo(String q)
			throws YoutubeServiceException {
		
		registro = Utils.configurarLog(YoutubeServiceImpl.class);
		
		q=q.replace(" ","+");
				
		q=Utils.suprimirCaracteresEspeciales(q);
		
		YouTubeQuery query = null;
		try {
			String youtubeUrl = Utils.getStringResource("application.youtube.videos.feeds.url");
			query = new YouTubeQuery(new URL(youtubeUrl+q));
		} catch (MalformedURLException e1) {			
			registro.error(this.getClass()+"-Error: " + e1.getMessage());
			throw new YoutubeServiceException(e1.getMessage());
		}

		// a category filter holds a collection of categories to limit the search
		Query.CategoryFilter categoryFilter1 = new Query.CategoryFilter();
		Query.CategoryFilter categoryFilter2 = new Query.CategoryFilter();
				
		// multiple filters mean "AND" in a category query
		query.addCategoryFilter(categoryFilter1);
		query.addCategoryFilter(categoryFilter2);

		String youtubeService = Utils.getStringResource("application.youtube.service.name");
		YouTubeService myService = new YouTubeService(youtubeService);
		 
		VideoFeed videoFeed = null;
		try {
			videoFeed = myService.query(query, VideoFeed.class);
		} catch (IOException e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new YoutubeServiceException(e.getMessage());
		} catch (ServiceException e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new YoutubeServiceException(e.getMessage());
		}
			
		List<YoutubeVideoDTO> videos=new ArrayList<YoutubeVideoDTO>();
		try{
			for(VideoEntry entry : videoFeed.getEntries() ) {
				YoutubeVideoDTO video=new YoutubeVideoDTO();
				video.setTitulo(entry.getTitle().getPlainText());
				video.setDescripcion(entry.getMediaGroup().getDescription().getPlainTextContent());
				List<MediaThumbnail> thumbnails=entry.getMediaGroup().getThumbnails();
				MediaThumbnail thumbnail=thumbnails.get(0);;			
				video.setUrlImagen(thumbnail.getUrl());
				video.setUrlVideo(entry.getHtmlLink().getHref());
				video.setIdVideo(entry.getMediaGroup().getVideoId());
				video.setDuration(entry.getMediaGroup().getDuration().toString());
				videos.add(video);
			
			}
		}catch(Exception e){
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new YoutubeServiceException(e.getMessage());
		}
			    
		return videos;
	}
	
	

}
