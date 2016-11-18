package Hackerrank

/**
  * Created by shivangi on 15/11/16.
  */
object ValidatingRomanNumerals {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if(!input.isEmpty) {
      if (input.head.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$")) println("True")
      else println("False")
    }
  }

  val stdinString = "III"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  ValidatingRomanNumerals.main(null)

}
