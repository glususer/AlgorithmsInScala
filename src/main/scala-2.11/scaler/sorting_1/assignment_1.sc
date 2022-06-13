import scala.annotation.tailrec

@tailrec
def helper(A:Array[Int], B:Array[Int], acc:List[Int]=List.empty):List[Int]={
  if(A.isEmpty)acc++B
  else if(B.isEmpty) acc++A
  else{
    val i = A.head
    val j = B.head
    if(i < j) helper(A.tail, B, acc:+i)
    else if (i==j)helper(A.tail, B.tail, (acc:+i):+j)
    else helper(A, B.tail, acc:+j)
  }
}

def solve(A: Array[Int], B: Array[Int]): Array[Int]  = {
 // helper(A,B).toArray
  val greaterLength = A.length + B.length

 val ( _,_,acc) = (0 until(greaterLength)).foldLeft(A,B,List[Int]()){case ((first,second,acc), _) =>
   //println(s"acc $acc, A ${first.toList} B ${second.toList}")
  if(first.isEmpty && second.isEmpty) (Array.empty, Array.empty, acc)
  else if(first.isEmpty)(Array.empty, Array.empty,acc++second)
  else if(second.isEmpty)(Array.empty, Array.empty,acc++first)
  else{
    val i = first.head
    val j = second.head
    if(i < j) (first.tail, second, acc:+i)
    else if (i==j)(first.tail, second.tail, (acc:+i):+j)
    else(first, second.tail, acc:+j)
  }
  }
  acc.toArray
}

solve(Array(1,3,4,5,6,8), Array(2,4,5,6,7,8,9,10))