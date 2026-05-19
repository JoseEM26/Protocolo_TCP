package cibertec.edu.server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmChatServer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMensaje;
	private JButton btnEnviar;
	private JTextArea txtConversacion;
	private JButton btnSalir;

	/**
	 * Punto de entrada para lanzar independientemente la interfaz visual.
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmChatServer frame = new FrmChatServer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor que crea el frame principal de la ventana del Servidor.
	 * Configura el título, dimensiones, la disposición (layout) y los 
	 * diversos componentes gráficos (botones, áreas de texto, paneles) 
	 * necesarios para que el usuario pueda interactuar con el chat.
	 * Create the frame.
	 */
	public FrmChatServer() {
		setTitle("Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.TRAILING);
		contentPane.add(panel, BorderLayout.SOUTH);

		btnSalir = new JButton("Salir");
		panel.add(btnSalir);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEADING);
		panel_1.add(panel_2, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Mensaje");
		panel_2.add(lblNewLabel);

		txtMensaje = new JTextField();
		panel_2.add(txtMensaje);
		txtMensaje.setColumns(10);

		btnEnviar = new JButton("Enviar");
		panel_2.add(btnEnviar);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		txtConversacion = new JTextArea();
		txtConversacion.setEditable(false);
		scrollPane.setViewportView(txtConversacion);
	}

	/**
	 * Retorna el campo de texto donde el usuario escribe los mensajes.
	 * Útil para poder leer su valor o para asignarle eventos desde otras clases.
	 */
	public JTextField getTxtMensaje() {
		return txtMensaje;
	}

	/**
	 * Retorna el botón que se utiliza para enviar los mensajes.
	 * Útil para poder asignarle listeners u otras configuraciones.
	 */
	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	/**
	 * Recibe un mensaje de texto como parámetro y lo añade al final del área
	 * de historial de la conversación. Luego de añadir el texto, se limpia
	 * la caja de texto para que quede lista para escribir un nuevo mensaje.
	 *
	 * @param message el mensaje a agregar al área de la conversación.
	 */
	public void addMessage(String message) {
		String conversacion = txtConversacion.getText();
		conversacion = conversacion.concat(message).concat("\n");
		
		txtConversacion.setText(conversacion);
		txtMensaje.setText("");
	}

}
