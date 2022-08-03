//Given the number of vertices A in a Cyclic Graph.
//
//Your task is to determine the minimum number of colors required to color the graph so that no two Adjacent vertices have the same color.


def solve(A: Int): Int  = {
  if(A%2==0) 2 else 3
}