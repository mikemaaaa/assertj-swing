/*
 * Created on Feb 16, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.core.BasicRobot.robotWithCurrentAwtHierarchy;

import java.awt.Dimension;
import java.awt.Window;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.lock.ScreenLock;

/**
 * Supports functional testing of {@code Window}.
 * 
 * @author Alex Ruiz
 */
public abstract class WindowFixture<T extends Window> extends ContainerFixture<T> implements CommonComponentFixture,
WindowLikeContainerFixture, JPopupMenuInvokerFixture {
  /**
   * Creates a new {@link WindowFixture}. This constructor creates a new {@link Robot} containing the current AWT
   * hierarchy.
   * 
   * @param type the type of {@code Window} to find using the created {@code Robot}.
   * @throws NullPointerException if the given {@code Window} type is {@code null}.
   * @throws ComponentLookupException if a {@code Window} having a matching type could not be found.
   * @throws ComponentLookupException if more than one {@code Window} having a matching type is found.
   * @see BasicRobot#robotWithCurrentAwtHierarchy()
   */
  public WindowFixture(Class<? extends T> type) {
    this(robotWithCurrentAwtHierarchy(), type);
  }

  /**
   * Creates a new {@link WindowFixture}.
   * 
   * @param robot performs simulation of user events on a {@code Window}.
   * @param type the type of {@code Window} to find using the given {@code Robot}.
   * @throws NullPointerException if the given robot is {@code null}.
   * @throws NullPointerException if the given {@code Window} type is {@code null}.
   * @throws ComponentLookupException if a {@code Window} having a matching type could not be found.
   * @throws ComponentLookupException if more than one {@code Window} having a matching type is found.
   */
  public WindowFixture(Robot robot, Class<? extends T> type) {
    super(robot, type);
  }

  /**
   * Creates a new {@link WindowFixture}. This constructor creates a new {@link Robot} containing the current AWT
   * hierarchy.
   * 
   * @param name the name of the {@code Window} to find.
   * @param type the type of {@code Window} to find using the created {@code Robot}.
   * @throws NullPointerException if the given {@code Window} type is {@code null}.
   * @throws ComponentLookupException if a {@code Window} having a matching name could not be found.
   * @throws ComponentLookupException if more than one {@code Window} having a matching name is found.
   * @see BasicRobot#robotWithCurrentAwtHierarchy()
   */
  public WindowFixture(String name, Class<? extends T> type) {
    this(robotWithCurrentAwtHierarchy(), name, type);
  }

  /**
   * Creates a new {@link WindowFixture}.
   * 
   * @param robot performs simulation of user events on a {@code Window}.
   * @param name the name of the {@code Window} to find using the given {@code Robot}.
   * @param type the type of {@code Window} to find using the given {@code Robot}.
   * @throws NullPointerException if the given robot is {@code null}.
   * @throws NullPointerException if the given {@code Window} type is {@code null}.
   * @throws ComponentLookupException if a {@code Window} having a matching name could not be found.
   * @throws ComponentLookupException if more than one {@code Window} having a matching name is found.
   */
  public WindowFixture(Robot robot, String name, Class<? extends T> type) {
    super(robot, name, type);
  }

  /**
   * Creates a new {@link WindowFixture}. This constructor creates a new {@link Robot} containing the current AWT
   * hierarchy.
   * 
   * @param target the {@code Window} to be managed by this fixture.
   * @throws NullPointerException if the given target {@code Window} is {@code null}.
   */
  public WindowFixture(T target) {
    this(robotWithCurrentAwtHierarchy(), target);
  }

  /**
   * Creates a new {@link WindowFixture}.
   * 
   * @param robot performs simulation of user events on the given {@code Window}.
   * @param target the {@code Window} to be managed by this fixture.
   * @throws NullPointerException if the given robot is {@code null}.
   * @throws NullPointerException if the given target {@code Window} is {@code null}.
   */
  public WindowFixture(Robot robot, T target) {
    super(robot, target);
  }

  /**
   * Shows this fixture's {@code Window}.
   * 
   * @return this fixture.
   */
  protected abstract WindowFixture<T> show();

  /**
   * Shows this fixture's {@code Window}, resized to the given size.
   * 
   * @param size the size to resize this fixture's {@code Window} to.
   * @return this fixture.
   */
  protected abstract WindowFixture<T> show(Dimension size);

  /**
   * Cleans up any used resources (keyboard, mouse, open windows and {@link ScreenLock}) used by this robot.
   */
  public final void cleanUp() {
    robot().cleanUp();
  }
}
