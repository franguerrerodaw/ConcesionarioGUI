package principal;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import concesionario.Coche;
import concesionario.Color;
import concesionario.Concesionario;
import javax.swing.ButtonGroup;

public class ElegirColor extends JDialog {


	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JRadioButton radioButtonPlata;
	private JRadioButton radioButtonRojo;
	private JRadioButton radioButtonAzul;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton aceptar;
	private JButton salir;
	private MostrarColor mostrarColor;

	/**
	 * Create the dialog.
	 */
	public ElegirColor(final Concesionario concesionario) {
		setModal(true);
		setTitle("Elegir color");
		setBounds(100, 100, 298, 122);
		
		radioButtonPlata = new JRadioButton("Plata");
		radioButtonPlata.setBounds(26, 7, 65, 23);
		buttonGroup.add(radioButtonPlata);
		
		radioButtonRojo = new JRadioButton("Rojo");
		radioButtonRojo.setBounds(111, 7, 65, 23);
		buttonGroup.add(radioButtonRojo);
		
		radioButtonAzul = new JRadioButton("Azul");
		radioButtonAzul.setBounds(198, 7, 65, 23);
		buttonGroup.add(radioButtonAzul);
		
		aceptar = new JButton("Aceptar");
		aceptar.setBounds(114, 48, 86, 23);
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = getColor();
				if (color != null) {
					ArrayList<Coche> coches = concesionario.getCochesColor(color);
					if (coches.isEmpty()) {
						JOptionPane.showMessageDialog(contentPanel, "No existe ningún coche de ese color.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					mostrarColor = new MostrarColor(coches);
					mostrarColor.setVisible(true);
				} else
					JOptionPane.showMessageDialog(contentPanel, "Selecciona un color.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		salir = new JButton("Atr\u00E1s");
		salir.setBounds(210, 48, 65, 23);
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		getContentPane().setLayout(null);
		getContentPane().add(aceptar);
		getContentPane().add(salir);
		getContentPane().add(radioButtonPlata);
		getContentPane().add(radioButtonRojo);
		getContentPane().add(radioButtonAzul);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	private Color getColor() {
		if (radioButtonPlata.isSelected())
			return Color.PLATA;
		else if (radioButtonRojo.isSelected())
			return Color.ROJO;
		else if (radioButtonAzul.isSelected())
			return Color.AZUL;
		else
			return null;
	}

}