import scala.collection.mutable
//https://leetcode.com/problems/partition-array-for-maximum-sum/


def maxSumAfterPartitioning(arr: Array[Int], k: Int): Int = {
  var i = 1
  var m = 1
  val sumArray = Array.fill(arr.length)(0)
  sumArray(0) = arr(0)

  while(i < arr.length){
    var prevMax = 0
    while(m <= k){
      var currSum = 0
      if(i-m >= -1) {
        if(i-m == -1){
          currSum = arr.slice(i+1-m,i+1).max*m
        }
        else currSum = sumArray(i-m)+arr.slice(i+1-m,i+1).max*m
      }
      if(currSum > prevMax) prevMax = currSum
      m+=1
    }
    sumArray(i) = prevMax
    m=1
    i+=1
  }

  sumArray.last
}
maxSumAfterPartitioning(Array(1),1)


/*val a = Array(1,15,7,9,2,5,10)
var i = 1
var m = 1
val  k = 3
val sumArray = Array(a(0),0,0,0,0,0,0)

while(i < a.length){
  var prevMax = 0
  while(m <= k){
    var currSum = 0
    if(i-m >= -1) {
      if(i-m == -1){
        currSum = a.slice(i+1-m,i+1).max*m
      } else currSum = sumArray(i-m)+a.slice(i+1-m,i+1).max*m
    }
    if(currSum > prevMax) prevMax = currSum
    m+=1
  }
  sumArray(i) = prevMax
  m=1
  i+=1
}

println(sumArray.toList.mkString(", "))*/
//https://leetcode.com/problems/minimum-cost-for-tickets/
/*val days = Array(3,4,7,8,9,11,12,18,19,20,24,27)
val costs = Array(2,12,52)
var i = 1
var m = 0
val sumArray = Array.fill(days.length)(0)

sumArray(0) = costs(0)*/

// for each valid day, we are calc the cose
def lastDays(validDays:Int, days:Array[Int],i:Int, sumArray:Array[Int],cost:Int,costs:Array[Int]):Int={
  if(days(i) -days(i-1) == validDays) sumArray(i-1)+cost

  // Given valid day is expired and I need tobuy new ticket so will buy least expensive ticket -
  else if (days(i) - days(i-1) > validDays) sumArray(i-1)+costs.min

    // find the day(Index) where I can buy ticket which will be valid on current day
  else{
    var k = 0
    while(i-1-k >=0 && days(i) - days(i-1-k) < validDays ){
      k = k+1
    }
    val idx = i-k-1
    println(s"idx = ${idx}, validDays $validDays cost $cost day ${days(i)}")
    if(idx < 0) cost else sumArray(idx)+cost
  }
}

def mincostTickets(days: Array[Int], costs: Array[Int]): Int = {
  var i = 1
  val sumArray = Array.fill(days.length)(0)

  sumArray(0) = costs.min

  while(i < days.length){
  val currCostList = List(lastDays(1,days,i,sumArray,costs(0), costs),
      lastDays(7,days,i,sumArray,costs(1),costs),
      lastDays(30,days,i,sumArray,costs(2), costs))
    println(currCostList,days(i))
    sumArray(i) = currCostList.min
    i+=1
  }
  println(sumArray.toList.mkString(", "))
  sumArray.last
}
/*while(i < days.length){
val  currCost = List(sumArray(i-1)+costs(0),
  lastDays(7,days,i,sumArray,1),
  lastDays(30,days,i,sumArray,2))
  println(currCost, days(i))
  sumArray(i) = currCost.min
  i+=1
}*/
mincostTickets(Array(1,2,3,4,5,6,7,8,9,10,30,31),Array(2,7,15))

def findLength(A: Array[Int], B: Array[Int]): Int = {
  val sumArray:Array[Array[Int]] = Array.fill(A.length)(Array.fill(B.length)(0))
  var i = A.length-2
  var j = B.length -2
  var ans = 0
  while(i>=0){
    while(j>=0){
      if(A(i) == B(j)) sumArray(i)(j) = sumArray(i+1)(j+1)+1
      if(ans < sumArray(i)(j)) ans = sumArray(i)(j)
      j-=1
    }
    i-=1
  }
  ans
}

findLength(Array(1,2,3,2,1),Array(3,2,1,4,7))
val A = Array(83,20,17,43,52,78,68,45)


