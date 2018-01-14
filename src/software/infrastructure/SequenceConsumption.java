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
        cont_selecionado = selectContent(isp, "");
//        System.err.println(cont_selecionado.getTitle());

        /////////
        // Seleciona conteudo
        contentDeleivered = provisionContent(isp, cont_selecionado, customer, "bobbob12", ((CryptoPerson)customer).getAccount());

        /////////
        // Cobra conteudo
        chargeDeliveredContent(isp, contentDeleivered);

        ///////
        // executar video e cobrar por tempo

    }
    
    
    
    /**
     * @method: selectContent
     * @param: isp : ISP
     * @param: contentName : String
     * @return: Content
     * 
     */
    public static Content selectContent(Party isp, String contentName){
        Content cont_selecionado = null;

        Collection<Content> contents = ((ISP)isp).listContents();

        for(Content content : contents){
            cont_selecionado = content;
        }
        return cont_selecionado;
    }
    
    
    
    /**
     * @method provisionContent
     * @param: isp : ISP
     * @param: cont_selecionado : Content
     * @param: customer : Party
     * @param: password : String
     * @param: account : String
     * @return ContentDelivered
     */
    public static ContentDelivered provisionContent(Party isp, 
            Content cont_selecionado, 
            Party customer, 
            String password, 
            String account){
        ContentDelivered contentDeleivered = null;

        try {
            contentDeleivered =  ((ISP)isp).provisionContent(cont_selecionado, customer, password, account);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentDeleivered;
    }
    
    
    
    /**
     * @method: chargeDeliveredContent
     * @param: isp : Party
     * @param: contentDeleivered : ContentDelivered
     * @return: void
     */
    public static void chargeDeliveredContent(Party isp, ContentDelivered contentDeleivered){
        try {
            ((ISP)isp).chargeDeliveredContent(contentDeleivered);
            System.err.println("Econtract: " + contentDeleivered.getEcontractId() + " foi tarifado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
