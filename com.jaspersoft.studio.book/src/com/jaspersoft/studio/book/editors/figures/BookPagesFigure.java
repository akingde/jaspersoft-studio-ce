package com.jaspersoft.studio.book.editors.figures;

import java.io.File;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignPart;
import net.sf.jasperreports.engine.part.PartComponent;
import net.sf.jasperreports.parts.subreport.SubreportPartComponent;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.OrderedLayout;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.book.ReportThumbnailsManager;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.utils.ExpressionUtil;



/**
 * This is the figure that shows a single document icon in a book section.
 * The label is the expression used to reference the jrxml in the subreportPart.
 * 
 */
public class BookPagesFigure extends RectangleFigure {

	private MReportPart model;
	
	private Image previewImage = null;
	private BusyImageFigure imageFigure = null;
	
	private TooltipFigure toolTipFigure;
	
	private Label textFigure;
	
	public static final int PREFERRED_HEIGHT = 150;
	
	public static final int PREFERRED_WIDTH = 150;

	public BookPagesFigure(MReportPart model){
		this(model, null);
	}
	
	/**
	 * Creates a new BookPagesFigure.
	 * 
	 * The model is declared final because we use it in a thread that will
	 * asynchronously load the report (if available) used by this part to create
	 * a thumbnail to show in the book editor.
	 * 
	 * @param model
	 */
	public BookPagesFigure(final MReportPart model, Image previewImage){
		super();
		this.model = model;
		setPreferredSize(PREFERRED_WIDTH, PREFERRED_HEIGHT);
		setOutline(false);
		ToolbarLayout layout = new ToolbarLayout(false);
		layout.setMinorAlignment(OrderedLayout.ALIGN_CENTER);
		layout.setSpacing(5);
		setLayoutManager(layout);
		setBackgroundColor(ResourceManager.getColor(255, 255, 255));
		
		if (previewImage == null || previewImage.isDisposed())
		{
			Image defaultImage = ResourceManager.getImage(model.getImageDescriptor()); //ReportThumbnailsManager.getNoPreviewImage(0, true, false);
			// The images for this jrxml.
			// This is a scaled version of the icon used to represent a single
			// document.
			imageFigure = new BusyImageFigure(defaultImage);
		}
		else
		{
			this.previewImage = previewImage;
			imageFigure = new BusyImageFigure(previewImage);
		}
		
		imageFigure.setAlignment(PositionConstants.CENTER);
		
		
		add(imageFigure);
		
		
		
		String text = this.model.getDisplayText();
		if(text.length()>20){
			text = "..."+text.substring(text.length()-17);
		}
		
		textFigure = new Label(text);
		textFigure.setPreferredSize(180, 20);
		textFigure.setTextAlignment(PositionConstants.CENTER);
		add(textFigure);
		toolTipFigure = new TooltipFigure();
		toolTipFigure.setMessage(model.getDisplayText());
		setToolTip(toolTipFigure);
		
		// If the previre image has not been provided yet...let's load it.
		// Attention, during startup, this operation may hang...
		if (this.previewImage == null)
		{
			loadPreviewImage();
		}
	}
	
	protected void loadPreviewImage()
	{
		Thread t = new Thread(new Runnable() {
		
		@Override
		public void run() {
		
			imageFigure.setBusy(true);
			
			try {
			// Set loading preview image....
			//			final Image previewImage = ResourceManager.getImage(model.getImageDescriptor());
			//			if (previewImage != null)
			//			{
			//				updateFigure(previewImage);
			//			}
			
			Object reportFileName =  null;
				// Try to find the expression used to reference the jrxml or jasper file
				// used to fill this part.
				JRDesignPart jrDesignPart = model.getValue();
				if(jrDesignPart!=null){
					PartComponent partComponent = jrDesignPart.getComponent();
					if(partComponent instanceof SubreportPartComponent) {
						JRExpression subreportExp = ((SubreportPartComponent)partComponent).getExpression();
						if(subreportExp!=null){
							
							// Try to evaluate the subreport expression for this part.
							// The dataset to use is clearly the main dataset, since we don't have
							// other options in Jasperbook...
							reportFileName = ExpressionUtil.eval(subreportExp, model.getJasperDesign().getMainDataset(),  model.getJasperConfiguration());
							
						}
					}
				}
				
				// No image to load...
				if (reportFileName == null) return;
				
				if (reportFileName instanceof File)
				{
					reportFileName = ((File)reportFileName).toURI().toString();
				}
				else if (!(reportFileName instanceof String))
				{
					return; // We only understand string paths...
				}
				
				final Image sourceImage = ReportThumbnailsManager.produceImage((String)reportFileName, model.getJasperConfiguration());
				
				if (sourceImage != null)
				{
					updateFigure(sourceImage);
				}
				
				} finally 
				{
					imageFigure.setBusy(false);
				}
				
			}
		});
		
		// Run the thread that will load the thumbnail in background!
		t.start();

	}
	
	
	/**
	 * Replace the figure image and dispose the old image if any
	 * 
	 * @param newImage
	 */
	public synchronized void updateFigure(final Image newImage)
    {
		updateFigure(newImage, false);
    }
	
	
	/**
	 * Replace the figure and provides an option to prevent the disposal of the
	 * the currently shown image.
	 * 
	 * @param newImage
	 * @param preventDispose
	 */
	public synchronized void updateFigure(final Image newImage, boolean preventDispose)
    {
        if (UIUtils.getDisplay() == null || UIUtils.getDisplay().isDisposed()) 
            return;
        UIUtils.getDisplay().asyncExec(new Runnable() {

            public void run() {
                
            	// Save the old image, that will be disposed later
            	Image oldImage = previewImage;
				
            	previewImage = newImage;
            	//scaleImage(sourceImage, getPreferredSize().width, getPreferredSize().height-20);
				// change the source Image...

            	if (previewImage != oldImage)
            	{
            		imageFigure.setImage(newImage);
            		// dispose the old one...
            		if (oldImage != null && !oldImage.isDisposed())
            		{
            			oldImage.dispose();
            		}
            	}
				BookPagesFigure.this.invalidateTree();
                BookPagesFigure.this.repaint();
            }
        });

    }
	
	public void updateText(){
		String text = this.model.getDisplayText();
		if(text.length()>20){
			text = "..."+text.substring(text.length()-17);
		}
		textFigure.setText(text);
		toolTipFigure.setMessage(model.getDisplayText());
	}
	
	@Override
	public void setBounds(Rectangle rect) {
		int offsetY = BookSectionFigure.HORIZONTAL_LINE_OFFSET + 4;
		super.setBounds(new Rectangle(rect.x+15, rect.y+offsetY, rect.width, rect.height));
	}

	/**
	 * Scaled image is disposed when the part is deactivated
	 */
	public void dispose(){
		if (previewImage != null && !previewImage.isDisposed()){
			previewImage.dispose();
		}
	}
	
	
	public Image getThubmnailImage()
	{
		return previewImage;
	}
}
