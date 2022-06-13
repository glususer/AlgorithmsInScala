import scala.collection.mutable


def count(l:List[Int], ele:Int, prev:Int, acc:(List[Int],Int) = (List.empty,0)):(List[Int], Int)={
 // println(s"l $l, ele $ele, prev $prev, acc $acc")
  l match{
    case Nil => acc
    case x::xs => count(xs, ele , if(x >= prev && prev <= ele) x else prev, if(x >= prev && prev <= ele) (acc._1:+x, acc._2+1) else if (x < prev && prev <= ele) (acc._1:+x, acc._2)else acc)
  }
}
def canSeePersonsCount(heights: Array[Int]): Array[Int] = {
  heights.zipWithIndex.toList.foldLeft(List[Int](), List[Int]()){case ((stack, acc),(ele, idx)) =>
    val (stck, stckRes) = count(if(stack.dropWhile(_ == ele).isEmpty)heights.takeRight(heights.length-idx-1).toList else stack.dropWhile(_ == ele), ele, Int.MinValue)
   // println(s" stck $stck, acc $acc ele $ele stack_1 $stack_1")
    (stck, acc:+stckRes)
  }._2.toArray

}

val heights = Array(1,5,4,3,7)
canSeePersonsCount(heights)
canSeePersonsCount(Array(10,6,8,5,11,9))
canSeePersonsCount((Array(5,1,2,3,10)))

//count(heights.tail.toList, 10, Int.MinValue)

//count(List(4,3,7), 5,Int.MinValue)

//heights.takeRight(heights.length-0-1)

