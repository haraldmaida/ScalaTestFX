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
import scala.concurrent.duration._
import javafx.{geometry => jfxge}
import javafx.{scene => jfxsc}
import javafx.{stage => jfxst}
import javafx.scene.{input => jfxin}
import org.testfx.{api => jtfx}
import org.testfx.service.{query => jtfxqu}
import org.testfx.service.query.BoundsQuery
import org.testfx.service.query.NodeQuery
import org.testfx.service.query.PointQuery
import org.testfx.util.BoundsQueryUtils
import org.hamcrest.Matcher
import com.google.common.base.Predicate
import scalafx.geometry.Bounds
import scalafx.geometry.Pos
import scalafx.geometry.Point2D
import scalafx.geometry.VerticalDirection
import scalafx.scene.Node
import scalafx.scene.Scene
import scalafx.stage.Screen
import scalafx.stage.Window
import scalafx.scene.image.Image
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyCodeCombination
import scalafx.scene.input.MouseButton
import scala.collection.JavaConversions._
import scalafx.Includes._
import io.scalatestfx.api.Includes._

/*
object SfxRobot {
  implicit def sfxRobot2jfx(v: jtfx.FxRobot): SfxRobot =
    v.delegate
}
*/

trait SfxRobot {

  val delegate = new jtfx.FxRobot()

  def robotContext(): jtfx.FxRobotContext = delegate.robotContext()


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
    delegate.targetWindow(
        new Predicate[jfxst.Window]() {
          def apply(window: jfxst.Window): Boolean =
            predicate(window)
        })
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
    delegate.window(new Predicate[jfxst.Window]() {
      override def apply(window: jfxst.Window): Boolean =
        predicate(window)
    })

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

  //TODO maybe we need our own ScalaTestFX NodeQuery

  def fromAll: NodeQuery =
    delegate.fromAll

  def from(parentNodes: Node*): NodeQuery =
    delegate.from(parentNodes)

  def from(nodeQuery: NodeQuery): NodeQuery =
    delegate.from(nodeQuery)

  def lookup(query: String): NodeQuery =
    delegate.lookup(query)

  //TODO replace hamcrest matcher by scalatest matcher (maybe)
  def lookup[J <: jfxsc.Node](matcher: Matcher[J]): NodeQuery =
    delegate.lookup(matcher)

  //TODO find generic solution for predicate with scalafx.scene.Node
  def lookup[S <: Node, J <: jfxsc.Node](predicate: (J) => Boolean): NodeQuery =
    delegate.lookup(new Predicate[J]() {
      override def apply(value: J): Boolean =
        predicate(value)
    })

  def rootNode(window: Window): Node =
    delegate.rootNode(window)

  def rootNode(scene: Scene): Node =
    delegate.rootNode(scene)

  def rootNode(node: Node): Node =
    delegate.rootNode(node)

  //---------------------------------------------------------------------------------------------
  // METHODS FOR BOUNDS LOCATION
  //---------------------------------------------------------------------------------------------

  //TODO maybe we need our own ScalaTestFX BoundsQuery

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

  //TODO replace hamcrest matcher by scalatest matcher (maybe)
  def bounds[J <: jfxsc.Node](matcher: Matcher[J]): BoundsQuery =
    delegate.bounds(matcher)

  //TODO find generic solution for predicate with scalafx.scene.Node
  def bounds[S <: Node, J <: jfxsc.Node](predicate: (J) => Boolean): BoundsQuery =
    delegate.bounds(new Predicate[J]() {
      override def apply(value: J): Boolean =
        predicate(value)
    })

  //---------------------------------------------------------------------------------------------
  // METHODS FOR POINT POSITION
  //---------------------------------------------------------------------------------------------

  def targetPos(pointPosition: Pos): SfxRobot = {
    delegate.robotContext.setPointPosition(pointPosition)
    this
  }

  //---------------------------------------------------------------------------------------------
  // METHODS FOR POINT LOCATION
  //---------------------------------------------------------------------------------------------

  //TODO maybe we need our own ScalaTestFX PointQuery

  def point(x: Double, y: Double): PointQuery =
    delegate.point(x, y)

  def point(point: Point2D): PointQuery =
    delegate.point(point.delegate)

  def point(bounds: Bounds): PointQuery =
    delegate.point(bounds.delegate)

  def point(node: Node): PointQuery =
    delegate.point(node.delegate)

  def point(scene: Scene): PointQuery =
    delegate.point(scene.delegate)

  def point(window: Window): PointQuery =
    delegate.point(window.delegate)

  def point(query: String): PointQuery =
    delegate.point(query)

  //TODO replace hamcrest matcher by scalatest matcher (maybe)
  def point[J <: jfxsc.Node](matcher: Matcher[J]): PointQuery =
    delegate.point(matcher)

