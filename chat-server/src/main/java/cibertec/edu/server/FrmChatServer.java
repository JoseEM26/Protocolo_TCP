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

	public JTextField getTxtMensaje() {
		return txtMensaje;
	}

	public JButton getBtnEnviar() {
		return btnEnviar;
	}

	public void addMessage(String message) {
		String conversacion = txtConversacion.getText();
		conversacion = conversacion.concat(message).concat("\n");
		
		txtConversacion.setText(conversacion);
		txtMensaje.setText("");
	}

}
