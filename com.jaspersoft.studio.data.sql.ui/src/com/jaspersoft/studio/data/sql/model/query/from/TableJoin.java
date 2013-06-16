package com.jaspersoft.studio.data.sql.model.query.from;

public class TableJoin {
	private MFromTable fromTable;
	private MFromTableJoin joinTable;

	public TableJoin(MFromTableJoin foreignTable, MFromTable primaryTable) {
		super();
		this.fromTable = primaryTable;
		this.joinTable = foreignTable;
		fromTable.addTableJoin(this);
	}

	public MFromTableJoin getJoinTable() {
		return joinTable;
	}

	public MFromTable getFromTable() {
		return fromTable;
	}

	public void setFromTable(MFromTable targetPrimaryKey) {
		this.fromTable = targetPrimaryKey;
	}

	public void setJoinTable(MFromTableJoin sourceForeignKey) {
		this.joinTable = sourceForeignKey;
	}
}
