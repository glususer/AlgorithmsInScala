//Runtime: 1813 ms, faster than 100.00% of Scala online submissions for Implement Magic Dictionary.
//Memory Usage: 122.7 MB, less than 100.00% of Scala online submissions for Implement Magic Dictionary

//https://leetcode.com/problems/implement-magic-dictionary/submissions/
class MagicDictionary() {
  class TrieNode(_value: Char = ' ', _children: Array[TrieNode] = Array.fill(26)(null), _isWord: Boolean = false) {
    var value: Char = _value
    var children: Array[TrieNode] = _children
    var isWord: Boolean = _isWord
  }

  object TrieNode {
    val charMap: Map[Char, Int] = ('a' to 'z').zipWithIndex.toMap
    def apply(_value: Char = ' ', _children: Array[TrieNode] = Array.fill(26)(null), _isWord: Boolean): TrieNode = new TrieNode(_value, _children, _isWord)
  }

  val root : Array[TrieNode] = Array.fill(26)(null)
  val charMap : Map[Char,Int] = ('a' to 'z').zipWithIndex.toMap

  private def insertHelper(tree: Array[TrieNode], item: String): Array[TrieNode] = {
    if(item.isEmpty) tree
    else {
      val node = tree.filter(_ != null).find(node => node.value == item.head)
      node match {
        case Some(innerNode) => if (item.length == 1) tree(charMap(item.head)).isWord = true
          insertHelper(innerNode.children, item.tail)
        case None => val newNode = TrieNode(item.head, Array.fill(26)(null), if (item.length == 1) true else false)
          tree(charMap(item.head)) = newNode
          insertHelper(newNode.children, item.tail)
      }
    }
  }

  private def insert(word: String): Unit = {
    insertHelper(root, word)
  }

  private def flipALetterAndFind(word:String, onceFlipped:List[(Boolean, TrieNode)]):Boolean={
    if(word.isEmpty) {
      if(onceFlipped.exists{ case(isFlipped, node) => isFlipped && node.isWord}) true
      else false
    }
    else{
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


  def buildDict(dictionary: Array[String]) {
    dictionary.foreach(word => insert(word))
  }

  def search(searchWord: String): Boolean = {
    flipALetterAndFind(searchWord.tail, root.collect{case node if node != null => ((node.value != searchWord.head, node))}.toList)
  }

}