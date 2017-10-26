package software.infrastructure;

import contracts.Econtract;
import entities.ISP;
import entities.Producer;

public class View {

    public View() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        CriaEntidades entidades = new CriaEntidades();
        entidades.criarEntidades();
        entidades.criarContratoPrdIsp();
        Producer prod = entidades.getProducer();
        ISP isp = entidades.getIsp();
        Econtract ectProdIsp = entidades.getEctPrdXisp();
        
        System.out.println("contrato: " + ectProdIsp.getId());
        System.out.println("status: " + ectProdIsp.getManagementEcontract().getStatusLabel());
        System.out.println();
        try {
            Runtime.getRuntime().exec("java -jar dist/PlayerVideo.jar " + ectProdIsp.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
