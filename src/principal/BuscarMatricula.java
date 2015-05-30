package principal;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import concesionario.Coche;
import concesionario.CocheNoExisteException;
import concesionario.Concesionario;
import concesionario.MatriculaInvalidaException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarMatricula extends ConcesionarioGUI {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public BuscarMatricula(final Concesionario concesionario) {
		super();
		setModal(true);
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		setTitle("Buscar Coche por Matrícula");
		btnAnterior.setVisible(false);
		btnSiguiente.setVisible(false);
		rdbtnAzul.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnPlata.setEnabled(false);
		
		okButton.setText("Buscar"); //cambia el nombre del botón que busca el coche
		cancelButton.setText("Atrás"); //cambia el nombre del botón que cancela la acción
		
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Coche coche;
				try {
					coche = concesionario.get(textField.getText());
					mostrarCoche(coche);
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