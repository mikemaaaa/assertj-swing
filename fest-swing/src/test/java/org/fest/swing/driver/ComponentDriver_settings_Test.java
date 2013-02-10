/*
 * Created on Jul 19, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.driver;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.TestRobots.newRobotMock;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.core.*;
import org.junit.*;

/**
 * Tests for {@link ComponentDriver#settings()}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ComponentDriver_settings_Test {
  private Robot robot;
  private ComponentDriver driver;

  @Before
  public void setUp() {
    robot = newRobotMock();
    driver = new ComponentDriver(robot);
  }

  @Test
  public void should_return_settings_from_Robot() {
    final Settings settings = new Settings();
    new EasyMockTemplate(robot) {
      @Override protected void expectations() {
        expect(robot.settings()).andReturn(settings);
      }

      @Override protected void codeToTest() {
        assertThat(driver.settings()).isSameAs(settings);
      }
    }.run();
  }
}
