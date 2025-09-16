package main.infrastructure

import main.domain.ExchangeRateProvider
import main.domain.ExchangeRateRenderer

class StandardOutputExchangeRateRenderer : ExchangeRateRenderer {

    private lateinit var provider: ExchangeRateProvider;

    override fun render() {
        println("1달러 환율: %.2f원%n".format(provider.getExchangeRate()))
    }

    override fun setExchangeRateProvider(provider: ExchangeRateProvider) {
        this.provider = provider;
    }

    override fun getExchangeRateProvider(): ExchangeRateProvider {
        return provider;
    }
}
