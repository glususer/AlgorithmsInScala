import scala.util.Random

def helper(idx:Int, A:Array[Int], count:Int):Int={
 // println(s"i is $idx count is $count A(i) ${if(idx < A.length) A(idx) else 0}")
  if(idx < A.length){
   // println(s"i is $idx A(i) is ${A(idx)} A is ${A.toList} ")
    if (A(idx) > 0 && A(idx) < A.length && A(idx) != A(A(idx)-1)) {
      val temp = A(A(idx) - 1)
      A(A(idx) - 1) = A(idx)
      A(idx) = temp
      helper(idx,A, count+1)
    }
    else helper(idx+1,A, count+1)
  }else idx
}

def firstMissingPositive(A: Array[Int]): Int  = {
  println(s"length ${A.length}")
 // helper(0,A,0)
  //println(s"A ${A.toList.zipWithIndex}")
  A.foreach(num => if(num< A.length && num >0 &&  num != A(num)) {
    val temp =  A(num)
    A(num) = A(A(num))
    A(A(num)) = temp
  } )

    A.tail.zipWithIndex.find{case (num, idx)  => num -1 != idx}.map(_._2+1)
    .getOrElse(A.max+1)
}
//firstMissingPositive(Seq.fill(1000000)(Random.nextInt(10000000)).toArray)
//firstMissingPositive(Array(2, 3, 1, 2, 4, 1 ))
firstMissingPositive(Array(699, 2, 690, 936, 319, 784, 562, 35, 151, 698, 126, 730, 587, 157, 201, 761, 956, 359, 198, 986, 915, 7, 703, 324, 814, 382, 294, 204, 120, 731, 615, 330, 486, 52, 223, 376, 649, 458, 564, 971, 72, 605, 177, 20, 461, 790, 872, 363, 916, 435, 991, 184, 410, 320, 16, 480, 768, 801, 117, 338, 650, 786, 17, 369, 979, 304, 445, 688, 862, 229, 311, 351, 985, 697, 135, 299, 310, 3, 643, 221, 831, 196, 887, 679, 484, 209, 824, 292, 588, 721, 140, 675, 827, 913, 271, 170, 812, 552, 334, 860, 981, 550, 308, 584, 442, 328, 251, 456, 976, 31, 507, 954, 982, 742, 45, 727, 794, 309, 527, 623, 56, 843, 436, 681, 143, 130, 689, 870, 362, 580, 560, 474, 385, 525, 881, 51, 890, 917, 820, 826, 139, 443, 978, 144, 512, 205, 682, 188, 344, 429, 497, 181, 749, 864, 664, 145, 621, 629, 886, 572, 89, 725, 945, 29, 553, 977, 783, 590, 236, 728, 125, 90, 492, 261, 543, 259, 662, 622, 285, 392, 561, 670, 200, 504, 246, 513, 910, 583, 460, 179, 207, 709, 127, 926, 816, 426, 520, 174, 464, 883, 780, 5, 268, 606, 1, 109, 704, 391, 661, 924, 516, 241, 477, 952, 405, 522, 247, 335, 356, 839, 423, 779, 4, 43, 720, 238, 965, 951, 914, 10, 496, 775, 651, 788, 373, 491, 746, 799, 518, 93, 86, 774, 652, 955, 494, 252, 781, 946, 412, 202, 741, 719, 612, 673, 896, 1000, 289, 554, 69, 424, 980, 506, 593, 889, 25, 959, 28, 736, 8, 969, 865, 657, 567, 434, 9, 167, 357, 929, 645, 250, 565, 94, 928, 473, 509, 823, 313, 762, -1, 208, 903, 922, 655, 948, 326, 485, 150, 73, 505, 225, 122, 129, 648, 838, 811, 972, 735, 78, 428, 740, 782, 632, 316, 440, 737, 297, 873, 281, 479, 654, 0, 633, 212, 152, 154, 470, 866, 79, 722, 958, 732, 900, 832, 278, 58, 842, 745, 540, 169, 347, 592, 438, 882, 462, 53, 34, 519, 489, 85, 757, 919, 701, 15, 211, 667, 637, 74, 573, 240, 559, -2, 472, 203, 112, 162, 776, -4, 155, 837, 99, 98, 64, 101, 983, 366, 853, 970, 482, 40, 921, 374, 758, 413, 339, 705, 771, 360, 734, 282, 219, 766, 535, 133, 532, 254))

def firstMissingPositive1(A: Array[Int]): Int  = {
  if (A.isEmpty || A.min > A.length) 1
  else {
    val arrMax = A.max
    val arrMin = A.min

    A.zipWithIndex.foreach(num => if (num._1 >= A.length || num._1 < 0) A(num._2) = 0)

    A.distinct.foreach { num =>
      val absoluteNum = math.abs(num)
      A(absoluteNum) = {
        if (A(absoluteNum) == 0) -1 else if (num != -1) A(absoluteNum) * -1 else A(absoluteNum)
      }
    }

    println(A.toList)
    A.tail.zipWithIndex.find(_._1 >= 0).getOrElse(0, if (A.length == 1) {
      if (arrMin < 0) 0 else arrMin
    } else {
      if (arrMax == A.length) A.length else A.length - 1
    })._2 + 1
  }


}
//firstMissingPositive1(Array(2, 3, 1, 2, 4, 1 ))
Array(2, 3, 1, 2, 4, 1 ).distinct
//firstMissingPositive1(Seq.fill(1000000)(Random.nextInt(100)).toArray)