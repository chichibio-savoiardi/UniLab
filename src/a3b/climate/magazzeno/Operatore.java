package a3b.climate.magazzeno;
import java.time.LocalDateTime;

import a3b.climate.utils.Convertable;
import a3b.climate.utils.DataTable;

/**
 * Rappresenta un operatore
 */

public class Operatore implements Convertable, DataTable {
	private String nome, cognome, email, cf, uid;
	private CentroMonitoraggio centro;

	/**
	 * Costruttore di un'istanza di Operatore
	 * @param cf Codice fiscale dell'operatore
     * @param uid User ID relativo all'operatore
	 * @param nome Nome dell'operatore
	 * @param cognome Cognome dell'operatore
	 * @param email E-mail dell'operatore
	 * @param centro Centro di monitoraggio a cui l'operatore e' associato
	 */

	public Operatore(String cf, String uid, String nome, String cognome, String email, CentroMonitoraggio centro) {
		this.cf = cf;
		this.uid = uid;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.centro = centro;
	}

	/**
	 * Costruttore vuoto di un'istanza di Operatore
	 */

	public Operatore() {
		cf = "CIVILE";
		uid = "civile";
		nome = "Civile";
		cognome = "Civile";
		email = "civile@example.com";
		centro = new CentroMonitoraggio();
	}

	/**
     * Metodo che consente di inserire i dati climatici di una determinata area nel database
	 * @param area Area geografica relativa ai dati
	 * @param dato Aggregato di informazioni (valori dei parametri climatici) relative al dato geografico
	 * @param tempo Data in cui avviene l'inserimento dei dati nel database
	 */

	public boolean inserisciParametri(AreaGeografica area, DatoGeografico dato, LocalDateTime tempo) {
		// TODO
		return false;
	}

	/**
	 * @return Restituisce il codice fiscale dell'operatore che esegue il metodo
	 */

	public String getCf() {
		return cf;
	}

	/**
	 * @return Restituisce il centro di monitoraggio al quale l'operatore che esegue il metodo e' associato
	 */

	public CentroMonitoraggio getCentro() {
		return centro;
	}

	/**
	 * @return Restituisce il cognome dell'operatore che esegue il metodo
	 */

	public String getCognome() {
		return cognome;
	}

	/**
	 * @return Restituisce la e-mail dell'operatore che esegue il metodo
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * @return Restituisce il nome dell'operatore che esegue il metodo
	 */

	public String getNome() {
		return nome;
	}

	/**
	 * @return Restituisce lo user ID relativo all'operatore che esegue il metodo
	 */

	public String getUid() {
		return uid;
	}

	/**
	 * Metodo che imposta il centro di monitoraggio al quale l' operatore che esegue il metodo e' associato
	 * @param centro Centro di monitoraggio al quale l' operatore e' associato
	 */

	public void setCentro(CentroMonitoraggio centro) {
		this.centro = centro;
	}

	/**
	 * Metodo che imposta il codice fiscale dell'operatore che esegue il metodo
	 * @param cf Codice fiscale dell'operatore
	 */

	public void setCf(String cf) {
		this.cf = cf;
	}

	/**
	 * Metodo che imposta il cognome dell'operatore che esegue il metodo
	 * @param cognome Cognome dell'operatore
	 */

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * Metodo che imposta la e-mail dell'operatore che esegue il metodo
	 * @param email E-mail dell'operatore
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo che imposta il nome dell'operatore che esegue il metodo
	 * @param nome Nome dell'operatore
	 */

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Metodo che imposta lo user ID relativo all'operatore che esegue il metodo
	 * @param uid User ID relativo all'operatore
	 */

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		String str = String.format(
			"%s: (\n\tCF: %s\n\tUserID: %s\n\tNome: %s\n\tCognome: %s\n\tEmail: %s\n\tCentro: %s\n)",
			super.toString(), cf, uid, nome, cognome, email, centro.getNome());

		return str;
	}

	@Override
	public String toCsv() {
		String csv = String.format("%s,%s,%s,%s,%s,%s", cf, uid, nome, cognome, email, centro.getNome());
		return csv;
	}

	@Override
	public String toJson() {
		//
		return "";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Operatore)) {
			return super.equals(obj);
		}

		Operatore op = (Operatore) obj;

		if(cf.equals(op.getCf()))
			return true;

		return false;
	}
}
