//destroying-asteroids
// 2 approaches to reach solution
/* 1. sort the array and then accumaulate -----> greedy O(nlgn)+O(n) ~ O(nlgn)
2. Sort the array and then binary search for the index where asteroid(i) > mass, add all the asteroid mass till i and repeat --this gives TLE
although theoretically runtime should be O(nlgn)+n*(lgn+lgn) = O(nlgn)+2nlgn
* */
def bsIdx(array:Array[Int], left:Int, right:Int, target:Int):Int={
  if(left < right){
    val mid = (right-left)/2 + left
    if(target < array(mid))bsIdx(array, left,mid, target)
    else bsIdx(array, mid+1, right, target)
  }else {
    if(left < array.length && array(left) > target) left-1 else left
  }
}

def helper(asteroids: Array[Int], planetMass:Int):Boolean={
  val idx = bsIdx(asteroids, 0, asteroids.length, planetMass)
  val zeroIdx = bsIdx(asteroids, 0, asteroids.length, 0)
  println(s"idx $idx zeroIdx $zeroIdx asteroids ${asteroids.toList} planetMass $planetMass")
  if(idx >= asteroids.length) true
  else if(zeroIdx == asteroids.length-2 || idx == zeroIdx) false
  else{
    val enhancedPlanetMass = (zeroIdx+1 to idx).foldLeft(planetMass){case (acc, i) =>
      val currentSum = asteroids(i)+acc
      asteroids(i) = 0
      currentSum
    }
    helper(asteroids, enhancedPlanetMass)
  }

}

def asteroidsDestroyed1(mass: Int, asteroids: Array[Int]): Boolean = {
  scala.util.Sorting.quickSort(asteroids)
  helper(asteroids, mass)
}

asteroidsDestroyed1(375, Array(5,6,24246,51305,87775,12816))

def asteroidsDestroyed(mass: Int, asteroids: Array[Int]): Boolean = {
  scala.util.Sorting.quickSort(asteroids)
  if(asteroids(0) > mass) false
  else{
    asteroids.foldLeft(mass*1L, false){case ((acc, _), asteroid) => if(acc >= asteroid) (acc+asteroid, true)
    else(acc, false)
    }._2
  }

}
asteroidsDestroyed(2, Array(9,19,5,3,21))


