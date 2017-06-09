import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import java.net.Socket;
import java.net.ConnectException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.IOException;

public class ClienteFrm extends JFrame{
	private JLabel lblIP;
	private JTextField txtIP;

	private JLabel lblPuerto;
	private JTextField txtPuerto;

	private JButton btnConectar;

	private JLabel lblMensaje;
	private JTextField txtMensaje;
	private JButton btnEnviarMensaje;
	
	
	private JLabel lblRespuesta;
	private JTextField txtRespuesta;

	private Socket cliente;
	private BufferedReader br; 
	private PrintStream ps;


	public ClienteFrm(){
		super("Cliente TCP");
		setSize(400,400);
		setLayout(new FlowLayout());

		lblIP = new JLabel("IP: ");
		txtIP = new JTextField(10);
		lblPuerto = new JLabel("Puerto:");
		txtPuerto = new JTextField(10);

		btnConectar = new JButton("Conectar");

		lblMensaje = new JLabel("Mensaje:");
		txtMensaje = new JTextField(10);
		btnEnviarMensaje = new JButton("Enviar Mensaje");
	
		lblRespuesta = new JLabel("Respuesta:");
		txtRespuesta = new JTextField(10);
		
		add(lblIP);
		add(txtIP);
		add(lblPuerto);
		add(txtPuerto);
		add(btnConectar);
		add(lblMensaje);
		add(txtMensaje);
		add(btnEnviarMensaje);
		add(lblRespuesta);
		add(txtRespuesta);

		addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}				
			}
		);


		btnConectar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Conectando");
				conectar();
			}	
		});

		btnEnviarMensaje.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Enviar mensaje...");
				enviar();				
			}	
		});


		setVisible(true);
		pack();
	}

	public void conectar(){
		try{
			cliente = new Socket(txtIP.getText(), Integer.parseInt(txtPuerto.getText()));
			JOptionPane.showMessageDialog(this, "Conexión exitosa");

			InputStream is = cliente.getInputStream();
			OutputStream os = cliente.getOutputStream();
		
			br = new BufferedReader(new InputStreamReader(is));
			ps = new PrintStream(os);

		}catch(IOException e){
			JOptionPane.showMessageDialog(this, "Error de conexión");
		}
	}

	public void enviar(){
		try{
			ps.println(txtMensaje.getText());
			ps.flush();
			txtRespuesta.setText(br.readLine());
		}catch(IOException e){
			JOptionPane.showMessageDialog(this, "Error al enviar mensaje");
		}
	}

	public static void main(String args[]){
		new ClienteFrm();	
	}
}