import scala.collection.mutable

class ListNode(_x: Int = 0, _next: ListNode = null) {
     var next: ListNode = _next
     var x: Int = _x
   }

object Solution {
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

  def helper(l1:List[Int], l2:List[Int]):List[Int]={
  (l1,l2) match{
    case (Nil,right) =>right
    case (left,Nil) => left
    case (x::xs, y::ys) if (x <= y)=>   x::(helper(xs,y::ys))
    case (x::xs, y::ys) if(y<x) => y::helper(x::xs,ys)
  }
}

//https://leetcode.com/problems/merge-k-sorted-lists/
def mergeKLists(lists: Array[ListNode]): ListNode = {
  val l = lists.map(listNode2list).toList
  val mergedList = l.foldLeft(List[Int]()){case (a,b) => helper(a,b)}
  list2ListNode(mergedList,null)
}

//https://leetcode.com/problems/merge-k-sorted-lists/
def mergeKLists2(lists: Array[ListNode]): ListNode = {
  val pq = mutable.PriorityQueue.empty[ListNode](Ordering.by(node => node.x)).reverse
  val dummyNode = new ListNode(0)
  var head = dummyNode

  lists.foreach(l => if(l != null) pq.enqueue(l))

  while(pq.nonEmpty){
    val current = pq.dequeue()
    head.next = new ListNode(current.x, null)
    if (current.next != null) pq.enqueue(current.next)
    head = head.next
  }
  dummyNode.next
}

listNode2list(mergeKLists2(Array(list2ListNode(List(1,4,5),null),list2ListNode(List(1,3,4),null),
  list2ListNode(List(2,6),null))))
  //listNode2list(list2ListNode(List(),null))

  //https://leetcode.com/problems/rotate-list/

  def len(node: ListNode): Int = {
    def helper(node1: ListNode, count: Int): Int = {
      node1 match {
        case null => count
        case _ => helper(node1.next, count + 1)
      }
    }

    helper(node, 0)
  }

  def rotateRight(head: ListNode, k: Int): ListNode = {
    if (head == null) null
    else {
      k match {
        case 0 => head
        case x if (x % len(head) == 0) => head
        case z => {
          val targetRotated = len(head) - z % len(head)

          def helper(l: ListNode, currentRotated: Int, targetRotated: Int): ListNode = {
            if (currentRotated == targetRotated) {
              val newHead = l.next
              var tail = newHead
              while (tail.next != null) {
                tail = tail.next
              }
              l.next = null
              tail.next = head
              newHead
            } else helper(l.next, currentRotated + 1, targetRotated)
          }

          helper(head, 1, targetRotated)

        }
      }
    }

  }

  val listNode = new ListNode(2, new ListNode(5, new ListNode(7, new ListNode(10, null))))
  val ln1 = new ListNode(2, null)
  len(listNode)
  listNode2list(rotateRight(null, 1))

  def hasCycle(head: ListNode): Boolean = {
    def helper(slow: ListNode, fast: ListNode): Boolean = {
      if (fast == null || fast.next == null) false
      else if (slow.next == fast.next || fast.next == slow) true
      else helper(slow.next, fast.next.next)
    }

    head match {
      case null => false
      case _ => helper(head, head.next)
    }
  }

  //hasCycle(new ListNode(2, new ListNode(5, new ListNode(6, new ListNode(8, null)))))

  def detectCycle(head: ListNode): ListNode = {
    def helperHasCycle(slow:ListNode, fast:ListNode) :ListNode={
      if(fast == null || fast.next == null) null
      else if(fast == slow) fast.next
      else helperHasCycle(slow.next, fast.next.next)
    }

    if(head == null) null
    else{
      var entrance = helperHasCycle(head, head.next)
      var temp = head

      if(entrance != null){
        while(temp != entrance){
          temp = temp.next
          entrance = entrance.next
        }
      } else temp = null
      temp
    }
  }


}
Solution.detectCycle(null)



