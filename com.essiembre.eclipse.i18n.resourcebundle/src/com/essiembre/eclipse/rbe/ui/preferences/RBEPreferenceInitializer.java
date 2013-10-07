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


import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;

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
	   
	  IEclipsePreferences prefs = InstanceScope.INSTANCE.getNode(RBEPlugin.ID); // does all the above behind the scene

      //General
      prefs.putBoolean(RBEPreferences.CONVERT_ENCODED_TO_UNICODE, true);
      prefs.putBoolean(RBEPreferences.FIELD_TAB_INSERTS, true);
      prefs.putBoolean(RBEPreferences.KEY_TREE_HIERARCHICAL, true);
      prefs.putBoolean(RBEPreferences.KEY_TREE_EXPANDED, true);
      prefs.putBoolean(RBEPreferences.SUPPORT_FRAGMENTS, true);
      prefs.putBoolean(RBEPreferences.LOAD_ONLY_FRAGMENT_RESOURCES, false);

      //Formatting
      prefs.putBoolean(RBEPreferences.CONVERT_UNICODE_TO_ENCODED, true);
      prefs.putBoolean(RBEPreferences.CONVERT_UNICODE_TO_ENCODED_UPPER, true);

      prefs.putBoolean(RBEPreferences.SPACES_AROUND_EQUAL_SIGNS, true);

      prefs.put(RBEPreferences.KEY_GROUP_SEPARATOR, "."); //$NON-NLS-1$
      prefs.putBoolean(RBEPreferences.ALIGN_EQUAL_SIGNS, true);
      prefs.putBoolean(RBEPreferences.SHOW_GENERATOR, true);
      prefs.putBoolean(RBEPreferences.KEY_TREE_HIERARCHICAL, true);

      prefs.putBoolean(RBEPreferences.GROUP_KEYS, true);
      prefs.putInt(RBEPreferences.GROUP_LEVEL_DEEP, 1);
      prefs.putInt(RBEPreferences.GROUP_LINE_BREAKS, 1);
      prefs.putBoolean(RBEPreferences.GROUP_ALIGN_EQUAL_SIGNS, true);

      prefs.putInt(RBEPreferences.WRAP_CHAR_LIMIT, 80);
      prefs.putInt(RBEPreferences.WRAP_INDENT_SPACES, 8);

      prefs.putInt(RBEPreferences.NEW_LINE_TYPE, RBEPreferences.NEW_LINE_UNIX);

      prefs.putBoolean(RBEPreferences.KEEP_EMPTY_FIELDS, false);

      // Reporting/Performance
      prefs.putBoolean(RBEPreferences.REPORT_MISSING_VALUES, true);
      prefs.putBoolean(RBEPreferences.REPORT_DUPL_VALUES, true);
      prefs.putBoolean(RBEPreferences.REPORT_SIM_VALUES_WORD_COMPARE, true);
      prefs.putDouble(RBEPreferences.REPORT_SIM_VALUES_PRECISION, 0.75d);

      prefs.putBoolean(RBEPreferences.NO_TREE_IN_EDITOR, false);

      prefs.putInt(RBEPreferences.MIN_HEIGHT, 80);
      prefs.putBoolean(RBEPreferences.AUTO_ADJUST, true);

   }

}
