/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2018 the original author or authors.
 */
package org.assertj.swing.core;

import org.junit.Test;

/**
 * Tests for {@link NameMatcher#NameMatcher(String)}.
 * 
 * @author Alex Ruiz
 */
public class NameMatcher_constructorWithName_Test {
  @Test(expected = NullPointerException.class)
  public void should_Throw_Error_If_Name_Is_Null() {
    new NameMatcher(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_Throw_Error_If_Name_Is_Empty() {
    new NameMatcher("");
  }
}
