package advancedscala.concurrent

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future, Promise}
import scala.util.{Failure, Random, Success}
import scala.concurrent.duration._

object FuturesAndPromises extends App {

  /*def calculateMeaningOfLife ={
    Thread.sleep(2000)
    42
  }
  val aFuture =  Future{
    calculateMeaningOfLife
  }
 // Thread.sleep(3000)

  println(FuturesAndPromises.aFuture.value)

  println("Waiting on the future")

  FuturesAndPromises.aFuture.onComplete{
    case Success(meaningOfLife) => println(s"The meaning of life is $meaningOfLife")
    case Failure(e) => println(s"I have failed with $e")
  }*/

  case class Profile(id: String, name: String) {
    def poke(anotherProfile: Profile): Unit = {
      println(s"${this.name} is poking ${anotherProfile.name}")

    }
  }

  object SocialNetwork {
    val names = Map("1" -> "Mark",
      "2" -> "Bill",
      "3" -> "Dummy")

    val friends = Map("1" -> "2", "2" -> "3")

    val random = new Random()

    def fetchProfile(id: String): Future[Profile] = {
      Future {
        Thread.sleep(random.nextInt(3000))
        Profile(id, names(id))
      }
    }

    def fetchBbestFriend(id: String): Future[Profile] = Future {
      Thread.sleep(random.nextInt(400))
      val bfdIf = friends(id)
      Profile(bfdIf, names(bfdIf))
    }

    // Client to poke

    /*val mark = SocialNetwork.fetchProfile("1")

    mark.onComplete{
      case Success(markProfile) => {
        val bill = SocialNetwork.fetchBbestFriend(markProfile.id)
        bill onComplete{
          case Success(billProfile) => markProfile.poke(billProfile)
          case Failure(e) => e.printStackTrace()
        }

      }
      case Failure(e)=> e.printStackTrace()
    }
  }*/

    for {
      mark <- SocialNetwork.fetchProfile("1")
      bill <- SocialNetwork.fetchBbestFriend(mark.id)
    } mark.poke(bill)

      Thread.sleep(1000)

    // online banking app
    case class User(name:String)
    case class Transaction(sender: String, receiver: String, amount: Double, status: String)

    object BankingApp{
      val name = "Rock the JVM banking"

      def fetchUser(name: String):Future[User]=Future{
       // SImulate long computation
        Thread.sleep(500)
        User(name)
      }

      def createTransaction(user: User, merchantName: String, amount: Double): Future[Transaction]=Future{
        Thread.sleep(3000)
        Transaction(user.name, merchantName, amount,"SUCCESS")
      }

      def purchase(userName: String, item: String, merchantName: String, cost: Double): String={
        val transactionStatusFuture = for{
          user <- fetchUser(userName)
          transaction <- createTransaction(user, merchantName, cost)
        }yield transaction.status

        Await.result(transactionStatusFuture, 2.seconds)
      }

      val promise = Promise[Int]()
      val future = promise.future

      // thread 1 -consumer
      future.onComplete{
        case Success(r) => println(s"Received value $r")
        case Failure(e)=> println(s"Got an excpetion")
      }

    }

  }

}
