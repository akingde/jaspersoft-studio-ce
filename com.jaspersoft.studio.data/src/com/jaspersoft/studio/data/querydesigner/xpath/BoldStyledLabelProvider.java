package com.jaspersoft.studio.data.querydesigner.xpath;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;

import com.jaspersoft.studio.data.Activator;
import com.jaspersoft.studio.model.datasource.xml.XMLNode;
import com.jaspersoft.studio.utils.ResourceManager;

/**
 * Styled label provider that can highlight in bold the 
 * actual specified nodes.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
class BoldStyledLabelProvider extends StyledCellLabelProvider{

	private List<XMLNode> selectedNodes=new ArrayList<XMLNode>();
	
	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		StyledString text = new StyledString();
		
		if(element instanceof XMLNode){
			XMLNode node=(XMLNode)element;
			if(selectedNodes.contains(node)){
				// bold text
				final Font boldFont = ResourceManager.getBoldFont(getViewer().getControl().getFont());
				text.append(((XMLNode)element).getDisplayText(), new Styler() {
					@Override
					public void applyStyles(TextStyle textStyle) {
						textStyle.font=boldFont;
					}
				});
			}
			else{
				// normal text
				text.append(((XMLNode)element).getDisplayText());				
			}
		}
		else if(element instanceof XMLTreeCustomStatus){
			// normal text
			text.append(((XMLTreeCustomStatus) element).getMessage());
		}
		
		cell.setText(text.toString());
		cell.setStyleRanges(text.getStyleRanges());
		cell.setImage(getImage(element));
		super.update(cell);
	}

	private Image getImage(Object element) {
		if(element instanceof XMLNode){
			return ((XMLNode) element).getImage();
		}
		else if(element instanceof XMLTreeCustomStatus){
			return ResourceManager.getPluginImage(
					Activator.PLUGIN_ID,((XMLTreeCustomStatus) element).getImagePath());
		}
		return null;
	}
	
	void setSelectedNodes(List<XMLNode> selectedNodes){
		this.selectedNodes.clear();
		this.selectedNodes.addAll(selectedNodes);
	}
}