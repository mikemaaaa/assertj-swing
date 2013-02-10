/*
 * Created on Apr 10, 2007
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

import static org.fest.swing.core.ComponentLookupScope.SHOWING_ONLY;
import static org.fest.swing.timing.Pause.pause;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.ComponentFoundCondition;
import org.fest.swing.core.ComponentMatcher;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.core.NameMatcher;
import org.fest.swing.core.Robot;
import org.fest.swing.core.TypeMatcher;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.timing.Timeout;

/**
 * Understands lookup of {@code Component}s contained in a {@code Container}.
 * 
 * @param <T> the type of container handled by this fixture.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class ContainerFixture<T extends Container> extends ComponentFixture<T> implements
ComponentContainerFixture {
  private final JMenuItemFinder menuItemFinder;

  /**
   * Creates a new {@link ContainerFixture}.
   * 
   * @param robot performs simulation of user events on a {@code Container}.
   * @param type the type of the {@code Container} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code type} is {@code null}.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see org.fest.swing.core.ComponentFinder#findByType(Class)
   */
  public ContainerFixture(@Nonnull Robot robot, @Nonnull Class<? extends T> type) {
    super(robot, type);
    menuItemFinder = new JMenuItemFinder(robot, component());
  }

  /**
   * Creates a new {@link ContainerFixture}.
   * 
   * @param robot performs simulation of user events on a {@code Container}.
   * @param name the name of the {@code Container} to find using the given {@code Robot}.
   * @param type the type of the {@code Container} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code type} is {@code null}.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @see org.fest.swing.core.ComponentFinder#findByName(String, Class)
   */
  public ContainerFixture(@Nonnull Robot robot, @Nullable String name, @Nonnull Class<? extends T> type) {
    super(robot, name, type);
    menuItemFinder = new JMenuItemFinder(robot, component());
  }

  /**
   * Creates a new {@link ContainerFixture}.
   * 
   * @param robot performs simulation of user events on the given {@code Container}.
   * @param target the {@code Container} to be.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code target} is {@code null}.
   */
  public ContainerFixture(@Nonnull Robot robot, @Nonnull T target) {
    super(robot, target);
    menuItemFinder = new JMenuItemFinder(robot, target);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JButtonFixture button() {
    return new JButtonFixture(robot(), findByType(JButton.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JButtonFixture button(@Nonnull GenericTypeMatcher<? extends JButton> matcher) {
    return new JButtonFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JButtonFixture button(@Nullable String name) {
    return new JButtonFixture(robot(), findByName(name, JButton.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JCheckBoxFixture checkBox() {
    return new JCheckBoxFixture(robot(), findByType(JCheckBox.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JCheckBoxFixture checkBox(@Nonnull GenericTypeMatcher<? extends JCheckBox> matcher) {
    return new JCheckBoxFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JCheckBoxFixture checkBox(@Nullable String name) {
    return new JCheckBoxFixture(robot(), findByName(name, JCheckBox.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JComboBoxFixture comboBox() {
    return new JComboBoxFixture(robot(), findByType(JComboBox.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JComboBoxFixture comboBox(@Nonnull GenericTypeMatcher<? extends JComboBox> matcher) {
    return new JComboBoxFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JComboBoxFixture comboBox(@Nullable String name) {
    return new JComboBoxFixture(robot(), findByName(name, JComboBox.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull DialogFixture dialog() {
    return dialog(DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull DialogFixture dialog(@Nonnull Timeout timeout) {
    TypeMatcher matcher = new TypeMatcher(Dialog.class, requireShowing());
    return findDialog(matcher, timeout);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull DialogFixture dialog(@Nonnull GenericTypeMatcher<? extends Dialog> matcher) {
    return dialog(matcher, DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull DialogFixture dialog(@Nonnull GenericTypeMatcher<? extends Dialog> matcher,
      @Nonnull Timeout timeout) {
    return findDialog(matcher, timeout);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull DialogFixture dialog(@Nullable String name) {
    return dialog(name, DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull DialogFixture dialog(@Nullable String name, @Nonnull Timeout timeout) {
    NameMatcher matcher = new NameMatcher(name, Dialog.class, requireShowing());
    return findDialog(matcher, timeout);
  }

  private @Nonnull DialogFixture findDialog(@Nonnull ComponentMatcher matcher, @Nonnull Timeout timeout) {
    String description = "dialog to be found using matcher " + matcher;
    ComponentFoundCondition condition = new ComponentFoundCondition(description, robot().finder(), matcher);
    pause(condition, timeout);
    return new DialogFixture(robot(), (Dialog) condition.found());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JFileChooserFixture fileChooser() {
    return fileChooser(DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JFileChooserFixture fileChooser(@Nonnull Timeout timeout) {
    TypeMatcher matcher = new TypeMatcher(JFileChooser.class, requireShowing());
    return findFileChooser(matcher, timeout);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JFileChooserFixture fileChooser(@Nonnull GenericTypeMatcher<? extends JFileChooser> matcher) {
    return fileChooser(matcher, DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JFileChooserFixture fileChooser(@Nonnull GenericTypeMatcher<? extends JFileChooser> matcher,
      @Nonnull Timeout timeout) {
    return findFileChooser(matcher, timeout);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JFileChooserFixture fileChooser(@Nullable String name) {
    return new JFileChooserFixture(robot(), findByName(name, JFileChooser.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JFileChooserFixture fileChooser(@Nullable String name, @Nonnull Timeout timeout) {
    NameMatcher matcher = new NameMatcher(name, JFileChooser.class, requireShowing());
    return findFileChooser(matcher, timeout);
  }

  private @Nonnull JFileChooserFixture findFileChooser(@Nonnull ComponentMatcher matcher, @Nonnull Timeout timeout) {
    String description = "file chooser to be found using matcher " + matcher;
    ComponentFoundCondition condition = new ComponentFoundCondition(description, robot().finder(), matcher);
    pause(condition, timeout);
    return new JFileChooserFixture(robot(), (JFileChooser) condition.found());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JLabelFixture label() {
    return new JLabelFixture(robot(), findByType(JLabel.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JLabelFixture label(@Nonnull GenericTypeMatcher<? extends JLabel> matcher) {
    return new JLabelFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JLabelFixture label(@Nullable String name) {
    return new JLabelFixture(robot(), findByName(name, JLabel.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JListFixture list() {
    return new JListFixture(robot(), findByType(JList.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JListFixture list(@Nonnull GenericTypeMatcher<? extends JList> matcher) {
    return new JListFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JListFixture list(@Nullable String name) {
    return new JListFixture(robot(), findByName(name, JList.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JMenuItemFixture menuItemWithPath(@Nonnull String... path) {
    return new JMenuItemFixture(robot(), menuItemFinder.menuItemWithPath(path));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JMenuItemFixture menuItem(@Nullable String name) {
    boolean requireShowing = SHOWING_ONLY.equals(robot().settings().componentLookupScope());
    return new JMenuItemFixture(robot(), finder().findByName(component(), name, JMenuItem.class, requireShowing));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JMenuItemFixture menuItem(@Nonnull GenericTypeMatcher<? extends JMenuItem> matcher) {
    return new JMenuItemFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JOptionPaneFixture optionPane() {
    return optionPane(DEFAULT_DIALOG_LOOKUP_TIMEOUT);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JOptionPaneFixture optionPane(@Nonnull Timeout timeout) {
    TypeMatcher matcher = new TypeMatcher(JOptionPane.class, requireShowing());
    String description = "option pane to be found using matcher " + matcher;
    ComponentFoundCondition condition = new ComponentFoundCondition(description, robot().finder(), matcher);
    pause(condition, timeout);
    return new JOptionPaneFixture(robot(), (JOptionPane) condition.found());
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JPanelFixture panel() {
    return new JPanelFixture(robot(), findByType(JPanel.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JPanelFixture panel(@Nonnull GenericTypeMatcher<? extends JPanel> matcher) {
    return new JPanelFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JPanelFixture panel(@Nullable String name) {
    return new JPanelFixture(robot(), findByName(name, JPanel.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JProgressBarFixture progressBar() {
    return new JProgressBarFixture(robot(), findByType(JProgressBar.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JProgressBarFixture progressBar(@Nonnull GenericTypeMatcher<? extends JProgressBar> matcher) {
    return new JProgressBarFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JProgressBarFixture progressBar(@Nullable String name) {
    return new JProgressBarFixture(robot(), findByName(name, JProgressBar.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JRadioButtonFixture radioButton() {
    return new JRadioButtonFixture(robot(), findByType(JRadioButton.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JRadioButtonFixture radioButton(@Nonnull GenericTypeMatcher<? extends JRadioButton> matcher) {
    return new JRadioButtonFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JRadioButtonFixture radioButton(@Nullable String name) {
    return new JRadioButtonFixture(robot(), findByName(name, JRadioButton.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JScrollBarFixture scrollBar() {
    return new JScrollBarFixture(robot(), findByType(JScrollBar.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JScrollBarFixture scrollBar(@Nonnull GenericTypeMatcher<? extends JScrollBar> matcher) {
    return new JScrollBarFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JScrollBarFixture scrollBar(@Nullable String name) {
    return new JScrollBarFixture(robot(), findByName(name, JScrollBar.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JScrollPaneFixture scrollPane() {
    return new JScrollPaneFixture(robot(), findByType(JScrollPane.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JScrollPaneFixture scrollPane(@Nonnull GenericTypeMatcher<? extends JScrollPane> matcher) {
    return new JScrollPaneFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JScrollPaneFixture scrollPane(@Nullable String name) {
    return new JScrollPaneFixture(robot(), findByName(name, JScrollPane.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSliderFixture slider() {
    return new JSliderFixture(robot(), findByType(JSlider.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSliderFixture slider(@Nonnull GenericTypeMatcher<? extends JSlider> matcher) {
    return new JSliderFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSliderFixture slider(@Nullable String name) {
    return new JSliderFixture(robot(), findByName(name, JSlider.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSpinnerFixture spinner() {
    return new JSpinnerFixture(robot(), findByType(JSpinner.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSpinnerFixture spinner(@Nonnull GenericTypeMatcher<? extends JSpinner> matcher) {
    return new JSpinnerFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSpinnerFixture spinner(@Nullable String name) {
    return new JSpinnerFixture(robot(), findByName(name, JSpinner.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSplitPaneFixture splitPane() {
    return new JSplitPaneFixture(robot(), findByType(JSplitPane.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSplitPaneFixture splitPane(@Nonnull GenericTypeMatcher<? extends JSplitPane> matcher) {
    return new JSplitPaneFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JSplitPaneFixture splitPane(@Nullable String name) {
    return new JSplitPaneFixture(robot(), findByName(name, JSplitPane.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTabbedPaneFixture tabbedPane() {
    return new JTabbedPaneFixture(robot(), findByType(JTabbedPane.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTabbedPaneFixture tabbedPane(@Nonnull GenericTypeMatcher<? extends JTabbedPane> matcher) {
    return new JTabbedPaneFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTabbedPaneFixture tabbedPane(@Nullable String name) {
    return new JTabbedPaneFixture(robot(), findByName(name, JTabbedPane.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTableFixture table() {
    return new JTableFixture(robot(), findByType(JTable.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTableFixture table(@Nonnull GenericTypeMatcher<? extends JTable> matcher) {
    return new JTableFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTableFixture table(@Nullable String name) {
    return new JTableFixture(robot(), findByName(name, JTable.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTextComponentFixture textBox() {
    return new JTextComponentFixture(robot(), findByType(JTextComponent.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTextComponentFixture textBox(@Nonnull GenericTypeMatcher<? extends JTextComponent> matcher) {
    return new JTextComponentFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTextComponentFixture textBox(@Nullable String name) {
    return new JTextComponentFixture(robot(), findByName(name, JTextComponent.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JToggleButtonFixture toggleButton() {
    return new JToggleButtonFixture(robot(), findByType(JToggleButton.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JToggleButtonFixture toggleButton(@Nonnull GenericTypeMatcher<? extends JToggleButton> matcher) {
    return new JToggleButtonFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JToggleButtonFixture toggleButton(@Nullable String name) {
    return new JToggleButtonFixture(robot(), findByName(name, JToggleButton.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JToolBarFixture toolBar() {
    return new JToolBarFixture(robot(), findByType(JToolBar.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JToolBarFixture toolBar(@Nonnull GenericTypeMatcher<? extends JToolBar> matcher) {
    return new JToolBarFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JToolBarFixture toolBar(@Nullable String name) {
    return new JToolBarFixture(robot(), findByName(name, JToolBar.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTreeFixture tree() {
    return new JTreeFixture(robot(), findByType(JTree.class));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTreeFixture tree(@Nonnull GenericTypeMatcher<? extends JTree> matcher) {
    return new JTreeFixture(robot(), find(matcher));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull JTreeFixture tree(@Nullable String name) {
    return new JTreeFixture(robot(), findByName(name, JTree.class));
  }

  /**
   * Finds a component by type, contained in this fixture's {@code Container}.
   * 
   * @param <C> the generic type of the component to find.
   * @param type the type of component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  protected final <C extends Component> C findByType(Class<C> type) {
    return finder().findByType(component(), type, requireShowing());
  }

  /**
   * Finds a component by name and type, contained in this fixture's {@code Container}.
   * 
   * @param <C> the generic type of the component to find.
   * @param name the name of the component to find.
   * @param type the type of component to find.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  protected final <C extends Component> C findByName(@Nullable String name, @Nonnull Class<C> type) {
    return finder().findByName(component(), name, type, requireShowing());
  }

  /**
   * Finds a {@code Component} using the given {@link GenericTypeMatcher}, contained in this fixture's {@code Container}
   * .
   * 
   * @param <C> the generic type of component the given matcher can handle.
   * @param matcher the matcher to use to find the component.
   * @return the found component.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  protected final <C extends Component> C find(@Nonnull GenericTypeMatcher<? extends C> matcher) {
    return finder().find(component(), matcher);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  @Override
  public @Nonnull <C extends Component, F extends ComponentFixture<C>> F with(
      @Nonnull ComponentFixtureExtension<C, F> extension) {
    return extension.createFixture(robot(), component());
  }

  /**
   * @return the {@code ComponentFinder} contained in this fixture's {@code Robot}.
   */
  protected final ComponentFinder finder() {
    return robot().finder();
  }
}
