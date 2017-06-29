/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.permission;

public class PermissionOptions {
	private boolean effectivePermissions = false;
	private boolean recipientTypeUser = false;
	private String recipientId;
	private boolean resolveAll = true;

	public boolean isEffectivePermissions() {
		return effectivePermissions;
	}

	public void setEffectivePermissions(boolean effectivePermissions) {
		this.effectivePermissions = effectivePermissions;
	}

	public boolean isRecipientTypeUser() {
		return recipientTypeUser;
	}

	public void setRecipientTypeUser(boolean recipientTypeUser) {
		this.recipientTypeUser = recipientTypeUser;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public boolean isResolveAll() {
		return resolveAll;
	}

	public void setResolveAll(boolean resolveAll) {
		this.resolveAll = resolveAll;
	}
}
