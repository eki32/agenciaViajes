package agenciaViajes.bbdd.entidad;

import java.util.Objects;

/**
 * Clase que representa un país, incluyendo su código y nombre. Proporciona
 * métodos para acceder y modificar sus atributos, así como métodos para
 * comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Pais {

	private String cod_pais = null;
	private String nom_pais = null;

	/**
	 * Obtiene el código del país.
	 *
	 * @return Código del país.
	 */
	public String getCod_pais() {
		return cod_pais;
	}

	/**
	 * Establece el código del país.
	 *
	 * @param cod_pais Nuevo código del país.
	 */
	public void setCod_pais(String cod_pais) {
		this.cod_pais = cod_pais;
	}

	/**
	 * Obtiene el nombre del país.
	 *
	 * @return Nombre del país.
	 */
	public String getNom_pais() {
		return nom_pais;
	}

	/**
	 * Establece el nombre del país.
	 *
	 * @param nom_pais Nuevo nombre del país.
	 */
	public void setNom_pais(String nom_pais) {
		this.nom_pais = nom_pais;
	}

	/**
	 * Genera un código hash para el objeto Pais basado en sus campos. Este método
	 * es utilizado para almacenar objetos en estructuras de datos que requieren un
	 * hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cod_pais, nom_pais);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Pais. Dos
	 * objetos Pais son considerados iguales si todos sus campos son iguales.
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
		Pais other = (Pais) obj;
		return Objects.equals(cod_pais, other.cod_pais) && Objects.equals(nom_pais, other.nom_pais);
	}

	/**
	 * Genera una representación en cadena del objeto Pais. Esta representación
	 * incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return cod_pais + ", " + nom_pais;
	}

}
