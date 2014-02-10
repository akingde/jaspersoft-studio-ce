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
package com.essiembre.eclipse.rbe.ui.editor.i18n.tree;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

import com.essiembre.eclipse.rbe.RBEPlugin;
import com.essiembre.eclipse.rbe.model.tree.KeyTreeItem;
import com.essiembre.eclipse.rbe.model.tree.visitors.IsCommentedVisitor;
import com.essiembre.eclipse.rbe.model.tree.visitors.IsMissingValueVisitor;
import com.essiembre.eclipse.rbe.model.workbench.RBEPreferences;
import com.essiembre.eclipse.rbe.ui.OverlayImageIcon;
import com.essiembre.eclipse.rbe.ui.UIUtils;

/**
 * Label provider for key tree viewer.
 * @author Pascal Essiembre (essiembre@users.sourceforge.net)
 * @version $Author: nl_carnage $ $Revision: 1.11 $ $Date: 2007/09/11 16:11:09 $
 */
public class KeyTreeLabelProvider 
        extends LabelProvider implements IFontProvider, IColorProvider {	
    
    private static final int KEY_DEFAULT = 1 << 1;
    private static final int KEY_COMMENTED = 1 << 2;
    private static final int KEY_NOT = 1 << 3;
    private static final int WARNING = 1 << 4;
    private static final int WARNING_GREY = 1 << 5;

    /** Registry instead of UIUtils one for image not keyed by file name. */
    private static ImageRegistry imageRegistry = new ImageRegistry();
    
    private Color commentedColor = UIUtils.getSystemColor(SWT.COLOR_GRAY);

    /** Group font. */
    private Font groupFontKey = UIUtils.createFont(SWT.BOLD);
    private Font groupFontNoKey = UIUtils.createFont(SWT.BOLD | SWT.ITALIC);

    
    /**
     * @see ILabelProvider#getImage(Object)
     */
    public Image getImage(Object element) {
        KeyTreeItem treeItem = ((KeyTreeItem) element);
        
        int iconFlags = 0;

        // Figure out background icon
        if (treeItem.getKeyTree().getBundleGroup().isKey(treeItem.getId())) {
            IsCommentedVisitor commentedVisitor = new IsCommentedVisitor();
            treeItem.accept(commentedVisitor, null);
            if (commentedVisitor.hasOneCommented()) {
                iconFlags += KEY_COMMENTED;
            } else {
                iconFlags += KEY_DEFAULT;
            }
        } else {
            iconFlags += KEY_NOT;
        }
        
        // Maybe add warning icon        
        if (RBEPreferences.getReportMissingValues()) {
            IsMissingValueVisitor misValVisitor = new IsMissingValueVisitor();
            treeItem.accept(misValVisitor, null);
            if (misValVisitor.isMissingValue()) {
                iconFlags += WARNING;
            } else if (misValVisitor.isMissingChildValueOnly()) {
                iconFlags += WARNING_GREY;
            }
        }

        return generateImage(iconFlags);
    }

    /**
     * @see ILabelProvider#getText(Object)
     */
    public String getText(Object element) {
        return ((KeyTreeItem) element).getName(); 
    }

    /**
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
        groupFontKey.dispose();
        groupFontNoKey.dispose();
    }

    /**
     * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
     */
    public Font getFont(Object element) {
        KeyTreeItem item = (KeyTreeItem) element; 
        if (item.getChildren().size() > 0) {
            if (item.getKeyTree().getBundleGroup().isKey(item.getId())) {
                return groupFontKey;
            }
            return groupFontNoKey;
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
     */
    public Color getForeground(Object element) {
        KeyTreeItem treeItem = (KeyTreeItem) element; 
        IsCommentedVisitor commentedVisitor = new IsCommentedVisitor();
        treeItem.accept(commentedVisitor, null);
        if (commentedVisitor.hasOneCommented()) {
            return commentedColor;
        }
        return null;
    }

    /**
     * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
     */
    public Color getBackground(Object element) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * Generates an image based on icon flags. 
     * @param iconFlags
     * @return generated image
     */
    private Image generateImage(int iconFlags) {
        Image image = imageRegistry.get("" + iconFlags); //$NON-NLS-1$
        if (image == null) {
            // Figure background image
            if ((iconFlags & KEY_COMMENTED) != 0) {
                image = getRegistryImage("keyCommented.gif"); //$NON-NLS-1$
            } else if ((iconFlags & KEY_NOT) != 0) {
                image = getRegistryImage("key.gif"); //$NON-NLS-1$
            } else {
                image = getRegistryImage("key.gif"); //$NON-NLS-1$
            }
            
            // Add warning icon
            if ((iconFlags & WARNING) != 0) {
                image = overlayImage(image, "warning.gif", //$NON-NLS-1$
                        OverlayImageIcon.BOTTOM_RIGHT, iconFlags);
            } else if ((iconFlags & WARNING_GREY) != 0) {
                image = overlayImage(image, "warningGrey.gif", //$NON-NLS-1$
                        OverlayImageIcon.BOTTOM_RIGHT, iconFlags);
            }
        }
        return image;
    }

    private Image overlayImage(
            Image baseImage, String imageName, int location, int iconFlags) {
        /* To obtain a unique key, we assume here that the baseImage and 
         * location are always the same for each imageName and keyFlags 
         * combination.
         */
        String imageKey = imageName + iconFlags;
        Image image = imageRegistry.get(imageKey);
        if (image == null) {
            image = new OverlayImageIcon(baseImage, getRegistryImage(
                    imageName), location).createImage();
            imageRegistry.put(imageKey, image);
        }
        return image;
    }

    private Image getRegistryImage(String imageName) {
        Image image = imageRegistry.get(imageName);
        if (image == null) {
            image = RBEPlugin.getImageDescriptor(imageName).createImage();
            imageRegistry.put(imageName, image);
        }
        return image;
    }
}
