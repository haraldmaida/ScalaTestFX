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
package io.scalatestfx.framework.scalatest

import javafx.{stage => jfxst}
import org.testfx.api.FxToolkit
import scalafx.application.JFXApp
import io.scalatestfx.api.Java8Conversions._

class JFXAppAdapter(
    val jfxAppFixture: JFXAppFixture
    ) extends javafx.application.Application {

  override def init() {
    FxToolkit.registerStage(() => {
        JFXApp.Stage = new jfxst.Stage()
        jfxAppFixture.stage.delegate
    })
  }

  override def start(stage: jfxst.Stage) {
    stage.show()
  }

  override def stop() {
    FxToolkit.hideStage()
    jfxAppFixture.stopApp
  }

}
