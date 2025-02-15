package agenciaViajes.bbdd.gestores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import agenciaViajes.bbdd.config.DBUtils;
import agenciaViajes.bbdd.config.SQLQuerys;
import agenciaViajes.bbdd.entidad.Pais;

/**
 * GestorPais gestiona la recuperación de datos de países.
 */
public class GestorPais {

	/**
	 * Busca todos los países en la base de datos.
	 * 
	 * @return Lista de objetos de tipo Pais.
	 */
	public ArrayList<Pais> buscarPaises() {

		ArrayList<Pais> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_TODOS_PAISES);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setString(1, nombrePais);
			resultSet = preparedStatement.executeQuery();

			// Solo entra una vez: if (resultSet.next()) {
			while (resultSet.next()) {
				if (null == ret)
					ret = new ArrayList<Pais>();

				Pais nombrePais = new Pais();
				nombrePais.setNom_pais(resultSet.getString("nom_pais"));
				ret.add(nombrePais);
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
