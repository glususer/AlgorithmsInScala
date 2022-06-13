//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

def searchLowerBound(target: Int, l: Int, r:Int, nums:Array[Int]):Int={
  if(l < r){
    val mid = (r-l)/2+l
    if(nums(mid) < target) searchLowerBound(target, mid+1,r,nums)
    else searchLowerBound(target,l,mid,nums)
  }else {
    if(nums(l)==target) l
    else -1
  }
}

def searchUpperBound(target: Int, l: Int, r:Int, nums:Array[Int]):Int={
  if(l < r){
    val mid = (r-l)/2+l
    if(nums(mid) > target) searchUpperBound(target,l,mid,nums)
    else searchUpperBound(target, mid+1,r,nums)
  }else {
    if(nums(l) == target) l
    else if( l > 0 && nums(l-1)== target) l-1
    else -1

  }
}
def searchRange(nums: Array[Int], target: Int): Array[Int] = {
  if(nums.isEmpty)Array(-1,-1)
  else {
    val left = searchLowerBound(target, 0, nums.length - 1, nums)
    val right = searchUpperBound(target, 0, nums.length - 1, nums)
    Array(left, right)
  }
}

//searchRange(Array(8,8,8,8,8,8,10),8)

//https://leetcode.com/problems/search-in-rotated-sorted-array/

def findPvt(nums:Array[Int], l:Int, r:Int):Int={
  if (l<r) {
    val mid = (r-l)/2+l
    if(nums(mid) > nums.last) findPvt(nums, mid+1,r)
    else findPvt(nums,l,mid)

  }else l
}

def binarySearch(nums:Array[Int], target:Int, l:Int,r:Int):Int={
  if(l<r){
    val mid = (r-l)/2+l
    if(nums(mid) < target) binarySearch(nums, target, mid+1, r)
    else binarySearch(nums, target, l,mid)
  }else{
    if(nums(l) == target) l else -1
  }
}
def search(nums: Array[Int], target: Int): Int = {
val pvt = findPvt(nums, 0, nums.length-1)
  if(pvt > 0){
    val inLeftSubArray = binarySearch(nums,target, 0,pvt-1)
     val isRightSubArray = binarySearch(nums, target, pvt, nums.length-1)
    if(inLeftSubArray != -1) inLeftSubArray else isRightSubArray
  } else binarySearch(nums, target, pvt, nums.length-1)
}

findPvt(Array(4,5,6,7,3),0,5)
findPvt(Array(3,4,5,6,0,1,2),0,7)
findPvt(Array(3,4,5,6,7,8),0,6)
findPvt(Array(4,5,0,1,2,3),0,6)
findPvt(Array(4,5,6,7,3),0,5)

//search(Array(4,5,6,7,0,1,2),0)
//search(Array(4,5,6,7,0,1,2),3)
//search(Array(-1),0)

//Day 2
//https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
def findPvtfindMin(nums:Array[Int], l:Int,r:Int):Int={
  if(l<r){
    val mid = (r-l)/2+l
    if(nums(mid)>nums.last) findPvtfindMin(nums, mid+1,r)
    else findPvtfindMin(nums,l,mid)
  }else l
}


def findMin(nums: Array[Int]): Int = {
  if(nums.isEmpty) 0
  else{
    val pvt = findPvtfindMin(nums, 0,nums.length-1)
    nums(pvt)
  }
}

//https://leetcode.com/problems/find-peak-element/

def findPeakElementHelper(nums:Array[Int], l:Int, r:Int):Int={
  print(s"l : $l , r: $r")
  if(l<r){
    val mid1 = (r-l)/2+l
    val mid2 = mid1+1
    println(s" mid $mid1")
    if(nums(mid1) < nums(mid2)) findPeakElementHelper(nums, mid1+1, r)
    else findPeakElementHelper(nums, l,mid1)
  }else l
}
def findPeakElement(nums: Array[Int]): Int = {
  val length = nums.length
   val idx  = findPeakElementHelper(nums,0, length-1)
      idx

}

//findPeakElement(Array(9,10,4,3,5,1,0))

def indexOfNum(arr:Array[Int], left:Int, right:Int, key:Int):Int={
  if(left < right){
    val mid = (right-left)/2+left
    if(arr(mid) < key ) indexOfNum(arr, mid+1, right, key)
    else indexOfNum(arr, left, mid, key)
  }else left
}

def lengthOfLIS(nums: Array[Int]): Int = {
  val longestSeqArray = Array.fill(nums.length)(0)

  val  right = nums.foldLeft(0){case(right,ele) =>
  val indexOfEle = indexOfNum(longestSeqArray, 0,right, ele)
    longestSeqArray(indexOfEle) = ele
  if(indexOfEle == right) right+1
  else right }
  right
}

val LISArray1 = Array(10,9,2,5,3,7,101,18)
val LISArray2 = Array(7,7,7,7,7,7,7)
val LISArray3 = Array(0,1,0,3,2,3)

/*indexOfNum(Array(0, 0, 0, 0, 0, 0, 0, 0), 0, 0,10)
indexOfNum(Array(10, 0, 0, 0, 0, 0, 0, 0), 0, 1,9)
indexOfNum(Array(2, 0, 0, 0, 0, 0, 0, 0), 0, 1,5)
indexOfNum(Array(2, 5, 0, 0, 0, 0, 0, 0), 0, 2,3)
indexOfNum(Array(2, 3, 0, 0, 0, 0, 0, 0), 0, 2,7)
indexOfNum(Array(2, 3, 7, 0, 0, 0, 0, 0), 0, 3,101)
indexOfNum(Array(2, 3, 7, 101, 0, 0, 0, 0), 0, 4,18)*/
/*indexOfNum(Array(1,2,4,0,34,90,10), 0, 3,1)
indexOfNum(Array(1,2,4,0,34,90,10), 0, 3,2)
indexOfNum(Array(1,2,4,0,34,90,10), 0, 3,3)
indexOfNum(Array(1,2,4,0,34,90,10), 0, 3,4)
indexOfNum(Array(1,2,4,0,34,90,10), 0, 3,5)*/


lengthOfLIS(LISArray1)
lengthOfLIS(LISArray2)
lengthOfLIS(LISArray3)

