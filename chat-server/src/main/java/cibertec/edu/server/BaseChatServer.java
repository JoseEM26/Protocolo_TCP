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

	public BaseChatServer() {
		setLookAndFeel();
		setPort();
		addEvents();
		initServer();
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setPort() {
		DlgConf conf = new DlgConf();
		conf.setVisible(true);
	
		if (!conf.isAccept()) {
			System.exit(0);
		}
		
		port = conf.getPuerto();
		conf.dispose();
	}
	
	private void addEvents() {
		frmChatServer = new FrmChatServer();
		frmChatServer.getTxtMensaje().addActionListener(e -> sendMessage());
		frmChatServer.getBtnEnviar().addActionListener(e -> sendMessage());
		
		frmChatServer.setVisible(true);
	}

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
	
	private void sendMessage() {
		String mensaje = frmChatServer.getTxtMensaje().getText().trim();
		
		if (!mensaje.isEmpty()) {
			mensaje = "Servidor: " + mensaje;
			frmChatServer.addMessage(mensaje);
			out.println(mensaje);
		}
	}

}
