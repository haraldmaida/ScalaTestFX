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
import io.scalatestfx.framework.scalatest.ApplicationFixture
import io.scalatestfx.testing.AcceptanceSpec
import org.testfx.api.FxAssert.verifyThat
import org.testfx.matcher.base.NodeMatchers.hasText
import javafx.stage.Stage
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.StackPane
import scalafx.Includes._
import scalafx.event.ActionEvent

class ApplicationStartSpec extends AcceptanceSpec
    with SfxRobot
    with ApplicationFixture
{

  override def start(stage: Stage) {
    stage.setTitle("Hallo World!")
    val button = new Button("click me!")
    button.setOnAction((_: ActionEvent) -> button.setText("clicked!"))
    stage.setScene(new Scene(new StackPane(button), 100, 100))
    stage.show()
  }

  "JFXappFixture mixed in in spec" should "start an simple JFXApp" in {

    window("Hallo World!").isShowing() shouldBe true

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
