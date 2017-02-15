package es.alex.futvre.DTO;

public class LastFMGTrackDTO extends LastFMTrackDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imageURL;

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public LastFMGTrackDTO(){
		super();
	}
	
}
