def getKthAncestorHelper(childToParents:Map[Int,Int], node:Option[Int], k:Int, current:Int, acc:List[Int]=List.empty):List[Int]={
  println(s"node $node, k $k current $current, acc $acc")
  if(current==k || node.isEmpty) acc
  else{
    val parent = {
      val p = node.map(childToParents)
      if(p.contains(-1)) None else p
    }
    getKthAncestorHelper(childToParents,parent, k, current+1,if (parent.isDefined) acc:+parent.get else acc )
  }
}

def getKthAncestor(parents:Array[Int], node: Int, k: Int): Int = {
 val ancestors = (getKthAncestorHelper(parents.zipWithIndex.map{case (parent, child) => (child, parent)}.toMap, Some(node),k, 0))

  if(ancestors.length < k) -1
  else ancestors(k-1)
}



class TreeAncestor(_n: Int, _parent: Array[Int]) {

  val parentToChildren = _parent.zipWithIndex.groupBy{case(parent, _) => parent}.map{case(parent, parentWithChildren) => (parent, parentWithChildren.map(_._2).toList)}
  val childToParents = _parent.zipWithIndex.map{case (parent, child) => (child, parent)}.toMap
  val levelOrderTraversal = depth(parentToChildren, List(0))
  val depthOfTreeAsPowerOfTwo = (0 to levelOrderTraversal.length)
    .collectFirst{case x if math.pow(2,x)  >= levelOrderTraversal.length => x}.getOrElse(0)
  val dpArray:Array[Array[Int]] = Array.fill(depthOfTreeAsPowerOfTwo)(Array.fill(_n)(-1))

  println(s"parentToChildren $parentToChildren")
  println(s"childToParents $childToParents")
  println(s"levelOrderTraversal $levelOrderTraversal")
  println(s"depth ${levelOrderTraversal.length}")
  println(s"depthOfTreeAsPowerOfTwo $depthOfTreeAsPowerOfTwo")
  //dpArray.foreach(row => println(row.mkString(", ")))
  preProcessTree()
 // dpArray.foreach(row => println(row.mkString(", ")))

  private def depth (parentToChildren:Map[Int,List[Int]], toVisit:List[Int], acc: List[List[Int]] = List.empty):List[List[Int]]= {
    if(toVisit.isEmpty) acc
    else{
      val children = toVisit.flatMap{node => parentToChildren.getOrElse(node, List.empty)}
      depth(parentToChildren, children, acc:+toVisit)
    }
  }

  private def getJumps(depth: Int, i:Int = 1,acc:List[Int]= List.empty):List[Int] = {
    if(depth <= math.pow(2,i).toInt) acc
    else getJumps(depth, i+1,acc:+i)
  }

  private def preProcessTree():Unit={
    val depthToNode = levelOrderTraversal.zipWithIndex.map{case(nodes, level) => (level+1, nodes)}.toMap.filter{case (depth, _) => depth!=1}
    val nCol = dpArray(0).length
    println(s" rows ${dpArray.length} col ${dpArray(0).length} ")

    (0 until nCol).foreach{col => dpArray(0)(col) = childToParents.getOrElse(col,-1)}
    depthToNode.foreach{case(depth, nodes) => nodes.foreach{ node =>
      //binaryJumpsTillDepth List(1, 2, 3) depth 10
      val binaryJumpsTillDepth : List[Int]= getJumps(depth)
      //  println(s"binaryJumpsTillDepth $binaryJumpsTillDepth depth $depth")
      binaryJumpsTillDepth.foreach{jump =>
        //node 639 jump 3 dp -1
      //  println(s" node $node jump $jump dp ${dpArray(jump-1)(node)}")
        dpArray(jump)(node) = if(dpArray(jump-1)(node) != -1) dpArray(jump-1)(dpArray(jump-1)(node)) else -1}
    }
    }
    println(s" Successfully completed preProcessing----------")
  }

