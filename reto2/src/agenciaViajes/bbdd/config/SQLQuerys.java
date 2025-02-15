package agenciaViajes.bbdd.config;

/**
 * Esta clase contiene las sentencias SQL del proyecto
 */
public class SQLQuerys {


	// SELECT DE PAIS
		public static final String SELECT_TODOS_PAISES = "SELECT nom_pais FROM `Pais`";

		// SELECT DE AEROPUERTOS
		public static final String SELECT_TODOS_AEROPUERTOS = "SELECT cod_aeropuerto, ciudad FROM `Aeropuerto`";

		// SELECT DE AEROLINEAS
		public static final String SELECT_NOMBRE_COD_AEROLINEA = "SELECT nom_ae, a.cod_pais FROM `Aerolinea` a join `Pais` p on a.cod_pais=p.cod_pais WHERE a.COD_PAIS IN (SELECT p.COD_PAIS FROM `Pais` p);";

		// SELECT DE AGENCIA
		public static final String SELECT_ID_NOMBRE_PASSWORD_COLOR_LOGO_AGENCIA = "SELECT id_agencia, nom_agencia, password, color, logo FROM `Agencia`";
		public static final String SELECT_ID_AGENCIA = "SELECT id_Agencia FROM `Agencia`";
		public static final String SELECT_ID_MAYORISTA = "Select id_tipo_agen from tipo_agencia where desc_tipo_agen = 'Mayorista' ";
		public static final String SELECT_ID_MINORISTA = "Select id_tipo_agen from tipo_agencia where desc_tipo_agen = 'Minorista' ";
		public static final String SELECT_ID_MAYOMINO = "Select id_tipo_agen from tipo_agencia where desc_tipo_agen = 'Mayorista-Minorista' ";
		public static final String SELECT_ID_2_10 = "Select cod_emp from empleado where desc_emp = 'Entre 2 y 10 empleados' ";
		public static final String SELECT_ID_10_100 = "Select cod_emp from empleado where desc_emp = 'Entre 10 y 100 empleados' ";
		public static final String SELECT_ID_100_1000 = "Select cod_emp from empleado where desc_emp = 'Entre 100 y 1000 empleados' ";

		// SELECT DE VIAJE
		public static final String SELECT_VIAJE_AGENCIA =  "SELECT v.id_viaje, v.nom_viaje AS tipo, v.fec_ini, v.fec_fin, v.duracion, v.pais FROM viaje v JOIN agencia a ON a.id_agencia = v.id_agencia WHERE v.id_agencia = ?";
		public static final String SELECT_VIAJE_TABLA = "SELECT v.id_viaje, v.nom_viaje, tv.desc_tipo_viaje AS tipo, v.fec_ini, v.fec_fin, v.duracion, v.pais FROM viaje v JOIN tipo_viaje tv ON v.id_tipo_viaje = tv.id_tipo_viaje where v.id_agencia = ?";
		
		// SELECT DE EVENTOS
		public static final String SELECT_EVENTOS = "SELECT nombreEvento, tipoEvento, fechaEvento, precioEvento FROM `Evento`";
		public static final String SELECT_EVENTOS_TABLA = "SELECT id_evento, nom_evento, tipo, fec_ida, precio FROM `Evento` where id_viaje= ? ";
		
		// SELECT DE TIPO HABITACION
		public static final String SELECT_ID_DOBLE = "Select id_tipo_hab from tipo_habitacion where desc_hab = 'Doble' ";
		public static final String SELECT_ID_INDIVIDUAL_DOBLE = "Select id_tipo_hab from tipo_habitacion where desc_hab = 'Individual-Doble' ";
		public static final String SELECT_ID_INDIVIDUAL = "Select id_tipo_hab from tipo_habitacion where desc_hab = 'Individual' ";
		public static final String SELECT_ID_TRIPLE = "Select id_tipo_hab from tipo_habitacion where desc_hab = 'Triple' ";
		

		// SELECT TIPO_VIAJE
		public static final String SELECT_ID_LUNAMIEL = "Select id_tipo_viaje from tipo_viaje where desc_tipo_viaje = 'Luna de Miel' ";
		public static final String SELECT_ID_FAMILIAS = "Select id_tipo_viaje from tipo_viaje where desc_tipo_viaje = 'Familias (con niños pequeños)' ";
		public static final String SELECT_ID_ESCAPADAS = "Select id_tipo_viaje from tipo_viaje where desc_tipo_viaje = 'Escapadas' ";
		public static final String SELECT_ID_VIAJES_GRANDES = "Select id_tipo_viaje from tipo_viaje where desc_tipo_viaje = 'Viajes grandes (destinos exóticos + vuelo + alojamiento)' ";
		public static final String SELECT_ID_GRUPOS = "Select id_tipo_viaje from tipo_viaje where desc_tipo_viaje = 'Grupos' ";
		public static final String SELECT_ID_SENIOR = "Select id_tipo_viaje from tipo_viaje where desc_tipo_viaje = 'Senior' ";


	// INSERTS AGENCIAS
		public static final String INSERT_NEW_AGENCIA = "insert into agencia ( nom_agencia, password, color, logo, id_tipo_agen, cod_emp) VALUES ('";
	// INSERTS VIAJES
		public static final String INSERT_NEW_VIAJE = "INSERT INTO viaje (nom_viaje, fec_ini, fec_fin, duracion, pais, desc_viaje, serv_no_inc, id_agencia, id_tipo_viaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		public static final String END_BLOCK = "')";
		public static final String SEPARATOR = "', '";
	//
//		// UPDATES 
	//	
//		public static final String UPDATE_MEDIA_ALUMNO_BY_DNI = "update alumnos set grupo = ? where dni = ?";
	//	
//		// DELETES
	//
//		public static final String DELETE_ALL_ALUMNOS = "delete from alumnos";
//		public static final String DELETE_ALL_NOTAS = "delete from calificaciones";

		public static final String SELECT_DESC_TIPO_VIAJE = "SELECT tv.desc_tipo_viaje FROM viaje v JOIN tipo_viaje tv ON v.id_tipo_viaje = tv.id_tipo_viaje";

		public static final String SELECT_EVENTOS_TABLA_IDA = "SELECT nom_evento,tipo,fec_ida,precio from evento where id_agencia= ?";
}