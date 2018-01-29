package software.infrastructure._deprec_;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class _deprec_Send {

    public _deprec_Send() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        try{
            Socket cliente = null;
            String IP = "localhost";
            int port = 8888;
            System.out.println("Voce pode rodar: java -jar ClienteTCPBasico $ENDERECO_IP");
            if(args != null){
                if(args[0] != null)
                    IP = args[0];
                if(args[1] != null)
                    port = Integer.valueOf(args[1]);
            }
            try {
                cliente = new Socket(IP, port);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                IP = "localhost";
                port = 8888;
                try {
                    cliente = new Socket(IP, port);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            try{
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream outToServer = new DataOutputStream(cliente.getOutputStream());
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                while(!inFromUser.ready()){
                    System.out.print("[" + new Date() + "] Entrada(cli): ");
//                    String sentence = inFromUser.readLine();
                    String sentence = args[1];
                    if(sentence.contentEquals("quit")){
                        break;
                    }
                    outToServer.writeBytes(sentence + '\n');
//                    outToServer.writeBytes("quit" + '\n');
//                    System.out.println("aguardando...");
                    String modifiedSentence = inFromServer.readLine();
                    System.out.println("[" + new Date() + "] Servidor: " + modifiedSentence);
                    if(modifiedSentence.contentEquals("quit")){
                        break;
                    }
                }
                inFromUser.close();
                outToServer.close();
                inFromServer.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            System.out.println("Conexão encerrada");
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
