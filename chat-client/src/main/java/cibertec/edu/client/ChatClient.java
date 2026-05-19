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
	
	public ChatClient() {
		setLookAndFeel();
		addEvents();
		initClient();
	}
	
	private void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addEvents() {
		frmChatClient = new FrmChatClient();
		frmChatClient.getTxtMensaje().addActionListener(e -> sendMessage());
		frmChatClient.getBtnEnviar().addActionListener(e -> sendMessage());
		
		frmChatClient.setVisible(true);
	}
	
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

	private void sendMessage() {
		String mensaje = frmChatClient.getTxtMensaje().getText().trim();
		
		if (!mensaje.isEmpty()) {
			mensaje = "Cliente: " + mensaje;
			frmChatClient.addMessage(mensaje);
			out.println(mensaje);
		}
	}
	
}
