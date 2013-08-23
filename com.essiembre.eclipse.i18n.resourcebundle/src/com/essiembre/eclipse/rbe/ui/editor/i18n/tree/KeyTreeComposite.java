/*
 * Copyright (C) 2003, 2004  Pascal Essiembre, Essiembre Consultant Inc.
 * 
 * This file is part of Essiembre ResourceBundle Editor.
 * 
 * Essiembre ResourceBundle Editor is free software; you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * Essiembre ResourceBundle Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Essiembre ResourceBundle Editor; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, 
 * Boston, MA  02111-1307  USA
 */
package com.essiembre.eclipse.rbe.ui.editor.i18n.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import com.essiembre.eclipse.rbe.RBEPlugin;
import com.essiembre.eclipse.rbe.model.bundle.BundleGroup;
import com.essiembre.eclipse.rbe.model.tree.KeyTree;
import com.essiembre.eclipse.rbe.model.tree.KeyTreeItem;
import com.essiembre.eclipse.rbe.model.tree.updater.FlatKeyTreeUpdater;
import com.essiembre.eclipse.rbe.model.tree.updater.GroupedKeyTreeUpdater;
import com.essiembre.eclipse.rbe.model.tree.visitors.KeysStartingWithVisitor;
import com.essiembre.eclipse.rbe.model.workbench.RBEPreferences;
import com.essiembre.eclipse.rbe.ui.UIUtils;

/**
 * Tree for displaying and navigating through resource bundle keys.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: cuhiodtick $ $Revision: 1.16 $ $Date: 2012/09/02 15:57:48 $
 */
public class KeyTreeComposite extends Composite {

    /** Image for tree mode toggle button. */
    private Image treeToggleImage;
    /** Image for flat mode toggle button. */
    private Image flatToggleImage;

    /*default*/ Cursor waitCursor;
    /*default*/ Cursor defaultCursor;
    
    /** Key Tree Viewer. */
    /*default*/ TreeViewer treeViewer;
    /** TreeViewer label provider. */
    protected KeyTreeLabelProvider labelProvider;
    
    /** Flat or Tree mode? */
    private boolean keyTreeHierarchical = 
            RBEPreferences.getKeyTreeHierarchical();
    
    /** Text box to add a new key. */
    /*default*/ Text addTextBox;
    
    /** Key tree. */
    /*default*/ KeyTree keyTree;
    
    /** Whether to synchronize the add text box with tree key selection. */
    /*default*/ boolean syncAddTextBox = true;

    /** Contributes menu items to the tree viewer. */
    private TreeViewerContributor  treeviewerContributor;
    
    private Text filterTextBox;
    
    /**
     * Constructor.
     * @param parent parent composite
     * @param keyTree key tree
     */
    public KeyTreeComposite(Composite parent, final KeyTree keyTree) {
        super(parent, SWT.BORDER);
        this.keyTree = keyTree;

        treeToggleImage = UIUtils.getImage(UIUtils.IMAGE_LAYOUT_HIERARCHICAL);
        flatToggleImage = UIUtils.getImage(UIUtils.IMAGE_LAYOUT_FLAT);
        waitCursor = UIUtils.createCursor(SWT.CURSOR_WAIT);
        defaultCursor = UIUtils.createCursor(SWT.CURSOR_ARROW);

        setLayout(new GridLayout(1, false));
        createTopSection();
        createMiddleSection();
        createBottomSection();
    }

    /**
     * Gets the tree viewer.
     * @return tree viewer
     */
    public TreeViewer getTreeViewer() {
        return treeViewer;
    }
    
    public void setFilter(String filter) {
       filterTextBox.setText(filter);
    }
    public String getFilter() {
       return filterTextBox.getText();
    }
    
    /**
     * Gets the selected key tree item.
     * @return key tree item
     */
    public KeyTreeItem getSelection() {
        IStructuredSelection selection = 
                (IStructuredSelection) treeViewer.getSelection();
        return (KeyTreeItem) selection.getFirstElement();
    }

    /**
     * Gets selected key.
     * @return selected key
     */
    public String getSelectedKey() {
        String key = null;
        KeyTreeItem item = getSelection();
        if (item != null) {
            key = item.getId();
        }
        return key;
    }

    /**
     * @see org.eclipse.swt.widgets.Widget#dispose()
     */
    public void dispose() {
       super.dispose();

       waitCursor.dispose();
       defaultCursor.dispose();
       //        treeviewerContributor.dispose();
       labelProvider.dispose();
       addTextBox.dispose();

       keyTree = null;
    }
    

