/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.meteo;

import java.net.URL;
import java.util.ResourceBundle;

import java.time.*;
import it.polito.tdp.meteo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController
{
	private Model model;
	list<Month> mesii ; 
	private enum Mesi
	{
		GENNAIO,
		FEBBRAIO,
		MARZO,
		APRILE,
		MAGGIO,
		GIUGNO,
		LUGLIO,
		AGOSTO,
		SETTEMBRE,
		OTTOBRE,
		NOVEMBRE,
		DICEMBRE;
	}
	private Mesi mesi;
	
	@FXML private ResourceBundle resources;

	@FXML private URL location;

	@FXML private ChoiceBox<String> boxMese;

	@FXML private Button btnUmidita;

	@FXML private Button btnCalcola;

	@FXML private TextArea txtResult;

	@FXML void doCalcolaUmidita(ActionEvent event)
	{
		
	}

	@FXML void doCalcolaSequenza(ActionEvent event)
	{
		
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize()
	{
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
	}

	public void setModel(Model model)
	{
		this.model = model;
		
		for (int i=0; i<12; i++)
			boxMese.getItems().add(mesi.values()[i].toString());
	}
}
