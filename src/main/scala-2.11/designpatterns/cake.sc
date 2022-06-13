

// Scala class
//The rough idea is that we’ll encapsulate the dependencies we want to inject inside of top-level traits, which represent our injectable components.
// Instead of instantiating dependencies directly inside of the trait, we create abstract vals that will hold references to them when we wire everything up.


trait TestEnvironmentComponent {
  val env: TestEnvironment
  trait TestEnvironment {
    val envName: String
    def readEnvironmentProperties: Map[String, String]
  }
}

trait Configuration {
  def value: String
}

class DefaultConfiguration extends Configuration {
  val value = "production"
}
class TestingConfiguration extends Configuration {
  val value = "test"
}

trait ConfigurationComponent {
  val configuration: Configuration
}

trait AComponent {
  this: ConfigurationComponent =>
  val a: A
  class A {
    val value = "a-" + configuration.value
  }
}

trait BComponent {
  this: ConfigurationComponent with AComponent =>
  val b: B
  class B {
    val value = a.value + "-b-"+ configuration.value
  }
}

trait Components
  extends ConfigurationComponent
    with AComponent
    with BComponent

object Registry extends Components {
  val configuration = new DefaultConfiguration
  val a = new A()
  val b = new B()
}

object RegistryTesting extends Components {
  val configuration = new TestingConfiguration
  val a = new A()
  val b = new B()
}

RegistryTesting.a







