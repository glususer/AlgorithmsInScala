package advancedscala.implicits

object MagnetPattern extends App{

  trait MathLibrary{
    def add1(x: Int):Int = x+1
    def add1(x:String):Int= x.toInt+1
  }

}
