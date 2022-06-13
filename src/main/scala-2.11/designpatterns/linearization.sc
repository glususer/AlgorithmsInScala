class MultiplierIdentity {
  def identity: Int = 1
}

trait DoubledMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 2 * super.identity
}

trait TripledMultiplierIdentity extends MultiplierIdentity {
  override def identity: Int = 3 * super.identity
}

// first Doubled, then Tripled
class ModifiedIdentity1 extends DoubledMultiplierIdentity with TripledMultiplierIdentity

class ModifiedIdentity2 extends DoubledMultiplierIdentity with TripledMultiplierIdentity {
  override def identity: Int = super[DoubledMultiplierIdentity].identity
}

class ModifiedIdentity3 extends DoubledMultiplierIdentity with TripledMultiplierIdentity {
  override def identity: Int = super[TripledMultiplierIdentity].identity
}
// first Doubled, then Tripled

/*// first Tripled, then Doubled
class ModifiedIdentity4 extends TripledMultiplierIdentity with DoubledMultiplierIdentity

class ModifiedIdentity5 extends TripledMultiplierIdentity with DoubledMultiplierIdentity {
  override def identity: Int = super[DoubledMultiplierIdentity].identity
}

class ModifiedIdentity6 extends TripledMultiplierIdentity with DoubledMultiplierIdentity {
  override def identity: Int = super[TripledMultiplierIdentity].identity
}
// first Tripled, then Doubled*/

object ModifiedIdentityUser {

  def main(args: Array[String]): Unit = {

    /*val instance3 = new ModifiedIdentity3
    val instance4 = new ModifiedIdentity4
    val instance5 = new ModifiedIdentity5
    val instance6 = new ModifiedIdentity6
*/

    /*System.out.println(s"Result 3: ${instance3.identity}")
    System.out.println(s"Result 4: ${instance4.identity}")
    System.out.println(s"Result 5: ${instance5.identity}")
    System.out.println(s"Result 6: ${instance6.identity}")*/
  }
}

val instance1 = new ModifiedIdentity1
val instance2 = new ModifiedIdentity2

System.out.println(s"Result 1: ${instance1.identity}")
System.out.println(s"Result 2: ${instance2.identity}")

///////////// https://stackoverflow.com/questions/34242536/linearization-order-in-scala

class A {
  def sayHello : String = "hello from A"
  def giveMeNum: Int = 2
}

trait B extends A {
  override def sayHello: String = "B"+super.sayHello

  override def giveMeNum: Int = 2*super.giveMeNum
}

trait C extends A{
  override def sayHello: String = "C"+super.sayHello

  override def giveMeNum: Int = 3*super.giveMeNum
}

class Z extends B with C{
  def sayHi: String = super[C].sayHello
}

class XB extends B with C{
  override def giveMeNum: Int = super[B].giveMeNum

  override def sayHello: String = super[B].sayHello
}

val z  = new Z
//z.sayHello
//z.giveMeNum
z.sayHi

/*
val xb = new XB
xb.giveMeNum
xb.sayHello*/
