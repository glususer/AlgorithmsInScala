import scala.collection.mutable.Stack

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

def calcLength(head:ListNode,len:Int):Int={
  head match{
    case null => len
    case _ => calcLength(head.next, len+1)
  }
}
//https://leetcode.com/problems/next-greater-node-in-linked-list/

def helperNextLargerNodes2(l:ListNode, s:Stack[(ListNode,Int)],arr:Array[Int], currentIdx:Int):Array[Int]={
  if(l == null) arr
  else{
    val currentValue = l.x
    if(currentValue <= s.top._1.x){
      s.push((l,currentIdx))
      helperNextLargerNodes2(l.next,s,arr, currentIdx+1)
    }else{
      var startIdx = 0
      while(s.nonEmpty && s.top._1.x < currentValue){
        val popped = s.pop()
        startIdx = popped._2
      }
      s.push((l,currentIdx))
      for( i<- startIdx until currentIdx){
        if(arr(i) == 0) arr(i) = l.x
      }
      helperNextLargerNodes2(l.next, s,arr, currentIdx+1)
    }
  }
}

def nextLargerNodes(head: ListNode): Array[Int] = {
  val arr = Array.fill(calcLength(head,0))(0)
  val s = Stack((head,0))
  helperNextLargerNodes2(head.next,s,arr,1)
  arr
}
val l = ListNode(1,ListNode(7,ListNode(5,ListNode(1,ListNode(9,ListNode(2,ListNode(5,ListNode(1,null))))))))
val l1 = ListNode(3,ListNode(3,null))
nextLargerNodes(l1)