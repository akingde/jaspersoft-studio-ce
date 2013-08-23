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
package com.essiembre.eclipse.rbe.model.bundle.visitors;

import java.util.ArrayList;
import java.util.Collection;

import com.essiembre.eclipse.rbe.model.bundle.BundleEntry;
import com.essiembre.eclipse.rbe.model.bundle.BundleVisitorAdapter;
import com.essiembre.eclipse.rbe.model.utils.ProximityAnalyzer;
import com.essiembre.eclipse.rbe.model.utils.WordCountAnalyzer;
import com.essiembre.eclipse.rbe.model.workbench.RBEPreferences;

/**
 * Finds bundle entries having values similar (case insensitive) to the bundle 
 * entry given as the pass-along argument.  If no proximity analyzer is set,
 * <code>WordCountAnalyser</code> is used.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: essiembre $ $Revision: 1.2 $ $Date: 2005/07/30 22:10:55 $
 */
public class SimilarValuesVisitor extends BundleVisitorAdapter {

    /** Holder for bundle entries having similars values. */
    private final Collection similars = new ArrayList();

    /** Proximity analyzer used to find similarities. */
    private ProximityAnalyzer analyzer = WordCountAnalyzer.getInstance();
    
    /**
     * Constructor.
     */
    public SimilarValuesVisitor() {
        super();
    }

    /**
     * @see com.essiembre.eclipse.rbe.model.bundle.IBundleVisitor
     *         #visitBundleEntry(
     *                 com.essiembre.eclipse.rbe.model.bundle.BundleEntry,
     *                 java.lang.Object)
     */
    public void visitBundleEntry(BundleEntry entry, Object passAlongArgument) {
        
        BundleEntry entryToMatch = (BundleEntry) passAlongArgument;
        if (entry != entryToMatch
                && entry != null && entryToMatch != null
                && entry.getValue().length() > 0
                && analyzer.analyse(
                        entry.getValue().toLowerCase(), 
                        entryToMatch.getValue().toLowerCase())
                        >= RBEPreferences.getReportSimilarValuesPrecision()) {
            similars.add(entry);
        }
    }
    
    
    
    /**
     * Gets the proximity analyzer.
     * @return Returns the proximity analyzer.
     */
    public ProximityAnalyzer getProximityAnalyzer() {
        return analyzer;
    }
    /**
     * Sets the proximity analyzer.
     * @param analyzer proximity analyzer
     */
    public void setProximityAnalyzer(ProximityAnalyzer analyzer) {
        this.analyzer = analyzer;
    }
    
    /**
     * Gets a collection of similar <code>BundleEntry</code> instance.
     * @return bundle entries with similar values
     */
    public Collection getSimilars() {
        return similars;
    }
    
    /**
     * Clears the list of duplicate values.
     */
    public void clear() {
        similars.clear();
    }
    
}