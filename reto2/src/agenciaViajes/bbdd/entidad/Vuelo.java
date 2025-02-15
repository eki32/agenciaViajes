package agenciaViajes.bbdd.entidad;

import java.sql.Date;
import java.util.Objects;

/**
 * Clase que representa un vuelo, incluyendo su identificador de evento, nombre
 * del evento, trayecto, aeropuertos de origen y destino, fechas de ida y
 * vuelta, códigos de vuelo, aerolíneas, horas de salida, precio del vuelo,
 * duraciones, códigos de aeropuerto y aerolínea, y el identificador del viaje
 * al que pertenece. Proporciona métodos para acceder y modificar sus atributos,
 * así como métodos para comparar objetos y representar el objeto como una
 * cadena.
 *
 * @author [Equipo 4]
 */
public class Vuelo {

	private int id_evento;
	private String nom_evento = null;

	// Atributos de Vuelo

	private String trayecto = null;
	private String ae_origen = null;
	private String ae_destino = null;
	private Date fec_ida = null;
	private Date fec_vuelta = null;
	private int cod_vuelo_ida;
	private int cod_vuelo_vuelta;
	private String aerolinea_ida = null;
	private String aerolinea_vuelta = null;
	private int hora_ida;
	private int hora_vuelta;
	private double precio_vuelo;
	private int duracion_ida;
	private int duracion_vuelta;
	private String cod_aeropuerto = null;
	private String cod_ae = null;
	private int id_viaje;

	/**
	 * Obtiene el identificador del evento asociado al vuelo.
	 *
	 * @return Identificador del evento.
	 */
	public int getId_evento() {
		return id_evento;
	}

	/**
	 * Establece el identificador del evento asociado al vuelo.
	 *
	 * @param id_evento Nuevo identificador del evento.
	 */
	public void setId_evento(int id_evento) {
		this.id_evento = id_evento;
	}

	/**
	 * Obtiene el nombre del evento asociado al vuelo.
	 *
	 * @return Nombre del evento.
	 */
	public String getNom_evento() {
		return nom_evento;
	}

	/**
	 * Establece el nombre del evento asociado al vuelo.
	 *
	 * @param nom_evento Nuevo nombre del evento.
	 */
	public void setNom_evento(String nom_evento) {
		this.nom_evento = nom_evento;
	}

	/**
	 * Obtiene el trayecto del vuelo.
	 *
	 * @return Trayecto del vuelo.
	 */
	public String getTrayecto() {
		return trayecto;
	}

	/**
	 * Establece el trayecto del vuelo.
	 *
	 * @param trayecto Nuevo trayecto del vuelo.
	 */
	public void setTrayecto(String trayecto) {
		this.trayecto = trayecto;
	}

	/**
	 * Obtiene el aeropuerto de origen del vuelo.
	 *
	 * @return Aeropuerto de origen.
	 */
	public String getAe_origen() {
		return ae_origen;
	}

	/**
	 * Establece el aeropuerto de origen del vuelo.
	 *
	 * @param ae_origen Nuevo aeropuerto de origen.
	 */
	public void setAe_origen(String ae_origen) {
		this.ae_origen = ae_origen;
	}

	/**
	 * Obtiene el aeropuerto de destino del vuelo.
	 *
	 * @return Aeropuerto de destino.
	 */
	public String getAe_destino() {
		return ae_destino;
	}

	/**
	 * Establece el aeropuerto de destino del vuelo.
	 *
	 * @param ae_destino Nuevo aeropuerto de destino.
	 */
	public void setAe_destino(String ae_destino) {
		this.ae_destino = ae_destino;
	}

	/**
	 * Obtiene la fecha de ida del vuelo.
	 *
	 * @return Fecha de ida.
	 */
	public Date getFec_ida() {
		return fec_ida;
	}

	/**
	 * Establece la fecha de ida del vuelo.
	 *
	 * @param fec_ida Nueva fecha de ida.
	 */
	public void setFec_ida(Date fec_ida) {
		this.fec_ida = fec_ida;
	}

