package es.alex.futvre.service.lastFM;

import java.util.Collection;
import java.util.List;

import es.alex.futvre.DTO.LastFMAlbumDTO;
import es.alex.futvre.DTO.LastFMArtistDTO;
import es.alex.futvre.DTO.LastFMGTrackDTO;
import es.alex.futvre.DTO.LastFMTrackDTO;
import es.alex.futvre.DTO.TrackListaReproduccionDTO;

public interface ILastFMService {
	
	/**
	 * Obtiene las novedades
	 * @return
	 * @throws LastFMServiceException
	 */
	public List<LastFMTrackDTO> searchTrack(String query) throws LastFMServiceException;
	
	public List<LastFMAlbumDTO> searchAlbum(String query) throws LastFMServiceException;
	
	public List<LastFMArtistDTO> searchArtist(String query) throws LastFMServiceException;
	
	public LastFMArtistDTO getArtist(String artist) throws LastFMServiceException;
	
	public String getArtistImage(String artist) throws LastFMServiceException;
	
	public Collection<LastFMArtistDTO> getArtistSimilar(String artist) throws LastFMServiceException;
	
	public String getArtistImageSmall(String artist) throws LastFMServiceException;
	
	public LastFMArtistDTO getArtistHeader(String artist) throws LastFMServiceException;	
	
	public LastFMAlbumDTO getAlbum(String artist, String album) throws LastFMServiceException;	

	public LastFMAlbumDTO getAlbumSinInfo(String artist, String album) throws LastFMServiceException;	
	
	public String getTrackID(String song, String artist) throws LastFMServiceException;
	
	public TrackListaReproduccionDTO getTrackInfo(String song, String artist) throws LastFMServiceException;
	
	public LastFMAlbumDTO getAlbumInfo(String artist, String album) throws LastFMServiceException;
	
	public String getAlbumImage(String artist, String album) throws LastFMServiceException;
	
	public List<LastFMTrackDTO> getAlbumTracks(String artist, String album) throws LastFMServiceException;
	
	public String getArtistSummary(String artist) throws LastFMServiceException;
	
	public String getArtistBio(String artist) throws LastFMServiceException;
	
	public List<LastFMGTrackDTO> getHypedTracks() throws LastFMServiceException;
	
	public Collection<LastFMArtistDTO> getHypedArtists() throws LastFMServiceException;
	
	public List<LastFMGTrackDTO> getLovedTracks() throws LastFMServiceException;
	
	public Collection<LastFMArtistDTO> getTopArtists() throws LastFMServiceException;
	
	
	
}
