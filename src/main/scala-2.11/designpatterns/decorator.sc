//Intent
//To add behavior to an individual object rather than to an entire class of objects—this
// allows us to change the behavior of an existing class.
//The decorator design pattern is about creating a decorator class that can wrap the original class and
// provides additional functionality, keeping the class methods signature intact.

def add(a:Int, b:Int):Int= a+b
def subtract(a:Int, b:Int):Int=a-b

def makeLogger(fn:(Int,Int)=>Int)={
  (a:Int, b:Int)=>val result = fn(a,b)
    println(s"result is $result")
    result
}

val loggingAdd = makeLogger(add)
val loggingSubtract = makeLogger(subtract)
val multiplyLogger = makeLogger((a:Int, b:Int)=>a*b)

loggingAdd(2,3)
loggingSubtract(90,3)
multiplyLogger(4,5)
