

def solution(a: Array[String], b: Array[Int]): String = {
  val countryVsPositions = a.map(bankAccount => bankAccount.take(2))
    .zipWithIndex.groupBy(_._1)
    .map{case (countryCode, seq) => (countryCode,seq.map(_._2).toList)
    }

  println(s"countryVsPositions ${countryVsPositions}")

  countryVsPositions.map{case (countryCode, positions) => (countryCode,positions.map(pos => b(pos)).sum)}.maxBy(_._2)._1

}

solution(Array("AB34","AB78","CD66"), Array(2,2,5))

case class Date(year:Int, month:Int, day:Int, hour:Int, min:Int, sec:Int)
case class OldPic(name:String, city:String, date:Date, originalDateTimeAsKey:String)
case class NewPic(city:String, serialNo:Int)

def solution(s:String):String={
 val oldPics =  s.split("\n").map{s => val fields = s.split(",")
   val dateStr =fields(2).trim.split(" ")(0)
   val timeStr  = fields(2).trim.split(" ")(1)
  //  println(s"fields(2) ${fields(2)}dateStr ${dateStr} timeStr $timeStr ")
   val dateArr = dateStr.split("-").map(_.trim.toInt)
   val timeArr =  timeStr.split(":").map(_.trim.toInt)

   OldPic(fields(0),fields(1).trim,Date(
     dateArr(0),
     dateArr(1),
     dateArr(2),
     if(timeArr(0)==0) 24 else timeArr(0),
     timeArr(1),timeArr(2)),
     dateStr+timeStr+fields(1).toUpperCase)
 }

  val timeVsPicMap = oldPics.map(pic => (pic.originalDateTimeAsKey, pic)).toMap

  oldPics.groupBy(_.city).foreach { case (city, oldPics) =>
    println(s"city $city --> ${oldPics.toList.mkString("\n")} \n ")
    (city, oldPics)
  }

  val newPicsMap = oldPics.groupBy(_.city).flatMap{case (city, oldPics) =>
    println(s"Insidecity $city --> ${oldPics.toList.mkString("\n")} \n ")
    println(s"\n")
    val sortedPics = oldPics.sortWith{(c,d) =>
    val a = c.date
    val b = d.date
    (a.year < b.year) || (a.year == b.year && a.month < b.month) ||
    (a.year == b.year && a.month == b.month && a.day < b.day) ||
    (a.year == b.year && a.month == b.month && a.day ==  b.day && a.hour < b.hour) ||
    (a.year == b.year && a.month == b.month && a.day ==  b.day && a.hour == b.hour && a.min < b.min) ||
    (a.year == b.year && a.month == b.month && a.day ==  b.day && a.hour == b.hour && a.min == b.min && a.sec < b.sec)
  }
    println(s"sortedPics ${sortedPics.zipWithIndex.toList.mkString("\n")}")
    val countOfPicsWithinCity = sortedPics.zipWithIndex.map{case (pic,ind) => (pic.originalDateTimeAsKey,city+ind+"."+pic.name.split('.').lastOption.getOrElse("NA"))}.toMap

    countOfPicsWithinCity
  }

  timeVsPicMap.map{case (key, _) => newPicsMap.getOrElse(key, "Not Found")}.mkString("\n")
}


val picString = "photo.jpg, Warsaw, 2013-09-05 14:08:15\njohn.png, London, 2015-06-20 15:13:22\nmyFriends.png, Warsaw, 2013-09-05 14:07:13\nEiffel.jpg, Paris, 2015-07-23 08:03:02\npisatower.jpg, Paris, 2015-07-22 23:59:59\nBOB.jpg, London, 2015-08-05 00:02:03\nnotredame.png, Paris, 2015-09-01 12:00:00\nme.jpg, Warsaw, 2013-09-06 15:40:22\na.png, Warsaw, 2016-02-13 13:33:50\nb.jpg, Warsaw, 2016-01-02 15:12:22\nc.jpg, Warsaw, 2016-01-02 14:34:30\nd.jpg, Warsaw, 2016-01-02 15:15:01\ne.png, Warsaw, 2016-01-02 09:49:09\nf.png, Warsaw, 2016-01-02 10:55:32\ng.jpg, Warsaw, 2016-02-29 22:13:11"
 val x = solution(picString)


/*
val date = Date(2013,9,5,14,8,15)
val date2 = Date(2013,9,4,15,8,15)

List(Date(2013,9,5,14,8,15),Date(2013,9,4,14,8,10),Date(2013,9,4,15,8,15),Date(2013,9,4,0,8,15),Date(2013,9,4,14,8,15) ).sortWith((a,b) => (a.year < b.year) || (a.year == b.year && a.month < b.month) ||
  (a.year == b.year && a.month == b.month && a.day < b.day) ||
  (a.year == b.year && a.month == b.month && a.day ==  b.day && a.hour < b.hour) ||
  (a.year == b.year && a.month == b.month && a.day ==  b.day && a.hour == b.hour && a.min < b.min) ||
  (a.year == b.year && a.month == b.month && a.day ==  b.day && a.hour == b.hour && a.min == b.min && a.sec < b.sec)
)*/
"photo.jpg".split('.')