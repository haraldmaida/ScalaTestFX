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

import scalafx.scene.Node
import org.scalatest.matchers.Matcher

trait NodeQuery {

  def from(parentNodes: Node*): NodeQuery

  def lookup(query: String): NodeQuery

  def lookup[T](matcher: Matcher[T]): NodeQuery

  def lookup[T <: Node](predicate: (T) => Boolean): NodeQuery

  def matching[T](matcher: Matcher[T]): NodeQuery

  def matching[T](predicate: (T) => Boolean): NodeQuery

  def nth(index: Int): NodeQuery

  def query[T <: Node](): Option[T]

  def queryAll[T <: Node](): Set[T]

}