/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server;

import java.util.ArrayList;
import java.util.List;

import com.jaspersoft.jasperserver.dto.resources.ClientResourceLookup;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public abstract class AFinderUI {
	private MServerProfile serverProfile;
	private String text;
	private List<String> types = new ArrayList<String>();
	private List<String> fileTypes = new ArrayList<String>();
	private boolean showHidden = false;

	public AFinderUI(MServerProfile sp) {
		this.serverProfile = sp;
	}

	public boolean isShowHidden() {
		return showHidden;
	}

	public List<String> getFileTypes() {
		return fileTypes;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public List<String> getTypes() {
		return types;
	}

	public MServerProfile getServerProfile() {
		return serverProfile;
	}

	public abstract void showResults(List<ClientResourceLookup> res);
}
