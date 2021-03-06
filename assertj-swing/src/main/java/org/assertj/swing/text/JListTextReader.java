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
package org.assertj.swing.text;

import javax.annotation.Nonnull;
import javax.swing.JList;
import javax.swing.ListModel;

import org.assertj.core.util.VisibleForTesting;
import org.assertj.swing.annotation.RunsInCurrentThread;
import org.assertj.swing.cell.JListCellReader;
import org.assertj.swing.driver.BasicJListCellReader;

/**
 * Reads the text displayed in a {@code JList}.
 * 
 * @author Alex Ruiz
 */
public class JListTextReader extends TextReader<JList> {
  private final JListCellReader cellReader;

  public JListTextReader() {
    this(new BasicJListCellReader());
  }

  @VisibleForTesting
  JListTextReader(@Nonnull JListCellReader cellReader) {
    this.cellReader = cellReader;
  }

  /**
   * @return {@code JList.class}.
   */
  @Override
  @Nonnull public Class<JList> supportedComponent() {
    return JList.class;
  }

  /**
   * Indicates whether the text representation of any of the elements in the given {@code JList} contains the given
   * text.
   * 
   * @param list the given {@code JList}.
   * @param text the given text.
   * @return {@code true} if the text representation of any of the elements the given {@code JList} contains the given
   *         text; {@code false} otherwise.
   */
  @RunsInCurrentThread
  @Override
  protected boolean checkContainsText(@Nonnull JList list, @Nonnull String text) {
    ListModel model = list.getModel();
    int elementCount = model.getSize();
    for (int i = 0; i < elementCount; i++) {
      String elementText = cellReader.valueAt(list, i);
      if (elementText == null) {
        continue;
      }
      if (elementText.contains(text)) {
        return true;
      }
    }
    return false;
  }
}
