//Given a singly linked list A
//
// A: A0 → A1 → … → An-1 → An
//reorder it to:
//
// A0 → An → A1 → An-1 → A2 → An-2 → …
//You must do this in-place without altering the nodes' values.

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

def getReversedList(startNode:ListNode, endNode:ListNode):ListNode={
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

def getMiddleNode(A:ListNode):ListNode={
  var slow = A
  var fast = A
  while(fast != null && fast.next != null){
    slow = slow.next
    fast = fast.next.next
  }
  slow
}
//1->2->3->4->5, 1->5->2->4->3
def reorderList(A: ListNode): ListNode  = {
  if(A.next == null) A
  else {
    val mid = getMiddleNode(A)

    val sorted = {
      var temp = A
      while (temp.next != mid)
        temp = temp.next
      temp.next = null
      A
    }
    val reversedList = getReversedList(mid, null)

    /*println(s"sorted ${listNode2list(sorted)}")
  println(s"reversed ${listNode2list(reversedList)}")
*/
    var h1 = sorted
    var h2 = reversedList
    var next1: ListNode = null
    var next2: ListNode = null
    while (h1 != null && h2 != null) {
      next1 = h1.next
      h1.next = h2
      next2 = h2.next
      h2.next = next1
      h1 = next1
      h2 = next2
    }

    var temp = A
    while (temp.next != null)
      temp = temp.next

    if (h1 != null) temp.next = h1
    if (h2 != null) temp.next = h2
    A
  }
}
// 5->25->7->20->10->18
val a = new ListNode(5)
//val b = new ListNode(7)
/*val c = new ListNode(10)
val d = new ListNode(18)
val e = new ListNode(20)
val f = new ListNode(25)
val g = new ListNode(30)*/
/*a.next = b
b.next = c
c.next = d
d.next =  e
e.next = f
f.next = g*/
println(s"${listNode2list(reorderList(a))}")
