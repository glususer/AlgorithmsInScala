def solve(A: Int, B: Int): Int  = {
  ???
}

def primsNosInRange(N:Int):List[Int]={
  (2 to math.sqrt(N).toInt).foldLeft((2 to N).toList){case (acc, i)  =>
    println(s"i $i , acc $acc")
    acc.filter(x => x <= i || x > i && x%i != 0)
  }
}

def findCountOfDivisors(N:Int):List[(Int,Int)]={
  (1 to N).foldLeft((1 to N).zip(List.fill(N)(1))){case (acc, i)  =>
    println(s"i $i , acc $acc")
    acc.map{case (x, sum) => if(x % i==0  && x > i ) (x, sum+1) else (x, sum)}
  }.toList
}

//primsNosInRange(100)
findCountOfDivisors(10)
