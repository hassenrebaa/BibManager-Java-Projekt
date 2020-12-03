package GUI;

import java.util.Objects;

import BibRebb.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ArtikelErfassungView extends ErfassungView {

	private Artikel artikel;

	public ArtikelErfassungView(Stage stage, Artikel artikel, BibManager bibManager) {

		super(stage, artikel, bibManager);

		this.artikel = Objects.requireNonNull(artikel);

		this.initOwner(stage);
		this.initModality(Modality.WINDOW_MODAL);
	}

	public void showView() {
		super.showView();

		Label zeitschrift = new Label("zeitschrift:");
		Label ausgabe = new Label("ausgabe:");

		TextField zeitschriftT = new TextField(artikel.getZeitschrift());
		//TextField ausgabeT = new TextField(" " + artikel.getAusgabe());
		TextField ausgabeT ;
		if (artikel.getAusgabe() == 0) {
			ausgabeT = new TextField();
		} else {
			 ausgabeT = new TextField(String.valueOf(artikel.getAusgabe()));
		}

		Button ok = new Button("ok");
		MenuItem ok1 = new MenuItem("ok");

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					bibmanager.hinzufuegen(new Artikel(new Autor(AutorT.getText(), ""), titelT.getText(),
							Integer.parseInt(jahrT.getText()), zeitschriftT.getText(),
							Integer.parseInt(ausgabeT.getText())));
				} catch (NumberFormatException ex) {
					System.out.println("Ungültige Eingabe\n");
					DialogUtil.showMessageDialog("Fehlermeldung", "Ungültige Eingabe");
				} catch (DoppelterBibEintragException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				close();
			}
		});

		Button abbr = new Button("Abbrechen");
		MenuItem abbrItem = new MenuItem("Abbrechen");
		abbr.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				close();
			}
		});
		
		
		super.gp.add(zeitschrift, 0, 3);
		super.gp.add(ausgabe, 0, 4);

		super.gp.add(zeitschriftT, 1, 3);
		super.gp.add(ausgabeT, 1, 4);

		super.gp.setPadding(new Insets(10.0));
		super.gp.setHgrow(zeitschrift, Priority.ALWAYS);
		super.gp.setVgrow(zeitschrift, Priority.NEVER);
		super.gp.setVgap(10.0);
		super.gp.setHgap(20.0);
		super.gp.setAlignment(Pos.CENTER_RIGHT);
		super.gp.setHalignment(zeitschrift, HPos.RIGHT);
		super.gp.setHalignment(ausgabe, HPos.RIGHT);

		super.hb.getChildren().addAll(ok, abbr);
		super.hb.setPadding(new Insets(10.0));
		super.hb.setSpacing(10.0);

		super.hb.setAlignment(Pos.CENTER);

		super.bp.setCenter(super.gp);
		super.bp.setBottom(super.hb);

		setScene(super.getScene());
		this.setTitle("Erfassung eines Artikels ");
		this.show();

	}

}
