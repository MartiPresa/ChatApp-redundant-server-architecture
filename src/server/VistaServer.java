package server;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class VistaServer extends JFrame implements IVistaServer{

	private JPanel contentPane;
	private JButton btnClose;
	private ActionListener actionListener;
	ControladorVistaServer controlador;
	JTextArea textAreaLista;
	private JTextArea textAreaMensajes;
	public static int nro = 0;
	private JTextField textFieldPuerto;
	private JButton btnCreaNuevoServer;
	//private Server server;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaServer frame = new VistaServer();
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
	public VistaServer() {
		this.setTitle("SERVIDOR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setVisible(true);
		setContentPane(contentPane);
		
		textAreaLista = new JTextArea();
		textAreaLista.setEditable(false);
		
		btnClose = new JButton("Cerrar servidor");
		btnClose.setActionCommand("CLOSE_SERVER");
		
		textAreaMensajes = new JTextArea();
		textAreaMensajes.setEditable(false);
		
		textFieldPuerto = new JTextField();
		textFieldPuerto.setColumns(10);
		
		 btnCreaNuevoServer = new JButton("Crear Servidor");
		 btnCreaNuevoServer.setActionCommand("CREAR_SERVER");
		btnCreaNuevoServer.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textAreaLista, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(textAreaMensajes, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(btnClose)
							.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
							.addComponent(btnCreaNuevoServer)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPuerto, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textAreaMensajes, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textAreaLista, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(btnClose))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldPuerto, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCreaNuevoServer, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		controlador = new ControladorVistaServer(this);
	}

	
	public JTextArea getTextArea() {
		return textAreaLista;
	}
	
	
	
	public JTextArea getTextAreaMensajes() {
		return textAreaMensajes;
	}

	@Override
	public void addActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
		this.btnCreaNuevoServer.addActionListener(actionListener);
		this.textFieldPuerto.addActionListener(actionListener);
		this.btnClose.addActionListener(actionListener);
	}

	@Override
	public void mostrarVentana(boolean cond) {
		// TODO Auto-generated method stub
		
	}
	
	public void setTitle () {
		nro++;
		this.setTitle("SERVIDOR "+ nro);
	}

	public int getNro() {
		return nro;
	}


	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getBtnClose() {
		return btnClose;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public JTextArea getTextAreaLista() {
		return textAreaLista;
	}

	public JTextField getTextFieldPuerto() {
		return textFieldPuerto;
	}

	public void setTextFieldPuerto(JTextField textFieldPuerto) {
		this.textFieldPuerto = textFieldPuerto;
	}
	
	
}
