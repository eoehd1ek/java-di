package main.infrastructure

import main.domain.ExchangeRateProvider
import java.util.*

class StandardInputExchangeRateProvider : ExchangeRateProvider {

    private val scanner = Scanner(System.`in`)

    override fun getExchangeRate(): Double {
        println("환율을 입력해주세요.")
        return scanner.nextDouble();
    }
}
