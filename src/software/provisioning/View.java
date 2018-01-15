package software.provisioning;

import entities.ISP;
import entities.Party;
import entities.values.ContentDelivered;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import pricing.components.TimeShares;
import software.infrastructure.PlayerVideo;

public class View extends Application implements Runnable {
    
    private Party isp;
    private ContentDelivered contentDelivered;
    private String url;
    private String time;
    private long sleep;

    public View(){
    }
    
    public View(Party isp, ContentDelivered contentDelivered){
        this.isp = isp;
        this.contentDelivered = contentDelivered;
        this.url = this.contentDelivered.getUrl();
        this.time = this.contentDelivered.getTimeReference();
        switch (time) {
        case "DAY":    this.sleep=1000l * 60l * 60l * 24l; break;
        case "HOUR":   this.sleep=1000l * 60l * 60l; break;
        case "MINUTE": this.sleep=1000l * 60l; break;
        case "SECOND": this.sleep=1000l; break;
        case "MILLISECOND": this.sleep=1l; break;
        default: this.sleep=1000l * 60l; break;
        }
    }
    
    
    public void Play() throws Exception{
        Runnable runnable = new PlayerVideo(this.isp, this.contentDelivered, this.url);
//        Runnable runnable = new View();
        Thread th = new Thread(runnable);
        
        th.start();
    }
    
//    @Override
    public void start(Stage palco) throws Exception {
        Media media = new Media(this.url); // 1
        MediaPlayer mediaPlayer = new MediaPlayer(media); // 2
        MediaView mediaView = new MediaView(mediaPlayer); // 3
        String time = this.time;

        StackPane raiz = new StackPane();
        raiz.getChildren().add(mediaView); // 4

        Scene cena = new Scene(raiz, 854, 480);
        palco.setTitle("Tocando Video em JavaFX");
        palco.setScene(cena);
        palco.show();

        ///////////////////////////////////////////////
        // Aqui esta pegando o tempo com
        /////////////////////////////////////////////// mediaPlayer.getCurrentTime().toSeconds()
        Runnable runplay = new Runnable() {

            @Override
            public void run() {
                long sleep=0l;
                switch (time) {
                case "DAY":    sleep=1000l * 60l * 60l * 24l; break;
                case "HOUR":   sleep=1000l * 60l * 60l; break;
                case "MINUTE": sleep=1000l * 60l; break;
                case "SECOND": sleep=1000l; break;
                case "MILLISECOND": sleep=1l; break;
                default: sleep=1000l * 60l; break;
                }

                while (true) {
                    if(mediaPlayer.getCurrentTime().toSeconds() >= mediaPlayer.getTotalDuration().toSeconds())
                        break;
//                    System.out.println("H:" + media.getHeight() + " W:" + media.getWidth());
//                    System.out.println("mediaPlayer.getStopTime().toSeconds()=" + mediaPlayer.getStopTime().toSeconds());
//                    System.out.println("Math.ceil(mediaPlayer.getStopTime().toSeconds())="
//                            + Math.ceil(mediaPlayer.getStopTime().toSeconds()));
/*
                    try {
                        System.out.println(
                                "tempo total=" + mediaPlayer.getTotalDuration().toSeconds() + " " +
                                "mediaPlayer.getCurrentTime().toSeconds()=" + mediaPlayer.getCurrentTime().toSeconds()
                                        + " ceil()=" + Math.ceil(mediaPlayer.getCurrentTime().toSeconds()));
// //                        Runtime.getRuntime().exec("java -jar dist/Send.jar " + "localhost " + mediaPlayer.getCurrentTime().toSeconds() ); //+ " " + econtractId);
*/
///////////
                        try {
                            ((ISP)isp).chargeDeliveredContent(contentDelivered);
                        } catch (Exception e) {
//                            throw new Exception("..:ERR:" + this.getClass().getSimpleName() + ":Econtract could not be charged. Video has stopped playing");
                            try {
                                throw new Exception("..:ERR:<Class>:Econtract could not be charged. Video has stopped playing");
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }
///////////
/*
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        break;
                    }
*/
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("getCurrentTime:ss="+ mediaPlayer.getCurrentTime().toSeconds());
//                try{
//                    Runtime.getRuntime().exec("java -jar dist/Send.jar " + "localhost " + "quit");
//                }
//                catch(Exception ex){
//                    ex.printStackTrace();
//                }
                System.exit(0);
            }
        };
        Thread thplay = new Thread(runplay);
        thplay.start();
        /////////////////////////////////////////////////

        mediaPlayer.play(); // 4
    }
    
    @Override
    public void run() {
        launch();
    }

}
