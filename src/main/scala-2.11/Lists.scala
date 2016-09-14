import scala.collection.immutable.IndexedSeq


object Lists {
  /**
    * return last element of the list
    *
    * @param l list
    * @return  last element of list and if list is empty throw no such element exception
    *
    */

  def last[T](l: List[T]): T = {
    l match {
      case Nil => throw new NoSuchElementException
      case x :: Nil => x
      case x :: xs => last(xs)
    }
  }

  /**
    * return last but one element of the list
    *
    * @param l list
    * @return  last but one element of list and if list is empty throw no such element exception
    *
    */

  def penultimate[T](l: List[T]): T = {
    l match {
      case x :: y :: Nil => x
      case x :: xs => penultimate(xs)
      case _ => throw new NoSuchElementException
    }
  }

  /**
    * return nth element of the list
    *
    * @param l list
    * @return  nth element of list and if nth element does not exists then throw no such element exception
    *
    */

  def nth[T](l: List[T], n: Int): T = {
    def helper[T](l: List[T], k: Int): T = {
      (l, k) match {
        case (x :: xs, 0) => x
        case (x :: xs, k) => helper(xs, k - 1)
        case (_, _) => throw new NoSuchElementException
      }
    }
    helper(l, n)
  }

  /**
    * return length element of the list
    *
    * @param l list
    * @return  last element of list and if list is empty throw no such element exception
    *
    */

  def length[T](l: List[T]): Int = {
    def helper[T](l: List[T], len: Int): Int = {
      l match {
        case Nil => 0
        case x :: Nil => len
        case x :: xs => (helper(xs, len + 1))
      }
    }
    helper(l, 1)
  }

  def reverse[T](l: List[T]): List[T] = {
    l match {
      case Nil => List()
      case x :: Nil => List(x)
      case x :: xs => reverse(xs) ::: List(x)
    }
  }

  def isPalindrome[T](l: List[T]): Boolean = {
    l match {
      case Nil => true
      case x :: Nil => true
      case x :: xs => {
        if (x == reverse(xs).head)
          isPalindrome(reverse(xs).tail)
        else false
      }
    }
  }

  def flatten[T](l: List[T]): List[T] = {
    l match {
      case (List()) => List()
      case (x: List[T]) :: (xs: List[T]) => flatten(x) ::: flatten(xs)
      case (x: T) :: (xs: List[T]) => x :: flatten(xs)
    }
  }

  def compress[T](l: List[T]): List[T] = {
    l match {
      case List() => List()
      case x :: Nil => List(x)
      case x :: xs => {
        if (x == xs.head)
          compress(xs)
        else x :: compress(xs)
      }
    }
  }

  def pack[T](l: List[T]): List[List[T]] = {
    l match {
      case Nil => Nil
      case x :: xs => {
        val (c, c_Rest) = l.span(z => z == x)
        c :: pack(c_Rest)
      }
    }
  }

  def encode[T](xs: List[T]): List[(Int, T)] = {
    if (xs.isEmpty) List()
    pack(xs) map (x => (x.length, x.head))
  }

  def encodeModified(l: List[Any]): List[Any] = {
    if (l.isEmpty) List()
    pack(l) map (x => {
      if (x.length == 1) x.head else (x.length, x.head)
    })
  }

  def decode[T](ls: List[(Int, T)]): List[T] = {
    ls flatMap { e => List.fill(e._1)(e._2) }
  }

  def encodeDirect[T](l: List[T]): List[(Int, T)] = {

    if (l.isEmpty) Nil
    else {
      val (c, c_Rest) = l span {
        _ == l.head
      }
      (c.length, c.head) :: encodeDirect(c_Rest)
    }
  }

  def duplicateN[T](n: Int, l: List[T]): List[T] = {
    l match {
      case Nil => List()
      case x :: Nil => List.fill(n)(x)
      case x :: xs => List.fill(n)(x) ::: duplicateN(n, xs)
    }
  }

  def duplicate[T](l: List[T]): List[T] = {
    duplicateN(2, l)
  }

