  //Sort a linked list, A in O(n log n) time using constant space complexity.
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

  def merge(A:ListNode, B:ListNode):ListNode={
    var head : ListNode= null
    var ans : ListNode= null
    var l1 = A
    var l2 = B
    if(A == null) B
    else if (B==null)A
    else if(A==null && B==null) null
    else{
      if(A.xc <= B.xc){
        head = l1
        ans = l1
        l1 = l1.next
      }
      else {
        head = l2
        ans = l2
        l2 = l2.next
      }

      while(l1!= null && l2 !=null){
        if(l1.xc <= l2.xc){
          ans.next = l1
          l1 = l1.next
        }
        else {
          ans.next = l2
          l2 = l2.next
        }
        ans = ans.next
      }
      if(l1 != null) ans.next = l1
      if(l2 !=null) ans.next = l2
      head
    }
  }

  def getMiddleNode(A:ListNode):ListNode ={
    if(A.next == null) A
    else {
      var slow = A
      var fast = A.next
      while (fast != null && fast.next != null) {
        slow = slow.next
        fast = fast.next.next
      }
      slow
    }
  }
  def sortList(A: ListNode): ListNode  = {
    if(A == null || A.next == null) A
    else{
      val middle = getMiddleNode(A)
      val second = middle.next
      val first = A
      middle.next = null
    //  println(s"first ${listNode2list(first)} second ${listNode2list(second)}")
      merge(sortList(first), sortList(second))
    }
  }
  val a = new ListNode(19)
  /*val b = new ListNode(10)
  val c = new ListNode(5)
  val d = new ListNode(20)
  val e = new ListNode(2)
  val f = new ListNode(30)
  a.next = b
  b.next=c
  c.next = d
  d.next =  e
  e.next = f*/
  val aa = new ListNode(100)
  val bb = new ListNode(12)
  val cc = new ListNode(27)
  aa.next = bb
  bb.next = cc
//println(s"${getMiddleNode(a).xc}")
  println(listNode2list(sortList(a)))

 // println(listNode2list(merge(a, aa)))

