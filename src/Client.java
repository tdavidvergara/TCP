
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
        public static void main(String [] args) throws IOException{

            //2. Solicitud de conexión
            Socket socket = new Socket("127.0.0.1", 6000) ;
            System.out.println("Qué función desea implementar?");

            /*

            //4. Cliente envía mensaje
            String mensaje = "ICESI" ;
            socket.getOutputStream().write(mensaje.getBytes("UTF-8"));

            //7. Cliente recibe
            byte[] buffer = new byte[300] ;
            socket.getInputStream().read(buffer) ;
            String received = new String(buffer, "UTF-8") ;

            System.out.println(received.trim());

            */


            new Thread(()->{
                while(true) {//evita que el server se cierre
                    try {
                        byte[] bf = new byte[300];
                        socket.getInputStream().read(bf); //se queda esperando hasta el cliente haga su primer envio
                        String rec = new String(bf, "UTF-8");
                        System.out.println(rec.trim());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }).start();

            Scanner scanner  = new Scanner(System.in) ;

            while(true){
                String msg = scanner.nextLine() ;
                socket.getOutputStream().write(msg.getBytes("UTF-8"));

                if(msg.equals("whatTimeIsIt")){
                    BigServer.Time() ;
                    socket.close();

                } else if(msg.equals("remoteIpConfig")){
                    BigServer.MyIp();

                } else if(msg.equals("interfaces")){
                    BigServer.Interfaces();

                } else {
                    System.out.println("Ese comando no existe");
                }





            }

        }




    }


