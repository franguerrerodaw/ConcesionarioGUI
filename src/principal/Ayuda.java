package principal;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
/**
 * 
 * @author Francisco Javier Guerrero Molina
 * @version 1.0
 *
 */
public class Ayuda extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Ayuda() {
		setModal(true);
		setTitle("Ayuda");
		setBounds(100, 100, 300, 297);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 264, 215);
			contentPanel.add(scrollPane);
			{
			JTextPane txtpnConcesionarioDeCoches = new JTextPane();
			scrollPane.setViewportView(txtpnConcesionarioDeCoches);
			txtpnConcesionarioDeCoches.setBackground(Color.LIGHT_GRAY);
			txtpnConcesionarioDeCoches.setEditable(false);
			txtpnConcesionarioDeCoches.setText("Concesionario de coches con entorno gr\u00E1fico en Java.\r\n\r\nTeclas de acceso r\u00E1pido:\r\n- Nuevo CTR+N\r\n- Abrir CTR+O\r\n- Guardar CTR+S\r\n- Guardar como... CTR+ALT+S\r\n- Salir CTR+Q\r\n- Ayuda F1\r\n\r\n\r\n- - - El Concesionario Permite - - -\r\n\r\nA\u00F1adir un nuevo coche al concesionario, se pedir\u00E1: Matr\u00EDcula con formato, 1234BBB - 1234-BBB - 1234 BBB, color, marca y modelo.\r\n\r\nEliminar un coche a trav\u00E9s de su matr\u00EDcula.\r\n\r\nBuscar un coche a trav\u00E9s de su matr\u00EDcula.\r\n\r\nMostrar todos los coches del concesionario.\r\n\r\nContar todos los coches del concesionario.\r\n\r\nBuscar todos los coches de un determinado color.");
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Ok");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}