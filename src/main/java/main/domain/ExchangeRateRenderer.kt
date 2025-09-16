package main.domain

interface ExchangeRateRenderer {
    fun render()
    fun setExchangeRateProvider(provider: ExchangeRateProvider)
    fun getExchangeRateProvider(): ExchangeRateProvider
}
