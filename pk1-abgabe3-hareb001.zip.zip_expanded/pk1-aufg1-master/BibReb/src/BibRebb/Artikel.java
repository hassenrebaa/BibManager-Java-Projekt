package BibRebb;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Artikel extends BibEintrag implements Primaerquelle, CsvExportable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ausgabe;
		result = prime * result + ((zeitschrift == null) ? 0 : zeitschrift.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artikel other = (Artikel) obj;
		if (ausgabe != other.ausgabe)
			return false;
		if (zeitschrift == null) {
			if (other.zeitschrift != null)
				return false;
		} else if (!zeitschrift.equals(other.zeitschrift))
			return false;
		return true;
	}

	private String zeitschrift;
	private int ausgabe;

	public Artikel() {

	}

	public Artikel(Autor autor, String titel, int jahr, String zeitschrift, int ausgabe) {
		super(autor, titel, jahr);
		this.zeitschrift = zeitschrift;
		this.ausgabe = ausgabe;
	}

	public String getZeitschrift() {
		return zeitschrift;
	}

	public int getAusgabe() {
		return ausgabe;
	}

	public void druckEintrag(OutputStream stream) {
		/*
		 * String str=(super.toString()+' '+getZeitschrift()+' '+getAusgabe()); try {
		 * stream.write(str.getBytes()); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		PrintStream ps = new PrintStream(stream);
		// ps.println(super.toString() + ' ' + getZeitschrift() + ' ' + getAusgabe());
		ps.println(this.toString());

	}

	public String toString() {
		return super.toString() + "Zeitschrift " + getZeitschrift() + " ausgabe :" + getAusgabe();
	}

	public void setZeitschrift(String zeitschrift) {
		this.zeitschrift = zeitschrift;
	}

	public void setAusgabe(int ausgabe) {
		this.ausgabe = ausgabe;
	}

	public String erzeugeZitierschluessel() {

		String str = super.getAutor() + super.getTitel() + getAusgabe();
		return "\n" + str.replaceAll("\\s+", "");

	}

	public String exportiereAlsCsv() {
		String str = super.getId() + "," + super.autor.exportiereAlsCsv() + "," + getTitel() + "," + getJahr() + ","
				+ getZeitschrift() + "," + getAusgabe();
		return str;

	}
}