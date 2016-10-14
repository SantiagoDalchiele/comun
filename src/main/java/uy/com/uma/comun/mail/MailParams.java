package uy.com.uma.comun.mail;

import java.util.ArrayList;
import java.util.Collection;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Clase liviana con los datos que se pasan como parámetros en el envio de un correo
 * 
 * @author Santiago Dalchiele
 */
public class MailParams {

	private String hostCorreo;
	private int puertoCorreo = 25;	
	private String usuarioCorreo;
	private String claveCorreo;
	private int timeout = 120;
	private String from;
	private Collection<Address> to = new ArrayList<Address>();	
	private Collection<Address> replyTo = new ArrayList<Address>();
	private Collection<Address> cc = new ArrayList<Address>();
	private Collection<Address> bcc = new ArrayList<Address>();
	private String asunto;
	private String texto;
	private String contenidoHTML;
	private Collection<String> adjuntos = new ArrayList<String>();
	
	
	
	public MailParams() {		
	}
	
	public MailParams (String host, String usuario, String clave, String from, String to, String asunto, String texto) throws AddressException {
		setHostCorreo(host);
		setUsuarioCorreo(usuario);
		setClaveCorreo(clave);
		setFrom(from);
		addTo(to);
		setAsunto(asunto);
		setTexto(texto);
	}
	
	
	
	public String getHostCorreo() {
		return hostCorreo;
	}
	public void setHostCorreo(String hostCorreo) {
		this.hostCorreo = hostCorreo;
	}
	public int getPuertoCorreo() {
		return puertoCorreo;
	}
	public void setPuertoCorreo(int puertoCorreo) {
		this.puertoCorreo = puertoCorreo;
	}
	public String getUsuarioCorreo() {
		return usuarioCorreo;
	}
	public void setUsuarioCorreo(String usuarioCorreo) {
		this.usuarioCorreo = usuarioCorreo;
	}
	public String getClaveCorreo() {
		return claveCorreo;
	}
	public void setClaveCorreo(String claveCorreo) {
		this.claveCorreo = claveCorreo;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public Address getFrom() throws AddressException {
		return new InternetAddress(from);
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Address[] getTo() {
		return to.toArray(new Address[to.size()]);
	}
	public void addTo (String to) throws AddressException {
		this.to.add(new InternetAddress(to));
	}
	public void setTo (String to) throws AddressException {
		this.to.clear();
		this.to.add(new InternetAddress(to));
	}
	public Address[] getReplyTo() {
		return replyTo.toArray(new Address[replyTo.size()]);
	}
	public void addReplyTo (String replyTo) throws AddressException {
		this.replyTo.add(new InternetAddress(replyTo));
	}
	public Address[] getCc() {
		return cc.toArray(new Address[cc.size()]);
	}
	public void addCc (String cc) throws AddressException {
		this.cc.add(new InternetAddress(cc));
	}
	public Address[] getBcc() {
		return bcc.toArray(new Address[bcc.size()]);
	}
	public void addBcc (String bcc) throws AddressException {
		this.bcc.add(new InternetAddress(bcc));
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getContenidoHTML() {
		return contenidoHTML;
	}
	public void setContenidoHTML(String contenidoHTML) {
		this.contenidoHTML = contenidoHTML;
	}
	public Collection<String> getAdjuntos() {
		return adjuntos;
	}
	
	
	
	/**
	 * Borra todos los recipientes a los que envia
	 */
	public void clearAllRecipients() {
		this.to.clear();
		this.replyTo.clear();
		this.cc.clear();
		this.bcc.clear();
	}
}
