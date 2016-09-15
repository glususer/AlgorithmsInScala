import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
 class ListsSuite extends FunSuite {

 test ("last element of list is"){
   assert(Lists.last(List(1,2,0)) === 0)
   assert(Lists.last(List(1)) === 1)
 }

 test ("penultimate element of list is"){
   assert(Lists.penultimate(List(1,2,0)) === 2)
   assert(Lists.penultimate(List(1,2,0,9)) === 0)
   assert(Lists.penultimate(List("apple","banana","guava")) === "banana")
 }

 test("nth element"){
   assert(Lists.nth(List(1,2,0),2) === 0)
   assert(Lists.nth(List(1,2,0,9),0) === 1)
   assert(Lists.nth(List("apple","banana","guava"),1) === "banana")
   assert(Lists.nth(List(1,2,0),4) === 0)

 }
 test("length of list"){
   assert(Lists.length(List(1,2,0)) === 3)
   assert(Lists.length(List(1,2,0,9)) === 4)
   assert(Lists.length(List("apple","banana","guava")) === 3)
   assert(Lists.length(List()) === 0)

 }
 test("reverse a list"){
   assert(Lists.reverse(List(1,2,0)) === List(0,2,1))
   assert(Lists.reverse(List(1,2,0,9)) === List(9,0,2,1))
   assert(Lists.reverse(List("apple","banana","guava")) === List("guava","banana","apple"))
   assert(Lists.reverse(List()) === List())

 }

 test("list is palindrome or not"){
   assert(Lists.isPalindrome(List(1,2,1)) === true)
   assert(Lists.isPalindrome(List(1,2,2,2)) === false)
   assert(Lists.isPalindrome(List("apple","banana","apple")) === true)
   assert(Lists.isPalindrome(List()) === true)

 }

 test("flatten list"){
   assert(Lists.flatten(List(List(1, 1), 2, List(3, List(5, 8)))) === List(1, 1, 2, 3, 5, 8))
  // assert(Lists.flatten(List(List(1,2),List(3,List(4,List(5,6,7))))) === List(1,2,3,4,5,6,7))
  // assert(Lists.flatten(List("apple",List("banana"))) === List("apple","banana"))
   //assert(Lists.flatten(List()) === List())

 }

 test("compress list"){
   assert(Lists.compress(List(1,1,1,2,2,3,3,3,4,4,5,2,2,3,4)) === List(1,2,3,4,5,2,3,4))
   assert(Lists.compress(List(1,2,3,4))=== List(1,2,3,4))
   assert(Lists.compress(List()) === List())
 }

 test("pack list") {
   assert(Lists.pack(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) === List(List('a', 'a', 'a', 'a'), List('b'), List('c', 'c'), List('a', 'a'), List('d'), List('e', 'e', 'e', 'e')))
   assert(Lists.pack(List(1,2,3,3,3,3,3,4)) === List(List(1), List(2), List(3,3,3,3,3), List(4)))
   assert(Lists.pack(List()) === List())
 }

 test("encode"){
   assert(Lists.encode(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) === List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e')))
   assert(Lists.encode(List()) === List())
 }
 List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

 test("encode modified"){
   assert(Lists.encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) === List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))
   assert(Lists.encodeModified(List()) === List())
   assert(Lists.encodeModified(List('a, 'a, 'a, 'a, 'b, 'b,'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) === List((4,'a), (2,'b), (2,'c), (2,'a), 'd, (4,'e)))
 }

 test("decode"){
   assert(Lists.decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))) === List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
 }

 test("encode direct"){
   assert(Lists.encodeDirect(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) === List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e')))
   assert(Lists.encodeDirect(List()) === List())
 }

 test("duplicate"){
   assert(Lists.duplicate(List('a, 'b, 'c, 'c, 'd))===List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
   assert(Lists.duplicate(List())===List())
 }

 test("duplicate N"){
   assert(Lists.duplicateN(2,List('a, 'b, 'c, 'c, 'd))===List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
   assert(Lists.duplicateN(3,List('a, 'b, 'c, 'c, 'd))===List('a, 'a, 'a,'b,'b, 'b, 'c,'c,'c, 'c, 'c, 'c, 'd,'d, 'd))
   assert(Lists.duplicateN(1,List('a, 'b, 'c, 'c, 'd))===List('a, 'b, 'c, 'c, 'd))
   assert(Lists.duplicateN(190,List())===List())

 }

 test("drop kth element N"){
   assert(Lists.drop(2,List('a, 'b, 'c, 'c, 'd))===List('a,'c,'d))
   assert(Lists.drop(10,List())===List())
   assert(Lists.drop(3,List(1,2,4,5,6))===List(1,2,5,6))
   assert(Lists.drop(2,List(2,4,6,8,10))===List(2,6,10))
   assert(Lists.drop(1,List(1,3,5,7,9))===List())

 }

 test("split list into 2"){
   assert(Lists.split(2,List('a, 'b, 'c, 'c, 'd))===(List('a,'b),List('c,'c,'d)))
   assert(Lists.split(1,List(1,2,3,4,5,6,7,8))===(List(1),List(2,3,4,5,6,7,8)))
   assert(Lists.split(0,List(1,2,4,5,6))===(List(),List(1,2,4,5,6)))
   assert(Lists.split(20,List(2,4,6,8,10))===(List(2,4,6,8,10),List()))
   assert(Lists.split(5,List(4,5,6,7,8,9)) === (List(4,5,6,7,8),List(9)))

 }

 test("slice list into 2") {
   assert(Lists.slice(2, 4, List(5, 6, 7, 8, 9)) === List(7,8,9))
   assert(Lists.slice(0, 4, List(5, 6, 7, 8, 9))===List(5,6,7,8,9))
   assert(Lists.slice(2, 5, List(5, 6, 7, 8, 9))===List(7,8,9))
   assert(Lists.slice(6, 10, List(5, 6, 7, 8, 9))===List())
 }

 test ("rotate by n") {
   assert(Lists.rotate(0, List(4, 5, 6, 7, 8, 9))===List(4,5,6,7,8,9))
   assert(Lists.rotate(1, List(4, 5, 6, 7, 8, 9))===List(5,6,7,8,9,4))
   assert(Lists.rotate(2, List(4, 5, 6, 7, 8, 9))===List(6,7,8,9,4,5))
   assert(Lists.rotate(3, List(4, 5, 6, 7, 8, 9))===List(7,8,9,4,5,6))
   assert(Lists.rotate(4, List(4, 5, 6, 7, 8, 9))===List(8,9,4,5,6,7))
   assert(Lists.rotate(5, List(4, 5, 6, 7, 8, 9))===List(9,4,5,6,7,8))
   assert(Lists.rotate(10, List(4, 5, 6, 7, 8, 9))===List(4,5,6,7,8,9))

 }

 test("remove element at") {
   assert(Lists.removeAt(2, List(1, 2, 3)) === (List(1, 2),Some(3)))
   assert(Lists.removeAt(1, List(1, 2, 3))=== (List(1, 3),Some(2)))
   assert(Lists.removeAt(0, List(1, 2, 3))===(List(2, 3),Some(1)))
   assert(Lists.removeAt(10, List(1, 2, 3))===(List(1, 2, 3),None))
   assert(Lists.removeAt(10, List())===(List(),None))
 }

 test ("insert element at position k") {
   assert(Lists.insertAt(2, 0, List(9, 8, 7, 6, 5))===List(2, 9, 8, 7, 6, 5))
   assert(Lists.insertAt(2, 1, List(9, 8, 7, 6, 5))===List(9, 2, 8, 7, 6, 5))
   assert(Lists.insertAt(2, 2, List(9, 8, 7, 6, 5))===List(9, 8, 2, 7, 6, 5))
   assert(Lists.insertAt(2, 3, List(9, 8, 7, 6, 5))===List(9, 8, 7, 2, 6, 5))
   assert(Lists.insertAt(2, 4, List(9, 8, 7, 6, 5))===List(9, 8, 7, 6, 2, 5))
   assert(Lists.insertAt(2, 5, List(9, 8, 7, 6, 5))===List(9, 8, 7, 6, 5))
   assert(Lists.insertAt(2, 5, List())===List())
 }

 test ("create a list containing all integers within a given range") {
   assert(Lists.range(2, 5)===List(2, 3,4,5))
   assert(Lists.range(2, 1)===List())
   assert(Lists.range(-2, 5)===List(-2, -1,0,1,2,3,4,5))
 }

  test ("create a list containing all integers within a given range") {
    assert(Lists.range(2, 5)===List(2, 3,4,5))
    assert(Lists.range(2, 1)===List())
    assert(Lists.range(-2, 5)===List(-2, -1,0,1,2,3,4,5))
  }


 println(Lists.firstLast(List(1,2,3,4,5,6)))
 println(Lists.reverseK(List(1,2),2))
 println(Lists.removeDup(List(4,4,4,4,4,5,5,6,6,6,6)))
 println(Lists.mergeSortedLists(List(4),List(2,5)))
 println(Lists.mergeSort(List(9,3,0,10,8)))

 println(Lists.rotateK(List(10,20,30,40,50,60,70),4))


 // println(randomSelect(2,List(1,2,3,4,5)))
// println(lotto(4,90))
// println(combinations(2,List(1,2,3,4,5)))
 /*test ("last element of empty list is"){
   assert(last(List()) == null)
 }*/

//  println(lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))))
// println(lsortFreq(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))))
 test("one plus one is two")(assert(1 + 1 == 2))

//  println(gcd(15,17))
//  println(totient(10))
// println(primeFactors(0))
// println(primeFactorMultiplicity(4))
 //println(listPrimesinRange(31,1000))
//  println(goldbach(28))
// println(printGoldbachList(9, 20))
 /*println(and(true,true))
 println(and(true,false))
 println(and(false,false))
 println(or(true,true))
 println(or(true,false))
 println(or(false,false))
 println(nand(true,true))
 println(nand(true,false))
 println(nand(false,false))
 println(nor(true,true))
 println(nor(true,false))
 println(nor(false,false))*/
// println(gray(3))
}
