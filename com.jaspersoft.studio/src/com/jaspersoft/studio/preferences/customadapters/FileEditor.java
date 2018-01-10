/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.customadapters;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

/**
 * A field editor to add/edit data adapter definitions paths
 * 
 * @author Orlandin Marco
 */
public class FileEditor extends ListEditor {

    /**
     * The last path, or <code>null</code> if none.
     */
    private String lastPath;

    /**
     * The special label text for directory chooser,
     * or <code>null</code> if none.
     */
    private String dirChooserLabelText;

    /**
     * Creates a new path field editor
     */
    protected FileEditor() {
    }

    /**
     * Creates a path field editor.
     *
     * @param name the name of the preference this field editor works on
     * @param labelText the label text of the field editor
     * @param dirChooserLabelText the label text displayed for the directory chooser
     * @param parent the parent of the field editor's control
     */
    public FileEditor(String name, String labelText, String dirChooserLabelText, Composite parent) {
        init(name, labelText);
        this.dirChooserLabelText = dirChooserLabelText;
        createControl(parent);
    }

    @Override
	protected String createList(String[] items) {
        StringBuffer path = new StringBuffer("");//$NON-NLS-1$

        for (int i = 0; i < items.length; i++) {
            path.append(items[i]);
            path.append(File.pathSeparator);
        }
        return path.toString();
    }

    @Override
	protected String getNewInputObject() {

        FileDialog dialog = new FileDialog(getShell(), SWT.SHEET);
        dialog.setFilterExtensions(new String[] {"*.json", "*.*"});
        if (dirChooserLabelText != null) {
        	dialog.setText(dirChooserLabelText);
		}
        if (lastPath != null) {
        	File lastPathFile = new File(lastPath);
            if (lastPathFile.exists()) {
				dialog.setFilterPath(lastPathFile.getParent());
			}
        }
        String dir = dialog.open();
        if (dir != null) {
            dir = dir.trim();
            if (dir.length() == 0) {
				return null;
			}
            lastPath = dir;
        }
        return dir;
    }

    @Override
	protected String[] parseString(String stringList) {
        StringTokenizer st = new StringTokenizer(stringList, File.pathSeparator
                + "\n\r");//$NON-NLS-1$
        ArrayList<Object> v = new ArrayList<>();
        while (st.hasMoreElements()) {
            v.add(st.nextElement());
        }
        return v.toArray(new String[v.size()]);
    }
}
