package cibertec.edu.server;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DlgConf extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JSpinner spnPuerto;
	private JButton okButton;
	private JButton cancelButton;
	
	private boolean accept;

	/**
	 * Inicia la ventana de configuración del puerto de manera independiente.
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgConf dialog = new DlgConf();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor que construye y configura el cuadro de diálogo (JDialog).
	 * Configura el comportamiento modal, sus dimensiones y los componentes 
	 * internos (etiquetas, spinner para seleccionar el puerto y los botones OK/Cancel).
	 * Create the dialog.
	 */
	public DlgConf() {
		setModal(true);
		setBounds(100, 100, 450, 118);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 194, 46, 0 };
		gbl_contentPanel.rowHeights = new int[] { 14, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);

		JLabel lblNewLabel = new JLabel("Puerto: ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		contentPanel.add(lblNewLabel, gbc_lblNewLabel);

		spnPuerto = new JSpinner();
		spnPuerto.setModel(new SpinnerNumberModel(Integer.valueOf(5000), Integer.valueOf(1), null, Integer.valueOf(1)));
		GridBagConstraints gbc_spnPuerto = new GridBagConstraints();
		gbc_spnPuerto.fill = GridBagConstraints.HORIZONTAL;
		gbc_spnPuerto.gridx = 1;
		gbc_spnPuerto.gridy = 0;
		contentPanel.add(spnPuerto, gbc_spnPuerto);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(e -> cancel());
	}
	
	/**
	 * Método invocado al presionar el botón "OK".
	 * Marca la variable 'accept' como true para indicar que el usuario
	 * confirmó la selección y oculta el cuadro de diálogo.
	 */
	private void ok() {
		accept = true;
		setVisible(false);
	}
	
	/**
	 * Método invocado al presionar el botón "Cancel".
	 * Marca la variable 'accept' como false para indicar que el usuario
	 * canceló la operación y oculta el cuadro de diálogo sin guardar el puerto.
	 */
	private void cancel() {
		accept = false;
		setVisible(false);
	}
	
	/**
	 * Retorna si el usuario aceptó la configuración (presionó OK).
	 *
	 * @return true si el usuario aceptó, false en caso contrario.
	 */
	public boolean isAccept() {
		return accept;
	}
	
	/**
	 * Retorna el puerto que fue seleccionado mediante el componente JSpinner.
	 * 
	 * @return El número de puerto configurado.
	 */
	public int getPuerto() {
		return (Integer) spnPuerto.getValue();
	}

}
