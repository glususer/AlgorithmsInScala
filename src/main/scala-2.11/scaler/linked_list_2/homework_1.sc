//Given a linked list A, swap every two adjacent nodes and return its head.
//
//NOTE: Your algorithm should use only constant space. You may not modify the values in the list; only nodes themselves can be changed.

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

// A = 1 -> 2 -> 3 -> 4 ,  2 -> 1 -> 4 -> 3
// split list into even and odd positions and then merge them
def swapPairs(A: ListNode): ListNode  = {
val len = calcLength(A)
  if(len == 0 || len == 1) A
  else if(len ==2) {
    val h = A.next
    h.next = A
    A.next = null
    h
  }
  else {
    var count = 3
    var current = A.next.next
    var (oddHead, evenHead) = (A, A.next)
    var (oddHeadTemp, evenHeadTemp) = (oddHead, evenHead)
    while (current != null) {
      println(s"oddHead ${listNode2list(oddHead)} evenHead ${listNode2list(evenHead)} count $count")
      if (count % 2 == 1) {
        oddHeadTemp.next = current
        oddHeadTemp = oddHeadTemp.next
      }
      else {
        evenHeadTemp.next = current
        evenHeadTemp = evenHeadTemp.next
      }
      if (len % 2 == 0 && count == len) oddHeadTemp.next = null
      if (len % 2 == 1 && count == len) evenHeadTemp.next = null
      count = count + 1
      current = current.next
    }
    //println(s"oddHead ${listNode2list(oddHead)} evenHead ${listNode2list(evenHead)}")
    var ans = evenHead
    evenHead = evenHead.next
    val head = ans
    count = 1
    while (count < len - 1) {
      if (count % 2 == 1) {
        ans.next = oddHead
        oddHead = oddHead.next
      }
      else {
        ans.next = evenHead
        evenHead = evenHead.next
      }
      ans = ans.next
      count = count + 1
    }

    if (evenHead != null) ans.next = evenHead
    if (oddHead != null) ans.next = oddHead
    head
  }
}

val a = new ListNode(1)
val b = new ListNode(2)
/*val c = new ListNode(3)
val d = new ListNode(4)
val e = new ListNode(5)
val f = new ListNode(6)*/
a.next = b
/*b.next = c
c.next = d
d.next =  e
e.next = f*/
listNode2list(a)

val x = swapPairs(a)

println(listNode2list(x))

