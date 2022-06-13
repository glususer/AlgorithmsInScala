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

def removeNthFromEnd(A: ListNode, B: Int): ListNode  = {
  val length = calcLength(A)

  if (B == 0) A
  else if (B == 1 && length == 1) null
  else {
    var count = 1
    var temp = A
    if (length <= B) A.next
    else {
      while (count < length - B) {
        temp = temp.next
        count = count + 1
      }
      // println(s"temp ${temp.xc}")
      temp.next = temp.next.next
      A
    }
  }
}

val a = new ListNode(5)
val b = new ListNode(7)
val c = new ListNode(10)
val d = new ListNode(18)
val e = new ListNode(20)//
val f = new ListNode(25)
a.next = b
b.next = c
c.next = d
d.next =  e
e.next = f

println(s"initial List ${listNode2list(a)}")
//println(s"${listNode2list(removeNthFromEnd(a, 1))}")
//println(s"${listNode2list(removeNthFromEnd(a, 2))}")
//println(s"${listNode2list(removeNthFromEnd(a, 3))}")
//println(s"${listNode2list(removeNthFromEnd(a, 4))}")
println(s"${listNode2list(removeNthFromEnd(a, 6))}")
//println(s"${listNode2list(removeNthFromEnd(a, 7))}")
