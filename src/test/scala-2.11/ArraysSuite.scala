import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * Created by shivangi on 20/09/16.
  */
@RunWith(classOf[JUnitRunner])
class ArraysSuite extends FunSuite {
//  println(Arrays.stockPrices(Array(10,7,5,8,11,9)))
//  println(Arrays.kthLargest(Array(5,2,1,3,9,4,8),1))
//  println(Array(5,2,1,3,9,4,8).sorted.mkString(" "))

  test ("kth largest element of array is"){
 //   assert(Arrays.kthLargest(Array(5,2,1,3,9,4,8),1)==Array(1,2,3,4,5,8,9)(6))
  //  assert(Arrays.kthLargest(Array(5,2,1,3,9,4,8),2)==Array(1,2,3,4,5,8,9)(5))
  //  assert(Arrays.kthLargest(Array(5,2,1,3,9,4,8),3)==Array(1,2,3,4,5,8,9)(4))
  //  assert(Arrays.kthLargest(Array(5,2,1,3,9,4,8),4)==Array(1,2,3,4,5,8,9)(3))
  //  assert(Arrays.kthLargest(Array(5,2,1,3,9,4,8),5)==Array(1,2,3,4,5,8,9)(2))
  //  assert(Arrays.kthLargest(Array(5,2,1,3,9,4,8),6)==Array(1,2,3,4,5,8,9)(1))
  }

 // println(Arrays.minSubArray(Array(2,3,1,2,4,3),7).mkString(" "))
//  println(Arrays.minInSortedArray(Array(2,3,4,5,6,8,1)))
  val arr = Array(Array(1,2),Array(4,3))
  println(Arrays.printSpiralMatrix(arr))
}
