//N children are standing in a line. Each child is assigned a rating value.
//
//You are giving candies to these children subjected to the following requirements:
//
//Each child must have at least one candy.
//Children with a higher rating get more candies than their neighbors.
//What is the minimum number of candies you must give?

def candy(A: Array[Int]): Int  = {
  val candiesWRTLeftNeighbour = A.scanLeft(0,0){case ((prevRating, prevCandy),currentRating) =>
    if(prevRating < currentRating) (currentRating,prevCandy+1)
    else (currentRating,1)
  }.tail.map(_._2)

  val candiesWRTRightNeighbour = A.scanRight(0,0){case (currentRating,(prevRating, prevCandy)) =>
    if(prevRating < currentRating) (currentRating,prevCandy+1)
    else (currentRating,1)
  }.dropRight(1).map(_._2)
 // println(s"left ${candiesWRTLeftNeighbour.toList} right ${candiesWRTRightNeighbour.toList}")
  candiesWRTLeftNeighbour.zip(candiesWRTRightNeighbour).map{case (l,r)=> l max r}.sum
}

candy(Array(1, 5, 2, 1))