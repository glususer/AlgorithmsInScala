class TrieNode(_value: Char = ' ', _children: Array[TrieNode] = Array.fill(26)(null), _isWord: Boolean = false) {
  var value: Char = _value
  var children: Array[TrieNode] = _children
  var isWord: Boolean = _isWord
}

def insertWord(trie:Array[TrieNode], word:String):Array[TrieNode]= {
  if (word.isEmpty) trie
  else {
    val probableNode = trie.filter(_ != null).find(_.value == word.head)
    probableNode match {
      case Some(node) => insertWord(node.children, word.tail)
      case None =>
        val newNode = new TrieNode(_value = word.head)
        trie(word.head.toInt - 97) = newNode
        insertWord(newNode.children, word.tail)
    }
  }
}

  def searchWord(trie:Array[TrieNode], word:String):Boolean={
    if(word.isEmpty) true
    else{
      val probableNode = trie.filter(node => node != null).find(_.value == word.head)
      probableNode match{
        case Some(node) => searchWord(node.children, word.tail)
        case None => false
      }
    }
  }

def alteredSearch(trie:Array[TrieNode], word:String, originalWord:String, skippedChars:Set[Char]):Boolean={
  if (trie.forall(_ == null)) {
    (originalWord diff word).forall(ch => !word.contains(ch))
  }
  else {
    val probableNode = trie.filter(node => node != null).find(node => node.value == word.head)
    probableNode match {
      case Some(node) => alteredSearch(node.children, word.tail, originalWord, skippedChars)
      case None => if(skippedChars.contains(word.head)) alteredSearch()
        alteredSearch(trie, word.tail, word, skippedChars + word.head)
    }
  }
}

def printTrie(trie:Array[TrieNode]):Unit={
  if(trie.isEmpty) return
  else {
    println(trie.map { node => node.value }.mkString(", "))
    printTrie(trie.flatMap(node => node.children.filter(_ != null)))
  }
}

def wordCount(startWords: Array[String], targetWords: Array[String]): Int = {
  val trieArr = startWords.map{word => word.min}.map{ch => new TrieNode(ch)}
  startWords.foreach{word => insertWord(trieArr,word.sorted)}
  targetWords.distinct.count { target =>alteredSearch(trieArr, target, target.sorted) }
}
//["uh"]
//["u","hur","k","b","u","yse","giqoy","lni","olqb","nemc"]
wordCount(Array("u"), Array("ruh"))

//wordCount(Array("ant","act","tack"), Array("tack","act","acti"))
//wordCount(Array("ab","a"), Array("abc","abcd"))
//wordCount(Array("mox","bj","rsy","jqsh"), Array("trk","vjb","jkr"))

/*
val words = Array("mox","bj","rsy","jqsh")
val trie = words.map{word => word.min}.map{ch => new TrieNode(ch)}

words.foreach(word => insertWord(trie, word.sorted))
printTrie(trie)
*/

/*
searchWord(Array(trie), "app")
searchWord(Array(trie), "ame")
searchWord(Array(trie), "abe")*/

val num|=1<<s.charAt(i)-'a'