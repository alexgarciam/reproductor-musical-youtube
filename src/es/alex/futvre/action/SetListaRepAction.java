package es.alex.futvre.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;

import es.alex.futvre.DTO.ListaReproduccionDTO;
import es.alex.futvre.DTO.TrackListaReproduccionDTO;
import es.alex.futvre.utils.Utils;

public class SetListaRepAction extends DispatchAction {
	private static org.apache.log4j.Logger registro;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		registro = Utils.configurarLog(SetListaRepAction.class);
		ListaReproduccionDTO milista = new ListaReproduccionDTO();
		String jsonOutput = null;
		try {

			String modo = request.getParameter("modo");
			String id = request.getParameter("id");
			String nombre = (request.getParameter("nombre"));
			String artista = (request.getParameter("artista"));
			String album = (request.getParameter("album"));
			String duracion = request.getParameter("duracion");
			String idVideo = request.getParameter("idYoutube");
			
			if(nombre!=null)
				nombre=URLDecoder.decode(nombre);
			if(album!=null)
				album=URLDecoder.decode(album);

			TrackListaReproduccionDTO tema = new TrackListaReproduccionDTO();
			tema.setAlbum(album);
			tema.setArtista(artista);
			if (duracion != null) {
				tema.setDuracion(duracion);
			}

			if (nombre != null) {
				tema.setAlbum(URLEncoder.encode(album));
			}
			if (artista != null) {
				tema.setArtista(URLEncoder.encode(artista));
			}
			if (album != null) {
				tema.setAlbum(URLEncoder.encode(album));
			}if (idVideo != null && !idVideo.equals("undefined")) {
				tema.setIdYoutube(idVideo);
			}
			else{
				tema.setIdYoutube("");
			}

			if (nombre != null && !nombre.equals("")) {
				tema.setNombre(URLEncoder.encode(nombre));
			}
			else{
				tema.setNombre("");
			}
			
			tema.setId(id);

			Collection<TrackListaReproduccionDTO> lista = new ArrayList<TrackListaReproduccionDTO>();

			Gson gson = new Gson();

			if (modo.equals("new")) {
				tema.setReproduciendo("true");
				lista.add(tema);
				milista.setNombre(artista + " - " + album);
				milista.setTemas(lista);
				jsonOutput = gson.toJson(milista);
			}
			if (modo.equals("add")) {
				ListaReproduccionDTO listaRepro = (ListaReproduccionDTO) request
						.getSession().getAttribute("listaRepDTO");
				if (listaRepro != null) {
					listaRepro.getTemas().add(tema);
					milista = listaRepro;

				} else {
					tema.setReproduciendo("true");
					lista.add(tema);
					milista.setNombre(artista + " - " + album);
					milista.setTemas(lista);

				}
			}
			if (modo.equals("quit")) {
				ListaReproduccionDTO listaRepro = (ListaReproduccionDTO) request
						.getSession().getAttribute("listaRepDTO");
				if (listaRepro != null) {
					lista = listaRepro.getTemas();
					Iterator it = lista.iterator();

					int contador = 0;

					// indica que hay que poner a reproducir la siguiente
					boolean hayQueCambiar = false;

					while (it.hasNext()) {
						contador++;
						TrackListaReproduccionDTO temita = (TrackListaReproduccionDTO) it
								.next();
						if (temita.getNombre().equals(tema.getNombre())
								&& temita.getArtista()
										.equals(tema.getArtista())
								&& temita.getAlbum().equals(tema.getAlbum())) {
							if (temita.getReproduciendo() != null
									&& temita.getReproduciendo().equals("true")) {
								if (it.hasNext()) {
									hayQueCambiar = true;
								}
							}
							it.remove();
							if (hayQueCambiar) {
								TrackListaReproduccionDTO nuevotemita = (TrackListaReproduccionDTO) it
										.next();
								nuevotemita.setReproduciendo("true");
							}
						}
					}
					milista = listaRepro;
				}
			}

			if (modo.equals("next")) {
				ListaReproduccionDTO listaRepro = (ListaReproduccionDTO) request
						.getSession().getAttribute("listaRepDTO");
				if (listaRepro != null) {
					lista = listaRepro.getTemas();
					Iterator it = lista.iterator();

					int contador = 0;

					// indica que hay que poner a reproducir la siguiente
					boolean hayQueCambiar = false;

					while (it.hasNext()) {
						contador++;
						TrackListaReproduccionDTO temita = (TrackListaReproduccionDTO) it
								.next();
						if (temita.getReproduciendo() != null
								&& temita.getReproduciendo().equals("true")) {
							if (temita.getReproduciendo() != null
									&& temita.getReproduciendo().equals("true")) {
								temita.setReproduciendo("false");
								if (it.hasNext()) {
									hayQueCambiar = true;
								}
							}
							if (hayQueCambiar) {
								TrackListaReproduccionDTO nuevotemita = (TrackListaReproduccionDTO) it
										.next();
								nuevotemita.setReproduciendo("true");
							}
						}
					}
					milista = listaRepro;
				}
			}

			if (modo.equals("previous")) {
				ListaReproduccionDTO listaRepro = (ListaReproduccionDTO) request
						.getSession().getAttribute("listaRepDTO");
				if (listaRepro != null) {
					lista = listaRepro.getTemas();
					Iterator it = lista.iterator();
					
					int contador = 0;
					// indica que hay que poner a reproducir la siguiente
					TrackListaReproduccionDTO previous = null;
					while (it.hasNext()) {
						contador++;
						TrackListaReproduccionDTO temita = (TrackListaReproduccionDTO) it
								.next();
						if (temita.getReproduciendo() == null
								|| temita.getReproduciendo().equals("false")) {
							previous = temita;
						}
						if (temita.getReproduciendo() != null
								&& temita.getReproduciendo().equals("true")) {
							previous.setReproduciendo("true");
							temita.setReproduciendo("false");
						}
					}
					milista = listaRepro;
				}
			}

			// se lanza cuando se elige una canción de la lista
			if (modo.equals("choose")) {
				ListaReproduccionDTO listaRepro = (ListaReproduccionDTO) request
						.getSession().getAttribute("listaRepDTO");
				if (listaRepro != null) {
					lista = listaRepro.getTemas();
					Iterator it = lista.iterator();

					int contador = 0;

					while (it.hasNext()) {
						contador++;
						TrackListaReproduccionDTO temita = (TrackListaReproduccionDTO) it
								.next();

						if (temita.getReproduciendo() != null
								&& temita.getReproduciendo().equals("true")) {
							temita.setReproduciendo("false");
						}

						if (URLDecoder.decode(temita.getNombre())
								.equals(nombre)
								&& URLDecoder.decode(temita.getAlbum()).equals(
										album)) {
							temita.setReproduciendo("true");
						}
					}
					milista = listaRepro;
				}
			}

			if (modo.equals("random")) {
				ListaReproduccionDTO listaRepro = (ListaReproduccionDTO) request
						.getSession().getAttribute("listaRepDTO");

				// genera un numero aleatorio entre 1 num canciones+1 (sin
				// contar mum canciones+1)
				int random = Utils.getNumeroAleatorioEntre1YParam(listaRepro
						.getTemas().size());

				// ----------------------
				if (listaRepro != null) {
					lista = listaRepro.getTemas();
					Iterator it = lista.iterator();
					
					Iterator it2 = lista.iterator();
					
					while (it2.hasNext()) {
						TrackListaReproduccionDTO temita = (TrackListaReproduccionDTO) it2
								.next();
							temita.setReproduciendo("false");						
					}
					
					int contador = 0;
					// indica que hay que poner a reproducir la siguiente					
					while (it.hasNext()) {
						contador++;
						TrackListaReproduccionDTO temita = (TrackListaReproduccionDTO) it
								.next();

						if (contador == random) {
							TrackListaReproduccionDTO nuevotemita = (TrackListaReproduccionDTO) it
									.next();
							nuevotemita.setReproduciendo("true");
						}
					}
					milista = listaRepro;
				}
				// ----------------------
			}

			jsonOutput = gson.toJson(milista);
			System.out.println(milista);

		} catch (Exception e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
		}
		request.setAttribute("listaRep", jsonOutput);
		request.getSession().setAttribute("listaRep", jsonOutput);
		request.getSession().setAttribute("listaRepDTO", milista);

		return mapping.findForward("success");
	}
}
