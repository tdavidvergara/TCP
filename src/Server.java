

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket; //La puerta hacia el otro lado de la conexión
import java.util.Scanner;
import java.time.ZonedDateTime ;
import java.net.InetAddress ;
import java.net.UnknownHostException ;
//import

public class Server {

    public static void main(String[] args) throws IOException {

        //1. Hago que el server esté atendo a conexiones

            ServerSocket server = new ServerSocket(6000); //5000, es el puerto de red a donde se quiere conectar
            System.out.println("Esperando conexión");

            //3. Conexión se acepta
            Socket socket = server.accept(); //espera una solicitud
            System.out.println("Conexión aceptada");
            String mess = "Qué función desea implementar?";
            socket.getOutputStream().write(mess.getBytes("UTF-8"));


            //5. Server recibe mensaje
            byte[] buffer = new byte[300];
            socket.getInputStream().read(buffer); //se queda esperando hasta el cliente haga su primer envio
            String received = new String(buffer, "UTF-8");
            System.out.println(received.trim());


        /*
        //6. Server manda
        String mensaje = "qué función desea implementar?";
        socket.getOutputStream().write(mensaje.getBytes());  */

            new Thread(() -> {
                while (true) {//evita que el server se cierre
                    try {
                        byte[] bf = new byte[300]; //los datos quedan en bytes
                        socket.getInputStream().read(bf); //se queda esperando hasta el cliente haga su primer envio
                        String rec = new String(bf, "UTF-8");
                        System.out.println(rec.trim());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }

            }).start();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                String msg = scanner.nextLine();
                socket.getOutputStream().write(msg.getBytes("UTF-8"));

            }
    }

    public static void Time(){
        System.out.println(">>>> "+ ZonedDateTime.now());



    }

    public static void MyIp() {
        try{
            InetAddress localHost = InetAddress.getLocalHost() ;
            String ipAddress = localHost.getHostAddress() ;
            System.out.println(ipAddress);
        }catch (UnknownHostException e) {
            System.out.println("NO SE PUDO DETERMINAR IP DEL SERVER");
        }


    }
}



