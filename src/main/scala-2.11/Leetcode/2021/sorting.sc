//https://leetcode.com/problems/sort-colors/
def sortColors(nums: Array[Int]): Unit = {
  nums.scanLeft(0, nums.length - 1) { case ((i, j), _) => if (i < j) {
    (nums(i), nums(j)) match {
      case (k, l) if k == 2 && l == 2 =>println(s"case 1 i $i j $j ${nums.mkString(", ")}")
        (i + 1, j)
      case (k, l) if k == 2 => println(s"case 2 i $i j $j ${nums.mkString(", ")}")
        nums(i) = nums(j)
        nums(j) = 2
        (i + 1, j - 1)
      case (k, l) if( k == 0 || k == 1 ) && l == 2 => println(s"case 3 i $i j $j ${nums.mkString(", ")}")
        (i + 1, j - 1)
      case (k,l)  if(k==1 && l==0) => nums(i) = 0
      nums(j) =1
        println(s"case 4 i $i j $j ${nums.mkString(", ")}")
        (i+1,j-1)
      case (k,l) if (k==0 && l==0) =>println(s"case 5 i $i j $j ${nums.mkString(", ")}")
        (i+1, j)
      case (k,l) if(k==1 && l==1)   => println(s"case 6 i $i j $j ${nums.mkString(", ")}")
        (i,j-1)
      case (k, l) if (k == 0  && l == 1) => println(s"case 7 i $i j $j ${nums.mkString(", ")}")
        (i + 1, j)
    }
  }else (i,j)
  }

  nums.foreach(print)
}

sortColors(Array(2,1,0,2,1,1,1,2,1,1,0,0))