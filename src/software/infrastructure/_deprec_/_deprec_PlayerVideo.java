package software.infrastructure._deprec_;

import entities.ISP;
import entities.Party;
import entities.values.ContentDelivered;

///
//adaptado de:
//http://aprendendo-javafx.blogspot.com.br/2012/07/tocando-audio-e-video.html

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class _deprec_PlayerVideo extends Application implements Runnable {

    private Party isp;
    private ContentDelivered contentDelivered;
    private String time;
    private long sleep;
    // private String VIDEO_URL =
    // "file:///Users/ubrals/git/Pingado/PvsNP_480p.mp4";
//    private String VIDEO_URL = "http://localhost/PvsNP_480p.mp4";
    private String VIDEO_URL;
//    private static long econtractId;

//    public PlayerVideo(){
//    }
    
    public _deprec_PlayerVideo(Party isp, ContentDelivered contentDelivered, String VIDEO_URL) {
        this.isp=isp;
        this.contentDelivered=contentDelivered;
        this.VIDEO_URL=this.contentDelivered.getUrl();
        this.time = this.contentDelivered.getTimeReference();
        switch (time) {
        case "DAY":    this.sleep=1000l * 60l * 60l * 24l; break;
        case "HOUR":   this.sleep=1000l * 60l * 60l; break;
        case "MINUTE": this.sleep=1000l * 60l; break;
        case "SECOND": this.sleep=1000l; break;
        case "MILLISECOND": this.sleep=1l; break;
        default: this.sleep=1000l * 60l; break;
        }
        System.err.println("<<create>>.sleep=" + sleep);
    }
    
    
/*
    public static void main(String[] args) {
        Runnable runnable = new PlayerVideo();
        Thread th = new Thread(runnable);
//        try { econtractId = Long.parseLong(args[0]); }
//        catch (NumberFormatException e) {        }
//        finally { }
        th.start();
    }
*/
    
    @Override
    public void start(Stage palco) throws Exception {
        _deprec_PlayerVideo pv = null;// = new PlayerVideo(null, contentDelivered, VIDEO_URL);
        Media media = new Media(VIDEO_URL); // 1
        MediaPlayer mediaPlayer = new MediaPlayer(media); // 2
        MediaView mediaView = new MediaView(mediaPlayer); // 3
        // Reset
        Party isp=pv.isp;
        ContentDelivered contentDelivered=pv.contentDelivered;
        long sleep=pv.sleep;
        System.err.println("start.sleep=" + sleep);

        StackPane raiz = new StackPane();
        raiz.getChildren().add(mediaView); // 4

        int width = media.getWidth();
        int height = media.getHeight();
        Scene cena = new Scene(raiz, 854, 480);
        palco.setTitle("Tocando Video em JavaFX");
        palco.setScene(cena);
        palco.show();
        // palco.setHeight(height);
        // palco.setWidth(width);

        // System.out.println("H:" + media.getHeight() + " W:" +
        // media.getWidth());
        // System.out.println(mediaPlayer.getStopTime().toMinutes());

        ///////////////////////////////////////////////
        // Aqui esta pegando o tempo com
        /////////////////////////////////////////////// mediaPlayer.getCurrentTime().toSeconds()
        Runnable runplay = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(mediaPlayer.getCurrentTime().toSeconds() >= mediaPlayer.getTotalDuration().toSeconds())
                        break;
//                    System.out.println("H:" + media.getHeight() + " W:" + media.getWidth());
//                    System.out
//                            .println("mediaPlayer.getStopTime().toSeconds()=" + mediaPlayer.getStopTime().toSeconds());
//                    System.out.println("Math.ceil(mediaPlayer.getStopTime().toSeconds())="
//                            + Math.ceil(mediaPlayer.getStopTime().toSeconds()));
                    try {
//                        System.err.println("..:DBG:sleep=" + sleep + " >>" + contentDelivered.getTimeReference());
                        System.err.println("..:DBG:Runnable.sleep=" + sleep + " >>" + contentDelivered.getTimeReference());
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
//                        System.out.println(
//                                "tempo total=" + mediaPlayer.getTotalDuration().toSeconds() + " " +
//                                "mediaPlayer.getCurrentTime().toSeconds()=" + mediaPlayer.getCurrentTime().toSeconds()
//                                        + " ceil()=" + Math.ceil(mediaPlayer.getCurrentTime().toSeconds()));
                        //////
//                        Runtime.getRuntime().exec("java -jar dist/Send.jar " + "localhost " + mediaPlayer.getCurrentTime().toSeconds() ); //+ " " + econtractId);
                        ((ISP)isp).chargeDeliveredContent(contentDelivered);
                        //////
                    } catch (Exception e1) {
                    	e1.printStackTrace();
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("getCurrentTime:ss="+ mediaPlayer.getCurrentTime().toSeconds());
                try{
                    ///////
//                    Runtime.getRuntime().exec("java -jar dist/Send.jar " + "localhost " + "quit");
                    ((ISP)isp).chargeDeliveredContent(contentDelivered);
                    ///////
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
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
