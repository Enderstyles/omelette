package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.File;

public class StartGame implements Constants{
    private final BorderPane pane;
    private final Pane center_pane;
    private final Button back;
    private final Button power_on;
    private final Button place_pan;
    private final Button break_an_egg;
    private final ImageView done_egg;
    private final ImageView pan;
    private final ImageView stove;
    private final ImageView[] egg_default;
    private boolean egg_on_pan;
    private boolean[] egg_status;
    private boolean isOn;
    private boolean isPlaced;
    private boolean wasPlaced;

    public StartGame(){
        isOn = false;
        isPlaced = false;
        wasPlaced = false;
        egg_on_pan = false;

        String table_texture = "source files/table_texture.jpg";
        ImageView table = new ImageView(new Image(new File(table_texture).toURI().toString()));

        String pan_path = "source files/pan.png";
        pan = new ImageView(new Image(new File(pan_path).toURI().toString()));
        {
            pan.setRotate(80);
            pan.setLayoutY(250);
            pan.setLayoutX(-100);
        }

        String stove_path = "source files/stove.png";
        stove = new ImageView(new Image(new File(stove_path).toURI().toString()));
        {
            stove.setFitHeight(900);
            stove.setFitWidth(800);
            stove.setLayoutX(100);
            stove.setLayoutY(-100);
        }

        String egg_default_path = "source files/egg.png";
        egg_status = new boolean[3];
        egg_default = new ImageView[3];
        {
            for (int i = 0; i < 3; i++) {
                egg_status[i] = false;
                egg_default[i] = new ImageView(new Image(new File(egg_default_path).toURI().toString()));
                egg_default[i].setFitHeight(100);
                egg_default[i].setFitWidth(100);
                egg_default[i].setLayoutX(900);
                egg_default[i].setLayoutY(100 * i);
            }
        }

        String done_egg_path = "source files/done egg.png";
        done_egg = new ImageView(new Image(new File(done_egg_path).toURI().toString()));

        pane = new BorderPane();
        center_pane = new Pane();

        power_on= new Button("Power on");
        power_on.setStyle(
                "-fx-background-color: red;" +
                        "-fx-text-fill: white;");
        power_on.setFont(Font.font(null, FontWeight.EXTRA_BOLD, FontPosture.ITALIC,15));
        power_on.setOnMouseClicked(e->{
            InnerShadow shadow = new InnerShadow();
            shadow.setOffsetX(2.0);
            shadow.setOffsetY(2.0);
            power_on.setEffect(shadow);
        });
        power_on.setOnMouseExited(e->power_on.setEffect(null));

        ImageView back_icon = new ImageView(new Image(new File("source files/back icon.png").toURI().toString()));
        back_icon.setFitHeight(30);
        back_icon.setFitWidth(30);
        back = new Button();
        back.setGraphic(back_icon);
        //back.setAlignment(Pos.TOP_LEFT);
        back.setStyle(
                "-fx-border-radius: 5;" +
                "-fx-background-radius: 5;" +
                "-fx-font-size: 20;"  +
                "-fx-border-color: yellow;" +
                "-fx-text-alignment: center"
        );
        back.setMinSize(40,45);


        place_pan = new Button("Place pan");
        break_an_egg = new Button("Break an egg");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.getChildren().addAll(power_on, place_pan, break_an_egg);

        center_pane.getChildren().addAll(table, back, stove, pan, egg_default[0],egg_default[1], egg_default[2]);
        pane.setCenter(center_pane);
        pane.setBottom(hbox);
    }
    public BorderPane getPane(){
        return pane;
    }
    public Pane getCenter_pane() {
        return center_pane;
    }
    public Button getBack() {
        return back;
    }
    public Button getPowerOn(){
        return power_on;
    }
    public Button getPlacePan(){
        return place_pan;
    }
    public Button getBreakAnEgg(){
        return break_an_egg;
    }
    public ImageView getDoneEgg(){
        return done_egg;
    }
    public ImageView[] getDefEgg(){
        return egg_default;
    }
    public ImageView getPan(){
        return pan;
    }
    public ImageView getStove(){
        return stove;
    }
    public boolean isOn(){
        return isOn;
    }
    public void setIsOn(boolean is){
        isOn = is;
    }
    public boolean isPlaced(){
        return isPlaced;
    }
    public void setIsPlaced(boolean is){
        isPlaced = is;
    }
    public boolean getWasPlaced(){
        return wasPlaced;
    }
    public void setWasPlaced(boolean is){
        wasPlaced = is;
    }
    public boolean getEggStatus(int i){
        return egg_status[i];
    }
    public void setEggStatus(int i, boolean is){
        egg_status[i] = is;
    }
    public boolean eggOnPan(){
        return egg_on_pan;
    }
    public void setEggOnPan(boolean is){
        egg_on_pan = is;
    }
}