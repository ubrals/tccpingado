package software.infrastructure;

import java.util.Collection;

import contracts.Econtract;
import entities.CryptoPerson;
import entities.Customer;
import entities.ISP;
import entities.Party;
import entities.values.Content;
import entities.values.ContentDelivered;
import software.controllers.CtrlEcontract;
import software.controllers.CtrlExchangedValue;

public class SequenceConsumption {

	public static void main(String[] args) {
		Party isp = new ISP(10000, "Mack Content Service Provider", "bobbob12", "0xae72eb6f58f92809940fdfaf3d292d08b55ed58a");
		Party customer = new Customer(29172, "Andre Miguel", "bobbob12", "0xcd2c65c3d10e45836e0c9309ba8f773c18c2fb5d");
		Content cont_selecionado = null;
		ContentDelivered contentDeleivered = null;
		
		////////
		// Lista conteudo
		System.out.println("DBG::" + "Sequence");
		Collection<Content> contents = ((ISP)isp).listContents();
		
		for(Content content : contents){
			System.out.println(content.getTitle());
			System.out.println(content.getLocation() + "/" + content.getFilename());
			cont_selecionado = content;
		}
		
		/////////
		// Seleciona conteudo
		try {
		    System.err.println("..:DBG:(Class.SequenceConsumption.contentDeleivered:new):" + cont_selecionado.getTitle() + " " + customer.getName() +  " " + "bobbob12" + " " + ((CryptoPerson)customer).getAccount());
		    contentDeleivered =  ((ISP)isp).deliverContent(cont_selecionado, customer, "bobbob12", ((CryptoPerson)customer).getAccount());
//		    System.out.println("url=" + contentDeleivered.getUrl());
//		    System.out.println("econtractId=" + contentDeleivered.getEcontractId());
//            System.out.println("contentId=" + contentDeleivered.getContentId());
//            System.out.println("customerId=" + contentDeleivered.getConsumerId());
//            System.out.println("account=" + contentDeleivered.getAccount());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /////////
        // Cobra conteudo
        try {
            ((ISP)isp).chargeDeliveredContent(contentDeleivered);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ///////
        // executar video e cobrar por tempo
        
	}
}
