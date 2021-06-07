def permutation(l:List[Int]):List[List[Int]]= {
  def helper(z: List[Int]): List[List[Int]] = {
    z match {
      case x :: xs if xs.isEmpty => List(x) :: Nil
      case x :: y :: xs if xs.isEmpty => List(List(x, y), List(y, x))
      case _ => val y = for {
        i <- z.indices
        (left, right) = z.splitAt(i)
        x = helper(left ::: right.tail).map(lst => z(i) +: lst)
      } yield x
        y.flatten.toList
    }
  }
  helper(l)
}

permutation(List(1,4,3,4))

def helper(l:List[Int], acc:List[Int], factorial:List[Int], k:Int):List[Int] = {
  l match{
    case  x::Nil => acc:+x
    case _ =>
      val q = k/factorial.head
      val remainder_q = k%factorial.head
      val remainder = q%l.length
      val idx = remainder match{
        case y if (remainder_q == 0)=> if(remainder-1 < 0) l.length-1 else remainder-1
        case _ => remainder
      }
      val (left,right) = l.splitAt(idx)
      helper(left:::right.tail,acc:+l(idx),factorial.tail,k)
  }
}
// Leet code problem https://leetcode.com/problems/permutation-sequence/submissions/
def getPermutation(n: Int, k: Int): String = {
  val factorial = (1 until n).reverse.scanRight(1)(_*_).toList
  val l = (1 to n).toList
  helper(l, List(),factorial,k).mkString("")
}



getPermutation(5,48)

// https://leetcode.com/problems/combination-sum-iii/

def combinationSum3(k: Int, n: Int): List[List[Int]] = {
  (1 to 9).combinations(k).map(_.toList).toList.filter(_.sum == n)
}

combinationSum3(9,45)

//https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
def maxLength(arr: List[String]): Int = {
  if (arr.length == 1) {
    if (arr.head.length == arr.head.toSet.size) arr.head.length else 0
  }
  else {
    val y = (for {
      x <- 1 to (arr.length)
      comb = arr.filter(str => str.length == str.toSet.size).combinations(x).toList
        .map(lst => lst.foldLeft("") { case (acc, s) => if (acc.diff(s).length == acc.length) acc + s else acc })
    } yield comb).toList
   val strLen =  y.flatMap(lst => lst.map(_.length))
      if(strLen.isEmpty) 0 else strLen.max
  }
}

maxLength(List("yy","bkhwmpbiisbldzknpm"))


/*val str = List("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p")

val comb = str.combinations(16).toList
  .map(lst => lst.foldLeft(""){case (acc,s) => if(acc.diff(s).length == acc.length) acc+s else acc})
  .map(_.length)

"abc".diff("sd")*/
