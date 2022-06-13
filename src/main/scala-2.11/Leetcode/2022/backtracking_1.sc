def subsetHelper(nums:Array[Int], acc:Set[Set[Int]], current:Set[Int]):Set[Set[Int]]={
  nums.indices.foldLeft(acc){case (accSet,idx) =>
    val next = current+nums(idx)
   // println(s"nums ${nums.toList} acc ${acc} current $current next $next idx $idx")
    val currLst = subsetHelper(nums.slice(idx+1, nums.length),
      if(! acc.contains(next)) accSet+next else accSet,
      next
    )
    currLst++accSet
  }
}


def subsets(nums:Array[Int]):List[List[Int]]={
  scala.util.Sorting.quickSort(nums)
  subsetHelper(nums, Set.empty, Set.empty.empty).toList.map(_.toList):+ List.empty
}

def binaryToInt(str:String):Int={
  str.foldRight(0,0){case (ele,(sum,power)) =>
    val currentContribution = if(ele == '0') 0 else  math.pow(2,power).toInt
    (sum + currentContribution, power+1)
  }._1

}
def generateGrayCode(n:Int, current:Int = 1,acc:List[String] = List.empty):List[String]={
  current match{
    case y if n < current => acc
    case 1 => generateGrayCode(n, current+1, List("0","1"))
    case _ =>
      val left = acc.map(l => "0"+l)
      val right = acc.reverse.map(r => "1"+r)
      generateGrayCode(n,current+1,left++right)
  }
}

def circularPermutation(n: Int, start: Int): List[Int] = {
  val grayCode = generateGrayCode(n).map(binaryToInt)
    val (left,right) = grayCode.span(_ != start)
  right++left
}

circularPermutation(3,2)