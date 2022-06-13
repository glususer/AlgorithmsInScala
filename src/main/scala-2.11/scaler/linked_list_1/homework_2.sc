//Given a sorted linked list, delete all duplicates such that each element appears only once.
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

def deleteDuplicates(A: ListNode): ListNode  = {
  if(A == null || A.next == null)A
  else {
    var prev = A
    var current = A.next
    while (current != null) {
      if (prev.xc == current.xc) current = current.next
      else {
        prev.next = current
        prev = prev.next
        current = current.next
      }
    }
    prev.next = current
    A
  }
}
val a = new ListNode(5)
val b = new ListNode(7)
val c = new ListNode(10)
val d = new ListNode(20)
val e = new ListNode(30)
val f = new ListNode(40)
a.next = b
b.next = c
c.next = d
d.next =  e
e.next = f

c