//https://leetcode.com/problems/longest-arithmetic-subsequence/
def longestArithSeqLength(A: Array[Int]): Int = {
  def helper(arr:Array[Int], acc:List[Map[Int,Int]],i:Int):List[Map[Int,Int]]={
    arr match{
      case a if(a.length == 0) => acc
      case _ => val current = arr.head
        val currentDiff = A.slice(0,i).map(num => current-num)
        val currentMap = acc.zip(currentDiff).map{case (diffMap, current) =>
          if (diffMap.contains(current)) (current,diffMap(current)+1) else (current,2)}.toMap
        helper(arr.tail,acc:+currentMap,i+1)
    }
  }
 val x = helper(A.tail,List(Map(0->0)),1)
  (x.foreach(println))
  x.map(diffMap => diffMap.values.max).max
}

longestArithSeqLength(Array(3,4,6,7,10,15,20))

//https://leetcode.com/problems/largest-sum-of-averages/

def mapPartition(partition:List[(Int,Int)], K:Int, current : Int):List[List[(Int,Int)]] ={
  if(partition.length == K) {
    val updatedLastParititon = (partition.last._1+current,partition.last._2+1)
    val lst :List[(Int,Int)]= partition.dropRight(1):+updatedLastParititon
    List(lst)
  }
  else{
    val includeEleInLastPartition:List[(Int,Int)] = partition.dropRight(1):+(partition.last._1+current,partition.last._2+1)
    val createNewPartitionForCurrentEle : List[(Int,Int)] = partition:+(current,1)
   List(includeEleInLastPartition,createNewPartitionForCurrentEle)
  }
}

def helper(arr:List[Int], acc:List[List[(Int,Int)]],K:Int):List[List[(Int,Int)]]={
  arr match{
    case Nil => acc
    case x::xs => val currentPartitions = acc.flatMap(partition => mapPartition(partition,K,x))
      helper(xs,currentPartitions,K)
  }
}

def largestSumOfAverages(A: Array[Int], K: Int): Double = {
  val sumWithFreq =  helper(A.toList.tail,List(List((A.head,1))),K)
  sumWithFreq.foreach(println)
  sumWithFreq.map(lst => lst.map{case (sumOfEle,freq) => sumOfEle*1.0/freq}.sum).max
}
//[4737,4611,1752,7231,4395,8569,1965,2591,2258,2874,1196,9770,1439,1694,7144,716,3764,1348,9831,6186]
//12
//largestSumOfAverages(Array(4737,4611,1752,7231,4395,8569,1965,2591,2258,2874,1196,9770,1439,1694,7144,716,3764,1348,9831,6186),12)

//https://leetcode.com/problems/longest-common-subsequence/

def helper(a:Array[Array[Int]],s1:String, s2:String,i:Int,j:Int):Unit={
  if(j==s2.length) return
  else{
    if(s1(i) == s2(j)){
      if(i==0 || j==0) a(i)(j)=1
      else a(i)(j)=a(i-1)(j-1)+1
    }
    else{
      if(i==0 && j ==0 ) a(i)(j) = 0
      else if(i==0 )a(i)(j) = a(i)(j-1)
      else if (j ==0) a(i)(j) = a(i-1)(j)
      else a(i)(j) = Math.max(a(i-1)(j),a(i)(j-1))
    }
    helper(a, s1, s2, i, j + 1)
  }
}
def longestCommonSubsequence(text1: String, text2: String): Int = {
  val dpArr = Array.fill(text1.length)(Array.fill(text2.length)(0))
  var i = 0
  while(i < text1.length){
    helper(dpArr, text1, text2,i,0)
    i+=1
  }
  dpArr(text1.length-1)(text2.length-1)
}

longestCommonSubsequence("01111","10101")

//https://leetcode.com/problems/maximum-length-of-repeated-subarray/
def helper(a:Array[Array[Int]],s1:Array[Int], s2:Array[Int],i:Int,j:Int):Unit={
 // println(a.map(_.toList).toList)
  if(j==s2.length) return
  else{
    if(s1(i) == s2(j)){
      if(i==0 || j==0) a(i)(j)=1
      else a(i)(j)=a(i-1)(j-1)+1
    }
    else{
      if(i==0 && j ==0 ) a(i)(j) = 0
      else if(i==0 )a(i)(j) = a(i)(j-1)
      else if (j ==0) a(i)(j) = a(i-1)(j)
      else a(i)(j) = Math.max(a(i-1)(j),a(i)(j-1))
    }
    helper(a, s1, s2, i, j + 1)
  }
}

def findLength1(A: Array[Int], B: Array[Int]): Int = {
  val dpArr = Array.fill(A.length)(Array.fill(B.length)(0))
  var i = 0
  while(i < A.length){
    helper(dpArr, A, B,i,0)
    i+=1
  }
  dpArr(A.length-1)(B.length-1)
}

