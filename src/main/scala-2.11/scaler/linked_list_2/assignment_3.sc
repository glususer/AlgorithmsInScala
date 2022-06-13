//Given a linked list of integers, find and return the middle element of the linked list.
//
//NOTE: If there are N nodes in the linked list and N is even then return the (N/2 + 1)th element.
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

def solve(A: ListNode): Int  = {
  if(A.next == null) A.xc
  else {
    val length = calcLength(A)
    var slow = A
    var fast = A
    while (fast != null && fast.next != null) {
      slow = slow.next
      fast = fast.next.next
    }
    if (length / 2 == 0) slow.next.xc else slow.xc
  }
}

val a = new ListNode(5)
val b = new ListNode(7)
/*val c = new ListNode(10)
val d = new ListNode(18)
val e = new ListNode(20)
val f = new ListNode(25)*/
a.next = b
/*b.next = c
c.next = d
d.next =  e
e.next = f*/
println(s"${solve(a)}")