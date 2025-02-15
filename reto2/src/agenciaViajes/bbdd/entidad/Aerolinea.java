package agenciaViajes.bbdd.entidad;

import java.util.Objects;

/**
 * Clase que representa una aerolínea, incluyendo su código, nombre y código del
 * país donde opera. Proporciona métodos para acceder y modificar sus atributos,
 * así como métodos para comparar objetos y representar el objeto como una
 * cadena.
 *
 * @author [Equipo 4]
 */
public class Aerolinea {

	private String cod_ae = null;
	private String nom_ae = null;
	private String cod_pais = null;

	/**
	 * Obtiene el código de la aerolínea.
	 *
	 * @return Código de la aerolínea.
	 */
	public String getCod_ae() {
		return cod_ae;
	}

	/**
	 * Establece el código de la aerolínea.
	 *
	 * @param cod_ae Nuevo código de la aerolínea.
	 */
	public void setCod_ae(String cod_ae) {
		this.cod_ae = cod_ae;
	}

	/**
	 * Obtiene el nombre de la aerolínea.
	 *
	 * @return Nombre de la aerolínea.
	 */
	public String getNom_ae() {
		return nom_ae;
	}

	/**
	 * Establece el nombre de la aerolínea.
	 *
	 * @param nom_ae Nuevo nombre de la aerolínea.
	 */
	public void setNom_ae(String nom_ae) {
		this.nom_ae = nom_ae;
	}

	/**
	 * Obtiene el código del país donde opera la aerolínea.
	 *
	 * @return Código del país.
	 */
	public String getCod_pais() {
		return cod_pais;
	}

	/**
	 * Establece el código del país donde opera la aerolínea.
	 *
	 * @param cod_pais Nuevo código del país.
	 */
	public void setCod_pais(String cod_pais) {
		this.cod_pais = cod_pais;
	}

	/**
	 * Genera un código hash para el objeto Aerolinea basado en sus campos. Este
	 * método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cod_ae, cod_pais, nom_ae);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Aerolinea.
	 * Dos objetos Aerolinea son considerados iguales si todos sus campos son
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
		Aerolinea other = (Aerolinea) obj;
		return Objects.equals(cod_ae, other.cod_ae) && Objects.equals(cod_pais, other.cod_pais)
				&& Objects.equals(nom_ae, other.nom_ae);
	}

	/**
	 * Genera una representación en cadena del objeto Aerolinea. Esta representación
	 * incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return cod_ae + ", " + nom_ae + ", " + cod_pais;
	}

}
