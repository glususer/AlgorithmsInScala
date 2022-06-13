//Given a singly linked list A, determine if it's a palindrome. Return 1 or 0, denoting if it's a palindrome or not, respectively.
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
def getMiddleNode(A:ListNode):ListNode={
  if(A==null || A.next == null)A
  else{
    var slow = A
    var fast = A.next
    while(fast!=null && fast.next !=null){
      slow = slow.next
      fast = fast.next.next
    }
    slow
  }
}
def reverse(A:ListNode):ListNode={
  if(A == null || A.next == null)A
  else {
    var current = A
    var prev :ListNode = null
    while(current.next !=null){
      val nxt = current.next
      current.next = prev
      prev = current
      current = nxt
    }
    current.next = prev
    current
  }
}
def lPalin(A: ListNode): Int  = {
  val middleNode = getMiddleNode(A)
  var first = A
  var second = reverse(middleNode.next)
  middleNode.next = null
  //println(s"first ${listNode2list(first)} second ${listNode2list(second)}")
  while(first!= null && second != null && first.xc == second.xc){
    first = first.next
    second = second.next
  }
  if((first == null && second == null) || (first.next == null && second == null)) 1 else 0
}

val a = new ListNode(19)
val b = new ListNode(20)
val c = new ListNode(30)
val d = new ListNode(20)
val e = new ListNode(30)
a.next = b
b.next=c
c.next = d
d.next =  e

lPalin(a)