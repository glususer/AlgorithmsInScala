

lazy val x:Int = throw new RuntimeException

def sideEffectCondition: Boolean={
  println("Boo")
  true
}

def simpleCondition:Boolean={
  false
}

lazy val lazyCondition = sideEffectCondition

println(if(simpleCondition && lazyCondition) "yes" else "no")

def byNameMethod(n: => Int):Int = {
  // CALL BBY NEED
  lazy val t = n
  t+t+t+1
}

def retrieveMagicValue={
  println("waiting")
  Thread.sleep(1000)
  42
}

byNameMethod(retrieveMagicValue)

def lessThan30(l:Int):Boolean={
  println("$l is less than 30 ?")
  l<30
}

def greaterThan30(l:Int):Boolean={
  println("$l is greater than 20 ?")
  l>20
}

val numbers = List(1,20,11,49, 30)
val lt30 = numbers.filter(lessThan30)


