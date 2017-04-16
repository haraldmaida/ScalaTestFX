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

import java.net.URL
import java.nio.file.Path
import java.util.regex.Pattern
import javafx.geometry.Bounds
import javafx.geometry.HorizontalDirection
import javafx.geometry.Point2D
import javafx.geometry.Pos
import javafx.geometry.Rectangle2D
import javafx.geometry.VerticalDirection
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.MouseButton
import javafx.stage.Window
import org.hamcrest.Matcher
import org.testfx.api.FxRobot
import org.testfx.api.FxRobotInterface
import org.testfx.service.query.BoundsQuery
import org.testfx.service.query.NodeQuery
import org.testfx.service.query.PointQuery
import scala.collection.JavaConverters._
import scala.concurrent.duration._
import io.scalatestfx.api.GuavaConversions._
import org.testfx.service.support.Capture
import org.testfx.robot.Motion

/** Mixin trait that defines the DSL of TestFX for being used in ScalaTest
 *  specficiations.
 *
 *  It uses `FxRobot`, the default implementation of the `FxRobotInterface`
 *  provided by TestFX.
 *
 *  Current version of the wrapped FxRobot is 4.0.6-alpha
 */
trait SfxRobot extends SfxRobotDsl {
  val delegate = new FxRobot()
}

/**
 * Definition of the DSL used to operate with the JavaFX application under test.
 */
trait SfxRobotDsl {

  def delegate: FxRobotInterface

  //=============================================================================================
  //---------------------------------------------------------------------------------------------
  // ADDITIONAL DSL METHODS (NOT AVAILABLE IN TestFX)
  //---------------------------------------------------------------------------------------------
  //=============================================================================================

  def closeCurrentWindow(): SfxRobotDsl = {
    push(KeyCode.ALT, KeyCode.F4).sleep(100.millis)
  }

