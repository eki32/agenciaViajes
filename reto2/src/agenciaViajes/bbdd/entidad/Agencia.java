package agenciaViajes.bbdd.entidad;

import java.util.Objects;

/**
 * Clase que representa una agencia, incluyendo su identificador, nombre,
 * contraseña, color, logo, tipo de agencia y código del empleado asociado.
 * Proporciona métodos para acceder y modificar sus atributos, así como métodos
 * para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Agencia {

	private int id_agencia;
	private String nom_agencia = null;
	private String password = null;
	private String color = null;
	private String logo = null;

	/**
	 * Identificador del tipo de agencia.
	 */
	private String id_tipo_agen = null; // Aquí se pone el id_tipo_agencia

	/**
	 * Código del empleado asociado a la agencia.
	 */
	private String cod_emp = null; // Aquí se pone el código del empleado

	/**
	 * Obtiene el identificador de la agencia.
	 *
	 * @return Identificador de la agencia.
	 */
	public int getId_agencia() {
		return id_agencia;
	}

	/**
	 * Establece el identificador de la agencia.
	 *
	 * @param id_agencia Nuevo identificador de la agencia.
	 */
	public void setId_agencia(int id_agencia) {
		this.id_agencia = id_agencia;
	}

	/**
	 * Obtiene el nombre de la agencia.
	 *
	 * @return Nombre de la agencia.
	 */
	public String getNom_agencia() {
		return nom_agencia;
	}

	/**
	 * Establece el nombre de la agencia.
	 *
	 * @param nom_agencia Nuevo nombre de la agencia.
	 */
	public void setNom_agencia(String nom_agencia) {
		this.nom_agencia = nom_agencia;
	}

	/**
	 * Obtiene la contraseña de la agencia.
	 *
	 * @return Contraseña de la agencia.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña de la agencia.
	 *
	 * @param password Nueva contraseña de la agencia.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtiene el color asociado a la agencia.
	 *
	 * @return Color de la agencia.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Establece el color asociado a la agencia.
	 *
	 * @param color Nuevo color de la agencia.
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Obtiene el logo de la agencia.
	 *
	 * @return Logo de la agencia.
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Establece el logo de la agencia.
	 *
	 * @param logo Nuevo logo de la agencia.
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

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
	 * Obtiene el código del empleado asociado a la agencia.
	 *
	 * @return Código del empleado.
	 */
	public String getCod_emp() {
		return cod_emp;
	}

	/**
	 * Establece el código del empleado asociado a la agencia.
	 *
	 * @param cod_emp Nuevo código del empleado.
	 */
	public void setCod_emp(String cod_emp) {
		this.cod_emp = cod_emp;
	}

	/**
	 * Genera un código hash para el objeto Agencia basado en sus campos. Este
	 * método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cod_emp, color, id_agencia, id_tipo_agen, logo, nom_agencia, password);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Agencia.
	 * Dos objetos Agencia son considerados iguales si todos sus campos son iguales.
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
		Agencia other = (Agencia) obj;
		return Objects.equals(cod_emp, other.cod_emp) && Objects.equals(color, other.color)
				&& id_agencia == other.id_agencia && Objects.equals(id_tipo_agen, other.id_tipo_agen)
				&& Objects.equals(logo, other.logo) && Objects.equals(nom_agencia, other.nom_agencia)
				&& Objects.equals(password, other.password);
	}

	/**
	 * Genera una representación en cadena del objeto Agencia. Esta representación
	 * incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_agencia + ", " + nom_agencia + ", " + password + ", " + color + ", " + logo + ", " + id_tipo_agen
				+ ", " + cod_emp;
	}

}
