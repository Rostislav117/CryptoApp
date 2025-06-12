//Модель представления
package ru.guu.ui;

import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject; 
import ru.guu.domain.Crypto;
import ru.guu.domain.GetCryptoUseCase;

public class CryptoViewModel {
    private final GetCryptoUseCase getCryptoUseCase;
    private List<Crypto> cryptoRates;

    @Inject
    public CryptoViewModel(GetCryptoUseCase getCryptoUseCase) {
        this.getCryptoUseCase = getCryptoUseCase;
        loadCryptoRates(); // Загружаем данные при инициализации
    }

    private void loadCryptoRates () {
        cryptoRates = getCryptoUseCase.getCryptoRates();
        System.out.println("Loaded crypto rates: " + cryptoRates);
    }

    public List<Crypto> getCryptoRates() {
        return cryptoRates;
    }
    public List<Crypto> filterCryptoRates(String query) {
        if (query == null || query.isEmpty()) {
            return cryptoRates; 
        }
        return cryptoRates.stream()
                .filter(crypto -> crypto.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