  def sleep(duration: FiniteDuration): SfxRobotDsl = {
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

  def targetWindow(window: Window): SfxRobotDsl = {
    delegate.targetWindow(window)
    this
  }

  def targetWindow(predicate: (Window) => Boolean): SfxRobotDsl = {
    delegate.targetWindow(predicate)
    this
  }

  def targetWindow(windowNumber: Int): SfxRobotDsl = {
    delegate.targetWindow(windowNumber)
    this
  }

  def targetWindow(stageTitleRegex: String): SfxRobotDsl = {
    delegate.targetWindow(stageTitleRegex)
    this
  }

  def targetWindow(stageTitlePattern: Pattern): SfxRobotDsl = {
    delegate.targetWindow(stageTitlePattern)
    this
  }

  def targetWindow(scene: Scene): SfxRobotDsl = {
    delegate.targetWindow(scene)
    this
  }

  def targetWindow(node: Node): SfxRobotDsl = {
    delegate.targetWindow(node)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR WINDOW LOOKUP
  //---------------------------------------------------------------------------------------------

  def listWindows: Seq[Window] =
    delegate.listWindows.asScala

  def listTargetWindows: Seq[Window] =
    delegate.listTargetWindows.asScala

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
    delegate.from(parentNodes: _*)

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

  def targetPos(pointPosition: Pos): SfxRobotDsl = {
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

  def capture(screenRegion: Rectangle2D): Capture =
    delegate.capture(screenRegion)

  def capture(bounds: Bounds): Capture =
    delegate.capture(bounds)

  def capture(node: Node): Capture =
    delegate.capture(node)

  def capture(image: Image): Capture =
    delegate.capture(image)

  def capture(path: Path): Capture =
    delegate.capture(path)

  def capture(url: URL): Capture =
    delegate.capture(url)

  //---------------------------------------------------------------------------------------------
  // METHODS FOR INTERACTION AND INTERRUPTION
  //---------------------------------------------------------------------------------------------

  //TODO implement methods for Interaction and Interruption

  //---------------------------------------------------------------------------------------------
  // METHODS FOR SLEEPING
  //---------------------------------------------------------------------------------------------

  def sleep(milliseconds: Long): SfxRobotDsl = {
    delegate.sleep(milliseconds)
    this
  }

  def sleep(length: Long, unit: TimeUnit): SfxRobotDsl = {
    delegate.sleep(length, unit)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR CLICKING
  //---------------------------------------------------------------------------------------------

  def clickOn(buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(buttons: _*)
    this
  }

  def clickOn(pointQuery: PointQuery, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(pointQuery, buttons: _*)
    this
  }

  def clickOn(pointQuery: PointQuery, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(pointQuery, motion, buttons: _*)
    this
  }

  def doubleClickOn(buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(buttons: _*)
    this
  }

  def doubleClickOn(pointQuery: PointQuery, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(pointQuery, buttons: _*)
    this
  }

  def doubleClickOn(pointQuery: PointQuery, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(pointQuery, motion, buttons: _*)
    this
  }

  def clickOn(x: Double, y: Double, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(x, y, buttons: _*)
    this
  }

  def clickOn(point: Point2D, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(point, buttons: _*)
    this
  }

  def clickOn(point: Point2D, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(point, motion, buttons: _*)
    this
  }

  def clickOn(bounds: Bounds, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(bounds, buttons: _*)
    this
  }

  def clickOn(bounds: Bounds, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(bounds, motion, buttons: _*)
    this
  }

  def clickOn(node: Node, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(node, buttons: _*)
    this
  }

  def clickOn(node: Node, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(node, motion, buttons: _*)
    this
  }

  def clickOn(scene: Scene, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(scene, buttons: _*)
    this
  }

  def clickOn(scene: Scene, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(scene, motion, buttons: _*)
    this
  }

  def clickOn(window: Window, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(window, buttons: _*)
    this
  }

  def clickOn(window: Window, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(window, motion, buttons: _*)
    this
  }

  def clickOn(query: String, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(query, buttons: _*)
    this
  }

  def clickOn(query: String, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(query, motion, buttons: _*)
    this
  }

  def clickOn[T <: Node](matcher: Matcher[T], buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(matcher, buttons: _*)
    this
  }

  def clickOn[T <: Node](matcher: Matcher[T], motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(matcher, motion, buttons: _*)
    this
  }

  def clickOn[T <: Node](predicate: T => Boolean, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(predicate, buttons: _*)
    this
  }

  def clickOn[T <: Node](predicate: T => Boolean, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.clickOn(predicate, motion, buttons: _*)
    this
  }

  def doubleClickOn(x: Double, y: Double, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(x, y, buttons: _*)
    this
  }

  def doubleClickOn(x: Double, y: Double, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(x, y, motion, buttons: _*)
    this
  }

  def doubleClickOn(point: Point2D, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(point, buttons: _*)
    this
  }

  def doubleClickOn(point: Point2D, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(point, motion, buttons: _*)
    this
  }

  def doubleClickOn(bounds: Bounds, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(bounds, buttons: _*)
    this
  }

  def doubleClickOn(bounds: Bounds, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(bounds, motion, buttons: _*)
    this
  }

  def doubleClickOn(node: Node, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(node, buttons: _*)
    this
  }

  def doubleClickOn(node: Node, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(node, motion, buttons: _*)
    this
  }

  def doubleClickOn(scene: Scene, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(scene, buttons: _*)
    this
  }

  def doubleClickOn(scene: Scene, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(scene, motion, buttons: _*)
    this
  }

  def doubleClickOn(window: Window, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(window, buttons: _*)
    this
  }

  def doubleClickOn(window: Window, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(window, motion, buttons: _*)
    this
  }

  def doubleClickOn(query: String, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(query, buttons: _*)
    this
  }

  def doubleClickOn(query: String, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(query, motion, buttons: _*)
    this
  }

  def doubleClickOn[T <: Node](matcher: Matcher[T], buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(matcher, buttons: _*)
    this
  }

  def doubleClickOn[T <: Node](matcher: Matcher[T], motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(matcher, motion, buttons: _*)
    this
  }

  def doubleClickOn[T <: Node](predicate: T => Boolean, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(predicate, buttons: _*)
    this
  }

  def doubleClickOn[T <: Node](predicate: T => Boolean, motion: Motion, buttons: MouseButton*): SfxRobotDsl = {
    delegate.doubleClickOn(predicate, motion, buttons: _*)
    this
  }

  def rightClickOn(): SfxRobotDsl = {
    delegate.clickOn()
    this
  }

  def rightClickOn(pointQuery: PointQuery): SfxRobotDsl = {
    delegate.clickOn(pointQuery)
    this
  }

  def rightClickOn(pointQuery: PointQuery, motion: Motion): SfxRobotDsl = {
    delegate.clickOn(pointQuery, motion)
    this
  }

  def rightClickOn(x: Double, y: Double): SfxRobotDsl = {
    delegate.rightClickOn(x, y)
    this
  }

  def rightClickOn(x: Double, y: Double, motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(x, y, motion)
    this
  }

  def rightClickOn(point: Point2D): SfxRobotDsl = {
    delegate.rightClickOn(point)
    this
  }

  def rightClickOn(point: Point2D, motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(point, motion)
    this
  }

  def rightClickOn(bounds: Bounds): SfxRobotDsl = {
    delegate.rightClickOn(bounds)
    this
  }

  def rightClickOn(bounds: Bounds, motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(bounds, motion)
    this
  }

  def rightClickOn(node: Node): SfxRobotDsl = {
    delegate.rightClickOn(node)
    this
  }

  def rightClickOn(node: Node, motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(node, motion)
    this
  }

  def rightClickOn(scene: Scene): SfxRobotDsl = {
    delegate.rightClickOn(scene)
    this
  }

  def rightClickOn(scene: Scene, motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(scene, motion)
    this
  }

  def rightClickOn(window: Window): SfxRobotDsl = {
    delegate.rightClickOn(window)
    this
  }

  def rightClickOn(window: Window, motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(window, motion)
    this
  }

  def rightClickOn(query: String): SfxRobotDsl = {
    delegate.rightClickOn(query)
    this
  }

  def rightClickOn(query: String, motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(query, motion)
    this
  }

  def rightClickOn[T <: Node](matcher: Matcher[T]): SfxRobotDsl = {
    delegate.rightClickOn(matcher)
    this
  }

  def rightClickOn[T <: Node](matcher: Matcher[T], motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(matcher, motion)
    this
  }

  def rightClickOn[T <: Node](predicate: T => Boolean): SfxRobotDsl = {
    delegate.rightClickOn(predicate)
    this
  }

  def rightClickOn[T <: Node](predicate: T => Boolean, motion: Motion): SfxRobotDsl = {
    delegate.rightClickOn(predicate, motion)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR DRAGGING
  //---------------------------------------------------------------------------------------------

  def drag(buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(buttons: _*)
    this
  }

  def drag(pointQuery: PointQuery, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(pointQuery, buttons: _*)
    this
  }

  def drag(x: Double, y: Double, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(x, y, buttons: _*)
    this
  }

  def drag(point: Point2D, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(point, buttons: _*)
    this
  }

  def drag(bounds: Bounds, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(bounds, buttons: _*)
    this
  }

  def drag(node: Node, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(node, buttons: _*)
    this
  }

  def drag(scene: Scene, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(scene, buttons: _*)
    this
  }

  def drag(window: Window, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(window, buttons: _*)
    this
  }

  def drag(query: String, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(query, buttons: _*)
    this
  }

  def drag[T <: Node](matcher: Matcher[T], buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(matcher, buttons: _*)
    this
  }

  def drag[T <: Node](predicate: T => Boolean, buttons: MouseButton*): SfxRobotDsl = {
    delegate.drag(predicate, buttons: _*)
    this
  }

  def drop(): SfxRobotDsl = {
    delegate.drop()
    this
  }

  def dropBy(x: Double, y: Double): SfxRobotDsl = {
    delegate.dropBy(x, y)
    this
  }

  def dropTo(x: Double, y: Double): SfxRobotDsl = {
    delegate.dropTo(x, y)
    this
  }

  def dropTo(pointQuery: PointQuery): SfxRobotDsl = {
    delegate.dropTo(pointQuery)
    this
  }

  def dropTo(point: Point2D): SfxRobotDsl = {
    delegate.dropTo(point)
    this
  }

  def dropTo(bounds: Bounds): SfxRobotDsl = {
    delegate.dropTo(bounds)
    this
  }

  def dropTo(node: Node): SfxRobotDsl = {
    delegate.dropTo(node)
    this
  }

  def dropTo(scene: Scene): SfxRobotDsl = {
    delegate.dropTo(scene)
    this
  }

  def dropTo(window: Window): SfxRobotDsl = {
    delegate.dropTo(window)
    this
  }

  def dropTo(query: String): SfxRobotDsl = {
    delegate.dropTo(query)
    this
  }

  def dropTo[T <: Node](matcher: Matcher[T]): SfxRobotDsl = {
    delegate.dropTo(matcher)
    this
  }

  def dropTo[T <: Node](predicate: T => Boolean): SfxRobotDsl = {
    delegate.dropTo(predicate)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR KEYBOARD
  //---------------------------------------------------------------------------------------------

  /**
   * Note: the original methods for pressing keys have been renamed from press
   * to pressKey. This became necessary because in Scala the signatures of
   * `press(KeyCode*)` and `press(MouseButton*)` are equal.
   */
  def pressKey(keys: KeyCode*): SfxRobotDsl = {
    delegate.press(keys: _*)
    this
  }

  /**
   * Note: the original methods for releasing keys have been renamed from
   * release to releaseKey. This became necessary because in Scala the
   * signatures of `release(KeyCode*)` and `release(MouseButton*)` are equal.
   */
  def releaseKey(keys: KeyCode*): SfxRobotDsl = {
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
  def press(buttons: MouseButton*): SfxRobotDsl = {
    delegate.press(buttons: _*)
    this
  }

  /**
   * Releases pressed mouse buttons.
   *
   * @param buttons mouse buttons to release, defaults to all pressed mouse buttons.
   */
  def release(buttons: MouseButton*): SfxRobotDsl = {
    delegate.release(buttons: _*)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR MOVING
  //---------------------------------------------------------------------------------------------

  def moveTo(pointQuery: PointQuery): SfxRobotDsl = {
    delegate.moveTo(pointQuery)
    this
  }

  def moveTo(pointQuery: PointQuery, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(pointQuery, motion)
    this
  }

  def moveBy(x: Double, y: Double): SfxRobotDsl = {
    delegate.moveBy(x, y)
    this
  }

  def moveBy(x: Double, y: Double, motion: Motion): SfxRobotDsl = {
    delegate.moveBy(x, y, motion)
    this
  }

  def moveTo(x: Double, y: Double): SfxRobotDsl = {
    delegate.moveTo(x, y)
    this
  }

  def moveTo(x: Double, y: Double, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(x, y, motion)
    this
  }

  def moveTo(point: Point2D): SfxRobotDsl = {
    delegate.moveTo(point)
    this
  }

  def moveTo(point: Point2D, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(point, motion)
    this
  }

  def moveTo(bounds: Bounds): SfxRobotDsl = {
    delegate.moveTo(bounds)
    this
  }

  def moveTo(bounds: Bounds, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(bounds, motion)
    this
  }

  def moveTo(node: Node): SfxRobotDsl = {
    delegate.moveTo(node)
    this
  }

  def moveTo(node: Node, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(node, motion)
    this
  }

  def moveTo(scene: Scene): SfxRobotDsl = {
    delegate.moveTo(scene)
    this
  }

  def moveTo(scene: Scene, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(scene, motion)
    this
  }

  def moveTo(window: Window): SfxRobotDsl = {
    delegate.moveTo(window)
    this
  }

  def moveTo(window: Window, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(window, motion)
    this
  }

  def moveTo(query: String): SfxRobotDsl = {
    delegate.moveTo(query)
    this
  }

  def moveTo(query: String, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(query, motion)
    this
  }

  def moveTo[T <: Node](matcher: Matcher[T]): SfxRobotDsl = {
    delegate.moveTo(matcher)
    this
  }

  def moveTo[T <: Node](matcher: Matcher[T], motion: Motion): SfxRobotDsl = {
    delegate.moveTo(matcher, motion)
    this
  }

  def moveTo[T <: Node](predicate: T => Boolean): SfxRobotDsl = {
    delegate.moveTo(predicate)
    this
  }

  def moveTo[T <: Node](predicate: T => Boolean, motion: Motion): SfxRobotDsl = {
    delegate.moveTo(predicate, motion)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR SCROLLING
  //---------------------------------------------------------------------------------------------

  def scroll(amount: Int): SfxRobotDsl = {
    delegate.scroll(amount)
    this
  }

  def scroll(amount: Int, direction: VerticalDirection): SfxRobotDsl = {
    delegate.scroll(amount, direction)
    this
  }

  def scroll(direction: VerticalDirection): SfxRobotDsl = {
    scroll(direction)
  }

  def scroll(amount: Int, direction: HorizontalDirection): SfxRobotDsl = {
    delegate.scroll(amount, direction)
    this
  }

  def scroll(direction: HorizontalDirection): SfxRobotDsl = {
    scroll(direction)
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR TYPING
  //---------------------------------------------------------------------------------------------

  def push(combination: KeyCode*): SfxRobotDsl = {
    delegate.push(combination: _*)
    this
  }

  def push(combination: KeyCodeCombination): SfxRobotDsl = {
    delegate.push(combination)
    this
  }

  /**
   * Note: The methods `type` have been renamed to `typing` due to `type` is a reserved keyword
   * in Scala and one would have to type the method name with surrounding back-ticks which
   * is uncomfortable.
   */
  def typing(keys: KeyCode*): SfxRobotDsl = {
    delegate.`type`(keys: _*)
    this
  }

  /**
   * Note: The methods `type` have been renamed to `typing` due to `type` is a reserved keyword
   * in Scala and one would have to type the method name with surrounding back-ticks which is
   * uncomfortable.
   */
  def typing(key: KeyCode, times: Int): SfxRobotDsl = {
    delegate.`type`(key, times)
    this
  }

  def eraseText(amount: Int): SfxRobotDsl = {
    delegate.eraseText(amount)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR WRITING
  //---------------------------------------------------------------------------------------------

  def write(character: Char): SfxRobotDsl = {
    delegate.write(character)
    this
  }

  def write(text: String): SfxRobotDsl = {
    delegate.write(text)
    this
  }

}
