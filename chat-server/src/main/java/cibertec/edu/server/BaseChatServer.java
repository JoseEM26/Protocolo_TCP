package cibertec.edu.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.UIManager;

public class BaseChatServer {

	private FrmChatServer frmChatServer;
	
	private BufferedReader in;
	private PrintWriter out;

	private int port = 5000;

	/**
	 * Constructor principal de la clase BaseChatServer.
	 * Se encarga de inicializar y configurar el servidor de chat paso a paso:
	 * 1. Establece el aspecto visual de la aplicación (Look and Feel).
	 * 2. Solicita y configura el puerto por el cual el servidor escuchará conexiones.
	 * 3. Añade los eventos a los componentes de la interfaz gráfica.
	 * 4. Inicializa el servidor para empezar a escuchar a los clientes.
	 */
	public BaseChatServer() {
		setLookAndFeel();
		setPort();
		addEvents();
		initServer();
	}
	
	/**
	 * Configura el aspecto visual de las ventanas de la aplicación.
	 * Utiliza el aspecto del sistema operativo subyacente para que la interfaz
	 * se vea nativa e integrada con el entorno del usuario.
	 */
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra un cuadro de diálogo de configuración para solicitar al usuario
	 * que introduzca el número de puerto en el que el servidor debe escuchar.
	 * Si el usuario cancela o cierra el diálogo, la aplicación se cierra.
	 * De lo contrario, se guarda el puerto ingresado.
	 */
	private void setPort() {
		DlgConf conf = new DlgConf();
		conf.setVisible(true);
	
		if (!conf.isAccept()) {
			System.exit(0);
		}
		
		port = conf.getPuerto();
		conf.dispose();
	}
	
	/**
	 * Instancia la interfaz gráfica principal del servidor y le añade los oyentes
	 * de eventos a sus componentes. Configura la acción de presionar "Enter" 
	 * en el campo de texto de mensajes y la acción de hacer clic en el botón "Enviar"
	 * para que ambas acciones ejecuten el método sendMessage().
	 * Finalmente, hace visible la ventana del servidor.
	 */
	private void addEvents() {
		frmChatServer = new FrmChatServer();
		frmChatServer.getTxtMensaje().addActionListener(e -> sendMessage());
		frmChatServer.getBtnEnviar().addActionListener(e -> sendMessage());
		
		frmChatServer.setVisible(true);
	}

	/**
	 * Inicia el servidor de sockets y comienza a escuchar conexiones entrantes.
	 * Abre un ServerSocket en el puerto configurado. Cuando un cliente se conecta,
	 * establece los flujos de entrada (para leer mensajes del cliente) y de 
	 * salida (para enviar mensajes al cliente). 
	 * Entra en un bucle infinito donde se queda esperando recibir mensajes del 
	 * cliente. Cuando recibe un mensaje, lo añade a la interfaz gráfica.
	 */
	@SuppressWarnings("resource")
	private void initServer() {
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Servidor iniciado en el puerto: " + port);

			Socket socket = serverSocket.accept();
			System.out.println("Client conectado");

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			String message;
			
			while (true) {
				message = in.readLine();
				
				if (message != null) {
					//System.out.println(message);
					frmChatServer.addMessage(message);
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
	 * Envía un mensaje desde el servidor hacia el cliente conectado.
	 * Obtiene el texto escrito en la caja de texto de la interfaz y si no está
	 * vacío, le añade el prefijo "Servidor: ", lo muestra en la propia interfaz
	 * del servidor, y luego lo transmite por el flujo de salida al cliente.
	 */
	private void sendMessage() {
		String mensaje = frmChatServer.getTxtMensaje().getText().trim();
		
		if (!mensaje.isEmpty()) {
			mensaje = "Servidor: " + mensaje;
			frmChatServer.addMessage(mensaje);
			out.println(mensaje);
		}
	}

}
