//https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/

def getDescentPeriodsHelper(start:Int, current:Int, acc:Long, prices:Array[Int]):Long={
  if(start <= current && current < prices.length){
    if(prices(current) +1 == prices(current-1)) getDescentPeriodsHelper(start, current+1, acc+current-start+1, prices)
    else getDescentPeriodsHelper(current, current+1, acc+1, prices)
  } else acc
}

def getDescentPeriods(prices: Array[Int]): Long = {
  if(prices.length==1)prices.head
  else getDescentPeriodsHelper(0,1,1,prices)
}

getDescentPeriods(Array(1,0))