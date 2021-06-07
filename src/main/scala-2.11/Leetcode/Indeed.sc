def getDomainFreq(counts: List[String]): Map[String, Int] = {

  def createSUbDOmains(freq: Int, domain: String): List[(String, Int)] = {
    val splitL = domain.split('.').toList.map(str => "." + str)
    val subDomains = splitL.scanRight("")(_ + _).map(str => str.drop(1)).filterNot(_.isEmpty)
    val x = subDomains.map(domain => (domain, freq))
    println(x)
    x
  }

  val subDomainFreq = counts.toList.map { case (str) => str.split(",") }.map(arr => arr.toList)
 // println(s"subDomainFreq ${subDomainFreq}")


  val output = subDomainFreq.flatMap { seq => createSUbDOmains(seq.head.toInt, seq.last) }
    .groupBy { case (subDomain, freq) => subDomain }.mapValues { lst => lst.map(_._2).sum }

  output
}

val counts = List(
  "900,mail.google.com",
  "200,sports.google.com",
  "60,mail.yahoo.com",
  "10,mobile.sports.yahoo.com",
  "40,sports.yahoo.com",
  "300,yahoo.com",
  "10,stackoverflow.com",
  "20,overflow.com",
  "5,com.com",
  "2,en.wikipedia.org",
  "1,m.wikipedia.org",
  "1,mobile.sports",
  "1,google.co.uk"
)

getDomainFreq(counts).foreach{case (key,value) => println(s"$key -> $value")}
