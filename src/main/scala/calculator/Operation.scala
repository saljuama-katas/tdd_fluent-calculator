package calculator

sealed trait Operation
final case class Plus(value: Int) extends Operation
final case class Minus(value: Int) extends Operation
