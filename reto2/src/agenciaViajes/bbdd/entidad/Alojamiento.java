package agenciaViajes.bbdd.entidad;

import java.sql.Date;
import java.util.Objects;

/**
 * Clase que representa un alojamiento, incluyendo su identificador de evento,
 * nombre del evento, ciudad, precio del alojamiento, fechas de entrada y
 * salida, tipo de habitación y el identificador del viaje al que pertenece.
 * Proporciona métodos para acceder y modificar sus atributos, así como métodos
 * para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Alojamiento {

	private int id_evento;
	private String nom_evento = null;
	private String ciudad = null;
	private double precio_aloj;
	private Date fec_entrada = null;
	private Date fec_salida = null;

	/**
	 * Identificador del tipo de habitación.
	 */
	private String id_tipo_hab = null; // Se guarda el tipo de habitacion que es

	/**
	 * Identificador del viaje al que pertenece el alojamiento.
	 */
	private int id_viaje; // Se guarda el ID del Viaje al que pertenece el evento

	/**
	 * Obtiene el identificador del evento asociado al alojamiento.
	 *
	 * @return Identificador del evento.
	 */
	public int getId_evento() {
		return id_evento;
	}

	/**
	 * Establece el identificador del evento asociado al alojamiento.
	 *
	 * @param id_evento Nuevo identificador del evento.
	 */
	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	/**
	 * Obtiene el nombre del evento asociado al alojamiento.
	 *
	 * @return Nombre del evento.
	 */
	public String getNom_evento() {
		return nom_evento;
	}

	/**
	 * Establece el nombre del evento asociado al alojamiento.
	 *
	 * @param nom_evento Nuevo nombre del evento.
	 */
	public void setNom_evento(String nom_evento) {
		this.nom_evento = nom_evento;
	}

	/**
	 * Obtiene la ciudad donde se encuentra el alojamiento.
	 *
	 * @return Ciudad del alojamiento.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Establece la ciudad donde se encuentra el alojamiento.
	 *
	 * @param ciudad Nueva ciudad del alojamiento.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Obtiene el precio del alojamiento.
	 *
	 * @return Precio del alojamiento.
	 */
	public double getPrecio_aloj() {
		return precio_aloj;
	}

	/**
	 * Establece el precio del alojamiento.
	 *
	 * @param precio_aloj Nuevo precio del alojamiento.
	 */
	public void setPrecio_aloj(double precio_aloj) {
		this.precio_aloj = precio_aloj;
	}

	/**
	 * Obtiene la fecha de entrada al alojamiento.
	 *
	 * @return Fecha de entrada.
	 */
	public Date getFec_entrada() {
		return fec_entrada;
	}

	/**
	 * Establece la fecha de entrada al alojamiento.
	 *
	 * @param fec_entrada Nueva fecha de entrada.
	 */
	public void setFec_entrada(Date fec_entrada) {
		this.fec_entrada = fec_entrada;
	}

	/**
	 * Obtiene la fecha de salida del alojamiento.
	 *
	 * @return Fecha de salida.
	 */
	public Date getFec_salida() {
		return fec_salida;
	}

	/**
	 * Establece la fecha de salida del alojamiento.
	 *
	 * @param fec_salida Nueva fecha de salida.
	 */
	public void setFec_salida(Date fec_salida) {
		this.fec_salida = fec_salida;
	}

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
	 * Obtiene el identificador del viaje al que pertenece el alojamiento.
	 *
	 * @return Identificador del viaje.
	 */
	public int getId_viaje() {
		return id_viaje;
	}

	/**
	 * Establece el identificador del viaje al que pertenece el alojamiento.
	 *
	 * @param id_viaje Nuevo identificador del viaje.
	 */
	public void setId_viaje(int id_viaje) {
		this.id_viaje = id_viaje;
	}

	/**
	 * Genera un código hash para el objeto Alojamiento basado en sus campos. Este
	 * método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ciudad, fec_entrada, fec_salida, id_evento, id_tipo_hab, id_viaje, nom_evento, precio_aloj);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto
	 * Alojamiento. Dos objetos Alojamiento son considerados iguales si todos sus
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
		Alojamiento other = (Alojamiento) obj;
		return Objects.equals(ciudad, other.ciudad) && Objects.equals(fec_entrada, other.fec_entrada)
				&& Objects.equals(fec_salida, other.fec_salida) && id_evento == other.id_evento
				&& Objects.equals(id_tipo_hab, other.id_tipo_hab) && id_viaje == other.id_viaje
				&& Objects.equals(nom_evento, other.nom_evento)
				&& Double.doubleToLongBits(precio_aloj) == Double.doubleToLongBits(other.precio_aloj);
	}

	/**
	 * Genera una representación en cadena del objeto Alojamiento. Esta
	 * representación incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_evento + ", " + nom_evento + ", " + ciudad + ", " + precio_aloj + ", " + fec_entrada + ", "
				+ fec_salida + ", " + id_tipo_hab + ", " + id_viaje;
	}

}
