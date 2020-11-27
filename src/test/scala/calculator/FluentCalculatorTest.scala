package calculator

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FluentCalculatorTest extends AnyWordSpec with Matchers {

  "The fluent calculator" can {
    import FluentCalculator.calcToInt

    "calc" when {
      Seq(0, 200, -150).foreach(value => s"for value $value" in {
        val result: Int = new FluentCalculator().calc(value)
        result mustBe value
      })
    }

    "plus" when {
      "add a value to the initial value" in {
        val result: Int = new FluentCalculator().calc(10).plus(5)
        result mustBe 15
      }
      "chain multiple calls" in {
        val result: Int = new FluentCalculator()
          .calc(10)
          .plus(5)
          .plus(10)
          .plus(20)
        result mustBe 45
      }
    }

    "minus" when {
      "subtract values to the initial value" in {
        val result: Int = new FluentCalculator().calc(10).minus(5)
        result mustBe 5
      }
      "chain multiple calls" in {
        val result: Int = new FluentCalculator().calc(10).minus(5).plus(10).minus(15)
        result mustBe 0
      }
    }

    "undo" when {
      "removes last operation" in {
        val result: Int = new FluentCalculator().calc(10).plus(20).undo()
        result mustBe 10
      }
      "does nothing when there are no operations" in {
        val result: Int = new FluentCalculator().calc(10).undo()
        result mustBe 10
      }
    }

    "redo" when {
      "adds the last undone operation" in {
        val result: Int = new FluentCalculator().calc(10).plus(10).undo().redo()
        result mustBe 20
      }
      "does nothing when there are no operations" in {
        val result: Int = new FluentCalculator().calc(10).redo()
        result mustBe 10
      }
    }

    "save" when {
      "prevents undo and redo to have effect on operations before save" in {
        val result: Int = new FluentCalculator().calc(10).plus(10).save().undo().redo().plus(5)
        result mustBe 25
      }
    }
  }

}
