package agenciaViajes.bbdd.entidad;

import java.util.Date;
import java.util.Objects;

/**
 * Clase que representa un viaje, incluyendo su identificador, tipo, nombre,
 * fechas de inicio y fin, duración, país, descripción, servicios no incluidos,
 * identificador de la agencia que lo registró y tipo de viaje. Proporciona
 * métodos para acceder y modificar sus atributos, así como métodos para
 * comparar objetos y representar el objeto como una cadena.
 *
 * @author [Equipo 4]
 */
public class Viaje {

	private int id_viaje;
	private String tipo;
	private String nom_viaje = null;
	private Date fec_ini = null;
	private Date fec_fin = null;
	private int duracion;
	private String pais = null;
	private String desc_viaje = null;
	private String serv_no_inc = null;
	private int id_agencia;
	private String id_tipo_viaje;

	/**
	 * Obtiene el identificador del viaje.
	 *
	 * @return Identificador del viaje.
	 */
	public int getId_viaje() {
		return id_viaje;
	}

	/**
	 * Establece el identificador del viaje.
	 *
	 * @param id_viaje Nuevo identificador del viaje.
	 */
	public void setId_viaje(int id_viaje) {
		this.id_viaje = id_viaje;
	}

	/**
	 * Obtiene el tipo del viaje.
	 *
	 * @return Tipo del viaje.
	 */

	public String getTipo() {
		return tipo;
	}

	/**
	 * Establece el tipo del viaje.
	 *
	 * @param tipo Nuevo tipo del viaje.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Obtiene el nombre del viaje.
	 *
	 * @return Nombre del viaje.
	 */
	public String getNom_viaje() {
		return nom_viaje;
	}

	/**
	 * Establece el nombre del viaje.
	 *
	 * @param nom_viaje Nuevo nombre del viaje.
	 */
	public void setNom_viaje(String nom_viaje) {
		this.nom_viaje = nom_viaje;
	}

	/**
	 * Obtiene la fecha de inicio del viaje.
	 *
	 * @return Fecha de inicio del viaje.
	 */
	public Date getFec_ini() {
		return fec_ini;
	}

	/**
	 * Establece la fecha de inicio del viaje.
	 *
	 * @param fec_ini Nueva fecha de inicio del viaje.
	 */
	public void setFec_ini(Date fec_ini) {
		this.fec_ini = fec_ini;
	}

	/**
	 * Obtiene la fecha de fin del viaje.
	 *
	 * @return Fecha de fin del viaje.
	 */
	public Date getFec_fin() {
		return fec_fin;
	}

	/**
	 * Establece la fecha de fin del viaje.
	 *
	 * @param fec_fin Nueva fecha de fin del viaje.
	 */
	public void setFec_fin(Date fec_fin) {
		this.fec_fin = fec_fin;
	}

	/**
	 * Obtiene la duración del viaje en días.
	 *
	 * @return Duración del viaje.
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * Establece la duración del viaje en días.
	 *
	 * @param duracion Nueva duración del viaje.
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * Obtiene el país donde se realiza el viaje.
	 *
	 * @return País del viaje.
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Establece el país donde se realiza el viaje.
	 *
	 * @param pais Nuevo país del viaje.
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Obtiene la descripción del viaje.
	 *
	 * @return Descripción del viaje.
	 */
	public String getDesc_viaje() {
		return desc_viaje;
	}

	/**
	 * Establece la descripción del viaje.
	 *
	 * @param desc_viaje Nueva descripción del viaje.
	 */
	public void setDesc_viaje(String desc_viaje) {
		this.desc_viaje = desc_viaje;
	}

	/**
	 * Obtiene los servicios no incluidos en el viaje.
	 *
	 * @return Servicios no incluidos.
	 */
	public String getServ_no_inc() {
		return serv_no_inc;
	}

	/**
	 * Establece los servicios no incluidos en el viaje.
	 *
	 * @param serv_no_inc Nuevos servicios no incluidos.
	 */
	public void setServ_no_inc(String serv_no_inc) {
		this.serv_no_inc = serv_no_inc;
	}

	/**
	 * Obtiene el identificador de la agencia que registró el viaje.
	 *
	 * @return Identificador de la agencia.
	 */
	public int getId_agencia() {
		return id_agencia;
	}

	/**
	 * Establece el identificador de la agencia que registró el viaje.
	 *
	 * @param id_agencia Nuevo identificador de la agencia.
	 */
	public void setId_agencia(int id_agencia) {
		this.id_agencia = id_agencia;
	}

	/**
	 * Obtiene el identificador del tipo de viaje.
	 *
	 * @return Identificador del tipo de viaje.
	 */
	public String getId_tipo_viaje() {
		return id_tipo_viaje;
	}

	/**
	 * Establece el identificador del tipo de viaje.
	 *
	 * @param id_tipo_viaje Nuevo identificador del tipo de viaje.
	 */
	public void setId_tipo_viaje(String id_tipo_viaje) {
		this.id_tipo_viaje = id_tipo_viaje;
	}

	/**
	 * Genera un código hash para el objeto Viaje basado en sus campos. Este método
	 * es utilizado para almacenar objetos en estructuras de datos que requieren un
	 * hash, como conjuntos o mapas.
	 *
	 * @return Código hash del objeto.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(desc_viaje, duracion, fec_fin, fec_ini, id_agencia, id_tipo_viaje, id_viaje, nom_viaje,
				pais, serv_no_inc);
	}

	/**
	 * Compara si el objeto pasado como parámetro es igual a este objeto Viaje. Dos
	 * objetos Viaje son considerados iguales si todos sus campos son iguales.
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
		Viaje other = (Viaje) obj;
		return Objects.equals(desc_viaje, other.desc_viaje) && duracion == other.duracion
				&& Objects.equals(fec_fin, other.fec_fin) && Objects.equals(fec_ini, other.fec_ini)
				&& id_agencia == other.id_agencia && id_tipo_viaje == other.id_tipo_viaje && id_viaje == other.id_viaje
				&& Objects.equals(nom_viaje, other.nom_viaje) && Objects.equals(pais, other.pais)
				&& Objects.equals(serv_no_inc, other.serv_no_inc);
	}

	/**
	 * Genera una representación en cadena del objeto Viaje. Esta representación
	 * incluye todos los campos del objeto.
	 *
	 * @return Representación en cadena del objeto.
	 */
	@Override
	public String toString() {
		return id_viaje + ", " + nom_viaje + ", " + fec_ini + ", " + fec_fin + ", " + duracion + ", " + pais + ", "
				+ desc_viaje + ", " + serv_no_inc + ", " + id_agencia + ", " + id_tipo_viaje;
	}

}
