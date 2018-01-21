package entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import contracts.Econtract;
import contracts.Status;
import entities.values.Content;
import entities.values.ContentDelivered;
import pricing.Framework;
import resources.databases.dao.api.TransactionDaoInterface;
import software.controllers.CtrlEcontract;
import software.controllers.CtrlExchangedValue;
import software.controllers.CtrlTransaction;

public class ISP extends CryptoPerson implements CSP {

    private Collection<Content> contents = new ArrayList<Content>();

    public ISP(long id, String name, String password, String account) {
        super(id, name, password, account);
    }


	/**
	 * @see entities.CSP#publishContent(entities.values.Content)
	 */
	public void publishContent(Content content) {

	}


	/**
	 * @see entities.CSP#receiveContent(entities.values.Content)
	 */
	public void receiveContent(Content content) {
	    this.contents.add(content);
	}


	/**
     * @return Collection<Content>
	 * @see entities.CSP#listContents()
	 */
	public Collection<Content> listContents() {
		CtrlExchangedValue ctrl_exv = new CtrlExchangedValue();
		this.contents = ctrl_exv.listContents();
		
	    return contents;
	}


	/**
	 * @return 
	 * @throws Exception 
	 * @see entities.CSP#deliverContent()
	 */
	public ContentDelivered provisionContent(Content content, Party consumer, String password, String account) throws Exception {
		CtrlEcontract ctrl_ect = new CtrlEcontract();
		Econtract econtract = content.getEcontract();
        Party provider = null;
        int i = 0;
        for(Party party : econtract.getParty()){
            if(i == 0) provider = party;
        }
        long contentId = content.getId();
        long econtractId = econtract.getId();
        long consumerId = consumer.getId();
        Framework framework = content.getEcontract().getFramework();
        String frameworkReference = (String) framework.getReference();
        double frameworkPrice = framework.getPrice();
        int fractionMicro = econtract.getMicroEcontract().getFraction();
        CtrlExchangedValue ctrl_exv = new CtrlExchangedValue();
		econtract = ctrl_ect.newEcontract(content, provider, consumer, framework, frameworkReference, frameworkPrice, fractionMicro);
		econtractId = econtract.getId();
		
        String contract_file = ctrl_exv.makeSymLink(econtract.getId(),
                                                    ((Content)econtract.getExchangedValue()).getLocation(),
                                                    ((Content)econtract.getExchangedValue()).getFilename());
        String url = ctrl_exv.getURL("http://localhost", "/", contract_file);
        System.err.println("..:INF:" + this.getClass().getSimpleName() + ":URL:" + url);
        
        return new ContentDelivered(url, contentId, econtractId, consumerId, password, account, frameworkReference, frameworkPrice);
	}
	
	
	/**
	 * @see entities.CSP#chargeDeliveredContent()
	 * @param contentDelivered:ContentDelivered
	 * @throws Exception
	 */
	public void chargeDeliveredContent(ContentDelivered contentDelivered) throws Exception {
        long consumerId = contentDelivered.getConsumerId();
        String password = contentDelivered.getPassword();
        String account = contentDelivered.getAccount();
        long contentId = contentDelivered.getContentId();
        double debitAmount = contentDelivered.getDebitAmount();
        long econtractId = contentDelivered.getEcontractId();
        String timeReference = contentDelivered.getTimeReference();
        String url = contentDelivered.getUrl();
	    
        String command[] = {
                "java",
                "-jar",
                "dist/SendDebit.jar",
        /* 0 */ "localhost",
        /* 1 */ "8888",
        /* 2 */ String.valueOf(consumerId),
        /* 3 */ password,
        /* 4 */ account,
        /* 5 */ String.valueOf(econtractId),
        /* 6 */ String.valueOf(debitAmount)
        };
        Process sendProcess = Runtime.getRuntime().exec(command);
        /*
         * STDERR
         */
//        System.err.println("..:DBG:Read ErrorStream");
//        System.err.println("..:DBG:command[]=" + command[0] + " " + command[1] + " " + command[2] + " " + command[3] + " " + command[4] + " " + command[5] + " " + command[6] + " " + command[7] + " " + command[8] + " " + command[9]);
	    BufferedReader br = new BufferedReader(new InputStreamReader(sendProcess.getErrorStream()));
	    String line;
	    while((line = br.readLine()) != null) {
;//	        System.out.println("Read error stream: \"" + line + "\"");
        }
	    // STDERR
	    Thread.sleep(300);
	    sendProcess.destroy();
	    sendProcess.isAlive();
//        System.err.println(sendProcess.isAlive());
        
	    sendProcess.waitFor();
//        System.out.println("");
//        System.out.println("waitFor: " + sendProcess.waitFor());
//        System.out.println("isAlive: " + sendProcess.isAlive());
        if(sendProcess.exitValue() != 0){
            System.err.println("..:ERR:" + this.getClass().getSimpleName() + ":Could not process debit from account: " + command[5] + ". Exit(" + sendProcess.exitValue() + ")");
	        throw new Exception("..:ERR:" + this.getClass().getSimpleName() + ":Could not process debit from account: " + command[5] + ". Exit(" + sendProcess.exitValue() + ")");
        }
	}


