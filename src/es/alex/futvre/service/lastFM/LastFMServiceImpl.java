package es.alex.futvre.service.lastFM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Chart;
import de.umass.lastfm.ImageSize;
import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;
import es.alex.futvre.DTO.LastFMAlbumDTO;
import es.alex.futvre.DTO.LastFMArtistDTO;
import es.alex.futvre.DTO.LastFMGTrackDTO;
import es.alex.futvre.DTO.LastFMTrackDTO;
import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.utils.Utils;

@Service("lastFMService")
public class LastFMServiceImpl implements ILastFMService {

	private static org.apache.log4j.Logger registro;

	public List<LastFMTrackDTO> searchTrack(String query)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);

		List<LastFMTrackDTO> tracks = new ArrayList<LastFMTrackDTO>();
		try {
			query = query.trim();
			query = Utils.suprimirCaracteresEspeciales(query);
			query = query.replace(" ", "+");

			String apiKey = Utils.getStringResource("last.fm.api.key");
			Collection<Track> results = Track.search(query, apiKey);

			int contador = 0;
			for (Track track : results) {
				contador++;
				LastFMTrackDTO newTrack = new LastFMTrackDTO();
				
				newTrack.setId(track.getId());
				newTrack.setName(track.getName());
				newTrack.setArtist(track.getArtist());

				Track mytrack = Track.getInfo(track.getArtist(), track
						.getName(), apiKey);
				newTrack.setAlbum(mytrack.getAlbum());
				newTrack.setDuration(mytrack.getDuration());

				tracks.add(newTrack);
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException(
					"Excepcion en trackSearch search: " + query + " \nerror: "
							+ e.getMessage());
		}
		return tracks;
	}

	public LastFMAlbumDTO getAlbum(String artist, String album)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		LastFMAlbumDTO myalbum = new LastFMAlbumDTO();
		try {
			album = album.trim();
			if (album.startsWith("¿")) {
				album = album.substring(1, album.length());
			}
			String apiKey = Utils.getStringResource("last.fm.api.key");

			myalbum.setName(album);
			Album albumLastFM = Album.getInfo(artist, album, apiKey);
			if (albumLastFM != null) {
				myalbum.setUrlImage(albumLastFM.getImageURL(ImageSize.LARGE));
				myalbum.setReleaseDate(albumLastFM.getReleaseDate());
				myalbum.setDescription(albumLastFM.getWikiText());

			}

			myalbum.setTracks(getAlbumTracks(artist, album));

			myalbum.setDescription(getAlbumContent(artist, album));
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getAlbum: "
					+ e.getMessage());
		}
		return myalbum;
	}

	@SuppressWarnings("deprecation")
	public LastFMAlbumDTO getAlbumSinInfo(String artist, String album)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		LastFMAlbumDTO myalbum = new LastFMAlbumDTO();
		try {
			album = album.trim();
			if (album.startsWith("¿")) {
				album = album.substring(1, album.length());
			}
			String apiKey = Utils.getStringResource("last.fm.api.key");

			Album albumLastFM = Album.getInfo(artist, album, apiKey);
			if (albumLastFM != null) {

				try {
					album = new String(album.getBytes(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					registro.error(this.getClass()+"-Error: " + e.getMessage());
					throw new LastFMServiceException(
							"Excepcion en getAlbumSinInfo: " + e.getMessage());
				}
				myalbum.setName(URLEncoder.encode(album));
				myalbum.setArtist(URLEncoder.encode(artist));
				myalbum.setUrlImage(albumLastFM.getImageURL(ImageSize.LARGE));
				myalbum.setReleaseDate(albumLastFM.getReleaseDate());
				myalbum.setDescription(albumLastFM.getWikiText());

			}

			myalbum.setTracks(getAlbumTracks(artist, album));
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getAlbumSinInfo: "
					+ e.getMessage());
		}

		return myalbum;
	}

	public String getArtistImage(String artist) throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		String image = "";
		try {
			String apiKey = Utils.getStringResource("last.fm.api.key");
			Artist result = Artist.getInfo(artist, apiKey);
			image = result.getImageURL(ImageSize.LARGE);
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getArtistImage: "
					+ e.getMessage());
		}
		return image;
	}
	
	public String getArtistImageSmall(String artist) throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		String image = "";
		try {
			String apiKey = Utils.getStringResource("last.fm.api.key");
			Artist result = Artist.getInfo(artist, apiKey);
			image = result.getImageURL(ImageSize.MEDIUM);
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			return "image error";
		}
		return image;
	}

	public LastFMArtistDTO getArtist(String artist)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		LastFMArtistDTO artista = new LastFMArtistDTO();
		try {
			String apiKey = Utils.getStringResource("last.fm.api.key");
			Artist result = Artist.getInfo(artist, apiKey);
			Collection<Album> albums = Artist.getTopAlbums(artist, apiKey);

			artista.setId(result.getId());
			artista.setName(result.getName());
			artista.setSummary(this.getArtistSummary(artist));

			artista.setImageURL(result.getImageURL(ImageSize.LARGE));
			Collection<LastFMAlbumDTO> albumes = new ArrayList<LastFMAlbumDTO>();

			for (Album album : albums) {
				LastFMAlbumDTO myalbum = this.getAlbumSinInfo(artist, album
						.getName());
				myalbum.setUrlImage(album.getImageURL(ImageSize.LARGE));
				albumes.add(myalbum);
			}
			artista.setAlbums(albumes);
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getArtist: "
					+ e.getMessage());
		}
		return artista;
	}

	public LastFMArtistDTO getArtistHeader(String artist)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		LastFMArtistDTO artista = new LastFMArtistDTO();
		try {
			String apiKey = Utils.getStringResource("last.fm.api.key");
			artist = Utils.suprimirCaracteresEspeciales(artist);
			//artist = URLEncoder.encode(artist,"UTF-8");
			Artist result = Artist.getInfo(artist, apiKey);
			if(result!=null){
				Collection<Album> albums = Artist.getTopAlbums(artist, apiKey);
				Collection<LastFMAlbumDTO> albumes = new ArrayList<LastFMAlbumDTO>();
	
				for (Album album : albums) {
					LastFMAlbumDTO albumDTO = new LastFMAlbumDTO();
					albumDTO.setName(album.getName());
					albumDTO.setId(album.getMbid());
					albumes.add(albumDTO);
				}
	
				artista.setId(result.getId());
				artista.setName(URLEncoder.encode(result.getName()));
				artista.setSummary(this.getArtistSummary(artist));
				artista.setAlbums(albumes);
				artista.setImageURL(result.getImageURL(ImageSize.LARGE));
			}
			else{
				artista=new LastFMArtistDTO();
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getArtistHeader: "
					+ e.getMessage());
		}
		return artista;
	}

	public String getTrackID(String song, String artist)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		String id = "";
		try {
			String apiKey = Utils.getStringResource("last.fm.api.key");

			Track mytrack = Track.getInfo(artist, song, apiKey);

			if (mytrack != null)
				id = mytrack.getId();
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getTrackID: "
					+ e.getMessage());
		}
		return id;
	}

	public LastFMAlbumDTO getAlbumInfo(String artist, String album)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		LastFMAlbumDTO lastFMAlbumDTO = null;
		try {
			album = album.trim();
			if (album.startsWith("¿")) {
				album = album.substring(1, album.length());
			}
			String album2 = album;

			String apiKey = Utils.getStringResource("last.fm.api.key");

			LastFMAlbumDTO myalbum = new LastFMAlbumDTO();
			myalbum.setName(album);
			String searchedAlbum = album2;
			searchedAlbum = searchedAlbum.replace(" ", "+");

			Album albumLastFM = Album.getInfo(artist, searchedAlbum, apiKey);
			if (albumLastFM != null) {
				lastFMAlbumDTO = new LastFMAlbumDTO();
				lastFMAlbumDTO.setName(album);
				lastFMAlbumDTO.setUrlImage(albumLastFM
						.getImageURL(ImageSize.LARGE));
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getAlbumInfo: "
					+ e.getMessage());
		}
		return lastFMAlbumDTO;
	}

	public String getAlbumImage(String artist, String album)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);

		String imageAlbum = "";
		try {
			album = album.trim();
			LastFMAlbumDTO myalbum = this.getAlbumInfo(artist, album);
			if (myalbum == null || myalbum.getUrlImage().equals("")) {
				String apiKey = Utils.getStringResource("last.fm.api.key");
				Collection<Album> albums = Artist.getTopAlbums(artist, apiKey);
				Iterator<Album> italbum = albums.iterator();
				boolean enc = false;
				while (italbum.hasNext() && !enc) {
					Album ab = italbum.next();
					if (ab.getName().equals(album)) {
						imageAlbum = ab.getImageURL(ImageSize.EXTRALARGE);
						if (imageAlbum == null || imageAlbum.equals("")) {
							imageAlbum = ab.getImageURL(ImageSize.HUGE);
							if (imageAlbum == null || imageAlbum.equals("")) {
								imageAlbum = ab.getImageURL(ImageSize.LARGE);
								if (imageAlbum == null || imageAlbum.equals("")) {
									imageAlbum = ab
											.getImageURL(ImageSize.LARGESQUARE);
									if (imageAlbum == null
											|| imageAlbum.equals("")) {
										imageAlbum = ab
												.getImageURL(ImageSize.MEDIUM);
										if (imageAlbum == null
												|| imageAlbum.equals("")) {
											imageAlbum = ab
													.getImageURL(ImageSize.MEGA);
											if (imageAlbum == null
													|| imageAlbum.equals("")) {
												imageAlbum = ab
														.getImageURL(ImageSize.ORIGINAL);
											}
										}
									}
								}
							}
						}
						enc = true;
					}
				}
			} else {
				imageAlbum = myalbum.getUrlImage();
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getAlbumImage: "
					+ e.getMessage());
		}
		return imageAlbum;
	}

	@SuppressWarnings("deprecation")
	public List<LastFMTrackDTO> getAlbumTracks(String artist, String album)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);

		album = album.trim();
		if (album.startsWith("¿")) {
			album = album.substring(1, album.length());
		}
		String album2 = Utils.suprimirCaracteresEspeciales(album);
		String artist2 = Utils.suprimirCaracteresEspeciales(artist);
		String elartista = Utils.suprimirCaracteresEspeciales(artist);
		// System.out.println("autorrr: "+artist2);

		album2 = album2.replace(" ", "+");
		artist2 = artist2.replace(" ", "+");

		LastFMAlbumDTO myalbum = new LastFMAlbumDTO();
		myalbum.setName(album);

		List<LastFMTrackDTO> tracks = new ArrayList<LastFMTrackDTO>();

		// ---------------------------
		// URL del sercivio Web
		URL url = null;
		String apiKey = Utils.getStringResource("last.fm.api.key");
		url = getUrl("http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key="+apiKey+"&artist="
				+ artist2 + "&album=" + album2);

		// Establecemos la conexión
		HttpURLConnection connection = setConnection(url);

		// OutputStreamWriter wr = setOutputStreamWriter(connection);

		try {
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				// System.out.println(d.toString());
				// Leemos el contenido de la respuesta y lo mostramos por la
				// salida estándar
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));

				String line = reader.readLine();
				while (line != null) {
					if (line.contains("error code")) {
						registro.error(this.getClass()+"-Error al hacer la peticion: " + line);
					}
					else if (line.contains("track rank")) {
						String[] vars = line.split(" ");
						for (String string : vars) {
							if (string.contains("rank")) {
								String[] vars2 = string.split("\"");
								for (String string2 : vars2) {
									if (!string2.contains("rank")
											&& !string2.contains(">")) {

										// System.out.println("Numero: "+string2);
										line = reader.readLine();
										line = line.trim();
										String name = line.substring(6, line
												.length() - 7);

										line = reader.readLine();
										line = line.trim();
										String duration = line.substring(10,
												line.length() - 11);
										// System.out.println("duration: "+duration);

										LastFMTrackDTO track = new LastFMTrackDTO();

										album = new String(album.getBytes(),
												"UTF-8");
										artist = new String(artist.getBytes(),
												"UTF-8");
										name = new String(name.getBytes(),
												"UTF-8");
										System.out.println("Nombre: " + name);

										track
												.setAlbum(URLEncoder
														.encode(album));
										track.setArtist(URLEncoder
												.encode(artist));
										track.setName(URLEncoder.encode(name));

										if (!duration.equals(""))
											track.setDuration(Integer
													.parseInt(duration));
										track.setId(this.getTrackID(name,elartista));
										tracks.add(track);
									}
								}
							}
						}
					}

					line = reader.readLine();
				}
				reader.close();

			} else {
				registro.warn(connection.getResponseMessage());
			}
		} catch (IOException e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException(
					"Excepcion en la conexion al buscar las canciones del album."
							+ e.getMessage());
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException(
					"Excepcion en la conexion al buscar las canciones del album."
							+ e.getMessage());
		}

		connection.disconnect();

		return tracks;
	}

	public String getArtistBio(String artist) throws LastFMServiceException {
		String artist2 = artist;
		artist2 = artist.replace(" ", "+");

		String bio = "";
		String apiKey = Utils.getStringResource("last.fm.api.key");
		// ---------------------------
		// URL del sercivio Web
		URL url = getUrl("http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&api_key="+apiKey+"&artist="
				+ artist2);

		// Establecemos la conexión
		HttpURLConnection connection = setConnection(url);

		// OutputStreamWriter wr = setOutputStreamWriter(connection);

		try {
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				// System.out.println(d.toString());
				// Leemos el contenido de la respuesta y lo mostramos por la
				// salida estándar
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));

				String line = reader.readLine();
				boolean enc = false;

				while (line != null && !enc) {
					if (line.contains("<content>")) {
						while (!line.contains("</content>")) {
							String bio2 = line;
							bio2 = bio2.trim();
							if (!bio2.equals("")) {
								// System.out.println(bio2);
								bio = bio + " " + bio2;
							}
							line = reader.readLine();
						}
					}

					line = reader.readLine();
				}
				reader.close();

				bio = new String(bio.getBytes(), "UTF-8");

			} else {
				registro.warn(connection.getResponseMessage());
			}
		} catch (IOException e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getArtistBio "
					+ e.getMessage());
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion getArtistBio "
					+ e.getMessage());
		}
		// Cerramos la conexión
		connection.disconnect();
		bio = bio.substring(19, bio.length());
		return bio;
	}

	public String getAlbumContent(String artist, String album)
			throws LastFMServiceException {
		// System.out.println("Procesando album: "+album);
		album = album.trim();
		if (album.startsWith("¿")) {
			album = album.substring(1, album.length());
		}

		String content = "";

		String album2 = Utils.suprimirCaracteresEspeciales(album);
		String artist2 = Utils.suprimirCaracteresEspeciales(artist);
		// System.out.println("autorrr: "+artist2);

		album2 = album2.replace(" ", "+");
		artist2 = artist2.replace(" ", "+");

		LastFMAlbumDTO myalbum = new LastFMAlbumDTO();
		myalbum.setName(album);

		String apiKey = Utils.getStringResource("last.fm.api.key");
		URL url = getUrl("http://ws.audioscrobbler.com/2.0/?method=album.getinfo&api_key="+apiKey+"&artist="
				+ artist2 + "&album=" + album2);

		// Establecemos la conexión
		HttpURLConnection connection = setConnection(url);

		// OutputStreamWriter wr = setOutputStreamWriter(connection);

		try {
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				// System.out.println(d.toString());
				// Leemos el contenido de la respuesta y lo mostramos por la
				// salida estándar
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));
				boolean enc = false;
				String line = reader.readLine();
				while (line != null && !enc) {
					if (line.contains("<content>")) {
						while (!line.contains("</content>")) {
							String bio2 = line;
							bio2 = bio2.trim();
							if (!bio2.equals("")) {
								// System.out.println(bio2);
								content = content + " " + bio2;
							}
							line = reader.readLine();
						}
					}

					line = reader.readLine();
				}
				reader.close();

				content = new String(content.getBytes(), "UTF-8");

			} else {
				registro.warn(connection.getResponseMessage());
			}
		} catch (IOException e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getAlbumContent "
					+ e.getMessage());
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getAlbumContent "
					+ e.getMessage());
		}
		// Cerramos la conexión
		connection.disconnect();
		if (!(content == null) && !content.equals("")) {
			content = content.substring(19, content.length());
		}
		return content;
	}

	public String getArtistSummary(String artist) throws LastFMServiceException {
		String artist2 = artist;
		artist2 = artist.replace(" ", "+");

		String summary = "";
		String apiKey = Utils.getStringResource("last.fm.api.key");
		URL url = getUrl("http://ws.audioscrobbler.com/2.0/?method=artist.getinfo&api_key="+apiKey+"&artist="
				+ artist2);

		// Establecemos la conexión
		HttpURLConnection connection = setConnection(url);

		// OutputStreamWriter wr = setOutputStreamWriter(connection);

		try {
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				// System.out.println(d.toString());
				// Leemos el contenido de la respuesta y lo mostramos por la
				// salida estándar
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(connection.getInputStream()));

				String line = reader.readLine();
				boolean enc = false;
				while (line != null && !enc) {
					if (line.contains("<summary>")) {
						summary = line;
						summary = summary.trim();
						if (!summary.equals("<summary></summary>")) {
							if (summary.equals("<summary>")) {
								summary=reader.readLine();
								String summary2="";
								while(!summary.contains("</summary>")){
									summary2+=summary;
									summary=reader.readLine();									
								}
								summary =summary2;
							}else{
							summary = summary.substring(18,
									summary.length() - 13);
							summary = new String(summary.getBytes(), "UTF-8");
							System.out.println("summary: " + summary);
							}
						} else {
							summary = "No hay información del artista";
						}
						enc = true;
					}

					line = reader.readLine();
				}
				reader.close();

			} else {
				registro.warn(connection.getResponseMessage());
			}
		} catch (IOException e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getArtistSummary "
					+ e.getMessage());
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getArtistSummary "
					+ e.getMessage());
		}
		// Cerramos la conexión
		connection.disconnect();

		if(summary.contains("<![CDATA[")){
			summary=summary.trim();
			summary=summary.substring(9,summary.length());
			summary=summary.substring(0,summary.length()-3);
			summary=summary.trim();
		}
		return summary;
	}

	public Collection<LastFMArtistDTO> getHypedArtists()
			throws LastFMServiceException {

		String apiKey = Utils.getStringResource("last.fm.api.key");
		Collection<LastFMArtistDTO> l = new ArrayList<LastFMArtistDTO>();
		try {

			PaginatedResult<Artist> artists = Chart.getHypedArtists(apiKey);
			Collection<Artist> artistas = artists.getPageResults();
			Iterator<Artist> it = artistas.iterator();
			while (it.hasNext()) {
				Artist artist = it.next();
				LastFMArtistDTO artistDTO = new LastFMArtistDTO();
				artistDTO.setName(artist.getName());
				artistDTO.setId(artist.getId());
				artistDTO.setImageURL(artist.getImageURL(ImageSize.LARGE));
				l.add(artistDTO);
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getHypedArtists "
					+ e.getMessage());
		}

		return l;
	}

	public List<LastFMGTrackDTO> getHypedTracks() throws LastFMServiceException {
		String apiKey = Utils.getStringResource("last.fm.api.key");

		List<LastFMGTrackDTO> l = new ArrayList<LastFMGTrackDTO>();
		try {
			PaginatedResult<Track> artists = Chart.getHypedTracks(apiKey);
			Collection<Track> artistas = artists.getPageResults();
			Iterator<Track> it = artistas.iterator();
			while (it.hasNext()) {
				Track track = it.next();
				LastFMGTrackDTO trackDTO = new LastFMGTrackDTO();
				trackDTO.setName(track.getName());
				trackDTO.setId(track.getId());
				trackDTO.setAlbum(track.getAlbum());
				trackDTO.setArtist(track.getArtist());

				trackDTO.setImageURL(track.getImageURL(ImageSize.LARGE));

				if (track.getImageURL(ImageSize.LARGE) == null
						|| track.getImageURL(ImageSize.LARGE).equals("")) {
					Artist art = Artist.getInfo(track.getArtist(), apiKey);
					trackDTO.setImageURL(art.getImageURL(ImageSize.LARGE));
				}

				l.add(trackDTO);
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getHypedTracks "
					+ e.getMessage());
		}
		return l;
	}

	public Collection<LastFMArtistDTO> getTopArtists()
			throws LastFMServiceException {
		String apiKey = Utils.getStringResource("last.fm.api.key");
		Collection<LastFMArtistDTO> l = new ArrayList<LastFMArtistDTO>();
		try {
			PaginatedResult<Artist> artists = Chart.getTopArtists(apiKey);
			Collection<Artist> artistas = artists.getPageResults();
			Iterator<Artist> it = artistas.iterator();
			while (it.hasNext()) {
				Artist artist = it.next();
				LastFMArtistDTO artistDTO = new LastFMArtistDTO();
				artistDTO.setName(artist.getName());
				artistDTO.setId(artist.getId());
				artistDTO.setImageURL(artist.getImageURL(ImageSize.LARGE));
				l.add(artistDTO);
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getTopArtists "
					+ e.getMessage());
		}
		return l;
	}

	/*@Secured("ROLE_ADMIN")*/
	public List<LastFMGTrackDTO> getLovedTracks() throws LastFMServiceException {
		String apiKey = Utils.getStringResource("last.fm.api.key");
		List<LastFMGTrackDTO> l = new ArrayList<LastFMGTrackDTO>();
		try {
			PaginatedResult<Track> artists = Chart.getLovedTracks(apiKey);
			Collection<Track> artistas = artists.getPageResults();
			Iterator<Track> it = artistas.iterator();
			while (it.hasNext()) {
				Track track = it.next();
				LastFMGTrackDTO trackDTO = new LastFMGTrackDTO();
				trackDTO.setArtist(track.getArtist());
				trackDTO.setName(track.getName());
				trackDTO.setId(track.getId());
				trackDTO.setImageURL(track.getImageURL(ImageSize.LARGE));
				if (track.getImageURL(ImageSize.LARGE) == null
						|| track.getImageURL(ImageSize.LARGE).equals("")) {
					Artist art = Artist.getInfo(track.getArtist(), apiKey);
					trackDTO.setImageURL(art.getImageURL(ImageSize.LARGE));
				}
				l.add(trackDTO);
			}

		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getLovedTracks "
					+ e.getMessage());
		}
		return l;
	}

	@SuppressWarnings("deprecation")
	public TrackListaReproduccionDTO getTrackInfo(String song, String artist)
			throws LastFMServiceException {
		String apiKey = Utils.getStringResource("last.fm.api.key");
		TrackListaReproduccionDTO lastFMTrackDTO = new TrackListaReproduccionDTO();

		try {
			Track mytrack = Track.getInfo(artist, song, apiKey);

			lastFMTrackDTO.setId(mytrack.getId());
			lastFMTrackDTO.setNombre(URLEncoder.encode(mytrack.getName()));
			lastFMTrackDTO.setArtista(URLEncoder.encode(mytrack.getArtist()));
			if (mytrack.getAlbum() != null)
				lastFMTrackDTO.setAlbum(URLEncoder.encode(mytrack.getAlbum()));
			else
				lastFMTrackDTO.setAlbum(mytrack.getName());
			lastFMTrackDTO.setDuracion("" + mytrack.getDuration());
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("error al pillar la cancion: "
					+ e.getMessage());
		}

		return lastFMTrackDTO;
	}

	public List<LastFMAlbumDTO> searchAlbum(String query)
			throws LastFMServiceException {
		List<LastFMAlbumDTO> albums = new ArrayList<LastFMAlbumDTO>();

		try {

			query = query.trim();
			query = Utils.suprimirCaracteresEspeciales(query);
			query = query.replace(" ", "+");

			String apiKey = Utils.getStringResource("last.fm.api.key");
			Collection<Album> results = Album.search(query, apiKey);

			for (Album album : results) {
				LastFMAlbumDTO newAlbum = new LastFMAlbumDTO();
				newAlbum.setId(album.getId());
				newAlbum.setName(album.getName());
				newAlbum.setArtist(album.getArtist());
				newAlbum.setUrlImage(album.getImageURL(ImageSize.EXTRALARGE));
				albums.add(newAlbum);
			}
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en searchAlbum "
					+ e.getMessage());
		}

		return albums;
	}

	public List<LastFMArtistDTO> searchArtist(String query)
			throws LastFMServiceException {

		List<LastFMArtistDTO> artists = new ArrayList<LastFMArtistDTO>();
		try {
			query = query.trim();
			query = Utils.suprimirCaracteresEspeciales(query);
			query = query.replace(" ", "+");

			String apiKey = Utils.getStringResource("last.fm.api.key");

			Collection<Artist> results = Artist.search(query, apiKey);

			for (Artist artist : results) {
				LastFMArtistDTO newArtist = new LastFMArtistDTO();
				newArtist.setId(artist.getId());
				newArtist.setName(artist.getName());

				String urlImage = artist.getImageURL(ImageSize.EXTRALARGE);
				if (urlImage != null)
					newArtist.setImageURL(urlImage);
				artists.add(newArtist);
			}

		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en searchArtist "
					+ e.getMessage());
		}

		return artists;
	}

	// -------------------------------------------------------------------------------------
	private URL getUrl(String laurl) throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		URL url = null;
		try {
			url = new URL(laurl);
		} catch (MalformedURLException e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException(
					"Excepcion al crear la url para buscar las canciones del album."
							+ e.getMessage());
		}
		return url;
	}

	private HttpURLConnection setConnection(URL url)
			throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException(
					"Excepcion en la conexion al buscar las canciones del album."
							+ e.getMessage());
		}

		// Enviamos los datos asociados a la petición
		connection.setDoOutput(true);
		return connection;
	}

	@Override
	public Collection<LastFMArtistDTO> getArtistSimilar(String artist) throws LastFMServiceException {
		registro = Utils.configurarLog(LastFMServiceImpl.class);
		Collection<Artist> artistas = new ArrayList<Artist>();
		Collection<LastFMArtistDTO> losArtistas = new ArrayList<LastFMArtistDTO>();
		
		try {
			String apiKey = Utils.getStringResource("last.fm.api.key");
			artistas = Artist.getSimilar(artist, 20, apiKey);
			for (Artist result : artistas) {
				LastFMArtistDTO artista = new LastFMArtistDTO();
				artista.setId(result.getId());
				artista.setName(URLEncoder.encode(result.getName(), "UTF-8"));
				artista.setImageURL(result.getImageURL(ImageSize.LARGE));
				losArtistas.add(artista);
			}
			
		} catch (Exception e) {
			registro.error(this.getClass()+"-Error: " + e.getMessage());
			throw new LastFMServiceException("Excepcion en getArtist: "
					+ e.getMessage());
		}
		return losArtistas;
	}

	/*
	 * Metodo que no se usa (quizas en el futuro)
	 * 
	 * private OutputStreamWriter setOutputStreamWriter(HttpURLConnection
	 * connection)throws LastFMServiceException{ OutputStreamWriter wr = null;
	 * try { wr = new OutputStreamWriter(connection.getOutputStream()); } catch
	 * (IOException e) { registro.error("error: " + e.getMessage()); throw new
	 * LastFMServiceException(
	 * "Excepcion pillar buscar las canciones del album." + e.getMessage()); }
	 * try { wr.write(URLEncoder.encode("parametro1", "UTF-8") + "=" +
	 * URLEncoder.encode("valor1", "UTF-8")); } catch
	 * (UnsupportedEncodingException e) { registro.error("error: " +
	 * e.getMessage()); throw new LastFMServiceException(
	 * "Excepcion al codificar las canciones del album." + e.getMessage()); }
	 * catch (IOException e) { registro.error("error: " + e.getMessage()); throw
	 * new LastFMServiceException(
	 * "Excepcion en la conexion al buscar las canciones del album." +
	 * e.getMessage()); } try { wr.write("&"); } catch (IOException e) {
	 * registro.error("error: " + e.getMessage()); throw new
	 * LastFMServiceException(
	 * "Excepcion en la conexion al buscar las canciones del album." +
	 * e.getMessage()); } try { wr.write(URLEncoder.encode("parametro2",
	 * "UTF-8") + "=" + URLEncoder.encode("valor2", "UTF-8")); } catch
	 * (UnsupportedEncodingException e) { registro.error("error: " +
	 * e.getMessage()); throw new LastFMServiceException(
	 * "Excepcion en la conexion al buscar las canciones del album." +
	 * e.getMessage()); } catch (IOException e) { registro.error("error: " +
	 * e.getMessage()); throw new LastFMServiceException(
	 * "Excepcion en la conexion al buscar las canciones del album." +
	 * e.getMessage()); } try { wr.flush(); } catch (IOException e) {
	 * registro.error("error: " + e.getMessage()); throw new
	 * LastFMServiceException(
	 * "Excepcion en la conexion al buscar las canciones del album." +
	 * e.getMessage()); } return wr; }
	 */
	// --------------------------------------------------------------------------------------

}
