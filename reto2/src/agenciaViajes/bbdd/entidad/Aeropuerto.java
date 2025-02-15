package agenciaViajes.bbdd.entidad;

import java.util.Objects;

/**
 * Clase que representa un aeropuerto, incluyendo su código y la ciudad donde se
 * encuentra. Proporciona métodos para acceder y modificar sus atributos, así
 * como métodos para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Aeropuerto {

	private String cod_aeropuerto = null;
	private String ciudad = null;

	/**
	 * Obtiene el código del aeropuerto.
	 *
	 * @return Código del aeropuerto.
	 */
	public String getCod_aeropuerto() {
		return cod_aeropuerto;
	}

	/**
	 * Establece el código del aeropuerto.
	 *
	 * @param cod_aeropuerto Nuevo código del aeropuerto.
	 */
	public void setCod_aeropuerto(String cod_aeropuerto) {
		this.cod_aeropuerto = cod_aeropuerto;
	}

	/**
	 * Obtiene la ciudad donde se encuentra el aeropuerto.
	 *
	 * @return Ciudad del aeropuerto.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Establece la ciudad donde se encuentra el aeropuerto.
	 *
	 * @param ciudad Nueva ciudad del aeropuerto.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Genera un código hash para el objeto Aeropuerto basado en sus campos. Este
	 * método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ciudad, cod_aeropuerto);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Aeropuerto.
	 * Dos objetos Aeropuerto son considerados iguales si todos sus campos son
	 * iguales.
	 *
	 * @param obj Objeto a comparar.
	 * @return True si los objetos son iguales, false de lo contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(cod_aeropuerto, other.cod_aeropuerto);
	}

	/**
	 * Genera una representación en cadena del objeto Aeropuerto. Esta
	 * representación incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return cod_aeropuerto + ", " + ciudad;
	}

}
