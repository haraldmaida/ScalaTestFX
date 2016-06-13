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
package io.scalatestfx.zzznolongerused

import java.util.concurrent.CountDownLatch
import javafx.application.Platform
import org.scalatest.{Outcome, TestSuite, TestSuiteMixin}

trait RunOnApplicationThread extends TestSuiteMixin {
  this: TestSuite =>
  abstract override def withFixture(test: NoArgTest): Outcome = {
    BootstrapApplication.launch()
    val appThreadLatch = new CountDownLatch(1)
    val superWith = super.withFixture _  // required to access to super withFixture method from within runnable for a trait
    var testException: Exception = null
    var outcome: Outcome = null
    Platform.runLater(new Runnable() {
      override def run() {
        try {
          outcome = superWith(test)
        } catch {
          case e: Exception => testException = e
        } finally {
          appThreadLatch.countDown()
        }
      }
    })
    appThreadLatch.await()
    if (testException != null) {
      throw testException
    }
    outcome
  }
}
