import scala.collection.immutable.Map

sealed trait Json

final case class JsObject(get: Map[String, Json]) extends Json
final case class JsString(get: String) extends Json
final case class JsNumber(get: Double) extends Json
final case object JsNull extends Json

trait JsonWriter[A]{
  def write(value:A): Json
}

final case class Person(name: String, email: String)

object JsonWriterInstances{
  implicit val stringWriter: JsonWriter[String] = new JsonWriter[String] {
    override def write(value: String) = JsString(value)
  }

  implicit val intWriter: JsonWriter[Double] = new JsonWriter[Double] {
    override def write(value: Double) = JsNumber(value)
  }

  implicit val personWriter : JsonWriter[Person] = new JsonWriter[Person] {
    override def write(value: Person) = JsObject(Map("name" -> JsString(value.name), "email" -> JsString(value.email)))
  }

  implicit def optionalWriter[A](implicit jsonWriter: JsonWriter[A]) : JsonWriter[Option[A]]={
    new JsonWriter[Option[A]] {
      override def write(value: Option[A]) = value match{
        case Some(innerVal) => jsonWriter.write(innerVal)
        case None => JsNull
      }
    }
  }

}

object Json{
  def toJson[A](value:A)(implicit w :JsonWriter[A] ): Json = w.write(value)
}

import JsonWriterInstances._
Json.toJson(Person("Dave","abc@gmail.com"))
Json.toJson(23.09)
Json.toJson(Option("abc"))
Json.toJson(Option(Option(123.00)))

trait Printable[A]{
  def format(value:A):String
}

final case class Cat(name: String, age: Int, color: String)

object PrintableInstances{
  implicit val stringPrintable : Printable[String]= new Printable[String] {
    override def format(value: String) = value
  }

  implicit val intPrintable: Printable[Int] = new Printable[Int] {
    override def format(value: Int) = value.toString
  }

  implicit val catPrintable:Printable[Cat] = new Printable[Cat] {
    override def format(value: Cat) = value.name+" is a "+value.color +" coloured cat"
  }

  implicit def listPrintable[A](implicit printer: Printable[A]) : Printable[List[A]]={
    new Printable[List[A]] {
      override def format(value: List[A]) : String =  value.map(v => printer.format(v)).mkString(", ")
    }
  }
}

object Print{
  def toPrint[A](value: A)(implicit p : Printable[A]): String = p.format(value)
}

import PrintableInstances._

Print.toPrint(List(Cat("mary",2,"brown"),Cat("ellen",3,"grey")))

object PrintableSyntax{

}