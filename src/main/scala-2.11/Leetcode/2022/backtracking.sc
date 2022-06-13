import scala.collection.SortedSet
import scala.collection.immutable.ListSet
import scala.collection.mutable.ListBuffer

// (10,1,2,7,6,1,5)
def helper(arr:Array[Int],target:Int, currentList:List[Int], currentIdx:Int, acc:Set[List[Int]]):Set[List[Int]]= {
  if (target < 0) acc
  else if (target == 0){
    val level = currentList.sorted
    if(acc.contains(level)) acc else acc+level
  }
  else {
    val combinations = (currentIdx until arr.length).foldLeft(acc) { case (set, i) =>
      if(i == currentIdx || arr(i) != arr(i-1)) {
        val currentSet = helper(arr, target - arr(i), currentList :+ arr(i), i + 1, acc)
        currentSet ++ set
      }else set
    }
    combinations
  }
}




def combinationSum2(candidates: Array[Int], target: Int): List[List[Int]] = {
 helper(candidates, target, List.empty,0,Set.empty).toList
}

//val arr = Array(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1)
val arr = Array(10,1,2,7,6,1,5)
//combinationSum2(arr,8)

//https://leetcode.com/problems/letter-tile-possibilities/
def dfs(tiles: String, set:Set[String],curr: String):Set[String] = {
  (0 until tiles.length).foldLeft(set){case (acc,i) =>
    println(s"tiles $tiles set  $acc curr $curr i $i ")
    val next = curr + tiles.charAt(i)

    val currSet =  dfs(tiles.substring(0,i) + tiles.substring(i + 1),
     if(next.nonEmpty) acc+next else acc,
      next
    )
    acc++currSet
  }
}
def numTilePossibilities(tiles: String): Int = {
 val set =  dfs(tiles, Set.empty, "")
  println(s" set $set")
  set.size
}
numTilePossibilities("ABC")

//https://leetcode.com/problems/permutations/

def dfsPermute(nums:Array[Int], acc:Set[List[Int]], curr:List[Int], orginalLength: Int):Set[List[Int]]={
    nums.indices.foldLeft(acc){case (set, idx) =>
      val next = curr:+nums(idx)
      val currSet = dfsPermute(nums.slice(0,idx)++nums.slice(idx+1, nums.length),
        if(next.length == orginalLength) acc+next else acc,
        next,
        orginalLength)
      set++currSet
    }
}

def permute(nums: Array[Int]): List[List[Int]] = {
val permList = dfsPermute(nums, Set.empty, List.empty, nums.length).toList
 permList.foreach(println)
  permList
}

//permute(Array(1,2,3,4))

//https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
/*def getHappyStringDFSHelper(str: String, currentStr: String, originalLength: Int, acc: List[String]): List[String] = {

  str.indices.foldLeft(acc) { case (accLst, idx) =>

    val next = currentStr :+ str.charAt(idx)
    println(s"str $str, currentStr $currentStr  accLst $accLst idx $idx next $next")

    val kthString = getHappyStringDFSHelper(
      str.substring(0, idx) + str.substring(idx + 1),
      next,
      originalLength,
      if (next.length == originalLength && !accLst.contains(next)) accLst :+ next else accLst
    )

    accLst ++ kthString
  }
}

def getHappyString(n: Int, k: Int): String = {
  val x = getHappyStringDFSHelper("abc","",n, List.empty).distinct
  x.foreach(println)
  x(k-1)
}*/
def generatePerm(arr: Array[Char], currentLength: Int, res: String, acc: scala.collection.mutable.ListBuffer[String]): Unit = {
  if (currentLength == 0) {
    acc.append(res)
    return
  }
  arr.indices.foreach(idx => {
    println(s"arr ${arr.toList} idx $idx currentStr $res acc $acc")
    if (res == "" || res.charAt(res.length - 1) != arr(idx)) {
      val pre: String = res + arr(idx)
      generatePerm(arr, currentLength - 1, pre, acc)
    }
  })
}

def generatePerm2(arr: Array[Char], currentLength: Int, currentStr: String, acc: List[String]): List[String] = {
  if (currentLength == 0) acc:+currentStr
  else {
    arr.indices.foldLeft(acc) { case (accSet, idx) => {
      println(s"arr ${arr.toList} idx $idx currentStr $currentStr acc $acc")
      if (currentStr == "" || currentStr.last != arr(idx)) {
        val pre: String = currentStr + arr(idx)
        val currSet = generatePerm2(arr, currentLength - 1, pre, accSet)
        currSet
      } else accSet
    }
    }
  }
}

def getHappyString(n: Int, k: Int): String = {
  val arr = Array('a', 'b', 'c')
  val  l = generatePerm2(arr, n, "", List.empty)
  if (l.size >= k)  l(k - 1)
  else ""
}

getHappyString(1,3)