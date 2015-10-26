package ajaxexample.v0

import scala.collection.SortedMap

class Converter(
  var srcCurr: String = "EUR",
  var dstCurr: String = "EUR",
  var rate: Double = 1.0
) {
  def convert(srcAmount: Double): Double = srcAmount * rate
}

object Converter {
  val currencies: SortedMap[String, String] = SortedMap(
    "EUR" -> "Euro",
    "CHF" -> "Swiss Franc",
    "USD" -> "US Dollar",
    "AUD" -> "Australian Dollar",
    "BGN" -> "Bulgarian Lev",
    "BRL" -> "Brazilian Real",
    "CAD" -> "Canadian Dollar",
    "CNY" -> "Chinese Renminbi",
    "CZK" -> "Czech Koruna",
    "DKK" -> "Danish Krone",
    "GBP" -> "British Pound",
    "HRK" -> "Croatian Kuna",
    "HKD" -> "Honk Kong Dollar",
    "HUF" -> "Hungarian Forint",
    "IDR" -> "Indonesian Rupiah",
    "ILS" -> "Israli Shekel",
    "ISK" -> "Islandic Krona",
    "INR" -> "Indian Rupee",
    "JPY" -> "Japanase Yen",
    "KRW" -> "South Korean Won",
    "LTL" -> "Lithuanian Litas",
    "LVL" -> "Latvian Lat",
    "MXN" -> "Mexican Peso",
    "MYR" -> "Malaysian Ringgit",
    "NZD" -> "New Zealand Dollar",
    "NOK" -> "Norwegian Krone",
    "PHP" -> "Philippine Peso",
    "PLN" -> "Polish Zloty",
    "RON" -> "Romanian New Leu",
    "RUB" -> "Russian Ruble",
    "SEK" -> "Swedish Krona",
    "SGD" -> "Singapore Dollar",
    "THB" -> "Thai Baht",
    "TRY" -> "Turkish Lira",
    "ZAR" -> "South African Rand"
  )
}
