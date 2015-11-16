/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.net.util.Base64;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.plugin.ExtensionManager;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.plugin.PaletteGroup;
import com.jaspersoft.studio.preferences.util.FieldEditorOverlayPage;
import com.jaspersoft.studio.utils.Misc;

/*
 * 
 */
public class PalettePreferencePage extends FieldEditorOverlayPage {

	public PalettePreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
		setDescription(Messages.PalettePreferencePage_0);
	}

	private static ExtensionManager m = JaspersoftStudioPlugin.getExtensionManager();

	/**
	 *
	 */
	public void createFieldEditors() {
		Map<String, PaletteGroup> map = new TreeMap<String, PaletteGroup>();

		List<PaletteGroup> pgroups = m.getPaletteGroups();
		PaletteGroup pgc = new PaletteGroup();
		pgc.setId(IPaletteContributor.KEY_COMMON_ELEMENTS);
		pgc.setName(Messages.common_elements);
		pgc.setImage("icons/resources/elementgroup-16.png"); //$NON-NLS-1$
		map.put(pgc.getId(), pgc);
		pgroups.add(0, pgc);

		for (PaletteGroup p : pgroups)
			map.put(p.getId(), p);

		Composite parent = getFieldEditorParent();

		Map<String, List<PaletteEntry>> me = m.getPaletteEntries();
		grmap = new HashMap<String, Group>();
		for (String key : me.keySet()) {
			PaletteGroup pg = map.get(key);
			if (pg == null)
				pg = pgc;
			Group grcmp = grmap.get(key);
			if (grcmp == null) {
				grcmp = new Group(parent, SWT.NONE);
				grcmp.setText(pg.getName());
				grcmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				grmap.put(key, grcmp);
			}
			List<PaletteEntry> lpe = me.get(key);
			for (PaletteEntry pe : lpe) {
				String id = getId(pe);

				BooleanFieldEditor be = new BooleanFieldEditor(id, pe.getLabel(), grcmp);
				Control c = be.getDescriptionControl(grcmp);
				if (c instanceof Button) {
					c.setToolTipText(pe.getDescription());
					((Button) c).setImage(JaspersoftStudioPlugin.getInstance().getImage(pe.getSmallIcon()));
				}
				addField(be);
			}
		}

	}

	public static final String getId(PaletteEntry pe) {
		String id = pe.getId();
		if (Misc.isNullOrEmpty(pe.getId()))
			id = "com.jaspersoft.studio.palette." + Base64.encodeBase64String(pe.getLabel().getBytes());
		return id;
	}

	protected void adjustGridLayout() {
		for (Group gr : grmap.values())
			gr.setLayout(new GridLayout(2, false));
	}

	public static void getDefaults(IPreferenceStore store) {
		Map<String, List<PaletteEntry>> me = m.getPaletteEntries();
		for (String key : me.keySet()) {
			List<PaletteEntry> lpe = me.get(key);
			for (PaletteEntry pe : lpe)
				store.setDefault(pe.getId(), false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	public static final String PAGE_ID = "com.jaspersoft.studio.preferences.PalettePreferencePage.property"; //$NON-NLS-1$
	private Map<String, Group> grmap;

	@Override
	protected String getPageId() {
		return PAGE_ID;
	}
}
