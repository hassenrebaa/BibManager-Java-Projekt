package BibRebb;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;

public class Autor implements CsvExportable, Serializable {

	private String vorname;
	private String nachname;

	public Autor()

	{

	}

	public Autor(String nachname, String vorname) {
		this.nachname = nachname;
		this.vorname = vorname;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	// hashCode Methode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
		return result;
	}

	// equals methode

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		} else if (!nachname.equals(other.nachname))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

	public String toString() {
		return getVorname() + getNachname();
	}

	public String exportiereAlsCsv() {
		String str = getVorname() + "," + getNachname();
		return str;

	}

	// public void druckEintrag(OutputStream stream) {
	// PrintStream ps =new PrintStream(stream);
	// ps.println(getVorname()+' '+getNachname());

	// }

}
