import scala.collection.mutable

trait Database[T]{
  def save(data:T)
}

trait History{
  def add():Unit={
    println("Action added to history")
  }
}

trait Mystery{
  def add(): Unit = {
    println("Mystery added!")
  }
}

trait Persister[T]{
  this: Database[T] with History with Mystery =>
  def persist(data:T):Unit ={
    println("Calling persist")
    save(data)
    add()
  }
}

trait MemoryDatabase[T] extends Database[T]{
  val db: mutable.MutableList[T] = mutable.MutableList.empty

  override def save(data: T): Unit = {
    db:+data
    println(s"adding $data to list")
  }
}

trait FileDatabase[T] extends Database[T]{
  override def save(data: T): Unit = println(s"Saving $data to file")
}

class FilePersister[T] extends Persister[T] with FileDatabase[T] with History with Mystery {
  override def add():Unit = super.add()
}

class MemoryPersister[T] extends Persister[T] with MemoryDatabase[T] with History with Mystery {
  override def add():Unit = super.add()
}

val fileStringPersister = new FilePersister[String]
val memoryIntPersister = new MemoryPersister[Int]

fileStringPersister.persist("Something")
fileStringPersister.persist("Something else")

memoryIntPersister.persist(100)
memoryIntPersister.persist(123)

// Self types Vs inheritance

//Inheritance leaks functionality

trait DB {
  def connect(): Unit = {
    System.out.println("Connected.")
  }

  def dropDatabase(): Unit = {
    System.out.println("Dropping!")
  }

  def close(): Unit = {
    System.out.println("Closed.")
  }
}

trait UserDB extends DB {
  def createUser(username: String): Unit = {
    connect()
    try {
      System.out.println(s"Creating a user: $username")
    } finally {
      close()
    }
  }

  def getUser(username: String): Unit = {
    connect()
    try {
      System.out.println(s"Getting a user: $username")
    } finally {
      close()
    }
  }
}

trait UserService extends UserDB {
  def bad(): Unit = {
    //  this is bad. We dont want to expose this fn to service. Should only
    // be used in UserDB
    dropDatabase()
  }
}

// to circumvent the above condition we use self types
trait UserDB2 {
  this :DB =>
  def createUser(username: String): Unit = {
    connect()
    try {
      System.out.println(s"Creating a user: $username")
    } finally {
      close()
    }
  }

  def getUser(username: String): Unit = {
    connect()
    try {
      System.out.println(s"Getting a user: $username")
    } finally {
      close()
    }
  }

}

trait UserService2 {

  this: UserDB2 =>
  def bad(): Unit = {
    // does not compile
    // def bad(): Unit = {
    // dropDatabase()
    //}
  }
    def good ():Unit = createUser("Hi there")
  }

val service : UserService2 = new UserService2 with UserDB2 with DB

service.good()
service.bad()


