import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * Created by shivangi on 13/09/16.
  */
@RunWith(classOf[JUnitRunner])
class DynamicProgrammingSuite extends FunSuite {

  //println(DynamicProgramming.subSetSum(List(5,9,0,3,4),7))
  //println(DynamicProgramming.closestPartition(List(3,1,1,2,2,1)))
  /*val l = DynamicProgramming.validBrackets(3)
  for (e<-l)
    println(e)*/

  println(DynamicProgramming.longestCommonSubsequence("ABCDEF".toList,"GHIJKL".toList))

}
