package BibRebb;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Observable;

import javax.swing.JOptionPane;

public class BibManager extends Observable {

	ArrayList<BibEintrag> list = new ArrayList<>();
	CsvExportable cc;
	BibEintrag Ei;

	// Iterator<BibEintrag> it =list.iterator();

	HashMap<Autor, Integer> HasBib = new HashMap<Autor, Integer>();

	// exportiereEintragALs Csv Methode ;

	public void exportiereEintraegeAlsCsv(File datei) throws FileNotFoundException

	{
		try (RandomAccessFile f = new RandomAccessFile(datei, "rw")) {
			f.readLine();
			for (BibEintrag Ein : list) {
				f.writeChars(Ein.exportiereAlsCsv() + "\n");

			}

		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	public void exportiereEintraegeAlsCsvRaf(File datei) throws FileNotFoundException

	{
		try (FileWriter file = new FileWriter(datei); PrintWriter pw = new PrintWriter(file)) {

			for (BibEintrag Ein : list) {
				pw.write(Ein.exportiereAlsCsv() + "\n");

			}

		} catch (Exception exp) {
			exp.printStackTrace();
		}

	}

	// laden Funktion mit richtigen Id
	public static BibManager laden(File datei)
			throws FileNotFoundException, IOException, ClassNotFoundException, DoppelterBibEintragException {
		try (FileInputStream fis = new FileInputStream(datei); ObjectInputStream ois = new ObjectInputStream(fis)) {

			BibManager manager = new BibManager();

			@SuppressWarnings("unchecked")
			ArrayList<BibEintrag> list = (ArrayList<BibEintrag>) ois.readObject();
			@SuppressWarnings("unchecked")
			HashMap<Autor, Integer> HasBib = (HashMap<Autor, Integer>) ois.readObject();
			int m = 0;
			for (BibEintrag eintrag : list)

			{
				if (m < eintrag.getID()) {
					m = eintrag.getID();
				}
				manager.hinzufuegen(eintrag);
			}
			BibEintrag.az = m + 1;
			return manager;

		}
	}

	// speicher
	public void speichern(File datei) throws FileNotFoundException, IOException {
		try (FileOutputStream fos = new FileOutputStream(datei); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			/*
			 * for (BibEintrag Ein : list) { oos.writeObject(Ein.exportiereAlsCsv()+"\n");}
			 */

			oos.writeObject(list);
			oos.writeObject(HasBib);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	// Hinzufuegen

	// hinzufuegen anderung
	// DoppelterBibEintragException
	public void hinzufuegen(BibEintrag element) throws DoppelterBibEintragException {
		try {
			if (list.contains(element))

				// JOptionPane.showMessageDialog(null, "Fehler eintrage ist schon enthalten ");
				throw new DoppelterBibEintragException("Fehler eintrage ist schon enthalten");
			list.add(element);
			if (HasBib.containsKey(element.getAutor())) {
				int c = HasBib.get(element.getAutor());
				HasBib.put(element.getAutor(), c + 1);
			} else {
				HasBib.put(element.getAutor(), 1);
			}
			// observable
			setChanged();
			notifyObservers(element);

		} catch (DoppelterBibEintragException exp) {
			JOptionPane.showMessageDialog(null, "Fehler eintrage ist schon enthalten  ");
		}

	}

	// Inneren class AutorColmparator
	public class AutorComparator implements Comparator<BibEintrag> {

		@Override
		public int compare(BibEintrag o1, BibEintrag o2) {
			// TODO Auto-generated method stub
			return o1.getAutor().getNachname().compareTo(o2.getAutor().getNachname());
		}

	}

	// druckeAlleEintraege
	public void druckeAlleEintraege(OutputStream stream) {
		Collections.sort(list, new AutorComparator());
		for (BibEintrag eintrag : list) {
			eintrag.druckEintrag(stream);
			// System.out.println(eintrag.getAutor().getNachname() + ";" +
			// HasBib.get(eintrag.));
		}
		// List<Autor> autorhash = new Array List<>(HasBib.values());
		// Collections.sort(autorhash, new AutorComparator());
		for (Entry<Autor, Integer> eintrag : HasBib.entrySet()) {

			System.out.println(eintrag.getKey() + ";" + eintrag.getValue() + " Eintrge");
		}

	}

	public BibEintrag sucheNeustenEintrag() {

		int min = 0;

		for (int i = 1; i < list.size(); i++)

		{
			if (list.get(i).getJahr() > list.get(min).getJahr()) {
				min = i;

			}
		}
		System.out.print(" Eintrag mit dem jngsten Jahr   " + list.get(min).toString());
		return list.get(min);
	}

	public Iterator<BibEintrag> iterator() {

		return list.iterator();

	}

	public double berechneErscheinungsjahr() {
		double summ = 0;
		for (BibEintrag bib : list) {
			if (bib != null)
				summ += bib.getJahr();
			else
				summ += 0.0;
		}
		System.out.println((summ / list.size()));
		return summ / list.size();

	}

	public void druckeZitierschluessel(Primaerquelle[] element) {

		for (int i = 0; i < element.length; i++) {

			System.out.println(element[i].erzeugeZitierschluessel());

		}
	}

	public int gibAnzahlEintraege(Autor autor) {
		// int summe = 0 ;

		// if(HasBib.equals(autor)==true)
		// {
		// return anz ;
		// }
		// else
		// return 0 ;
		Integer c = HasBib.get(autor);
		if (c == null) {
			return 0;
		} else {
			return c;

		}
	}
}
