//https://leetcode.com/problems/number-of-longest-increasing-subsequence/

def findNumberOfLIS(nums: Array[Int]): Int = {
  case class Info(length:Int, count:Int, index:Int)

  val acc = Array(Info(1,1,0))

  val maxLengthMaxCount = nums.zipWithIndex.tail.foldLeft(acc){case (acc, ele) =>

    val indices = nums.zipWithIndex.slice(0,ele._2).filter(_._1 < ele._1).map(_._2)

    if(indices.isEmpty)(acc:+Info(1,1,acc.length))
  else
    {
    val maxInfo = acc.filter(info  => indices.contains(info.index)).maxBy(_.length)
    val indexMaxLength = acc.filter(info => info.length == maxInfo.length && indices.contains(info.index))
    val numMaxLength = indexMaxLength.map(_.count).sum
    (acc:+ Info(maxInfo.length + 1,numMaxLength, acc.length))
  }
}
  val maxLength = maxLengthMaxCount.maxBy(_.length).length
  maxLengthMaxCount.filter(info => info.length == maxLength).map(_.count).sum
}

//findNumberOfLIS(Array(1,2,3,1,2,3,1,2,3))

//https://leetcode.com/problems/reducing-dishes/
def maxSatisfaction(satisfaction: Array[Int]): Int = {
  val sortedSatisfaction = satisfaction.sorted

  sortedSatisfaction.zip(1 to satisfaction.length)
    .foldRight(0){case (ele,acc) =>
      val currentDishesCount = sortedSatisfaction.length-ele._2+1
      val currentSatisfaction = sortedSatisfaction.slice(sortedSatisfaction.length-currentDishesCount,sortedSatisfaction.length)
        .zip(1 to currentDishesCount)
      .map(dish => dish._1*dish._2).sum

      if(currentSatisfaction > acc)currentSatisfaction else acc
    }
}
//maxSatisfaction(Array(-1,-4,-5))

//https://leetcode.com/problems/longest-valid-parentheses/
// idea is to calculate valid parenthesis by adding currentValidParenthesis + previousVaidParenthesis
def longestValidParentheses(s: String): Int = {
  val dpArr = Array.fill(s.length)(0)
  val (_, result) = s.zipWithIndex.foldLeft(0, 0) { case ((leftCount, result), (ele, idx)) =>
    // println(s"leftCount is $leftCount and result is $result, elem is $ele and index is $idx")
    if (ele == '(') (leftCount + 1, result)
    else if (leftCount > 0) {
      dpArr(idx) = dpArr(idx - 1) + 2
      val previousIndexThatHadCompleteParenthesis = idx - dpArr(idx)
      if (previousIndexThatHadCompleteParenthesis > 0) dpArr(idx) = dpArr(idx) + dpArr(previousIndexThatHadCompleteParenthesis)
      (leftCount - 1, math.max(result, dpArr(idx)))
    } else (leftCount, result)
  }
  result
}

longestValidParentheses("()(()))")

