/*
 * Created on Nov 26, 2008
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.exception;

import javax.annotation.Nonnull;

/**
 * Understands an error thrown when a EDT violation is detected. For more details, please read the <a
 * href="http://java.sun.com/javase/6/docs/api/javax/swing/package-summary.html#threading" target="_blank">Swing's
 * Threading Policy</a>.
 * 
 * @author Alex Ruiz
 */
public class EdtViolationException extends RuntimeException {
  /**
   * Creates a new {@link EdtViolationException}.
   * 
   * @param message the detail message.
   */
  public EdtViolationException(@Nonnull String message) {
    super(message);
  }
}
