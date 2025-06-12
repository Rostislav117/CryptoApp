//Конфигурация Guice
package ru.guu.app;

import com.google.inject.AbstractModule;
import ru.guu.data.CryptoApi;
import ru.guu.data.CryptoApiImpl;
import ru.guu.domain.GetCryptoUseCase;
import ru.guu.ui.CryptoView;
import ru.guu.ui.CryptoViewModel;

public class CryptoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CryptoApi.class).to(CryptoApiImpl.class);
        bind(GetCryptoUseCase.class); 
        bind(CryptoViewModel.class); 
        bind(CryptoView.class);
    }
}