//findLength1(Array(0,1,1,1,1), Array(1,0,1,0,1))

//https://leetcode.com/problems/palindromic-substrings/
//https://www.youtube.com/watch?v=XmSOWnL6T_I

def initialize(array: Array[Array[Int]], str: String):Unit={
  var i = 0
  var j = 0
  while(i<str.length){
    array(i)(i)=1
    i+=1
  }
  i=0
  while(i<str.length-1){
   if(str.charAt(i)==str.charAt(i+1)) array(i)(i+1)= 1
    i+=1
  }
}

def countSubstrings(s: String): Int = {
 val grid = Array.fill(s.length)(Array.fill(s.length) (0))
 var diff = 2
 var i= 0
 var j = 0
  initialize(grid,s)
  while(diff < s.length){
    j = j+diff
    while(j<s.length) {
      if (s.charAt(i) == s.charAt(j) && grid(i + 1)(j - 1) == 1) grid(i)(j) = 1
      j+=1
      i+=1
    }
    j=0
    i=0
    diff+=1
  }
  grid.map(_.toList).toList.foreach(lst => println(lst))

  grid.map(arr => arr.count(z => z==1)).sum
}

countSubstrings("bb")

//https://leetcode.com/problems/longest-palindromic-substring/
/*Similar to above except streo the lenght and string instead of 0 and 1*/
def initialize1(array: Array[Array[(String,Int)]], str: String):Unit={
  var i = 0
  var j = 0
  while(i<str.length){
    array(i)(i)=(str.substring(i,i+1),1)
    i+=1
  }
  i=0
  while(i<str.length-1){
    if(str.charAt(i)==str.charAt(i+1)) array(i)(i+1)= (str.substring(i,i+2),2)
    i+=1
  }
}

def longestPalindrome(s: String): String = {
  println(s"s length is ${s.length}")
  val grid = Array.fill(s.length)(Array.fill(s.length)(("",0)))
  var i = 0
  var j = 0
  var diff =2
  initialize1(grid,s)

  while(diff < s.length)
  {
    j=j+diff
    while(j<s.length){
      val before = grid(i)(j-1)
      val diagonal = grid(i+1)(j-1)
      val below = grid(i +1)(j)
      if(s.charAt(i) == s.charAt(j) &&
        s.charAt(i).toString+diagonal._1+s.charAt(j).toString ==s.substring(i,j+1))
      {
        grid(i)(j) = (s.charAt(i)+diagonal._1+s.charAt(j),diagonal._2+2)
      }
      else{
        if(before._2 == 0 &&  below._2==0 && diagonal._2 == 1)
          grid(i)(j)=("",0)
        else {
          grid(i)(j) = List(before,
            below,
            grid(i + 1)(j - 1)).maxBy(_._2)
        }
      }
      j+=1
      i+=1
    }
    j=0
    i=0
    diff+=1
  }
 // grid.map(_.toList).toList.foreach(lst => println(lst))
  grid.map(_.last).maxBy(_._2)._1
}

val x = longestPalindrome("asd")
 println(s"palindorm length is ${x.length}")

//https://leetcode.com/problems/minimum-falling-path-sum/
def helper(prevRow:Array[Int], grid:Array[Array[Int]], count:Int):Unit={
  if(count == grid.length) return
  else{
    val currentRow = grid(count)
   val newRow =  currentRow.zipWithIndex.map{ele =>
      prevRow.zipWithIndex.filter{prev => Math.abs(prev._2-ele._2)<=1}.map(prev => prev._1+ele._1).min}

    newRow.zipWithIndex.foreach{newEle => grid(count)(newEle._2) = newEle._1}

    helper(newRow, grid,count+1)
  }
}
def minFallingPathSum(A: Array[Array[Int]]): Int = {
  helper(A.head,A,1)
  A.last.min
}
val arr = Array(Array(1),Array(4))
minFallingPathSum(arr)



//https://leetcode.com/problems/maximum-profit-in-job-scheduling/
  //https://www.youtube.com/watch?v=cr6Ip0J9izc , sort by end time and for each end time,
  // check if the previous non overlapping jobs profit is greater then the current profit.
// Not accepted, need to use binary search to find indices
  def isNotOverlapping(interval1: (Int, Int), interval2: (Int, Int)) :Boolean= {
    interval2._1 >= interval1._2
  }

