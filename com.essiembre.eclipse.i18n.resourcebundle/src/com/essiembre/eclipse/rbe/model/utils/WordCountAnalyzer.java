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
package com.essiembre.eclipse.rbe.model.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Compares two strings (case insensitive) and returns a proximity level
 * based on how many words there are, and how many words are the same 
 * in both strings.  Non-string objects are converted to strings using
 * the <code>toString()</code> method.
 * @author Pascal Essiembre
 * @version $Author: essiembre $ $Revision: 1.2 $ $Date: 2005/07/30 22:10:55 $
 */
public class WordCountAnalyzer implements ProximityAnalyzer {

    private static final ProximityAnalyzer INSTANCE = new WordCountAnalyzer();
    private static final String WORD_SPLIT_PATTERN =
            "\r\n|\r|\n|\\s"; //$NON-NLS-1$

    /**
     * Constructor.
     */
    private WordCountAnalyzer() {
        //TODO add case sensitivity?
        super();
    }

    /**
     * Gets the unique instance.
     * @return a proximity analyzer
     */
    public static ProximityAnalyzer getInstance() {
        return INSTANCE;
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.utils.ProximityAnalyzer
     *         #analyse(java.lang.Object, java.lang.Object)
     */
    public double analyse(Object obj1, Object obj2) {
        if (obj2 == null || obj2 == null) {
            return 0;
        }
        
        Collection str1 = new ArrayList(
                Arrays.asList(obj1.toString().split(WORD_SPLIT_PATTERN)));
        Collection str2 = new ArrayList(
                Arrays.asList(obj2.toString().split(WORD_SPLIT_PATTERN)));
        
        int maxWords = Math.max(str1.size(), str2.size());
        if (maxWords == 0) {
            return 0;
        }
        
        int matchedWords = 0;
        for (Iterator iter = str1.iterator(); iter.hasNext();) {
            String str = (String) iter.next();
            if (str2.remove(str)) {
                matchedWords++;
            }
        }

        return (double) matchedWords / (double) maxWords;
    }

}
