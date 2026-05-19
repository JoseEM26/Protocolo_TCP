package cibertec.edu.client;

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

public class FrmChatClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMensaje;
	private JButton btnEnviar;
	private JTextArea txtConversacion;
	private JButton btnSalir;

	/**
	 * Método main para ejecutar el formulario de manera independiente
	 * y visualizarlo. Útil durante el desarrollo de la interfaz gráfica.
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmChatClient frame = new FrmChatClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor que inicializa y estructura la ventana gráfica del Cliente.
	 * Establece componentes básicos como el título, tamaño, layout principal 
	 * (BorderLayout) y agrega los botones, áreas de texto de la conversación 
	 * y el campo para ingresar nuevos mensajes.
	 * Create the frame.
	 */
	public FrmChatClient() {
		setTitle("Cliente");
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
	 * Devuelve la referencia al campo de texto donde el usuario escribe el mensaje.
	 * Permite leer el texto ingresado o asignarle un listener de acción.
	 */
	public JTextField getTxtMensaje() {
		return txtMensaje;
	}

	/**
	 * Devuelve el botón de envío de mensajes.
	 * Útil para asignarle su respectivo listener de acción (ActionListener).
	 */
	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	/**
	 * Agrega un nuevo mensaje al área de conversación (historial) del chat.
	 * Toma el texto existente, le adjunta el nuevo mensaje con un salto de línea
	 * y actualiza el componente gráfico. Luego limpia el campo de texto de entrada.
	 * 
	 * @param message el mensaje de texto a agregar a la interfaz.
	 */
	public void addMessage(String message) {
		String conversacion = txtConversacion.getText();
		conversacion = conversacion.concat(message).concat("\n");
		
		txtConversacion.setText(conversacion);
		txtMensaje.setText("");
	}

}
