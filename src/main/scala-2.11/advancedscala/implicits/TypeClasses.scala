package advancedscala.implicits


object TypeClasses extends App{

  case class User(name:String, age:Int, email:String)

  trait Equals[T]{
    def compare(t1:T, t2:T):Boolean
  }

  object Equals{
    def apply[T](a:T, b:T)(implicit equalizer: Equals[T]): Boolean = equalizer.compare(a,b)
  }

  /*implicit object NameEquals extends Equals[User]{
    override def compare(t1: User, t2: User): Boolean = t1.name == t2.name
  }
*/
  implicit object NameAndEmailEquals extends Equals[User]{
    override def compare(t1: User, t2: User): Boolean  = t1.name == t2.name && t1.email == t2.email
  }

  implicit class TypeSafeEqaul[T] (value: T){
    def === (anotherValue: T)(implicit equalizer: Equals[T]): Boolean={
      equalizer.compare(anotherValue, value)
    }

    def !== (anotherValue: T)(implicit equalizer: Equals[T]): Boolean={
      !equalizer.compare(anotherValue, value)
    }
  }


  println(User("abc",23,"abc@gmail.com") === User("xyz",42,"xyz@gmail.com"))
  println(User("abc",23,"abc@gmail.com") !== User("xyz",42,"xyz@gmail.com"))

  // improve the Equal TC with an implicit conversion class
  // ===(anotherValue: T)
  //!== (anotherValue: T)


}


