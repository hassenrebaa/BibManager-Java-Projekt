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

public class WebseiteErfassungView extends ErfassungView {

	private Webseite webseite;

	public WebseiteErfassungView(Stage stage, Webseite webseite, BibManager bibManager) {
		super(stage, webseite, bibManager);

		this.webseite = Objects.requireNonNull(webseite);

		this.initOwner(stage);
		this.initModality(Modality.WINDOW_MODAL);
	}

	public void showView() {
		super.showView();

		Label url = new Label("url:");

		TextField urlT = new TextField(webseite.getUrl());

		Button ok = new Button("ok");
		MenuItem ok1 = new MenuItem("ok");

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					bibmanager.hinzufuegen(new Webseite(new Autor(AutorT.getText(), ""), titelT.getText(),
							Integer.parseInt(jahrT.getText()), urlT.getText()));
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

		super.gp.add(url, 0, 3);

		super.gp.add(urlT, 1, 3);

		super.gp.setPadding(new Insets(10.0));
		super.gp.setHgrow(url, Priority.ALWAYS);
		super.gp.setVgrow(url, Priority.NEVER);
		super.gp.setVgap(10.0);
		super.gp.setHgap(20.0);
		super.gp.setAlignment(Pos.CENTER_RIGHT);
		super.gp.setHalignment(urlT, HPos.RIGHT);

		// HBox hb = new HBox();

		super.hb.getChildren().addAll(ok, abbr);
		super.hb.setPadding(new Insets(10.0));
		super.hb.setSpacing(10.0);

		super.hb.setAlignment(Pos.CENTER);

		// BorderPane bp = new BorderPane();
		super.bp.setCenter(super.gp);
		super.bp.setBottom(super.hb);

		// Scene scene = new Scene(bp);
		setScene(super.getScene());
		this.setTitle("Erfassung einer WebSeite ");
		this.show();
	}

}
