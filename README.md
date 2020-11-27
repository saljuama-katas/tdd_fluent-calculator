# TDD Kata: Fluent Calculator

Language: Scala

## Description

* All input values should be int.
* The calculator should never throw exceptions.
* The api should guide through the fluent interface, i.e. after the calc method you are never again able to call the calc method.
* It is possible to get the result by implicitly casting it to int.

Here are two examples:
```
int result = Calculator
      .Calc(10)
      .Plus(5)
      .Minus(2)
      .Undo()
      .Redo()
      .Undo()
      .Plus(5)
// -> result = 20
```
```
int result = Calculator
      .Calc(10)
      .Plus(5)
      .Minus(2)
      .Undo()
      .Undo() // -> 10
      .Redo()
      .Redo() // -> 13
      .Redo();
// -> result = 13
```

### Extra credit

* It is possible to call Save anytime which saves the state of the previous operations
* After a save point directly executed Undo or Redo operations have no effect

```
int result = Calculator
      .Calc(10)
      .Plus(5)
      .Minus(2)
      .Save()
      .Undo()
      .Redo()
      .Undo()
      .Plus(5)
// -> result = 18
```
