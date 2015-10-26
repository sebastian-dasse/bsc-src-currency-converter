package ajaxexample

object Utils extends Utils

trait Utils {
  val Numeric = """[+-]?[0-9]+([.,][0-9]*)?""".r

  implicit class StringWrapper(str: String) {
    def asDouble: Double =
      Numeric.findFirstIn(str).getOrElse("0").replaceAll(",", ".").toDouble
  }

  def format(dbl: Double): String = f"$dbl%.4f"
}
