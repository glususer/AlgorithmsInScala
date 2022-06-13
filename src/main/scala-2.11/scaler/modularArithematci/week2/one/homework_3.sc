//You are given an integer A which denotes the number of people standing in the queue_1.
//
//A selection process follows a rule where people standing on even positions are selected. Of the selected people, a queue_1 is formed, and again out of these, only people on even position are selected.
//
//This continues until we are left with one person. Find and return the position of that person in the original queue_1.

def binarySearch(key:Int, left:Int, right:Int):Int={
  if(left<right){
    val mid = left+(right-left)/2
    if(math.pow(2,mid).toLong > key) binarySearch(key, left, mid)
    else binarySearch(key, mid+1, right)
  }else left-1
}
def solve(A: Int): Int  = {
val powOfTwo = binarySearch(A, 0, 29)
  math.pow(2, powOfTwo).toInt
}

solve(1000000000)