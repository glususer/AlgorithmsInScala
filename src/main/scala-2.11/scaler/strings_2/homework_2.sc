//Groot has a profound love for palindrome which is why he keeps fooling around with strings.
//A palindrome string is one that reads the same backward as well as forward.
//
//Given a string A of size N consisting of lowercase alphabets, he wants to know if it is possible
// to make the given string a palindrome by changing exactly one of its character.

def solve(A: String): String  = {
  var i =0
  var j= A.length-1
  var noOfMismatches = 0
  while(i<=j) {
    if (A(i) != A(j)) {
      noOfMismatches = noOfMismatches + 1
    }
    i = i + 1
    j = j - 1
  }
 // println(s"noOfMismatches $noOfMismatches")
  if(noOfMismatches == 1) "YES"
  else if(noOfMismatches ==0 ){
    if(A.length%2==1)"YES"
    else "NO"
  } else "NO"
}

solve("")