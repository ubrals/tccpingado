package entities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;

import contracts.Econtract;
import entities.values.Content;
import entities.values.ContentDelivered;
import pricing.Framework;
import software.controllers.CtrlEcontract;
import software.controllers.CtrlExchangedValue;

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
	public ContentDelivered deliverContent(Content content, Party consumer, String password, String account) throws Exception {
		CtrlEcontract ctrl_ect = new CtrlEcontract();
		
        Econtract econtract = ctrl_ect.findEcontractById(content.getId());
                  econtract = content.getEcontract();
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
//        long jitTimeToStart = econtract.getJustintimeEcontract().getTimeToStartLong();
//        int valid = econtract.getEnactmentEcontract().isValidInt();
//        int status = econtract.getManagementEcontract().getStatus();
        CtrlExchangedValue ctrl_exv = new CtrlExchangedValue();
//		econtract = ctrl_ect.existentEcontract(econtractId, content, provider, consumer, framework, frameworkValue, fractionMicro, jitTimeToStart, valid, status);
		econtract = ctrl_ect.newEcontract(content, provider, consumer, framework, frameworkReference, frameworkPrice, fractionMicro);
		econtractId = econtract.getId();
		
        String contract_file = ctrl_exv.makeSymLink(econtract.getId(),
                                                    ((Content)econtract.getExchangedValue()).getLocation(),
                                                    ((Content)econtract.getExchangedValue()).getFilename());
        System.out.println(contract_file);
        String url = ctrl_exv.getURL("http://localhost", "/", contract_file);
        System.out.println(url);
        
        return new ContentDelivered(url, contentId, econtractId, consumerId, password, account, frameworkReference, frameworkPrice);
	}


	/**
	 * @see entities.CSP#chargeDeliveredContent()
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
        /*
         * STDERR
         */
        System.out.println("..:DBG:Read ErrorStream");
        System.err.println("..:DBG:command[]=" + command[0] + " " + command[1] + " " + command[2] + " " + command[3] + " " + command[4] + " " + command[5] + " " + command[6] + " " + command[7] + " " + command[8] + " " + command[9]);
	    Process sendProcess = Runtime.getRuntime().exec(command);
	    BufferedReader br = new BufferedReader(new InputStreamReader(sendProcess.getErrorStream()));
	    String line;
	    while((line = br.readLine()) != null) {
	        System.out.println("Read error stream: \"" + line + "\"");
        }
	    // STDERR
	    Thread.sleep(300);
	    sendProcess.destroy();
        System.out.println(sendProcess.isAlive());
        
        System.out.println("");
        System.out.println("waitFor: " + sendProcess.waitFor());
	    System.out.println("isAlive: " + sendProcess.isAlive());
        if(sendProcess.exitValue() != 0){
            System.out.println("..:ERR:" + this.getClass().getName() + "Could not process debit from the account: " + command[3] + ".Exit(" + sendProcess.exitValue() + ")");
	        throw new Exception("..:ERR:Could not process debit from account. Exit(" + sendProcess.exitValue() + ")");
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
