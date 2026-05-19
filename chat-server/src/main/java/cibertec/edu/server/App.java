package cibertec.edu.server;

/**
 * Hello world!
 *
 */
public class App {
	
	/**
	 * Punto de entrada principal de la aplicación del Servidor de Chat.
	 * Al ejecutarse, instancia la clase BaseChatServer que a su vez se encarga
	 * de toda la inicialización (interfaz gráfica y sockets).
	 * 
	 * @param args Argumentos de la línea de comandos (no se utilizan).
	 */
	public static void main(String[] args) {
		new BaseChatServer();
	}
}
