package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.util.Objects;

import BibRebb.*;

//  class ErfassungView 
public abstract class ErfassungView extends Stage {
	protected BibManager bibmanager;
	private BibEintrag eintrag;
	BorderPane bp = new BorderPane();
	HBox hb = new HBox();
	GridPane gp = new GridPane();
	protected TextField titelT;
	protected TextField AutorT;
	protected TextField jahrT;

	public ErfassungView(Stage stage, BibEintrag eintrag ,BibManager bibManager  ) {
		this.bibmanager=bibManager;
		// this.eintrag = Objects.requireNonNull(eintrag);
		this.eintrag = eintrag;
		this.initOwner(stage);
		this.initModality(Modality.WINDOW_MODAL);
		this.titelT = new TextField(eintrag.getTitel());
		if (eintrag.getAutor() == null) {
			this.AutorT = new TextField();
		} else {
			this.AutorT = new TextField(eintrag.getAutor().toString());
		}
		if (eintrag.getJahr() == 0) {
			this.jahrT = new TextField();
		} else {
			this.jahrT = new TextField(String.valueOf(eintrag.getJahr()));
		}

	} 

	public void showView() {
		Label titel = new Label(" Titel ");
		Label Autor = new Label(" Autor ");
		Label jahr = new Label(" Jahr ");

		gp.add(titel, 0, 0);
		gp.add(Autor, 0, 1);
		gp.add(jahr, 0, 2);
		gp.add(titelT, 1, 0);
		gp.add(AutorT, 1, 1);
		gp.add(jahrT, 1, 2);
		gp.setPadding(new Insets(10.0));
		gp.setHgrow(titelT, Priority.ALWAYS);
		gp.setVgrow(titelT, Priority.NEVER);
		gp.setVgap(10.0);
		gp.setHgap(20.0);
		gp.setAlignment(Pos.CENTER_RIGHT);
		gp.setHalignment(titel, HPos.RIGHT);
		gp.setHalignment(Autor, HPos.RIGHT);
		gp.setHalignment(jahr, HPos.RIGHT);
		hb.setPadding(new Insets(10.0));
		hb.setSpacing(10.0);
		hb.setAlignment(Pos.CENTER);
		bp.setCenter(gp);
		bp.setBottom(hb);

		Scene scene = new Scene(bp, 370, 270);
		setScene(scene);

		this.show();

	}

}
