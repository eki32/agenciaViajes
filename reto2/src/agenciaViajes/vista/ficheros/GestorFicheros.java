package agenciaViajes.vista.ficheros;

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Clase interna para la gestión de ficheros. Proporciona métodos para guardar y
 * leer ficheros.
 */
public class GestorFicheros {
	// Lo copiamos aqui porque nos sirve para guardar y para leer
	public static final String NOMBRE_FICHERO = "Oferta Cliente.txt";
	public static final String RUTA_FICHERO = "Ofertas";
	File f = new File(RUTA_FICHERO + "/" + NOMBRE_FICHERO);

	/**
	 * Guarda el contenido en un fichero.
	 *
	 * @param ruta      - Ruta del fichero.
	 * @param contenido - Contenido a guardar.
	 */
	public void guardarFichero(String rutaFichero, String texto) {
		System.out.println("Vamos a escribir en el fichero " + NOMBRE_FICHERO + " en la ruta " + RUTA_FICHERO);

		// Si no existe la carpeta, la crea
		File carpeta = new File(RUTA_FICHERO);
		if (!carpeta.exists()) {
			// crea una carpeta
			carpeta.mkdirs();
		}
		File f = new File(RUTA_FICHERO + "/" + NOMBRE_FICHERO);
		// Preparamos las clases necesarias para escribir un fichero
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;

		try {
			// Asignamos el fichero que vamos a escribir
			fileWriter = new FileWriter(f); // f: porque se llama así donde lo hemos declarado

			// Si preferimos que el fichero se actualice a final...
			// fileWriter = new FileWriter(RUTA_FICHERO + NOMBRE_FICHERO, true);
			// fileWriter = new FileWriter(f, true);

			printWriter = new PrintWriter(fileWriter);

			// Lo mandamos al fichero
			printWriter.println(texto);
			System.out.println("El fichero " + NOMBRE_FICHERO + " se ha guardado correctamente");

		} catch (IOException e) {
			System.out.println("IOException - Error de escritura en el fichero " + RUTA_FICHERO + NOMBRE_FICHERO);
		} finally {
			if (null != printWriter)
				printWriter.close();
			try {
				if (null != fileWriter)
					fileWriter.close();
			} catch (IOException e) {
				// Nos da igual
			}
		}
	}

	/*
	 * Lee todo el contenido de un fichero y lo imprime en la consola.
	 */
	public String leerTodoFichero() {
		String ret = null;
		System.out.println("Vamos leer el fichero " + NOMBRE_FICHERO + " en la ruta " + RUTA_FICHERO);

		// Preparamos las clases necesarias para leer un fichero
		File file = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		// Asignamos el fichero que queremos leer
		file = new File(RUTA_FICHERO + "/" + NOMBRE_FICHERO);

		try {
			// Preparamos el lector
			fileReader = new FileReader(file);

			// Preparamos el buffer de lectura
			bufferedReader = new BufferedReader(fileReader);

			// Leemos linea a linea
			String linea;
			while ((linea = bufferedReader.readLine()) != null) {
				// Imprime linea a linea
				// System.out.println(linea);

				// Devuelve toda la línea y podriamos trabajar con ello
				ret = null == ret ? "" : ret;
				ret += linea + "\r\n";

			}

		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException - El fichero " + RUTA_FICHERO + NOMBRE_FICHERO + " no existe");
		} catch (IOException e) {
			System.out.println("IOException - Error de lectura del fichero " + RUTA_FICHERO + NOMBRE_FICHERO);
		} finally {
			try {
				if (null != bufferedReader)
					bufferedReader.close();
			} catch (IOException e) {
				// Nos da igual
			}
			try {
				fileReader.close();
			} catch (IOException e) {
				// Nos da igual
			}
		}

		return ret;
	}

	public void anyadirTextoAlFichero(String texto) {
		System.out.println("Vamos a escribir en el fichero " + NOMBRE_FICHERO + " en la ruta " + RUTA_FICHERO);

		// Preparamos las clases necesarias para escibir un fichero
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;

		try {
			// Asignamos el fichero que vamos a escribir
			// fileWrite = new FileWriter (RUTA_FICHERO + NOMBRE_FICHERO);
			// Si preferimos que el fichero se actualice al final...
			fileWriter = new FileWriter(RUTA_FICHERO + "/" + NOMBRE_FICHERO, true);
			printWriter = new PrintWriter(fileWriter);
			// Lo mandamos al fichero
			printWriter.println(texto);
			System.out.println("El fichero " + NOMBRE_FICHERO + " se ha guardado correctamente");
			System.out.println("");
		} catch (IOException e) {
			System.out.println("IOException - Error de escritura en el fichero " + RUTA_FICHERO + NOMBRE_FICHERO);
		} finally {
			printWriter.close();
			try {
				fileWriter.close();
			} catch (IOException e) {
				// Nos da igual
			}
		}
	}

}
