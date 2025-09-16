package main.domain

interface ExchangeRateProvider {
    fun getExchangeRate(): Double
}
