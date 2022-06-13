//Given a singly linked list A and an integer B, reverse the nodes of the list B at a time and return the modified linked list.
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

def calcLength(head:ListNode):Int={
  var len = 0
  var temp = head
  while(temp != null){
    temp = temp.next
    len = len+1
  }
  len
}

def reverse(head:ListNode,prevNode: ListNode,startNode:ListNode, endNode:ListNode):ListNode={

  var curr = startNode
  var prev : ListNode = null
  while(curr.next!=endNode){
    val next = curr.next
    curr.next = prev
    prev = curr
    curr = next
  }
  curr.next = prev
  if(prevNode != null) prevNode.next = curr
  head
}
def reverseList(A: ListNode, B: Int): ListNode  = {
  if(B==1) A
  else {
    var count = 1
    var head = A
    var tail = A
    var flag = true
    var prevHead: ListNode = null
    var newHead: ListNode = null
    var nextChunk = A
    while (nextChunk != null) {
      while (count < B) {
        count = count + 1
        tail = tail.next
      }
      nextChunk = tail.next
      if (flag) newHead = tail
      reverse(newHead, prevHead, head, nextChunk)
      head.next = nextChunk
      flag = false

      prevHead = head
      tail = nextChunk
      head = nextChunk
      count = 1
    }
    newHead
  }
}

val a = new ListNode(1)
val b = new ListNode(2)
val c = new ListNode(3)
val d = new ListNode(4)
val e = new ListNode(5)
val f = new ListNode(6)
val g = new ListNode(7)
val h = new ListNode(8)

a.next = b
b.next = c
c.next = d
d.next =  e
e.next = f
f.next = g
g.next = h

println(s"initial ${listNode2list(a)}")
println(s" reversed ${listNode2list(reverseList(a,8))}")