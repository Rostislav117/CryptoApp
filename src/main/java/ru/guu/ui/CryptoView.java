//Представление (View)
package ru.guu.ui;

import java.util.List;

import com.google.inject.Inject;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.guu.domain.Crypto;

public class CryptoView extends ScrollPane {
    private final VBox root;
    private final CryptoViewModel viewModel;
    private final TextField searchField;
    

    @Inject
    public CryptoView(CryptoViewModel viewModel) {
        this.viewModel = viewModel;
        root = new VBox(10); // Контейнер для карточек
        root.setPadding(new Insets(20));

        // Поле для поиска
        searchField = new TextField();
        searchField.setPromptText("Поиск криптовалюты...");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterCryptoRates(newValue));

        root.getChildren().add(searchField); // Добавляем поле поиска в контейнер

    
        // Получаем список криптовалют из ViewModel и отображаем его
        List<Crypto> cryptoRates = viewModel.getCryptoRates();
        if (cryptoRates != null && !cryptoRates. isEmpty()) {
            for (Crypto crypto : cryptoRates) {
                root.getChildren().add(createCryptoCard(crypto));
            }
        } else {
            root.getChildren().add(new Text("No data available"));
        }

        // Устанавливаем ScrollPane с ограничением по размеру
        this.setContent(root);
        this.setFitToWidth(true); // Карточки будут растягиваться по ширине 
        this.setMaxWidth(600); // Ограничение по ширине
        this.setMaxHeight(600); // Ограничение по высоте
        
        // Настроим прокрутку, если контента слишком много
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER) ;
    }

    //фильтр для поиска
    private void filterCryptoRates(String query) {
        List<Crypto> filteredRates = viewModel.filterCryptoRates(query);
        updateCryptoDisplay(filteredRates);
    }

    private void updateCryptoDisplay(List<Crypto> cryptoRates) {
        // Удаляем старые карточки
        root.getChildren().removeIf(node -> node instanceof HBox);
        
        // Удаляем текст "Нет доступных данных", если он есть
        root.getChildren().removeIf(node -> node instanceof Text && ((Text) node).getText().equals("Нет доступных данных"));
        
        // Проверяем, есть ли доступные данные
        if (cryptoRates != null && !cryptoRates.isEmpty()) {
            for (Crypto crypto : cryptoRates) {
                root.getChildren().add(createCryptoCard(crypto));
            }
        } else {
            // Добавляем текст, если нет доступных данных
            root.getChildren().add(new Text("Нет доступных данных"));
        }
    }

    // Метод для создания карточки
    private HBox createCryptoCard (Crypto crypto) {
        HBox card = new HBox(20);
        card.setPadding(new Insets(15));
        card.setSpacing(10);
    
        // Устанавливаем фон, закругление и обводку
        card.setBackground (new Background (new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY))); 
        card.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii (10), BorderWidths.DEFAULT)));
        
        // Добавляем тень
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.GRAY); 
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2); 
        dropShadow.setRadius(5); 
        card.setEffect(dropShadow);
        
        // Название криптовалюты
        Text name = new Text(crypto.getName());
        name.setFont(Font.font("Arial", 18)); 
        name.setFill(Color.BLACK);

        // Цена криптовалюты
        Text price = new Text(String.format("%.2f USD", crypto.getPriceUsd()));
        price.setFont(Font.font("Arial", 16)); 
        price.setFill(Color.DARKGREEN);
        
        card.getChildren().addAll(name, price);
        return card;
    }
}

