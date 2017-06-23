package de.htwg.se.menschaergerdichnicht.util

import scala.collection.mutable
import scala.util.{Failure, Try}

/**
  * Created by Anastasia on 06.06.17.
  */
class UndoManager {

  var undoStack: mutable.Stack[Command] = mutable.Stack()
  var redoStack: mutable.Stack[Command] = mutable.Stack()

  def action(com: Command): Try[_] = {
    val result = com.action()
    if (result.isSuccess) {
      undoStack.push(com)
      redoStack.clear()
    }
    result
  }

  def undo(): Try[_] = {
    if (undoStack.nonEmpty) {
      val temp = undoStack.pop()
      val result = temp.undo()
      if (result.isSuccess) {
        redoStack.push(temp)
      }
      result
    } else {
      Failure(new Exception("Not possible to undo right now!"))
    }
  }

  def redo(): Try[_] = {
    if (redoStack.nonEmpty) {
      val temp = redoStack.pop()
      val result = temp.action()
      if (result.isSuccess) {
        undoStack.push(temp)
      }
      result
    } else {
      Failure(new Exception("Not possible to redo right now!"))
    }
  }
}
