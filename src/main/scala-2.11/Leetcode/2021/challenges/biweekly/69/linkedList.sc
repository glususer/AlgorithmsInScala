class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}

def linkedListToList(head: ListNode, acc: List[Int] = List.empty): List[Int] = {
  if (head == null) acc
  else linkedListToList(head.next, acc :+ head.x)
}

def reverseList(head: ListNode, acc: ListNode = null): ListNode = {
  if (head == null) acc
  else {
    val temp = head.next
    if (acc != null) head.next = acc else head.next = null
    reverseList(temp, if (acc == null) head else head)
  }
}

def listToLinkedList(l: List[Int], acc: ListNode = null): ListNode = {
  l match {
    case Nil => acc
    case x :: xs => val node = new ListNode(x)
      if (acc != null) acc.next = node
      listToLinkedList(xs, if (acc == null) node else acc)
  }
}

def lengthofList(l: ListNode, len: Int = 0): Int = {
  if (l == null) len
  else lengthofList(l.next, len + 1)
}

def traverseToMiddle(l: ListNode, mid: Int, current: Int): ListNode = {
  if (mid == current) l
  else traverseToMiddle(l.next, mid, current + 1)
}

def pairSumHelper(l: ListNode, m: ListNode, acc: Int = 0): Int = {
  if (l == null) acc
  else pairSumHelper(l.next, m.next, acc max (l.x + m.x))
}


/*val reversedList = reverseList(ln)
linkedListToList(reversedList)
lengthofList(reversedList)*/

def pairSum(head: ListNode): Int = {
  val middlePoint = lengthofList(head) / 2
  val straightLast = traverseToMiddle(head, middlePoint, 1)
  val toBbeReversed = reverseList(straightLast.next)
  straightLast.next = null
  pairSumHelper(head, toBbeReversed)
}

val ln = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8, new ListNode(9, new ListNode(10, null))))))))))
val ln2 = new ListNode(4, new ListNode(2, new ListNode(2, new ListNode(3, null))))
val ln3 = new ListNode(1, new ListNode(2, null))

pairSum(ln3)