package advancedscala.implicits

object PimpMyLibrary extends App{
// compiler does not do multiple implicit searches
  implicit class RichInt(val value:Int) extends AnyVal {
    def isEven : Boolean = value%2==0
    def sqrt : Double = Math.sqrt(value)
  }

  new RichInt(42)
  42.isEven

  1 to 10
}
