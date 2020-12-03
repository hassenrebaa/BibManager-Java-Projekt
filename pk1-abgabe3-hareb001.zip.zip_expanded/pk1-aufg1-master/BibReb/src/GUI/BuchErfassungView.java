package GUI;

import java.awt.Font;
import java.util.Iterator;
import java.util.Objects;

import BibRebb.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BuchErfassungView extends ErfassungView {

	public BuchErfassungView(Stage stage, Buch buch, BibManager bibManager) {

		super(stage, buch, bibManager);
		/*
		 * if (buch == null) throw new NullPointerException();
		 */

		this.buch = Objects.requireNonNull(buch);
		this.initOwner(stage);
		this.initModality(Modality.WINDOW_MODAL);
	}

	private Buch buch;

	public void showView() {
		super.showView();

		Label Verlag = new Label("Verlag:");
		Label Isbn = new Label("Isbn:");

		TextField verlag = new TextField(buch.getVerlag());
		TextField isbn = new TextField(buch.getIsbn());

		Button ok = new Button("ok");
		MenuItem ok1 = new MenuItem("ok");

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					bibmanager.hinzufuegen(new Buch(new Autor(AutorT.getText(), ""), titelT.getText(),
							Integer.parseInt(jahrT.getText()), verlag.getText(), isbn.getText()));
					
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


		super.gp.add(Verlag, 0, 3);
		super.gp.add(Isbn, 0, 4);

		super.gp.add(verlag, 1, 3);
		super.gp.add(isbn, 1, 4);

		super.gp.setPadding(new Insets(10.0));
		super.gp.setHgrow(verlag, Priority.ALWAYS);
		super.gp.setVgrow(verlag, Priority.NEVER);
		super.gp.setVgap(10.0);
		super.gp.setHgap(20.0);
		super.gp.setAlignment(Pos.CENTER_RIGHT);
		super.gp.setHalignment(Verlag, HPos.RIGHT);
		super.gp.setHalignment(Isbn, HPos.RIGHT);

		super.hb.getChildren().addAll(ok, abbr);
		super.hb.setPadding(new Insets(10.0));
		super.hb.setSpacing(10.0);

		super.hb.setAlignment(Pos.CENTER);

		super.bp.setCenter(super.gp);
		super.bp.setBottom(super.hb);

		setScene(super.getScene());
		this.setTitle("Erfassung eines Buches ");
		this.show();

	}

}
