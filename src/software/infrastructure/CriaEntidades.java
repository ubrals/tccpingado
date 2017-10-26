package software.infrastructure;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import contracts.Econtract;
import contracts.EnactmentEcontract;
import contracts.JustintimeEcontract;
import contracts.ManagementEcontract;
import contracts.MicroEcontract;
import contracts.Status;
import entities.Customer;
import entities.ISP;
import entities.Party;
import entities.Producer;
import entities.values.Content;
import entities.values.ExchangedValue;
import entities.values.Product;
import resources.Wallet;

public class CriaEntidades {
     Wallet ispWallet;
     ISP isp;

     Wallet prdWallet;
     Producer producer;

     Wallet cstWallet;
     Customer customer;

     Econtract ectPrdXisp;
     Econtract ectIspXcst;

     public ISP getIsp() {
        return isp;
    }

    public void setIsp(ISP isp) {
        this.isp = isp;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Econtract getEctPrdXisp() {
        return ectPrdXisp;
    }

    public void setEctPrdXisp(Econtract ectPrdXisp) {
        this.ectPrdXisp = ectPrdXisp;
    }

    public Econtract getEctIspXcst() {
        return ectIspXcst;
    }

    public void setEctIspXcst(Econtract ectIspXcst) {
        this.ectIspXcst = ectIspXcst;
    }

    void criarEntidades() {
        // ISP
        ispWallet = new Wallet("senha");
        isp = new ISP((long) (Math.random() * 10000000l), "Provedor de conteudo LTDA");
        isp.setWallet(ispWallet);
        isp.addCredits(300000l, Byte.valueOf("10"));
        // Producer
        prdWallet = new Wallet("senha");
        producer = new Producer((long) (Math.random() * 10000000l), "Produtor independente");
        producer.setWallet(prdWallet);
        producer.addCredits(200000l, Byte.valueOf("10"));
        // Customer
        cstWallet = new Wallet("senha");
        customer = new Customer((long) (Math.random() * 10000000l), "Cliente final");
        customer.setWallet(cstWallet);
        customer.addCredits(200000l, Byte.valueOf("10"));
    }

     void criarContratoPrdIsp() {
        ectPrdXisp = new Econtract();
        ExchangedValue content = new Content("video", null, ectPrdXisp, "PvsNP", 17000000l, (byte) 0, producer, isp);
        ectPrdXisp.setExchangedValue(content);

        Collection<Party> party = new ArrayList<Party>();
        party.add(producer);
        party.add(isp);
        ectPrdXisp.setParty(party);

        EnactmentEcontract ectEnactment = new EnactmentEcontract();
        ectEnactment.setValidTrue();
        // ---
        ManagementEcontract ectManagement = new ManagementEcontract();
        ectManagement.setStatus(Status.INITIATED);
        // ---
        MicroEcontract ectMicro = new MicroEcontract(60l);
        // ---
        ectPrdXisp.setEnactmentEcontract(ectEnactment);
        ectPrdXisp.setManagementEcontract(ectManagement);
        ectPrdXisp.setMicroEcontract(ectMicro);

        isp.receiveContent((Content) content);
    }

     void criarContratoIspCst(String nome) {
        ectIspXcst = new Econtract();
        ExchangedValue content;
        for (Content cont : isp.listContent()) {
            if (cont.getTitle() == nome) {
                content = cont;
                break;
            }
        }
        Collection<Party> party = new ArrayList<Party>();
        party.add(producer);
        party.add(isp);
        ectPrdXisp.setParty(party);

        EnactmentEcontract ectEnactment = new EnactmentEcontract();
        ectEnactment.setValidTrue();
        // ---
        ManagementEcontract ectManagement = new ManagementEcontract();
        ectManagement.setStatus(Status.INITIATED);
        // ---
        MicroEcontract ectMicro = new MicroEcontract(60l);
        // ---
        JustintimeEcontract ectJust = new JustintimeEcontract();
        long ts = System.currentTimeMillis();
        long mspdia = 1000l * 60l * 60l * 24l;
        ts = ts + (mspdia * 5);
        ectJust.setTimeToStartLong(ts);
    }

//    public static void main(String[] args) {
//        criarEntidades();
//        String sep = " :: ";
//        System.out.println(isp.getName() + sep + isp.getId() + sep + "$" + isp.getBalance());
//        System.out.println(producer.getName() + sep + producer.getId() + sep + "$" + producer.getBalance());
//        System.out.println(customer.getName() + sep + customer.getId() + sep + "$" + customer.getBalance());
//        System.out.println("+++++++++++++++");
//        criarContratoPrdIsp();
//        System.out.println(ectPrdXisp.getId() + sep + ((ArrayList<Party>) ectPrdXisp.getParty()).get(0).getName() + sep
//                + ((ArrayList<Party>) ectPrdXisp.getParty()).get(1).getName() + sep
//                + ectPrdXisp.getEnactmentEcontract().isValid() + sep 
//                + ((Content) ectPrdXisp.getExchangedValue()).getTitle()
//                );
//        // SimpleDateFormat sdfts = new SimpleDateFormat("dd/MM/YYYY
//        // HH:mm:ss.SS");
//        // long ts = System.currentTimeMillis();
//        // System.out.println(ts + " " + sdfts.format(ts));
//        // System.out.println(ts / 1000l / (2017l - 1970l));
//        // System.out.println(1000l * 60l * 60l * 24l);
//        // long mspdia = 1000l * 60l * 60l * 24l;
//        // ts = ts +(mspdia*5);
//        // System.out.println(ts + " " + sdfts.format(ts));
//        //
//        // long ap = 60l * 60l * 24l * 365l * (2017l-1970l) * 1000l;
//        // System.out.println(ap + " " + sdfts.format(ap));
//        //
//        // long ss = 1000l
//        // , mm = 60l * ss
//        // , hh = mm * 60l + 0l
//        //
//        // , DD = hh * 24l
//        // , MM = DD *30l
//        // , YY = DD * 365l
//        // , DIFF = (2017l-1970l)
//        // , ms = 1000l;
//        // ap = (ss + mm + hh + DD + MM + YY) * DIFF ;
//        // ts = System.currentTimeMillis();
//        // ap = ts + (DD * 5l) + hh/2l;
//        // System.out.println("ap:"+ ap + " " + sdfts.format(ap));
//    }

}
