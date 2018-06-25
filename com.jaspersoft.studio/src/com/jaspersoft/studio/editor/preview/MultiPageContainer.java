/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.stats.Statistics;
import com.jaspersoft.studio.editor.preview.view.APreview;

public class MultiPageContainer {
	protected LinkedHashMap<String, APreview> pmap = new LinkedHashMap<>();
	private List<String> keys;
	private Composite composite;
	private Statistics stats;

	public APreview getViewer(String key) {
		return pmap.get(key);
	}

	public void switchView(String key) {
		switchView(stats, pmap.get(key));
	}

	public void switchView(Statistics stats, String key) {
		switchView(stats, pmap.get(key));
	}

	public void switchView(Statistics stats, APreview view) {
		this.stats = stats;
		if (composite.isDisposed())
			return;
		if (((StackLayout) composite.getLayout()).topControl != view.getControl()) {
			((StackLayout) composite.getLayout()).topControl = view.getControl();
			composite.layout();
		}
		// here should update exporters properties
	}

	public void afterSwitchView() {
		// do nothing
	}

	public List<String> getKeys() {
		if (keys == null)
			keys = new ArrayList<>(pmap.keySet());
		return keys;
	}

	public void populate(Composite composite, LinkedHashMap<String, APreview> pmap) {
		this.composite = composite;
		this.pmap = pmap;
	}

	public void dispose() {
		for (APreview p : pmap.values()) {
			if (p != null)
				p.dispose();
		}
	}

	public void setEnabled(boolean enabled) {
		for (APreview p : pmap.values()) {
			if (p != null)
				p.setEnabled(enabled);
		}
	}
}
