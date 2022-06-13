def earliestFullBloomHelper(growAndPlantTime:List[(Int,Int)], acc:Int=0):Int={
  if(growAndPlantTime.isEmpty) acc
  else earliestFullBloomHelper(growAndPlantTime.tail, (acc max growAndPlantTime.head._1)+growAndPlantTime.head._2)
}

def earliestFullBloom(plantTime: Array[Int], growTime: Array[Int]): Int = {
  earliestFullBloomHelper(growTime.zip(plantTime).sortBy(_._1).toList)
}


earliestFullBloom(Array(1,4,3), Array(2,3,1))
earliestFullBloom(Array(1,2,3,2), Array(2,1,2,1))