package BibRebb;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Webseite extends BibEintrag implements CsvExportable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Webseite other = (Webseite) obj;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	private String url;

	public Webseite() {

	}

	public Webseite(Autor autor, String titel, int jahr, String url) {
		super(autor, titel, jahr);
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void druckEintrag(OutputStream stream) {
		/*
		 * String str=(super.toString()+' '+getUrl()); try {
		 * stream.write(str.getBytes()); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		PrintStream ps = new PrintStream(stream);
		// ps.println(super.toString()+" Url: "+getUrl());
		ps.println(this.toString());

	}

	public String toString() {
		return super.toString() + "  Url: " + getUrl();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String exportiereAlsCsv() {
		String str = super.getID() + "," + autor.exportiereAlsCsv() + "," + getTitel() + "," + getJahr() + ","
				+ getUrl();
		return str;

	}

}