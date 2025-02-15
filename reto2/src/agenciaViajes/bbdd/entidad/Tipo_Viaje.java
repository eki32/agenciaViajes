package agenciaViajes.bbdd.entidad;

import java.util.Objects;

/**
 * Clase que representa un tipo de viaje, incluyendo su identificador y
 * descripción. Proporciona métodos para acceder y modificar sus atributos, así
 * como métodos para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Tipo_Viaje {

	private String id_tipo_viaje = null;
	private String desc_tipo_viaje = null;

	/**
	 * Obtiene el identificador del tipo de viaje.
	 *
	 * @return Identificador del tipo de viaje.
	 */
	public String getId_tipo_viaje() {
		return id_tipo_viaje;
	}

	/**
	 * Establece el identificador del tipo de viaje.
	 *
	 * @param id_tipo_viaje Nuevo identificador del tipo de viaje.
	 */
	public void setId_tipo_viaje(String id_tipo_viaje) {
		this.id_tipo_viaje = id_tipo_viaje;
	}

	/**
	 * Obtiene la descripción del tipo de viaje.
	 *
	 * @return Descripción del tipo de viaje.
	 */
	public String getDesc_tipo_viaje() {
		return desc_tipo_viaje;
	}

	/**
	 * Establece la descripción del tipo de viaje.
	 *
	 * @param desc_tipo_viaje Nueva descripción del tipo de viaje.
	 */
	public void setDesc_tipo_viaje(String desc_tipo_viaje) {
		this.desc_tipo_viaje = desc_tipo_viaje;
	}

	/**
	 * Genera un código hash para el objeto Tipo_Viaje basado en sus campos. Este
	 * método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(desc_tipo_viaje, id_tipo_viaje);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Tipo_Viaje.
	 * Dos objetos Tipo_Viaje son considerados iguales si todos sus campos son
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
		Tipo_Viaje other = (Tipo_Viaje) obj;
		return Objects.equals(desc_tipo_viaje, other.desc_tipo_viaje)
				&& Objects.equals(id_tipo_viaje, other.id_tipo_viaje);
	}

	/**
	 * Genera una representación en cadena del objeto Tipo_Viaje. Esta
	 * representación incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_tipo_viaje + ", " + desc_tipo_viaje;
	}

}
