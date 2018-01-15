package software.blockchain.connector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Listen {

    private Listen() {
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
                    /*
                     * inFromClient: entrada da conexão do cliente */
                    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                    /*
                     * inFromUser: entrada de dados do servidor */
                    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                    /*
                     * outToClient: saída para o cliente */
                    DataOutputStream outToClient = new DataOutputStream(cliente.getOutputStream());
                    while(!inFromUser.ready()){
                        String clientSentence = inFromClient.readLine();
                        String input[] = clientSentence.split(" ");
                        String cmd = input[0];
                        String consumerId = input[1];
                        String password = input[2];
                        String account = input[3];
                        String econtract = input[4];
                        String debit = input[5];
                        System.err.print("[" + new Date() + "] Client : command=" + cmd + "; consumerId=" + consumerId + "; account=" + account + "; econtract=" + econtract + "; debit=" + debit + "\n");
                        try{
                            /*
                             * old
//                            Runtime.getRuntime().exec("java -jar dist/Send.jar " + "localhost ");
//                            Runtime.getRuntime().exec("cat /Users/ubrals/ethereum/microTransacao.eth |/usr/local/bin/geth attach ipc:///Users/ubrals/ethereum/geth.ipc >/Users/ubrals/ethereum/`date +%s`");
                            if((int)Double.parseDouble(clientSentence) % 15 == 0){
                                System.out.print("[" + new Date() + "] Ethereum: descontando 0.001 ether\n");
//                                System.out.println("tempo=" + clientSentence + " %40=" + (Double.parseDouble(clientSentence) % 40));
                                Runtime.getRuntime().exec("/Users/ubrals/ethereum/batch.sh");
                            }
                            if((int)Double.parseDouble(clientSentence) % 60 == 0){
                                System.out.print("[" + new Date() + "] Ethereum: mineirando para processar transacoes\n");
                                Runtime.getRuntime().exec("/Users/ubrals/ethereum/mineirar.sh");
                            }
                            // */
                            switch (cmd.toUpperCase()) {
                            case "DEBIT":
                                System.out.print("[" + new Date() + "] Debit : consumerId=" + consumerId + "; account=" + account + "; econtract=" + econtract + "; debit=" + debit + "\n");
                                String command[] = {
                                        "java",
                                        "-jar",
                                        "dist/DebitAccountDefault.jar",
                                /* 0 */ consumerId,
                                /* 1 */ password,
                                /* 2 */ account,
                                /* 3 */ econtract,
                                /* 4 */ debit
                                };
                                Process debitProcess = Runtime.getRuntime().exec(command);
                                System.out.println(debitProcess.waitFor());
                                System.out.println(debitProcess.isAlive());
                                System.out.println(debitProcess.isAlive());
                                if(debitProcess.exitValue() != 0){
                                    outToClient = new DataOutputStream(cliente.getOutputStream());
                                    //
                                    // TODO: colocar error:return# 
                                    outToClient.writeBytes("error");
                                    outToClient.close();
                                    Listen obj = new Listen();
//                                    System.err.println("..:ERR:" + obj.getClass().getName() + ":Could not process debit from the account: " + command[2] + ".Exit(" + debitProcess.exitValue() + ")");
                                    throw new Exception("..:ERR:" + obj.getClass().getName() + ":Could not process debit from the account: " + command[3] + " " + command[4] + " " + command[5] + " " + command[6] + " " + command[7] + ".Exit(" + debitProcess.exitValue() + ")");
                                    //
                                    // TODO: aqui deve retornar erro pro SendDebit
                                }
                                break;
                            default:
                                break;
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
//                    Runtime.getRuntime().exec("/Users/ubrals/ethereum/mineirar.sh");
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
