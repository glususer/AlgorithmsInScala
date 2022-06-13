val aPartialFunction = PartialFunction[Int,Int] {
  case 1 =>2
  case 2 =>3
  case 5 => 7
}

aPartialFunction(1)

aPartialFunction.isDefinedAt(2)
aPartialFunction.isDefinedAt(324123)

val lifted = aPartialFunction.lift

println(lifted(2))
//println(lifted(234234))

val pfChain = aPartialFunction.orElse[Int,Int]{
  case 45 => 58
}

val totalFunction : Int => Int= {
  case 23 =>45
  case _ => 33
}
println(pfChain(5))

val chatBot = new PartialFunction[String,String]{
  override def apply(v1: String): String = v1 match{
    case "Hi" => "Hello"
    case "How are you" => "I am fine"
    case "Today is sunny" => "Yes it is"
  }

  override def isDefinedAt(x: String): Boolean = ???

}

chatBot("Today is sunny")