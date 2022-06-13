//Given an array of integers A, arrange them in an alternate fashion such that every non-negative number is followed by negative and vice-versa,
// starting from a negative number, maintaining the order of appearance. The number of non-negative and negative numbers need not be equal.
//
//If there are more non-negative numbers, they appear at the end of the array. If there are more negative numbers, they also appear at the array's end.
//
//Note: Try solving with O(1) extra space.

@scala.annotation.tailrec
def helper(neg:Array[Int], pos:Array[Int], acc:List[Int],bit:Int):List[Int]={
  if(neg.isEmpty)acc++pos
  else if(pos.isEmpty)acc++neg
  else {
    if(bit==0)helper(neg.tail, pos, acc:+neg.head,1)
    else helper(neg, pos.tail, acc:+pos.head,0)
  }

}
def solve(A: Array[Int]): Array[Int]  = {
  val(neg,pos) = A.partition(_<0)
  helper(neg,pos, List.empty,0).toArray

  A.indices.foldLeft(List[Int](), neg,pos,0){case ((acc,first,second,bit),i) =>
    if(first.isEmpty)(acc++second,Array.empty, Array.empty,0)
    else if(second.isEmpty)(acc++first,Array.empty, Array.empty,1)
    else {
      if(bit==0)(acc:+first.head,first.tail, second ,1)
      else (acc:+second.head,first, second.tail,0)
    }
  }._1.toArray

}

solve(Array(5, -17, -100, -11))

