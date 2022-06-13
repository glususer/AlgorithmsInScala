import scala.collection.mutable.ListBuffer

//You are given two linked lists, A and B, representing two non-negative numbers.
//
//The digits are stored in reverse order, and each of their nodes contains a single digit.
//
//Add the two numbers and return it as a linked list.
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
def list2ListNodeWithVars(list:List[Int]):ListNode={
  var i = 1
  val head = new ListNode(list.head)
  var current = head
  while(i<list.length){
    val temp = new ListNode(list(i))
    current.next = temp
    current = current.next
    i=i+1
  }
  head
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

def addTwoNumbers(A: ListNode, B: ListNode): ListNode  = {
  var carry = 0
  var result:ListNode = null
  var head :ListNode= null
  var AA = A
  var BB = B

  while(AA!=null || BB!=null){
    val currentSum = {
      (AA,BB)match{
        case (null,null) => carry
        case(null,b) =>{
          BB = BB.next
          b.xc+carry
        }
        case (a,null)=>{
          AA = AA.next
          a.xc+carry
        }
        case(a,b) => {
          AA = AA.next
          BB = BB.next
          a.xc+b.xc+carry
        }
      }
    }
    carry = if(currentSum >=10) 1 else 0
    //println(s"carry $carry currentSum $currentSum")

    val temp = new ListNode(if(currentSum >=10) currentSum%10 else currentSum)
    if(result == null){
      head = temp
      result = temp
    }
    else {
      result.next = temp
      result = result.next
    }
  }
  if (carry==1) {
    result.next = new ListNode(carry)
  }
  head
}
val a = new ListNode(9)
val b = new ListNode(9)
val c = new ListNode(9)
/*val d = new ListNode(4)
val e = new ListNode(5)
val f = new ListNode(6)*/
a.next = b
b.next=c
/*c.next = d
d.next =  e
e.next = f*/
val aa = new ListNode(9)
val bb = new ListNode(9)
//val cc = new ListNode(4)

  aa.next = bb
//bb.next = cc
listNode2list(addTwoNumbers(aa, a)).mkString("")