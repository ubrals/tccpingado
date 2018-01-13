package software.blockchain.connector;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class SendDebit {

    private SendDebit() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        try{
            Socket cliente = null;
            String IP = "localhost";
            int port = 8888;
            long consumerId = 0l;
            String password = "";
            String account = "";
            long contractId = 0l;
            double debitAmount = 0.0;
            
            System.out.println("Usage: java -jar SendDebit.jar $ENDERECO_IP $PORTA $CRYPTOPERSON_ID $WALLET_PASSWORD $CONTRACT_ID $DEBIT_AMOUNT");
//            for(String arg : args)
//                System.err.println(arg);
//            System.err.println("");
            if(args != null){
                if(args[0] != null)
                    IP = args[0];
                if(args[1] != null)
                    port = Integer.valueOf(args[1]);
            }
            try {
                cliente = new Socket(IP, port);
                try {
                    consumerId = Long.valueOf(args[2]);
                    password = args[3];
                    account = args[4];
                    contractId = Long.valueOf(args[5]);
                    debitAmount = Double.valueOf(args[6]);
                    System.err.println("..:(Class.SendDebit)DBG:parms=" + consumerId + "/" + password + "/" + account +  "/" + contractId + "/" + debitAmount);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//            finally {
//                IP = "localhost";
//                port = 8888;
//                try {
//                    cliente = new Socket(IP, port);
//                    try {
//                        consumerId = Long.valueOf(args[0]);
//                        password = args[1];
//                        contractId = Long.valueOf(args[2]);
//                        debitAmount = Integer.valueOf(args[3]);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } catch (Exception e2) {
//                    e2.printStackTrace();
//                }
//            }
            try{
                /*
                 * inFromUser: preparo para enviar para servidor */
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                /*
                 * outToServer: envio de dados para o servidor */
                DataOutputStream outToServer = new DataOutputStream(cliente.getOutputStream());
                /*
                 * inFromServer: resposta do servidor */
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                while(!inFromUser.ready()){
                    System.err.print("[" + new Date() + "] Entrada(cli): ");
//                    String sentence = inFromUser.readLine();
//                    String sentence = args[1];
                    //                   0          1                  2                3                4                  5
                    String sentence = "debit " 
                                    + consumerId 
                                    + " " 
                                    + password 
                                    + " " 
                                    + account 
                                    + " " 
                                    + contractId 
                                    + " " 
                                    + debitAmount;
                    System.err.println("sentence:" + sentence);
                    if(sentence.contentEquals("quit")){
                        break;
                    }
                    // outToServer: manda comandos pra Listen
                    outToServer.writeBytes(sentence + '\n');
//                    outToServer.writeBytes("quit" + '\n');
//                    System.out.println("aguardando...");
                    Thread.sleep(500l);
                    // modifiedSentence: resposta do servidor
                    String modifiedSentence = inFromServer.readLine();
                    System.err.println("[" + new Date() + "] Servidor:Listen): " + modifiedSentence);
                    if(modifiedSentence.contentEquals("error")){
                        SendDebit obj = new SendDebit();
                        System.err.println("..:ERR:" + obj.getClass().getName() + "Could not process debit from the account: " + "Listen" + ".Exit(" + modifiedSentence + ")");
                        throw new Exception("..:ERR:Could not process debit for account");
                    }
                    System.out.println("modifiedSentence=" + modifiedSentence);
                    if(modifiedSentence.contentEquals("quit")){
                        break;
                    }
                    Thread.sleep(400);
                    break;
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
