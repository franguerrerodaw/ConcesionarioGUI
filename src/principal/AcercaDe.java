package principal;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AcercaDe extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public AcercaDe() {
		setModal(true);
		setTitle("Acerca de");
		setBounds(100, 100, 300, 297);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextPane txtpnConcesionarioDeCoches = new JTextPane();
		txtpnConcesionarioDeCoches.setBackground(Color.LIGHT_GRAY);
		txtpnConcesionarioDeCoches.setEditable(false);
		txtpnConcesionarioDeCoches.setText("Concesionario de coches con entorno gr\u00E1fico en Java.\r\n\r\nAutor: Francisco Javier Guerrero Molina\r\n\r\nVersi\u00F3n: 1.0");
		txtpnConcesionarioDeCoches.setBounds(10, 11, 272, 215);
		contentPanel.add(txtpnConcesionarioDeCoches);
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