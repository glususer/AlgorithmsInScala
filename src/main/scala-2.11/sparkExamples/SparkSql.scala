package sparkExamples

import Leetcode.Sort

/*import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object SparkSql {
  def main(args: Array[String]): Unit = {

    val x = List(
      (1,"John", Some(30), 75, "Maths"),
      (1,"John", Some(30), 80, "English"),
      (1,"Mike", Some(30), 34, "Maths"),
      (1,"John", None, 90, "Science"),
      (2,"Johnny", Some(20), 25, "Maths"),
      (2,"Jill", Some(5), 77, "English"),
      (2,"Pearson", Some(10), 45, "Maths"),
      (2,"Johnny", Some(20), 55, "English"),
      (3,"Mike", Some(15), 23, "English")
    )


    val spark: SparkSession = SparkSession.builder.master("local").getOrCreate
    val sc = spark.sparkContext
    val distData = sc.parallelize(x)
    val df = spark.createDataFrame(distData).toDF("DepartmentId","Name", "Age","Marks","Subject")
    df.show()
    import spark.implicits._

    df.groupBy("Name").count().show()

    df.createOrReplaceTempView("people")
    spark.sql("SELECT name, age FROM people WHERE age BETWEEN 13 AND 19")
      .map(teenager => "Name: " + teenager.getAs[String]("name")).show()

    df.groupBy("DepartmentId").pivot("Name").agg("Marks" -> "max").show()//.max("Marks").show()
    /*spark.sql("SELECT DepartmentId,Subject," +
      "MAX(Marks) " +
      "FROM people " +
      "GROUP BY DepartmentId, Subject").show()*/

   // df.groupBy("DepartmentId","Subject").max("Marks").select("DepartmentId","Subject","Name","Marks").show()

    df.select($"*").orderBy($"Marks".desc).groupBy($"DepartmentId").max("Marks").show()
   val empDF = spark.createDataFrame(Seq(
     (7369, "SMITH", "CLERK", 7902, "17-Dec-80", 800, 20, 10),
     (7370, "ALLENYE", "CLERK", 7902, "20-Dec-80", 1800, 10, 10),
     (7499, "ALLEN", "SALESMAN", 7698, "20-Feb-81", 1600, 300, 30),
     (7521, "WARD", "SALESMAN", 7698, "22-Feb-81", 1250, 500, 30),
     (7566, "JONES", "MANAGER", 7839, "2-Apr-81", 2975, 0, 20),
     (7654, "MARTIN", "SALESMAN", 7698, "28-Sep-81", 1250, 1400, 30),
     (7698, "BLAKE", "MANAGER", 7839, "1-May-81", 2850, 0, 30),
     (7782, "CLARK", "MANAGER", 7839, "9-Jun-81", 2450, 0, 10),
     (7788, "SCOTT", "ANALYST", 7566, "19-Apr-87", 3000, 0, 20),
     (7839, "KING", "PRESIDENT", 0, "17-Nov-81", 5000, 0, 10),
     (7844, "TURNER", "SALESMAN", 7698, "8-Sep-81", 1500, 0, 30),
     (7876, "ADAMS", "CLERK", 7788, "23-May-87", 1100, 0, 20))).
     toDF("empno", "ename", "job", "mgr", "hiredate", "sal", "comm", "deptno")

    val partitionWindow = Window.partitionBy($"deptno").orderBy($"sal".desc)
    val partitionWindowRank = rank().over(partitionWindow)

    empDF.select($"*", partitionWindowRank as "dense_rank").where($"dense_rank" ===2).show()

    //1. Add new coloumn
    empDF.withColumn("Country", lit("USA"))
      .withColumn("Planet", lit("Earth")).show
    //2. Update existing coloumn
    empDF.withColumn("sal",col("sal")*10).show()

    //3. Filter
      empDF.filter($"sal" === 15000 ).show()
      // a. Filter on col that are of array type.
        //df.filter(array_contains(df("languages"),"Java"))

    //4. Pivot to rotate the data from one column into multiple columns (transpose rows into columns).
    // It is an aggregation where one of the grouping columns values transposed into individual columns with distinct data
    val positions = Seq("ANALYST","CLERK","MANAGER","PRESIDENT","SALESMAN")
    empDF.groupBy("deptno").pivot("job",positions).max("sal").show()
    empDF.groupBy("deptno","job")
      .agg(
        max("sal").as("max_salary"),
        min("sal").as("min_salary"),
        min("hiredate").as("min_hiredate"),
        max("hiredate").as("max_hiredate")).show()

    //5. sort col
    empDF.sort($"sal".asc)
    empDF.orderBy("sal", "hiredate")
   /* df.select($"employee_name",asc("department"),
      desc("state"),$"salary",$"age",$"bonus").show(false)*/

    //6. Sql Joins - INNER, LEFT OUTER, RIGHT OUTER, LEFT ANTI, LEFT SEMI, CROSS, SELF
    val emp = sc.parallelize(Seq((1,"Smith",-1,"2018","10","M",3000),
      (2,"Rose",1,"2010","20","M",4000),
      (3,"Williams",1,"2010","10","M",1000),
      (4,"Jones",2,"2005","10","F",2000),
      (5,"Brown",2,"2010","40","",-1),
      (6,"Brown",2,"2010","50","",-1)
    )).toDF("emp_id","name","superior_emp_id","year_joined",
      "emp_dept_id","gender","salary")

    val dept = sc.parallelize(Seq(("Finance",10),
      ("Marketing",20),
      ("Sales",30),
      ("IT",40))).toDF("dept_name","dept_id")
    emp.join(dept,emp("emp_dept_id") === dept("dept_id"),"inner").groupBy("dept_id").count()
      .withColumnRenamed("count","No Of Employees")
    emp.join(dept,emp("emp_dept_id") === dept("dept_id"),"fullouter").show()

    //7. Union, combines 2 DF with same schema, use distinct to filter out duplicates
    emp.union(empDF).distinct()

  }

  Sort.main(Array("1 2 3 4 5"))
} */
