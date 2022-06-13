//Given a linked list A and a value B, partition it such that all nodes less than B come before nodes greater than or equal to B.
//
//You should preserve the original relative order of the nodes in each of the two partitions

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
def partition(A: ListNode, B: Int): ListNode  = {
  var dummyLeft = new ListNode(0)
  var dummyRight = new ListNode(0)
  val rightHead = dummyRight
  val leftHead = dummyLeft
  var current = A
  while(current !=null){
    //println(s" left ${listNode2list(leftHead)} right ${listNode2list(rightHead)} current ${current.xc}")
    if(current.xc < B){
      dummyLeft.next = current
      dummyLeft = dummyLeft.next
    }
    else {
      dummyRight.next = current
      dummyRight = dummyRight.next
    }
    current = current.next
    dummyLeft.next = null
    dummyRight.next = null
  }

  dummyLeft.next = rightHead.next
  leftHead.next
}

val a = new ListNode(1)
val b = new ListNode(2)
val c = new ListNode(3)
val d = new ListNode(1)
val e = new ListNode(3)
//val f = new ListNode(2)
/*val g = new ListNode(1)
val h = new ListNode(3)
val i = new ListNode(2)
val j = new ListNode(2)*/
a.next = b
b.next=c
c.next = d
d.next =  e
//e.next = f
/*f.next = g
g.next = h
h.next = i
i.next = j*/
listNode2list(partition(a,2))