package advancedscala.implicits

object ImplicitsIntro extends App{
  println(List(3,1,2,5,6).sortWith{case (a,b) => a<b})
  println(List((3,1),(2,2),(2,4),(5,9),(6,10)).sortBy{x => (x._1,x._2)})

  case class Person(name:String, age:Int)

  implicit val OrderingOnAge :Ordering[Person] = Ordering.fromLessThan{case(p,q) => (p.name.compareTo(q.name)< 0) }

  println(List(Person("zabc",20),Person("gnh",50),Person("saad",80)).sorted)

  case class Purchase(nUnits:Int, unitPrice:Double)

  object Purchase{
    implicit val OrderingOnTotalPrice:Ordering[Purchase] = Ordering.fromLessThan((a,b) => (a.nUnits*a.unitPrice) < (b.nUnits*b.unitPrice))
  }

  object OnNumberOfUnits{
    implicit val OrderingOnNumberOfUnits: Ordering[Purchase] = Ordering.fromLessThan((a,b) => a.unitPrice < b.unitPrice)
  }

  object OnUnitPrice{
    implicit val OrderingOnUnitPrice: Ordering[Purchase] = Ordering.fromLessThan((a,b) => a.nUnits < b.nUnits)
  }
}
