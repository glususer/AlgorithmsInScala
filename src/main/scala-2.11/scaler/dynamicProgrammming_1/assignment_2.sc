//Given a positive integer A, write a program to find the Ath Fibonacci number.
//
//In a Fibonacci series, each term is the sum of the previous two terms and the first two terms of the series are 0 and 1. i.e. f(0) = 0 and f(1) = 1. Hence, f(2) = 1, f(3) = 2, f(4) = 3 and so on.
//
//NOTE: 0th term is 0. 1th term is 1 and so on.

object Main {

  private def helper(current:Int, n:Int, prev1:Int, prev2:Int):Int={
    if(current == n) prev1+prev2
    else helper(current+1, n,prev2+prev1, prev1)
  }

  def main(args: Array[String]): Int = {
    // YOUR CODE GOES HERE
    // Please take input and print output to standard input/output (stdin/stdout)
    // DO NOT USE ARGUMENTS FOR INPUTS
    // E.g. 'Scanner' for input & 'System.out/println' for output
    val scanner = new java.util.Scanner(System.in)
    val fibonacciNumber = scanner.nextLine().trim.toInt
    if(fibonacciNumber == 0) println(0)
    else if (fibonacciNumber ==1) println(1)
    else{
      val fibonacciNumberValue = helper(2,fibonacciNumber,1,0)
      println(fibonacciNumberValue)
    }
    return 0;
  }
}