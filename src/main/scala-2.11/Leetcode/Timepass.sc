import scala.collection.mutable

//https://leetcode.com/problems/find-common-characters/
def commonChars(A: Array[String]): List[String] = {
  val x = A.zipWithIndex.flatMap { case (str, index) => str.map(ch => (ch, index)) }
   val y =  x.toList.groupBy(_._1)
    .mapValues(lst => lst.map(_._2)).filter { case (key, value) => (0 to A.length).intersect(value).length == A.length }
     val z = y.flatMap { case (key, value) => val count = value.groupBy(identity).mapValues(_.size).toList.minBy(_._2)
    List.fill(count._2)(key.toString)
  }.toList
  z
}

commonChars(Array("cool","lock","cook"))

//https://leetcode.com/problems/arranging-coins/

def helper(coins:Int, count:Int):Int={
  coins match{
    case 0 => count-1
    case x if x < 0  => count-2
    case _ => helper(coins-count, count+1)
  }
}
def arrangeCoins(n: Int): Int = {
  helper(n,1)
}

arrangeCoins(9)

val names = mutable.Map("Mark" -> 100, "Jonathan" -> 350, "Bob" -> 65)
names.update("Jacob",names.getOrElse("Jacob",0)+150)
println(names)



def checkPossibility(nums: Array[Int]): Boolean = {
  val numOfInversions = nums.scanRight(nums.last+1,nums.last){case (ele,acc) =>(acc._2-ele,ele)}.map(_._1)
 println(s"numOfInversions ${numOfInversions.toList}")
  if(numOfInversions.forall(ele => ele >= 0)) true
  else{
    val x = nums.zipWithIndex.map{case(ele,idx) => nums.splitAt(idx)._1.count(_ >=ele)}
    println(x.toList)
    x.forall(nosGreaterThenCurrent =>nosGreaterThenCurrent <2)
  }
}
val nums = Array(5,7,1,8)
checkPossibility(nums)

val x = nums.zipWithIndex.map{case(ele,idx) => nums.splitAt(idx)._1.count(_ >=ele)}
println(x.toList)
  x.forall(nosGreaterThenCurrent =>nosGreaterThenCurrent <2)
