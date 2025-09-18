package main

import main.domain.ExchangeRateSupportFactory

fun main() {
    val factory = ExchangeRateSupportFactory()
    val renderer = factory.exchangeRateRenderer
    renderer.render()
}
