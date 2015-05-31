package principal;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import concesionario.Concesionario;
import concesionario.Ficheros;
/**
 * 
 * @author Francisco Javier Guerrero Molina
 *
 */
public class Gestion {
	
	/**
	 * genera una nueva ventana jframe
	 */
	private static JFrame frame = new JFrame();
	
	/**
	 * almacena si el concesionario ha sido modificado añadiendo o eliminando coches. Comprueba si hay que guardar antes de crear uno nuevo.
	 */
	protected static boolean modificado = false;
	
	/**
	 * almacena si el concesionario ha sido guardado o no para mostrar el título en barra de título.
	 */
	protected static boolean guardado = false;
	
	
	public static boolean setModificado(boolean valor) {
		modificado = valor;
		return true;
	}
	
	public static boolean getModificado() {
		return modificado;
	}
	
	/**
	 * Muestra el título de la barra según el fichero existente.
	 * @param frmConcesionario
	 */
	static void getTitle(JFrame frmConcesionario) {
		if(Gestion.getGuardado() == false)
			frmConcesionario.setTitle("Concesionario  Sin_Título");
		else
			frmConcesionario.setTitle("Concesionario  " + Ficheros.getFile());
		
	}
	
	/**
	 * modifica el estado del título.
	 * @param valor
	 * @return
	 */
	public static boolean setGuardado(boolean valor) {
		guardado = valor;
		return true;
	}
	
	public static boolean getGuardado() {
		return guardado;
	}
	
	
	/** ********* GESTIÓN DE FICHEROS ************* */
	
	
	
	static void nuevo(JFrame frmConcesionario, JPanel contentPanel, Concesionario concesionario){
		if (Gestion.getModificado() == true){ //true == concesionario con coches (modificado)
			int n = JOptionPane.showOptionDialog(contentPanel, "El concesionario ha sido modificado. \n¿Desea guardar los cambios?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			switch (n) {
				case JOptionPane.YES_OPTION:
				try {
					Ficheros.guardarComo(concesionario);
					gestionNuevo(frmConcesionario);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(frame, "No se ha podido abrir el fichero", "Error: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, "No se ha podido abrir el fichero", "Error: IOException", JOptionPane.ERROR_MESSAGE);
				}
					break;
				case JOptionPane.NO_OPTION:
					gestionNuevo(frmConcesionario);
			}
		} else{
			gestionNuevo(frmConcesionario);
		}
	}

	
	
	static void abrir(JFrame frmConcesionario, JPanel contentPanel, Concesionario concesionario){
		if (Gestion.getModificado() == true){ //true == concesionario con coches
			int n = JOptionPane.showOptionDialog(contentPanel, "El concesionario ha sido modificado. \n¿Desea guardar los cambios?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			switch (n) {
				case JOptionPane.YES_OPTION:
				try {
					Ficheros.guardarComo(concesionario);
					gestionModificado(frmConcesionario);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(frame, "No se ha podido abrir el fichero", "Error: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, "No se ha podido abrir el fichero", "Error: IOException", JOptionPane.ERROR_MESSAGE);
				}
					break;
				case JOptionPane.NO_OPTION:
				try {
					Principal.concesionario = Ficheros.abrir(concesionario);
					gestionModificado(frmConcesionario);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(frame, "No se ha podido abrir el fichero", "Error: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(frame, "El fichero no contiene un concesionario válido", "Error: ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, "No se ha podido abrir el fichero", "Error: IOException", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else {
			try {
				Principal.concesionario = Ficheros.abrir(concesionario);
				gestionModificado(frmConcesionario);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(frame, "No se ha podido abrir el fichero", "Error: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(frame, "El fichero no contiene un concesionario válido", "Error: ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(frame, "No se ha podido abrir el fichero", "Error: IOException", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	
	static void guardar(JFrame frmConcesionario, Concesionario concesionario){
		try {
			Ficheros.guardar(concesionario);
			gestionModificado(frmConcesionario);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(frame, "No se ha podido guardar el archivo", "Error: FileNotFoundException", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error al guardar", "Error: IOException", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	static void guardarComo(Concesionario concesionario){
		try {
			Ficheros.guardarComo(concesionario);
			Gestion.setModificado(false);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error al guardar", "Error: IOException", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private static void gestionNuevo(JFrame frmConcesionario) {
		Principal.concesionario = new Concesionario();
		gestionModificado(frmConcesionario);
	}
	
	private static void gestionModificado(JFrame frmConcesionario) {
		Ficheros.nuevo();
		Gestion.setModificado(false);
		Gestion.setGuardado(false); // modifica el estado del título.
		Gestion.getTitle(frmConcesionario); // muestra la ruta del fichero en la barra de título.
	}
	

}