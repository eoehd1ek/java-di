package main

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    
    println("환율을 입력해주세요.")
    val exchangeRate = scanner.nextDouble()
    println("1달러 환율: %.2f원".format(exchangeRate))
}
