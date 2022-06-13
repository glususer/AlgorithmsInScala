//Reverse a linked list A from position B to C.
//
//NOTE: Do it in-place and in one-pass.
class ListNode(val xc: Int){
  var value: Int = xc
  var next: ListNode = null
}
object ListNode{
  def apply(_x: Int, _next: ListNode): ListNode = new ListNode(_x)
}
def listNode2list(list: ListNode): List[Int] = {
  var head: ListNode = list
  var acc = List[Int]()

  while (head != null) {
    acc = acc :+ head.xc
    head = head.next
  }
  acc
}

def list2ListNode(list: List[Int], acc: ListNode): ListNode = {
  list match {
    case Nil => acc
    case x :: xs if (acc == null) => list2ListNode(xs, new ListNode(x))
    case x :: xs => var temp = acc
      while (temp.next != null) {
        temp = temp.next
      }
      temp.next = new ListNode(x)
      list2ListNode(xs, acc)
  }
}

def reverse(startNode:ListNode, endNode:ListNode):ListNode={
  var curr = startNode
  var prev : ListNode = null
  while(curr.next!=endNode){
    val next = curr.next
    curr.next = prev
    prev = curr
    curr = next
  }
  curr.next = prev
  curr
}


def calcLength(head:ListNode):Int={
  var len = 0
  var temp = head
  while(temp != null){
    temp = temp.next
    len = len+1
  }
  len
}
// 1-> 2-> 3->4 ->5->6, B=2, C=4 1-> 4->3->2 ->5->6
def reverseBetween(A: ListNode, B: Int, C: Int): ListNode  = {
   // println(s"${listNode2list(A)}")
  var startNode : ListNode= null
  var endNode:ListNode = null
  var prevStartNode :ListNode= null
  var nextEndNode :ListNode= null

  var count = 1
  var temp = A

  if(B == C)A
  else if(calcLength(A) == C-B+1)   reverse(A, null)
  else if(B==1){
    count = 0
    temp = A
    while(count < C-1){
      temp = temp.next
      count = count+1
    }
    endNode = temp
    nextEndNode = temp.next
    val headReversedList = reverse(A, endNode.next)
    A.next = nextEndNode
    headReversedList
  }
  else{
    while(count <  B-1){
      temp = temp.next
      count = count+1
    }
    startNode = temp.next
    prevStartNode = temp
    count = 0
    temp = A
    while(count < C-1){
      temp = temp.next
      count = count+1
    }
    endNode = temp
    nextEndNode = temp.next
    val headReversedList = reverse(startNode, endNode.next)

    prevStartNode.next = headReversedList
    startNode.next = nextEndNode
    //   println(s"${listNode2list(A)} temp ${temp.xc} ")
    A
  }
}

val a = new ListNode(5)
val b = new ListNode(7)
val c = new ListNode(10)
val d = new ListNode(18)
val e = new ListNode(20)
val f = new ListNode(25)
a.next = b
b.next = c
c.next = d
d.next =  e
e.next = f

val x = reverseBetween(a, 1,2)
println(s"${listNode2list(x)}")
/*reverseBetween(a, 2,5)
reverseBetween(a, 3,4)
reverseBetween(a, 4,4)*/


