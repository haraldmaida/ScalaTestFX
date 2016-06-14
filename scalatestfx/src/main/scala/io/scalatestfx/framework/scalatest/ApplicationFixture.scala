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

import java.util.function.Supplier
import javafx.stage.Stage
import org.scalatest.Outcome
import org.scalatest.TestSuite
import org.scalatest.TestSuiteMixin
import org.testfx.api.FxToolkit
import javafx.scene.Parent

trait ApplicationFixture extends TestSuiteMixin { self: TestSuite =>

  def start(start: Stage): Unit

  def init() {
    FxToolkit.registerStage(new Supplier[Stage] {
      override def get() = {
        new Stage()
      }
    })
  }

  def stop() {
    FxToolkit.hideStage()
  }

  abstract override def withFixture(test: NoArgTest): Outcome = {
    val superWithFixture = super.withFixture _   // required to access to super withFixture method from within runnable for a trait
    //setup before all tests
    FxToolkit.registerPrimaryStage()
    FxToolkit.setupApplication(new Supplier[javafx.application.Application] {
      override def get() = new ApplicationAdapter(ApplicationFixture.this)
    })
    val outcome = superWithFixture(test)
    //cleanup after all tests
    FxToolkit.cleanupApplication(new ApplicationAdapter(ApplicationFixture.this))
    outcome
  }

}
