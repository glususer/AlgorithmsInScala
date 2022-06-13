//Given an integer A, you have to find the Ath Perfect Number.
//
//A Perfect Number has the following properties:
//
//It comprises only 1 and 2.
//
//The number of digits in a Perfect number is even.
//
//It is a palindrome number.
//
//For example, 11, 22, 112211 are Perfect numbers, where 123, 121, 782, 1 are not.

def solve(A: Int): String  = {
  import scala.collection.mutable
  val queue =  mutable.Queue[Long]()
  queue.enqueue(1)
  queue.enqueue(2)
  var count = queue.size
  var temp = 0

  while(count < A){
    val size = queue.size
    val generatedElements : List[Long]= List(queue).flatten.flatMap(ele => List(ele*1l * 10 + 1, ele *1l* 10 + 2))
    generatedElements.foreach{ele => queue.enqueue(ele)}

    temp = 0
    while(temp < size){
      queue.dequeue()
      temp = temp+1
    }

    count = count+queue.size
  }

  val numOfElementsDequeued = count-queue.size
  temp = numOfElementsDequeued

  while(temp<A-1){
    queue.dequeue()
    temp=temp+1
  }
  val firstHalf = queue.dequeue().toString
  val result= firstHalf+firstHalf.reverse
  result
}

solve(100000)
