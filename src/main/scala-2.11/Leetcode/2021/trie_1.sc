class Trie(){

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

  def insert(word: String): Unit = {
    insertHelper(root, word)
  }

  private def searchHelper(tree:Array[TrieNode], word: String): Boolean = {
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
        case Some(_) => searchHelper(tree(charMap(word.head)).children, word.tail)
        case None => false
      }
    }
  }

  def search(word: String): Boolean = {
    searchHelper(root, word)
  }

  private def startsWithHelper(tree:Array[TrieNode], prefix: String): Boolean = {
    if(prefix.isEmpty)true
    else{
      val currentCharNodeExists = tree.collect{case node if node != null=> node}.find(node => node.value == prefix.head)
      currentCharNodeExists match{
        case Some(_) => startsWithHelper(tree(charMap(prefix.head)).children, prefix.tail)
        case None => false
      }
    }
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  def startsWith(prefix: String): Boolean = {
    startsWithHelper(root, prefix)
  }
}

val trie = new Trie

trie.insert("apple")
trie.search("apple")
trie.search("app")
trie.startsWith("app")
trie.insert("app")
trie.search("app")