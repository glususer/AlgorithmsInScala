//Given a linked list of integers. Find and return the length of the longest palindrome list that exists in that linked list.
//
//A palindrome list is a list that reads the same backward and forward.
//
//Expected memory complexity : O(1)

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
def getLengthOfPalidromicString(left:ListNode, right:ListNode):Int={
  var count = 0
  var l1 = left
  var r1 = right
  while(l1!=null && r1 !=null && l1.xc == r1.xc) {
    count = count+1
    l1 = l1.next
    r1 = r1.next
  }
  count
}

def solve(A: ListNode): Int  = {
  if (A == null) 0
  else if (A.next == null) 1
  else {
    var prev: ListNode = null
    var current = A
    var ans = Integer.MIN_VALUE
    while (current.next != null) {
      val nxt = current.next
      current.next = prev

      val currentOddLength = getLengthOfPalidromicString(prev, nxt)
      val currentEvenLength = if (current.xc == nxt.xc) getLengthOfPalidromicString(prev, nxt.next) else 0
      val totalOddLength = (currentOddLength * 2) + 1
      val totlEvenLength = (currentEvenLength * 2) + 2
       println(s"current ${current.xc} even $totlEvenLength odd $totalOddLength")
      ans = ans max totalOddLength max totlEvenLength

      prev = current
      current = nxt
    }
    ans
  }
}
//2 -> 1 -> 2 -> 1 -> 2 -> 2 -> 1 -> 3 -> 2 -> 2
val a = new ListNode(2)
val b = new ListNode(2)
/*val c = new ListNode(2)
val d = new ListNode(1)
val e = new ListNode(2)
val f = new ListNode(2)
val g = new ListNode(1)
val h = new ListNode(3)
val i = new ListNode(2)
val j = new ListNode(2)*/
a.next = b
/*b.next=c
c.next = d
d.next =  e
e.next = f
f.next = g
g.next = h
h.next = i
i.next = j*/
solve(a)