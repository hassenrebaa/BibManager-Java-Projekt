package BibRebb;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.naming.NamingException;
import javax.swing.JOptionPane;

import javafx.beans.*;

public class Menu extends Exception {

	Scanner scanner = new Scanner(System.in);
	BibManager element = new BibManager();
	File file = new File("test");

	public void add(BibEintrag x) {
		try {
			element.hinzufuegen(x);
		} catch (DoppelterBibEintragException exp) {
			JOptionPane.showMessageDialog(null, "Schon ");
			JOptionPane.showMessageDialog(null, exp.getMessage());
		}
	}

	@SuppressWarnings("null")
	public Menu() throws InvalidEingabException, NumberFormatException, InputMismatchException,
			DoppelterBibEintragException, IOException, ClassNotFoundException {
			    // annonym Klasse 
		element.addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {

				File filll = new File("log.txt");
				String date = new Date().toString();

				try (FileWriter file = new FileWriter(filll, true); PrintWriter pw = new PrintWriter(file)) {

					{
						pw.println("[" + date + "]" + " Eintrag hinzugeft " + arg.toString());

					}

				} catch (Exception exp) {
					exp.printStackTrace();
				}
 
			}

		});
		Scanner x = new Scanner(System.in);
		int Eingabe = 0;

		while (Eingabe < 1 || Eingabe > 11) {

			do {
				try {
					System.out.println("Bibliographie-Manager ");
					System.out.println("1. Buch hinzufgen ");
					System.out.println("2. Artikel hinzufgen ");
					System.out.println("3. Webseite hinzufgen");
					System.out.println("4. Drucke alle Eintrge ");
					System.out.println("5. Suche neusten Eintrag ");
					System.out.println("6. Berechne durchschnittliches Erscheinungsjahr");
					System.out.println("7. CSV-Export ");
					System.out.println("8. speicher ");
					System.out.println("9. Laden ");
					System.out.println("10. Beenden ");
					System.out.println("Bitte Aktion whlen: ");

					String eingabeString = x.nextLine();
					Eingabe = Integer.parseInt(eingabeString);
					if (Eingabe < 1 || Eingabe > 10) {
						throw new Exception();
					}

				} catch (Exception e) {
					System.out.println("FALSCH EINAGBE BITTE ");

				}

				// if((Eingabe !=1)&(Eingabe !=2)&(Eingabe !=3)&(Eingabe !=4)&(Eingabe
				// !=5)&(Eingabe !=6)&(Eingabe !=7))
				// {
				// System.out.println( "Falsch Eingabe bitte Whlen Sie von 1 bis 7 Zahl aus");
				// }

				switch (Eingabe) {
				case 1:
					String vornamee = JOptionPane.showInputDialog("Bitte geben Sie ein Vorname-Autor ein: ");

					String nachnamautorr = JOptionPane.showInputDialog("Bitte geben Sie ein Vorname-Autor ein: ");
					String Titell = "";
					while (Titell.isEmpty()) {
						Titell = (JOptionPane.showInputDialog("Bitte geben Sie ein Titel ein: "));
						if (Titell.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Sie haben Kein Titel eingegeben bitte  ! ");
						}
					}

					int jahrr = 0;
					boolean ok = false;

					while (!ok) {

						String jahrrString = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Jahr ein: ");
						try {
							jahrr = Integer.parseInt(jahrrString);

							ok = true;
						}

						catch (NumberFormatException exp) {
							JOptionPane.showMessageDialog(null, "Sie haben Kein gltig Jahr einge bitte ! ");

						}

					}
					String verlag = (JOptionPane.showInputDialog("Bitte geben Sie eine verlag ein "));
					String isbn = JOptionPane.showInputDialog(null, "Bitte geben Sie ein isbn ein: ");
					Autor aut = new Autor(nachnamautorr, vornamee);
					BibEintrag buch = new Buch(aut, Titell, jahrr, verlag, isbn);

					element.hinzufuegen(buch);

					break;

				case 2:

					String nachnamautor = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Nachname-Autor ein: ");
					String vorname = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Vorname-Autor ein: ");
					int jahr = 0;
					boolean okk = false;

					while (!okk) {
						String jahrrString1 = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Jahr ein: ");
						try {
							jahr = Integer.parseInt(jahrrString1);
							okk = true;
						}

						catch (NumberFormatException exp) {
							JOptionPane.showMessageDialog(null, "Sie haben Kein gltig Jahr einge bitte ! ");

						}

					}

					String zeitschrift = JOptionPane.showInputDialog(null, "Bitte geben Sie ein zeitschrift ein: ");
					String ausgabe = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Ausgabe ein: ");
					// String Titel = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Titel
					// ein: ");

					String Titel = "";
					while (Titel.isEmpty()) {

						Titel = (JOptionPane.showInputDialog("Bitte geben Sie ein Titel ein: "));
						if (Titel.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Sie haben Kein Titel eingegeben bitte  ! ");
						}

					}

					int ausgbei = Integer.parseInt(ausgabe);
					Autor aut1 = new Autor(nachnamautor, vorname);

					Artikel artikel = new Artikel(aut1, Titel, jahr, zeitschrift, ausgbei);

					element.hinzufuegen(artikel);

					break;
				case 3:
					String nachnamautorrr = JOptionPane.showInputDialog(null,
							"Bitte geben Sie ein Nachname-Autor ein: ");
					String vornameee = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Vorname-Autor ein: ");
					// String Titelll = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Titel
					// ein: ");
					String Titelll = "";
					while (Titelll.isEmpty()) {

						Titelll = (JOptionPane.showInputDialog("Bitte geben Sie ein Titel ein: "));
						if (Titelll.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Sie haben Kein Titel eingegeben bitte  ! ");
						}

					}

					int jahrrr = 0;
					boolean okkk = false;
					while (!okkk) {
						String jahrrString11 = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Jahr ein: ");
						try {
							jahrrr = Integer.parseInt(jahrrString11);
							okkk = true;
						} catch (NumberFormatException exp) {
							JOptionPane.showMessageDialog(null, "Sie haben Kein gltig Jahr einge bitte ! ");

						}

					}

					String url = JOptionPane.showInputDialog(null, "Bitte geben Sie ein Url-WEBSEITE ein: ");
					Autor aut2 = new Autor(nachnamautorrr, vornameee);

					Webseite web = new Webseite(aut2, Titelll, jahrrr, url);

					element.hinzufuegen(web);

					break;
				case 4:
					OutputStream out = System.out;
					element.druckeAlleEintraege(out);
					// String mess =
					// JOptionPane.showMessageDialog(null,element.druckeAlleEintraege());
					break;

				case 5:
					element.sucheNeustenEintrag();
					break;
				case 6:
					element.berechneErscheinungsjahr();
					break;
				case 7:
					String datei;
					while (true) {
						datei = JOptionPane.showInputDialog("geben Sie Bitte eine Datei-name ein ");
						if (datei.length() == 0) {
							JOptionPane.showMessageDialog(null, " Sie haben kein Datei-name eingegeben ");
						} else {
							break;
						}
					}
					int antwort = -1;
					File dateifile = new File(datei);
					if (dateifile.exists()) {
						antwort = JOptionPane.showConfirmDialog(null,
								" file existiert schon !! wollen Sie trotz berschreiben ");
					}
					if (antwort == 0) {
						dateifile = new File(datei);
					} else if (antwort == 1) {
						while (true) {
							datei = JOptionPane.showInputDialog("geben sie eine Datei-name ");
							if (datei.length() == 0) {
								JOptionPane.showMessageDialog(null, " Sie haben Kein Detai-name Eigegebne ");
							} else {
								break;
							}
						}

					}
					element.exportiereEintraegeAlsCsv(dateifile);
					break;
				// case 8 und case 9
				case 8:
					element.speichern(file);
					break;
				case 9:
					element = BibManager.laden(file);
					break;
				case 10:
					System.exit(0);

				}

			} while (Eingabe <= 10);

		}
	}

	
}