  def drop[T](n: Int, l: List[T]): List[T] = {

    def helper(k: Int, l: List[T]): List[T] = {
      (k, l) match {
        case (m, List()) => List()
        case (m, x :: Nil) => {
          if (m == 1) List()
          else List(x)
        }
        case (m, x :: xs) => {
          if (m == 1) helper(n, xs)
          else if (m > l.length) l
          else x :: helper(m - 1, xs)
        }
      }
    }
    helper(n, l)
  }

  def split[T](n: Int, l: List[T]): (List[T], List[T]) = {
    if (n > l.length) (l, Nil)
    else {
      (n, l) match {
        case (n, Nil) => (Nil, Nil)
        case (n, x :: Nil) => (Nil, List(x))
        case (n, x :: xs) => {
          if (n == 0) (List(), x :: xs)
          else {
            val z: (List[T], List[T]) = split(n - 1, xs)
            (x :: z._1, z._2)
          }
        }
      }
    }
  }

  def slice[T](i: Int, j: Int, l: List[T]): List[T] = {
    if (i > j || i > l.length) List()
    else {
      (i, j, l) match {
        case (0, j, x :: Nil) => List(x)
        case (0, 1, _) => l
        case (0, j, x :: xs) => x :: slice(0, j - 1, xs)
        case (i, j, x :: xs) => slice(i - 1, j - 1, xs)
      }
    }
  }

  def rotate[T](n: Int, l: List[T]): List[T] = {
    val z: (List[T], List[T]) = split(n, l)
    z._2 ::: z._1
  }

  // removeAt(3,List(2,4,6,8)) == (List(2,4,6),8)
  def removeAt[T](k: Int, l: List[T]): (List[T], Option[T]) = {
    (k, l) match {
      case (k, Nil) => (List(), None)
      case (0, x :: Nil) => (List(), Some(x))
      case (0, x :: xs) => (xs, Some(x))
      case (k, x :: xs) => {
        if (k < l.length) {
          val z = removeAt(k - 1, xs)
          (x :: z._1, z._2)
        }
        else (l, None)
      }
    }
  }

  def insertAt[T](elem: T, index: Int, l: List[T]): List[T] = {
    (elem, index, l) match {
      case (elem, index, Nil) => Nil
      case (elem, 0, x :: xs) => elem :: x :: xs
      case (elem, index, x :: Nil) => List(x)
      case (elem, index, x :: xs) => x :: insertAt(elem, index - 1, xs)
    }
  }

  def range(i: Int, j: Int): List[Int] = {
    (i, j) match {
      case (i, 0) => List()
      case (i, j) => {
        if (i > j) List()
        else (i to j).toList
      }
    }
  }

  def randomSelect[T](n: Int, ls: List[T]): List[Any] = {
    if (n <= 0) Nil
    else {
      val (rest, e) = removeAt((new util.Random).nextInt(ls.length), ls)
      e match {
        case None => randomSelect(n, rest)
        case Some(x) => x :: randomSelect(n - 1, rest)
      }

    }
  }

  def lotto(n: Int, m: Int): List[Int] = {
    if (n <= 0) Nil
    else {
      val x = (new util.Random).nextInt(m)
      x :: lotto(n - 1, m)
    }
  }

  def combinations[T](n: Int, l: List[T]): List[List[T]] = {
    if (n > l.length) Nil
    else l match {
      case x :: xs => {
        if (n == 1) l.map(x => List(x))
        else {
          combinations(n - 1, xs).map(x :: _) ::: combinations(n, xs)
        }
      }
      case _ => Nil
    }
  }

  def group3[T](n: List[Int], l: List[T]): List[List[List[T]]] = ???

  def lsort[T](l: List[List[T]]): List[List[T]] = l.sortBy(x => x.length)

  def lsortFreq[T](z: List[List[T]]): List[List[T]] = z.map(x => (x, z.count(p => p.length == x.length))).sortBy(x => x._2).map(x => x._1)

  def isPrime(n: Int): Boolean = {
    (2 until n) forall (n % _ != 0)
  }

  def gcd(n: Int, m: Int): Int = {
    if (n > m) 0
    else (n, m) match {
      case (0, m) => m
      case (n, m) => gcd(m % n, n)
    }
  }

  def isCoprime(n: Int, m: Int): Boolean = gcd(n, m) == 1

  def totient(n: Int): Int = {
    {
      for {
        x <- 1 to n
        if (isCoprime(x, n))
      } yield x
    }.length
  }

