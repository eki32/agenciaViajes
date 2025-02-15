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
import agenciaViajes.bbdd.entidad.Agencia;
import agenciaViajes.bbdd.entidad.Pais;

/**
 * Clase GestorAgencia que gestiona las operaciones relacionadas con agencias en
 * la base de datos.
 */
public class GestorAgencia {

	/**
	 * Busca todas las agencias en la base de datos.
	 * 
	 * @return Una lista de agencias encontradas.
	 */
	public ArrayList<Agencia> buscarAgencias() {

		ArrayList<Agencia> ret = new ArrayList<>();
		;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		// Ejemplo de conexión y consulta
		try {

			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_NOMBRE_PASSWORD_COLOR_LOGO_AGENCIA);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Agencia agencia = new Agencia();
				agencia.setId_agencia(resultSet.getInt("id_agencia"));
				agencia.setNom_agencia(resultSet.getString("nom_agencia"));
				agencia.setPassword(resultSet.getString("password"));
				agencia.setColor(resultSet.getString("color"));
				agencia.setLogo(resultSet.getString("logo"));
				ret.add(agencia);
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
	 * Guarda una nueva agencia en la base de datos.
	 * 
	 * @param agencia Objeto Agencia que se desea guardar.
	 */
	public void guardarAgencia(Agencia agencia) {

		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			statement = connection.createStatement();

			String sql = SQLQuerys.INSERT_NEW_AGENCIA + agencia.getNom_agencia() + SQLQuerys.SEPARATOR
					+ agencia.getPassword() + SQLQuerys.SEPARATOR + agencia.getColor() + SQLQuerys.SEPARATOR
					+ agencia.getLogo() + SQLQuerys.SEPARATOR + agencia.getId_tipo_agen() + SQLQuerys.SEPARATOR
					+ agencia.getCod_emp() + SQLQuerys.END_BLOCK;

			statement.executeUpdate(sql);

		} catch (SQLException sqle) {
			System.out.println("Error con la BBDD - " + sqle.getMessage());
		} catch (Exception e) {
			System.out.println("Error generico - " + e.getMessage());
		} finally {
			// Cerramos al reves de como las abrimos
			try {
				if (statement != null)
					statement.close();
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
	}

	/**
	 * Busca una agencia por su ID.
	 * 
	 * @param id_agencia ID de la agencia a buscar.
	 * @return El ID de la agencia si se encuentra, de lo contrario, devuelve 0.
	 */
	public int buscarAgenciaPorId(int id_agencia) {

		int ret = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_AGENCIA);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setInt(1, id_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				ret = (resultSet.getInt("id_agencia"));

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
	 * Busca el ID de una agencia mayorista.
	 * 
	 * @return ID de la agencia mayorista como cadena de texto.
	 */
	public String buscarId_Mayorista() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_MAYORISTA);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_agen"));

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
	 * Busca el ID de una agencia minorista.
	 * 
	 * @return ID de la agencia minorista como cadena de texto.
	 */
	public String buscarId_Minorista() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_MINORISTA);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_agen"));

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
	 * Busca el ID de una agencia que sea tanto mayorista como minorista.
	 * 
	 * @return ID de la agencia mayorista-minorista como cadena de texto.
	 */
	public String buscarId_MayoMino() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_MAYOMINO);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_agen"));

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
	 * Busca el ID de una agencia con empleados entre 2 y 10.
	 * 
	 * @return Código de la empresa como cadena de texto.
	 */
	public String buscarId_2_10() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_2_10);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("cod_emp"));

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
	 * Busca el ID de una agencia con empleados entre 10 y 100.
	 * 
	 * @return Código de la empresa como cadena de texto.
	 */
	public String buscarId_10_100() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_10_100);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("cod_emp"));

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
	 * Busca el ID de una agencia con empleados entre 100 y 1000.
	 * 
	 * @return Código de la empresa como cadena de texto.
	 */
	public String buscarId_100_1000() {

		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_100_1000);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("cod_emp"));

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
}
