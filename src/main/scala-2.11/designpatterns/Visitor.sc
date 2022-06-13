//Intent
//To encapsulate an action to be performed on a data structure in a way that allows the addition
// of new operations to the data structure without having to modify it.

trait Person{
  def fullName: String
  def firstName: String
  def lastName: String
  def houseNumber: String
  def street: String
}

class SimplePerson(val firstName: String, val lastName: String, val houseNumber: String, val street: String) extends Person{
  override def fullName = firstName+" "+lastName
}

implicit class ExtendedPerson(person: Person){
  def fullAddress():String = person.houseNumber+"-"+person.street
}
case class Name(firtsName: String, lastName: String)
case class Address(houseNumber: String, streetName: String)

class ComplexPerson (val name: Name, val add: Address) extends Person{
  override def fullName = name.firtsName+"-"+name.lastName

  override def firstName = name.firtsName

  override def lastName =name.lastName

  override def houseNumber = add.houseNumber

  override def street = add.streetName
}

val simplePerson = new SimplePerson("shivangi", "bansal","411","Niharika")

simplePerson.fullAddress()

val complexPerson = new ComplexPerson(Name("shivangi","bansal"), Address("411","Niharika"))

complexPerson.fullAddress()

