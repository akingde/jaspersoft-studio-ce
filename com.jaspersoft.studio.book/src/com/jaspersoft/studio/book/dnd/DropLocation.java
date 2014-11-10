package com.jaspersoft.studio.book.dnd;

import com.jaspersoft.studio.book.editparts.BookPagesEditPart;
import com.jaspersoft.studio.book.editparts.BookSectionEditPart;

public class DropLocation {

	private BookSectionEditPart container;
	
	private BookPagesEditPart afterPart;
	
	public DropLocation(BookSectionEditPart container, BookPagesEditPart afterPart){
		this.container = container;
		this.afterPart = afterPart;
	}

	public BookSectionEditPart getContainer() {
		return container;
	}

	public BookPagesEditPart getAfterPart() {
		return afterPart;
	}
	
	public void reset(){
		container = null;
		afterPart = null;
	}
	
	public void set(BookSectionEditPart container, BookPagesEditPart afterPart){
		this.container = container;
		this.afterPart = afterPart;
	}
	
}
