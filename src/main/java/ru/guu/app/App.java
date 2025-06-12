package ru.guu.app;

import javafx.application.Application;
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import ru.guu.ui.CryptoView;
import com.google.inject.Guice; 
import com.google.inject.Injector;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start (Stage stage) {
        Injector injector = Guice.createInjector(new CryptoModule());
        CryptoView cryptoView = injector.getInstance(CryptoView.class);
        
        Scene scene = new Scene(cryptoView, 650, 700); // Ограничиваем размер окна
        stage.setTitle("Crypto Rates");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}