  def primeFactors(n: Int): List[Int] = {
    def helper(k: Int, m: Int, l: List[Int]): List[Int] = {
      (k, m) match {
        case (k, 0) => l
        case (k, 1) => l
        case (k, m) => {
          if (isPrime(k) && m % k == 0) helper(k, m / k, k :: l)
          else helper(k + 1, m, l)
        }
      }
    }
    helper(2, n, List()).sorted
  }

  def primeFactorMultiplicity(n: Int): Map[Int, Int] = {
    val l = primeFactors(n)
    l.map(x => (x, l.count(z => z == x))).toMap
  }

  def listPrimesinRange(n: Int, m: Int): List[Int] = {
    {
      for {
        i <- n to m
        if (isPrime(i))
      } yield i
    }.toList
  }

  def goldbach(n: Int): (Int, Int) = {
    {
      for {
        i <- 2 until n
        j = n - i
        if (isPrime(i) && isPrime(j))
      } yield (i, j)
    }.toList.map(x => (Math.min(x._1, x._2), Math.max(x._1, x._2))).distinct.head
  }

  def printGoldbachList(n: Int, m: Int): IndexedSeq[(Int, Int)] = {
    for {
      i <- n to m
      if (i % 2 == 0)
    } yield (goldbach(i))
  }

  def and(a: Boolean, b: Boolean): Boolean = {
    if (a && b) true
    else false
  }

  def not(a: Boolean): Boolean = {
    if (a) false
    else true
  }

  def or(a: Boolean, b: Boolean): Boolean = {
    if (a || b) true
    else false
  }

  def xor(a: Boolean, b: Boolean): Boolean = {
    (a, b) match {
      case (false, false) => false
      case (true, true) => false
      case (_, _) => true
    }
  }

  def nand(a: Boolean, b: Boolean): Boolean = and(not(a), not(b))

  def nor(a: Boolean, b: Boolean): Boolean = or(not(a), not(b))

  def gray(n: Int): List[String] = {
    def helper(k: Int, l: List[String]): List[String] = {
      k match {
        case (0) => List()
        case (1) => List("0", "1")
        case (_) => helper(k - 1, l).map(x => "0".concat(x)) ::: reverse(helper(k - 1, l)).map(x => "1".concat(x))
      }
    }
    helper(n, List())
  }

  def firstLast(l: List[Int]): List[Int] = {
    l match {
      case (Nil) => List()
      case (x :: Nil) => l
      case (x :: y :: Nil) => l
      case (x :: xs) => x :: firstLast(xs.reverse)
    }
  }

  def reverseK(l: List[Int], k: Int): List[Int] = {
    (l, k) match {
      case (Nil, k) => List()
      case (x :: Nil, k) => l
      case (l, 1) => l
      case (l, k) => {
        if (l.length < k) l
        else l.splitAt(k)._1.reverse ::: reverseK(l.splitAt(k)._2, k)
      }
    }
  }

  /*def subSeq(str:String,sub:String):Int={
    val arr = Array.ofDim[Int](sub.length,str.length)

  }*/

  def removeDup(l: List[Int]): List[Int] = {
    def helper(l: List[Int], n: Int): List[Int] = {
      (l, n) match {
        case (Nil, n) => l
        case (x :: Nil, n) => {
          if (x == n) List()
          else l
        }
        case (x :: xs, n) => {
          if (x == n) helper(xs.drop(1), x)
          else x :: helper(xs, x)
        }
      }
    }
    helper(l, 0)
  }

  def mergeSortedLists(l: List[Int], m: List[Int]): List[Int] = {
    (l, m) match {
      case (Nil, m) => m
      case (l, Nil) => l
      case (x :: xs, y :: ys) => {
        if (x <= y) x :: mergeSortedLists(xs, m)
        else y :: mergeSortedLists(l, ys)
      }
    }
  }

  def mergeSort(l: List[Int]): List[Int] = {
    if (l.length > 1) {
      val splittedList = l.splitAt(l.length / 2)
      mergeSortedLists(mergeSort(splittedList._1), mergeSort(splittedList._2))
    }
    else l
  }

  def rotateK(l:List[Int],k:Int):List[Int]={
    val m = l.splitAt(k)
    m._2:::m._1
  }
}
