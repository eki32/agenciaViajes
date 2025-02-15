package agenciaViajes.bbdd.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import agenciaViajes.bbdd.config.DBUtils;
import agenciaViajes.bbdd.config.SQLQuerys;
import agenciaViajes.bbdd.entidad.Aerolinea;
import agenciaViajes.bbdd.entidad.Aeropuerto;

/**
 * Clase que gestiona las operaciones relacionadas con las aerolíneas en la base
 * de datos.
 */
public class GestorAerolinea {

	/**
	 * Busca todas las aerolíneas en la base de datos.
	 *
	 * @return Lista de objetos Aerolinea con los datos encontrados.
	 */
	public ArrayList<Aerolinea> buscarAerolinea() {

		ArrayList<Aerolinea> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_NOMBRE_COD_AEROLINEA);
			resultSet = preparedStatement.executeQuery();

			// Solo entra una vez: if (resultSet.next()) {
			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Aerolinea>();

				Aerolinea nombreAerolinea = new Aerolinea();
				nombreAerolinea.setCod_pais(resultSet.getString("cod_pais"));
				nombreAerolinea.setNom_ae(resultSet.getString("nom_ae"));

				ret.add(nombreAerolinea);
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
	 * Busca las aerolíneas asociadas a un aeropuerto específico por su código.
	 *
	 * @param codAeropuerto Código del aeropuerto.
	 * @return Lista de objetos Aerolinea asociados al aeropuerto.
	 */
	public ArrayList<Aerolinea> buscarAerolineasPorAeropuerto(String codAeropuerto) {
		ArrayList<Aerolinea> aerolineas = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String query = "SELECT NOMBRE, COD_PAIS FROM aerolinea "
					+ "WHERE COD_PAIS = (SELECT COD_PAIS FROM aeropuerto WHERE COD_AEROPUERTO = ?)";

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, codAeropuerto);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Aerolinea aerolinea = new Aerolinea();
				aerolinea.setNom_ae(resultSet.getString("NOMBRE"));
				aerolinea.setCod_pais(resultSet.getString("COD_PAIS"));
				aerolineas.add(aerolinea);
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Error con la BBDD - " + e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				// No es necesario manejar
			}
		}
		return aerolineas;
	}

}
