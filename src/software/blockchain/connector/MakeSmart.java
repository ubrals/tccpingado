package software.blockchain.connector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import contracts.Status;

public class MakeSmart {
    private long econtractid;
    private String buyer;
    private long contentid;
    private int decimals;
    private int price;
    private int fraction;
    private long timeToStart;
    private Status status;
    private boolean valid;
    private String seller;
    private long value;
    private String buyerpwd;
    private String sellerpwd;
    private String d_work;
    private String d_curr;
    private String filename;
    private File file;
    private File directory;
    
    
    /**
     * @param econtractid
     * @param buyer
     * @param contentid
     * @param decimals
     * @param price
     * @param fraction
     * @param timeToStart
     * @param status
     * @param valid
     * @param seller
     * @param value
     * @param buyerpwd
     * @param sellerpwd
     * @param d_work
     * @param filename
     * @param file
     */
    public MakeSmart(long econtractid, String buyer, long contentid, int decimals, int price, int fraction,
            long timeToStart, Status status, boolean valid, String seller, long value, String buyerpwd,
            String sellerpwd, String d_work) {
        this.econtractid = econtractid;
        
        this.buyer = buyer;
        this.buyerpwd = buyerpwd;
        this.seller = seller;
        this.sellerpwd = sellerpwd;
        
        this.contentid = contentid;
        
        this.decimals = decimals;
        this.price = price;
        this.fraction = fraction;
        
        this.timeToStart = timeToStart;
        
        this.status = status;
        
        this.valid = valid;
        this.value = value;
        
        this.d_work = d_work;
        this.filename = this.econtractid + ".smart";
        this.directory = new File(d_work);
        if(directory.isDirectory())
            if(directory.canWrite())
                this.file = new File(filename);
        
    }

    
    /**
     * 
     * @throws FileNotFoundException
     * sh deployEcontract.sh 352506755630 0x626bf55fbd632320461a53e1f439a6ea7f0752e7 918273 100 5 60 1515709977307 INITIATED true 0x9b18199846e53a4cd2b44da8f8ac378d01f851fb 8000000 bobbob12 bobbob12 2>/dev/null 
     */
    void makeNewSmartContract() {
        String command[] = {
        /*  0 */ "/Users/ubrals/ethereum/deployEcontract.sh",
        /*  1 */ Long.toString(econtractid), 
        /*  2 */ buyer,
        /*  3 */ Long.toString(contentid),
        /*  4 */ Integer.toString(decimals),
        /*  5 */ Integer.toString(price),
        /*  6 */ Integer.toString(fraction),
        /*  7 */ Long.toString(timeToStart),
        /*  8 */ status.toString(),
        /*  9 */ Boolean.toString(valid),
        /* 10 */ seller,
        /* 11 */ Long.toString(value),
        /* 12 */ buyerpwd,
        /* 13 */ sellerpwd
        };
        
        while(String s)
        fos.write(null);
        
        Process smartProcess = Runtime.getRuntime().exec(command);
        Thread.sleep(300);
//        debitProcess.destroy();
        System.out.println("smartProcess.alive=" + smartProcess.isAlive());
        while(smartProcess.isAlive());
        
        BufferedReader br = new BufferedReader(new InputStreamReader(smartProcess.getErrorStream()));
        String line;
        String address;
        while((line = br.readLine()) != null) {
//;//         System.out.println("Read error stream: \"" + line + "\"");
            String _line[]=line.split(":");
            address = _line[1];
            if(address.startsWith("0x"))
                break;
        }
        // STDERR
        Thread.sleep(300);
        smartProcess.destroy();
        smartProcess.isAlive();
//        System.err.println(sendProcess.isAlive());
        
        smartProcess.waitFor();
//        System.out.println("");
//        System.out.println("waitFor: " + sendProcess.waitFor());
//        System.out.println("isAlive: " + sendProcess.isAlive());
        if(smartProcess.exitValue() != 0){
            System.err.println("..:ERR:" + this.getClass().getSimpleName() + ":Could not process debit from account: " + command[5] + ". Exit(" + smartProcess.exitValue() + ")");
            throw new Exception("..:ERR:" + this.getClass().getSimpleName() + ":Could not process debit from account: " + command[5] + ". Exit(" + smartProcess.exitValue() + ")");
        }

    }
    
    
    /**
     * 
     * @param directory_name
     * @return
     */
    public boolean setCurrentDirectory(String directory_name){
        boolean result = false;  // Boolean indicating whether directory was set
        
        d_curr = System.setProperty("user.dir", directory.getAbsolutePath());
        directory = new File(directory_name).getAbsoluteFile();
        
        if (directory.exists() || directory.mkdirs()){
            result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
            d_work = System.setProperty("user.dir", directory.getAbsolutePath());
        }

        return result;
    }

}
