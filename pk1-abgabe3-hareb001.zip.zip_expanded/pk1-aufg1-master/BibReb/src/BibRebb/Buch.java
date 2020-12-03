package BibRebb;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class Buch extends BibEintrag implements Primaerquelle, CsvExportable, Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((verlag == null) ? 0 : verlag.hashCode());
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
		Buch other = (Buch) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (verlag == null) {
			if (other.verlag != null)
				return false;
		} else if (!verlag.equals(other.verlag))
			return false;
		return true;
	}

	private String verlag;
	private String isbn;
// konstruktur
	public Buch() {
	}

	public Buch(Autor autor, String titel, int jahr, String verlag, String isbn) {
		super(autor, titel, jahr);
		this.verlag = verlag;
		this.isbn = isbn;
	}

	public void druckEintrag(OutputStream stream) {
		/*
		 * //System.out .println(super.toString()+getVerlag()+getIsbn()); String
		 * str=super.toString()+getVerlag()+getIsbn(); try {
		 * stream.write(str.getBytes()); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		PrintStream ps = new PrintStream(stream);
		// ps.println(super.toString()+' '+getVerlag()+' '+getIsbn());
		ps.println(this.toString());
	}

	public String getVerlag() {
		return verlag;
	}

	public String getIsbn() {
		return isbn;
	}

	public String toString() {
		return super.toString() + " verlag" + getVerlag() + "Isbn " + getIsbn();
	}

	public void setVerlag(String verlag) {
		this.verlag = verlag;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	

	public String erzeugeZitierschluessel() {
		String str = super.getAutor() + super.getTitel();
		return "\n" + str.replaceAll("\\s+", "");
	}

	public String exportiereAlsCsv() {
		String str = getId() + "," + autor.exportiereAlsCsv() + "," + getTitel() + "," + getJahr() + "," + getVerlag()
				+ "," + getIsbn();
		return str;

	}

}