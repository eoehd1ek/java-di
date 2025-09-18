package main.domain

import java.util.*

class ExchangeRateSupportFactory {

    private val properties: Properties
    val exchangeRateProvider: ExchangeRateProvider
    val exchangeRateRenderer: ExchangeRateRenderer

    init {
        this.properties = Properties()
        try {
            // resource 디렉터리에서 exchange-rate.properties 파일을 InputStream으로 가져온다.
            val resourceStream = this.javaClass.getResourceAsStream("/exchange-rate.properties")

            // 불러온 파일을 properties 객체에 저장한다.
            properties.load(resourceStream)

            // exchange-rate.properties 파일에서 provider.class와 renderer.class 값을 가져온다.
            // providerClass, rendererClass의 값은 객체로 만들 클래스 이름이다.
            val providerClass = properties.getProperty("provider.class")
            val rendererClass = properties.getProperty("renderer.class")

            // 클래스 이름으로 ExchangeRateProvider와 ExchangeRateRenderer 객체를 생성한다.
            this.exchangeRateProvider = Class.forName(providerClass).getDeclaredConstructor()
                .newInstance() as ExchangeRateProvider
            this.exchangeRateRenderer = Class.forName(rendererClass).getDeclaredConstructor()
                .newInstance() as ExchangeRateRenderer

            // 객체를 연결한다.
            exchangeRateRenderer.setExchangeRateProvider(exchangeRateProvider)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
