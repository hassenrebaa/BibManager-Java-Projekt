package BibRebb;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

//equals und Hashcode 

public abstract class BibEintrag implements CsvExportable, Serializable {
	public static int az = 0;

	@Override

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + id;
		result = prime * result + jahr;
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibEintrag other = (BibEintrag) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;

		if (jahr != other.jahr)
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

	public int id = 0;

	private String titel;

	private Object vorname;
	Autor autor;
	private int jahr;

	public BibEintrag() {

	}

	public BibEintrag(Autor autor, String titel, int jahr) {
		this.id = az++;
		this.autor = autor;
		this.titel = titel;
		this.jahr = jahr;

	}

	public int getID() {
		return id;
	}

	public Autor getAutor() {
		return autor;
	}

	public String getTitel() {
		return titel;
	}

	public int getJahr() {
		return jahr;
	}

	public int berechneAlter() {

		int today;
		today = LocalDate.now().getYear();
		return today - jahr;

	}

	public abstract void druckEintrag(OutputStream stream);

	public String toString() {
		return '[' + "ID" + getID() + ']' + getAutor() + ':' + getTitel() + ' ' + '.' + getJahr();

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public void setJahr(int jahr) {
		this.jahr = jahr;
	}

	// EXPORTIERE Als Csv Methode
	public String exportiereAlsCsv() {
		String str = getID() + "," + autor.exportiereAlsCsv() + "," + getTitel() + "," + getJahr();
		return str;

	}
}