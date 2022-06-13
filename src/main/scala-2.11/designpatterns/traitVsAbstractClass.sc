//When to use an abstract class in Scala (instead of a trait)
//You want to create a base class that requires constructor arguments.
//The code will be called from Java code.

// scala
//package foo

// the original trait
trait MathTrait {
  def sum(x: Int, y: Int) =  x + y
}

// the wrapper class
class MathTraitWrapper extends MathTrait

// java
/*package foo;

public class JavaMath extends MathTraitWrapper {

  public static void main(String[] args) {
    new JavaMath();
  }

  public JavaMath() {
    System.out.println(sum(2,2));
  }

}*/
