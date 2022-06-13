def DFS(nodeToChildren:Map[Int, List[Int]], toVisit:Int, goal:Int, visited:Set[Int]= Set.empty):Boolean={
  if(visited.contains(toVisit)) false
  else{
    val children = nodeToChildren.getOrElse(toVisit, List.empty)
    if(children.contains(goal)) true
    else children.exists{child => DFS(nodeToChildren, child, goal,visited+toVisit)}
  }
}

def graphDFS(arr:Array[Int], src:Int, dest:Int):Boolean={
  val nodeToChildren = arr.zipWithIndex.groupBy{case(parent, _) => parent}.map{case(parent, parentWithChildren) => (parent, parentWithChildren.map(_._2).toList)}
  DFS(nodeToChildren,src,dest )
}

val a =  Array(-1,0,0,1,2,0,1,3,6,1)

graphDFS(a, 0,8)
graphDFS(a, 0,2)
graphDFS(a, 0,5)
graphDFS(a, 3,5)
graphDFS(a, 8,5)
graphDFS(a, 3,9)
graphDFS(a, 3,2)
graphDFS(a, 0,7)
graphDFS(a, 0,9)
graphDFS(a, 1,7)