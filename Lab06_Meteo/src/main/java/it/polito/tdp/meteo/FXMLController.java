/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.meteo;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.time.*;

import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class FXMLController
{
	private Model model; 
	
	@FXML private ResourceBundle resources;

	@FXML private URL location;

	@FXML private ChoiceBox<Month> boxMese;

	@FXML private Button btnUmidita;

	@FXML private Button btnCalcola;

	@FXML private TextArea txtResult;

	@FXML void doCalcolaUmidita(ActionEvent event)
	{
		txtResult.setText(model.getUmiditaMedia(boxMese.getValue().getValue()).toString());
	}

	@FXML void doCalcolaSequenza(ActionEvent event)
	{
		txtResult.setText(model.trovaSequenza(boxMese.getValue().getValue()).toString());
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
			boxMese.getItems().add(Month.values()[i]);
	}
}
