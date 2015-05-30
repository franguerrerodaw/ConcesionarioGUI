package concesionario;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
* 
* @author Francisco Javier Guerrero Molina
* 
*/
public class Ficheros implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static File file = new File("sin_titulo.obj");
	private static JFileChooser fileChooser = new JFileChooser();
	private static JFrame frame = new JFrame();
	private static FileNameExtensionFilter filtro = new FileNameExtensionFilter("objeto.obj", "obj");
	
	
	public static File getFile() {
		return file;
	}
	
	public static void nuevo(){
		new File("sin_titulo.obj");
	}
	

	public static Concesionario abrir(Concesionario concesionario) throws FileNotFoundException, ClassNotFoundException, IOException {
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filtro);
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) { //si la opción elegida es aceptar abrimos el archivo
			file = fileChooser.getSelectedFile();
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
				concesionario = (Concesionario) in.readObject();
			}
		}
		return concesionario;
	}
	
	public static void guardar(Concesionario concesionario) throws IOException, FileNotFoundException {
		if (file.getName() == "sin_titulo.obj")
			guardarComo(concesionario);
		else {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file + ".obj"))) {
				out.writeObject(concesionario);
			}
		}
	}
	
	public static void guardarComo(Concesionario concesionario) throws FileNotFoundException, IOException {
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(filtro);
		if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Ficheros.file + ".obj"))) {
				out.writeObject(concesionario);
			}
		}
	}
	
}