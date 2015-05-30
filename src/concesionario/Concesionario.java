package concesionario;
import java.io.Serializable;
import java.util.ArrayList;
import principal.Gestion;

/**
 * El nombre del concesionario es "IES Gran Capitán".
 * 
 * Lógicamente, no podrá añadirse un coche inválido glmacén del concesinario)
 * 
 * Han de conocerse todas sus características Ninguno de los valores puede ser
 * por defecto
 * 
 * @author Francisco Javier Guerrero Molina
 * 
 */
public class Concesionario implements Serializable {
	/**
	 * colección de coches. No puede tener null
	 */
	private ArrayList<Coche> almacen = new ArrayList<Coche>();
	private final String nombre = "Concesionario";

	public boolean annadir(String matricula, Color color, Modelo modelo) throws MatriculaInvalidaException, ColorNoValidoException, ModeloNoValidoException, CocheYaExisteException {
		Coche coche = new Coche(matricula, color, modelo);
		if (almacen.contains(coche))
			throw new CocheYaExisteException("El coche ya existe");
		return almacen.add(coche) && Gestion.setModificado(true);
	}

	public boolean eliminar(String matricula) throws CocheNoExisteException, MatriculaInvalidaException {
		Coche coche = new Coche(matricula);
		if (almacen.contains(coche))
			return almacen.remove(coche) && Gestion.setModificado(true);
		else
			throw new CocheNoExisteException("El coche no existe en el concesionario.");
	}

	
	public int size() {
		return almacen.size();
	}

	/**
	 * Devuelve el Coche del almacén indicado por la matrícula
	 * 
	 * @param matricula
	 *            Matrícula a buscar
	 * @return Coche contenido en el almacén. null si no existe
	 * @throws MatriculaInvalidaException 
	 * @throws CocheNoExisteException 
	 */
	public Coche get(String matricula) throws MatriculaInvalidaException, CocheNoExisteException {
		Coche coche = new Coche(matricula);
		int index = almacen.indexOf(coche);
		if (index != -1) {
			return almacen.get(index);
		}
		throw new CocheNoExisteException("El coche no existe en el concesionario.");
	}
	
	public Coche get(int index) {
		if(almacen.isEmpty())
			return null;
		if(index < 0 | index > almacen.size()-1)
			return null;
		return almacen.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Concesionario " + nombre + "[almacen=" + almacen + "]";
	}

	public ArrayList<Coche> getCochesColor(Color color) {
		ArrayList<Coche> arrCochesColor = new ArrayList<Coche>();
		for (Coche coche : almacen) {
			if(coche.getColor()== color)
				arrCochesColor.add(coche);
		}
		return arrCochesColor;
	}

}