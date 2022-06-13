
object Intro{
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Thread is running")
  })

  // creates a thread that runs on top of JVM thread
  aThread.start()

  def runInParallel():Unit={
    var x = 0

    val thread1 = new Thread(new Runnable {
      override def run(): Unit = x=1
    })

    val thread2 = new Thread(new Runnable {
      override def run(): Unit = x=2
    })

    thread1.start()
    thread2.start()
    println(x)
  }

}

Intro.runInParallel()
