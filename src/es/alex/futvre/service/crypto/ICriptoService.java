package es.alex.futvre.service.crypto;

import java.util.Date;


public interface ICriptoService {
	
	public String enconde(String Cancion, String album, String artista) throws CriptoServiceException;
	
	public String decode(String code) throws CriptoServiceException;
	
	public String encodeUserAccountRequest(String username, Date created) throws CriptoServiceException;
	
	public String decodeUserAccountRequest(String code) throws CriptoServiceException;
}
