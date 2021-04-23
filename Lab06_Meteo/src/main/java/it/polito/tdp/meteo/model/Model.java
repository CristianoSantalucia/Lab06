package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model
{
	MeteoDAO dao;

	private List<Citta> leCitta;
	private List<Citta> best;

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	public Model()
	{
		dao = new MeteoDAO();
		leCitta = dao.getAllCitta();
	}

//				1 		2		3		4		5		6		7		8		9 		10		11
//	PARZIALE : torino	torino	torino	milano  milano 	milano milano 	genova ... 		... 	...
//	LIVELLO : n giorni trascorsi <= 15 -> COMPLETO = 15
//	CONDIZIONI : >=3 giorni per citta;  <= 6 giorni per citta
//	
//	
	public StringBuilder getUmiditaMedia(int mese)
	{
		List<Citta> citta = dao.getAllCitta();

		StringBuilder s = new StringBuilder();
		for (Citta c : citta)
			s.append(c + ": " + String.format("%.2f", dao.getUmiditaMedia(mese, c)) + "\n");
		return s;
	}

	public List<Citta> trovaSequenza(int mese)
	{
		List<Citta> parziale = new ArrayList<>();
		this.best = null;

		MeteoDAO dao = new MeteoDAO(); 
		
		for (Citta c : leCitta)
			c.setRilevamenti(dao.getAllRilevamentiLocalitaMese(mese, c));

		cerca(parziale, 0);
		return best;
	}

	private void cerca(List<Citta> parziale, int livello)
	{

		if (livello == NUMERO_GIORNI_TOTALI)
		{
			// caso terminale
			Double costo = calcolaCosto(parziale);
			if (best == null || costo < calcolaCosto(best))
				best = new ArrayList<>(parziale);
		}
		else
		{
			for (Citta nuova : leCitta)
			{
				if (aggiuntaValida(nuova, parziale))
				{
					parziale.add(nuova);
					cerca(parziale, livello + 1);
					parziale.remove(parziale.size() - 1);
				}
			}
		}
	}
 
	private boolean aggiuntaValida(Citta nuova, List<Citta> parziale)
	{
		int cnt = 0;
		for (Citta prec : parziale)
			if (prec.equals(nuova)) cnt++;
		if (cnt >= NUMERO_GIORNI_CITTA_MAX) return false;
		// da qui so che ci sono meno di MAX giorni
		if (parziale.size() == 0) return true;
		if (parziale.size() == 1 || parziale.size() == 2) return parziale.get(parziale.size() - 1).equals(nuova); //almeno 3 giorni
		if (parziale.get(parziale.size() - 1).equals(nuova)) return true;
		if (parziale.get(parziale.size() - 1).equals(parziale.get(parziale.size() - 2))
				&& parziale.get(parziale.size() - 2).equals(parziale.get(parziale.size() - 3)))
			return true;

		return false;
	}

	private Double calcolaCosto(List<Citta> parziale)
	{
		double costo = 0.0;
		for (int giorno = 1; giorno <= NUMERO_GIORNI_TOTALI; giorno++)
		{
			// dove mi trovo
			Citta c = parziale.get(giorno - 1);

			double umid = c.getRilevamenti().get(giorno - 1).getUmidita();
			costo += umid;
		}
		for (int giorno = 2; giorno <= NUMERO_GIORNI_TOTALI; giorno++)
		{
			// dove mi trovo
			if (!parziale.get(giorno - 1).equals(parziale.get(giorno - 2)))
			{
				costo += COST;
			}
		}
		return costo;
	}
}