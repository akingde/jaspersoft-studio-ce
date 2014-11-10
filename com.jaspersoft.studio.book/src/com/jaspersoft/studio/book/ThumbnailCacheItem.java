package com.jaspersoft.studio.book;

import java.awt.Image;
import java.util.Date;



/**
 * This cache item just stores an AWT Image preview of a report
 * created by the ReportThumbnailManager.
 * 
 * The preview cached is just a BufferedImage as big as the Thumbnail,
 * so it can be quickly used to create a shadowed image (the thumbnail with the shadow)
 * or an image used during drag and drop opertaions (which is slightly bigger since it
 * does not have a shadow).
 * 
 * The manager will check the date and decide if this cached preview should be
 * recreated or not based on the cache item timestamp.
 * 
 * 
 * @author gtoffoli
 *
 */
public class ThumbnailCacheItem {
	
	private String location = null;
	private Date cacheDate = null;
	private Image preview = null;
	
	public ThumbnailCacheItem(String location, Image previewImage)
	{
		this.preview = previewImage;
		this.cacheDate = new java.util.Date();
		this.location = location;
	}
	
    /**
     * @return
     */
    public Image getPreview()
    {
    	return preview;
    }

	public String getLocation() {
		return location;
	}

	public Date getTimestamp() {
		return cacheDate;
	}

	
    
	
}
