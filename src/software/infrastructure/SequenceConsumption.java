package software.infrastructure;

import entities.Customer;
import entities.ISP;
import entities.Party;
import entities.values.Content;

public class SequenceConsumption {

	public static void main(String[] args) {
		Party isp = new ISP(10000, "Mack Content Service Provider"); 
		Party customer = new Customer(29172, "Andre Miguel");
		
		Content contents[] = ((ISP)isp).listContent();
		
		for(Content content : contents){
			System.out.println(content.getLocation() + "/" + content.getFilename());
		}

	}

}
