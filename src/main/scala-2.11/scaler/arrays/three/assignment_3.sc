case class Interval(start:Int, end:Int)

def helper(intervals: Array[Interval],newInterval:Interval, mergedIntervals:Array[Interval]= Array.empty):Array[Interval]={
  /*if(intervals.isEmpty) if(mergedIntervals.isEmpty)
  else{
    val currentInterval  = intervals.head
    if(newInterval.start <= currentInterval.end && currentInterval.start <= newInterval.end){
      helper(intervals.tail, Interval(currentInterval.start min newInterval.start, currentInterval.end max newInterval.end), mergedIntervals)
    }else{
      helper(intervals.tail, newInterval,
        if(newInterval.start < currentInterval.start) (mergedIntervals:+newInterval):+currentInterval else mergedIntervals:+currentInterval)
    }
  }*/
  ???
}

def overLappingFromLeft(interval1:Interval, interval2:Interval):Boolean=
  interval2.start <= interval1.start  && interval1.start <= interval2.end

def overLappingFromRight(interval1:Interval, interval2:Interval):Boolean=
  interval1.start <= interval2.start && interval2.start <= interval1.end

def helper1(intervals: Array[Interval],newInterval:Option[Interval], mergedIntervals:Array[Interval]= Array.empty):Array[Interval]={

  if(intervals.isEmpty )
    newInterval match{
      case Some(interval)  => (mergedIntervals:+interval)++intervals
      case None =>   mergedIntervals++intervals
    }
  else {
    newInterval match {
      case Some(interval) =>
        if (overLappingFromLeft(intervals.head, interval) || overLappingFromRight(intervals.head, interval)) {
          val updatedMergedInterval = Interval(intervals.head.start min interval.start, intervals.head.end max interval.end)
          helper1(intervals.tail, Some(updatedMergedInterval), mergedIntervals)
        }
        else {
          if (interval.end < intervals.head.start)
            helper1(intervals.tail, None, mergedIntervals :+ interval :+ intervals.head)
          else
            helper1(intervals.tail, Some(interval), mergedIntervals :+ intervals.head)
        }

      case None => helper1(intervals.tail, None, mergedIntervals :+ intervals.head)
    }
  }
}

def insert(intervals: Array[Array[Int]], newInterval: Array[Int]): Array[Array[Int]] = {
  if(intervals.isEmpty) Array(newInterval)
  else {
    val insertedIntervals = helper1(intervals.map(arr => Interval(arr(0), arr(1))), Some(Interval(newInterval(0), newInterval(1))))
    insertedIntervals.map(interval => Array(interval.start, interval.end))
  }
}
val arr = Array(Array(1,2),Array(3,5),Array(6,7),Array(8,10),Array(12,16))
val mergedIntervals = insert(arr, Array(4,8))

mergedIntervals.foreach(x => println(x.toList))