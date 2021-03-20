package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class Menu extends Stage implements Constants {
    private static final BorderPane pane = new BorderPane();
    private static final VBox vbox = new VBox();

    private final ImageView image;
    private final Media music;
    private final MediaPlayer player;
    private double volume = 0.5;

    private final Button start = new Button("Start");
    private final Button settings = new Button("Settings");
    private final Button exit = new Button("Exit");

    public ImageView getImage(){
        return image;
    }
    public Media getMusic(){
        return music;
    }
    public MediaPlayer getPlayer() {
        return player;
    }
    public double getPlayerVolume(){
        return volume;
    }
    public void setPlayerVolume(double volume){
        this.volume = volume;
        this.player.setVolume(volume);
    }
    public Button getStartButton(){
        return start;
    }
    public Button getSettings() {
        return settings;
    }
    public Button getExit(){
        return exit;
    }

    public Menu(){
        String image_url = "https://i.pinimg.com/originals/fd/3c/cd/fd3ccd7b49e366b4206f5ac7f8fa8dac.gif";
        image = new ImageView(image_url);
        image.setFitWidth(W);
        image.setFitHeight(H);

        String music_path = "source files/music.mp3";
        music = new Media(Paths.get(music_path).toUri().toString());
        player = new MediaPlayer(music);
        player.setAutoPlay(true);
        player.setVolume(getPlayerVolume());
        player.play();

        InnerShadow shadow = new InnerShadow();
        shadow.setOffsetX(4.0f);
        shadow.setOffsetY(4.0f);
        Text text = new Text("Omelette");
        text.setEffect(shadow);
        text.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC,80));
        text.setFill(Color.WHEAT);


        vbox.setSpacing(30);
        vbox.setAlignment(Pos.CENTER);

        buttonInitialize(start);
        buttonInitialize(settings);
        buttonInitialize(exit);

        vbox.getChildren().addAll(text,start,settings,exit);
        pane.getChildren().add(image);
        pane.setCenter(vbox);

    }
    protected void buttonInitialize(Button button){
        button.setMinSize(50,25);
        button.setMaxSize(100,50);
        button.setStyle("-fx-background-color: white; -fx-border-color: cyan");

        button.setOnMouseEntered(e-> button.setStyle("-fx-background-color: yellow; -fx-border-color: black"));
        button.setOnMousePressed(e->{
            InnerShadow shadow = new InnerShadow();
            shadow.setOffsetY(2.0f);
            shadow.setOffsetX(2.0f);
            button.setEffect(shadow);
        });
        button.setOnMouseExited(e->{
            button.setEffect(null);
            button.setStyle("-fx-background-color: white; -fx-border-color: cyan");
        });
    }

    public BorderPane getPane(){
        return pane;
    }

}