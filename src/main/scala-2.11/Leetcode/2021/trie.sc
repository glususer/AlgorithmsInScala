class TrieNode(_value: Char = ' ', _children: Array[TrieNode] = Array.fill(26)(null), _isWord: Boolean = false) {
  var value: Char = _value
  var children: Array[TrieNode] = _children
  var isWord: Boolean = _isWord
}

object TrieNode {
  val charMap: Map[Char, Int] = ('a' to 'z').zipWithIndex.toMap
  def apply(_value: Char = ' ', _children: Array[TrieNode] = Array.fill(26)(null), _isWord: Boolean = false): TrieNode = new TrieNode(_value, _children, _isWord)

 def insert(tree: Array[TrieNode], item: String): Array[TrieNode] = {
  if(item.isEmpty) tree
  else {
    val node = tree.filter(_ != null).find(node => node.value == item.head)
    node match {
      case Some(innerNode) => if (item.length == 1) tree(charMap(item.head)).isWord = true
        insert(innerNode.children, item.tail)
      case None => val newNode = TrieNode(item.head, Array.fill(26)(null), if (item.length == 1) true else false)
        tree(charMap(item.head)) = newNode
        insert(newNode.children, item.tail)
    }
  }
}

  def getPrefix(tree:Array[TrieNode], word: String, acc: String=""): String= {
    if (word.isEmpty) acc
    else {
      val currentCharNodeExists = tree.collect { case node if node != null => node }.find(node => node.value == word.head)
      currentCharNodeExists match {
        case Some(node) => if (node.isWord) acc + node.value else getPrefix(tree(charMap(word.head)).children, word.tail, acc + node.value)
        case None => acc + word
      }
    }
  }

  def flipALetterAndFind(word:String, onceFlipped:List[(Boolean, TrieNode)]):Boolean={
    println(s"onceFlipped ${onceFlipped.map(node => (node._1, node._2.value)).mkString(", ")}")
    if(word.isEmpty) {
      if(onceFlipped.exists{ case(isFlipped, node) => isFlipped && node.isWord}) true
      else false
    }
    else{
      val x = onceFlipped.flatMap{case (isFlipped, node) => (node.children.filter(_ !=  null).map{child =>
        (isFlipped, child)})}
      println(s"${x.map{case(flipped, node) => (flipped, node.value)}.mkString(", ")}")

      val neighbours = onceFlipped.flatMap{case (isFlipped, node) => (node.children.filter(_ !=  null).map{child =>
        (isFlipped, child)})}
          .filterNot{case(isFlipped, node) => isFlipped && node.value != word.head}
          .map{case(isFlipped, node) => if(!isFlipped && node.value != word.head) (true, node)
          else if(isFlipped && node.value == word.head) (isFlipped, node)
          else if(!isFlipped && node.value == word.head) (false, node)
          else (isFlipped, node)}
      flipALetterAndFind(word.tail, neighbours)
    }
  }

   def search(tree:Array[TrieNode], word: String): Boolean = {
    if(word.length == 1){
      val currentCharNodeExists = tree.collect{case node if node != null=> node}.find(node => node.value == word.head)
      currentCharNodeExists match{
        case Some(node) => node.isWord
        case None => false
      }
    }
    else{
      val currentCharNodeExists = tree.collect{case node if node != null=> node}.find(node => node.value == word.head)
      currentCharNodeExists match{
        case Some(_) => search(tree(charMap(word.head)).children, word.tail)
        case None => false
      }
    }
  }
}

val emptyTrieNodeArray : Array[TrieNode] = Array.fill(26)(null)
//TrieNode.printTrie(emptyTrieNodeArray)

TrieNode.insert(emptyTrieNodeArray,"holao")
TrieNode.insert(emptyTrieNodeArray,"halli")
TrieNode.insert(emptyTrieNodeArray,"hallo")
TrieNode.insert(emptyTrieNodeArray, "hello")
TrieNode.insert(emptyTrieNodeArray, "hillo")
TrieNode.insert(emptyTrieNodeArray, "halbo")

/*TrieNode.search(emptyTrieNodeArray,"holao")
TrieNode.search(emptyTrieNodeArray,"halli")
TrieNode.search(emptyTrieNodeArray,"hallo")
TrieNode.search(emptyTrieNodeArray, "hello")
TrieNode.search(emptyTrieNodeArray, "hillo")
TrieNode.search(emptyTrieNodeArray, "halbo")*/



/*TrieNode.getPrefix(emptyTrieNodeArray, "the")
TrieNode.getPrefix(emptyTrieNodeArray, "cattle")
TrieNode.getPrefix(emptyTrieNodeArray, "was")
TrieNode.getPrefix(emptyTrieNodeArray, "rattled")
TrieNode.getPrefix(emptyTrieNodeArray, "by")
TrieNode.getPrefix(emptyTrieNodeArray, "the")
TrieNode.getPrefix(emptyTrieNodeArray, "battery")*/

def replaceWords(dictionary: List[String], sentence: String): String = {
  val emptyTrieNodeArray : Array[TrieNode] = Array.fill(26)(null)
  dictionary.foreach(word => TrieNode.insert(emptyTrieNodeArray, word))

  sentence.split(" ").map{word => TrieNode.getPrefix(emptyTrieNodeArray,word)}.mkString(" ")
}
//["a", "aa", "aaa", "aaaa"]
//"a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
//replaceWords(List("a", "aa", "aaa", "aaaa"),"a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa")
TrieNode.flipALetterAndFind("ollb", emptyTrieNodeArray.collect{case node if node != null => ((node.value != 'h', node))}.toList)






