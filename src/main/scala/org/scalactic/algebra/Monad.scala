/*
 * Copyright 2001-2014 Artima, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.scalactic.algebra

import scala.language.higherKinds

/**
 * Proxy for algebraic structure containing <em>insertion</em> and <em>flat-mapping</em> methods that obey
 * laws of <em>identity</em> and <em>associativity</em>.
 *
 * <p>
 * A <code>MonadProxy</code> instance wraps an object that in some way behaves as a <code>Monad</code>.
 * </p>
 */
trait MonadProxy[Context[_], T] {

  /**
   * Applies the given function to the value contained in this context, returning the result 
   * of the function lifted into the same context.
   */
  def map[U](f: T => U): Context[U]

  /**
   * Applies the given function to the value contained in this context, returning the result 
   * of the function, which is another value wrapped in the same context.
   */
  def flatMap[U](f: T => Context[U]): Context[U]
}

/**
 * Algebraic structure containing <em>insertion</em> and <em>flat-mapping</em> methods that obey
 * laws of <em>identity</em> and <em>associativity</em>.
 */
trait Monad[Context[_]] {

  /**
   * Produces a <code>MonadProxy</code> wrapping the given context instance.
   */
  def apply[T](o: Context[T]): MonadProxy[Context, T]

  /**
   * Inserts a value into a context.
   */
  def insert[T](o: T): Context[T]
}

