package agenciaViajes.bbdd.gestores;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import agenciaViajes.bbdd.config.DBUtils;
import agenciaViajes.bbdd.config.SQLQuerys;
import agenciaViajes.bbdd.entidad.Evento;

/**
 * Clase GestorEventos que gestiona las operaciones relacionadas con eventos en
 * la base de datos.
 */
public class GestorEventos {

	/**
	 * Guarda un nuevo evento en la base de datos.
	 * 
	 * @param evento Objeto Evento que se desea guardar.
	 */
	public void guardarEvento(Evento evento) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "INSERT INTO evento (nom_evento, tipo, trayecto, ae_origen, ae_destino, fec_ida, cod_vuelo_ida, aerolinea_ida, precio_vuelo,hora_ida, duracion_ida, id_viaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, evento.getNom_evento());
			preparedStatement.setString(2, evento.getTipo());
			preparedStatement.setString(3, evento.getTrayecto());
			preparedStatement.setString(4, evento.getAe_origen());
			preparedStatement.setString(5, evento.getAe_destino());
			preparedStatement.setDate(6, new java.sql.Date(evento.getFec_ida().getTime()));
			preparedStatement.setInt(7, evento.getCod_vuelo_ida());
			preparedStatement.setString(8, evento.getAerolinea_ida());
			preparedStatement.setDouble(9, evento.getPrecio());
			preparedStatement.setTime(10, java.sql.Time.valueOf(evento.getHora_ida()));
			preparedStatement.setString(11, evento.getDuracion_ida());
			preparedStatement.setInt(12, evento.getId_viaje());

			preparedStatement.executeUpdate();

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
			} catch (SQLException e) {

				// No hace falta
			}
		}
	}

	public String generarOferta(int idViaje) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		StringBuilder ofertaTexto = new StringBuilder();

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "SELECT v.id_viaje, a.nom_agencia, v.nom_viaje, "
					+ "GROUP_CONCAT(DISTINCT e.nom_evento ORDER BY e.nom_evento SEPARATOR ', ') AS Eventos, "
					+ "SUM(e.precio) AS PrecioTotal " + "FROM agencia a "
					+ "JOIN viaje v ON a.id_agencia = v.id_agencia " + "JOIN evento e ON v.id_viaje = e.id_viaje "
					+ "WHERE v.id_viaje = ? " + "GROUP BY v.id_viaje, a.nom_agencia, v.nom_viaje";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idViaje);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				ofertaTexto.append("ID Viaje: ").append(resultSet.getInt("id_viaje")).append("\n");
				ofertaTexto.append("Agencia: ").append(resultSet.getString("nom_agencia")).append("\n");
				ofertaTexto.append("Nombre Viaje: ").append(resultSet.getString("nom_viaje")).append("\n");
				ofertaTexto.append("Eventos: ").append(resultSet.getString("Eventos")).append("\n");
				ofertaTexto.append("Precio Total: ").append(resultSet.getDouble("PrecioTotal")).append("\n");
			} else {
				ofertaTexto.append("No se encontraron datos para el ID de viaje proporcionado.");
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			ofertaTexto.append("Error al generar la oferta: ").append(e.getMessage());
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ofertaTexto.toString();
	}

