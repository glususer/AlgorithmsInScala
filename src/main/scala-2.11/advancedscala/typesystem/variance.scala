package advancedscala.typesystem

object variance extends App{

  trait Animal{
    def name : String = "Animal"
  }
  class Dog extends Animal{
    override def name: String = "Dog"
  }
  class Cat extends Animal {
    override def name: String = "Cat"
  }
  class Crocodile extends Animal {
    override def name: String = "Croc"
  }

  // waht is variance ?? It is problem of type substitution of Generics

  class Cage[T]
  class CCAge[+T] // yes
  val ccage: CCAge[Animal] = new CCAge[Cat]

  // no - variance
  class ICage[T]

 // val icage: ICage[Animal] = new ICage[Cat]

  // hell no - opposite - contravariance

  class CoCage[-T]

  val cocage: CoCage[Cat] = new CoCage[Animal]

  class InvatiantCage[T](animal: T){
    def getAnimalInCage:T = animal
  }
  class CoVariantCage[+T](animal: T) {
    def getAnimalInCage:T = animal
  }// Covariant position

  class ContraVariantCage[+T](animal:T){
    def getAnimalInCage[B >: T]: T = animal
  }
  val x = new CoVariantCage(new Cat)
  val y = new InvatiantCage(new Dog)
  val z = new ContraVariantCage(new Crocodile)

  println(x.getAnimalInCage.name)
  println(y.getAnimalInCage.name)
  println(z.getAnimalInCage.name)




}
