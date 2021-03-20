package sample;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

interface Constants {
    int W = 1000;
    int H = 700;
}

public class Main extends Application implements Constants {
    @Override
    public void start(Stage primaryStage) {
        BorderPane pain = new BorderPane();
        Menu menu = new Menu();
        StartGame start = new StartGame();
        Settings settings = new Settings();

        pain.setCenter(menu.getPane());

        Scene program = new Scene(pain, W, H);
        program.setFill(Color.BLACK);

        actions(menu, start, settings, pain);

        File icon = new File("B://JavaProjects/Omelette/source files/icon.jpg");
        primaryStage.getIcons().add(new Image(icon.toURI().toString()));
        primaryStage.setTitle("Omelette");
        primaryStage.setScene(program);
        primaryStage.show();
    }

    public void actions(Menu menu, StartGame start, Settings settings, BorderPane pane) {
        menuActions(menu, start, settings, pane);
        startActions(menu, start, settings,pane);
        settingsActions(menu, settings, pane);
    }

    public void menuActions(Menu menu, StartGame start, Settings settings, BorderPane pane) {
        menu.getStartButton().setOnAction(e -> {
            //if (menu.getPlayerVolume() >= 0.5) {
            //    menu.getPlayer().setVolume(0.3);
            //}
            pane.getChildren().clear();
            pane.setCenter(start.getPane());
        });
        menu.getSettings().setOnAction(e -> {
            ImageView background = new ImageView(menu.getImage().getImage());

            GaussianBlur blur = new GaussianBlur();
            blur.setRadius(30);
            background.setEffect(blur);
            background.setFitWidth(W);
            background.setFitHeight(H);

            pane.getChildren().clear();
            pane.getChildren().add(background);
            pane.setCenter(settings.getMain());

        });
        menu.getExit().setOnAction(e -> System.exit(0));
    }

    public void startActions(Menu menu, StartGame start, Settings settings, BorderPane pane) {
        start.getBack().setOnAction(e -> {
            //if (menu.getPlayerVolume() < 0.5) {
            //    menu.setPlayerVolume(menu.getPlayerVolume());
            //    settings.getVolumeSlider().setValue(50);
            //}
            pane.getChildren().clear();
            pane.setCenter(menu.getPane());
        });
        start.getPane().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                if (menu.getPlayerVolume() < 0.5) {
                    //menu.setPlayerVolume(0.5);
                    settings.getVolumeSlider().setValue(0.5);
                }
                pane.getChildren().clear();
                pane.setCenter(menu.getPane());
            }
        });
        start.getPowerOn().setOnAction(e -> {
            if (!start.isOn()) {
                start.setIsOn(true);
                start.getPowerOn().setStyle("-fx-background-color: lime");
                start.getPowerOn().setText("Power off");
            }
            else if (!start.isPlaced()) {
                start.setIsOn(false);
                start.getPowerOn().setStyle("-fx-background-color: red");
                start.getPowerOn().setText("Power on");
            }
        });
        start.getPlacePan().setOnAction(e -> {
            TranslateTransition animation = new TranslateTransition(Duration.seconds(1));
            animation.setNode(start.getPan());

            RotateTransition rotate = new RotateTransition(Duration.seconds(1));
            rotate.setNode(start.getPan());

            if (!start.isPlaced() && start.isOn()) {
                start.setIsPlaced(true);
                start.setWasPlaced(true);

                rotate.setByAngle(40);

                animation.setToX(200);
                animation.setToY(-180);

                start.getPan().setRotate(120);
                start.getPlacePan().setText("Take away");
            }
            else if (start.getWasPlaced() && start.isPlaced() && !start.eggOnPan()) {

                start.setIsPlaced(false);

                rotate.setByAngle(-60);

                animation.setToX(-100);
                animation.setToY(80);

                start.getPlacePan().setText("Place pan");
            }
            rotate.play();
            animation.play();
        });
        start.getBreakAnEgg().setOnAction(e -> {
            start.setEggOnPan(true);
            if (start.isPlaced()) {
                TranslateTransition animation = new TranslateTransition(Duration.seconds(1));
                RotateTransition rotate = new RotateTransition(Duration.seconds(1));
                int x;
                int y;

                for (int i = 0; i < 3; i++) {
                    if (i == 0) {
                        y = 150;
                        x = -390;
                    } else if (i == 2) {
                        y = 50;
                        x = -500;
                    } else {
                        y = 100;
                        x = -450;
                    }
                    if (!start.getEggStatus(i)) {
                        start.setEggStatus(i, true);
                        animation.setNode(start.getDefEgg()[i]);
                        animation.setToX(x);
                        animation.setToY(y);
                        break;
                    }
                }
                animation.play();
                rotate.play();
            }
        });

    }

    public void settingsActions(Menu menu, Settings settings, BorderPane pane) {
        settings.getVolumeSlider().setValue(menu.getPlayer().getVolume() * 100);
        settings.getVolumeSlider().valueProperty().addListener(l -> menu.getPlayer().setVolume(settings.getVolumeSlider().getValue() / 100));

        settings.getMusicIs().setSelected(true);
        settings.getMusicIs().setOnAction(e -> {
            if (settings.getMusicIs().isSelected() && settings.getMusicStatus()) {
                menu.getPlayer().play();
            } else if (!settings.getMusicIs().isSelected()) {
                menu.getPlayer().pause();
            }
        });
        settings.getMain().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                pane.getChildren().clear();
                pane.setCenter(menu.getPane());
                System.out.println("Pressed");
            }
        });
        settings.getBack().setOnAction(e->{
            if (menu.getPlayerVolume() < 0.5) {
                menu.setPlayerVolume(0.5);
                settings.getVolumeSlider().setValue(0.5);
            }
            pane.getChildren().clear();
            pane.setCenter(menu.getPane());
        });
    }
}