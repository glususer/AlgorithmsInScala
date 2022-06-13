def numberOfBeams(bank: Array[String]): Int = {

 val laserCountPerRow =  bank.map{row => row.count(_ == '1')}
   laserCountPerRow.tail.foldLeft(0,laserCountPerRow.head){case ((product, prevLaserCount), currentCount) =>
   if(currentCount != 0) (product+prevLaserCount*currentCount, currentCount) else (product, prevLaserCount)
   }._1
}

numberOfBeams(Array("011001","000000", "010100", "001000"))
numberOfBeams(Array("000","111","000"))