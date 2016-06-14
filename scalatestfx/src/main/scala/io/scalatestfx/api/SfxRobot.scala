/*
 * Copyright (c) 2015, Innoave.com
 * All rights reserved.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL INNOAVE.COM OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package io.scalatestfx.api

import java.util.regex.Pattern
import javafx.geometry.Bounds
import javafx.geometry.Point2D
import javafx.geometry.Pos
import javafx.geometry.VerticalDirection
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.MouseButton
import javafx.stage.Screen
import javafx.stage.Window
import scala.concurrent.duration._
import org.hamcrest.Matcher
import org.testfx.api.FxRobot
import org.testfx.service.query.BoundsQuery
import org.testfx.service.query.NodeQuery
import org.testfx.service.query.PointQuery
import org.testfx.util.BoundsQueryUtils
import scala.collection.JavaConversions._
import io.scalatestfx.api.GuavaConversions._
import org.testfx.api.FxRobotInterface
import org.testfx.api.FxRobotContext

class SfxRobotWrapper(
    override val delegate: FxRobotInterface
    ) extends SfxRobot

/**
 * Mixin trait that defines the DSL of TestFX for being used in ScalaTest specficiations.
 *
 * It uses `FxRobot`, the default implementation of the FxRobotInterface provided by TestFX.
 *
 * Current version of the wrapped FxRobot is 4.0.4-alpha
 */
trait SfxRobot {

  val delegate: FxRobotInterface = new FxRobot()

  //=============================================================================================
  //---------------------------------------------------------------------------------------------
  // ADDITIONAL DSL METHODS (NOT AVAILABLE IN TestFX)
  //---------------------------------------------------------------------------------------------
  //=============================================================================================

  def sleep(duration: FiniteDuration): SfxRobot = {
    delegate.sleep(duration.length, duration.unit)
    this
  }

  //=============================================================================================
  //---------------------------------------------------------------------------------------------
  // DSL METHODS DEFINED IN THE API OF TestFX (FxRobotInterface)
  //---------------------------------------------------------------------------------------------
  //=============================================================================================

  //---------------------------------------------------------------------------------------------
  // METHODS FOR WINDOW TARGETING
  //---------------------------------------------------------------------------------------------

  def targetWindow: Window =
    delegate.targetWindow

  def targetWindow(window: Window): SfxRobot = {
    delegate.targetWindow(window)
    this
  }

  def targetWindow(predicate: (Window) => Boolean): SfxRobot = {
    delegate.targetWindow(predicate)
    this
  }

  def targetWindow(windowNumber: Int): SfxRobot = {
    delegate.targetWindow(windowNumber)
    this
  }

  def targetWindow(stageTitleRegex: String): SfxRobot = {
    delegate.targetWindow(stageTitleRegex)
    this
  }

  def targetWindow(stageTitlePattern: Pattern): SfxRobot = {
    delegate.targetWindow(stageTitlePattern)
    this
  }

  def targetWindow(scene: Scene): SfxRobot = {
    delegate.targetWindow(scene)
    this
  }

