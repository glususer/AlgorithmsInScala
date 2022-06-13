def getSieve(n: Int) :Array[Int]= {
  val isPrime = Array.fill(n+1)(0)
  isPrime(0) = 0
  isPrime(1)=0
  var i=2
  while(i*i <= n){
    if(isPrime(i) == 0){
      var j=i+i
      while(j<=n){
        isPrime(j) =1
        j=j+i
      }
    }
    i=i+1
  }
  isPrime
}
def populatePrimeInFactorialCount(n:Int):Array[Int]={
  val sieve : Array[Int]= getSieve(n)
  val factorialPrimeCount:Array[Int] = Array.fill(n+1)(1)
  for(i<- 2 to n){
    if(sieve(i) !=0)factorialPrimeCount(i) = 1+factorialPrimeCount(i-1)
    else factorialPrimeCount(i) = factorialPrimeCount(i - 1)
  }
  factorialPrimeCount
}

def solve(A: Array[Int]): Int  = {
  import scala.collection.mutable
  scala.util.Sorting.quickSort(A)
  val n = A.last
  val primeInFactorialCount = populatePrimeInFactorialCount(n)
  val B = Array.fill(A.length)(0)
  for(i<-A.indices){
    B(i) = primeInFactorialCount(A(i))
  }

  val frequencyMap = mutable.HashMap[Int,Int]()
  for(j<-A.indices){
    if(B(j) !=0){
      if(frequencyMap.contains(B(j))){
        frequencyMap.update(B(j),1+frequencyMap.getOrElse(B(j),0))
      }else frequencyMap.put(B(j),1)
    }
  }

  val m = 1000000000l+7
  val maxPower = if(frequencyMap.nonEmpty) frequencyMap.values.max else 0
  val powArr = Array.fill(maxPower+1)(1)
  powArr(0) = 1
  for(i<- 1 to maxPower){
    powArr(i) = powArr(i-1)<<1
  }

  frequencyMap.foldLeft(0l){case (a,(_,value)) =>
    (a+powArr(value)-1)%m
  }.toInt
}

solve(Array(1,2,3,4,5))
