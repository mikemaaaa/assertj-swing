/*
 * Created on May 20, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.core;

import static org.fest.javafx.threading.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;

import java.util.*;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.exception.NodeLookupException;
import org.fest.javafx.threading.GuiTask;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * Understands <code>{@link Node}</code> lookups.
 *
 * @author Alex Ruiz
 */
public class BasicNodeFinder implements NodeFinder {

  /** {@inheritDoc} */
  @Override public Node findById(Scene root, String id, Visibility visibility) {
    return find(root, new NodeMatcherById(id, visibility));
  }

  /** {@inheritDoc} */
  @Override public <T extends Node> T findByType(Scene root, Class<T> type, Visibility visibility) {
    Node found = find(root, new NodeMatcherByType(type, visibility));
    return type.cast(found);
  }

  @RunsInUIThread
  private Node find(Scene root, NodeMatcher matcher) {
    Collection<? extends Node> found = findAll(root, matcher);
    if (found.isEmpty()) throw nodeNotFound(root, matcher);
    if (found.size() > 1) throw multipleNodesFound(found, matcher);
    return found.iterator().next();
  }

  private NodeLookupException nodeNotFound(Scene root, NodeMatcher matcher) {
    String message = concat("Unable to find node using matcher ", matcher, ".");
    // TODO print node hierarchy
    throw new NodeLookupException(message);
  }

  private NodeLookupException multipleNodesFound(Collection<? extends Node> found, NodeMatcher matcher) {
    StringBuilder message = new StringBuilder();
    message.append("Found more than one node using matcher ").append(matcher);
    // TODO print found nodes
    throw new NodeLookupException(message.toString(), found);
  }

  @RunsInUIThread
  private Collection<? extends Node> findAll(final Scene root, final NodeMatcher matcher) {
    // TODO abstract Scene into a NodeHierarchy
    final Set<Node> found = new LinkedHashSet<Node>();
    execute(new GuiTask() {
      @Override protected void executeInUIThread() {
        for (Node node : root.get$content()) {
          // TODO go deeper in the hierarchy
          if (matcher.matches(node)) found.add(node);
        }
      }
    });
    return found;
  }
}