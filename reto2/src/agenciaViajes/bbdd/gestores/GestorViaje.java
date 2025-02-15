package agenciaViajes.bbdd.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import agenciaViajes.bbdd.config.DBUtils;
import agenciaViajes.bbdd.config.SQLQuerys;
import agenciaViajes.bbdd.entidad.Evento;
import agenciaViajes.bbdd.entidad.Viaje;

/**
 * administra la información de los viajes en la base de datos.
 */
public class GestorViaje {

	 

	/**
	 * Guarda un nuevo viaje en la base de datos.
	 * 
	 * @param viaje Objeto de tipo Viaje con la información a guardar.
	 */
	public void guardarViaje(Viaje viaje) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int idViaje;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			preparedStatement = connection.prepareStatement(SQLQuerys.INSERT_NEW_VIAJE);

			preparedStatement.setString(1, viaje.getNom_viaje());
			preparedStatement.setDate(2, new java.sql.Date(viaje.getFec_ini().getTime()));
			preparedStatement.setDate(3, new java.sql.Date(viaje.getFec_fin().getTime()));
			preparedStatement.setInt(4, viaje.getDuracion());
			preparedStatement.setString(5, viaje.getPais());
			preparedStatement.setString(6, viaje.getDesc_viaje());
			preparedStatement.setString(7, viaje.getServ_no_inc());
			preparedStatement.setInt(8, viaje.getId_agencia());
			preparedStatement.setString(9, viaje.getId_tipo_viaje());

			preparedStatement.executeUpdate();

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				idViaje = resultSet.getInt(1);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
	

	}

	/**
	 * Busca los viajes asociados a una agencia.
	 * 
	 * @param idAgencia Identificador de la agencia.
	 * @return Lista de viajes asociados.
	 */
	public ArrayList<Viaje> buscarViajes(int idAgencia) {

		ArrayList<Viaje> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_VIAJE_TABLA);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setInt(1,idAgencia);
			resultSet = preparedStatement.executeQuery();

			// Solo entra una vez: if (resultSet.next()) {
			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Viaje>();

				Viaje nombreViaje = new Viaje();

				nombreViaje.setId_viaje(resultSet.getInt("id_viaje"));
				nombreViaje.setNom_viaje(resultSet.getString("nom_viaje"));
				nombreViaje.setTipo(resultSet.getString("tipo"));
				nombreViaje.setFec_ini(resultSet.getDate("fec_ini"));
				nombreViaje.setFec_fin(resultSet.getDate("fec_fin"));
				nombreViaje.setDuracion(resultSet.getInt("duracion"));
				nombreViaje.setPais(resultSet.getString("pais"));

				ret.add(nombreViaje);
			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	/**
	 * Busca el identificador de un tipo de viaje de luna de miel.
	 * 
	 * @return Identificador del tipo de viaje.
	 */
	public String buscarLunaMiel() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_LUNAMIEL);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_viaje"));

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	/**
	 * Busca el identificador de un tipo de viaje senior.
	 * 
	 * @return Identificador del tipo de viaje.
	 */
	public String buscarSenior() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_SENIOR);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_viaje"));

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	/**
	 * Busca el identificador de un tipo de viaje para grupos.
	 * 
	 * @return Identificador del tipo de viaje.
	 */
	public String buscarGrupos() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_GRUPOS);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_viaje"));

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	/**
	 * Busca el identificador de un tipo de viaje grande.
	 * 
	 * @return Identificador del tipo de viaje.
	 */
	public String buscarViajesGrandes() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_VIAJES_GRANDES);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_viaje"));

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	/**
	 * Busca el identificador de un tipo de escapada.
	 * 
	 * @return Identificador del tipo de viaje.
	 */
	public String buscarEscapadas() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_ESCAPADAS);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_viaje"));

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	/**
	 * Busca el identificador de un tipo de viaje familiar.
	 * 
	 * @return Identificador del tipo de viaje.
	 */
	public String buscarFamilias() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_FAMILIAS);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_viaje"));

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	/**
	 * Busca la descripción de un tipo de viaje.
	 * 
	 * @return Descripción del tipo de viaje.
	 */
	public String buscarTipo() {

		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_DESC_TIPO_VIAJE);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("desc_tipo_viaje"));

			}
		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return ret;
	}

	/**
	 * Elimina un viaje de la base de datos por su ID.
	 * 
	 * @param idViaje Identificador del viaje.
	 * @return true si el viaje fue eliminado, false en caso contrario.
	 */
	public boolean eliminarViaje(int idViaje) {

		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "DELETE FROM viaje WHERE id_viaje = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idViaje);

			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0; // Devuelve true si se eliminó el viaje

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				// No hace falta
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No hace falta
			}
		}
		return false;
	}

//	public void actualizarViajeEnBD(int idViaje, int selectcolumn, Object newValue) {
//		// Definir las columnas en la base de datos
//		String[] columnas = { "id_viaje", "nom_viaje", "desc_tipo_viaje", "fec_ini", "fec_fin", "duracion", "pais" };
//		if (selectcolumn < 1 || selectcolumn >= columnas.length) {
//			System.out.println("❌ Error: Columna no válida.");
//			return;
//		}
//
//		String columnaBD = columnas[selectcolumn]; // Obtener el nombre real de la columna en la BD
//
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//
//		try {
//			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
//			String sql = "UPDATE viaje SET " + columnaBD + " = ? WHERE id_viaje = ?";
//			preparedStatement = connection.prepareStatement(sql);
//
//			// Detectar el tipo de dato y asignarlo correctamente
//			if (newValue instanceof String) {
//				preparedStatement.setString(1, (String) newValue);
//			} else if (newValue instanceof Integer) {
//				preparedStatement.setInt(1, (Integer) newValue);
//			} else if (newValue instanceof java.util.Date) {
//				preparedStatement.setDate(1, new java.sql.Date(((java.util.Date) newValue).getTime()));
//			} else {
//				preparedStatement.setObject(1, newValue);
//			}
//
//			preparedStatement.setInt(2, idViaje);
//			int rowsUpdated = preparedStatement.executeUpdate();
//
//			if (rowsUpdated > 0) {
//				System.out.println("✅ Viaje actualizado correctamente en la BD.");
//			} else {
//				System.out.println("⚠ No se encontró el viaje con ID: " + idViaje);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("❌ Error al actualizar viaje en la BD: " + e.getMessage());
//		} finally {
//			try {
//				if (preparedStatement != null)
//					preparedStatement.close();
//				if (connection != null)
//					connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
