package Hackerrank

/**
  * Created by shivangi on 16/11/16. ACCEPTED
  */

/**
  * 1st check- if number is not factor of sum of elements in list then it cannot be a solution. second check -for a
  * number to be solution all the multiple upto sum of list should be present in the cumulative sum array, only then it
  * can be solution. So create a treeset and do binary search for multiples. If any multiple is missing then it cannt be
  * a solution so print false.
  */
object BusStation {
  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val ll = input.last.split(" ").map(_.toInt)
      val cumSum = ll.scanLeft(0)(_ + _).drop(1)
      val binSearchCollection = collection.SortedSet(cumSum:_*)
      if(ll.length==1) println(ll.head)
      else {
        var x=cumSum(0)
        var i=0
        while(x <= math.ceil(cumSum.last.toFloat/2).toInt){
          if(cumSum.last%cumSum(i)==0){
            var flag = true
            var totalNoOfFactors = cumSum.last/cumSum(i)-1
            while(totalNoOfFactors > 0 && flag){
              if(binSearchCollection(cumSum(i)*totalNoOfFactors)){
                flag = true
                totalNoOfFactors-=1
              }
              else flag=false
            }
            if(flag) print(cumSum(i)+" ")
          }
          i+=1
          x=cumSum(i)
        }
        print(cumSum.last)
      }
     }
  }

  val stdinString = "8\n1 2"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  BusStation.main(null)

}
