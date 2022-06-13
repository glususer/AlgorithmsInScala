object SelfTypes extends App{
  // requireing a type to be mixed in
  trait InstrumnetList{
    def plau():Unit
  }

  trait Singer{
    self: InstrumnetList => // whoever implements singer to

    def sing():Unit
  }
}