package concesionario;
import java.io.Serializable;
import java.util.regex.Pattern;
/**
 * @author Francisco Javier Guerrero Molina
 */

public class Coche implements Serializable {
	private String matricula;
	private Color color;
	private Modelo modelo;
	static final private Pattern patternMatricula = Pattern.compile("^\\d{4}[ -]?[[B-Z]&&[^QEIOU]]{3}$");

	/**
	 * Constructor para crear el objeto coche cuando añadimos un coche nuevo.
	 * @param matricula
	 * @param color
	 * @param modelo
	 * @throws MatriculaInvalidaException
	 * @throws ColorNoValidoException 
	 * @throws ModeloNoValidoException 
	 */
	Coche(String matricula, Color color, Modelo modelo) throws MatriculaInvalidaException, ColorNoValidoException, ModeloNoValidoException {
		setMatricula(matricula); //comprueba que la matrícula sea válida.
		setColor(color); //comprueba que el color no sea null.
		setModelo(modelo); //comprueba que el modelo no sea null.
	}

	/**
	 * Constructor para crear el objeto coche con solo el argumento matrícula, que después
	 * será comprobado mediante el método equals.
	 * @param matricula
	 * @throws MatriculaInvalidaException
	 */
	Coche(String matricula) throws MatriculaInvalidaException {
		setMatricula(matricula);
	}
	
	private void setMatricula(String matricula) throws MatriculaInvalidaException {
		if (esValida(matricula))
			this.matricula = matricula;
		else
			throw new MatriculaInvalidaException("La matrícula es inválida.");
	}

	private static boolean esValida(String matricula) {
		return patternMatricula.matcher(matricula).matches();
	}
	
	private void setColor(Color color) throws ColorNoValidoException {
		if (color != null)
			this.color = color;
		else
			throw new ColorNoValidoException("El color no puede estar vacío.");
	}
	
	public Color getColor() {
		return color;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
	public String getMatricula() {
		return matricula;
	}

	private void setModelo(Modelo modelo) throws ModeloNoValidoException {
		if (modelo != null)
			this.modelo = modelo;
		else
			throw new ModeloNoValidoException("El modelo no puede estar vacío.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\nCoche [matricula=" + matricula + ", color=" + color
				+ ", modelo=" + modelo + "]";
	}

}
