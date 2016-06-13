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
package io.scalatestfx.zzznolongerused

import org.testfx.api.FxService
import org.testfx.robot.BaseRobot
import org.testfx.robot.ClickRobot
import org.testfx.robot.DragRobot
import org.testfx.robot.KeyboardRobot
import org.testfx.robot.MouseRobot
import org.testfx.robot.MoveRobot
import org.testfx.robot.ScrollRobot
import org.testfx.robot.SleepRobot
import org.testfx.robot.TypeRobot
import org.testfx.robot.WriteRobot
import org.testfx.robot.impl.BaseRobotImpl
import org.testfx.robot.impl.KeyboardRobotImpl
import org.testfx.robot.impl.MouseRobotImpl
import org.testfx.robot.impl.SleepRobotImpl
import org.testfx.service.finder.NodeFinder
import org.testfx.service.finder.WindowFinder
import org.testfx.service.locator.BoundsLocator
import org.testfx.service.locator.PointLocator
import org.testfx.service.locator.impl.BoundsLocatorImpl
import org.testfx.service.locator.impl.PointLocatorImpl
import org.testfx.service.support.CaptureSupport

trait SfxRobotContext {

  val windowFinder: WindowFinder = FxService.serviceContext.getWindowFinder
  val nodeFinder: NodeFinder = FxService.serviceContext.getNodeFinder

  val boundsLocator: BoundsLocator = new BoundsLocatorImpl()
  val pointLocator: PointLocator = new PointLocatorImpl(boundsLocator)

  val baseRobot: BaseRobot = new BaseRobotImpl()
  val keyboardRobot: KeyboardRobot = new KeyboardRobotImpl(baseRobot)
  val mouseRobot: MouseRobot = new MouseRobotImpl(baseRobot)
  val sleepRobot: SleepRobot = new SleepRobotImpl()

  val typeRobot: TypeRobot
  val writeRobot: WriteRobot
  val moveRobot: MoveRobot
  val clickRobot: ClickRobot
  val dragRobot: DragRobot
  val scrollRobot: ScrollRobot

  val captureSupport: CaptureSupport

}