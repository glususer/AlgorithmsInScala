package Hackerrank

/**
  * Created by shivangi on 18/11/16. ACCEPTED
  */
object SpecialMultiple {

  def main(args: Array[String]) {
    val input = io.Source.stdin.getLines().toList
    if (!input.isEmpty) {
      val l = input.tail.map(x => x.toInt)
      val factors=(1 until 5000).toList.map(_.toBinaryString).map(_.replace('1','9')).filter(_.startsWith("9")).map(x=>x.toLong)
      for (ele <- l) {
        println(factors.filter(z=>z % ele == 0).head)
      }
    }

  }

  val stdinString = "3\n5\n7\n1"
  System.setIn(new java.io.ByteArrayInputStream(stdinString.getBytes("UTF-8")))
  SpecialMultiple.main(null)

}
