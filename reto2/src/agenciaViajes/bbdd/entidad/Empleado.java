package agenciaViajes.bbdd.entidad;

import java.util.Objects;

/**
 * Clase que representa un empleado, incluyendo su código y descripción.
 * Proporciona métodos para acceder y modificar sus atributos, así como métodos
 * para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Empleado {

	private String cod_emp = null;
	private String desc_emp = null;

	/**
	 * Obtiene el código del empleado.
	 *
	 * @return Código del empleado.
	 */
	public String getCod_emp() {
		return cod_emp;
	}

	/**
	 * Establece el código del empleado.
	 *
	 * @param cod_emp Nuevo código del empleado.
	 */
	public void setCod_emp(String cod_emp) {
		this.cod_emp = cod_emp;
	}

	/**
	 * Obtiene la descripción del empleado.
	 *
	 * @return Descripción del empleado.
	 */
	public String getDesc_emp() {
		return desc_emp;
	}

	/**
	 * Establece la descripción del empleado.
	 *
	 * @param desc_emp Nueva descripción del empleado.
	 */
	public void setDesc_emp(String desc_emp) {
		this.desc_emp = desc_emp;
	}

	/**
	 * Genera un código hash para el objeto Empleado basado en sus campos. Este
	 * método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cod_emp, desc_emp);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Empleado.
	 * Dos objetos Empleado son considerados iguales si todos sus campos son
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
		Empleado other = (Empleado) obj;
		return Objects.equals(cod_emp, other.cod_emp) && Objects.equals(desc_emp, other.desc_emp);
	}

	/**
	 * Genera una representación en cadena del objeto Empleado. Esta representación
	 * incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return cod_emp + ", " + desc_emp;
	}

}
