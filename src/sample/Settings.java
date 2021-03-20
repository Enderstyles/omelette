package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.io.File;

public class Settings implements Constants{
    private final BorderPane main;
    private final GridPane center;
    private final Button back;
    private final Slider volumeSlider;
    private final Text volumeText;
    private final CheckBox musicIs;
    private boolean musicStatus;

    public Settings(){
        musicStatus = true;
        main = new BorderPane();
        center = new GridPane();
        musicIs = new CheckBox();

        musicIs.setText("Music");
        musicIs.setStyle(
                "-fx-font-size: 20;" +
                "-fx-stroke: yellow;" +
                "-fx-font-weight: 800;"
        );

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.DARKGRAY);
        volumeText = new Text("Volume");
        //volumeText.setFill(Color.WHITE);
        volumeText.setStyle(
                            "-fx-stroke: white;" +
                            "-fx-font-size: 30;" +
                            "-fx-font-weight: 800;"+
                            "-fx-font-style: italic"
        );
        //volumeText.setFont(Font.font(null, FontPosture.ITALIC,));
        //volumeText.setEffect(dropShadow);

        volumeSlider = new Slider();
        volumeSlider.setValue(100);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setPrefWidth(250);

        ImageView back_image = new ImageView(new Image(new File("source files/back icon.png").toURI().toString()));
        back_image.setFitHeight(30);
        back_image.setFitWidth(30);
        back = new Button();
        back.setGraphic(back_image);
        //back.setAlignment(Pos.TOP_LEFT);
        back.setStyle(
                "-fx-border-radius: 5;" +
                        "-fx-background-radius: 5;" +
                        "-fx-font-size: 20;"  +
                        "-fx-border-color: yellow;" +
                        "-fx-text-alignment: center"
        );
        back.setMinSize(40,45);


        center.setHgap(20);
        center.setVgap(20);
        center.setPadding(new Insets(200,200,200,200));
        center.setAlignment(Pos.CENTER);
        center.add(volumeText,0,0);
        center.add(volumeSlider,0,1);
        center.add(musicIs,0,2);
        main.setTop(back);
        main.setCenter(center);
    }
    public BorderPane getMain(){
        return main;
    }
    public GridPane getCenter() {
        return center;
    }
    public Slider getVolumeSlider(){
        return volumeSlider;
    }
    public Button getBack(){
        return back;
    }
    public Text getVolumeText(){
        return volumeText;
    }
    public void setElement(Node element, int col, int row){
        center.add(element, col, row);
    }
    public void clear(){
        center.getChildren().clear();
    }
    public CheckBox getMusicIs(){
        return musicIs;
    }
    public void setMusicStatus(boolean is) {
        musicStatus = is;
    }
    public boolean getMusicStatus(){
        return musicStatus;
    }
}