module ru.guu {
    // Требования к JavaFX модулям 
    requires javafx.controls;
    requires javafx.fxml;

    // Требования к используемым библиотекам
    requires com.google.gson; 
    requires com.google.guice;
    requires javafx.base;
    requires javafx.graphics;

    // Экспортируемые пакеты
    exports ru.guu.app; 
    exports ru.guu.data; 
    exports ru.guu.domain;
    exports ru.guu.ui;
    exports ru.guu.utils;

    // Открываем для рефлексии (нужно для Guice)
    opens ru.guu.app to com.google.guice;
    opens ru.guu.data to com.google.gson, com.google.guice; 
    opens ru.guu. domain to com.google.guice; 
    opens ru.guu.ui to javafx.fxml, com.google.guice;

}
