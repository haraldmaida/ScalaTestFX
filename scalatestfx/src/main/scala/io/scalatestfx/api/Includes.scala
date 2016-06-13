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
import scala.collection.JavaConversions._
import javafx.{geometry => jfxge}
import javafx.{stage => jfxst}
import javafx.{scene => jfxsc}
import javafx.scene.{input => jfxin}
import scalafx.stage.Window
import scalafx.scene.Node
import scalafx.scene.input.KeyCode
import scalafx.scene.input.MouseButton
import scalafx.scene.SceneIncludes._
import com.google.common.base.Predicate

object Includes extends Includes

trait Includes
    extends JfxConversions
    with GuavaConversions

object JfxConversions extends JfxConversions

trait JfxConversions {

  implicit def jfxWindowSeq2sfx(windowList: java.util.List[jfxst.Window]): Seq[Window] =
    windowList.map[Window, Seq[Window]] { window => window }

  implicit def jfxNodeSet2sfx(nodeSet: java.util.Set[jfxsc.Node]): Set[Node] =
    nodeSet.map[Node, Set[Node]] { node => node }

  implicit def sfxNodeSeq2jfx(nodeSeq: Seq[Node]): java.util.List[jfxsc.Node] =
    nodeSeq.map[jfxsc.Node, Seq[jfxsc.Node]] { node => node }

  implicit def sfxKeyCodeSeq2jfx(keyCodes: Seq[KeyCode]): Seq[jfxin.KeyCode] =
    keyCodes.map[jfxin.KeyCode, Seq[jfxin.KeyCode]] { keyCode => keyCode }

  implicit def sfxMouseButtonSeq2jfx(mouseButtons: Seq[MouseButton]): Seq[jfxin.MouseButton] =
    mouseButtons.map[jfxin.MouseButton, Seq[jfxin.MouseButton]] { mouseButton => mouseButton }

}

object GuavaConversions extends GuavaConversions

trait GuavaConversions {

  implicit def toPredicate[T](f: T => Boolean) =
    new Predicate[T] {
      override def apply(v: T): Boolean =
        f(v)
    }

}