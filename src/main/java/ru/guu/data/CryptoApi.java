// Интерфейс для получения данных
package ru.guu.data;

import java.util.List;
import ru.guu.domain.Crypto;

public interface CryptoApi {
    List<Crypto> fetchCryptoRates();
} 