	@Override
    public void setEcontractStatusDeliveredContent(ContentDelivered contentDelivered, Status status) throws Exception {
        CtrlEcontract ctrl_ect = new CtrlEcontract();
        Econtract econtract=ctrl_ect.findEcontractById(contentDelivered.getEcontractId());
        ctrl_ect.setEcontractStatus(econtract, status);
        System.err.println("..:INF:" + this.getClass().getSimpleName() + ":Set Econtract.status:" + econtract.getManagementEcontract().getStatusLabel());
    }


    @Override
    public void deliverContent(ContentDelivered contentDelivered) throws Exception {
        long sleep;
        switch (contentDelivered.getTimeReference()) {
        case "DAY":    sleep=1000l * 60l * 60l * 24l; break;
        case "HOUR":   sleep=1000l * 60l * 60l; break;
        case "MINUTE": sleep=1000l * 60l; break;
        case "SECOND": sleep=1000l; break;
        case "MILLISECOND": sleep=1l; break;
        default: sleep=1000l * 60l; break;
        }

        this.setEcontractStatusDeliveredContent(contentDelivered, Status.STARTED);
        
        try {
            String command[] = {
                    "java",
                    "-jar",
                    "dist/PlayerVideoNew.jar",
                    contentDelivered.getUrl()
            };
            Process playerProcess = Runtime.getRuntime().exec(command);
///////////
//            while(true){
            while(playerProcess.isAlive()){
                ////////
                try {
                    int procExitValue = playerProcess.exitValue();
                    if(procExitValue != 0){
                        this.setEcontractStatusDeliveredContent(contentDelivered, Status.ABORTED);
                        break;
                    }
//                    System.err.println("..DBG:" + this.getClass().getSimpleName() + ":proc.exitStatus=" + procExitValue);
                }
                catch (Exception e) { }
                ///////
                try {
                    System.err.println("..:DBG:(" + new Date() + ")" + this.getClass().getSimpleName() + ":sleep=" + sleep);
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ///////
                // charge
                // TODO: alterar o tempo de sleep
                // TODO: ou outra coisa. pq so esta rodando uma vez
                this.chargeDeliveredContent(contentDelivered);
                this.setEcontractStatusDeliveredContent(contentDelivered, Status.PROVISIONING);
                ////////
                // transaction
                TransactionDaoInterface ctrl_trx = new CtrlTransaction();
                long transactionSeq = 1l+ctrl_trx.getLastTransactionSeq(contentDelivered.getEcontractId());
                long trxtimestamp = System.currentTimeMillis();
                double price = contentDelivered.getDebitAmount();
                Transaction transaction = new Transaction(transactionSeq, trxtimestamp, price);
                ctrl_trx.insertTransaction(transaction, contentDelivered.getEcontractId());
                
                // <STDERR>
                BufferedReader br = new BufferedReader(new InputStreamReader(playerProcess.getErrorStream()));
                String line;
                while((line = br.readLine()) != null) {
                    System.err.println("Read error stream: \"" + line + "\"");
                    Thread.sleep(1000l);
                    break;
                }
                // </STDERR>
                
//                playerProcess.waitFor();
                if(!playerProcess.isAlive()){
                    playerProcess.destroy();
                    break;
                }
//                System.out.println("");
//                System.out.println("waitFor: " + sendProcess.waitFor());
//                System.out.println("isAlive: " + sendProcess.isAlive());
//                if(playerProcess.exitValue() != 0){
//                    System.err.println("..:ERR:" + this.getClass().getSimpleName() + ":Could not process debit from account: " + command[5] + ". Exit(" + sendProcess.exitValue() + ")");
//                    throw new Exception("..:ERR:" + this.getClass().getSimpleName() + ":Could not process debit from account: " + command[5] + ". Exit(" + sendProcess.exitValue() + ")");
//                }
            }
            ////////
            try {
                int procExitValue = playerProcess.exitValue();
                if(procExitValue == 0){
                    this.setEcontractStatusDeliveredContent(contentDelivered, Status.CONCLUDED);
                }
//                System.err.println("..DBG:" + this.getClass().getSimpleName() + ":proc.exitStatus=" + procExitValue);
            }
            catch (Exception e) { }
            ///////

//            this.setEcontractStatusDeliveredContent(contentDelivered, Status.CONCLUDED);
///////////
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
	 * @see entities.CSP#payDeliveredContent()
	 */
	public void payDeliveredContent(Econtract econtract) {

	}


	/**
	 * @see entities.CSP#negotiatePrice()
	 */
	public void negotiatePrice() {

	}

}