def jobScheduling(startTime: Array[Int], endTime: Array[Int], profit: Array[Int]): Int = {
  val intervalsWithProfit = profit.zip(startTime.zip(endTime))
    .sortBy{case (profit,indices)=>(indices._2,indices._1,profit)}
  val intervals =   intervalsWithProfit.map(_._2)
  val profitAccToEndTime = intervalsWithProfit.map(_._1)

  val cumulativeProfit = Array(profitAccToEndTime:_*)

  var i=0
  var j=1
  while(j<intervals.length){
    while(i<j){
      if(isNotOverlapping(intervals(i),intervals(j)) &&
        cumulativeProfit(i)+profitAccToEndTime(j) > cumulativeProfit(j)) {
        cumulativeProfit(j) = cumulativeProfit(i)+profitAccToEndTime(j)
      }
      i+=1
    }
    i=0
    j+=1
  }
  println(cumulativeProfit.toList)
  cumulativeProfit.max
}

jobScheduling(Array(1,2,3,4,6),Array(3,4,10,6,9), Array(20,20,100,70,60))

//https://leetcode.com/problems/longest-palindromic-subsequence/


def longestPalindromeSubseq(s: String): Int = {
  val dp = Array.fill(s.length)(Array.fill(s.length)(1))
  var i=0
  var j=1
  while(j<s.length){
    if(s.charAt(i) == s.charAt(j))dp(i)(j) = 2
    i+=1
    j+=1
  }
  dp.foreach(arr => println(arr.toList))

  var diff = 2
  i=0
  j=0
  while(diff<s.length){
    j=0+diff
    i=0
    while(j<s.length){
      if(s.charAt(i) == s.charAt(j))dp(i)(j) = dp(i+1)(j-1)+2
      else dp(i)(j) = Math.max(dp(i)(j-1),dp(i+1)(j))
      i+=1
      j+=1
    }
    diff+=1
  }
  dp.foreach(arr => println(arr.toList))
  dp(0)(s.length-1)
}

longestPalindromeSubseq("abccab")

//https://leetcode.com/problems/edit-distance/
//https://www.youtube.com/watch?v=We3YDTzNXEk
def minDistance(word1: String, word2: String): Int = {
  val m = word1.length
  val n = word2.length

  val dp = Array.fill(m+1, n+1)(0)
  for (i <- 1 to m) dp(i)(0) = i
  for (j <- 1 to n) dp(0)(j) = j
  for (i <- 1 to m; j <- 1 to n) {
    val a = dp(i)(j-1) + 1
    val b = dp(i-1)(j) + 1
    val c = if (word1(i-1) == word2(j-1)) dp(i-1)(j-1) else 1 + dp(i-1)(j-1)
    dp(i)(j) = a min b min c
  }
  dp(m)(n)
}
//"zoologicoarchaeologist"
//"zoogeologist"
val word1= "zoologicoarchaeologist"
val word2 = "zoogeologist"
println(word1.length, word2.length)
minDistance(word1,word2)

//https://leetcode.com/problems/delete-operation-for-two-strings/
//https://www.youtube.com/watch?v=-wxbv5l3Poc
def minDistance1(word1: String, word2: String): Int = {
  val m = word1.length
  val n = word2.length
  val arr = Array.fill(m+1,n+1)(0)
  arr.foreach(a => println(a.toList))
  for(i<-1 to m) arr(i)(0) = i
  for(j<-1 to n) arr(0)(j) = j

  for(i<-1 to m;j<-1 to n){
    if(word1(i-1) == word2(j-1)) arr(i)(j) = arr(i-1)(j-1)
    else arr(i)(j) = 1+Math.min(arr(i-1)(j),arr(i)(j-1))
  }

  //arr.foreach(a => println(a.toList))
  arr(m)(n)
}

minDistance1("sea","eat") // 2

//https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
// problem is exactly same as above, just that now instead of adding +1 as the cost of deletion (line no 427) ,
// we are adding the ascii value of the char we are deleting
def minimumDeleteSum(s1: String, s2: String): Int = {
  val m = s1.length
  val n = s2.length
  val arr = Array.fill(n+1,m+1)(0)

  val firstRow = s1.scanLeft(0){case (acc,ch) => acc+ch.toInt}.toArray
  arr(0) = firstRow

  val firstCol = s2.scanLeft(0){case (acc,ch) => acc+ch.toInt}.toArray
  firstCol.zipWithIndex.foreach{num => arr(num._2)(0) = num._1}

  for(i<-1 to n;j<- 1 to m){
    if(s2.charAt(i-1) == s1.charAt(j-1)) arr(i)(j) = arr(i-1)(j-1)
    else arr(i)(j) = Math.min(arr(i-1)(j)+s2.charAt(i-1).toInt,
      arr(i)(j-1)+s1.charAt(j-1).toInt)
  }
  //  arr.foreach(a => println(a.toList))

  arr(n)(m)

}
minimumDeleteSum("delete","leet") //403

