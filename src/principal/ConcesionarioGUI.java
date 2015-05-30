package principal;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import concesionario.Marca;
import concesionario.Modelo;
/**
 * 
 * @author Francisco Javier Guerrero Molina
 * @version 1.0
 *
 */
public class ConcesionarioGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	
	protected final JPanel contentPanel = new JPanel();
	protected JTextField textField;
	protected final ButtonGroup buttonGroup = new ButtonGroup();
	protected JPanel buttonPane;
	protected JLabel lblIntroduceUnaMatrcula;
	protected JPanel panel;
	protected JRadioButton rdbtnPlata;
	protected JRadioButton rdbtnRojo;
	protected JRadioButton rdbtnAzul;
	protected JPanel panel_1;
	protected JComboBox<Marca> comboBoxMarca;
	protected JComboBox<Modelo> comboBoxModelo;
	protected JButton btnAnterior;
	protected JButton btnSiguiente;
	protected JButton okButton;
	protected JButton cancelButton;


	/**
	 * Create the dialog.
	 */
	public ConcesionarioGUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField();
			textField.setBounds(209, 27, 138, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		
		lblIntroduceUnaMatrcula = new JLabel("Matr\u00EDcula del Coche :");
		lblIntroduceUnaMatrcula.setBounds(84, 30, 152, 14);
		contentPanel.add(lblIntroduceUnaMatrcula);
		
		panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(null, "Color del Coche", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(72, 95, 121, 111);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		rdbtnPlata = new JRadioButton("Plata");
		rdbtnPlata.setBounds(6, 16, 109, 23);
		panel.add(rdbtnPlata);
		buttonGroup.add(rdbtnPlata);
		
		rdbtnRojo = new JRadioButton("Rojo");
		rdbtnRojo.setBounds(6, 50, 109, 23);
		panel.add(rdbtnRojo);
		buttonGroup.add(rdbtnRojo);
		
		rdbtnAzul = new JRadioButton("Azul");
		rdbtnAzul.setBounds(6, 82, 109, 23);
		panel.add(rdbtnAzul);
		buttonGroup.add(rdbtnAzul);
		
		comboBoxMarca = new JComboBox<Marca>();
		comboBoxMarca.setBounds(232, 120, 104, 20);
		contentPanel.add(comboBoxMarca);
		
		
		comboBoxModelo = new JComboBox<Modelo>();
		comboBoxModelo.setBounds(232, 158, 104, 22);
		contentPanel.add(comboBoxModelo);
		
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			btnAnterior = new JButton("< Anterior");
			buttonPane.add(btnAnterior);
			
			btnSiguiente = new JButton("Siguiente >");
			buttonPane.add(btnSiguiente);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}