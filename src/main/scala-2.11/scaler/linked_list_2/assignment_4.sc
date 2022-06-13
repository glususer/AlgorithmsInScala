//Merge two sorted linked lists, A and B, and return it as a new list.
//
//The new list should be made by splicing together the nodes of the first two lists and should also be sorted.

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

def mergeTwoLists(A: ListNode, B: ListNode): ListNode  = {
  var h1 = A
  var h2 = B
  var ans :ListNode =null
  var head:ListNode = null

  if(h1.xc <= h2.xc){
    head = h1
    h1 = h1.next
  }
  else{
    head  = h2
    h2 = h2.next
  }
ans = head

  while (h1 != null && h2 != null) {
    if(h1.xc <= h2.xc) {
      ans.next = h1
      h1 = h1.next
    }
    else{
      ans.next = h2
      h2= h2.next
    }
    ans = ans.next
  }

  if (h1 != null) ans.next = h1
  if (h2 != null) ans.next = h2
  head
}

val a = new ListNode(5)
val b = new ListNode(17)
val c = new ListNode(18)
a.next = b
b.next = c
val aa = new ListNode(18)
val bb = new ListNode(27)
val cc = new ListNode(110)
aa.next = bb
bb.next = cc
println(s" ${listNode2list(mergeTwoLists(a, aa))}")


