package main.infrastructure

import main.domain.ExchangeRateProvider
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.UncheckedIOException
import java.net.HttpURLConnection
import java.net.URI
import java.util.regex.Pattern

class DaumExchangeRateProvider : ExchangeRateProvider {

    override fun getExchangeRate(): Double {
        val httpURLConnection: HttpURLConnection = connect()
        val responseBody: String = readResponseBody(httpURLConnection)
        val pattern = Pattern.compile("\"basePrice\":(\\d+\\.?\\d*)")
        val matcher = pattern.matcher(responseBody)
        if (matcher.find()) {
            return matcher.group(1).toDouble()
        }
        return 0.0
    }

    companion object {
        private const val DAUM_EXCHANGES_API_URL = "https://finance.daum.net/api/exchanges/FRX.KRWUSD"
        private const val DAUM_EXCHANGES_URL = "https://finance.daum.net/exchanges/FRX.KRWUSD"

        // 연결한 HTTP 응답의 본문을 읽어온다.
        private fun readResponseBody(httpURLConnection: HttpURLConnection): String {
            try {
                InputStreamReader(httpURLConnection.getInputStream()).use { inputStreamReader ->
                    BufferedReader(inputStreamReader).use { bufferedReader ->
                        val builder = StringBuilder()
                        var line: String?
                        while ((bufferedReader.readLine().also { line = it }) != null) {
                            builder.append(line)
                        }
                        return builder.toString()
                    }
                }
            } catch (e: IOException) {
                throw UncheckedIOException(e)
            }
        }

        // Daum API에 연결한다.
        private fun connect(): HttpURLConnection {
            try {
                val url = URI.create(DAUM_EXCHANGES_API_URL).toURL()
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.setRequestMethod("GET")
                httpURLConnection.setRequestProperty("referer", DAUM_EXCHANGES_URL)
                val responseCode = httpURLConnection.getResponseCode()
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    throw RuntimeException("HTTP 응답 코드: " + responseCode)
                }
                return httpURLConnection
            } catch (e: IOException) {
                throw UncheckedIOException(e)
            }
        }
    }
}
