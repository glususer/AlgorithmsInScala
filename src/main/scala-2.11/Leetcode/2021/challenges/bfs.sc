def minMovesHelpe(currentDoubles:Int, acc:Int, steps:Int, currentNum:Int):Int= {
 // println(s"currentDoubles $currentDoubles ,acc $acc steps $steps, currentNum $currentNum isEqual ${currentNum == 1} ")
  if (currentNum == 1 && currentDoubles >= 0) acc min steps
  else if (currentNum < 1 || currentDoubles < 0 || steps > acc) acc
  else {
    if(currentNum %2 ==0 && currentDoubles > 0) minMovesHelpe(currentDoubles-1, acc, steps+1, currentNum/2)
    else minMovesHelpe(currentDoubles, acc, steps + 1, currentNum - 1)
  }
}

def minMoves(target: Int, maxDoubles: Int): Int = {
  if(maxDoubles == 0) target-1
  else minMovesHelpe(maxDoubles, Integer.MAX_VALUE, 0, target)
}
999999995
0
//minMoves(766972377,92)
minMoves(999999995,2)
//minMoves(5,0)