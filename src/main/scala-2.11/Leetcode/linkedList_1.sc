
class ListNode(_x: Int = 0, _next: ListNode = null) {
  var next: ListNode = _next
  var x: Int = _x
}

object ListNode{
  def apply(_x: Int, _next: ListNode): ListNode = new ListNode(_x, _next)
}

  def listNode2list(list: ListNode): List[Int] = {
    var head: ListNode = list
    var acc = List[Int]()

    while (head != null) {
      acc = acc :+ head.x
      head = head.next
    }
    acc

  }

  def list2ListNode(list: List[Int], acc: ListNode): ListNode = {
    list match {
      case Nil => acc
      case x :: xs if (acc == null) => list2ListNode(xs, new ListNode(x, null))
      case x :: xs => var temp = acc
        while (temp.next != null) {
          temp = temp.next
        }
        temp.next = new ListNode(x, null)
        list2ListNode(xs, acc)
    }
  }


listNode2list(ListNode(1,ListNode(2,null)))


//https://leetcode.com/problems/intersection-of-two-linked-lists/
def calcLength(head:ListNode,len:Int):Int={
  head match{
    case null => len
    case _ => calcLength(head.next, len+1)
  }
}
def getIntersectionNode(headA: ListNode, headB: ListNode): ListNode = {
  if(headA == null || headB == null ) null
  else{
    val l1 = calcLength(headA,0)
    val l2 = calcLength(headB,0)
    var (startA,startB) = if(l1 < l2){
      var temp = headB
      val diff = l2-l1
      for(i <-0 until diff){
        temp = temp.next
      }
      (headA, temp)
    } else if(l1 > l2) {
      var temp = headA
      val diff = l1 - l2
      for (_ <- 0 until diff) {
        temp = temp.next
      }
      (temp, headB)
    }
    else(headA, headB)
    while(startA != null && startB != null && startA !=startB){
      startA = startA.next
      startB = startB.next
    }
    if(startA == null) null else startA
  }
}
val l11 = ListNode(3,null)
val l1 = l11
val l2 = l11

println(s"l1 is ${listNode2list(l1)} and l2 is ${listNode2list(l2)}")
//getIntersectionNode(l1,l2).x

//https://leetcode.com/problems/palindrome-linked-list/
def helper(node:ListNode, acc:ListNode):ListNode={
  node match{
    case null => acc
    case _ => val current  = node.next
      node.next = acc
      helper(current, node)
  }
}
def reverseLinkedList(node:ListNode):ListNode ={
  helper(node,null)
}
//1->2->2->1
def palindromicHelper1(head:ListNode, end:ListNode):Boolean={
 if(head == end || (head.next == end && head.x == end.x)) true
 else if(head.x == end.x){
  var pointerToEnd = head
  while(pointerToEnd.next!= end) pointerToEnd = pointerToEnd.next
  palindromicHelper1(head.next,pointerToEnd)
}
  else false
}

def palindromicHelper(head:ListNode, tail:ListNode):ListNode={
  if(tail == null) head
  else {
    val temp = palindromicHelper(head, tail.next)
    if (temp == null || temp.x != tail.x) null
    else if (temp.next != null)temp.next
    else temp
  }
}

def isPalindrome(head: ListNode): Boolean = {
//  head == null || head.next == null || palindromicHelper(head, head) != null
  var end = head
  while(end.next != null) end = end.next
head == null || head.next == null ||  palindromicHelper1(head,end )
}

isPalindrome(ListNode(1,ListNode(2,ListNode(2,ListNode(1,null)))))

//https://leetcode.com/problems/next-greater-node-in-linked-list/

def nextLargerNodes(head: ListNode): Array[Int] = {
val lst = listNode2list(head)
 lst.zipWithIndex.map{num => lst.splitAt(num._2+1)._2.span(_ < num._1)._2.headOption.getOrElse(0)}.toArray
}
//7,9,9,9,0,5,0,0]
//nextLargerNodes(Array(1,7,5,1,9,2,5,1)).toList
//List(5,1,9,2,5,1).span(_ < 7)

def helper(head:ListNode, acc:ListNode, toBeSkipped:Int, acchead:ListNode):ListNode ={
  head match{
    case null => acchead
    case n =>
      if(n.x == toBeSkipped) helper(n.next,acc,toBeSkipped,acchead)
              else{
                  val next = n.next
                  if(acc == null) {
                    n.next = null
                    helper(next, n,toBeSkipped,n)
                  }
                  else {
                    acc.next = n
                    n.next = null
                    println(s"acc is ${listNode2list(acc)} and currentNode is ${n.x} and acc is ${acc.x}")
                    helper(next, acc.next,toBeSkipped,acchead)
                  }
                  }
            }
}

def removeElements(head: ListNode, `val`: Int): ListNode = {
  helper(head,null,`val`,null)
}

val x = removeElements(ListNode(1,ListNode(2,ListNode(3,ListNode(4,ListNode(9,null))))),2)

listNode2list(x)

def reverseBetween(head: ListNode, m: Int, n: Int): ListNode = {
    if(m > 1){
      var temp = head
      while(m > 0){
        temp = temp.next
        m=m-1
      }
    }else {
      reverseLinkedList(head)
    }
}
