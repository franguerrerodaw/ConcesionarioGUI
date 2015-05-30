package principal;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import concesionario.Coche;
import concesionario.CocheNoExisteException;
import concesionario.Concesionario;
import concesionario.MatriculaInvalidaException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EliminarCoche extends ConcesionarioGUI {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public EliminarCoche(final Concesionario concesionario) {
		super();
		setModal(true);
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		
		setTitle("Eliminar Coche"); //cambia el nombre de la ventana
		okButton.setText("Eliminar"); //cambia el nombre del botón que elimina el coche
		cancelButton.setText("Atrás");
		btnAnterior.setVisible(false);
		btnSiguiente.setVisible(false);
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Coche coche = concesionario.get(textField.getText());
					mostrarCoche(coche);
					int n = JOptionPane.showOptionDialog(contentPanel, "¿Está seguro de que desea eliminarlo?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
					switch (n) {
						case JOptionPane.YES_OPTION:
							concesionario.eliminar(textField.getText());
							limpiar();
							break;
						}
				} catch (MatriculaInvalidaException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Formato de matrícula inválida. \nFormato: 1234BBB - 1234-BBB - 1234 BBB", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (CocheNoExisteException e1) {
					JOptionPane.showMessageDialog(contentPanel, "El coche no existe.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}
	
	private void limpiar() {
		textField.setText("");
		buttonGroup.clearSelection();
		comboBoxMarca.setSelectedItem(null);
		comboBoxModelo.setSelectedItem(null);	
	}
	
	private void mostrarCoche(Coche coche) {
		switch (coche.getColor()) {
		case PLATA:
			rdbtnPlata.setSelected(true);
			break;
		case ROJO:
			rdbtnRojo.setSelected(true);
			break;
		case AZUL:
			rdbtnAzul.setSelected(true);
		}
		comboBoxMarca.addItem(coche.getModelo().getMarca());
		comboBoxMarca.setSelectedItem(coche.getModelo().getMarca());
		comboBoxModelo.addItem(coche.getModelo());
		comboBoxModelo.setSelectedItem(coche.getModelo());
	}

}