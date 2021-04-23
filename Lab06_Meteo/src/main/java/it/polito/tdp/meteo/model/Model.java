package it.polito.tdp.meteo.model;

import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model
{
	MeteoDAO dao = new MeteoDAO();
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	public Model()
	{
		
	}

	public StringBuilder getUmiditaMedia(int mese)
	{
		List<Citta> citta = dao.getAllCitta();
		System.out.println(citta);
		StringBuilder s = new StringBuilder();
		for (Citta c : citta)
		{
			double u = dao.getUmiditaMedia(mese, c);
			c.setUmidita(u);
			s.append(c + ": " + String.format("%.2f", u) + "\n");
		}
		return s;
	}

	public String trovaSequenza(int mese)
	{
		return "TODO!";
	}
}