  private def getkAsSumOfPowerOfTwo(k:Int, acc:List[Int]=List.empty, i:Int):List[Int]={
    if(k==0 || i <0 ) acc
    else{
      val diff = k-math.pow(2,i).toInt
      val (updatedK, updatedI) = if(diff >=0) (k-math.pow(2,i).toInt, i-1) else(k, i-1)
      getkAsSumOfPowerOfTwo(updatedK, if(updatedK != k)acc:+i else acc , updatedI)
    }
  }

  def getKthAncestor(node: Int, k: Int): Int = {
    val initialGuess = (0 to k).collectFirst{case x if math.pow(2,x)  > k => x}.getOrElse(0)-1
    val kAsSumOfPowerOfTwo = getkAsSumOfPowerOfTwo(k, List.empty, initialGuess).reverse
    println(s"kAsSumOfPowerOfTwo $kAsSumOfPowerOfTwo")
    if(k > levelOrderTraversal.length) -1
    else{
      kAsSumOfPowerOfTwo.map{p => dpArray(p)(node)}
      kAsSumOfPowerOfTwo.tail.foldLeft(dpArray(kAsSumOfPowerOfTwo.head)(node)){case (acc, ele) =>
        println(s"jump $ele node $acc")
        dpArray(ele)(acc)}
    }

  }
}

val obj1 = new TreeAncestor(7, Array(-1, 0, 0, 1, 1, 2, 2))
obj1.getKthAncestor(3, 1)
obj1.getKthAncestor(5, 2)
obj1.getKthAncestor(6, 3)

val obj2 = new TreeAncestor(5, Array(-1, 0, 0, 3))
obj2.getKthAncestor(1, 5)
obj2.getKthAncestor(3, 2)
obj2.getKthAncestor(0, 1)
obj2.getKthAncestor(3, 1)
obj2.getKthAncestor(3, 5)

val ob3 = new TreeAncestor(10, Array(-1, 0, 0, 1, 2, 0, 1, 3, 6, 1))
//ob3.getKthAncestor(8,6)
ob3.getKthAncestor(9, 7)
ob3.getKthAncestor(1, 1)
ob3.getKthAncestor(2, 5)
ob3.getKthAncestor(4, 2)
ob3.getKthAncestor(7, 3)
ob3.getKthAncestor(3, 7)
ob3.getKthAncestor(9, 6)
ob3.getKthAncestor(3, 5)
ob3.getKthAncestor(8, 8)

