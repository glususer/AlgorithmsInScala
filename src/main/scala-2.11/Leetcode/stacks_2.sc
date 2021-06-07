import scala.collection.mutable

//https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/
def minAddToMakeValid(S: String): Int = {
  val minParenthesis = S.foldLeft(0,0){case (acc, ch) => if(ch == '(') (acc._1+1, acc._2)
  else if(ch == ')' && acc._1 > 0) (acc._1-1, acc._2)
  else if(ch == ')' && acc._1<=0) (acc._1, acc._2+1)
  else acc }
  minParenthesis._1+minParenthesis._2
}

minAddToMakeValid("())")

//https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
def reverseParentheses(s: String): String = {

 val stack =  s.foldLeft(mutable.Stack[Char]()){case (acc, ch) =>
    if(ch == ')') {
      val toBePushed = acc.foldLeft(List[Char](),true){case (l,ele) =>
        if(ele != '(' && l._2)
        {acc.pop()
          (l._1:+ele,l._2)}
        else  (l._1,false)
      }._1.mkString("")
      println(s"toBePushed ${toBePushed}")
      acc.pop()
      toBePushed.foreach(ch => acc.push(ch))
      acc
    }
    else{
      acc.push(ch)
  }
 }
  stack.foldRight(""){case (ele,acc) => acc+ele}

}

reverseParentheses("a(bcdefghijkl(mno)p)q")

/*val l = List('(','e','d','(','e','t','(','o','c').reverse
val s = mutable.Stack[Char](l:_*)
s.top
s.foldLeft(List[Char](),true){case (l,ele) =>
  if(ele != '(' && l._2)
{s.pop()
  (l._1:+ele,l._2)}
else  (l._1,false)
}._1.mkString("").reverse

s.top*/

//https://leetcode.com/problems/merge-intervals/
def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
 val mergedIntervals =  intervals.sortBy(arr => arr.head).foldLeft(mutable.Stack[Array[Int]]()){case (acc,ele) =>
    if(acc.isEmpty) acc.push(ele)
    else{
      val top = acc.top
      if(top.last>= ele.head){
        acc.pop
        val start = if(top.head > ele.head) ele.head else top.head
        val end = if(top.last > ele.last) top.last else ele.last
     //   println(s"mergedInterval is {$start,$end}")
        acc.push(Array(start,end))
      }
        else{
        acc.push(ele)
      }
    }
  }

  mergedIntervals.reverse.foldLeft(Array[Array[Int]]()){case (acc, ele) => acc:+ele}
}
val arr1 = Array(Array(1,3),Array(2,6),Array(8,10),Array(15,18))
val arr2 = Array(Array(1,4), Array(1,4))
merge(arr2).foreach(arr => println(s"${arr.toList}"))
