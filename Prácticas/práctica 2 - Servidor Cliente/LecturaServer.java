import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;

public class LecturaServer{
	public static void main(String args[]){
		try{
			//Creación del servidor en el puerto 1000
			ServerSocket server = new ServerSocket(1000);
			
			Socket socketCliente = server.accept();

			System.out.println("Leyendo algo: ");
			InputStreamReader isr = new InputStreamReader(socketCliente.getInputStream());
			BufferedReader br = new BufferedReader(isr);

			PrintWriter pw = new PrintWriter(socketCliente.getOutputStream());

			String s = br.readLine();
			while (s != null){
				System.out.println("Leí " + s);
				s = br.readLine();
				if (s.equals("EXIT")){
					pw.println("Cerrando servidor");
					break;
				}
				if (s.equals("DIR")){
					//System.out.println("Listando archivos...");
					pw.write("Listado de archivos");
				}
				pw.flush();
			}
			socketCliente.close();
		}catch(IOException e){

		}
	}	
}