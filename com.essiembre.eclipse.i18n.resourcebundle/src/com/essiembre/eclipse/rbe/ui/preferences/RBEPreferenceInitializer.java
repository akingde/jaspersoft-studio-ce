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
package com.essiembre.eclipse.rbe.ui.preferences;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import com.essiembre.eclipse.rbe.RBEPlugin;
import com.essiembre.eclipse.rbe.model.workbench.RBEPreferences;


/**
 * Initializes default preferences.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: cuhiodtick $ $Revision: 1.13 $ $Date: 2012/07/29 21:39:13 $
 */
public class RBEPreferenceInitializer extends AbstractPreferenceInitializer {

   /**
    * Constructor.
    */
   public RBEPreferenceInitializer() {
      super();
   }

   /**
    * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer
    *      #initializeDefaultPreferences()
    */
   @Override
   public void initializeDefaultPreferences() {
      Preferences prefs = RBEPlugin.getDefault().getPluginPreferences();

      //General
      prefs.setDefault(RBEPreferences.CONVERT_ENCODED_TO_UNICODE, true);
      prefs.setDefault(RBEPreferences.FIELD_TAB_INSERTS, true);
      prefs.setDefault(RBEPreferences.KEY_TREE_HIERARCHICAL, true);
      prefs.setDefault(RBEPreferences.KEY_TREE_EXPANDED, true);
      prefs.setDefault(RBEPreferences.SUPPORT_FRAGMENTS, true);
      prefs.setDefault(RBEPreferences.LOAD_ONLY_FRAGMENT_RESOURCES, false);

      //Formatting
      prefs.setDefault(RBEPreferences.CONVERT_UNICODE_TO_ENCODED, true);
      prefs.setDefault(RBEPreferences.CONVERT_UNICODE_TO_ENCODED_UPPER, true);

      prefs.setDefault(RBEPreferences.SPACES_AROUND_EQUAL_SIGNS, true);

      prefs.setDefault(RBEPreferences.KEY_GROUP_SEPARATOR, "."); //$NON-NLS-1$
      prefs.setDefault(RBEPreferences.ALIGN_EQUAL_SIGNS, true);
      prefs.setDefault(RBEPreferences.SHOW_GENERATOR, true);
      prefs.setDefault(RBEPreferences.KEY_TREE_HIERARCHICAL, true);

      prefs.setDefault(RBEPreferences.GROUP_KEYS, true);
      prefs.setDefault(RBEPreferences.GROUP_LEVEL_DEEP, 1);
      prefs.setDefault(RBEPreferences.GROUP_LINE_BREAKS, 1);
      prefs.setDefault(RBEPreferences.GROUP_ALIGN_EQUAL_SIGNS, true);

      prefs.setDefault(RBEPreferences.WRAP_CHAR_LIMIT, 80);
      prefs.setDefault(RBEPreferences.WRAP_INDENT_SPACES, 8);

      prefs.setDefault(RBEPreferences.NEW_LINE_TYPE, RBEPreferences.NEW_LINE_UNIX);

      prefs.setDefault(RBEPreferences.KEEP_EMPTY_FIELDS, false);

      // Reporting/Performance
      prefs.setDefault(RBEPreferences.REPORT_MISSING_VALUES, true);
      prefs.setDefault(RBEPreferences.REPORT_DUPL_VALUES, true);
      prefs.setDefault(RBEPreferences.REPORT_SIM_VALUES_WORD_COMPARE, true);
      prefs.setDefault(RBEPreferences.REPORT_SIM_VALUES_PRECISION, 0.75d);

      prefs.setDefault(RBEPreferences.NO_TREE_IN_EDITOR, false);

      prefs.setDefault(RBEPreferences.MIN_HEIGHT, 80);
      prefs.setDefault(RBEPreferences.AUTO_ADJUST, true);

   }

}
