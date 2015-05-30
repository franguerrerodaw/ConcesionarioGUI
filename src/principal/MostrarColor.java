package principal;
import java.util.ArrayList;

import concesionario.Coche;
import concesionario.CocheYaExisteException;
import concesionario.ColorNoValidoException;
import concesionario.Concesionario;
import concesionario.MatriculaInvalidaException;
import concesionario.ModeloNoValidoException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

public class MostrarColor extends ConcesionarioGUI {

	private static final long serialVersionUID = 1L;
	private Concesionario concesionario;
	private int indiceCoche = -1;

	/**
	 * Create the dialog.
	 * @throws CocheYaExisteException 
	 * @throws ModeloNoValidoException 
	 * @throws ColorNoValidoException 
	 * @throws MatriculaInvalidaException 
	 */
	public MostrarColor(ArrayList<Coche> concesionario){
		super();
		setModal(true);
		
		setTitle("Mostrar por Color");
		okButton.setText("Atrás");
		cancelButton.setVisible(false);
		
		textField.setEnabled(false);
		
		rdbtnPlata.setEnabled(false);
		rdbtnRojo.setEnabled(false);
		rdbtnAzul.setEnabled(false);
		
		comboBoxMarca.setEnabled(false);
		comboBoxModelo.setEnabled(false);
		
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
		
		try {
			this.concesionario = generarConcesionario(concesionario);
		} catch (MatriculaInvalidaException e1) {
			JOptionPane.showMessageDialog(contentPanel, "Formato de matrícula inválida. \nFormato: 1234BBB - 1234-BBB - 1234 BBB", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ColorNoValidoException e1) {
			JOptionPane.showMessageDialog(contentPanel, "Elige un color.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (ModeloNoValidoException e1) {
			JOptionPane.showMessageDialog(contentPanel, "Añade un modelo.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (CocheYaExisteException e1) {
			JOptionPane.showMessageDialog(contentPanel, "El coche ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	
		actualizar();
	}
	
	void actualizar() {
		if (concesionario.size() == 0) {
			return;
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
	
	private Concesionario generarConcesionario(ArrayList<Coche> concesionario) throws MatriculaInvalidaException, ColorNoValidoException, ModeloNoValidoException, CocheYaExisteException {
		Concesionario mostrarPorColor = new Concesionario();
		for (Coche coche : concesionario) {
			mostrarPorColor.annadir(coche.getMatricula(),coche.getColor(), coche.getModelo());
		}
		return mostrarPorColor;
	}

}