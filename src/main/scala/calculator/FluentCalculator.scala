package calculator

class FluentCalculator {
  private var value = 0
  private var operations: Seq[Operation] = Seq()
  private var undone: Seq[Operation] = Seq()

  def calc(initial: Int): FluentCalculator = {
    value = initial
    this
  }

  def plus(value: Int): FluentCalculator = {
    operations +:= Plus(value)
    this
  }

  def minus(value: Int): FluentCalculator = {
    operations +:= Minus(value)
    this
  }

  def undo(): FluentCalculator = {
    if (operations.nonEmpty) {
      undone +:= operations.head
      operations = operations.tail
    }
    this
  }

  def redo(): FluentCalculator = {
    if (undone.nonEmpty) {
      operations +:= undone.head
      undone = undone.tail
    }
    this
  }

  def save(): FluentCalculator = {
    value = processPendingOperations()
    operations = Seq()
    undone = Seq()
    this
  }

  private def processPendingOperations(): Int = {
    operations.foldLeft(value)((acc, op) => op match {
      case Plus(x) => acc + x
      case Minus(x) => acc - x
    })
  }
}

object FluentCalculator {

  import scala.language.implicitConversions

  implicit def calcToInt(calculator: FluentCalculator): Int = calculator.processPendingOperations()
}
