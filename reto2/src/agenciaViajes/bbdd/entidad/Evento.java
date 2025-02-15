package agenciaViajes.bbdd.entidad;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase que representa un evento, que puede ser un vuelo, un alojamiento o una
 * actividad. Incluye atributos comunes y específicos para cada tipo de evento,
 * así como métodos para acceder y modificar sus atributos. Proporciona métodos
 * para comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Evento {

	private int id_evento;
	private String nom_evento = null;
	private String tipo = null;

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
	private LocalTime hora_ida;
	private LocalTime hora_vuelta;
	private double precio;
	private String duracion_ida;
	private String duracion_vuelta;
	private String cod_aeropuerto = null;
	private String cod_ae = null;

	// Atributos de Alojamiento

	private String ciudad = null;
	private Date fec_salida = null;
	private String id_tipo_hab = null;

	// Atributos de Actividad

	private String desc_act = null;
	private int id_viaje;

	/**
	 * Genera un código hash para el objeto Evento basado en sus campos. Este método
	 * es utilizado para almacenar objetos en estructuras de datos que requieren un
	 * hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(ae_destino, ae_origen, aerolinea_ida, aerolinea_vuelta, ciudad, cod_ae, cod_aeropuerto,
				cod_vuelo_ida, cod_vuelo_vuelta, desc_act, duracion_ida, duracion_vuelta, fec_ida, fec_salida,
				fec_vuelta, hora_ida, hora_vuelta, id_evento, id_tipo_hab, id_viaje, nom_evento, precio, tipo,
				trayecto);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Evento. Dos
	 * objetos Evento son considerados iguales si todos sus campos son iguales.
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
		Evento other = (Evento) obj;
		return Objects.equals(ae_destino, other.ae_destino) && Objects.equals(ae_origen, other.ae_origen)
				&& Objects.equals(aerolinea_ida, other.aerolinea_ida)
				&& Objects.equals(aerolinea_vuelta, other.aerolinea_vuelta) && Objects.equals(ciudad, other.ciudad)
				&& Objects.equals(cod_ae, other.cod_ae) && Objects.equals(cod_aeropuerto, other.cod_aeropuerto)
				&& cod_vuelo_ida == other.cod_vuelo_ida && cod_vuelo_vuelta == other.cod_vuelo_vuelta
				&& Objects.equals(desc_act, other.desc_act) && Objects.equals(duracion_ida, other.duracion_ida)
				&& duracion_vuelta == other.duracion_vuelta && Objects.equals(fec_ida, other.fec_ida)
				&& Objects.equals(fec_salida, other.fec_salida) && Objects.equals(fec_vuelta, other.fec_vuelta)
				&& Objects.equals(hora_ida, other.hora_ida) && hora_vuelta == other.hora_vuelta
				&& id_evento == other.id_evento && Objects.equals(id_tipo_hab, other.id_tipo_hab)
				&& id_viaje == other.id_viaje && Objects.equals(nom_evento, other.nom_evento)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(tipo, other.tipo) && Objects.equals(trayecto, other.trayecto);
	}

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
	 * Obtiene el tipo de evento.
	 *
	 * @return Tipo de evento.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo de evento.
	 *
	 * @param tipo Nuevo tipo de evento.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public LocalTime getHora_ida() {
		return hora_ida;
	}

	/**
	 * Establece la hora de salida del vuelo de ida.
	 *
	 * @param hora_ida Nueva hora de salida del vuelo de ida.
	 */
	public void setHora_ida(LocalTime hora_ida) {
		this.hora_ida = hora_ida;
	}

	/**
	 * Obtiene la hora de salida del vuelo de vuelta.
	 *
	 * @return Hora de salida del vuelo de vuelta.
	 */
	public LocalTime getHora_vuelta() {
		return hora_vuelta;
	}

	/**
	 * Establece la hora de salida del vuelo de vuelta.
	 *
	 * @param hora_vuelta Nueva hora de salida del vuelo de vuelta.
	 */
	public void setHora_vuelta(LocalTime hora_vuelta) {
		this.hora_vuelta = hora_vuelta;
	}

	/**
	 * Obtiene el precio del evento.
	 *
	 * @return Precio del evento.
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Establece el precio del evento.
	 *
	 * @param precio Nuevo precio del evento.
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Obtiene la duración del vuelo de ida.
	 *
	 * @return Duración del vuelo de ida.
	 */
	public String getDuracion_ida() {
		return duracion_ida;
	}

	/**
	 * Establece la duración del vuelo de ida.
	 *
	 * @param duracion_ida Nueva duración del vuelo de ida.
	 */
	public void setDuracion_ida(String duracion_ida) {
		this.duracion_ida = duracion_ida;
	}

	/**
	 * Obtiene la duración del vuelo de vuelta.
	 *
	 * @return Duración del vuelo de vuelta.
	 */
	public String getDuracion_vuelta() {
		return duracion_vuelta;
	}

	/**
	 * Establece la duración del vuelo de vuelta.
	 *
	 * @param duracion_vuelta Nueva duración del vuelo de vuelta.
	 */
	public void setDuracion_vuelta(String duracion_vuelta) {
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
	 * Obtiene la ciudad del alojamiento.
	 *
	 * @return Ciudad del alojamiento.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Establece la ciudad del alojamiento.
	 *
	 * @param ciudad Nueva ciudad del alojamiento.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Obtiene la fecha de salida del alojamiento.
	 *
	 * @return Fecha de salida del alojamiento.
	 */
	public Date getFec_salida() {
		return fec_salida;
	}

	/**
	 * Establece la fecha de salida del alojamiento.
	 *
	 * @param fec_salida Nueva fecha de salida del alojamiento.
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
	 * Obtiene el identificador del viaje al que pertenece el evento.
	 *
	 * @return Identificador del viaje.
	 */
	public int getId_viaje() {
		return id_viaje;
	}

	/**
	 * Establece el identificador del viaje al que pertenece el evento.
	 *
	 * @param idViaje Nuevo identificador del viaje.
	 */
	public void setId_viaje(int idViaje) {
		this.id_viaje = idViaje;
	}

	/**
	 * Genera una representación en cadena del objeto Evento. Esta representación
	 * incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_evento + ", " + nom_evento + ", " + tipo + ", " + trayecto + ", " + ae_origen + ", " + ae_destino
				+ ", " + fec_ida + ", " + fec_vuelta + ", " + cod_vuelo_ida + ", " + cod_vuelo_vuelta + ", "
				+ aerolinea_ida + ", " + aerolinea_vuelta + ", " + hora_ida + ", " + hora_vuelta + ", " + precio + ", "
				+ duracion_ida + ", " + duracion_vuelta + ", " + ciudad + ", " + fec_salida + ", " + desc_act + ", "
				+ id_viaje + ", " + cod_aeropuerto + ", " + cod_ae + ", " + id_tipo_hab;

	}

//	public String TextoOferta() {
//	    String texto = "**********************************\n";
//	    texto += "EVENTO: " + (nom_evento != null ? nom_evento.toUpperCase() : "") + "\n";
//	    texto += "**********************************\n";
//	    texto += "ID Evento: " + id_evento + "\n";
//
//	    if (tipo != null) texto += "Tipo: " + tipo + "\n";
//	    if (trayecto != null) texto += "Trayecto: " + trayecto + "\n";
//	    if (ae_origen != null) texto += "Aeropuerto de Origen: " + ae_origen + "\n";
//	    if (ae_destino != null) texto += "Aeropuerto de Destino: " + ae_destino + "\n";
//	    if (fec_ida != null) texto += "Fecha de Ida: " + fec_ida + "\n";
//	    if (fec_vuelta != null) texto += "Fecha de Vuelta: " + fec_vuelta + "\n";
//	    if (aerolinea_ida != null) texto += "Aerolínea Ida: " + aerolinea_ida + "\n";
//	    if (aerolinea_vuelta != null) texto += "Aerolínea Vuelta: " + aerolinea_vuelta + "\n";
//	    
//	    if (hora_ida != null) texto += "Hora de Ida: " + hora_ida + "\n";
//	    if (hora_vuelta > 0) texto += "Hora de Vuelta: " + hora_vuelta + "\n";
//	    
//	    if (precio_vuelo > 0) texto += "Precio del Vuelo: " + precio_vuelo + " €\n";
//	    if (duracion_ida != null) texto += "Duración de Ida: " + duracion_ida + "\n";
//	    if (duracion_vuelta > 0) texto += "Duración de Vuelta: " + duracion_vuelta + " min\n";
//	    
//	    if (ciudad != null) texto += "Ciudad de Alojamiento: " + ciudad + "\n";
//	    if (precio_aloj > 0) texto += "Precio de Alojamiento: " + precio_aloj + " €\n";
//	    if (fec_entrada != null) texto += "Fecha de Entrada: " + fec_entrada + "\n";
//	    if (fec_salida != null) texto += "Fecha de Salida: " + fec_salida + "\n";
//	    
//	    if (desc_act != null) texto += "Descripción de la Actividad: " + desc_act + "\n";
//	    if (precio_act > 0) texto += "Precio de la Actividad: " + precio_act + " €\n";
//	    if (fec_act != null) texto += "Fecha de la Actividad: " + fec_act + "\n";
//	    
//	    texto += "ID Viaje Asociado: " + id_viaje + "\n";
//
//	    texto += "**********************************\n";
//
//	    return texto;
//	}
//
//	

}