  def targetWindow(node: Node): SfxRobot = {
    delegate.targetWindow(node)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR WINDOW LOOKUP
  //---------------------------------------------------------------------------------------------

  def listWindows: Seq[Window] =
    delegate.listWindows

  def listTargetWindows: Seq[Window] =
    delegate.listTargetWindows

  def window(predicate: (Window) => Boolean): Window =
    delegate.window(predicate)

  def window(windowIndex: Int): Window =
    delegate.window(windowIndex)

  def window(stageTitleRegex: String): Window =
    delegate.window(stageTitleRegex)

  def window(stageTitlePattern: Pattern): Window =
    delegate.window(stageTitlePattern)

  def window(scene: Scene): Window =
    delegate.window(scene)

  def window(node: Node): Window =
    delegate.window(node)

  //---------------------------------------------------------------------------------------------
  // METHODS FOR NODE LOOKUP
  //---------------------------------------------------------------------------------------------

  def fromAll: NodeQuery =
    delegate.fromAll

  def from(parentNodes: Node*): NodeQuery =
    delegate.from(parentNodes)

  def from(nodeQuery: NodeQuery): NodeQuery =
    delegate.from(nodeQuery)

  def lookup(query: String): NodeQuery =
    delegate.lookup(query)

  def lookup[T <: Node](matcher: Matcher[T]): NodeQuery =
    delegate.lookup(matcher)

  def lookup[T <: Node](predicate: T => Boolean): NodeQuery =
    delegate.lookup(predicate)

  def rootNode(window: Window): Node =
    delegate.rootNode(window)

  def rootNode(scene: Scene): Node =
    delegate.rootNode(scene)

  def rootNode(node: Node): Node =
    delegate.rootNode(node)

  //---------------------------------------------------------------------------------------------
  // METHODS FOR BOUNDS LOCATION
  //---------------------------------------------------------------------------------------------

  def bounds(minX: Double, minY: Double, width: Double, height: Double): BoundsQuery =
    delegate.bounds(minX, minY, width, height)

  def bounds(point: Point2D): BoundsQuery =
    delegate.bounds(point)

  def bounds(bounds: Bounds): BoundsQuery =
    delegate.bounds(bounds)

  def bounds(node: Node): BoundsQuery =
    delegate.bounds(node)

  def bounds(scene: Scene): BoundsQuery =
    delegate.bounds(scene)

  def bounds(window: Window): BoundsQuery =
    delegate.bounds(window)

  def bounds(query: String): BoundsQuery =
    delegate.bounds(query)

  def bounds[T <: Node](matcher: Matcher[T]): BoundsQuery =
    delegate.bounds(matcher)

  def bounds[T <: Node](predicate: T => Boolean): BoundsQuery =
    delegate.bounds(predicate)

  //---------------------------------------------------------------------------------------------
  // METHODS FOR POINT POSITION
  //---------------------------------------------------------------------------------------------

  def targetPos(pointPosition: Pos): SfxRobot = {
    delegate.targetPos(pointPosition)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR POINT LOCATION
  //---------------------------------------------------------------------------------------------

  def point(x: Double, y: Double): PointQuery =
    delegate.point(x, y)

  def point(point: Point2D): PointQuery =
    delegate.point(point)

  def point(bounds: Bounds): PointQuery =
    delegate.point(bounds)

  def point(node: Node): PointQuery =
    delegate.point(node)

  def point(scene: Scene): PointQuery =
    delegate.point(scene)

  def point(window: Window): PointQuery =
    delegate.point(window)

  def point(query: String): PointQuery =
    delegate.point(query)

  def point[T <: Node](matcher: Matcher[T]): PointQuery =
    delegate.point(matcher)

  def point[T <: Node](predicate: T => Boolean): PointQuery =
    delegate.point(predicate)

  //---------------------------------------------------------------------------------------------
  // METHODS FOR POINT OFFSET
  //---------------------------------------------------------------------------------------------

  def offset(point: Point2D, offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(point, offsetX, offsetY)

  def offset(bounds: Bounds, offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(bounds, offsetX, offsetY)

  def offset(node: Node, offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(node, offsetX, offsetY)

  def offset(scene: Scene, offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(scene, offsetX, offsetY)

  def offset(window: Window, offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(window, offsetX, offsetY)

  def offset(query: String, offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(query, offsetX, offsetY)

  def offset[T <: Node](matcher: Matcher[T], offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(matcher, offsetX, offsetY)

  def offset[T <: Node](predicate: T => Boolean, offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(predicate, offsetX, offsetY)

  //---------------------------------------------------------------------------------------------
  // METHODS FOR SCREEN CAPTURING
  //---------------------------------------------------------------------------------------------

  def capture(screen: Screen): Image =
    delegate.capture(screen)

  def capture(bounds: Bounds): Image =
    delegate.capture(bounds)

  def capture(node: Node): Image =
    delegate.capture(node)

  //---------------------------------------------------------------------------------------------
  // METHODS FOR INTERACTION AND INTERRUPTION
  //---------------------------------------------------------------------------------------------

  //TODO implement methods for Interaction and Interruption

  //---------------------------------------------------------------------------------------------
  // METHODS FOR SLEEPING
  //---------------------------------------------------------------------------------------------

  def sleep(length: Long, unit: TimeUnit): SfxRobot = {
    delegate.sleep(length, unit)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR TYPING
  //---------------------------------------------------------------------------------------------

  def push(combination: KeyCode*): SfxRobot = {
    delegate.push(combination: _*)
    this
  }

  def push(combination: KeyCodeCombination): SfxRobot = {
    delegate.push(combination)
    this
  }

  /**
   * Note: The methods `type` have been renamed to `typing` due to `type` is a reserved keyword
   * in Scala and one would have to type the method name with surrounding back-ticks which
   * is uncomfortable.
   */
  def typing(keys: KeyCode*): SfxRobot = {
    delegate.`type`(keys: _*)
    this
  }

  /**
   * Note: The methods `type` have been renamed to `typing` due to `type` is a reserved keyword
   * in Scala and one would have to type the method name with surrounding back-ticks which is
   * uncomfortable.
   */
  def typing(key: KeyCode, times: Int): SfxRobot = {
    delegate.`type`(key, times)
    this
  }

  def eraseText(amount: Int): SfxRobot = {
    delegate.eraseText(amount)
    this
  }

  def closeCurrentWindow(): SfxRobot = {
    push(KeyCode.ALT, KeyCode.F4).sleep(100.millis)
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR WRITING
  //---------------------------------------------------------------------------------------------

  def write(character: Char): SfxRobot = {
    delegate.write(character)
    this
  }

  def write(text: String): SfxRobot = {
    delegate.write(text)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR KEYBOARD
  //---------------------------------------------------------------------------------------------

  /**
   * Note: the original methods for pressing keys have been renamed from press to pressKey.
   * This became necessary because in Scala the signatures of press(KeyCode*) and
   * press(MouseButton*) are equal.
   */
  def pressKey(keys: KeyCode*): SfxRobot = {
    delegate.press(keys: _*)
    this
  }

  /**
   * Note: the original methods for releasing keys have been renamed from release to releaseKey.
   * This became necessary because in Scala the signatures of release(KeyCode*) and
   * release(MouseButton*) are equal.
   */
  def releaseKey(keys: KeyCode*): SfxRobot = {
    delegate.release(keys: _*)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR MOUSE
  //---------------------------------------------------------------------------------------------

  /**
   * Presses and holds mouse buttons.
   *
   * @param buttons mouse buttons to press, defaults to primary mouse button.
   */
  def press(buttons: MouseButton*): SfxRobot = {
    delegate.press(buttons: _*)
    this
  }

  /**
   * Releases pressed mouse buttons.
   *
   * @param buttons mouse buttons to release, defaults to all pressed mouse buttons.
   */
  def release(buttons: MouseButton*): SfxRobot = {
    delegate.release(buttons: _*)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR CLICKING
  //---------------------------------------------------------------------------------------------

  def clickOn(buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(buttons: _*)
    this
  }

  def clickOn(pointQuery: PointQuery, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(pointQuery, buttons: _*)
    this
  }

  def doubleClickOn(buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(buttons: _*)
    this
  }

  def doubleClickOn(pointQuery: PointQuery, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(pointQuery, buttons: _*)
    this
  }

  def clickOn(x: Double, y: Double, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(x, y, buttons: _*)
    this
  }

  def clickOn(point: Point2D, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(point, buttons: _*)
    this
  }

  def clickOn(bounds: Bounds, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(bounds, buttons: _*)
    this
  }

  def clickOn(node: Node, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(node, buttons: _*)
    this
  }

  def clickOn(scene: Scene, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(scene, buttons: _*)
    this
  }

  def clickOn(window: Window, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(window, buttons: _*)
    this
  }

  def clickOn(query: String, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(query, buttons: _*)
    this
  }

  def clickOn[T <: Node](matcher: Matcher[T], buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(matcher, buttons: _*)
    this
  }

  def clickOn[T <: Node](predicate: T => Boolean, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(predicate, buttons: _*)
    this
  }

  def doubleClickOn(x: Double, y: Double, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(x, y, buttons: _*)
    this
  }

  def doubleClickOn(point: Point2D, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(point, buttons: _*)
    this
  }

  def doubleClickOn(bounds: Bounds, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(bounds, buttons: _*)
    this
  }

  def doubleClickOn(node: Node, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(node, buttons: _*)
    this
  }

  def doubleClickOn(scene: Scene, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(scene, buttons: _*)
    this
  }

  def doubleClickOn(window: Window, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(window, buttons: _*)
    this
  }

  def doubleClickOn(query: String, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(query, buttons: _*)
    this
  }

  def doubleClickOn[T <: Node](matcher: Matcher[T], buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(matcher, buttons: _*)
    this
  }

  def doubleClickOn[T <: Node](predicate: T => Boolean, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(predicate, buttons: _*)
    this
  }

  def rightClickOn(): SfxRobot = {
    delegate.clickOn()
    this
  }

  def rightClickOn(pointQuery: PointQuery): SfxRobot = {
    delegate.clickOn(pointQuery)
    this
  }

  def rightClickOn(x: Double, y: Double): SfxRobot = {
    delegate.rightClickOn(x, y)
    this
  }

  def rightClickOn(point: Point2D): SfxRobot = {
    delegate.rightClickOn(point)
    this
  }

  def rightClickOn(bounds: Bounds): SfxRobot = {
    delegate.rightClickOn(bounds)
    this
  }

  def rightClickOn(node: Node): SfxRobot = {
    delegate.rightClickOn(node)
    this
  }

  def rightClickOn(scene: Scene): SfxRobot = {
    delegate.rightClickOn(scene)
    this
  }

  def rightClickOn(window: Window): SfxRobot = {
    delegate.rightClickOn(window)
    this
  }

  def rightClickOn(query: String): SfxRobot = {
    delegate.rightClickOn(query)
    this
  }

  def rightClickOn[T <: Node](matcher: Matcher[T]): SfxRobot = {
    delegate.rightClickOn(matcher)
    this
  }

  def rightClickOn[T <: Node](predicate: T => Boolean): SfxRobot = {
    delegate.rightClickOn(predicate)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR DRAGGING
  //---------------------------------------------------------------------------------------------

  def drag(buttons: MouseButton*): SfxRobot = {
    delegate.drag(buttons: _*)
    this
  }

  def drag(pointQuery: PointQuery, buttons: MouseButton*): SfxRobot = {
    delegate.drag(pointQuery, buttons: _*)
    this
  }

  def drag(x: Double, y: Double, buttons: MouseButton*): SfxRobot = {
    delegate.drag(x, y, buttons: _*)
    this
  }

  def drag(point: Point2D, buttons: MouseButton*): SfxRobot = {
    delegate.drag(point, buttons: _*)
    this
  }

  def drag(bounds: Bounds, buttons: MouseButton*): SfxRobot = {
    delegate.drag(bounds, buttons: _*)
    this
  }

  def drag(node: Node, buttons: MouseButton*): SfxRobot = {
    delegate.drag(node, buttons: _*)
    this
  }

  def drag(scene: Scene, buttons: MouseButton*): SfxRobot = {
    delegate.drag(scene, buttons: _*)
    this
  }

  def drag(window: Window, buttons: MouseButton*): SfxRobot = {
    delegate.drag(window, buttons: _*)
    this
  }

  def drag(query: String, buttons: MouseButton*): SfxRobot = {
    delegate.drag(query, buttons: _*)
    this
  }

  def drag[T <: Node](matcher: Matcher[T], buttons: MouseButton*): SfxRobot = {
    delegate.drag(matcher, buttons: _*)
    this
  }

  def drag[T <: Node](predicate: T => Boolean, buttons: MouseButton*): SfxRobot = {
    delegate.drag(predicate, buttons: _*)
    this
  }

  def drop(): SfxRobot = {
    delegate.drop()
    this
  }

  def dropTo(pointQuery: PointQuery): SfxRobot = {
    delegate.dropTo(pointQuery)
    this
  }

  def dropBy(x: Double, y: Double): SfxRobot = {
    delegate.dropBy(x, y)
    this
  }

  def dropTo(x: Double, y: Double): SfxRobot = {
    delegate.dropTo(x, y)
    this
  }

  def dropTo(point: Point2D): SfxRobot = {
    delegate.dropTo(point)
    this
  }

  def dropTo(bounds: Bounds): SfxRobot = {
    delegate.dropTo(bounds)
    this
  }

  def dropTo(node: Node): SfxRobot = {
    delegate.dropTo(node)
    this
  }

  def dropTo(scene: Scene): SfxRobot = {
    delegate.dropTo(scene)
    this
  }

  def dropTo(window: Window): SfxRobot = {
    delegate.dropTo(window)
    this
  }

  def dropTo(query: String): SfxRobot = {
    delegate.dropTo(query)
    this
  }

  def dropTo[T <: Node](matcher: Matcher[T]): SfxRobot = {
    delegate.dropTo(matcher)
    this
  }

  def dropTo[T <: Node](predicate: T => Boolean): SfxRobot = {
    delegate.dropTo(predicate)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR MOVING
  //---------------------------------------------------------------------------------------------

  def moveTo(pointQuery: PointQuery): SfxRobot = {
    delegate.moveTo(pointQuery)
    this
  }

  def moveBy(x: Double, y: Double): SfxRobot = {
    delegate.moveBy(x, y)
    this
  }

  def moveTo(x: Double, y: Double): SfxRobot = {
    delegate.moveTo(x, y)
    this
  }

  def moveTo(point: Point2D): SfxRobot = {
    delegate.moveTo(point)
    this
  }

  def moveTo(bounds: Bounds): SfxRobot = {
    delegate.moveTo(bounds)
    this
  }

  def moveTo(node: Node): SfxRobot = {
    delegate.moveTo(node)
    this
  }

  def moveTo(scene: Scene): SfxRobot = {
    delegate.moveTo(scene)
    this
  }

  def moveTo(window: Window): SfxRobot = {
    delegate.moveTo(window)
    this
  }

  def moveTo(query: String): SfxRobot = {
    delegate.moveTo(query)
    this
  }

  def moveTo[T <: Node](matcher: Matcher[T]): SfxRobot = {
    delegate.moveTo(matcher)
    this
  }

  def moveTo[T <: Node](predicate: T => Boolean): SfxRobot = {
    delegate.moveTo(predicate)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR SCROLLING
  //---------------------------------------------------------------------------------------------

  def scroll(amount: Int, direction: VerticalDirection): SfxRobot = {
    delegate.scroll(amount, direction)
    this
  }

  def scroll(direction: VerticalDirection): SfxRobot = {
    scroll(1, direction)
  }

}
