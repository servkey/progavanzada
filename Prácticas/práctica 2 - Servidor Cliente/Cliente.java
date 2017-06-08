import java.net.Socket;
import java.net.ServerSocket;
import java.net.ConnectException;
import java.util.Calendar;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.IOException;
import java.util.Scanner;

public class Cliente{
	public static void main(String args[]){
		System.out.println("Ejecutando cliente...");
		try{
			Calendar calendar = Calendar.getInstance();

			int hora = calendar.get(Calendar.HOUR_OF_DAY);
			int minutos = calendar.get(Calendar.MINUTE);
			int segundos = calendar.get(Calendar.SECOND);

			System.out.println("La hora es: " + hora + ":" + minutos + ":" + segundos);

			Socket cliente = new Socket(args[0], Integer.parseInt(args[1]));
			System.out.println("Conexión exitosa...");	
			InputStream is = cliente.getInputStream();
			OutputStream os = cliente.getOutputStream();
		
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			PrintStream ps = new PrintStream(os);
	
			Scanner in = new Scanner(System.in);
		
			while (true){
				System.out.print("Escribe mensaje: ");
				String mensaje = in.nextLine();
				if (mensaje.equals("fin")){
					break;
				}
				System.out.println("Enviando mensaje....");	
				ps.println(mensaje);

				System.out.println("Leyendo mensaje del servidor...:");				
				String mensajeLeido = br.readLine();
				System.out.println(mensajeLeido);						
			}	
			is.close();				
			cliente.close();
			os.close();
		}catch(ConnectException e){
			System.out.println("Error en la conexión!");
		}catch(IOException e){
			System.out.println("Erro en conexión!");
			e.printStackTrace();
		}
		
	}
}
