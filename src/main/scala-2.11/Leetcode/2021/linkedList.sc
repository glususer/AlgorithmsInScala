class ListNode(_x: Int=0, _next:ListNode=null){
  var next = _next
  var x = _x
}
object ListNode{
  def apply(_x: Int, _next: ListNode): ListNode = new ListNode(_x, _next)
}

def List2ListNode(list: List[Int], accHead: ListNode= null, accTail:ListNode = null): ListNode = {
  list match {
    case Nil => accHead
    case x :: xs if accHead == null =>{
      val head = new ListNode(x, null)
      List2ListNode(xs, head, head)
    }
    case x :: xs => val node = new ListNode(x, null)
      accTail.next = node
      List2ListNode(xs, accHead, accTail)
  }
}

def ListNode2List(current:ListNode, acc:List[Int] = List.empty):List[Int]={
  if(current==null) acc
  else{
    ListNode2List(current.next, acc:+(current.x))
  }
}

//https://leetcode.com/problems/remove-duplicates-from-sorted-list/
def helper(head:ListNode, current:ListNode, prev:ListNode):ListNode={
  if(current==null) head
  else {
    if (prev.x == current.x) {
      // if it is the last node then irrespective of whether prev.x == current.x or not, we need to link prev to current
      // if this condition is not there then case like 1->1->1->1 fails
      if(current.next == null){
        prev.next=null
        helper(head, current.next, prev)
      }
      else helper(head, current.next, prev)
    }
    else {
      prev.next = current
      helper(head, current, current)
    }
  }
}
def deleteDuplicates(head: ListNode): ListNode = {
  helper(head, head, head)
}

def swapNodes(head: ListNode, k: Int): ListNode = {
  ???
}

def cycleDetectionHelper(fast:ListNode, slow:ListNode):ListNode={
  if(fast == slow) fast
  else if(fast.next == null || fast.next.next==null) null
  else cycleDetectionHelper(fast.next.next, slow.next)
}

def traverseList(current: ListNode, stopNode:ListNode):ListNode={
  if(current == stopNode) current
  else traverseList(current.next, stopNode.next)
}

//https://leetcode.com/problems/linked-list-cycle-ii/
def detectCycle(head: ListNode): ListNode = {
  if(head == null) null
  else{
    val fast =  cycleDetectionHelper(head,head.next)
    if(fast == null) null
    else
      traverseList(head, fast)
  }
}


val duplicateList = ListNode(1,ListNode(1,ListNode(1,ListNode(1,ListNode(1,ListNode(1,ListNode(2,ListNode(2,null))))))))
val uniqueList = ListNode(1,ListNode(2,ListNode(3,null)))

println(ListNode2List(deleteDuplicates(duplicateList), List.empty))
println(ListNode2List(deleteDuplicates(uniqueList), List.empty))
val x = List(-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5)
x.length

//https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

def deleteDuplicatesHelper(head: ListNode, first: ListNode, second: ListNode, isPrevSame: Boolean):ListNode={
  if(second == null) head
  else if (second.next == null){
    if(isPrevSame) {
      first.next = second.next
      head
    }else head
  }
  else if(second.x != second.next.x) {
    if(isPrevSame) {
      first.next = second.next
      deleteDuplicatesHelper(head, first, second,false)
    }else deleteDuplicatesHelper(head, first.next, second.next, isPrevSame)
  }
  else if (second.x == second.next.x){
    deleteDuplicatesHelper(head, first, second.next, true)
  }else{
    deleteDuplicatesHelper(head, first, second.next, false)
  }
}

def attachDummiesAtExtremes(head:ListNode, curr:ListNode):ListNode={
  if(curr.next == null){
    curr.next = ListNode(0,null)
    head
  }
  else if(curr == head){
    val dummyNodeAtStart = ListNode(0,curr)
    dummyNodeAtStart.next = curr
    attachDummiesAtExtremes(dummyNodeAtStart, curr)
  }
  else attachDummiesAtExtremes(head, curr.next)
}
def deleteDuplicates2(head: ListNode): ListNode = {
  val dummiesAtExtremes = attachDummiesAtExtremes(head,head)
  println(s"here ${ListNode2List(dummiesAtExtremes).mkString(", ")}")
  deleteDuplicatesHelper(dummiesAtExtremes,dummiesAtExtremes, dummiesAtExtremes.next, false)
}

//val deduplicatedListNode= deleteDuplicates2(List2ListNode(List(1,1,2,2,3,3,3,4)))
val xx = ListNode(1, ListNode(1,ListNode(2,ListNode(2,ListNode(3,ListNode(3,ListNode(3,ListNode(4,null))))))))
//val deduplicatedListNode= deleteDuplicates2(xx)
//println(ListNode2List(deduplicatedListNode))

//https://leetcode.com/problems/reverse-linked-list/

  def reverseListHelper(acc:ListNode, curr:ListNode):ListNode={
    curr match{
      case null => acc
      case _ => val next = curr.next
        curr.next = acc
        reverseListHelper( curr, next)
    }
  }
  def reverseList(head: ListNode): ListNode = {
    reverseListHelper(null, head)
  }

val list = ListNode(1, ListNode(2,ListNode(3, null)))
val list2 = null
println(s" ${ListNode2List(reverseList(list2))}")