  //TODO find generic solution for predicate with scalafx.scene.Node
  def point[S <: Node, J <: jfxsc.Node](predicate: (J) => Boolean): PointQuery =
    delegate.point(new Predicate[J]() {
      override def apply(value: J): Boolean =
        predicate(value)
    })

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

  def offset[J <: jfxsc.Node](matcher: Matcher[J], offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(matcher, offsetX, offsetY)

  //TODO find generic solution for predicate with scalafx.scene.Node
  def offset[S <: Node, J <: jfxsc.Node](predicate: (J) => Boolean, offsetX: Double, offsetY: Double): PointQuery =
    delegate.offset(new Predicate[J]() {
      override def apply(value: J): Boolean =
        predicate(value)
    }, offsetX, offsetY)

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
  // IMPLEMENTATION OF TYPE ROBOT
  //---------------------------------------------------------------------------------------------

  def push(combination: KeyCode*): SfxRobot = {
    delegate.push(sfxKeyCodeSeq2jfx(combination): _*)
    this
  }

  def push(combination: KeyCodeCombination): SfxRobot = {
    delegate.push(combination)
    this
  }

  def typing(keyCodes: KeyCode*): SfxRobot = {
    delegate.`type`(keyCodes: _*)
    this
  }

  def typing(keyCode: KeyCode, times: Int): SfxRobot = {
    delegate.`type`(keyCode, times)
    this
  }

  def eraseText(amount: Int): SfxRobot = {
    delegate.eraseText(amount)
    this
  }

  def closeCurrentWindow(): SfxRobot = {
    push(KeyCode.Alt, KeyCode.F4).sleep(100.millis)
  }

  //---------------------------------------------------------------------------------------------
  // IMPLEMENTATION OF WRITE ROBOT
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
  // IMPLEMENTATION OF SLEEP ROBOT
  //---------------------------------------------------------------------------------------------

  def sleep(length: Long, unit: TimeUnit): SfxRobot = {
    delegate.sleep(length, unit)
    this
  }

  //---------------------------------------------------------------------------------------------
  // IMPLEMENTATION OF SCROLL ROBOT
  //---------------------------------------------------------------------------------------------

  def scroll(amount: Int, direction: VerticalDirection): SfxRobot = {
    delegate.scroll(amount, direction)
    this
  }

  def scroll(direction: VerticalDirection): SfxRobot = {
    scroll(1, direction)
  }

  //---------------------------------------------------------------------------------------------
  // IMPLEMENTATION OF KEYBOARD ROBOT
  //---------------------------------------------------------------------------------------------

  /**
   * Note: the original methods for pressing keys have been renamed from press to pressKey.
   * This became necessary because in Scala the signatures of press(KeyCode*) and
   * press(MouseButton*) are equal.
   */
  def pressKey(keys: KeyCode*): SfxRobot = {
    delegate.press(sfxKeyCodeSeq2jfx(keys): _*)
    this
  }

  /**
   * Note: the original methods for releasing keys have been renamed from release to releaseKey.
   * This became necessary because in Scala the signatures of release(KeyCode*) and
   * release(MouseButton*) are equal.
   */
  def releaseKey(keys: KeyCode*): SfxRobot = {
    delegate.release(sfxKeyCodeSeq2jfx(keys): _*)
    this
  }

  //---------------------------------------------------------------------------------------------
  // IMPLEMENTATION OF MOUSE ROBOT
  //---------------------------------------------------------------------------------------------

  def press(buttons: MouseButton*): SfxRobot = {
    delegate.press(sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def release(buttons: MouseButton*): SfxRobot = {
    delegate.release(sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  //---------------------------------------------------------------------------------------------
  // IMPLEMENTATION OF CLICK ROBOT
  //---------------------------------------------------------------------------------------------

  def clickOn(buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn(pointQuery: PointQuery, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(pointQuery, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def doubleClickOn(buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def doubleClickOn(pointQuery: PointQuery, buttons: MouseButton*): SfxRobot = {
    delegate.doubleClickOn(pointQuery, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn(x: Double, y: Double, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(x, y, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn(point: Point2D, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(point, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn(bounds: Bounds, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(bounds, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn(node: Node, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(node, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn(scene: Scene, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(scene, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn(window: Window, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(window, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn(query: String, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(query, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn[J <: jfxsc.Node](matcher: Matcher[J], buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(matcher, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

  def clickOn[J <: jfxsc.Node](predicate: (J) => Boolean, buttons: MouseButton*): SfxRobot = {
    delegate.clickOn(predicate, sfxMouseButtonSeq2jfx(buttons): _*)
    this
  }

}
