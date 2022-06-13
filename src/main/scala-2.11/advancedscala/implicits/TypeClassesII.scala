package advancedscala.implicits

import advancedscala.implicits.TypeClasses.User

object TypeClassesII extends App{
  /* type class itself ----- HTMLSerializer[T]{...}
  type class instances ----------UserSerializer, IntSerializer
  conversion with implicit classes ------------ HTMLEnricment
  */

  trait HTMLSerializer[T]{
    def serialize(value:T)(implicit serializer: HTMLSerializer[T]): String
  }

  object HTMLSerializer{
    def serialize[T](value:T)(implicit serializer: HTMLSerializer[T]): String = serializer.serialize(value)
  }

  implicit object UserSerializer extends HTMLSerializer[User]{
    override def serialize(user: User)(implicit serializer: HTMLSerializer[User]): String = s"<div> ${user.name} ${user.email} </div>"
  }

  implicit object IntSerializer extends HTMLSerializer[Int]{
    override def serialize(value: Int)(implicit serializer: HTMLSerializer[Int]): String = s"<div> $value </div>"
  }

  implicit def stringToUser (name:String):User={
    User(name,0,"name@gmail.com")
  }

  println(HTMLSerializer.serialize(42))
  println(HTMLSerializer.serialize(User("shivangi",36,"abc@gmail.com")))
  val shivangi :User = "Shivangi"
  println(HTMLSerializer.serialize(shivangi))

  implicit class HTMLEnricment[T](value:T){
    def toHTML(implicit serializer: HTMLSerializer[T]):String={
      serializer.serialize(value)
    }
  }

  // context bounds
  def htmlBoilerPlate[T](content: T)(implicit serializer: HTMLSerializer[T]): String={
    s"<html><body> ${content.toHTML(serializer)}</body></html>"
  }

  def htmlSugar[T:HTMLSerializer](content: T):String={
    s"<html><body> ${content.toHTML}</body></html>"
  }
  val john = User("john",42,"john@gmail.com")

  println(john.toHTML)
  println(2.toHTML)

}
