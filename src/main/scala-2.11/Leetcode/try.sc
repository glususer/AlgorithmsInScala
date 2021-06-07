sealed abstract class DatabaseError
trait DatabaseValue

object Database {
  def databaseThings(): Either[DatabaseError, DatabaseValue] = ???
}

sealed abstract class ServiceError
trait ServiceValue

object Service {
  def serviceThings(v: DatabaseValue): Either[ServiceError, ServiceValue] = ???
}

sealed abstract class AppError
object AppError {
  final case class Database(error: DatabaseError) extends AppError
  final case class Service(error: ServiceError) extends AppError
}

def doApp: Either[AppError, ServiceValue] =
  Database.databaseThings().leftMap[AppError](AppError.Database).
    flatMap(dv => Service.serviceThings(dv).leftMap(AppError.Service))