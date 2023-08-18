import java.io.IOException;
import java.net.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class BigServer {



    public static ArrayList<Session> sessions = new ArrayList<>() ;
    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(6000);
        while (true) {
            System.out.println("Esperando cliente");
            Socket socketClient = server.accept();
            System.out.println("Cliente conecatdo");

        }


    }

    public static void sendBroadCast(String message){
        for(Session session: sessions){
            session.send(message) ;

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

    public static void Interfaces(){

        try{
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces() ;
            for(NetworkInterface net : Collections.list(enumeration)) {
                ListAllNet(net);

            }

        }catch(SocketException e){
            e.printStackTrace();
        }

    }

    public static void ListAllNet(NetworkInterface networkInterface){

        System.out.println("Name: " + networkInterface.getName());
        System.out.println("Display Name: "+ networkInterface.getDisplayName());
        Enumeration<InetAddress> enumeration = networkInterface.getInetAddresses() ;
        for (InetAddress inetAddress : Collections.list(enumeration)){

            String ipAddress = inetAddress.getHostAddress() ;
            String hostName = inetAddress.getHostName() ;


            System.out.println("Ip Address: "+ ipAddress+", Host Name: " + hostName);

        }


    }
}