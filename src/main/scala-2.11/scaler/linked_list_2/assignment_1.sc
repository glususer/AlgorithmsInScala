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
def solve(A:ListNode):ListNode={
  if(A== null || A.next == null) A
  else {
    var slow = A
    var fast = A
    var loop = false

    while (fast != null && fast.next != null && !loop) {
      slow = slow.next
      fast = fast.next.next
      if(fast == slow){
        loop = true
      }
    }
    slow = A

    while (slow != fast) {
        slow = slow.next
        fast = fast.next
    }

    while(slow != fast.next){
      fast = fast.next;
    }
    fast.next = null;
    A
  }
}



val a = new ListNode(1)
val b = new ListNode(2)
val c = new ListNode(3)
val d = new ListNode(4)
val e = new ListNode(5)
val f = new ListNode(6)
val g = new ListNode(7)
a.next = b
b.next = c
c.next = d
d.next =  e
e.next = f
f.next = g
g.next = c

println(s"g next ${g.next.value}")
solve(a)
println(s"g next ${g.next}")
