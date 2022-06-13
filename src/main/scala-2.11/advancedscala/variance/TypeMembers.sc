object TypeMembers extends App{
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  class AnimalCollection{
    type AnimalType
    type BoundedAnimal <: Animal
    type SuperBounded >:Dog <:Animal
    type AnimalC = Cat
  }

  val ac = new AnimalCollection

  val pup : ac.SuperBounded = new Dog
  val cat : ac.AnimalC = new Cat

  type CatAlias = Cat
  val anotherCat: CatAlias = new Cat
// alternatives to generics
  trait MyList{
    type T
    def add(ele:T):MyList
  }

  class NonEmptyList(value:Int) extends MyList{
    override type T = Int
    override def add(ele: Int): MyList = ???
  }

  type CatsType = cat.type

  /*Exercise -  enforce a type to be applicable for some type memebers*/

  trait ApplicableToNumbers{
    type A <: Number
  }
  trait MList  {
    type A
    def head:A
    def tail:MList
  }

  /*class CustomList(hd:String, tl:CustomList) extends MList{
    type A = String

    override def head: String = hd

    override def tail: MList = tl
  }*/

  class IntList(hd:Integer, tl:IntList) extends MList with ApplicableToNumbers {
    type A = Integer

    override def head: Integer = hd

    override def tail: MList = tl
  }
}