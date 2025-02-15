package agenciaViajes.bbdd.entidad;

import java.util.Objects;

/**
 * Clase que representa un tipo de agencia, incluyendo su identificador y
 * descripción. Proporciona métodos para acceder y modificar sus atributos, así
 * como métodos para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Tipo_Agencia {

	private String id_tipo_agen = null;
	private String desc_tipo_agen = null;

	/**
	 * Obtiene el identificador del tipo de agencia.
	 *
	 * @return Identificador del tipo de agencia.
	 */
	public String getId_tipo_agen() {
		return id_tipo_agen;
	}

	/**
	 * Establece el identificador del tipo de agencia.
	 *
	 * @param id_tipo_agen Nuevo identificador del tipo de agencia.
	 */
	public void setId_tipo_agen(String id_tipo_agen) {
		this.id_tipo_agen = id_tipo_agen;
	}

	/**
	 * Obtiene la descripción del tipo de agencia.
	 *
	 * @return Descripción del tipo de agencia.
	 */
	public String getDesc_tipo_agen() {
		return desc_tipo_agen;
	}

	/**
	 * Establece la descripción del tipo de agencia.
	 *
	 * @param desc_tipo_agen Nueva descripción del tipo de agencia.
	 */
	public void setDesc_tipo_agen(String desc_tipo_agen) {
		this.desc_tipo_agen = desc_tipo_agen;
	}

	/**
	 * Genera un código hash para el objeto Tipo_Agencia basado en sus campos. Este
	 * método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(desc_tipo_agen, id_tipo_agen);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto
	 * Tipo_Agencia. Dos objetos Tipo_Agencia son considerados iguales si todos sus
	 * campos son iguales.
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
		Tipo_Agencia other = (Tipo_Agencia) obj;
		return Objects.equals(desc_tipo_agen, other.desc_tipo_agen) && Objects.equals(id_tipo_agen, other.id_tipo_agen);
	}

	/**
	 * Genera una representación en cadena del objeto Tipo_Agencia. Esta
	 * representación incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_tipo_agen + ", " + desc_tipo_agen;
	}

}
