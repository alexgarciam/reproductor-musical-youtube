package es.alex.futvre.DTO;

import java.io.Serializable;
import java.util.Collection;

public class LastFMArtistDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String imageURL;	
	private String summary;
	private String bio;
	private  Collection<LastFMArtistDTO> similares;
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	private Collection<LastFMAlbumDTO> albums;
	
	public String getId() {
		return id;
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
	public Collection<LastFMAlbumDTO> getAlbums() {
		return albums;
	}
	public void setAlbums(Collection<LastFMAlbumDTO> albums) {
		this.albums = albums;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public Collection<LastFMArtistDTO> getSimilares() {
		return similares;
	}
	public void setSimilares(Collection<LastFMArtistDTO> similares) {
		this.similares = similares;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
