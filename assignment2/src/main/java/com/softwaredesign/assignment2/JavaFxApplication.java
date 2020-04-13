package com.softwaredesign.assignment2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.softwaredesign.assignment2.presentation.controller.LoginController;

public class JavaFxApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;
    private static Stage window;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);
        this.applicationContext = new SpringApplicationBuilder()
                .sources(Assignment2Application.class)
                .run(args);
    }

    @Override
    public void start(Stage stage) {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LoginController.class);
        window = stage;

        Scene scene = new Scene(root);
        window.setScene(scene);
        window.setTitle("Assignment2");
        window.show();
    }

    public static Scene changeScene(Class controller){
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent pane = fxWeaver.loadView((Class<Object>) controller);

        //Stage stage = new Stage();
        Scene newScene = new Scene(pane);
        window.setScene(newScene);
        window.show();

        return newScene;
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

}
