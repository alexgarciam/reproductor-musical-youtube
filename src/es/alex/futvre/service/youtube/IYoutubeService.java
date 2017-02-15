package es.alex.futvre.service.youtube;

import java.util.List;

import es.alex.futvre.DTO.YoutubeVideoDTO;

public interface IYoutubeService {
	
	public YoutubeVideoDTO getYoutubeVideo(String idVideo) throws YoutubeServiceException;
	
	public List<YoutubeVideoDTO> searchYoutubeVideo(String query) throws YoutubeServiceException;
	
	
}