	/**
	 * Obtiene la fecha de vuelta del vuelo.
	 *
	 * @return Fecha de vuelta.
	 */
	public Date getFec_vuelta() {
		return fec_vuelta;
	}

	/**
	 * Establece la fecha de vuelta del vuelo.
	 *
	 * @param fec_vuelta Nueva fecha de vuelta.
	 */
	public void setFec_vuelta(Date fec_vuelta) {
		this.fec_vuelta = fec_vuelta;
	}

	/**
	 * Obtiene el código del vuelo de ida.
	 *
	 * @return Código del vuelo de ida.
	 */
	public int getCod_vuelo_ida() {
		return cod_vuelo_ida;
	}

	/**
	 * Establece el código del vuelo de ida.
	 *
	 * @param cod_vuelo_ida Nuevo código del vuelo de ida.
	 */
	public void setCod_vuelo_ida(int cod_vuelo_ida) {
		this.cod_vuelo_ida = cod_vuelo_ida;
	}

	/**
	 * Obtiene el código del vuelo de vuelta.
	 *
	 * @return Código del vuelo de vuelta.
	 */
	public int getCod_vuelo_vuelta() {
		return cod_vuelo_vuelta;
	}

	/**
	 * Establece el código del vuelo de vuelta.
	 *
	 * @param cod_vuelo_vuelta Nuevo código del vuelo de vuelta.
	 */
	public void setCod_vuelo_vuelta(int cod_vuelo_vuelta) {
		this.cod_vuelo_vuelta = cod_vuelo_vuelta;
	}

	/**
	 * Obtiene la aerolínea del vuelo de ida.
	 *
	 * @return Aerolínea del vuelo de ida.
	 */
	public String getAerolinea_ida() {
		return aerolinea_ida;
	}

	/**
	 * Establece la aerolínea del vuelo de ida.
	 *
	 * @param aerolinea_ida Nueva aerolínea del vuelo de ida.
	 */
	public void setAerolinea_ida(String aerolinea_ida) {
		this.aerolinea_ida = aerolinea_ida;
	}

	/**
	 * Obtiene la aerolínea del vuelo de vuelta.
	 *
	 * @return Aerolínea del vuelo de vuelta.
	 */
	public String getAerolinea_vuelta() {
		return aerolinea_vuelta;
	}

	/**
	 * Establece la aerolínea del vuelo de vuelta.
	 *
	 * @param aerolinea_vuelta Nueva aerolínea del vuelo de vuelta.
	 */
	public void setAerolinea_vuelta(String aerolinea_vuelta) {
		this.aerolinea_vuelta = aerolinea_vuelta;
	}

	/**
	 * Obtiene la hora de salida del vuelo de ida.
	 *
	 * @return Hora de salida del vuelo de ida.
	 */
	public int getHora_ida() {
		return hora_ida;
	}

	/**
	 * Establece la hora de salida del vuelo de ida.
	 *
	 * @param hora_ida Nueva hora de salida del vuelo de ida.
	 */
	public void setHora_ida(int hora_ida) {
		this.hora_ida = hora_ida;
	}

	/**
	 * Obtiene la hora de salida del vuelo de vuelta.
	 *
	 * @return Hora de salida del vuelo de vuelta.
	 */
	public int getHora_vuelta() {
		return hora_vuelta;
	}

	/**
	 * Establece la hora de salida del vuelo de vuelta.
	 *
	 * @param hora_vuelta Nueva hora de salida del vuelo de vuelta.
	 */
	public void setHora_vuelta(int hora_vuelta) {
		this.hora_vuelta = hora_vuelta;
	}

	/**
	 * Obtiene el precio del vuelo.
	 *
	 * @return Precio del vuelo.
	 */
	public double getPrecio_vuelo() {
		return precio_vuelo;
	}

	/**
	 * Establece el precio del vuelo.
	 *
	 * @param precio_vuelo Nuevo precio del vuelo.
	 */
	public void setPrecio_vuelo(double precio_vuelo) {
		this.precio_vuelo = precio_vuelo;
	}

