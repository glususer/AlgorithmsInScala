def longestPalindrome(words: Array[String]): Int = {
  val (uniform, nonUniform) = words.partition(word => word.contentEquals(word.reverse))
  val wordSet = nonUniform.toSet

 // println(s"uniform ${uniform.toList} non ${nonUniform.toList} set $wordSet")

 val nonUniformSum =  nonUniform.foldLeft(0){case (palLen, word) =>
 //  println(s"non $word")
  if(wordSet.contains(word.reverse)) palLen+(word.length) else palLen
  }
  val uniformWordsFreq1 = uniform.groupBy(identity)
  println(s"uniformWordsFreq1 ${uniformWordsFreq1.map{case(key, value) => (key, value.toList)}.toString} ")
    val uniformWordsFreq = uniformWordsFreq1.map{case (key, value) => (key, value.map(_.length).sum)}

  val uniformSum = {
    if (uniformWordsFreq.forall { case (_, count) => count == 1 }) uniformWordsFreq.values.max
    else uniformWordsFreq.values.sum
  }

      if(nonUniformSum > 0) nonUniformSum+uniformSum
      else if(uniformSum > 0) uniformSum
      else 0
  }

longestPalindrome(Array("lc","cl","gg"))
longestPalindrome(Array("cc","ll","xx"))
longestPalindrome(Array("dd","aa","bb","dd","aa","dd","bb","dd","aa","cc","bb","cc","dd","cc"))