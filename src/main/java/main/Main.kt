package main

import main.infrastructure.StandardInputExchangeRateProvider
import main.infrastructure.StandardOutputExchangeRateRenderer

fun main() {
    val provider = StandardInputExchangeRateProvider()
    val renderer = StandardOutputExchangeRateRenderer()

    renderer.setExchangeRateProvider(provider);
    renderer.render();
}
