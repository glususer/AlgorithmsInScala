def isPerfectSqaure(x:Integer):Boolean={
  val floor = math.sqrt(x.toDouble).floor
  val ceil = math.sqrt(x.toDouble).ceil
  floor == ceil
}

//(1 to 1000).map(x => (x, isPerfectSqaure(x))).filter(_._2).mkString(", ")

(7 >> 0)&1
(7 >> 1)&1
(7 >> 2)&1

(8>>0)&1
(8>>1)&1
(8>>2)&1
(8>>3)&1

2^3^5^6^3^6^2





