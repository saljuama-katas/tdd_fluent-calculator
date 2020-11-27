package calculator

import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FluentCalculatorTest extends AnyWordSpec with Matchers with BeforeAndAfterEach {

  private var calculator: FluentCalculator = _
  override protected def beforeEach(): Unit = {
    calculator = new FluentCalculator()
  }

  "The fluent calculator" when {
    import FluentCalculator.calcToInt

    "calc" can  {
      Seq(0, 200, -150).foreach(value => s"initialize the calculator with value $value" in {
        val result: Int = calculator
          .calc(value)
        result mustBe value
      })
    }

    "plus" can  {
      "add a value to the initial value" in {
        val result: Int = calculator
          .calc(10)
          .plus(5)
        result mustBe 15
      }
      "chain multiple calls" in {
        val result: Int = calculator
          .calc(10)
          .plus(5)
          .plus(10)
          .plus(20)
        result mustBe 45
      }
    }

    "minus" can  {
      "subtract values to the initial value" in {
        val result: Int = calculator
          .calc(10)
          .minus(5)
        result mustBe 5
      }
      "chain multiple calls" in {
        val result: Int = calculator
          .calc(10)
          .minus(5)
          .plus(10)
          .minus(15)
        result mustBe 0
      }
    }

    "undo" can  {
      "discard the last operation" in {
        val result: Int = calculator
          .calc(10)
          .plus(20)
          .undo()
        result mustBe 10
      }
      "does nothing when there are no pending operations" in {
        val result: Int = calculator
          .calc(10)
          .undo()
        result mustBe 10
      }
    }

    "redo" can  {
      "restore the last undone operation" in {
        val result: Int = calculator
          .calc(10)
          .plus(10)
          .undo()
          .redo()
        result mustBe 20
      }
      "does nothing when there are no discarded operations" in {
        val result: Int = calculator
          .calc(10)
          .redo()
        result mustBe 10
      }
    }

    "save" can  {
      "prevents undo and redo to have effect on operations before save" in {
        val result: Int = calculator
          .calc(10)
          .plus(10)
          .save()
          .undo()
          .redo()
          .plus(5)
        result mustBe 25
      }
    }
  }
}
