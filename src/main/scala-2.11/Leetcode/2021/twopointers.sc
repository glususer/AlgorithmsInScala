import scala.collection.Searching._
import scala.collection.mutable

//https://leetcode.com/problems/3sum/
/* intution : Start from i and j as 0 and 1, keep 3rd pointer at last ele and iterate over
 array. For each i, incr/decr j and k depending upon sum and do this for all i */

def threeSumHelper(nums: Array[Int], i:Int, acc: Set[(Int,Int,Int)]):Set[(Int,Int,Int)]={
  val length = nums.length
  if(i==length-1) acc
  else{
   val (updatedSet,_,_) =  nums.foldLeft(acc, i,i+1) { case ((acc, i, j), _) =>
     println(s"i $i, j $j")
     if (j < length-1) {
       val numToBeSearched = -(nums(i) + nums(j))
       val index = nums.search(numToBeSearched, j+1, length)
       val sum = nums(i)+nums(j)+nums(index.insertionPoint)
       if (sum == 0) (Set((i, j, index.insertionPoint)) ++ acc, i, j + 1)
       else (acc, i, j + 1)
     }else (acc, i, j)
   }
    threeSumHelper(nums, i+1,updatedSet)
  }
}
def threeSum1(nums: Array[Int]): List[List[Int]] = {
  val length = nums.length
  val sortedNums = nums.sorted
  val threeSumSet: Set[(Int,Int,Int)] = length match{
  case len if len == 0 || len ==1 || len == 2 => Set.empty
  case  _ =>   threeSumHelper(sortedNums, 0, Set[(Int,Int,Int)]())
}
 threeSumSet.map(tuple => (sortedNums(tuple._1), sortedNums(tuple._2), sortedNums(tuple._3)))
    .map(tuple => List(tuple._1, tuple._2,tuple._3))
    .toList

}
//threeSum(Array(-1,0,1,2,-1,-4))


def threeSum(nums: Array[Int]): List[List[Int]] = {
  scala.util.Sorting.quickSort(nums)
  var i =0
  val set = mutable.Set[List[Int]]()
  while(i<nums.length-1) {
    var left = 1
    var right = nums.length-1
    while (left < right) {
      if (nums(i) + nums(left) + nums(right) > 0) right = right - 1
      else if (nums(i) + nums(left) + nums(right) < 0) left = left + 1
      else {
        val current = List(nums(i), nums(left), nums(right)).sorted
        if (!set.contains(current)) set + current
      }
    }
    i=i+1
  }
  set.toList
}
threeSum(Array(-1,0,1,2,-1,-4))
//https://leetcode.com/problems/interval-list-intersections/
case class Point(x: Int, y: Int)
def helper(first:List[Point], second: List[Point], acc:List[Point], cameFrom: String):List[Point]={
 // println(s"first $first, second $second, acc $acc")
  (first, second)  match{
    case (Nil,Nil) => acc
    case (Nil, _) => acc ++ second
    case (_, Nil) => acc ++ first
    case (z::zs, w::ws) =>
      val mergedInterval = Point(math.max(z.x, w.x), math.min(z.y, w.y))
     println(s"mergedInterval $mergedInterval first $first, second $second")
      if(mergedInterval.x < mergedInterval.y) {
        if (z.y < w.y) helper(zs, second, acc :+ mergedInterval, "first")
        else helper(first, ws, acc :+ mergedInterval, "second")
      }
      else if(mergedInterval.x == mergedInterval.y) helper(zs, ws, acc :+ mergedInterval, "second")
      else{
        cameFrom match{
          case "first" => helper(first, ws, acc,"second")
          case "second" => helper(zs, second, acc, "first")
        }
      }
  }
}
def intervalIntersection(firstList: Array[Array[Int]], secondList: Array[Array[Int]]): List[Point] = {
  val initialCameFrom = if(firstList(0)(1) < secondList(0)(1)) "first" else "second"
helper(firstList.map(p => Point(p(0), p(1))).toList, secondList.map(p => Point(p(0),p(1))).toList, List.empty, initialCameFrom)
}

//[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]
//intervalIntersection(Array(Array(0,2),Array(5,10),Array(13,23),Array(24,25)),
  Array(Array(1,5),Array(8,12),Array(15,24),Array(25,26))

//https://leetcode.com/problems/subarray-product-less-than-k/