val ob4 = new TreeAncestor(1000, Array(-1,0,0,0,2,0,1,5,3,4,0,7,9,2,6,5,1,12,16,8,8,3,16,18,2,4,11,7,10,7,26,18,24,14,27,21,13,18,30,34,25,13,17,7,33,0,7,24,15,15,9,31,35,52,32,11,32,7,41,4,37,32,29,59,63,22,4,41,20,59,64,35,0,9,66,26,24,11,15,56,4,32,10,68,52,21,84,59,85,56,10,33,48,34,70,43,23,48,44,63,15,99,2,92,86,22,43,28,76,8,49,55,19,108,34,73,17,10,20,90,68,97,5,50,52,104,92,9,92,74,91,110,125,72,128,93,119,2,50,136,86,53,125,80,43,16,1,137,23,36,13,0,111,114,99,140,84,135,101,54,81,136,3,84,127,94,139,144,128,147,94,64,57,156,152,159,55,75,109,52,122,34,122,70,178,23,48,79,10,22,150,172,81,10,20,128,102,102,168,187,167,141,55,159,176,49,127,3,70,158,95,58,26,89,30,96,82,207,16,65,120,76,49,107,89,56,216,2,167,83,162,180,150,165,94,108,21,225,44,215,174,86,89,24,25,243,94,39,103,50,106,122,25,212,98,232,179,170,216,167,80,2,116,259,204,98,236,240,138,138,177,165,93,33,15,225,45,85,175,171,110,98,274,66,114,176,187,202,283,135,164,102,107,189,276,237,94,241,2,36,221,217,80,8,81,111,189,21,33,102,260,189,305,26,243,215,249,175,91,166,245,75,96,253,202,122,6,189,129,131,327,273,119,164,158,92,112,253,192,309,147,10,292,217,339,331,154,252,22,193,110,181,84,168,267,16,243,356,258,43,61,318,177,144,31,253,201,56,357,281,135,338,196,94,240,134,275,369,76,318,109,365,296,316,225,299,380,267,380,382,8,40,222,41,26,234,379,109,397,182,374,149,119,173,76,249,241,120,260,162,242,395,352,57,353,102,248,110,298,397,204,28,119,356,16,59,26,388,254,88,5,120,127,126,304,358,133,230,175,270,373,432,350,370,82,190,109,59,400,315,303,273,298,359,37,151,209,48,184,249,356,20,197,181,407,252,239,20,129,438,156,205,156,66,29,31,255,197,77,266,328,163,178,339,207,240,9,289,478,144,197,387,351,40,421,198,68,325,485,482,237,266,178,24,421,237,26,290,92,415,55,385,326,91,223,280,255,164,176,11,233,386,160,114,247,207,250,35,442,319,70,349,36,253,268,239,212,207,80,504,508,447,180,511,209,26,429,476,284,132,148,63,395,468,376,297,329,301,278,363,12,211,260,442,158,7,108,53,558,161,327,321,498,371,280,336,162,424,455,55,399,5,570,229,17,233,112,197,498,478,201,565,378,54,431,149,136,23,127,475,169,529,196,151,506,29,498,85,202,138,57,269,538,459,560,276,263,274,555,511,526,243,57,63,208,273,122,128,68,298,115,305,498,232,74,413,193,608,617,428,516,7,173,600,270,105,187,497,173,422,563,189,119,230,195,496,255,381,484,515,282,659,637,496,44,331,588,104,359,475,89,76,299,330,649,388,175,349,480,337,378,73,382,630,200,422,508,83,20,214,596,530,459,274,111,129,518,214,334,269,89,423,108,31,163,8,544,568,78,290,90,533,619,208,693,455,302,480,686,270,696,560,250,202,432,197,676,536,48,151,675,390,233,513,462,402,134,304,729,508,233,647,487,553,477,176,283,532,90,411,605,61,57,203,691,665,622,444,413,259,27,535,535,195,696,729,481,50,666,472,334,178,764,755,725,205,80,214,746,695,20,741,728,602,736,49,118,604,577,708,179,750,61,676,680,439,272,560,617,210,296,581,264,617,796,547,689,715,3,524,746,314,221,177,774,800,708,354,133,486,543,371,483,156,622,131,176,604,560,687,24,763,354,89,541,430,461,260,405,109,430,147,31,587,744,91,764,816,802,791,753,827,202,637,194,696,510,345,489,705,261,211,791,638,741,27,791,330,84,385,107,157,722,469,394,746,517,298,77,752,678,303,375,469,68,190,453,227,74,583,635,524,792,736,431,814,601,801,762,270,393,783,787,548,180,295,601,490,411,203,696,281,5,206,257,183,854,515,850,28,355,800,239,782,425,862,631,707,540,410,498,800,58,391,42,783,231,331,417,884,158,646,301,754,45,540,781,321,75,125,380,674,944,335,582,631,585,633,639,41,802,203,221,57,85,547,220,350,330,139,20,357,763,408,659,585,248,377,572,426,554,922,155,305,106,313,836,977,457,200,862,563,721,805,460,777,129,765,123,851))
ob4.getKthAncestor(961,479)

/*val d =(0 to 20)

d.map{depth =>
  val powerOfTwo =  (depth / 2 until 0 by -1).collectFirst{case x if math.pow(2,x)  < depth => x}.getOrElse(0)+1
  (depth, powerOfTwo)}*/

