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
package io.scalatestfx.cases.acceptance

import scala.concurrent.duration._
import io.scalatestfx.api.SfxRobot
import io.scalatestfx.api.ViewObject
import io.scalatestfx.framework.scalatest.JFXAppFixture
import io.scalatestfx.testing.AcceptanceSpec
import io.scalatestfx.testing.UiTest
import org.testfx.api.FxAssert.verifyThat
import org.testfx.matcher.base.NodeMatchers.hasText
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.StackPane

@UiTest
class JFXAppStartSpec extends AcceptanceSpec
    with SfxRobot
    with JFXAppFixture
{

  override lazy val stage: PrimaryStage = new JFXApp.PrimaryStage with ViewObject {
    title = "Hallo World!"
    width = 200
    height = 200
    scene = new Scene {
      content = new StackPane {
        children = Seq(
          new Button {
            text = "click me!"
            onAction = { _: ActionEvent =>
              text = "clicked!"
            }
          }
        )
      }
    }
  }

  "JFXappFixture mixed in in spec" should "start an simple JFXApp" in {

    window("Hallo World!").isShowing shouldBe true

  }

  it should "contain a button with text 'click me'" in {

    verifyThat(".button", hasText("click me!"))

  }

  it should "change the button text when it is clicked" in {

    sleep(1.second)

    clickOn(".button")

    sleep(1.second)

    verifyThat(".button", hasText("clicked!"))

  }

}
