trait Food[+A]

trait Ingredient[A] extends Food[A] {
  def isDelicious :Boolean
}

class Tomato extends Ingredient{
  def isDelicious = false
}

class Chilli extends Ingredient{
  def isDelicious = true
}

class Banana extends Ingredient{
  def isDelicious = true
}

def eat[A<:Ingredient](i :A):Unit={
  println(i.isDelicious)
}

val tomato  = new Tomato
val x = eat(tomato)

