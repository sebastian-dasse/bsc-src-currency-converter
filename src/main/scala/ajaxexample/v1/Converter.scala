package ajaxexample.v1

import scala.scalajs.js
import scala.collection.SortedMap
import org.scalajs.dom.ext.Ajax
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.concurrent.Future
import scala.async.Async.{async, await}

case class Converter(
  srcCurr: String = "EUR",
  dstCurr: String = "EUR"
) {
  def convert(srcAmount: Double): Future[Double] = async{
    srcAmount * await{ rate }
  }

  private def url = "http://query.yahooapis.com/v1/public/yql?q=select * from yahoo.finance.xchange where pair in ('" +
    srcCurr + dstCurr + "')&format=json&env=store://datatables.org/alltableswithkeys"

  private lazy val rate: Future[Double] = Ajax.get(url).map{ case xhr =>
    val json: js.Dynamic = js.JSON.parse(xhr.responseText)
    assert(json.query.results.rate.id.toString == srcCurr + dstCurr)
    json.query.results.rate.Rate.toString.toDouble
  }
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
