package cibertec.edu.client;

/**
 * Hello world!
 *
 */
public class App {
	
	/**
	 * Punto de entrada principal de la aplicación del Cliente de Chat.
	 * Al ejecutarse, instancia la clase ChatClient que se encarga de crear
	 * la interfaz y establecer la conexión por sockets con el servidor.
	 * 
	 * @param args Argumentos de la línea de comandos (no se utilizan).
	 */
	public static void main(String[] args) {
		new ChatClient();
	}
	
}
