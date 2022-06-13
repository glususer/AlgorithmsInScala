

def byValueFunction(x: Long):Long={
  println(x)
  println(x)
  x+1
}

def byNameFunction(x: =>Long):Long={
  println("hey")
  x+1
}

byValueFunction(System.nanoTime())
byNameFunction(System.nanoTime())

def byNameLazyMethod(x: => Long):Long={
  /*lazy val t = x
  t+t+t*/
  x+x+x
}

def retrieveMagicalValue():Long={
  println("retrieving")
  42
}

byNameLazyMethod(retrieveMagicalValue())