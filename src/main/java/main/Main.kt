package main

import main.domain.ExchangeRateRenderer
import org.springframework.context.support.ClassPathXmlApplicationContext

fun main() {
    val context = ClassPathXmlApplicationContext("exchange-rate-context.xml")
    val renderer = context.getBean("exchangeRateRenderer", ExchangeRateRenderer::class.java)
    renderer.render()
}
