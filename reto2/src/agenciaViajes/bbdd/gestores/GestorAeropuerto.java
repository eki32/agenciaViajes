package agenciaViajes.bbdd.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import agenciaViajes.bbdd.config.DBUtils;
import agenciaViajes.bbdd.config.SQLQuerys;
import agenciaViajes.bbdd.entidad.Aeropuerto;

/**
 * Clase que gestiona las operaciones relacionadas con los aeropuertos en la
 * base de datos.
 */
public class GestorAeropuerto {

	/**
	 * Busca todos los aeropuertos en la base de datos.
	 *
	 * @return Lista de objetos Aeropuerto con los datos encontrados.
	 */
	public ArrayList<Aeropuerto> buscarAeropuerto() {

		ArrayList<Aeropuerto> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_AEROPUERTOS);
			resultSet = preparedStatement.executeQuery();

			// Solo entra una vez: if (resultSet.next()) {
			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Aeropuerto>();

				Aeropuerto nombreAeropuerto = new Aeropuerto();
				nombreAeropuerto.setCod_aeropuerto(resultSet.getString("cod_aeropuerto"));
				nombreAeropuerto.setCiudad(resultSet.getString("ciudad"));

				ret.add(nombreAeropuerto);
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