//	    Connection connection = null;
//	    PreparedStatement preparedStatement = null;
//	    ResultSet resultSet = null;
//	    String ret = null;
//
//	    try {
//	        Class.forName(DBUtils.DRIVER);
//	        connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
//
//	        String sql = "SELECT v.id_viaje, a.nom_agencia, v.nom_viaje, GROUP_CONCAT(DISTINCT e.nom_evento "
//	        		+ "ORDER BY e.nom_evento SEPARATOR ', ') AS Eventos, SUM(e.precio) AS PrecioTotal FROM agencia a JOIN "
//	        		+ "viaje v ON a.id_agencia = v.id_agencia JOIN evento e ON v.id_viaje = e.id_viaje WHERE v.id_viaje != 0 "
//	        		+ "GROUP BY v.id_viaje, a.nom_agencia, v.nom_viaje";
//
//	        preparedStatement = connection.prepareStatement(sql);
//	        resultSet = preparedStatement.executeQuery();
//
//	        try (FileWriter fichero = new FileWriter("informacion_viajes.txt")) {
//	            // Encabezados del fichero
//	            fichero.write("NombreAgencia;NombreViaje;Evento;PrecioTotal\n");
//
//	            while (resultSet.next()) {
//	                String nomAgencia = resultSet.getString("nom_agencia");
//	                String nomViaje = resultSet.getString("nom_viaje");
//	                String nomEvento = resultSet.getString("nom_evento");
//	                double precioTotal = resultSet.getDouble("PrecioTotal");
//
//	                // Escribir en el fichero
//	                fichero.write(String.format("%s;%s;%s;%f\n", nomAgencia, nomViaje, nomEvento, precioTotal));
//	            }
//
//	            System.out.println("Fichero generado correctamente.");
//	            ret = "Fichero generado correctamente.";
//	        }
//
//	    } catch (SQLException | IOException | ClassNotFoundException e) {
//	        System.err.println("Error al generar el fichero: " + e.getMessage());
//	        ret = "Error al generar el fichero: " + e.getMessage();
//	    } finally {
//	        try {
//	            if (resultSet != null) {
//	                resultSet.close();
//	            }
//	            if (preparedStatement != null) {
//	                preparedStatement.close();
//	            }
//	            if (connection != null) {
//	                connection.close();
//	            }
//	        } catch (SQLException ex) {
//	            System.err.println("Error al cerrar recursos: " + ex.getMessage());
//	        }
//	    }
//	    return ret;


	/**
	 * Elimina un evento de la base de datos.
	 * 
	 * @param idEvento ID del evento a eliminar.
	 * @return true si el evento fue eliminado con éxito, false en caso contrario.
	 */
	public boolean eliminarEventos(int idEvento) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "DELETE FROM evento where id_evento = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idEvento);

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

	/**
	 * Busca eventos asociados a un viaje en la base de datos.
	 * 
	 * @param idViaje ID del viaje del cual se desean buscar eventos.
	 * @return Una lista de eventos encontrados.
	 */
	public ArrayList<Evento> buscarEventos(int idViaje) {

		ArrayList<Evento> ret = new ArrayList<>();
		;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		// Ejemplo de conexión y consulta
		try {

			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_EVENTOS_TABLA);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			preparedStatement.setInt(1, idViaje);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Evento evento = new Evento();
				evento.setId_evento(resultSet.getInt("id_evento"));
				evento.setNom_evento(resultSet.getString("nom_evento"));
				evento.setTipo(resultSet.getString("tipo"));
				evento.setFec_ida(resultSet.getDate("fec_ida"));
				evento.setPrecio(resultSet.getDouble("precio"));
				ret.add(evento);
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
	 * Busca el ID de un viaje a partir de un evento.
	 * 
	 * @return ID del viaje o -1 si no se encuentra.
	 */
	public int buscarIdViajePorEvento() {

		ArrayList<Evento> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		int idViaje = -1;
		try {
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "SELECT id_viaje FROM eventos WHERE nombre = ?";
			preparedStatement = connection.prepareStatement(sql);
			// preparedStatement.setString(1, nombreEvento);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				idViaje = resultSet.getInt("id_viaje");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idViaje;
	}

	/**
	 * Borra un evento asociado a un viaje por su ID de viaje.
	 * 
	 * @param idViaje Identificador del viaje.
	 * @return true si se eliminó el evento, false en caso contrario.
	 */
	public boolean borrarEventoPorIdViaje(int idViaje) {

		ArrayList<Evento> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "DELETE FROM eventos WHERE id_viaje = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, idViaje);
			int filasAfectadas = preparedStatement.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Muestra los eventos de ida relacionados con una agencia.
	 * 
	 * @param idaAgencia Identificador de la agencia.
	 * @return Lista de eventos de ida.
	 */
	public ArrayList<Evento> mostrarEventoIda(int idaAgencia) {

		ArrayList<Evento> ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		// Ejemplo de conexión y consulta
		try {

			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_EVENTOS_TABLA_IDA);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setString(1, nombrePais);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				if (null == ret) {
					ret = new ArrayList<Evento>();

					Evento evento = new Evento();

					evento.setNom_evento(resultSet.getString("nom_evento"));
					evento.setTipo(resultSet.getString("tipo"));
					evento.setFec_ida(resultSet.getDate("fec_ida"));
					evento.setPrecio(resultSet.getDouble("precio"));

					ret.add(evento);
				}
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
	 * Guarda un evento de vuelo de ida en la base de datos.
	 * 
	 * @param evento Objeto Evento con los detalles del vuelo.
	 */
	public void guardarEventoVueloIda(Evento evento) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "INSERT INTO evento (nom_evento, tipo, trayecto, ae_origen, ae_destino, fec_ida, cod_vuelo_ida, aerolinea_ida, precio, hora_ida, duracion_ida, id_viaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, evento.getNom_evento());
			preparedStatement.setString(2, evento.getTipo());
			preparedStatement.setString(3, evento.getTrayecto());
			preparedStatement.setString(4, evento.getAe_origen());
			preparedStatement.setString(5, evento.getAe_destino());
			preparedStatement.setDate(6, evento.getFec_ida());
			preparedStatement.setInt(7, evento.getCod_vuelo_ida());
			preparedStatement.setString(8, evento.getAerolinea_ida());
			preparedStatement.setDouble(9, evento.getPrecio());
			preparedStatement.setTime(10, Time.valueOf(evento.getHora_ida())); // Formato de hora correcto
			preparedStatement.setString(11, evento.getDuracion_ida());
			preparedStatement.setInt(12, evento.getId_viaje()); // Aquí se debe verificar que no sea -1

			preparedStatement.executeUpdate();
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
			} catch (SQLException e) {
				// No hace falta
			}
		}
	}

	/**
	 * Guarda un evento de alojamiento en la base de datos.
	 * 
	 * @param evento Objeto Evento con los detalles del alojamiento.
	 */
	public void guardarEventoAlojamiento(Evento evento) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "INSERT INTO evento (nom_evento, tipo, id_tipo_hab, precio, ciudad, fec_ida, fec_salida, id_viaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, evento.getNom_evento());
			preparedStatement.setString(2, evento.getTipo());
			preparedStatement.setString(3, evento.getId_tipo_hab());
			preparedStatement.setDouble(4, evento.getPrecio());
			preparedStatement.setString(5, evento.getCiudad());
			preparedStatement.setDate(6, evento.getFec_ida());
			preparedStatement.setDate(7, evento.getFec_salida());

			preparedStatement.setInt(8, evento.getId_viaje()); // Aquí se debe verificar que no sea -1

			preparedStatement.executeUpdate();
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
			} catch (SQLException e) {
				// No hace falta
			}
		}
	}

	/**
	 * Guarda un evento de actividad en la base de datos.
	 * 
	 * @param evento Objeto Evento con los detalles de la actividad.
	 */
	public void guardarEventoActividad(Evento evento) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "INSERT INTO evento (nom_evento, tipo, desc_act, precio, fec_ida, id_viaje) VALUES (?, ?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, evento.getNom_evento());
			preparedStatement.setString(2, evento.getTipo());
			preparedStatement.setString(3, evento.getDesc_act());
			preparedStatement.setDouble(4, evento.getPrecio());
			preparedStatement.setDate(5, evento.getFec_ida());

			preparedStatement.setInt(6, evento.getId_viaje()); // Aquí se debe verificar que no sea -1

			preparedStatement.executeUpdate();
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
			} catch (SQLException e) {
				// No hace falta
			}
		}
	}

	/**
	 * Guarda un evento de vuelo de ida y vuelta en la base de datos.
	 * 
	 * @param evento Objeto Evento con los detalles del vuelo.
	 */
	public void guardarEventoVueloIdaVuelta(Evento evento, int idViaje) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			String sql = "INSERT INTO evento (nom_evento, tipo, trayecto, ae_origen, ae_destino, fec_ida, fec_vuelta, cod_vuelo_ida, cod_vuelo_vuelta, aerolinea_ida, aerolinea_vuelta, hora_ida, hora_vuelta, precio, duracion_ida, duracion_vuelta, id_viaje) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, evento.getNom_evento());
			preparedStatement.setString(2, evento.getTipo());
			preparedStatement.setString(3, evento.getTrayecto());
			preparedStatement.setString(4, evento.getAe_origen());
			preparedStatement.setString(5, evento.getAe_destino());
			preparedStatement.setDate(6, evento.getFec_ida());
			preparedStatement.setDate(7, evento.getFec_vuelta());
			preparedStatement.setInt(8, evento.getCod_vuelo_ida());
			preparedStatement.setInt(9, evento.getCod_vuelo_vuelta());
			preparedStatement.setString(10, evento.getAerolinea_ida());
			preparedStatement.setString(11, evento.getAerolinea_vuelta());
			preparedStatement.setTime(12, Time.valueOf(evento.getHora_ida())); // Formato de hora correcto
			preparedStatement.setTime(13, Time.valueOf(evento.getHora_vuelta())); // Formato de hora correcto
			preparedStatement.setDouble(14, evento.getPrecio());
			preparedStatement.setString(15, evento.getDuracion_ida());
			preparedStatement.setString(16, evento.getDuracion_vuelta());
			preparedStatement.setInt(17, evento.getId_viaje()); // Aquí se debe verificar que no sea -1

			int filasInsertadas = preparedStatement.executeUpdate();
			if (filasInsertadas > 0) {
				System.out.println("✅ Evento guardado con éxito.");
			} else {
				System.out.println("⚠ No se insertó el evento.");
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
			} catch (SQLException e) {
				// No hace falta
			}
		}

	}

	/**
	 * Busca el ID de habitación de tipo doble.
	 * 
	 * @return ID de la habitación doble.
	 */
	public String buscarId_Doble() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_DOBLE);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_hab"));

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
	 * Busca el ID de habitación de tipo individual o doble.
	 * 
	 * @return ID de la habitación individual o doble.
	 */
	public String buscarId_IndividualDoble() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_INDIVIDUAL_DOBLE);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_hab"));

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
	 * Busca el ID de habitación de tipo individual.
	 * 
	 * @return ID de la habitación individual.
	 */
	public String buscarId_Individual() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_INDIVIDUAL);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_hab"));

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
	 * Busca el ID de habitación de tipo triple.
	 * 
	 * @return ID de la habitación triple.
	 */
	public String buscarId_Triple() {
		String ret = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			Class.forName(DBUtils.DRIVER);
			connection = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			preparedStatement = connection.prepareStatement(SQLQuerys.SELECT_ID_TRIPLE);
			// Enlaza cada ? de SQLQuerys con los parametros que se pasan
			// preparedStatement.setInt(1, id_tipo_agencia);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				// ret = new Agencia();
				ret = (resultSet.getString("id_tipo_hab"));

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

//	public void actualizarEventoEnBD() {
//		
//	}


}
