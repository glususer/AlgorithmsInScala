//Given an array of integers A of size N and an integer B.
//
//The College library has N books. The ith book has A[i] number of pages.
//
//You have to allocate books to B number of students so that the maximum number of pages allocated to a student is minimum.
//
//A book will be allocated to exactly one student.
//Each student has to be allocated at least one book.
//Allotment should be in contiguous order, for example:
// A student cannot be allocated book 1 and book 3, skipping book 2.
//Calculate and return that minimum possible number.
//
//NOTE: Return -1 if a valid assignment is not possible.


def canStudentsReadBooks(mid: Int, students: Int, books: Array[Int]):Boolean = {
  val (actualNumOfStudents,_) = books.foldLeft(0,0){case((currentStudents, prevPages),ele) =>
    val currentPages = prevPages+ele
  if(currentPages > mid)(currentStudents+1, ele)
  else (currentStudents, currentPages)
  }
 // println(s"actualNumOfStudents $actualNumOfStudents pagesToRead $mid")
  if(actualNumOfStudents!=0) actualNumOfStudents+1 <= students else actualNumOfStudents <= students
}

def binarySearchOnNumOfPages(left:Int, right:Int, students:Int, books:Array[Int]):Int={
  if(left<right){
    val mid = ((right-left)/2)+left
  //  println(s"left $left mid $mid right $right")
    if(canStudentsReadBooks(mid, students, books))binarySearchOnNumOfPages(left,mid, students, books)
    else binarySearchOnNumOfPages(mid+1, right, students, books)

  } else left
}

def books(A: Array[Int], B: Int): Int  = {
  if(A.length == B) A.max
  else if(A.length < B) -1
  else binarySearchOnNumOfPages(A.max, A.sum,B,A)
}

books(Array(98, 42, 99, 68, 12, 60, 57, 94, 8, 95, 68, 13, 30, 6, 62, 42),16)