//https://leetcode.com/problems/coin-change-2/
// not exactly same but somewhat on similar lines - https://www.youtube.com/watch?v=Y0ZqKpToTic
def change(amount: Int, coins: Array[Int]): Int = {
  if (amount == 0) 1
  else if(coins.isEmpty) 0
  else {
    val col = amount
    val row = coins.length - 1
    val arr = Array.fill(coins.length, amount + 1)(0)

    for (i <- 0 to row) arr(i)(0) = 0
    for (j <- 1 to col) arr(0)(j) = if (j % coins(0) == 0) 1 else 0

    for (i <- 1 to row; j <- 1 to col) {
      // go back to coins(i) in the 2d arr and add that value to current sum. If that value is 0, add 1
      val prev = {
        if (j - coins(i) > 0) arr(i)(j - coins(i))
        else if (j - coins(i) == 0) 1
        else 0
      }
      arr(i)(j) = arr(i - 1)(j) + prev
    }
      arr.foreach(a => println(a.toList))

    arr(row)(amount)
  }
}

//https://leetcode.com/problems/frog-jump/
def updateAcc(stones: Array[Int],acc:mutable.Map[Int,Set[Int]], curr : Int):mutable.Map[Int,Set[Int]]={
  val newStepsWithValidStones = acc.getOrElse(curr,Set.empty)
    .flatMap(step => List(step-1,step,step+1)
      .filter(step => step >0 && stones.contains(step+curr)))

  newStepsWithValidStones.foreach(step => acc.update(curr+step,Set(step)++acc.getOrElse(curr+step,Set())))
  acc

}
def canCross(stones: Array[Int]): Boolean = {

  val result = stones.drop(1).foldLeft(mutable.Map(stones(0)+1->Set(1)))
  {
    case (acc,ele) => updateAcc(stones,acc,ele)
  }
  result.exists{case (stone,steps) => stone == stones.last && steps.nonEmpty}
}

canCross(Array(0,1,2,3,4,8,9,11))

//https://leetcode.com/problems/integer-break/
def integerBreak(n: Int): Int = {
  val dp = Array.fill(n+1)(1)

  for(i <- 3 to n){

    dp(i) = (1 until i).flatMap(num => List(num * dp(Math.abs(i-num)),num*(Math.abs(i-num)))).max
    // println((1 until i).map(num => List(num * dp(Math.abs(i-num)),num*(Math.abs(i-num)))), i)

  }
  println(dp.toList)

  dp(n)
}

integerBreak(10)

//https://leetcode.com/problems/best-team-with-no-conflicts/
def helperPartition(scoresWithAge:List[(Int,Int)],acc:List[Int]):List[Int]={
  scoresWithAge match{
    case Nil  => acc
    case x::xs => val scoresGreaterThen = scoresWithAge.tail.
      scanLeft(x._1,x._1){case (acc,ele) => if(ele._1 >= acc._2) (acc._1+ele._1,ele._1) else (acc._1,acc._2)}
      println(scoresGreaterThen)
      helperPartition(xs,acc:+(scoresGreaterThen.last._1))
  }
}

def bestTeamScore(scores: Array[Int], ages: Array[Int]): Int = {
  println(s"input is ${scores.zip(ages).sortWith((a,b) => if(a._2 == b._2) a._1 <= b._1 else a._2 <= b._2).toList}")
  val x = helperPartition(scores.zip(ages).sortWith((a,b) => if(a._2 == b._2) a._1 <= b._1 else a._2 <= b._2).toList,List.empty)
    println(x)
    x.max
}
//[319776,611683,835240,602298,430007,574,142444,858606,734364,896074]
//[1,1,1,1,1,1,1,1,1,1]
//[9,2,8,8,2]
//[4,1,3,3,5]
//[1,1,1,1,1,1,1,1,1,1]
//[811,364,124,873,790,656,581,446,885,134]
bestTeamScore(Array(596,277,897,622,500,299,34,536,797,32,264,948,645,537,83,589,770),
  Array(18,52,60,79,72,28,81,33,96,15,18,5,17,96,57,72,72))

/*bestTeamScore(Array(9,2,8,8,2),
  Array(4,1,3,3,5))

bestTeamScore(Array(1,1,1,1,1,1,1,1,1,1),
  Array(811,364,124,873,790,656,581,446,885,134))*/