    /**
     * Deletes a key or group of key.
     */
    protected void deleteKeyOrGroup() {
        KeyTreeItem selectedItem = getSelection();
        String key = selectedItem.getId();
        String msgHead = null;
        String msgBody = null;
        if (selectedItem.getChildren().size() == 0) {
            msgHead = RBEPlugin.getString(
                    "dialog.delete.head.single"); //$NON-NLS-1$
            msgBody = RBEPlugin.getString(
                    "dialog.delete.body.single", key); //$NON-NLS-1$
        } else {
            msgHead = RBEPlugin.getString(
                    "dialog.delete.head.multiple"); //$NON-NLS-1$
            msgBody = RBEPlugin.getString(
                    "dialog.delete.body.multiple", //$NON-NLS-1$ 
                    selectedItem.getName());
        }
        MessageBox msgBox = new MessageBox(
                getShell(), SWT.ICON_QUESTION|SWT.OK|SWT.CANCEL);
        msgBox.setMessage(msgBody);
        msgBox.setText(msgHead);
        if (msgBox.open() == SWT.OK) {
            BundleGroup bundleGroup = keyTree.getBundleGroup();
            Collection items = new ArrayList();
            items.add(selectedItem);
            items.addAll(selectedItem.getNestedChildren());
            for (Iterator iter = items.iterator(); iter.hasNext();) {
                KeyTreeItem item = (KeyTreeItem) iter.next();
                bundleGroup.removeKey(item.getId());
            }
        }
    }    

    
    /**
     * Creates the top section (toggle buttons) of this composite.
     */
    private void createTopSection() {
        Composite topComposite = new Composite(this, SWT.NONE);
        GridLayout gridLayout = new GridLayout(2, false);
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        topComposite.setLayout(gridLayout);
        topComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        filterTextBox = new Text(topComposite, SWT.BORDER);
//        filterTextBox.setText("");
        filterTextBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        filterTextBox.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                keyTree.filterKeyItems(filterTextBox.getText());
                treeViewer.getControl().setRedraw(false);
                treeViewer.refresh();
                if(!filterTextBox.getText().isEmpty())
                   treeViewer.expandAll();
                treeViewer.getControl().setRedraw(true);
            }
        });
        
        Composite topRightComposite = new Composite(topComposite, SWT.NONE);
        gridLayout = new GridLayout(2, false);
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        topRightComposite.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalAlignment = GridData.END;
//        gridData.verticalAlignment = GridData.CENTER;
//        gridData.grabExcessHorizontalSpace = true;
        topRightComposite.setLayoutData(gridData);
        
        final Button hierModeButton = new Button(topRightComposite, SWT.TOGGLE);
        hierModeButton.setImage(treeToggleImage);
        hierModeButton.setToolTipText(
                RBEPlugin.getString("key.layout.tree")); //$NON-NLS-1$
        final Button flatModeButton = new Button(topRightComposite, SWT.TOGGLE);
        flatModeButton.setImage(flatToggleImage);
        flatModeButton.setToolTipText(
                RBEPlugin.getString("key.layout.flat")); //$NON-NLS-1$
        if (keyTreeHierarchical) {
            hierModeButton.setSelection(true);
            hierModeButton.setEnabled(false);
        } else {
            flatModeButton.setSelection(true);
            flatModeButton.setEnabled(false);
        }
        //TODO merge the two listeners into one
        hierModeButton.addSelectionListener(new SelectionAdapter () {
            public void widgetSelected(SelectionEvent event) {
                if (hierModeButton.getSelection()) {
                    flatModeButton.setSelection(false);
                    flatModeButton.setEnabled(true);
                    hierModeButton.setEnabled(false);
                    setCursor(waitCursor);
                    setVisible(false);
                    keyTree.setUpdater(new GroupedKeyTreeUpdater(RBEPreferences.getKeyGroupSeparator()));
//                    treeviewerContributor.getMenuItem(TreeViewerContributor.MENU_EXPAND).setEnabled(true);
//                    treeviewerContributor.getMenuItem(TreeViewerContributor.MENU_COLLAPSE).setEnabled(true);
                    if (RBEPreferences.getKeyTreeExpanded()) {
                       treeViewer.getControl().setRedraw(false);
                       treeViewer.expandAll();
                       treeViewer.getControl().setRedraw(true);    
                    }
                    selectKeyTreeItem(addTextBox.getText());
                    setVisible(true);
                    setCursor(defaultCursor);
                }
            }
        });
        flatModeButton.addSelectionListener(new SelectionAdapter () {
            public void widgetSelected(SelectionEvent event) {
                if (flatModeButton.getSelection()) {
                    hierModeButton.setSelection(false);
                    hierModeButton.setEnabled(true);
                    flatModeButton.setEnabled(false);
                    setCursor(waitCursor);
                    setVisible(false);
                    keyTree.setUpdater(new FlatKeyTreeUpdater());
//                    treeviewerContributor.getMenuItem(TreeViewerContributor.MENU_EXPAND).setEnabled(false);
//                    treeviewerContributor.getMenuItem(TreeViewerContributor.MENU_COLLAPSE).setEnabled(false);
                    selectKeyTreeItem(addTextBox.getText());
                    setVisible(true);
                    setCursor(defaultCursor);                    
                }
            }
        });
    }
    
    /**
     * Creates the middle (tree) section of this composite.
     */
    private void createMiddleSection() {

        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;

        treeViewer = new TreeViewer(this, SWT.SINGLE | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        treeViewer.setContentProvider(new KeyTreeContentProvider());
        labelProvider = new KeyTreeLabelProvider();
        treeViewer.setLabelProvider(labelProvider);
        treeViewer.setUseHashlookup(true);
        treeViewer.setInput(keyTree);
        if (RBEPreferences.getKeyTreeExpanded()) {
            treeViewer.expandAll();
        }
        treeViewer.getTree().setLayoutData(gridData);      
        treeViewer.getTree().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                if (event.character == SWT.DEL) {
                    deleteKeyOrGroup();
                }
            }
        });
        treeViewer.addSelectionChangedListener(
                new ISelectionChangedListener() {
                    public void selectionChanged(SelectionChangedEvent event) {
                        if (syncAddTextBox && getSelectedKey() != null) {
                            addTextBox.setText(getSelectedKey());
                            keyTree.selectKey(getSelectedKey());
                        }
                        syncAddTextBox = true;
                    }
        });
        treeViewer.getTree().addMouseListener(new MouseAdapter() {
            public void mouseDoubleClick(MouseEvent event) {
                Object element = getSelection();
                if (treeViewer.isExpandable(element)) {
                    if (treeViewer.getExpandedState(element)) {
                        treeViewer.collapseToLevel(element, 1);
                    } else {
                        treeViewer.expandToLevel(element, 1);
                    }
                }
            }
        });
        
        ViewerFilter filter = new ViewerFilter() {
            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
//                if (parentElement instanceof KeyTreeItem) {
//                    KeyTreeItem parent = (KeyTreeItem) parentElement;
//                    if (parent.isSelected())
//                        return true;
//                }
                if (element instanceof KeyTreeItem) {
                    KeyTreeItem item = (KeyTreeItem) element;
                    return item.isVisible();
                }
                return true;
//                String text = filterTextBox.getText();
//                if (element instanceof KeyTreeItem) {
//                    KeyTreeItem item = (KeyTreeItem) element;
//                    if (item.getId().indexOf(text) != -1)
//                        return true;
//                }
//                return true;
            }
        };
        treeViewer.addFilter(filter);
        
        treeviewerContributor = new TreeViewerContributor(keyTree, treeViewer);
        treeviewerContributor.createControl(this);

    }
    
    /**
     * Creates the botton section (add field/button) of this composite.
     */
    private void createBottomSection() {
        Composite bottomComposite = new Composite(this, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.horizontalSpacing = 0;
        gridLayout.verticalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        bottomComposite.setLayout(gridLayout);
        GridData gridData = new GridData();
        gridData.horizontalAlignment = GridData.FILL;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.grabExcessHorizontalSpace = true;
        bottomComposite.setLayoutData(gridData);

        // Text box
        addTextBox = new Text(bottomComposite, SWT.BORDER);
        gridData = new GridData();
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        addTextBox.setLayoutData(gridData);

        // Add button
        final Button addButton = new Button(bottomComposite, SWT.PUSH);
        addButton.setText(RBEPlugin.getString("key.add")); //$NON-NLS-1$
        addButton.setEnabled(false);
        addButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                addKey();
            }
        });

        addTextBox.addKeyListener(new KeyAdapter() {
           public void keyReleased( KeyEvent event ) {
              if ( event.character == SWT.CR  && addButton.isEnabled() ) {
                 addKey();
              }  
           }
        });
        addTextBox.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent event) {
                String key = addTextBox.getText();
                boolean keyExist = keyTree.getBundleGroup().isKey(key);
                if (keyExist || key.length() == 0) {
                    addButton.setEnabled(false);
                } else {
                    addButton.setEnabled(true);
                }
                if ( key.length() > 0 && !key.equals(getSelectedKey()) ) {
                   KeysStartingWithVisitor visitor = new KeysStartingWithVisitor();
                   keyTree.accept(visitor, key);
                   KeyTreeItem item = visitor.getKeyTreeItem();
                   if ( item != null ) {
                      syncAddTextBox = false;
                      selectKeyTreeItem(item);
     
                      if ( key.equals(getSelectedKey()) ) {
                         keyTree.selectKey(getSelectedKey());
                      }
                   }
                }                
            }
        });
    }
    
    /**
     * Adds a key to the tree, based on content from add field.
     */
    /*default*/ void addKey() {
        String key = addTextBox.getText();
        keyTree.getBundleGroup().addKey(key);
        selectKeyTreeItem(key);
    }
    
    /**
     * Selected the key tree item matching given key.
     * @param key key to select
     */
    public void selectKeyTreeItem(String key) { 
        selectKeyTreeItem(keyTree.getKeyTreeItem(key));
    }
    
    /**
     * Selected the key tree item matching given key tree item.
     * @param item key tree item to select
     */
    /*default*/ void selectKeyTreeItem(KeyTreeItem item) {
        if (item != null) {
            treeViewer.setSelection(new StructuredSelection(item), true);
        }
    }
    
//	public KeyTreeItem getNextKeyTreeItem() {
//		// Either find the next sibbling
//		KeyTreeItem currentItem = keyTree.getKeyTreeItem(keyTree.getSelectedKey());
//		return currentItem.getNextLeaf();
//	}
}
