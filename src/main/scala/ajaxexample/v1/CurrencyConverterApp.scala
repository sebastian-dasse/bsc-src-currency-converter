package ajaxexample.v1

import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.dom.{document, html}
import org.scalajs.dom.ext.KeyCode
import scalatags.JsDom.all._
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow

object CurrencyConverterApp extends JSApp with ajaxexample.Utils {

  def main(): Unit = {
    setupUi()
    println("UI was set up.")
  }

  def setupUi(): Unit = {
    val container = div.render
    document.body.appendChild(container)

    var converter = Converter()

    val inputAmount = input(
      id := "inp-src-amount",
      placeholder:="Your amount here...",
      value:=format(1)
    ).render

    inputAmount.onfocus = (evt: dom.Event) => {
      inputAmount.select()
    }

    inputAmount.onmouseup = (evt: dom.Event) => {
      evt.preventDefault()
    }

    val outputAmount = div(
      id:="out-dst-amount",
      disabled,
      format(1)
    ).render

    def srcAmount = inputAmount.value.asDouble
    def updateSrcAmount(d: Double) = inputAmount.value = format(d)

    /// version 1: with futures
    def updateDstAmount(): Unit = converter.convert(srcAmount).onSuccess{
      case dstAmount => outputAmount.textContent = format(dstAmount)
    }

    /// alternative 2: with async-await
//    import scala.async.Async.{async, await}
//    def updateDstAmount(): Unit = async {
//      val dstAmount = await{ converter.convert(srcAmount) }
//      outputAmount.textContent = format(dstAmount)
//    }

    inputAmount.onkeyup = (evt: dom.KeyboardEvent) => evt.keyCode match {
      case KeyCode.enter => {
        updateSrcAmount(srcAmount)
        updateDstAmount()
      }
      case KeyCode.escape =>
        updateSrcAmount(1)
        inputAmount.select()
      case _ => ()
    }

    inputAmount.onblur = (evt: dom.Event) => {
      updateSrcAmount(srcAmount)
    }

    def setupCurrencySelect(theId: String, callback: String => Unit, defaultOption: String = "EUR"): html.Select = {
      val sel = select(
        id:=theId,
        for ((code, name) <- Converter.currencies.toSeq) yield option(
          id:=s"$theId-opt-${code.toLowerCase}",
          value:=code, name
        )
      ).render
      sel.onchange = (e: dom.Event) => callback(sel.value)
      sel.value = defaultOption
      sel
    }

    container.appendChild(
      div(
        h1("Currency Converter"),
        form(
          id:="form-conv",
          onsubmit:={ (evt: dom.Event) => evt.preventDefault() },
          inputAmount,
          setupCurrencySelect("sel-src-curr", c => converter = converter.copy(srcCurr = c)),
          outputAmount,
          setupCurrencySelect("sel-dst-curr", c => converter = converter.copy(dstCurr = c)),
          button(
            id:="btn-do-get",
            cls:="input-btn",
            "Convert",
            onclick:={ () => updateDstAmount() }
          )
        )
      ).render
    )
  }
}
