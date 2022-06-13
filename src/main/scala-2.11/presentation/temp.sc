def add(x:Int,y:Int):Int={
  x+y
}

add(5+3, 4+7)

add(8,11)

def retrieveFn(x:Int):()=>Int={
      println(s"here we go")
  ()=>x
}

def retrieveFn1(x:Int):Int={
  println(s"here we go")
  x
}

def addCallByValue(x:Int, y:Int):Int={
  x+y
}

def addCallByName(x:() => Int, y:() =>Int):Int={
  x()+y()
}

addCallByName(retrieveFn(5+3), retrieveFn(9+4))
addCallByValue(retrieveFn1(5+3),retrieveFn1(9+4))

def value: Int = 5
def method(): Int = 5

def f1(f: () => Int) = ???
def f2(f: => Int) = ???

f1(value)  // fails
f1(method) // works
f2(value)  // works
f2(method) // works with a warning "empty-paren method accessed as parameterless"
