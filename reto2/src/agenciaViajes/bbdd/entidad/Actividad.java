package agenciaViajes.bbdd.entidad;

import java.sql.Date;
import java.util.Objects;

/**
 * Clase que representa una actividad o evento en un contexto de viajes o
 * eventos. Incluye métodos para acceder y modificar sus atributos, así como
 * métodos para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Actividad {

	private int id_evento;
	private String nom_evento = null;
	private String desc_act = null;
	private double precio_act;
	private Date fec_act = null;
	private int id_viaje; // Se guarda el ID del Viaje al que pertenece el evento

	/**
	 * Obtiene el identificador del evento.
	 *
	 * @return Identificador del evento.
	 */
	public int getId_evento() {
		return id_evento;
	}

	/**
	 * Establece el identificador del evento.
	 *
	 * @param id_evento Nuevo identificador del evento.
	 */
	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	/**
	 * Obtiene el nombre del evento.
	 *
	 * @return Nombre del evento.
	 */
	public String getNom_evento() {
		return nom_evento;
	}

	/**
	 * Establece el nombre del evento.
	 *
	 * @param nom_evento Nuevo nombre del evento.
	 */
	public void setNom_evento(String nom_evento) {
		this.nom_evento = nom_evento;
	}

	/**
	 * Obtiene la descripción de la actividad.
	 *
	 * @return Descripción de la actividad.
	 */
	public String getDesc_act() {
		return desc_act;
	}

	/**
	 * Establece la descripción de la actividad.
	 *
	 * @param desc_act Nueva descripción de la actividad.
	 */
	public void setDesc_act(String desc_act) {
		this.desc_act = desc_act;
	}

	/**
	 * Obtiene el precio de la actividad.
	 *
	 * @return Precio de la actividad.
	 */
	public double getPrecio_act() {
		return precio_act;
	}

	/**
	 * Establece el precio de la actividad.
	 *
	 * @param precio_act Nuevo precio de la actividad.
	 */
	public void setPrecio_act(double precio_act) {
		this.precio_act = precio_act;
	}

	/**
	 * Obtiene la fecha de la actividad.
	 *
	 * @return Fecha de la actividad.
	 */
	public Date getFec_act() {
		return fec_act;
	}

	/**
	 * Establece la fecha de la actividad.
	 *
	 * @param fec_act Nueva fecha de la actividad.
	 */
	public void setFec_act(Date fec_act) {
		this.fec_act = fec_act;
	}

	/**
	 * Obtiene el identificador del viaje asociado.
	 *
	 * @return Identificador del viaje.
	 */
	public int getId_viaje() {
		return id_viaje;
	}

	/**
	 * Establece el identificador del viaje asociado.
	 *
	 * @param id_viaje Nuevo identificador del viaje.
	 */
	public void setId_viaje(int id_viaje) {
		this.id_viaje = id_viaje;
	}

	/**
	 * Genera un código hash para el objeto Actividad basado en sus campos. Este
	 * método es utilizado para almacenar objetos en estructuras de datos que
	 * requieren un hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(desc_act, fec_act, id_evento, id_viaje, nom_evento, precio_act);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Actividad.
	 * Dos objetos Actividad son considerados iguales si todos sus campos son
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
		Actividad other = (Actividad) obj;
		return Objects.equals(desc_act, other.desc_act) && Objects.equals(fec_act, other.fec_act)
				&& id_evento == other.id_evento && id_viaje == other.id_viaje
				&& Objects.equals(nom_evento, other.nom_evento)
				&& Double.doubleToLongBits(precio_act) == Double.doubleToLongBits(other.precio_act);
	}

	/**
	 * Genera una representación en cadena del objeto Actividad. Esta representación
	 * incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_evento + ", " + nom_evento + ", " + desc_act + ", " + precio_act + ", " + fec_act + ", " + id_viaje;
	}

}
