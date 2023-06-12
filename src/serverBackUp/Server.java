package serverBackUp;

import java.io.DataInputStream;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import serverBackUp.DataCliente;


public class Server extends Thread {

	private ServerSocket serverSocket;
	public  int puerto;
	private HashMap<String, DataCliente> clientes = new HashMap<>();
	private HashMap<String, String> chats = new HashMap<>();
	private ArrayList<DataCliente> listaClientes = new ArrayList<DataCliente>();
	private ControladorVistaServerRespaldo controlador;
	private Sincronizacion sincronizacion = new Sincronizacion(chats, this);
	
	public static boolean terminar = false;

	
//	public void iniciaServer(String text) {
//		this.puerto = Integer.parseInt(text);
//		try {
//			this.serverSocket = new ServerSocket(puerto);
//			this.controlador.setServer(this);
//			this.Registrar();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	

	public Server(String text, ControladorVistaServerRespaldo cont) {
		this.puerto = Integer.parseInt(text);
		try {
			this.serverSocket = new ServerSocket(puerto);
			this.controlador=cont;
			this.controlador.setServer(this);
			this.sincronizacion.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void run(){
		Socket s = null;
		String nickname;
		String nicknameReceptor;
		DataCliente dataCliente;
		Object object;
		char bandera;

		try {
			while (this.terminar == false) {
				s = serverSocket.accept();
				this.controlador.appendMensajes(s.getPort()+"");
				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());

				nickname = dis.readUTF();
				bandera = nickname.charAt(0);
				nickname = nickname.substring(1);

				if (this.clientes.containsKey(nickname) == false) {
					dataCliente = new DataCliente(s, nickname, dis, dos);
					this.clientes.put(nickname, dataCliente);
					this.listaClientes.add(dataCliente);

					Conection conection = new Conection(s, dataCliente, this.clientes, dis, dos);
					conection.setCont(controlador);
					conection.start();
					dos.writeUTF("1REGISTRADOCORRECTAMENTE");
					controlador.appendListaConectados(dataCliente.toString()+" dos: "+dataCliente.getDos());
				} else {

					dos.writeUTF("1USERREGISTRADO");
				}
			}
//			System.out.println("Termina server");
//			this.serverSocket.close();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
//	public void Registrar() throws IOException {
//
//		Socket s = null;
//		String nickname;
//		String nicknameReceptor;
//		DataCliente dataCliente;
//		Object object;
//		char bandera;
//
//		try {
//			while (this.terminar == false) {
//				s = serverSocket.accept();
//
//				DataInputStream dis = new DataInputStream(s.getInputStream());
//				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
//
//				nickname = dis.readUTF();
//				bandera = nickname.charAt(0);
//				nickname = nickname.substring(1);
//
//				if (this.clientes.containsKey(nickname) == false) {
//					dataCliente = new DataCliente(s, nickname, dis, dos);
//					this.clientes.put(nickname, dataCliente);
//					this.lista.add(dataCliente);
//
//					Conection conection = new Conection(s, dataCliente, this.clientes, dis, dos);
//					conection.setCont(controlador);
//					conection.start();
//					dos.writeUTF("1REGISTRADOCORRECTAMENTE");
//					controlador.appendListaConectados(dataCliente.toString());
//				} else {
//
//					dos.writeUTF("1USERREGISTRADO");
//				}
//			}
//
//		} catch (SocketException e) {
//			e.printStackTrace();
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//	}

	public ArrayList<DataCliente> getLista() {
		return listaClientes;
	}

	public void setLista(ArrayList<DataCliente> lista) {
		this.listaClientes = lista;
	}

	public void closeServer() throws IOException {	// podriamos cerrar el socket de conexion con otros servidores tmb
		this.terminar = true;
		//this.serverSocket.close();
		
//		for(int i = 0; i < this.clientes.size(); i++) {
//			this.listaClientes.get(i).getSocket().close();
//		}
		
	}

	public ControladorVistaServerRespaldo getControlador() {
		return controlador;
	}

	public void setControlador(ControladorVistaServerRespaldo controlador) {
		this.controlador = controlador;
	}

	
}