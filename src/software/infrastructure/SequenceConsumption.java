package software.infrastructure;

import java.util.Collection;

import contracts.Econtract;
import entities.Customer;
import entities.ISP;
import entities.Party;
import entities.values.Content;
import software.controllers.CtrlEcontract;
import software.controllers.CtrlExchangedValue;

public class SequenceConsumption {

	public static void main(String[] args) {
		Party isp = new ISP(10000, "Mack Content Service Provider"); 
		Party customer = new Customer(29172, "Andre Miguel");
		Content cont_selecionado = null;
		
		////////
		// Lista conteudo
		System.out.println("DBG::" + "Sequence");
		Collection<Content> contents = ((ISP)isp).listContent();
		
		for(Content content : contents){
			System.out.println(content.getTitle());
			System.out.println(content.getLocation() + "/" + content.getFilename());
			cont_selecionado = content;
		}
		
		/////////
		// Seleciona conteudo
		try {
            ((ISP)isp).deliverContent(cont_selecionado, customer);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
