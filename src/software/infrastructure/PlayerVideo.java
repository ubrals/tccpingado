package software.infrastructure;

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

public class PlayerVideo extends Application implements Runnable {

    // private String VIDEO_URL =
    // "file:///Users/ubrals/git/Pingado/PvsNP_480p.mp4";
    private String VIDEO_URL = "http://localhost/PvsNP_480p.mp4";

    public PlayerVideo() {
    }

    public static void main(String[] args) {
        Runnable runnable = new PlayerVideo();
        Thread th = new Thread(runnable);
        th.start();
    }

    @Override
    public void start(Stage palco) throws Exception {
        Media media = new Media(VIDEO_URL); // 1
        MediaPlayer mediaPlayer = new MediaPlayer(media); // 2
        MediaView mediaView = new MediaView(mediaPlayer); // 3

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
                    System.out.println("H:" + media.getHeight() + " W:" + media.getWidth());
                    System.out
                            .println("mediaPlayer.getStopTime().toSeconds()=" + mediaPlayer.getStopTime().toSeconds());
                    System.out.println("Math.ceil(mediaPlayer.getStopTime().toSeconds())="
                            + Math.ceil(mediaPlayer.getStopTime().toSeconds()));
                    try {
                        System.out.println(
                                "tempo total=" + mediaPlayer.getTotalDuration().toSeconds() + " " +
                                "mediaPlayer.getCurrentTime().toSeconds()=" + mediaPlayer.getCurrentTime().toSeconds()
                                        + " ceil()=" + Math.ceil(mediaPlayer.getCurrentTime().toSeconds()));
                        Runtime.getRuntime().exec("java -jar dist/Send.jar " + "localhost " + mediaPlayer.getCurrentTime().toSeconds());
                    } catch (Exception e1) {
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
                    Runtime.getRuntime().exec("java -jar dist/Send.jar " + "localhost " + "quit");
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
