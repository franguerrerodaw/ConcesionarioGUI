package principal;
import java.awt.HeadlessException;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import concesionario.CocheYaExisteException;
import concesionario.Color;
import concesionario.ColorNoValidoException;
import concesionario.Concesionario;
import concesionario.Marca;
import concesionario.MatriculaInvalidaException;
import concesionario.Modelo;
import concesionario.ModeloNoValidoException;
/**
 * 
 * @author Francisco Javier Guerrero Molina
 * @version 1.0
 *
 */
public class AgregarCoche extends ConcesionarioGUI {

	private static final long serialVersionUID = 1L;

	public AgregarCoche(final Concesionario concesionario) {
		super();
		setModal(true);
		setTitle("Agregar Coche");
		cancelButton.setText("Atrás");
		okButton.setText("Añadir");
		btnAnterior.setVisible(false);
		btnSiguiente.setVisible(false);
		textField.setToolTipText("Formato de Matr\u00EDcula: 1234BBB - 1234-BBB - 1234 BBB");
		
		comboBoxMarca.setModel(new DefaultComboBoxModel(Marca.values())); //iniciamos el primer combobox con las marcas Marca.values().
		comboBoxMarca.addItemListener(new ItemListener() { //iniciamos el segundo combobox mediante un evento de item
			public void itemStateChanged(ItemEvent arg0) { //evento item
				comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo()));
			}
		});

		comboBoxMarca.setModel(new DefaultComboBoxModel(Marca.values())); //iniciar de nuevo para que la marca no aparezca en blanco
		comboBoxModelo.setModel(new DefaultComboBoxModel(getModelo()));//iniciar de nuevo para que el modelo no aparezca en blanco
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (concesionario.annadir(textField.getText(), getColor(), (Modelo) comboBoxModelo.getSelectedItem()))
						JOptionPane.showMessageDialog(contentPanel, "Coche añadido con éxito.");
					else
						JOptionPane.showMessageDialog(contentPanel, "El coche no se ha podido añadir.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (MatriculaInvalidaException e) {
					JOptionPane.showMessageDialog(contentPanel, "Formato de matrícula inválida. \nFormato: 1234BBB - 1234-BBB - 1234 BBB", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ColorNoValidoException e) {
					JOptionPane.showMessageDialog(contentPanel, "Elige un color.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ModeloNoValidoException e) {
					JOptionPane.showMessageDialog(contentPanel, "Añade un modelo.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (CocheYaExisteException e) {
					JOptionPane.showMessageDialog(contentPanel, "El coche ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

	}
	
	/**
	 * 
	 * @return color elegido.
	 */
	private Color getColor() {
		if (rdbtnPlata.isSelected())
			return Color.PLATA;
		else if (rdbtnRojo.isSelected())
			return Color.ROJO;
		else if (rdbtnAzul.isSelected())
			return Color.AZUL;
		else
			return null;
	}
	
	/**
	 * Recorre una enumeración y elige los modelos de una determinada marca.
	 * @return lista de modelos
	 */
	private Object[] getModelo() {
		Marca marca = (Marca) comboBoxMarca.getSelectedItem(); //coge la marca del combobox
		ArrayList<Modelo> modelos = new ArrayList<Modelo>();
		for (Modelo m: Modelo.values()) {
			if (m.getMarca() == marca) //la primera marca es la enumeración, la segunda marca es la del combobox
				modelos.add(m);
		}
		return modelos.toArray(); //devolvemos los modelos en forma de array.
	}

}