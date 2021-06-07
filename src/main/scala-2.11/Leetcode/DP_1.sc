import scala.collection.mutable

//https://leetcode.com/problems/longest-continuous-increasing-subsequence/
def findLengthOfLCIS(nums: Array[Int]): Int = {
  val x = nums.scanLeft(0,0,0){case(acc,ele) => if (acc._2 < ele) {
    if(acc._1+1 > acc._3) (acc._1+1,ele,acc._1+1)
    else(acc._1+1,ele,acc._3)
  }
  else{
    if(acc._1 > acc._3)(1,ele,acc._1) else(1,ele,acc._3)
  }}

 // println(x.toList)

x.last._3
}

findLengthOfLCIS(Array(2,2,2,2))
//https://leetcode.com/problems/subarray-sum-equals-k/
//https://www.youtube.com/watch?v=20v8zSo2v18
def subarraySum(nums: Array[Int], k: Int): Int = {

  val prefixArr = nums.scanLeft(0)(_+_).tail

  val subArraySum = prefixArr.scanLeft(mutable.HashMap(0->1),0){
    case (acc,ele) => val diff = acc._1.getOrElse(ele-k,0)
      acc._1.update(ele,acc._1.getOrElse(ele,0)+1)
      (acc._1,acc._2+diff)
  }

  //subArraySum.foreach(println)
  println(s"sum ${subArraySum.toList.last}")
  subArraySum.last._2
}

subarraySum(Array(1,2,3),3)

subarraySum(Array(3,9,-2,4,1,-7,2,6,-5,8,-3,-7,6,2,1),5)

//https://leetcode.com/problems/arithmetic-slices/
def numberOfArithmeticSlices(A: Array[Int]): Int = {
 A.zipWithIndex.foldLeft(0,0){case (acc, ele) => if(ele._2 == 0 || ele._2==1) acc
  else{
    val currentIndex = ele._2
    if(A(currentIndex) - A(currentIndex-1) == A(currentIndex-1)-A(currentIndex-2)){
     val updatedCur = acc._1+1
      (updatedCur, acc._2+updatedCur)
    }else (0, acc._2)
  }}._2

}

numberOfArithmeticSlices(Array(1,2,3,4,5,6,7))






