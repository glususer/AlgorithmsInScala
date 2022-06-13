class Vehicle
class Bike extends Vehicle
class Car extends Vehicle
class IList[T]

class Parking[T](things:List[T]){
  def park(vehicle: T):Unit={
    ???
  }
  def impound(vehicles:List[T]):Unit ={
    ???
  }
  def checkVehicles(conditions: String): List[T]={
    ???
  }

}

class IParking[T](vehicles:List[T]){
  def park(vehicle:T):IParking[T]= ???
  def impound(vehicles:List[T]):IParking[T]= ???
  def checkVehicles(conditions: String):List[T] = ???
  def flatMap[S](fn: T => IParking[S]): IParking[S]
}

class CParking[+T](vehicles:List[T]){
  def park[S >: T](vehicle:S):CParking[S] = ???
  def impound[S >: T](vehicles: List[S]):CParking[S] = ???
  def checkVehicles(conditions: String):List[T] = ???

  def flatMap[S](fn: T => CParking[S]): CParking[S] = ???
}

class XParking[-T](vehicles:List[T]){
  def park(vehicle:T):XParking[T] = ???
  def impound(vehicles: List[T]):XParking[T] = ???
  def checkVehicles[S <:T](conditions: String):List[S] = ???

  def flatMap[R <: T,S](fn: R => XParking[S]): XParking[S] = ???
}
/*Use covariant if you use = COLLECTION oF things
* COntravariance = GROUP OF ACTIONS*/

class CParking2[+T](vehicles:IList[T]){
  def park[S >: T](vehicle:S):CParking2[S] = ???
  def impound[S >: T](vehicles: IList[S]):CParking2[S] = ???
  def checkVehicles[S >:T](conditions: String):IList[S] = ???
}

class XParking2[-T](vehicles:IList[T]){
  def park(vehicle:T):XParking2[T] = ???
  def impound[S <:T](vehicles: IList[S]):XParking2[S] = ???
  def checkVehicles[S <:T](conditions: String):IList[S] = ???
}