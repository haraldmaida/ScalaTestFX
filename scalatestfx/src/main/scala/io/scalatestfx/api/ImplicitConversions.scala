/*
 * Copyright (c) 2016, Innoave.com
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

import scala.language.implicitConversions

object ImplicitConversions extends ImplicitConversions

trait ImplicitConversions
    extends JfxConversions
    with GuavaConversions
    with Java8Conversions

object JfxConversions extends JfxConversions

trait JfxConversions {
  import javafx.{stage => jfxst}
  import javafx.{scene => jfxsc}
  import javafx.scene.{input => jfxin}
  import scalafx.stage.Window
  import scalafx.scene.Node
  import scalafx.scene.input.KeyCode
  import scalafx.scene.input.MouseButton
  import scalafx.scene.input.InputIncludes._

  implicit def asSfxWindowSeq(windowList: java.util.List[jfxst.Window]): Seq[Window] =
    windowList.map[Window, Seq[Window]] { window => window }

  implicit def asSfxNodeSet(nodeSet: java.util.Set[jfxsc.Node]): Set[Node] =
    nodeSet.map[Node, Set[Node]] { node => node }

  implicit def asSfxNodeList(nodeList: java.util.List[jfxsc.Node]): List[Node] =
    nodeList.map[Node, List[Node]] { node => node }

  implicit def asSfxMouseButtonSeq(mouseButtons: Seq[jfxin.MouseButton]): Seq[MouseButton] =
    mouseButtons.map[MouseButton, Seq[MouseButton]] { mouseButton => mouseButton }

  implicit def asJfxNodeSet(nodeSet: Set[Node]): Set[jfxsc.Node] =
    nodeSet.map[jfxsc.Node, Set[jfxsc.Node]] { node => node }

  implicit def asJfxNodeSet(nodeList: List[Node]): List[jfxsc.Node] =
    nodeList.map[jfxsc.Node, List[jfxsc.Node]] { node => node }

  implicit def asJfxKeyCodeSeq(keyCodes: Seq[KeyCode]): Seq[jfxin.KeyCode] =
    keyCodes.map[jfxin.KeyCode, Seq[jfxin.KeyCode]] { keyCode => keyCode }

  implicit def asJfxMouseButtonSeq(mouseButtons: Seq[MouseButton]): Seq[jfxin.MouseButton] =
    mouseButtons.map[jfxin.MouseButton, Seq[jfxin.MouseButton]] { mouseButton => mouseButton }

}

object GuavaConversions extends GuavaConversions

trait GuavaConversions {

  implicit def asPredicate[T](f: T => Boolean) =
    new com.google.common.base.Predicate[T] {
      override def apply(v: T): Boolean =
        f(v)
    }

}

object Java8Conversions extends Java8Conversions

trait Java8Conversions {

  implicit def asSupplier[T](f: () => T) =
    new java.util.function.Supplier[T] {
      override def get(): T =
        f()
    }

}
