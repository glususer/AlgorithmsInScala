//You are given a string A. Find the number of substrings that start and end with 'a'.

def solve(A: String): Int  = {
  val count = A.count(_ == 'a')

  (count*(count+1))/2
}

solve("abca")