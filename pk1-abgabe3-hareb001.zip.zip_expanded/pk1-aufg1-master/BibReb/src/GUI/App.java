package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import BibRebb.Artikel;
import BibRebb.Autor;
import BibRebb.BibEintrag;
import BibRebb.BibManager;
import BibRebb.Buch;
import BibRebb.DoppelterBibEintragException;
import BibRebb.Webseite;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class App extends Application {
	protected BibManager bibmanager;
	protected BibEintrag eintrag;
	File file = new File("data");

	@Override

	public void start(Stage primaryStage) throws Exception {
		ListView<BibEintrag>bEintrag=new ListView<>();
	

		bibmanager = new BibManager();
		

		MenuBar bar = new MenuBar();
		Menu datei = new Menu("Datei");
		Menu eintrag = new Menu("Eintrag");
		Menu anzeige = new Menu("Anzeige");
// LADEN  itEM
		MenuItem ladenItem = new MenuItem("Laden");
		ladenItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					bibmanager=BibManager.laden(file);
					bEintrag.getItems().clear();
					Iterator<BibEintrag> it = bibmanager.iterator();
					while (it.hasNext()) {
						BibEintrag i = it.next();
						bEintrag.getItems().add(i);
					}
					
					
				} catch (ClassNotFoundException | IOException | DoppelterBibEintragException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		// SPEICHER iTEM
		MenuItem speichernItem = new MenuItem("Speichern");
		speichernItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					bibmanager.speichern(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
// CSV iTEM
		MenuItem csvItem = new MenuItem("CSV-Export");
		csvItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				File file = (new File(DialogUtil.showInputDialog("", "dateiname") + ".csv"));
				if (file.exists()) {
					boolean antwort = DialogUtil.showConfirmDialog("",
							"Mchten Sie die existierende Datei berschreiben?");
					if (antwort == true) {
						file.delete();
					} else
						new File(DialogUtil.showInputDialog("", "dateiname") + ".csv");
				}
				try {
					bibmanager.exportiereEintraegeAlsCsv(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
 // BEENDEN iTEM
		MenuItem beendenItem = new MenuItem("Beenden");
		beendenItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});

		MenuItem buchItem = new MenuItem("Buch hinzufgen");
		buchItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Buch b1 = new Buch();
				BuchErfassungView b = new BuchErfassungView(primaryStage, b1, bibmanager);
				b.showView();

			}
		});

		MenuItem artikelItem = new MenuItem("Artikel hinzufgen");
		artikelItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Artikel a = new Artikel();
				ArtikelErfassungView a1 = new ArtikelErfassungView(primaryStage, a, bibmanager);
				a1.showView();

			}
		});

		MenuItem WebItem = new MenuItem("Webseite hinzufgen");
		WebItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Webseite w = new Webseite();
				WebseiteErfassungView w1 = new WebseiteErfassungView(primaryStage, w, bibmanager);
				w1.showView();

			}
		});

		MenuItem durchschnittItem = new MenuItem("Berechne durchnittliches ErscheinungsJahr");
		durchschnittItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DialogUtil.showMessageDialog("Durchschnittliches Erscheinungsjahr",
						" Dasdurchschnittliche Erscheinungsjahr ist :" + bibmanager.berechneErscheinungsjahr());
			}
		});

		MenuItem sucheItem = new MenuItem("Suche neuen Eintrag");
		sucheItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DialogUtil.showMessageDialog("Neuster Eintrag", "" + bibmanager.sucheNeustenEintrag());
			}
		});

		datei.getItems().addAll(ladenItem, speichernItem, new SeparatorMenuItem(), csvItem, new SeparatorMenuItem(),
				beendenItem);
		eintrag.getItems().addAll(buchItem, artikelItem, WebItem);
		anzeige.getItems().addAll(durchschnittItem, sucheItem);
		bar.getMenus().addAll(datei, eintrag, anzeige);

		BorderPane border = new BorderPane();

	
	
	
		bibmanager.addObserver(new Observer(){
			public void update( Observable o,Object arg)
			
			{
				BibEintrag eintrag1= (BibEintrag) arg ;
				bEintrag.getItems().add(eintrag1);
				
			}
		});
		border.setTop(bar);
		border.setCenter(bEintrag);
		primaryStage.setTitle("Bib-Manager ");
		primaryStage.setScene(new Scene(border,400,400));

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
