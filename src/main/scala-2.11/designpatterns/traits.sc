

//https://learning.oreilly.com/library/view/scala-design-patterns/9781785882500/ch02s02.html
trait Alarm {
  def trigger(): String
}

trait Notifier {
  val notificationMessage: String

  def printNotification(): Unit = {
    System.out.println(notificationMessage)
  }

  def clear()
}

class NotifierImpl(val notificationMessage: String) extends Notifier {
  override def clear(): Unit = System.out.println("is cleared")
}

abstract class Connector {
  def connect()

  def close()
}

trait ConnectorWithHelper extends Connector {
  def findDriver(): Unit = {
    System.out.println("Find driver called.")
  }
}

class PgSqlConnector extends ConnectorWithHelper {
  override def connect(): Unit = {
    System.out.println("Connected...")
  }

  override def close(): Unit = {
    System.out.println("Closed...")
  }
}

// composing simple traits
// If we want the case class to override these methods then we make case class extend with Alarm with Notifier. If we want specific instances to have diff behaviour then
// we extend the traits in the new instances of the class like below watch
case class Watch(brand: String, initialTime: Long) extends Connector {
  def getTime(): Long = System.currentTimeMillis() - initialTime

  override def connect(): Unit = {
    System.out.println("Connected with another connector.")
  }
  override def close(): Unit = {
    System.out.println("Closed with another connector.")
  }
}

val expensiveWatch = new Watch("expensive brand", 1000L) with Alarm with Notifier {
  override def trigger(): String = "The alarm was triggered."

  override def clear(): Unit = {
    System.out.println("Alarm cleared.")
  }

  override val notificationMessage: String = "Alarm is running!"
}
val cheapWatch = new Watch("cheap brand", 1000L) with Alarm {
  override def trigger(): String = "The alarm was triggered."
}
// show some watch usage.
System.out.println(expensiveWatch.trigger())
expensiveWatch.printNotification()
System.out.println(s"The time is ${expensiveWatch.getTime()}.")
expensiveWatch.clear()

System.out.println(cheapWatch.trigger())

val reallyExpensiveWatch =  new Watch("Can Connect to DB", 1000L) with ConnectorWithHelper{

}

System.out.println("Using the really expensive watch.")
reallyExpensiveWatch.findDriver()
reallyExpensiveWatch.connect()
reallyExpensiveWatch.close()

/****** Mixin compositions ***************/
// New Pattern -  If we want the trait to inherit a method from a particualr super trait then we ned to specify in the
// sub trait otherwise whatever is the last trait to be mixed in while declaring the sub trait gets called


trait A{
  def sayHello():String = "Hello there"
}

trait B{
  def sayHello(): String= "Hi there"
}

trait C extends B with A{
  override def sayHello(): String = super[B].sayHello()
}

case class test() extends C {
  def greetings():String = sayHello()
}

test().greetings()




