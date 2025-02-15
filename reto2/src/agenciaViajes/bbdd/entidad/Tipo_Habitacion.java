package agenciaViajes.bbdd.entidad;

import java.util.Objects;

/**
 * Clase que representa un tipo de habitación, incluyendo su identificador y
 * descripción. Proporciona métodos para acceder y modificar sus atributos, así
 * como métodos para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Tipo_Habitacion {

	private String id_tipo_hab = null;
	private String desc_hab = null;

	/**
	 * Obtiene el identificador del tipo de habitación.
	 *
	 * @return Identificador del tipo de habitación.
	 */
	public String getId_tipo_hab() {
		return id_tipo_hab;
	}

	/**
	 * Establece el identificador del tipo de habitación.
	 *
	 * @param id_tipo_hab Nuevo identificador del tipo de habitación.
	 */
	public void setId_tipo_hab(String id_tipo_hab) {
		this.id_tipo_hab = id_tipo_hab;
	}

	/**
	 * Obtiene la descripción del tipo de habitación.
	 *
	 * @return Descripción del tipo de habitación.
	 */
	public String getDesc_hab() {
		return desc_hab;
	}

	/**
	 * Establece la descripción del tipo de habitación.
	 *
	 * @param desc_hab Nueva descripción del tipo de habitación.
	 */
	public void setDesc_hab(String desc_hab) {
		this.desc_hab = desc_hab;
	}

	/**
	 * Genera un código hash para el objeto Tipo_Habitacion basado en sus campos.
	 * Este método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(desc_hab, id_tipo_hab);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto
	 * Tipo_Habitacion. Dos objetos Tipo_Habitacion son considerados iguales si
	 * todos sus campos son iguales.
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
		Tipo_Habitacion other = (Tipo_Habitacion) obj;
		return Objects.equals(desc_hab, other.desc_hab) && Objects.equals(id_tipo_hab, other.id_tipo_hab);
	}

	/**
	 * Genera una representación en cadena del objeto Tipo_Habitacion. Esta
	 * representación incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_tipo_hab + ", " + desc_hab;
	}

}
