
//Subtype polymorphism

//Subtype polymorphism is expressed using inheritance with the extends keyword.
abstract class Item {
  def pack: String
}

class Fruit extends Item {
  override def pack: String = "I'm a fruit and I'm packed in a bag."
}

class Drink extends Item {
  override def pack: String = "I'm a drink and I'm packed in a bottle."
}

val shoppingBasket: List[Item] = List(
  new Fruit,
  new Drink
)
shoppingBasket.foreach(i => System.out.println(i.pack))

//Parametric polymorphism
//Ad-hoc polymorphism allows us to extend our code without modifying
// the base classes. This is very useful if we are using external libraries,
// or if we simply are not able to change the original code for some reason.
// It is really powerful and is evaluated in compile time, which makes sure
// that our program will work as expected.
//Parametric polymorphism is expressed using type parameters.

trait Adder[T] {
  def sum(a: T, b: T): T
}

object Adder {
  def sum[T: Adder](a: T, b: T): T =
    implicitly[Adder[T]].sum(a, b)

  implicit val intAdder: Adder[Int] = new Adder[Int] {
    override def sum(a: Int, b: Int) = a+b
  }

  implicit val stringAdder:Adder[String] = new Adder[String] {
    override def sum(a: String, b: String) = a.concat(b)
  }
}

Adder.sum(1,2)
Adder.sum("a","b")

