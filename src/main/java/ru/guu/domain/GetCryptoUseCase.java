// Use Case для получения данных
package ru.guu.domain;

import java.util.List;
import com.google.inject.Inject;
import ru.guu.data.CryptoApi;

public class GetCryptoUseCase {
    private final CryptoApi cryptoApi;

    @Inject
    public GetCryptoUseCase(CryptoApi cryptoApi) {
        this.cryptoApi = cryptoApi;
    }
    
    public List<Crypto> getCryptoRates() {
        return cryptoApi.fetchCryptoRates();
    }   
}