def numSubarrayProductLessThanKHelper(nums:Array[Int], k:Int, left:Int, right:Int, count:Int, product: Int):Int={
  if(right < nums.length){
    println(s" left $left, right $right, product $product count $count, right ${nums(right)} left ${nums(left)} ")
  //  println(s" left $left, right $right, product $product count ${count+(right-left+1)}")

    if(left <= right && product >=k){
      numSubarrayProductLessThanKHelper(nums, k, left+1,right, count-1, product/nums(left))
    }
    else {
      if(left <= right && product*nums(right) >=k){
        numSubarrayProductLessThanKHelper(nums,k,left+1,right+1, count+(right-left+1),(product*nums(right))/nums(left))
      }
      else  numSubarrayProductLessThanKHelper(nums,k,left,right+1, count+(right-left+1), product*nums(right))
    }
  }
  else {
    println(s" left $left, right $right, product $product count $count ")
    count
  }
}

  def numSubarrayProductLessThanK(nums: Array[Int], k: Int): Int = {
    /*var left =0
    var right =0
    var count =0
    var product = 1
    while(right < nums.length){
      product*=nums(right)
      while(left <=right && product>=k){
        println(s" nums ${nums.slice(left,right).toList} i $left j $right")
        product = product/nums(left)
        left+=1
      }
      count += right-left+1
      right +=1
    }
    count*/

    numSubarrayProductLessThanKHelper(nums, k, 0,0,0,1)
  }

val array1 = Array(10,9,10,4,3,8,3,3,6,2,10,10,9,3)
val array2 = Array(10,5,2,6)
//numSubarrayProductLessThanK(array1,19)

//List(1,3,10,9,3).slice(2,4)

//https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/

def numSubarrayBoundedMax(nums: Array[Int], left: Int, right: Int): Int = {
 nums.scanLeft((-1,0,0,0)){case ((start, ans, cnt, end), _)=>
   if(nums(end) < left) (start,ans+cnt, cnt,end+1)
  else if(nums(end) > right) (end, ans, 0, end+1)
  else if(nums(end) >= left && nums(end)  <= right) (start,ans+end-start,end-start,end+1)
  else(start, ans, cnt,end+1)}.last._2
}

def test(nums: Array[Int], left: Int, right: Int): Int={
  var prev = -1
  var ans = 0
  var cnt = 0
  for(i <- nums.indices)
  {
    if(nums(i) < left){
      ans = ans+cnt
    }
    else if(nums(i) > right) {
      cnt = 0
      prev = i
    } else if(nums(i) >= left && nums(i)  <= right) {
    cnt =  i-prev
      ans = ans+cnt
    }
  }
   ans
}

numSubarrayBoundedMax(Array(2,1,4,3), 2,3)

def numRescueBoats(people: Array[Int], limit: Int): Int = {
  scala.util.Sorting.quickSort(people)
  val x =  people.scanLeft(0, people.length-1, 0) { case ((start, end, count), _) =>
    if (start <= end) {
      if (people(start) + people(end) <= limit) (start + 1, end - 1, count + 1)
      else if (people(end) <= limit) (start, end - 1, count + 1)
      else if (people(start) <= limit) (start + 1, end, count + 1)
      else (start, end, count)
    } else (start, end, count)
  }

 // x.foreach(println)
  x.last._3

}

//numRescueBoats(Array(1,2), 3)

def minSubArrayLenHelper(target: Int, prefixSum: Array[Int], left:Int, right:Int): Int={
  if(prefixSum(right)-prefixSum(left) >= target) minSubArrayLenHelper(target, prefixSum, left+1, right)
  else left
}

def minSubArrayLen(target: Int, nums: Array[Int]): Int = {
  if(nums.sum < target) 0
  else {
    val prefixSum = {
      nums.scanLeft(0) { case (acc, ele) => acc + ele }
    }.tail

    prefixSum.foldLeft(0, 0, Int.MaxValue, prefixSum(0)) { case ((left, right, minLength, _), ele) =>
      if (prefixSum(right) < target) {
        (left, right + 1, minLength, ele)
      }
      else {
        val newLeft = minSubArrayLenHelper(target, prefixSum, left, right)
        val currentLength = right - newLeft + 1
        (newLeft, right + 1, if (minLength < currentLength) minLength else currentLength, ele)
      }
    }._3
  }
}

def numSubarrayProductLessThanK1Helper(k:Int, nums:Array[Int], left:Int, right: Int,product:Int):(Int, Int)={
  if(product  >= k && left <= right) numSubarrayProductLessThanK1Helper(k, nums, left+1, right,product/nums(left))
  else {
    (if(left > right) right else left, product)
  }
}
def numSubarrayProductLessThanK1(nums: Array[Int], k: Int): Int = {
  if(k == 0) 0
  else {
    val x = nums.scanLeft(0, 0, 0, 1, nums(0)) { case ((left, right, count, product, _), ele) =>

      if (left <= right) {
        val currentProduct = product * nums(right)
        if (currentProduct < k) (left, right + 1, count + (right - left + 1), currentProduct, ele)
        else {
          val (newLeft, adjustedProduct) = numSubarrayProductLessThanK1Helper(k, nums, left, right,currentProduct)
            println(s" count is ${count+(right-newLeft+1)} and ele is $ele and newLeft is $newLeft right ${right+1}")
          (newLeft, right + 1, count + (right - newLeft + 1), adjustedProduct, ele)
        }
      } else (left,right, count, product,ele)
    }
     x.foreach(println)

    x.last._3
  }
}

// 10, (10,5), (5), (2), (2,5)
//[10,9,10,4,3,8,3,3,6,2,10,10,9,3]
//19
numSubarrayProductLessThanK1(Array(1,1,1), 1)