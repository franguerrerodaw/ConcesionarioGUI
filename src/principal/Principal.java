package principal;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JSeparator;

import concesionario.Concesionario;
import concesionario.Ficheros;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * @author Francisco Javier Guerrero Molina
 * @version 1.0
 *
 */
public class Principal {

	private JFrame frmConcesionario;
	public static Concesionario concesionario = new Concesionario();
	private AcercaDe acercade;
	private Ayuda ayuda;
	protected AgregarCoche agregarCoche;
	protected EliminarCoche eliminarCoche;
	protected BuscarMatricula buscarMatricula;
	protected MostrarConcesionario mostrar;
	protected ElegirColor elegirColor;
	private final JPanel contentPanel = new JPanel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmConcesionario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConcesionario = new JFrame();
		Gestion.getTitle(frmConcesionario);
		frmConcesionario.setBounds(100, 100, 450, 300);
		frmConcesionario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConcesionario.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		frmConcesionario.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gestion.nuevo(frmConcesionario, contentPanel, concesionario);
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmNuevo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion.abrir(frmConcesionario, contentPanel, concesionario);
			}
		});
		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmAbrir);
		
		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion.guardar(frmConcesionario, concesionario);
			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardar);
		
		JMenuItem mntmGuardarComo = new JMenuItem("Guardar Como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion.guardarComo(concesionario);
			}
		});
		mntmGuardarComo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnArchivo.add(mntmGuardarComo);
		
		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);
		
		JMenu mnEditar = new JMenu("Editar");
		mnEditar.setMnemonic('E');
		menuBar.add(mnEditar);
		
		JMenuItem mntmAadirAlConcesionario = new JMenuItem("A\u00F1adir al Concesionario");
		mntmAadirAlConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCoche = new AgregarCoche(concesionario);
				agregarCoche.setVisible(true);
			}
		});
		mntmAadirAlConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnEditar.add(mntmAadirAlConcesionario);
		
		JMenuItem mntmEliminarDelConcesionario = new JMenuItem("Eliminar del Concesionario");
		mntmEliminarDelConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarCoche = new EliminarCoche(concesionario);
				eliminarCoche.setVisible(true);
			}
		});
		mntmEliminarDelConcesionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnEditar.add(mntmEliminarDelConcesionario);
		
		JMenuItem mntmBuscarCoche = new JMenuItem("Buscar Coche por Matr\u00EDcula");
		mntmBuscarCoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarMatricula = new BuscarMatricula(concesionario);
				buscarMatricula.setVisible(true);
			}
		});
		mntmBuscarCoche.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnEditar.add(mntmBuscarCoche);
		
		JMenuItem mntmMostrarConcesionario = new JMenuItem("Mostrar Concesionario");
		mntmMostrarConcesionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrar = new MostrarConcesionario(concesionario);
				mostrar.setVisible(true);
			}
		});
		mnEditar.add(mntmMostrarConcesionario);
		
		final JMenuItem mntmContarCoches = new JMenuItem("Contar Coches");
		mntmContarCoches.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concesionario == null)
					JOptionPane.showMessageDialog(mntmContarCoches, "No hay coches en el concesionario.");
				else
					JOptionPane.showMessageDialog(mntmContarCoches, "El concesionario tiene "+ concesionario.size() +" coches.");
			}
		});
		mnEditar.add(mntmContarCoches);
		
		JMenuItem mntmMostrarCochesDe = new JMenuItem("Mostrar Coches de un Color");
		mntmMostrarCochesDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (concesionario.size() == 0) {
					JOptionPane.showMessageDialog(frmConcesionario.getContentPane(), "Hay 0 coches en el concesionario", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				elegirColor = new ElegirColor(concesionario);
				elegirColor.setVisible(true);
			}
		});
		mnEditar.add(mntmMostrarCochesDe);
		
		JMenu mnAcercaDe = new JMenu("Ayuda");
		mnAcercaDe.setMnemonic('Y');
		menuBar.add(mnAcercaDe);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ayuda = new Ayuda();
				ayuda.setVisible(true);
			}
		});
		mntmAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mnAcercaDe.add(mntmAyuda);
		
		JMenuItem mntmAutor = new JMenuItem("Acerca de...");
		mntmAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				acercade = new AcercaDe();
				acercade.setVisible(true);
			}
		});
		mnAcercaDe.add(mntmAutor);
		
		frmConcesionario.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {
			@Override
				public void windowClosing(WindowEvent e) {
					if (Gestion.getModificado()){
						int confirm = JOptionPane.showOptionDialog(null, "El concesionario ha sido modificado. \n¿Desea guardar los cambios?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
						if (confirm == 0)
							Gestion.guardarComo(concesionario);
						if (confirm == 1)
							System.exit(0);
					}
					System.exit(0);
				}
		};
		frmConcesionario.addWindowListener(exitListener);

		
	}

}