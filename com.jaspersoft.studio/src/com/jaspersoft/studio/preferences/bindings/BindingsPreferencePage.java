/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences.bindings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.bindings.Binding;
import org.eclipse.jface.bindings.TriggerSequence;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.keys.IBindingService;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.plugin.ExtensionManager;

/**
 * A preference page that is capable of displaying and editing the bindings between action and user input events.
 * This preference page has four general types of methods. Create methods are called when the page is first made
 * visible. They are responsible for creating all of the widgets, and laying them out within the preference page. Fill
 * methods populate the contents of the widgets that contain collections of data from which items can be selected. The
 * select methods respond to selection events from the user, such as a button press or a table selection. The update
 * methods update the contents of various widgets based on the current state of the user interface. For example, the
 * command name label will always try to match the current select in the binding table.

 *
 * @author Orlandin Marco
 */
public class BindingsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	/**
	 * Content for the tree where the bindings are displayed. It is essentially
	 * the same of a ListContentProvider but for a tree viewer instead of a table
	 * viewer
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class ModelContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof List) {
				return ((List<?>) parentElement).toArray();
			}
			return new Object[0];
		}

		@Override
		public Object getParent(Object element) {
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			return (element instanceof List);
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	/**
	 * A FilteredTree that provides a combo which is used to organize and display elements in the tree according to the
	 * selected criteria.
	 *
	 */
	protected class CategoryFilterTree extends FilteredTree {

		protected CategoryFilterTree(Composite parent, int treeStyle, PatternFilter filter) {
			super(parent, treeStyle, filter, true);
			setQuickSelectionMode(true);
		}
	}

	private final class BindingModelComparator extends ViewerComparator {

		private LinkedList<Integer> sortColumns = new LinkedList<Integer>();

		private boolean ascending = true;

		public BindingModelComparator() {
			for (int i = 0; i < NUM_OF_COLUMNS; i++) {
				sortColumns.add(new Integer(i));
			}
		}

		public int getSortColumn() {
			return ((Integer) sortColumns.getFirst()).intValue();
		}

		public void setSortColumn(int column) {
			if (column == getSortColumn()) {
				return;
			}
			Integer sortColumn = new Integer(column);
			sortColumns.remove(sortColumn);
			sortColumns.addFirst(sortColumn);
		}

		/**
		 * @return Returns the ascending.
		 */
		public boolean isAscending() {
			return ascending;
		}

		/**
		 * @param ascending
		 *          The ascending to set.
		 */
		public void setAscending(boolean ascending) {
			this.ascending = ascending;
		}

		@Override
		public final int compare(final Viewer viewer, final Object a, final Object b) {
			int result = 0;
			Iterator<Integer> i = sortColumns.iterator();
			while (i.hasNext() && result == 0) {
				int column = ((Integer) i.next()).intValue();
				result = compareColumn(viewer, a, b, column);
			}
			return ascending ? result : (-1) * result;
		}

		private int compareColumn(final Viewer viewer, final Object a, final Object b, final int columnNumber) {
			IBaseLabelProvider baseLabel = ((TreeViewer) viewer).getLabelProvider();
			if (baseLabel instanceof ITableLabelProvider) {
				ITableLabelProvider tableProvider = (ITableLabelProvider) baseLabel;
				String e1p = tableProvider.getColumnText(a, columnNumber);
				String e2p = tableProvider.getColumnText(b, columnNumber);
				if (e1p != null && e2p != null) {
					return e1p.compareTo(e2p);
				}
			}
			return 0;
		}

	}

	/**
	 * Selection listener when a column is selected, will trigger the reordering
	 * of the input basing it on the clicked column
	 */
	private final class ResortColumn extends SelectionAdapter {
	
		private final BindingModelComparator comparator;
		
		private final TreeColumn treeColumn;
		
		private final TreeViewer viewer;
		
		private final int column;

		private ResortColumn(BindingModelComparator comparator, TreeColumn treeColumn, TreeViewer viewer, int column) {
			this.comparator = comparator;
			this.treeColumn = treeColumn;
			this.viewer = viewer;
			this.column = column;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (comparator.getSortColumn() == column) {
				comparator.setAscending(!comparator.isAscending());
				viewer.getTree().setSortDirection(comparator.isAscending() ? SWT.UP : SWT.DOWN);
			} else {
				viewer.getTree().setSortColumn(treeColumn);
				comparator.setSortColumn(column);
			}
			try {
				viewer.getTree().setRedraw(false);
				viewer.refresh();
			} finally {
				viewer.getTree().setRedraw(true);
			}
		}
	}

	private class BindingElementLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override
		public String getText(Object element) {
			String rc = getColumnText(element, 0);
			if (rc == null) {
				rc = super.getText(element);
			}
			StringBuffer buf = new StringBuffer(rc);
			for (int i = 1; i < NUM_OF_COLUMNS; i++) {
				String text = getColumnText(element, i);
				if (text != null) {
					buf.append(' ');
					buf.append(text);
				}
			}
			return buf.toString();
		}

		@Override
		public String getColumnText(Object element, int index) {
			PreferenceBindingElement bindingElement = ((PreferenceBindingElement) element);
			switch (index) {
			case COMMAND_NAME_COLUMN: // name
				return bindingElement.getName();
			case KEY_SEQUENCE_COLUMN: // keys
				TriggerSequence seq = bindingElement.getTrigger();
				return seq == null ? "" : seq.format();
			case CONTEXT_COLUMN: // when
				String context = bindingElement.getContext();
				return context == null ? "" : context;

			}
			return null;
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
	}
	
	/**
	 * Class that contains the information of a conflict between two bindings
	 */
	private class BindingConflict{
		
		private String jssElementName;
		
		private PreferenceBindingElement jssElement;
		
		private String bidningConflict;
		
		private boolean isPlatformConflict;
		
		public BindingConflict(PreferenceBindingElement jssElement, String bindingConflictName, String bindingConflictSequence, boolean isPlatformConflict){
			this.jssElement = jssElement;
			this.jssElementName = jssElement.getName();
			this.bidningConflict = bindingConflictName + "(" + bindingConflictSequence + ")";
			this.isPlatformConflict = isPlatformConflict;
		}
	}


	/**
	 * The number of items to show in the bindings table tree.
	 */
	private static final int ITEMS_TO_SHOW = 12;

	private static final int COMMAND_NAME_COLUMN = 0;

	private static final int KEY_SEQUENCE_COLUMN = 1;

	private static final int CONTEXT_COLUMN = 2;

	private static int NUM_OF_COLUMNS = 3;

	private PatternFilter fPatternFilter;

	private CategoryFilterTree fFilteredTree;

	private Label commandNameValueLabel;

	private Text fBindingText;

	private StyledText fDescriptionText;

	private Label fWhenLabel;
	
	private Button removeBindingButton;
	
	private Button restoreBindingButton;
	
	private KeySequenceText fKeySequenceText;

	private TableViewer conflictViewer;
	
	private List<PreferenceBindingElement> input = null;

	@Override
	protected Control createContents(Composite parent) {
		final Composite page = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		page.setLayout(layout);

		fPatternFilter = new PatternFilter();
		createTree(page);
		createTreeControls(page);
		createDataControls(page);

		fill();

		applyDialogFont(page);

		// we want the description text control to span four lines, but because
		// we need the dialog's font for this information, we have to set it here
		// after the dialog font has been applied
		GC gc = new GC(fDescriptionText);
		gc.setFont(fDescriptionText.getFont());
		FontMetrics metrics = gc.getFontMetrics();
		gc.dispose();
		int height = metrics.getHeight() * 5 ;

		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.heightHint = height;
		gridData.widthHint = 200;
		gridData.minimumWidth = 200;
		fDescriptionText.setLayoutData(gridData);

		return page;
	}

	private final void createDataControls(final Composite parent) {
		GridLayout layout;
		GridData gridData;

		// Creates the data area.
		final Composite dataArea = new Composite(parent, SWT.NONE);
		layout = new GridLayout(2, true);
		layout.marginWidth = 0;
		dataArea.setLayout(layout);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		dataArea.setLayoutData(gridData);

		// LEFT DATA AREA
		// Creates the left data area.
		final Composite leftDataArea = new Composite(dataArea, SWT.NONE);
		layout = new GridLayout(2, false);
		leftDataArea.setLayout(layout);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.TOP;
		gridData.horizontalAlignment = SWT.FILL;
		leftDataArea.setLayoutData(gridData);

		// The command name label.
		final Label commandNameLabel = new Label(leftDataArea, SWT.NONE);
		commandNameLabel.setText("Name");

		// The current command name.
		commandNameValueLabel = new Label(leftDataArea, SWT.NONE);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		commandNameValueLabel.setLayoutData(gridData);

		Label commandDescriptionlabel = new Label(leftDataArea, SWT.LEAD);
		commandDescriptionlabel.setText("Description");
		gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		commandDescriptionlabel.setLayoutData(gridData);

		fDescriptionText = new StyledText(leftDataArea, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
		fDescriptionText.setBackground(commandDescriptionlabel.getBackground());

		// The binding label.
		final Label bindingLabel = new Label(leftDataArea, SWT.NONE);
		bindingLabel.setText("Binding");

		// The key sequence entry widget.
		fBindingText = new Text(leftDataArea, SWT.BORDER);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.widthHint = 200;
		fBindingText.setLayoutData(gridData);

		fKeySequenceText = new KeySequenceText(fBindingText);
		fKeySequenceText.setKeyStrokeLimit(4);
		fKeySequenceText.addPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public final void propertyChange(final PropertyChangeEvent event) {
				boolean somethingChanged = false;
				StructuredSelection selection = (StructuredSelection) fFilteredTree.getViewer().getSelection();
				JSSKeySequence keySequence = fKeySequenceText.getKeySequence();
				for (Object element : selection.toArray()) {
					PreferenceBindingElement activeBinding = (PreferenceBindingElement) element;
					if (!keySequence.isEqual(activeBinding.getTrigger())){
						somethingChanged = true;
						activeBinding.setTrigger(keySequence);
					}
				}
				if (somethingChanged){
					fFilteredTree.getViewer().refresh();
					computeOverlappingBindings();
					fBindingText.setSelection(fBindingText.getTextLimit());
				}
			}
		});

		// The when label.
		final Label whenLabel = new Label(leftDataArea, SWT.NONE);
		whenLabel.setText("When");

		// The when combo.
		fWhenLabel = new Label(leftDataArea, SWT.NONE);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.widthHint = 200;
		fWhenLabel.setLayoutData(gridData);

		// RIGHT DATA AREA
		// Creates the right data area.
		final Group rightDataArea = new Group(dataArea, SWT.NONE);
		layout = new GridLayout(1, false);
		rightDataArea.setLayout(layout);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		rightDataArea.setLayoutData(gridData);
		rightDataArea.setText("Conflicts");
		
		//Create the conflict table
		conflictViewer = new TableViewer(rightDataArea, SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		Table table = conflictViewer.getTable();
		table.setHeaderVisible(true);
		TableColumn bindingStudioNameColumn = new TableColumn(table, SWT.LEAD);
		bindingStudioNameColumn.setText("Binding");
		bindingStudioNameColumn.setWidth(100);
		TableColumn bindingNameColumn = new TableColumn(table, SWT.LEAD);
		bindingNameColumn.setText("Conflicting Binding");
		bindingNameColumn.setWidth(100);
		TableColumn bindingContextNameColumn = new TableColumn(table, SWT.LEAD);
		bindingContextNameColumn.setText("Origin");
		bindingContextNameColumn.setWidth(100);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		table.setLayoutData(gridData);
		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(35));
		tableLayout.addColumnData(new ColumnWeightData(35));
		tableLayout.addColumnData(new ColumnWeightData(30));
		table.setLayout(tableLayout);
		conflictViewer.setContentProvider(new IStructuredContentProvider() {

			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof Collection) {
					return ((Collection<?>) inputElement).toArray();
				}
				return new Object[0];
			}

			@Override
			public void dispose() {
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}
		});
		
		conflictViewer.setLabelProvider(new BindingElementLabelProvider() {
			@Override
			public String getColumnText(Object o, int index) {
				BindingConflict conflict = (BindingConflict)o;
				if (index == 0){
					return conflict.jssElementName;
				} else if (index == 1){
					return conflict.bidningConflict;
				}	else {
					return conflict.isPlatformConflict ? "Eclipse" : "Jaspersoft Studio" ;
				}
			}
		});
		
		conflictViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				StructuredSelection sel = (StructuredSelection)event.getSelection();
				if (!sel.isEmpty()){
					BindingConflict selectedEntry = (BindingConflict)sel.getFirstElement();
					fFilteredTree.getViewer().setSelection(new StructuredSelection(selectedEntry.jssElement));
					//Changing the selection doesn't correctly update the old selected row, this will force it
					fFilteredTree.getViewer().getControl().redraw();
				}
			}
		});

	}

	private void createTree(Composite parent) {
		fPatternFilter = new PatternFilter();
		GridData gridData;

		fFilteredTree = new CategoryFilterTree(parent, SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER, fPatternFilter);
		final GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = 0;
		fFilteredTree.setLayout(layout);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.FILL;
		fFilteredTree.setLayoutData(gridData);

		final TreeViewer viewer = fFilteredTree.getViewer();
		// Make sure the filtered tree has a height of ITEMS_TO_SHOW
		final Tree tree = viewer.getTree();
		tree.setHeaderVisible(true);
		final Object layoutData = tree.getLayoutData();
		if (layoutData instanceof GridData) {
			gridData = (GridData) layoutData;
			final int itemHeight = tree.getItemHeight();
			if (itemHeight > 1) {
				gridData.heightHint = ITEMS_TO_SHOW * itemHeight;
			}
		}

		BindingModelComparator comparator = new BindingModelComparator();
		viewer.setComparator(comparator);

		final TreeColumn commandNameColumn = new TreeColumn(tree, SWT.LEFT, COMMAND_NAME_COLUMN);
		commandNameColumn.setText("Name");
		tree.setSortColumn(commandNameColumn);
		tree.setSortDirection(comparator.isAscending() ? SWT.UP : SWT.DOWN);
		commandNameColumn.addSelectionListener(new ResortColumn(comparator, commandNameColumn, viewer, COMMAND_NAME_COLUMN));

		final TreeColumn triggerSequenceColumn = new TreeColumn(tree, SWT.LEFT, KEY_SEQUENCE_COLUMN);
		triggerSequenceColumn.setText("Binding");
		triggerSequenceColumn.addSelectionListener(new ResortColumn(comparator, triggerSequenceColumn, viewer, KEY_SEQUENCE_COLUMN));

		final TreeColumn whenColumn = new TreeColumn(tree, SWT.LEFT, CONTEXT_COLUMN);
		whenColumn.setText("When");
		whenColumn.addSelectionListener(new ResortColumn(comparator, whenColumn, viewer, CONTEXT_COLUMN));

		viewer.setContentProvider(new ModelContentProvider());
		viewer.setLabelProvider(new BindingElementLabelProvider());

		fFilteredTree.getPatternFilter().setIncludeLeadingWildcard(true);
		final TreeColumn[] columns = viewer.getTree().getColumns();

		columns[COMMAND_NAME_COLUMN].setWidth(240);
		columns[KEY_SEQUENCE_COLUMN].setWidth(130);
		columns[CONTEXT_COLUMN].setWidth(130);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			// When the viewer changes selection, update the model's current
			// selection
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateInformationPanel();
			}
		});
	}
	
	/**
	 * Update the information into the bottom area with the selected element into 
	 * the tree viewer, or erase them if no element is selected
	 */
	private void updateInformationPanel(){
		ISelection selection = fFilteredTree.getViewer().getSelection();
		if (selection != null){
			PreferenceBindingElement binding = (PreferenceBindingElement) ((IStructuredSelection) selection).getFirstElement();
			if (binding != null) {
				commandNameValueLabel.setText(binding.getName());
				fDescriptionText.setText(binding.getDescription());
				fWhenLabel.setText(binding.getContext());
				JSSKeyStroke[] keyStrokes = binding.getTrigger().getKeyStrokes();
				fKeySequenceText.setKeySequence(JSSKeySequence.getInstance(keyStrokes));
				removeBindingButton.setEnabled(true);
				restoreBindingButton.setEnabled(true);
				fBindingText.setEnabled(true);
				return;
			}  
		}
		//If here the selection is invalid, set the default values
		commandNameValueLabel.setText("");
		fDescriptionText.setText("");
		fWhenLabel.setText("");
		fKeySequenceText.setKeySequence(JSSKeySequence.getInstance(new JSSKeyStroke[] {}));
		removeBindingButton.setEnabled(false);
		restoreBindingButton.setEnabled(false);
		fBindingText.setEnabled(false);
	}

	private final Control createTreeControls(final Composite parent) {
		GridLayout layout;
		GridData gridData;
		int widthHint;

		// Creates controls related to the tree.
		final Composite treeControls = new Composite(parent, SWT.NONE);
		layout = new GridLayout(4, false);
		layout.marginWidth = 0;
		treeControls.setLayout(layout);
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;
		treeControls.setLayoutData(gridData);

		removeBindingButton = new Button(treeControls, SWT.PUSH);
		gridData = new GridData();
		widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		removeBindingButton.setText("Unbind Command");
		gridData.widthHint = Math.max(widthHint, removeBindingButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x) + 5;
		removeBindingButton.setLayoutData(gridData);
		removeBindingButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public final void widgetSelected(final SelectionEvent event) {
				StructuredSelection selection = (StructuredSelection) fFilteredTree.getViewer().getSelection();
				for (Object element : selection.toArray()) {
					PreferenceBindingElement selectedElement = (PreferenceBindingElement) element;
					selectedElement.setTrigger(JSSKeySequence.getInstance(new JSSKeyStroke[] {}));
				}
				fFilteredTree.getViewer().refresh();
				updateInformationPanel();
				computeOverlappingBindings();
			}
		});

		restoreBindingButton = new Button(treeControls, SWT.PUSH);
		gridData = new GridData();
		widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		restoreBindingButton.setText("Restore Default");
		gridData.widthHint = Math.max(widthHint, restoreBindingButton.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x) + 5;
		restoreBindingButton.setLayoutData(gridData);
		restoreBindingButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public final void widgetSelected(final SelectionEvent event) {
				StructuredSelection selection = (StructuredSelection) fFilteredTree.getViewer().getSelection();
				for (Object element : selection.toArray()) {
					PreferenceBindingElement selectedElement = (PreferenceBindingElement) element;
					selectedElement.setTrigger(selectedElement.getDefault());
				}
				fFilteredTree.getViewer().refresh();
				updateInformationPanel();
				computeOverlappingBindings();
			}
		});

		return treeControls;
	}
	
	/**
	 * Compute the list of binding conflict between the current bindings, considering also
	 * the binding provided by eclipse
	 * 
	 * @return a not null list of conflicts
	 */
	private List<BindingConflict> getPartialMatches(){
		//Check the conflict with the eclipse bindings
		IBindingService bindingService = (IBindingService) PlatformUI.getWorkbench().getAdapter(IBindingService.class);
		Map<KeyStroke, Binding> firstTokenBindings = new HashMap<KeyStroke, Binding>();
		List<BindingConflict> result = new ArrayList<BindingConflict>();
		
		for(Binding binding : bindingService.getBindings()){
			if (binding.getTriggerSequence().getTriggers().length > 0){
				KeyStroke eclipseKeyStroke = (KeyStroke)binding.getTriggerSequence().getTriggers()[0];
				firstTokenBindings.put(eclipseKeyStroke, binding);
			}
		}
		for(PreferenceBindingElement element : input){
			KeyStroke[] keystrokes = element.getTrigger().getEclipseKeyStrokes();
			if(keystrokes.length > 0){
				for(KeyStroke keystroke : keystrokes){
					Binding searchBinding = firstTokenBindings.get(keystroke);
					if (searchBinding != null &&  searchBinding.getParameterizedCommand() != null){
						try{
							String platformName = searchBinding.getParameterizedCommand().getName();
							String conflictSequence = searchBinding.getTriggerSequence().format();
							BindingConflict conflict = new BindingConflict(element, platformName, conflictSequence, true);
							result.add(conflict);
						} catch (Exception ex){
							ex.printStackTrace();
							JaspersoftStudioPlugin.getInstance().logError(ex);
						}
					}
				}
			}
		}
		
		//Compute the conflicts between the JSS bindings
		List<PreferenceBindingElement> sortedElements = new ArrayList<PreferenceBindingElement>(input);
		Collections.sort(sortedElements, new Comparator<PreferenceBindingElement>() {
			@Override
			public int compare(PreferenceBindingElement o1, PreferenceBindingElement o2) {
				return o1.getTrigger().getSize() - o2.getTrigger().getSize();
			}
		});
		
		for(int i = 0 ; i < sortedElements.size() - 1; i++){
			PreferenceBindingElement baseElement = sortedElements.get(i);
			for(int j = i + 1; j < sortedElements.size(); j++){
				PreferenceBindingElement currentElement = sortedElements.get(j);
				if (currentElement.contains(baseElement)){
					String platformName = currentElement.getName();
					String conflictSequence = currentElement.getTrigger().format();
					BindingConflict conflict = new BindingConflict(baseElement, platformName, conflictSequence, false);
					result.add(conflict);
				}
			}
		}
		return result;
	}
	
	/**
	 * Compute the binding conflicts and set the result in the conflict
	 * viewer
	 */
	private void computeOverlappingBindings(){
		List<BindingConflict> conflicts = getPartialMatches();
		conflictViewer.setInput(conflicts);
	}

	private void fill() {
		input = new ArrayList<PreferenceBindingElement>();
		for(BindingElement binding : ExtensionManager.getContributedBindings().values()){
			HashMap<String, JSSKeySequence> preferencesBindings = BindingsPreferencePersistence.getPreferenceBindings();
			JSSKeySequence preferenceSequence = preferencesBindings.get(binding.getId());
			if (preferenceSequence == null){
				preferenceSequence = binding.getDefault();
			}
			input.add(new PreferenceBindingElement(binding, preferenceSequence));
		}
		fFilteredTree.getViewer().setInput(input);
		TreeItem[] items = fFilteredTree.getViewer().getTree().getItems();
		if (items.length > 0 && items[0].getData() != null){
			StructuredSelection firstElementSelection = new StructuredSelection(items[0].getData());
			fFilteredTree.getViewer().setSelection(firstElementSelection);
		}
		updateInformationPanel();
		computeOverlappingBindings();
	}

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	public void applyData(Object data) {
		BindingsPreferencePersistence.writeBindingsToPreferences(input);
	}

	@Override
	public boolean performOk() {
		BindingsPreferencePersistence.writeBindingsToPreferences(input);
		return super.performOk();
	}

	@Override
	protected final void performDefaults() {
		super.performDefaults();
		for(PreferenceBindingElement model : input){
			model.setTrigger(model.getDefault());
		}
		fFilteredTree.getViewer().refresh();
		updateInformationPanel();
		computeOverlappingBindings();
	}
}
