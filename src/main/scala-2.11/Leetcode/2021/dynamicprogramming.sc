
/**Day 1**/
//https://leetcode.com/problems/fibonacci-number/

def fibHelper(prev: Int, prevPrev:Int, current:Int):Int={
  if (current == 1)  prev+prevPrev
  else{
    fibHelper(prev+prevPrev, prev,current-1)
  }
}
def fib(n: Int): Int = {
  if(n==1 || n==2) 1
  else fibHelper(1,1,n-2)
}

//(1 to 10).foreach(n => println(n,fib(n)))

def tribHelper(prev: Int, prevPrev:Int, prevprevPrev:Int,current:Int):Int={
  if (current == 0)  prev+prevPrev+prevprevPrev
  else{
    tribHelper(prev+prevPrev+prevprevPrev, prev,prevPrev,current-1)
  }
}

def tribonacci(n: Int): Int = {
if(n ==0) 0
else if(n==1 || n==2) 1
else tribHelper(1,1,0,n-3)
}

//(1 to 25).foreach(n => println(n,tribonacci(n)))

/**Day 2**/
  def climbHelper(numOfWaysPrevStep: Int, numOfWaysPrevPrevStep: Int, currentCount:Int):Int={
    if(currentCount == 1) numOfWaysPrevStep+numOfWaysPrevPrevStep
    else climbHelper(numOfWaysPrevStep+numOfWaysPrevPrevStep,numOfWaysPrevStep, currentCount-1 )
  }

def climbStairs(n: Int): Int = {
  if(n==0 || n==1) 1
  else climbHelper(1,1,n-1)
}
//(1 to 25).foreach(n => println(n,climbStairs(n)))


def minCostClimbingStairs(cost: Array[Int]): Int ={
  val updatedCostArray = cost:+0

  if(updatedCostArray.length == 1) updatedCostArray(0)
  else if(updatedCostArray.length == 2) math.min(updatedCostArray(0), updatedCostArray(1))
  else{
    val (minCostOfLastToLatStep, minCostOfLastStep)= updatedCostArray.zipWithIndex.foldLeft(updatedCostArray(0), updatedCostArray(1)) {
      case ((minCostOfPrevToPrevStep, minCostOfPreviousStep), (currentCost, idx)) =>
        if (idx > 1) {
          val minCostToCrossThisStep = math.min(minCostOfPreviousStep, minCostOfPrevToPrevStep) + currentCost
          (minCostOfPreviousStep, minCostToCrossThisStep)
        }
        else (minCostOfPrevToPrevStep, minCostOfPreviousStep)
    }
    math.min(minCostOfLastStep,minCostOfLastToLatStep)
}
}

//minCostClimbingStairs(Array(1,100,1,1,1,100,1,1,100,1))
//minCostClimbingStairs(Array(10,15,20))

// Day 3

//https://leetcode.com/problems/house-robber/
def rob(nums: Array[Int]): Int = {
  val length =  nums.length
  length match{
     case 0 => 0
     case 1 => nums(0)
     case 2 => math.min(nums(0), nums(1))
     case _ =>
       val costArray = nums.tail.tail.scanLeft(nums(0),nums(1)){case((maxMoneyEarnedBeforeThisHouse, moneyFromPrevHouse),currentMoney) =>
       (math.max(moneyFromPrevHouse,maxMoneyEarnedBeforeThisHouse ), maxMoneyEarnedBeforeThisHouse+currentMoney)

     }
       costArray.foreach(ele => println(ele))
       math.max(costArray.last._2 ,costArray.last._1)
   }

}

//rob(Array(2,1,1,2))

//Day 4
// https://leetcode.com/problems/jump-game/
//[2,3,1,1,4]
def canJump(nums: Array[Int]): Boolean = {
  nums.length match{
    case 0 | 1 => true
    case _ =>   val (noOfStepsToLastIndex, isSomeIndexBlocked) =  nums.tail.foldLeft(nums.head,nums.head <=0){case  ((acc, isBlocked), ele) =>
      if(!isBlocked) {
        val maxSteps = math.max(acc-1,ele)
         println(s"acc $maxSteps  and isBlcoked : ${maxSteps <=0} , current ele $ele")
        (maxSteps, maxSteps <=0)
      } else(acc, isBlocked)}
      println(s"noOfStepsToLastIndex $noOfStepsToLastIndex")
      !isSomeIndexBlocked
  }

}
/*canJump(Array(1,2))
canJump(Array(0,1,2,3))
canJump(Array(2,3,1,1,4))
canJump(Array(3,2,1,0,4))
canJump(Array(2,0))*/
canJump(Array(2,0,0))

//https://leetcode.com/problems/coin-change/

def initialize(numOfCoinsForDenomination: Array[Int], coins: Array[Int]): Unit = {
  for(coin <- coins){
    numOfCoinsForDenomination.zipWithIndex.tail.foldLeft(Int.MaxValue){case (noOfCoins, (currentCoins,denomination)) =>
      numOfCoinsForDenomination(denomination) = if(denomination%coin ==0) math.min(denomination/coin, if (currentCoins == 0)Int.MaxValue else currentCoins ) else currentCoins
      noOfCoins
    }
  }
}

//&& numOfCoinsForDenomination(coin) !=0 && numOfCoinsForDenomination(denomination - coin) !=0
def proccessCurrentDenomination(numOfCoinsForDenomination: Array[Int], coin: Int):Unit={
  numOfCoinsForDenomination.zipWithIndex.tail.foldLeft(0){case(i,(currentCoins,denomination)) =>
    if(denomination-coin >0 && numOfCoinsForDenomination(coin) !=0 && numOfCoinsForDenomination(denomination - coin) !=0) {
      /*if(coin == 50 && denomination == 83) {
        println(s"${numOfCoinsForDenomination(denomination)}, ${numOfCoinsForDenomination(denomination - coin)}, ${numOfCoinsForDenomination(coin)}")
      }*/
      numOfCoinsForDenomination(denomination) = math.min(if (currentCoins > 0) currentCoins else Int.MaxValue,
        numOfCoinsForDenomination(denomination - coin) + numOfCoinsForDenomination(coin))
    }
    i
  }
}

def coinChange(coins: Array[Int], amount: Int): Int = {

  if(amount == 0) 0
  else {
    val numOfCoinsForDenomination = Array.fill(amount+1)(0)

    scala.util.Sorting.quickSort(coins)
    initialize(numOfCoinsForDenomination, coins)
    coins.foldLeft(0) { case (acc, currentCoin) =>
      proccessCurrentDenomination(numOfCoinsForDenomination, currentCoin)
      acc
    }
   // println(s"${numOfCoinsForDenomination.zipWithIndex.mkString(", ")}")
    if(numOfCoinsForDenomination.last == 0) -1 else numOfCoinsForDenomination.last
  }
}

val denomination = Array.fill(89)(0)
val coins = Array(1,2,5)
val coins2 = Array(186,419,83,408)
val coins3= Array(2,5,10,1)
//coinChange(Array(4,6), 20)

/*initialize(denomination, Array(4,6))
denomination.mkString(", ")*/
//coinChange(coins, 11)

//initialize(denomination, Array(1,2,5))


//initialize(denomination,Array(50,23,11,11).distinct)
//denomination.zipWithIndex.mkString(", ")
//coinChange(coins2, 6249)






