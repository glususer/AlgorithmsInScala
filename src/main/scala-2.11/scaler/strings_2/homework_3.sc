//Given a string A of size N consisting only of lowercase alphabets.
// The only operation allowed is to insert characters in the beginning of the string.
//
//Find and return how many minimum characters are needed to be inserted to make the string a palindrome string.

def isPalindrome(A:String):Boolean={
  if(A.nonEmpty){
    if (A.head == A.last) isPalindrome(A.tail.dropRight(1))
    else false
  }
  else true
}

def loopsWithVar(A:String):Int={
  var count = 0
  var a = A
  while(a.nonEmpty && !isPalindrome(a)){
    a = a.dropRight(1)
    count = count+1
  }
  count
}
def solve1(A: String): Int  = {
  loopsWithVar(A)
}

///////////////////Another method, to https://www.geeksforgeeks.org/minimum-characters-added-front-make-string-palindrome/

def calculateZValue(A:Array[Char]):Array[Long]={
  val z = Array.fill(A.length)(0l)
  var l = 0
  var r = 0

  for(i<- A.indices){
    if(i > r){
      l=i
      r=i
      while(r<A.length && A(r)==A(r-l)){
        r=r+1
      }
      z(i) = r-l
      r = r-1
    }else{
      if(i+z(i) <=r){
        z(i) = z(i-l)
      }else{
        l=i
        while(r<=A.length && A(r) == A(r-l)) {
          r = r+1
        }
        z(i) = r-l
        r = r-1
      }
    }
  }
  z
}

def solve(A: String):Int={
  val zArr = calculateZValue((A+"#"+A.reverse).toCharArray)
  println(s"arr ${(A+"#"+A.reverse).toCharArray.toList} zArr ${zArr.toList}")
  (A.length-zArr.last).toInt
}

solve("aaaa")