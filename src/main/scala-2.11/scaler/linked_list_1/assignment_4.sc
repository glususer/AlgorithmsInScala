//You are given a singly linked list having head node A. You have to reverse the linked list and return the head node of that reversed list.
//
//NOTE: You have to do it in-place and in one-pass.
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


def helper(curr:ListNode, prev:ListNode):ListNode={
  if(curr.next == null) {
    curr.next = prev
    curr
  } else{
    val next = curr.next
    curr.next = prev
    helper(next, curr)
  }
}

def helperWithVars(A:ListNode):ListNode={
  var curr = A
  var prev : ListNode = null
  while(curr.next!=null){
    val next = curr.next
    curr.next = prev
    prev = curr
    curr = next
  }
  curr.next = prev
  curr
}


def reverseList(A: ListNode): ListNode  = {
  if(A == null || A.next == null) A
  else helperWithVars(A)
}



val a: ListNode = new ListNode(5)
a.next = new ListNode(7)
a.next.next = new ListNode(9)
a.next.next.next = new ListNode(11)
reverseList(a)
