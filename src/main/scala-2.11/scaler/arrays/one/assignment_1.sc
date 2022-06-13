//import "math"
//func maxArr(A []int )  (int) {
//    INT_MAX:=int(^uint(0)>>1)
//    INT_MIN:=^INT_MAX
//
//    Xmax:=float64(INT_MIN)
//    Xmin:=float64(INT_MAX)
//    Ymax:=float64(INT_MIN)
//    Ymin:=float64(INT_MAX)
//    for i, ele:=range A {
//        Xmax=math.Max(float64(ele+i), Xmax)
//        Xmin=math.Min(float64(ele+i), Xmin)
//        Ymax=math.Max(float64(ele-i), Ymax)
//        Ymin=math.Min(float64(ele-i), Ymin)
//    }
//    return int(math.Max(Xmax-Xmin, Ymax-Ymin))
//}

def maxArr(A: Array[Int]): Int  = {
  val(xMax, xMin, yMax, yMin) = A.indices.foldLeft(Int.MinValue, Int.MaxValue, Int.MinValue, Int.MaxValue){case ((xMax, xMin, yMax, yMin), idx) =>
    (A(idx)+idx max xMax, A(idx)+idx min xMin, A(idx)-idx max yMax, A(idx)-idx min yMin)
  }
  xMax-xMin max yMax-yMin
}

maxArr(Array(2))