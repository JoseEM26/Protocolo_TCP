package cibertec.edu.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.UIManager;

public class ChatClient {
	
	private FrmChatClient frmChatClient;

	private BufferedReader in;
	private PrintWriter out;
	
	private String server = "localhost";
	private int port = 5000;
	
	/**
	 * Constructor principal del Cliente de Chat.
	 * 1. Establece la apariencia de la aplicación.
	 * 2. Crea la interfaz gráfica y le asigna los eventos a los botones y campos de texto.
	 * 3. Inicializa la conexión del socket hacia el servidor.
	 */
	public ChatClient() {
		setLookAndFeel();
		addEvents();
		initClient();
	}
	
	/**
	 * Configura el aspecto visual de las ventanas del cliente,
	 * aplicando el estilo nativo del sistema operativo (Look and Feel).
	 */
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Crea la instancia del formulario del cliente (FrmChatClient) y asigna
	 * los eventos (listeners) necesarios para enviar mensajes al presionar "Enter"
	 * o hacer clic en el botón "Enviar".
	 * Posteriormente hace visible el formulario en pantalla.
	 */
	private void addEvents() {
		frmChatClient = new FrmChatClient();
		frmChatClient.getTxtMensaje().addActionListener(e -> sendMessage());
		frmChatClient.getBtnEnviar().addActionListener(e -> sendMessage());
		
		frmChatClient.setVisible(true);
	}
	
	/**
	 * Inicializa la conexión con el servidor. Crea el objeto Socket usando 
	 * el host (localhost) y el puerto (5000) predeterminados.
	 * Luego obtiene los flujos de entrada y salida para recibir y enviar datos.
	 * Finalmente, ingresa a un bucle infinito que escucha constantemente los mensajes
	 * entrantes enviados por el servidor, los cuales se añaden a la interfaz del cliente.
	 */
	private void initClient() {
		try (Socket socket = new Socket(server, port)){
			System.out.println("Conectado al servidor: " + server + ":" + port);
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
			String message;
			
			while (true) {
				message = in.readLine();
				
				if (message != null) {
					frmChatClient.addMessage(message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	/**
	 * Método para enviar un mensaje escrito por el usuario.
	 * Toma el texto del campo de texto de la interfaz y si no está en blanco,
	 * le añade el prefijo "Cliente: ", lo muestra en su propia pantalla y 
	 * lo escribe en el flujo de salida (PrintWriter) para enviarlo al servidor.
	 */
	private void sendMessage() {
		String mensaje = frmChatClient.getTxtMensaje().getText().trim();
		
		if (!mensaje.isEmpty()) {
			mensaje = "Cliente: " + mensaje;
			frmChatClient.addMessage(mensaje);
			out.println(mensaje);
		}
	}
	
}
