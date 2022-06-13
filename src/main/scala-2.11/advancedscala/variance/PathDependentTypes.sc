object PathDependentTypes extends App{
  class Outer{
    class Inner
    object InnerObject
    type InnerType

    def print(i:Inner) = println(i)
    def printGeneral(i:Outer#Inner) = println(i)
  }

  def aMethod:Int={
    class helperClass
    type HelperTypee = String
    2
  }

  val outer = new Outer
  val innner = new outer.Inner

  val oo = new Outer

  /*Exercise: DB keyedby Int or String, but maybe others*/
  /*trait ItemLike{
    type Key
  }*/
  trait Item[K] {
    type Key = K
  }
  trait IntItem extends Item[Int]
  trait StringItem extends Item[String]

  def get[ItemType](key: ItemType):ItemType
}