package advancedscala.implicits

import java.util.Date

object JsonSerialization extends App{

  case class User(name: String, age:Int, email:String)
  case class Post(content: String, createdAt: Date)
  case class Feed(user: User, posts: List[Post])

  /* 1. Intermediate data types like Int, String, Date
     2. type classes for conversion to intermediate data type
     3. serialize to JSON
  */

  //1
  sealed trait JSONValue{
    def stringify: String
  }

  //2.1
  final case class JSONString (value: String) extends JSONValue {
    override def stringify: String = "\""+value+"\""
  }

  final case class JSONInt (value: Long) extends JSONValue{
    override def stringify: String = value.toString
  }

  final case class JSONArray (values:List[JSONValue]) extends JSONValue{
    override def stringify: String = values.map(_.stringify).mkString("[",",","]")
  }

  final case class JSONObject(values: Map[String, JSONValue]) extends JSONValue{
    override def stringify: String = values.map{case (key, value) => "\"" + key + "\":" + value.stringify }.mkString("{",",","}")
  }

  // conversion with implicit classes -2.3
  implicit class JSONOps[T](value: T){
    def toJSON(implicit converter: JSONConverter[T]): JSONValue={
      converter.convert(value)
    }
  }

  //2.2
// type class
  trait JSONConverter[T] {
    def convert(value:T):JSONValue
  }

  // type instances
  implicit object UserConverter extends JSONConverter[User]{
    override def convert(user: User): JSONValue = JSONObject(Map("name"->JSONString(user.name), "age"-> JSONInt(user.age), "email"-> JSONString(user.email)))
  }

  implicit object PostConverter extends JSONConverter[Post]{
    override def convert(post: Post): JSONValue = JSONObject(Map(
      "content"-> JSONString(post.content),
      "createdAt"->JSONString((new Date(System.currentTimeMillis()).toString))))
  }

  implicit object FeedConverter extends JSONConverter[Feed]{
    override def convert(feed: Feed): JSONValue = JSONObject(Map(
      "user"-> feed.user.toJSON,
      "posts" -> (JSONArray(feed.posts.map(_.toJSON)))))
  }

  val user = User("Shivangi",35,"abc@gmail.com")
  val post1 = Post("Shivangi Rocks", new Date(System.currentTimeMillis()))
  val post2 = Post("Shivangi Rocks even more", new Date(System.currentTimeMillis()))
  val feed = Feed(user, List(post1, post2))

  println(feed.toJSON.stringify)

}
