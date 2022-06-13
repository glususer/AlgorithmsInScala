package advancedscala.typesystem

object RockingInheritance extends App{

  trait writer[T]{
    def write(value:T):Unit
  }

  trait Closeable{
    def close(status:Int):Unit
  }

  trait GenericStream[T]{
    def foreach(f:T=>Unit):Unit
  }

  def processStream[T](stream: GenericStream[T] with writer[T] with Closeable):Unit={

    stream.foreach(println)
    stream.close(0)

  }

  // diamond problem

  trait Animal {
    def name():String
  }
  trait Lion extends Animal{
    override def name(): String = "Lion"
  }
  trait Tiger extends Animal{
    override def name(): String = "Tiger"
  }
  // last override gets picked
  class Mutant extends Lion with Tiger{
   // override def name(): String = "Alien"
  }

  val mutant = new Mutant
  println(mutant.name())

  trait Cold{
    def print:Unit= println("cold")
  }

  trait Green extends Cold{
    override def print: Unit = {
      println("green")
      super.print
    }
  }

  trait Blue extends Cold{
    override def print: Unit = {
      println("blue")
      super.print
    }
  }

  class Red {
    def print:Unit= println("red")
  }

  class White extends Red with Green with Blue{
    override def print: Unit = {
      println("white")
      super.print
    }
  }

  val color = new White

  println(color.print)

}
