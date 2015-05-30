package principal;

import concesionario.Coche;
import concesionario.Concesionario;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarConcesionario extends ConcesionarioGUI {

	private static final long serialVersionUID = 1L;
	private Concesionario concesionario;
	private int indiceCoche = -1;

	/**
	 * Create the dialog.
	 */
	public MostrarConcesionario(Concesionario concesionario) {
		super();
		setModal(true);
		comboBoxModelo.setEnabled(false);
		comboBoxMarca.setEnabled(false);
		
		setTitle("Mostrar concesionario");
		okButton.setText("Atrás"); //cambia el nombre del botón que busca el coche
		cancelButton.setVisible(false);
		
		this.concesionario = concesionario;
		
		textField.setEnabled(false);
		
		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnAzul.setEnabled(false);
		
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarAnterior();
			}
		});
		
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarSiguiente();
			}
		});
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		actualizar();
	}
	
	
	void actualizar() {
		if (concesionario.size() == 0) {
			JOptionPane.showMessageDialog(contentPanel, "No hay coches en el concesionario.");
		}
		indiceCoche = 0;
		mostrarCoche(concesionario.get(indiceCoche));
		comprobarBotones();		
	}
	
	private void mostrarSiguiente() {
		mostrarCoche(concesionario.get(++indiceCoche));
		comprobarBotones();
	}

	private void mostrarAnterior() {
		mostrarCoche(concesionario.get(--indiceCoche));
		comprobarBotones();
	}
	
	private void mostrarCoche(Coche coche) {
		textField.setText(coche.getMatricula());
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
	
	private void comprobarBotones() {
		if (concesionario.get(indiceCoche + 1) == null)
			btnSiguiente.setEnabled(false);
		else
			btnSiguiente.setEnabled(true);

		if (concesionario.get(indiceCoche - 1) == null)
			btnAnterior.setEnabled(false);
		else
			btnAnterior.setEnabled(true);
	}

}