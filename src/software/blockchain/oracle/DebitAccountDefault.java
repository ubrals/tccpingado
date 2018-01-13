package software.blockchain.oracle;

import java.net.Socket;
import java.util.Date;

public class DebitAccountDefault {
    private DebitAccountDefault(){}
    
    public static void main(String args[]) throws Exception{
        String consumerId = "";
        String account = "";
        String password = "";
        String econtract = "";
        String debit = "";
        System.out.println("Usage: java -jar DebitAccountDefault.jar $CRYPTOPERSON_ID $WALLET_PASSWORD $DEBIT_AMOUNT");
        if(args != null){
            if(args[0] != null)
                consumerId = args[0];
            else
                throw new Exception("..:Customer was not informed");
            //
            if(args[1] != null)
                password = args[1];
            else
                throw new Exception("..:ERR:Either customer or password were not informed");
            //
            if(args[2] != null)
                account = args[2];
            else
                throw new Exception("..:ERR:Either customer, password or account were not informed");
            //
            if(args[3] != null)
                econtract = args[3];
            else
                throw new Exception("..:ERR:Either customer, password, account or econtract were not informed");
            //
            if(args[4] != null)
                debit = args[4];
            else
                throw new Exception("..:ERR:Either customer, password, account or econtract or debit amount were not informed");
        }
        System.out.print("[" + new Date() + "] Debit : account=" + account + "; econtract=" + econtract + "; debit=" + debit + "\n");
        String command[] = {
                "/Users/ubrals/ethereum/makeTrxToIsp.sh",
       /* $1 */ consumerId,
       /* $2 */ password,
       /* $3 */ account,
//                "0xcd2c65c3d10e45836e0c9309ba8f773c18c2fb54",
       /* $4 */ econtract,
       /* $5 */ debit
//       , "|"
//       , "tee"
//       , "/Users/ubrals/ethereum/econtract/makeTrx.log"
        };
        for(String cmd : command)
            System.err.println("DBG::DebAccDef.cmd=" + cmd);
        System.err.println("");
        Process debitProcess = Runtime.getRuntime().exec(command);
        Thread.sleep(300);
//        debitProcess.destroy();
        System.out.println("debitProcess.alive=" + debitProcess.isAlive());
        while(debitProcess.isAlive());
        
        System.out.println("");
        System.out.println("waitFor: " + debitProcess.waitFor());
        System.out.println("isAlive: " + debitProcess.isAlive());

        if(debitProcess.exitValue() != 0){
            DebitAccountDefault obj = new DebitAccountDefault();
            System.out.println("..:ERR:" + obj.getClass().getName() + ":Could not process debit from the account: " + command[0] + ".Exit(" + debitProcess.exitValue() + ")");
            System.exit(debitProcess.exitValue());
        }
    }
}