	/**
	 * Obtiene la duración del vuelo de ida en minutos.
	 *
	 * @return Duración del vuelo de ida.
	 */
	public int getDuracion_ida() {
		return duracion_ida;
	}

	/**
	 * Establece la duración del vuelo de ida en minutos.
	 *
	 * @param duracion_ida Nueva duración del vuelo de ida.
	 */
	public void setDuracion_ida(int duracion_ida) {
		this.duracion_ida = duracion_ida;
	}

	/**
	 * Obtiene la duración del vuelo de vuelta en minutos.
	 *
	 * @return Duración del vuelo de vuelta.
	 */
	public int getDuracion_vuelta() {
		return duracion_vuelta;
	}

	/**
	 * Establece la duración del vuelo de vuelta en minutos.
	 *
	 * @param duracion_vuelta Nueva duración del vuelo de vuelta.
	 */
	public void setDuracion_vuelta(int duracion_vuelta) {
		this.duracion_vuelta = duracion_vuelta;
	}

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
	 * Obtiene el identificador del viaje al que pertenece el vuelo.
	 *
	 * @return Identificador del viaje.
	 */
	public int getId_viaje() {
		return id_viaje;
	}

	/**
	 * Establece el identificador del viaje al que pertenece el vuelo.
	 *
	 * @param id_viaje Nuevo identificador del viaje.
	 */
	public void setId_viaje(int id_viaje) {
		this.id_viaje = id_viaje;
	}

	/**
	 * Genera un código hash para el objeto Vuelo basado en sus campos. Este método
	 * es utilizado para almacenar objetos en estructuras de datos que requieren un
	 * hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ae_destino, ae_origen, aerolinea_ida, aerolinea_vuelta, cod_ae, cod_aeropuerto,
				cod_vuelo_ida, cod_vuelo_vuelta, duracion_ida, duracion_vuelta, fec_ida, fec_vuelta, hora_ida,
				hora_vuelta, id_evento, id_viaje, nom_evento, precio_vuelo, trayecto);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Vuelo. Dos
	 * objetos Vuelo son considerados iguales si todos sus campos son iguales.
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
		Vuelo other = (Vuelo) obj;
		return Objects.equals(ae_destino, other.ae_destino) && Objects.equals(ae_origen, other.ae_origen)
				&& Objects.equals(aerolinea_ida, other.aerolinea_ida)
				&& Objects.equals(aerolinea_vuelta, other.aerolinea_vuelta) && Objects.equals(cod_ae, other.cod_ae)
				&& Objects.equals(cod_aeropuerto, other.cod_aeropuerto) && cod_vuelo_ida == other.cod_vuelo_ida
				&& cod_vuelo_vuelta == other.cod_vuelo_vuelta && duracion_ida == other.duracion_ida
				&& duracion_vuelta == other.duracion_vuelta && Objects.equals(fec_ida, other.fec_ida)
				&& Objects.equals(fec_vuelta, other.fec_vuelta) && hora_ida == other.hora_ida
				&& hora_vuelta == other.hora_vuelta && id_evento == other.id_evento && id_viaje == other.id_viaje
				&& Objects.equals(nom_evento, other.nom_evento)
				&& Double.doubleToLongBits(precio_vuelo) == Double.doubleToLongBits(other.precio_vuelo)
				&& Objects.equals(trayecto, other.trayecto);
	}

	/**
	 * Genera una representación en cadena del objeto Vuelo. Esta representación
	 * incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_evento + ", " + nom_evento + ", " + trayecto + ", " + ae_origen + ", " + ae_destino + ", " + fec_ida
				+ ", " + fec_vuelta + ", " + cod_vuelo_ida + ", " + cod_vuelo_vuelta + ", " + aerolinea_ida + ", "
				+ aerolinea_vuelta + ", " + hora_ida + ", " + hora_vuelta + ", " + precio_vuelo + ", " + duracion_ida
				+ ", " + duracion_vuelta + ", " + cod_aeropuerto + ", " + cod_ae + ", " + id_viaje;
	}

}
