/*
 * Interdisciplinary Workshop A
 * Climate Monitoring
 * A.A. 2022-2023
 *
 * Authors:
 * - Iuri Antico, 753144
 * - Beatrice Balzarini, 752257
 * - Michael Bernasconi, 752259
 * - Gabriele Borgia, 753262
 *
 * Some rights reserved.
 * See LICENSE file for additional information.
 */
package a3b.climate.magazzeno;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import a3b.climate.utils.CercaAree;
import a3b.climate.utils.MediaAree;
import a3b.climate.utils.TipoDatoGeografico;
import a3b.climate.utils.result.Result;

/**
 * La classe {@code Filtratore} rappresenta uno strumento per filtra una lista
 * di istanze di {@link Misurazione}.
 * <p>
 * Fornisce vari metodi per filtrare la lista in base a diversi criteri.
 * <p>
 * Implementa l'interfaccia {@link Iterable} per consentire l'iterazione delle
 * istanze di {@link Misurazione}, mentre le interfacce
 * {@link CercaAree} e {@link MediaAree} per consentire le operazioni che
 * coinvolgono istanze di {@link AreaGeografica}.
 */

public class Filtratore implements Iterable<Misurazione>, CercaAree, MediaAree {
	private List<Misurazione> lm;

	/**
	 * Costruttore di un'istanza di {@code Filtratore}.
	 *
	 * @param lm {@link List} di istanze di {@link Misurazione} da filtrare
	 */
	public Filtratore(List<Misurazione> lm) {
		this.lm = lm;
	}

	/**
	 * Recupera la lista di misurazioni dal filtratore.
	 *
	 * @return {@link List} di istanze di {@link Misurazione} dal filtratore
	 */
	public List<Misurazione> getList() {
		return lm;
	}

	/**
	 * Filtra le misurazioni in base gli operatori.
	 *
	 * @param ops array di istanze di {@link Operatore} usate per filtrare
	 * @return nuovo {@code Filtratore} contenente la lista filtrata
	 */
	public Filtratore filtraOperatore(Operatore... ops) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (Operatore op : ops) {
				if (mis.getOperatore().equals(op)) {
					nlm.add(mis);
					break;
				}
			}
		}

		return new Filtratore(nlm);
	}

	/**
	 * Filtra le misurazioni in base ai centri di monitoraggio.
	 *
	 * @param ops array di istanze di {@link CentroMonitoaggio} usate per filtrare
	 * @return nuovo {@code Filtratore} contenente la lista filtrata
	 */

	public Filtratore filtraCentro(CentroMonitoraggio... cms) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (CentroMonitoraggio cm : cms) {
				if (mis.getCentro().equals(cm)) {
					nlm.add(mis);
					break;
				}
			}

		}
		return new Filtratore(nlm);
	}

	/**
	 * Filtra le misurazioni in base alle aree geografiche.
	 *
	 * @param ops array di istanze di {@link AreaGeografica} usate per filtrare
	 * @return nuovo {@code Filtratore} contenente la lista filtrata
	 */

	public Filtratore filtraAree(AreaGeografica... ags) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (AreaGeografica ag : ags) {
				if (mis.getArea().equals(ag)) {
					nlm.add(mis);
					break;
				}
			}

		}
		return new Filtratore(nlm);
	}

	/**
	 * Filtra le misurazioni in base alle note.
	 *
	 * @param ops array di note usate per filtrare
	 * @return nuovo {@code Filtratore} contenente la lista filtrata
	 */
	public Filtratore filtraNote(String... note) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (String nota : note) {
				for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
					if (mis.getDato().getNota(tipo).equals(nota)) {
						nlm.add(mis);
						break;
					}
				}
			}

		}
		return new Filtratore(nlm);
	}

	/**
	 * Filtra le misurazioni in base ai dati geografici.
	 *
	 * @param ops array di istanze di {@link Datogeografico} usate per filtrare
	 * @return nuovo {@code Filtratore} contenente la lista filtrata
	 */
	public Filtratore filtraDato(DatoGeografico... dati) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione mis : lm) {
			for (DatoGeografico dat : dati) {
				if (mis.getDato().equals(dat)) {
					nlm.add(mis);
					break;
				}
			}
		}
		return new Filtratore(nlm);
	}

	/**
	 * Filtra le misurazioni in base a delle stringhe (di ricerca).
	 *
	 * @param ops array di stringhe usate per filtrare
	 * @return nuovo {@code Filtratore} contenente la lista filtrata
	 */
	public Filtratore filtraStrings(String... strs) {
		List<Misurazione> nlm = new LinkedList<>();
		for (Misurazione m : lm) {
			for (String s : strs) {
				if (m.toString().contains(s)) {
					nlm.add(m);
					break;
				}
			}
		}

		return new Filtratore(nlm);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format("%s <<<\n", super.toString()));

		for (int i = 0; i < lm.size(); i++) {
			sb.append(String.format("[%d] %s\n", i, lm.get(i).toString()));
		}

		return sb.append(">>> ").append(super.toString()).toString();
	}

	@Override
	public DatoGeografico visualizzaAreaGeografica(AreaGeografica area) {
		Filtratore fil = filtraAree(area);
		/*
		 * Il valore della HashMap rappresenta quante volte un valore appare
		 * gli indici dell'array vanno da 0 a 5, come i valori possibili nel dato.
		 * Quindi la HashMap associa un tipo di dato al suo array delle ricorrenze,
		 * l'indice i rappresenta il valore del dato, il valore arr[i] rappresenta la
		 * sua ricorrenza.
		 */
		HashMap<TipoDatoGeografico, int[]> moda = new HashMap<>();

		// gli array vanno inizializzati individualmente
		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			moda.put(tipo, new int[6]);
		}

		// controllo per ogni misurazione il suo dato
		for (Misurazione mis : fil) {
			for (TipoDatoGeografico tipo : moda.keySet()) {
				int[] arr = moda.get(tipo); // prendi l'array delle ricorrenze del tipo di dato corrente
				arr[mis.getDato().getDato(tipo)]++; // aumenta di uno la conta delle volte che un valore appare
				moda.put(tipo, arr); // aggiorna il valore
			}
		}

		// Ricostruisco il dato finale
		HashMap<TipoDatoGeografico, Byte> dato = new HashMap<>();
		for (TipoDatoGeografico tipo : TipoDatoGeografico.values()) {
			// questi valori devono persistere tra i cicli del for seguente
			int hi = 0;
			int[] arr = moda.get(tipo);
			byte modaDato = 0;
			for (int i = 0; i < arr.length; i++) {
				// se la ricorrenza e' piu' alta aggiorna i valori
				if (arr[i] > hi) {
					hi = arr[i];
					modaDato = (byte) i;
				}
			}

			dato.put(tipo, modaDato);
		}

		return new DatoGeografico(0, dato, null);
	}

	@Override
	public ListaAree cercaAreaGeografica(String denominazione, String stato) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cercaAreaGeografica'");
	}

	@Override
	public Result<AreaGeografica> cercaAreeGeografiche(double latitudine, double longitudine) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'cercaAreeGeografiche'");
	}

	@Override
	public Iterator<Misurazione> iterator() {
		return lm.iterator();
	}
}
