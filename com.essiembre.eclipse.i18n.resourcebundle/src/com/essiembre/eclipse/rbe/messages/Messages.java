/*******************************************************************************
 * Copyright (C) 2010 - 2014 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.essiembre.eclipse.rbe.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	public static final String BUNDLE_NAME = "com.essiembre.eclipse.rbe.messages.messages"; //$NON-NLS-1$

	public static String dialog_delete_body_multiple;
	public static String dialog_delete_body_single;
	public static String dialog_delete_head_multiple;
	public static String dialog_delete_head_single;
	public static String dialog_duplicate_body_multiple;
	public static String dialog_duplicate_body_single;
	public static String dialog_duplicate_head_multiple;
	public static String dialog_duplicate_head_single;
	public static String dialog_identical_body;
	public static String dialog_identical_head;
	public static String dialog_new_body;
	public static String dialog_new_head;
	public static String dialog_rename_body_multiple;
	public static String dialog_rename_body_single;
	public static String dialog_rename_head_multiple;
	public static String dialog_rename_head_single;
	public static String dialog_similar_body;
	public static String dialog_similar_head;

	public static String editor_content_desc;
	public static String editor_default;
	public static String editor_new_create;
	public static String editor_new_tab;
	public static String editor_new_title;
	public static String editor_properties;
	public static String editor_wiz_add;
	public static String editor_wiz_browse;
	public static String editor_wiz_bundleName;
	public static String editor_wiz_creating;
	public static String editor_wiz_createFolder;
	public static String editor_wiz_desc;
	public static String editor_wiz_error_bundleName;
	public static String editor_wiz_error_container;
	public static String editor_wiz_error_noLocale;
	public static String editor_wiz_error_wrongType;
	public static String editor_wiz_error_noPrject;
	public static String editor_wiz_error_extension;
	public static String editor_wiz_folder;
	public static String editor_wiz_opening;
	public static String editor_wiz_remove;
	public static String editor_wiz_selectFolder;
	public static String editor_wiz_selected;
	public static String editor_wiz_title;
	public static String editor_wiz_window_title;

	public static String error_init_badencoding;
	public static String error_init_ui;
	public static String error_newfile_cannotCreate;
	public static String error_newfile_cannotOpen;
	public static String error_seeLogs;

	public static String key_add;
	public static String key_collapseAll;
	public static String key_comment;
	public static String key_delete;
	public static String key_duplicate;
	public static String key_expandAll;
	public static String key_filter_incomplete;
	public static String key_layout_flat;
	public static String key_layout_tree;
	public static String key_new;
	public static String key_rename;
	public static String key_uncomment;

	public static String prefs_alignEquals;
	public static String prefs_convertEncoded;
	public static String prefs_convertUnicode;
	public static String prefs_convertUnicode_upper;
	public static String prefs_fieldTabInserts;
	public static String prefs_groupAlignEquals;
	public static String prefs_groupKeys;
	public static String prefs_groupSep;
	public static String prefs_keepEmptyFields;
	public static String prefs_keyTree_expanded;
	public static String prefs_keyTree_hierarchical;
	public static String prefs_levelDeep;
	public static String prefs_levelDeep_error;
	public static String prefs_linesBetween;
	public static String prefs_linesBetween_error;
	public static String prefs_newline_force;
	public static String prefs_newline_nice;
	public static String prefs_noTreeInEditor;
	public static String prefs_perform_duplVals;
	public static String prefs_perform_intro1;
	public static String prefs_perform_intro2;
	public static String prefs_perform_missingVals;
	public static String prefs_perform_simVals;
	public static String prefs_perform_simVals_levensthein;
	public static String prefs_perform_simVals_precision;
	public static String prefs_perform_simVals_precision_error;
	public static String prefs_perform_simVals_wordCount;
	public static String prefs_showGeneratedBy;
	public static String prefs_spacesAroundEquals;
	public static String prefs_supportNL;
	public static String prefs_supportFragments;
	public static String prefs_loadOnlyFragmentResources;
	public static String prefs_wrapAlignEquals;
	public static String prefs_wrapIndent;
	public static String prefs_wrapIndent_error;
	public static String prefs_wrapLines;
	public static String prefs_wrapLinesChar;
	public static String prefs_wrapLinesChar_error;

	public static String prefs_properties_desc;
	public static String prefs_autoAdjust;
	public static String prefs_minHeight;
	public static String prefs_minHeight_error;

	public static String resource_wiz_title;
	public static String resource_wiz_description;
	public static String resource_wiz_information_title;
	public static String resource_wiz_information_plugin;
	public static String resource_wiz_information_package;
	public static String resource_wiz_information_name;
	public static String resource_wiz_information_description;
	public static String resource_wiz_information_noPackage;

	public static String selector_country;
	public static String selector_language;
	public static String selector_title;
	public static String selector_variant;

	public static String translation_wiz_title;
	public static String translation_wiz_description;
	public static String translation_wiz_name;

	public static String value_comment_tooltip;
	public static String value_duplicate_tooltip;
	public static String value_goto_tooltip;
	public static String value_similar_tooltip;
	public static String value_uncomment_tooltip;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
