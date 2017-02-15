package es.alex.futvre.service.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import es.alex.futvre.utils.Utils;

public class CriptoServiceImpl implements ICriptoService {
	private static org.apache.log4j.Logger registro;

	public String decode(String code) throws CriptoServiceException {

		registro = Utils.configurarLog(CriptoServiceImpl.class);

		sun.misc.BASE64Decoder base64decoder;
		SecretKey key;
		DESKeySpec keySpec;
		String s = "";
		try {
			keySpec = new DESKeySpec("Your secret Key phrase".getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			key = keyFactory.generateSecret(keySpec);
			base64decoder = new BASE64Decoder();

			byte[] encrypedPwdBytes = base64decoder.decodeBuffer(code);
			Cipher cipher2 = Cipher.getInstance("DES");// cipher is not thread
			// safe
			cipher2.init(Cipher.DECRYPT_MODE, key);
			byte[] plainTextPwdBytes = (cipher2.doFinal(encrypedPwdBytes));
			s = new String(plainTextPwdBytes, "UTF8");

		} catch (InvalidKeyException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (BadPaddingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (Exception e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		}

		return s;
	}

	public String enconde(String Cancion, String album, String artista)
			throws CriptoServiceException {

		registro = Utils.configurarLog(CriptoServiceImpl.class);

		sun.misc.BASE64Encoder base64encoder;
		SecretKey key;
		DESKeySpec keySpec;
		String encrypedPwd = "";

		try {
			keySpec = new DESKeySpec("Your secret Key phrase".getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			key = keyFactory.generateSecret(keySpec);
			base64encoder = new BASE64Encoder();

			String plainTextPassword = Cancion + "&" + album + "&" + artista;
			byte[] cleartext = plainTextPassword.getBytes("UTF8");
			Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread
			// safe
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypedPwd = base64encoder.encode(cipher.doFinal(cleartext));

		} catch (InvalidKeyException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (BadPaddingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (Exception e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		}

		return encrypedPwd;

	}

	@Override
	public String encodeUserAccountRequest(String username, Date created) throws CriptoServiceException {
		registro = Utils.configurarLog(CriptoServiceImpl.class);

		sun.misc.BASE64Encoder base64encoder;
		SecretKey key;
		DESKeySpec keySpec;
		String encrypedPwd = "";

		try {
			keySpec = new DESKeySpec("sputifly".getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			key = keyFactory.generateSecret(keySpec);
			base64encoder = new BASE64Encoder();

			int month=created.getMonth()+1;
			int year=created.getYear()+1900;
			String plainTextPassword = username + "&" +  created.getDate()+"/"+month+"/"+year+"-"+created.getHours()+":"+created.getMinutes()+":"+created.getSeconds();
			System.out.println(created.getDate());
			System.out.println(created.toLocaleString());
			System.out.println(created.toString());
			byte[] cleartext = plainTextPassword.getBytes("UTF8");
			Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread
			// safe
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypedPwd = base64encoder.encode(cipher.doFinal(cleartext));

		} catch (InvalidKeyException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (BadPaddingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (Exception e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		}

		return encrypedPwd;
	}
	
	public String decodeUserAccountRequest(String code) throws CriptoServiceException {

		registro = Utils.configurarLog(CriptoServiceImpl.class);

		sun.misc.BASE64Decoder base64decoder;
		SecretKey key;
		DESKeySpec keySpec;
		String s = "";
		try {
			keySpec = new DESKeySpec("sputifly".getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			key = keyFactory.generateSecret(keySpec);
			base64decoder = new BASE64Decoder();

			byte[] encrypedPwdBytes = base64decoder.decodeBuffer(code);
			Cipher cipher2 = Cipher.getInstance("DES");// cipher is not thread
			// safe
			cipher2.init(Cipher.DECRYPT_MODE, key);
			byte[] plainTextPwdBytes = (cipher2.doFinal(encrypedPwdBytes));
			s = new String(plainTextPwdBytes, "UTF8");

		} catch (InvalidKeyException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (BadPaddingException e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		} catch (Exception e) {
			registro.error(this.getClass() + "-Error: " + e.getMessage());
			throw new CriptoServiceException(e.getMessage());
		}

		return s;
	}


}
