val superAdder : Int  => Int =>Int = x =>y=>x+y
val add3 = superAdder(3)
println(add3(4))
println(superAdder(3)(5))

val simpleAddFunction = (x:Int, y:Int) => x+y
def simpleAddMethid(x:Int, y:Int) :Int=  x+y
def curriedAddMethod(x:Int)(y:Int) :Int =  x+y

val add7 = (x:Int) => simpleAddFunction(x,7)
val add7_1 = simpleAddFunction(7, _:Int)

add7_1(4)

def concatenator(a:String, b:String,c:String) = a+b+c

val insertName= concatenator("Hello, I'm",_:String,", how are you")

insertName("Shivangi")

def curreiedFormatter(f: String)(number:Double) = f.format(number)

val numbers = List(Math.PI, Math.E, 1, 9.8, 1.3e-12)

val simpleFormat = curreiedFormatter("%4.2f")_

val complexFormat = curreiedFormatter("%8.6f")_

numbers.map(simpleFormat)
numbers.map(complexFormat)

def byName(n: => Int) = n+1
def byFunction(f:()=>Int) = f()+1

def method: Int=42
def parentMethod():Int=42

byName(23)
byName((()=> 42)())