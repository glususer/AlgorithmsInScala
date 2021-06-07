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
//3,5,7,8,9,10,11
//val arr = Array(200, 217, 234, 251, 268, 285, 302, 319, 336, 353, 370, 387, 404, 421, 438, 455, 472, 489, 506, 523, 540, 557, 574, 591, 608, 625, 642, 659, 676, 693, 710, 727, 744, 761, 778, 795, 812, 829, 846, 863, 880, 897, 914, 931, 948, 965, 982, 999, 1016, 1033, 1050, 1067, 1084, 1101, 1118, 1135, 1152, 1169, 1186, 1203, 1220, 1237, 1254, 1271, 1288, 1305, 1322, 1339, 1356, 1373, 1390, 1407, 1424, 1441, 1458, 1475, 1492, 1509, 1526, 1543, 1560, 1577, 1594, 1611, 1628, 1645, 1662, 1679, 1696, 1713, 1730, 1747, 1764, 1781, 1798, 1815, 1832, 1849, 1866, 1883, 1900, 1917, 1934, 1951, 1968, 1985, 2002, 2019, 2036, 2053, 2070, 2087, 2104, 2121, 2138, 2155, 2172, 2189, 2206, 2223, 2240, 2257, 2274, 2291, 2308, 2325, 2342, 2359, 2376, 2393, 2410, 2427, 2444, 2461, 2478, 2495, 2512, 2529, 2546, 2563, 2580, 2597, 2614, 2631, 2648, 2665, 2682, 2699, 2716, 2733, 2750, 2767, 2784, 2801, 2818, 2835, 2852, 2869, 2886, 2903, 2920, 2937, 2954, 2971, 2988, 3005, 3022, 3039, 3056, 3073, 3090, 3107, 3124, 3141, 3158, 3175, 3192, 3209, 3226, 3243, 3260, 3277, 3294, 3311, 3328, 3345, 3362, 3379, 3396, 3413, 3430, 3447, 3464, 3481, 3498, 3515, 3532, 3549, 3566, 3583, 3600, 3617, 3634, 3651, 3668, 3685, 3702, 3719, 3736, 3753, 3770, 3787, 3804, 3821, 3838, 3855, 3872, 3889, 3906, 3923, 3940, 3957, 3974, 3991, 4008, 4025, 4042, 4059, 4076, 4093, 4110, 4127, 4144, 4161, 4178, 4195, 4212, 4229, 4246, 4263, 4280, 4297, 4314, 4331, 4348, 4365, 4382, 4399, 4416, 4433, 4450, 4467, 4484, 4501, 4518, 4535, 4552, 4569, 4586, 4603, 4620, 4637, 4654, 4671, 4688, 4705, 4722, 4739, 4756, 4773, 4790, 4807, 4824, 4841, 4858, 4875, 4892, 4909, 4926, 4943, 4960, 4977, 4994)
//change(11,Array(1,2,5))

//int T[] = new int[total + 1];
//        int R[] = new int[total + 1];
//        T[0] = 0;
//        for(int i=1; i <= total; i++){
//            T[i] = Integer.MAX_VALUE-1;
//            R[i] = -1;
//        }
//        for(int j=0; j < coins.length; j++){
//            for(int i=1; i <= total; i++){
//                if(i >= coins[j]){
//                    if (T[i - coins[j]] + 1 < T[i]) {
//                        T[i] = 1 + T[i - coins[j]];
//                        R[i] = j;
//                    }
//                }
//            }
//        }
//        printCoinCombination(R, coins);
//        return T[total];
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
//[186,419,83,408]
//6249
coinChange(Array(1,2,5),11)
