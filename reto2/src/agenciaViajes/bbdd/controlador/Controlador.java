package agenciaViajes.bbdd.controlador;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import agenciaViajes.bbdd.entidad.Aerolinea;
import agenciaViajes.bbdd.entidad.Aeropuerto;
import agenciaViajes.bbdd.entidad.Agencia;
import agenciaViajes.bbdd.entidad.Evento;
import agenciaViajes.bbdd.entidad.Pais;
import agenciaViajes.bbdd.entidad.Viaje;
import agenciaViajes.bbdd.gestores.GestorAerolinea;
import agenciaViajes.bbdd.gestores.GestorAeropuerto;
import agenciaViajes.bbdd.gestores.GestorAgencia;
import agenciaViajes.bbdd.gestores.GestorEventos;
import agenciaViajes.bbdd.gestores.GestorPais;
import agenciaViajes.bbdd.gestores.GestorViaje;
import agenciaViajes.vista.ficheros.GestorFicheros;

public class Controlador {

	private int idAgencia;
	private GestorViaje gestor;
	private GestorViaje gestorViaje;
	private GestorEventos gestorEvento;
	private int idViaje;
	private int idEvento;

	/**
	 * busca paises en la base de datos
	 * 
	 * @return devuelve los paises encontrados en BBDD
	 */
	public ArrayList<Pais> buscarPaises() {
		GestorPais gestor = new GestorPais();
		return gestor.buscarPaises();
	}

	/*
	 * elimina eventos de la base de datos
	 */
	public boolean eliminarEvento(int idEvento) {
		GestorEventos gestor = new GestorEventos();
		return gestor.eliminarEventos(idEvento);
	}

	/*
	 * busca eventos en la base de datos
	 */
	public ArrayList<Evento> buscarEventos(int idViaje) {
		GestorEventos gestorEvento = new GestorEventos();
		return gestorEvento.buscarEventos(idViaje);
	}

	/*
	 * busca agencias en la base de datos
	 */
	public ArrayList<Agencia> buscarAgencias() {
		GestorAgencia gestorAgencia = new GestorAgencia();
		return gestorAgencia.buscarAgencias();
	}

	/*
	 * busca agencias en la base de datos
	 */
	public void guardarAgencia(Agencia agencia) {
		GestorAgencia gestorAgencias = new GestorAgencia();
		gestorAgencias.guardarAgencia(agencia);
	}
	
	public String generarOferta(int idViaje) {
		GestorEventos gestorEvento = new GestorEventos();
		return  gestorEvento.generarOferta(idViaje);
	}
	
//	public void actualizarViajeEnBD() {
//		GestorViaje actualizarViaje = new GestorViaje();
//		actualizarViaje.actualizarViajeEnBD();
//	}
//	
//	public void actualizarEventoEnBD() {
//		GestorEventos actualizarEvento = new GestorEventos();
//		actualizarEvento.actualizarEventoEnBD();
//	}


	/*
	 * guarda viajes en la base de datos
	 */
	public void guardarViaje(Viaje viaje) {
		GestorViaje gestorViaje = new GestorViaje();
		gestorViaje.guardarViaje(viaje);
	}

	/*
	 * elimina viajes de la base de datos
	 */
	public boolean eliminarViaje(int idViaje) {
		GestorViaje eliminar = new GestorViaje();
		return eliminar.eliminarViaje(idViaje);
	}

	/*
	 * guarda los alojamientos en la base de datos
	 */
	public void guardarEventoAlojamiento(Evento evento) {
		GestorEventos gestorAlojamiento = new GestorEventos();
		gestorAlojamiento.guardarEventoAlojamiento(evento);

	}

	/*
	 * guarda los viajes de vuelo(ida) en la base de datos
	 */
	public void guardarEventoVueloIda(Evento evento) {
		GestorEventos gestorVueloIda = new GestorEventos();
		gestorVueloIda.guardarEventoVueloIda(evento);
	}

	/*
	 * guarda los viajes(ida-vuelta) en la base de datos
	 */
	public void guardarEventoVueloIdaVuelta(Evento evento, int idViaje) {
		GestorEventos gestorVueloIdaVuelta = new GestorEventos();
		gestorVueloIdaVuelta.guardarEventoVueloIdaVuelta(evento, idViaje);
	}

	/*
	 * guarda las actividades en la base de datos
	 */
	public void guardarEventoActividad(Evento evento) {
		GestorEventos gestorActividad = new GestorEventos();
		gestorActividad.guardarEventoActividad(evento);
	}

	/*
	 * guarda eventos en base de datos
	 */
	public void guardarEvento(Evento evento) {
		GestorEventos gestorEventos = new GestorEventos();
		gestorEventos.guardarEvento(evento);
	}

	/*
	 * guarda las agencias a traves de su idAgencia en la base de datos
	 */
	public ArrayList<Agencia> buscarAgenciasPorId() {
		GestorAgencia gestorAgencia = new GestorAgencia();
		return gestorAgencia.buscarAgencias();
	}

	/*
	 * busca los aeropuertos guardados en la base de datos
	 */
	public ArrayList<Aeropuerto> buscarAeropuertos() {
		GestorAeropuerto gestor = new GestorAeropuerto();
		return gestor.buscarAeropuerto();
	}

