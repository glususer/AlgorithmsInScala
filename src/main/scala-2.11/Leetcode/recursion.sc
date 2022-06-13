//https://leetcode.com/problems/coin-change-2/

def change1(amount: Int, coins: Array[Int]): Int = {
  if(amount == 0 )1
  else if(coins.isEmpty || amount < 0) 0
  else{
    val exclude = change1(amount, coins.tail)
      val include = change1(amount-coins.head, coins)
    include+exclude
  }
}
//500
//[3,5,7,8,9,10,11]
change1(10,Array(1,2,5))

def change(amount: Int, coins: Array[Int]): Int = {
  if (amount == 0) 1
  else if(coins.isEmpty) 0

  else {
    val col = amount
    val row = coins.length - 1
    val arr = Array.fill(coins.length, amount + 1)(0)

    for (i <- 0 to row) arr(i)(0) = 0
    for (j <- 1 to col) arr(0)(j) = if (j % coins(0) == 0) 1 else 0

    for (i <- 1 to row; j <- 1 to col) {
      val prev = {
        if (j - coins(i) > 0) arr(i)(j - coins(i))
        else if (j - coins(i) == 0) 1
        else 0
      }
      arr(i)(j) = arr(i - 1)(j) + prev
    }
    //  arr.foreach(a => println(a.toList))

    arr(row)(amount)
  }

}

def coinChange(coins: Array[Int], amount: Int): Int = {
 val T = Array.fill(amount+1)(Integer.MAX_VALUE-1)
  val R = Array.fill(amount+1)(-1)
  T(0)=0
  scala.util.Sorting.quickSort(coins)

  for(j<-coins.indices;i <- 1 to amount){
    if(i >= coins(j)){
      if(T(i-coins(j))+1 < T(i)){
        T(i) = 1+T(i-coins(j))
        R(i) = j
      }
    }
  }
  println(T.toList)
  println(R.toList)
   T(amount)
}
coinChange(Array(1,2,5),11)
