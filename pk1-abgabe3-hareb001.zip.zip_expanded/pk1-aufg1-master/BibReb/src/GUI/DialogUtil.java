package GUI;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class DialogUtil {

	/**
	 * Oeffnet einen Nachfragedialog mit den Buttons "Ja" und "Nein".
	 * 
	 * @param titel
	 *            Der Titel des Dialoges
	 * @param nachricht
	 *            Die Nachricht, die auf dem Dialog angezeigt wird (Nachfrage).
	 * @return {@code true} wenn der Benutzer "Ja" geklickt hat, {@code false} sonst
	 */
	public static boolean showConfirmDialog(String titel, String nachricht) {
		Alert dialog = new Alert(AlertType.CONFIRMATION, nachricht, ButtonType.YES, ButtonType.NO);
		dialog.setTitle(titel);
		Optional<ButtonType> result = dialog.showAndWait();
		return result.get() == ButtonType.YES;
	}

	/**
	 * Oeffnet einen Dialog, der eine Nachricht anzeigt.
	 * 
	 * @param titel
	 *            Der Titel des Dialoges
	 * @param nachricht
	 *            Die Nachricht, die auf dem Dialog angezeigt wird.
	 */
	public static void showMessageDialog(String titel, String nachricht) {
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle(titel);
		dialog.setContentText(nachricht);
		dialog.showAndWait();
	}

	/**
	 * Oeffnet einen Dialog, der einen Fehlermeldung anzeigt.
	 * 
	 * @param titel
	 *            Der Titel des Dialoges
	 * @param nachricht
	 *            Die Fehlermeldung, die auf dem Dialog angezeigt wird.
	 */
	public static void showErrorDialog(String titel, String nachricht) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titel);
		alert.setContentText(nachricht);
		alert.showAndWait();
	}

	/**
	 * Oeffnet einen Dialog, der eine Texteingabe vom Benutzer anfordert.
	 * 
	 * @param titel
	 *            Der Titel des Dialoges
	 * @param nachricht
	 *            Die Nachricht, die auf dem Dialog angezeigt wird.
	 * @return Der vom Benutzer eingegebene Text oder ein leerer String, falls der
	 *         Benutzer nichts eingegeben bzw. "Abbrechen" geklickt hat.
	 */
	public static String showInputDialog(String titel, String nachricht) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(titel);
		dialog.setContentText(nachricht);

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}
		return "";
	}
}
