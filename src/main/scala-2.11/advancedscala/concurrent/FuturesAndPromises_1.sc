
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Random, Success}

object FuturesAndPromises extends App{

  /*def calculateMeaningOfLife ={
    Thread.sleep(2000)
    42
  }
  val aFuture =  Future{
    calculateMeaningOfLife
  }
  Thread.sleep(3000)

  println(FuturesAndPromises.aFuture.value)

  println("Waiting on the future")
  FuturesAndPromises.aFuture.onComplete{
    case Success(meaningOfLife) => println(s"The meaning of life is $meaningOfLife")
    case Failure(e) => println(s"I have failed with $e")
  }*/

  case class Profile(id:String, name:String){
    def poke(anotherProfile:Profile):Unit={
      println(s"${this.name} is poking ${anotherProfile.name}")

    }
  }

  object SocialNetwork{
    val names = Map("1"->"Mark",
    "2"->"Bill",
    "3"->"Dummy")

    val friends = Map("1"->"2","2"->"3")

    val random = new Random()

    def fetchProfile(id:String):Future[Profile]={
      Future {
        Thread.sleep(random.nextInt(3000))
        Profile(id, names(id))
      }
    }

    def fetchBbestFriend(id:String):Future[Profile]=Future{
      Thread.sleep(random.nextInt(400))
      val bfdIf = friends(id)
      Profile(bfdIf, names(bfdIf))
    }

    // Client to poke

    val mark = SocialNetwork.fetchProfile("1")

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
    Thread.sleep(1000)

  }
}