	/*
	 * busca las aerolineas guardadas en la base de datos
	 */
	public ArrayList<Aerolinea> buscarAerolinea() {
		GestorAerolinea gestor = new GestorAerolinea();
		return gestor.buscarAerolinea();
	}

	/*
	 * busca las aerolineas asociadas a un aeropuerto guardadas en la
	 * base de datos
	 */
	public ArrayList<Aerolinea> buscarAerolineaPorAeropuerto(String codAeropuerto) {
		GestorAerolinea gestor = new GestorAerolinea();
		return gestor.buscarAerolineasPorAeropuerto(codAeropuerto);
	}

	/*
	 * busca los viajes guardados en la base de datos
	 */
	public ArrayList<Viaje> buscarViajes(int idAgencia) {
		gestor = new GestorViaje();
		return gestor.buscarViajes(idAgencia);
	}

	/*
	 * muestra los vuelos(ida) guardados en la base de datos
	 */
	public ArrayList<Evento> mostrarEventoIda() {
		GestorEventos eventoIda = new GestorEventos();
		return eventoIda.mostrarEventoIda(idAgencia);
	}

	/*
	 * busca el id_tipo_habitacion(individual-doble) en la base de datos
	 */
	public String buscarId_IndividualDoble() {
		GestorEventos gestorEventos = new GestorEventos();
		return gestorEventos.buscarId_IndividualDoble();
	}

	/*
	 * busca el id_tipo_habitacion(individual) en la base de datos
	 */
	public String buscarId_Individual() {
		GestorEventos gestorEventos = new GestorEventos();
		return gestorEventos.buscarId_Individual();
	}

	/*
	 * busca el id_tipo_habitacion(triple) en la base de datos
	 */
	public String buscarId_Triple() {
		GestorEventos gestorEventos = new GestorEventos();
		return gestorEventos.buscarId_Triple();
	}

	/*
	 * busca el id_tipo_habitacion(doble) en la base de datos
	 */
	public String buscarId_Doble() {
		GestorEventos gestorEventos = new GestorEventos();
		return gestorEventos.buscarId_Doble();
	}

	/*
	 * busca el id_tipo_agencia(mayorista) en la base de datos
	 */
	public String buscarID_Mayorista() {
		GestorAgencia gestorAgencia = new GestorAgencia();
		return gestorAgencia.buscarId_Mayorista();
	}

	/*
	 * busca el id_tipo_agencia(minorista) en la base de datos
	 */
	public String buscarID_Minorista() {
		GestorAgencia gestorAgencia = new GestorAgencia();
		return gestorAgencia.buscarId_Minorista();
	}

	/*
	 * busca el id_tipo_agencia(mayorista-minorista) en la base de datos
	 */
	public String buscarID_MayoMino() {
		GestorAgencia gestorAgencia = new GestorAgencia();
		return gestorAgencia.buscarId_MayoMino();
	}

	/*
	 * busca el id_cant_empleados(2-10) en la base de datos
	 */
	public String buscarID_2_10() {
		GestorAgencia gestorAgencia = new GestorAgencia();
		return gestorAgencia.buscarId_2_10();
	}

	/*
	 * busca el id_cant_empleados(10-100) en la base de datos
	 */
	public String buscarID_10_100() {
		GestorAgencia gestorAgencia = new GestorAgencia();
		return gestorAgencia.buscarId_10_100();
	}

	/*
	 * busca el id_cant_empleados(100-1000) en la base de datos
	 */
	public String buscarID_100_1000() {
		GestorAgencia gestorAgencia = new GestorAgencia();
		return gestorAgencia.buscarId_100_1000();
	}

	/*
	 * busca el tipo_viaje(luna de miel) en la base de datos
	 */
	public String buscarLunaMiel() {
		GestorViaje gestorViaje = new GestorViaje();
		return gestorViaje.buscarLunaMiel();
	}

	/*
	 * busca el tipo_viaje(senior) en la base de datos
	 */
	public String buscarSenior() {
		GestorViaje gestorViaje = new GestorViaje();
		return gestorViaje.buscarSenior();
	}

	/*
	 * busca el tipo_viaje(grupos) en la base de datos
	 */
	public String buscarGrupos() {
		GestorViaje gestorViaje = new GestorViaje();
		return gestorViaje.buscarGrupos();
	}

	/*
	 * busca el tipo_viaje(grandes) en la base de datos
	 */
	public String buscarViajesGrandes() {
		GestorViaje gestorViaje = new GestorViaje();
		return gestorViaje.buscarViajesGrandes();
	}

	/*
	 * busca el tipo_viaje(escapadas) en la base de datos
	 */
	public String buscarEscapadas() {
		GestorViaje gestorViaje = new GestorViaje();
		return gestorViaje.buscarEscapadas();
	}

	/*
	 * busca el tipo_viaje(familiar) en la base de datos
	 */
	public String buscarFamilias() {
		GestorViaje gestorViaje = new GestorViaje();
		return gestorViaje.buscarFamilias();
	}

	/*
	 * busca el tipo_viaje en la base de datos
	 */
	public String buscarTipo() {
		gestorViaje = new GestorViaje();
		return gestorViaje.buscarTipo();
	}

	public int buscarAgenciaPorId(int idAgencia) {
		GestorAgencia gestor= new GestorAgencia();
		return gestor.buscarAgenciaPorId(idAgencia);
	}



}
