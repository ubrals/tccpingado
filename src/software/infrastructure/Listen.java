package software.infrastructure;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Listen {

    public Listen() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        try{
            int port = 8888;
            ServerSocket servidor = new ServerSocket(port);
            System.out.println("Ouvindo na porta " + servidor.getLocalPort());
            while(true){
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                try{
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                    DataOutputStream outToClient = new DataOutputStream(cliente.getOutputStream());
                    while(!inFromUser.ready()){
                        String clientSentence = inFromClient.readLine();
                        System.out.print("[" + new Date() + "] Cliente : " + clientSentence + "\n");
                        try{
//                            Runtime.getRuntime().exec("java -jar dist/Send.jar " + "localhost ");
//                            Runtime.getRuntime().exec("cat /Users/ubrals/ethereum/microTransacao.eth |/usr/local/bin/geth attach ipc:///Users/ubrals/ethereum/geth.ipc >/Users/ubrals/ethereum/`date +%s`");
                            if((int)Double.parseDouble(clientSentence) % 15 == 0){
                                System.out.print("[" + new Date() + "] Ethereum: descontando 0.001 ether\n");
                                System.out.println("tempo=" + clientSentence + " %40=" + (Double.parseDouble(clientSentence) % 40));
                                Runtime.getRuntime().exec("/Users/ubrals/ethereum/batch.sh");
                            }
                            if((int)Double.parseDouble(clientSentence) % 60 == 0){
                                System.out.print("[" + new Date() + "] Ethereum: mineirando para processar transacoes\n");
                                Runtime.getRuntime().exec("/Users/ubrals/ethereum/mineirar.sh");
                            }
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
//                        if(clientSentence.contentEquals("quit")){
//                            break;
//                        }
//                        System.out.print("[" + new Date() + "] Entrada(srv): ");
//                        String sentence = inFromUser.readLine();
//                        if(sentence.contentEquals("quit")){
//                            break;
//                        }
                        outToClient.writeBytes("quit" + '\n');
                        break;
//                        outToClient.writeBytes(sentence + '\n');
//                        System.out.println("aguardando...");
                    }
//                    Runtime.getRuntime().exec("cat /Users/ubrals/ethereum/mineirar.eth |geth attach ipc:///Users/ubrals/ethereum/geth.ipc");
                    Runtime.getRuntime().exec("/Users/ubrals/ethereum/mineirar.sh");
                    inFromUser.close();
                    outToClient.close();
                    inFromClient.close();
//                    servidor.close();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                cliente.close();
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        finally {}
    }
}
