import scala.util.Random

lazy val fs: Stream[Int] = 0 #:: fs.scanLeft(1)(_ + _)

//val r = fs.takeWhile(_ <= 4000000).filter(_ % 2 == 0).sum

val x = List(1,1,2,3,5)
x.scanLeft(1)(_+_)

def factors(n: Long) = (2 to math.sqrt(n).toInt)
  .filter(n % _ == 0)//.filter(num => factors(num).isEmpty).toList

val r = factors(20)
//Find the largest palindrome made from the product of two 3-digit numbers.*

val lst = (100 to 999)

/*val m = ( for{
  x<-lst
  y<-lst
}yield (x*y)).toList.filter(num => num.toString == num.toString.reverse).max*/
//What is the smallest number divisible by each of the numbers 1 to 20?*

//val v = Range(20,Int.MaxValue).find(n => Range(2,21).forall(n%_==0))

//Find the 10001st prime.*

val arr = Array(1,2)

val z =   arr.groupBy(identity).map{case (_,value) => value.length}.toList

z.distinct.length == z.length

List(2,5,6,10,12,14,15).span(_ != 10)

val dict = List("catt","cat","bat","rat")
val sentence = "the cattle was rattled by the battery"

val replacedSentence = sentence.split(" ").map(word => {
  val prefixList = dict.filter(dic => word.contains(dic) && word.startsWith(dic))
  if (prefixList.nonEmpty) prefixList.maxBy(_.length)
  else word
}).toList.mkString(" ")

println(replacedSentence)

def add(a: Int, b: Int) = a + b
def subtract(a: Int, b: Int) = a - b
def multiply(a: Int, b: Int) = a * b

def execute(fn:(Int, Int) => Int, x: Int, y: Int):Int = fn(x, y)

println("Add:      " + execute(add, 3, 4))
println("Subtract: " + execute(subtract, 3, 4))
println("Multiply: " + execute(multiply, 3, 4))

implicit def intToStr(x:Int): String = x.toString+"y"

42.toUpperCase()

def functionTakingString(str: String) = str
functionTakingString(42)

case class StringOps(str: String) {
  def yell = str.toUpperCase() + "!"
  def isQuestion = str.endsWith("?")
}

implicit def stringToStringOps(str: String): StringOps = StringOps(str)


val name = "john".yell

//https://leetcode.com/problems/filter-restaurants-by-vegan-friendly-price-and-distance/
def filterRestaurants(restaurants: Array[Array[Int]], veganFriendly: Int, maxPrice: Int, maxDistance: Int): List[Int] = {
  case class Restaurant(id:Int, rating:Int, isVeganFriendly:Int, price:Int, distance:Int)
  val x= restaurants.map(arr => Restaurant(arr(0),arr(1),arr(2),arr(3),arr(4)))
    .filter{restaturant => {if(veganFriendly==1) restaturant.isVeganFriendly == 1  else  true } &&
    restaturant.distance <= maxDistance &&
      restaturant.price <=maxPrice}.toList

  println(x)
   x.sortWith((a,b) => a.rating > b.rating || a.rating == b.rating && a.id > b.id)//.sortWith(_.id > _.id)
    .map(_.id)
}
//[[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]]
//1
//50
//10
//[3,1,5]

filterRestaurants(Array(Array(1,4,1,40,10),Array(2,8,0,50,5),Array(3,8,1,30,4),Array(4,10,0,10,3),Array(5,1,1,15,1)),0,50,10)

//https://leetcode.com/problems/kth-largest-element-in-an-array/

def helper(nums:Array[Int], k:Int):Int ={
    val pvt =  Random.nextInt(nums.length)
    val (leftArr, rightArr) = nums.partition(_ < nums(pvt))
    println(s" pvt is $pvt ${nums(pvt)}, leftArr  is ${leftArr.toList} and rightArr is ${rightArr.toList}")
    if (leftArr.length == k) nums(pvt)
    else if (leftArr.length < k) helper(rightArr, k-leftArr.length)
    else helper(leftArr, k)

}
def findKthLargest(nums: Array[Int], k: Int): Int = {
  helper(nums,k)
}

/*findKthLargest(Array(3,2,3,1,2,4,5,5,6),1)
findKthLargest(Array(3,2,3,1,2,4,5,5,6),2)
findKthLargest(Array(3,2,3,1,2,4,5,5,6),3)
findKthLargest(Array(3,2,3,1,2,4,5,5,6),4)*/
//findKthLargest(Array(3,2,3,1,2,4,5,5,6),7)
//findKthLargest(Array(3,2,3,1,2,4,5,5,6),8)

def quickSelect(seq: Seq[Int], n: Int): Int = {
  val pivot = Random.nextInt(seq.length);
  val (left, right) = seq.partition(_ < seq(pivot))
  if (left.length == n) {
    seq(pivot)
  } else if (left.length < n) {
    quickSelect(right, n - left.length)
  } else {
    quickSelect(left, n)
  }
}

quickSelect(Array(3,2,3,1,2,4,5,5,6),5)

//https://leetcode.com/problems/minimum-absolute-sum-difference/
def binarySearch(nums:Array[Int], key:Int, left:Int, right:Int):Int={
  if(left < right){
    val mid = left+(right-left)/2
    if(nums(mid) >= key) binarySearch(nums,key,left,mid)
    else binarySearch(nums, key, mid+1, right)
  }else {
    if(left == nums.length-1 || left == 0) left
    else{
      left
    // math.abs(key-nums(left)) min math.abs(key-nums(left))
    }
  }
}

def minAbsoluteSumDiff(nums1: Array[Int], nums2: Array[Int]): Int = {
  val sortedNums1 = nums1.zipWithIndex.sortBy(_._1)

  val (_,(maxDiffIndex, maxDiff, totalSum)) = nums1.foldLeft(0,(0,0,0)){case((currentIdx, (maxDiffIndex, maxDiff, totalSum)), ele) => val currentDifference =  math.abs(nums1(currentIdx) - nums2(currentIdx))
  if(currentDifference > maxDiff) (currentIdx+1,(currentIdx, currentDifference, totalSum+currentDifference))
  else(currentIdx+1,(maxDiffIndex, maxDiff, totalSum+currentDifference))}

  val nextBest = binarySearch(nums1, nums2(maxDiffIndex),0, nums1.length)

 ???
}
val nums = Array(1,2,4,4,7,8,11)
binarySearch(nums,9,0,nums.length)
binarySearch(nums,8,0,nums.length)
binarySearch(nums,11,0,nums.length)
binarySearch(nums,0,0,nums.length)




