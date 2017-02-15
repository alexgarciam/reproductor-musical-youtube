package es.alex.futvre.DTO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class LastFMAlbumDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String urlImage;
	private Collection<LastFMTrackDTO> tracks;	
	private Date releaseDate;
	private String description;
	private String artist;
	
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getId() {
		return id;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Collection<LastFMTrackDTO> getTracks() {
		return tracks;
	}
	public void setTracks(Collection<LastFMTrackDTO> tracks) {
		this.tracks = tracks;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	
}
