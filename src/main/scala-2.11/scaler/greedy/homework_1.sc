//The monetary system in DarkLand is really simple and systematic. The locals-only use coins. The coins come in different values. The values used are:
//
// 1, 5, 25, 125, 625, 3125, 15625, ...
//Formally, for each K >= 0 there are coins worth 5K.
//
//Given an integer A denoting the cost of an item, find and return the smallest number of coins necessary to pay exactly the cost of the item (assuming you have a sufficient supply of coins of each of the types you will need).
def binarySearch(left:Int, right:Int, key:Int):Int={
  if(left<right){
    val mid = (right-left)/2+left
    if(key < math.pow(5,mid)) binarySearch(left,mid,key)
    else binarySearch(mid+1,right,key)
  }else math.pow(5,left-1).toInt
}
def solve(A: Int): Int  = {
  var result = 0
  var currentMoney = A
  while(currentMoney >0){
    val nearestFivePower = binarySearch(0,14,currentMoney)
    result = result+1
    currentMoney = currentMoney-nearestFivePower
  }
  result
}
solve(47)
solve(9)

val limit = 2*1000000000
val fivePower = math.pow(5,14)
limit > fivePower

/*binarySearch(0,14,5)
binarySearch(0,14,6)
binarySearch(0,14,25)
binarySearch(0,14,24)
binarySearch(0,14,125)
binarySearch(0,14,124)
binarySearch(0,14,126)*/

