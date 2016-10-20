import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * Created by shivangi on 27/09/16.
  */
@RunWith(classOf[JUnitRunner])
class StringsSuite extends FunSuite {

  test("scramble a string"){
    assert(Strings.isScrambled("tiger","retig")==false)
    assert(Strings.isScrambled("tiger","riterg")==false)
    assert(Strings.isScrambled("synonym","oysymno")==false)
    assert(Strings.isScrambled("tiger","itreg")==true)
  }
  test("check if 2 strings are isomorphic"){
    assert(Strings.isomorphic("food","good")==true)
    assert(Strings.isomorphic("egg","add")==true)
    assert(Strings.isomorphic("human","people")==false)
    assert(Strings.isomorphic("aba","")==false)
  }

  test("indices of palindromic pair words"){
    assert(Strings.palindromePairs(Array("bat", "tab", "cat")) === List((0, 1), (1, 0)))
    assert(Strings.palindromePairs(Array("abcd", "dcba", "lls", "s", "sssll")) === List((0,1), (1,0), (2,4), (3,2)))
    assert(Strings.palindromePairs(Array(""," s ")) === List((0,1), (1,0)))
  }

  println(Strings.interLeavings("AB","C").mkString(" "))

}
