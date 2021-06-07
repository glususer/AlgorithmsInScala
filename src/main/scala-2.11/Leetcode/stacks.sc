//https://leetcode.com/problems/132-pattern/
//https://en.wikipedia.org/wiki/Stack-sortable_permutation
import scala.collection.mutable
import scala.collection.mutable.Stack

def helper(s:mutable.Stack[Int], nums:Array[Int], i:Int, acc:List[Int]):List[Int]= {
  if (i == nums.length || acc.length == 3) acc
  else {
    if (nums(i) <= s.top) {
      s.push(nums(i))
      helper(s, nums, i + 1, acc)
    }
    else {
      val b = nums(i)
      val a = s.top
      val c = nums.takeRight(nums.length - i - 1).find(c => c > a && c < b)
      c match {
        case Some(f) => s.pop()
          helper(s, nums, i + 1, acc ::: List(a, b, f))
        case None => helper(s, nums, i + 1, acc)
      }
    }
  }
}

def find132pattern(nums: Array[Int]): Boolean = {
  val s = Stack(nums.head)
 val acc = helper(s, nums,1,List.empty)
  println(acc)
  if(acc.length ==3 ) true else false
}

find132pattern(Array(1,2,5,3))

//https://leetcode.com/problems/find-the-town-judge/
def findJudge(N: Int, trust: Array[Array[Int]]): Int = {
  val s = Stack(1 to N:_*)
  while(s.length >1){
    val a = s.pop()//2
    val b = s.pop()//
    if(trust.exists(arr => arr.head == a && arr.last == b) && ! trust.exists(arr => arr.head == b && arr.last == a)) s.push(b)
    if(trust.exists(arr => arr.head == b && arr.last == a) && ! trust.exists(arr => arr.head == a && arr.last == b)) s.push(a)
    println(s"s $s, contains (${a}, ${b}) ${trust.contains(Array(a,b))} ,  contains (${b}, ${a}) ${trust.contains(Array(b,a))}")
  }
  if(s.isEmpty) -1 else {
    val last = s.pop()
    if (!trust.exists(arr => arr.head == last) && (1 to N).filterNot(_ == last)
      .forall(neighbour => trust.exists(arr => arr.last == last && arr.head == neighbour))) last else -1
  }
}

findJudge(3,Array(Array(1,3),Array(2,3),Array(3,1)))

Array(Array(1,2),Array(1,2,3),Array(1,2,3,4)).exists(arr => arr.head == 1 && arr.last == 2)
