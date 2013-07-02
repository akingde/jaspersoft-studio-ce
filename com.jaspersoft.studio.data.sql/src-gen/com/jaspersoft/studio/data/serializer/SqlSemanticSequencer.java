package com.jaspersoft.studio.data.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jaspersoft.studio.data.services.SqlGrammarAccess;
import com.jaspersoft.studio.data.sql.AndHavingEntry;
import com.jaspersoft.studio.data.sql.AndWhereEntry;
import com.jaspersoft.studio.data.sql.BooleanArrayExpression;
import com.jaspersoft.studio.data.sql.BooleanExpression;
import com.jaspersoft.studio.data.sql.Column;
import com.jaspersoft.studio.data.sql.ColumnAlias;
import com.jaspersoft.studio.data.sql.ColumnFull;
import com.jaspersoft.studio.data.sql.Database;
import com.jaspersoft.studio.data.sql.DateArrayExpression;
import com.jaspersoft.studio.data.sql.DateExpression;
import com.jaspersoft.studio.data.sql.DoubleArrayExpression;
import com.jaspersoft.studio.data.sql.DoubleExpression;
import com.jaspersoft.studio.data.sql.GroupByColumnFull;
import com.jaspersoft.studio.data.sql.LongArrayExpression;
import com.jaspersoft.studio.data.sql.LongExpression;
import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry;
import com.jaspersoft.studio.data.sql.NullArrayExpression;
import com.jaspersoft.studio.data.sql.NullExpression;
import com.jaspersoft.studio.data.sql.OrColumn;
import com.jaspersoft.studio.data.sql.OrGroupByColumn;
import com.jaspersoft.studio.data.sql.OrHavingEntry;
import com.jaspersoft.studio.data.sql.OrOrderByColumn;
import com.jaspersoft.studio.data.sql.OrTable;
import com.jaspersoft.studio.data.sql.OrWhereEntry;
import com.jaspersoft.studio.data.sql.OrderByColumnFull;
import com.jaspersoft.studio.data.sql.ReplacableValue;
import com.jaspersoft.studio.data.sql.Schema;
import com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.StringArrayExpression;
import com.jaspersoft.studio.data.sql.StringExpression;
import com.jaspersoft.studio.data.sql.Table;
import com.jaspersoft.studio.data.sql.TableAlias;
import com.jaspersoft.studio.data.sql.TableFull;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class SqlSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private SqlGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == SqlPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case SqlPackage.AND_HAVING_ENTRY:
				if(context == grammarAccess.getAndHavingEntryRule() ||
				   context == grammarAccess.getAndHavingEntryAccess().getAndHavingEntryEntriesAction_1_0() ||
				   context == grammarAccess.getConcreteHavingEntryRule() ||
				   context == grammarAccess.getHavingEntryRule() ||
				   context == grammarAccess.getHavingEntryAccess().getOrHavingEntryEntriesAction_1_0() ||
				   context == grammarAccess.getParHavingEntryRule()) {
					sequence_AndHavingEntry(context, (AndHavingEntry) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.AND_WHERE_ENTRY:
				if(context == grammarAccess.getAndWhereEntryRule() ||
				   context == grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0() ||
				   context == grammarAccess.getConcreteWhereEntryRule() ||
				   context == grammarAccess.getParWhereEntryRule() ||
				   context == grammarAccess.getWhereEntryRule() ||
				   context == grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0()) {
					sequence_AndWhereEntry(context, (AndWhereEntry) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.BOOLEAN_ARRAY_EXPRESSION:
				if(context == grammarAccess.getArrayExpressionRule() ||
				   context == grammarAccess.getBooleanArrayExpressionRule()) {
					sequence_BooleanArrayExpression(context, (BooleanArrayExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.BOOLEAN_EXPRESSION:
				if(context == grammarAccess.getBooleanExpressionRule() ||
				   context == grammarAccess.getExpressionRule()) {
					sequence_BooleanExpression(context, (BooleanExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.COLUMN:
				if(context == grammarAccess.getColumnRule()) {
					sequence_Column(context, (Column) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.COLUMN_ALIAS:
				if(context == grammarAccess.getColumnAliasRule()) {
					sequence_ColumnAlias(context, (ColumnAlias) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.COLUMN_FULL:
				if(context == grammarAccess.getColumnFullRule() ||
				   context == grammarAccess.getColumnOrAliasRule() ||
				   context == grammarAccess.getColumnsRule() ||
				   context == grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()) {
					sequence_ColumnFull(context, (ColumnFull) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.DATABASE:
				if(context == grammarAccess.getColumnFullRule() ||
				   context == grammarAccess.getColumnOrAliasRule() ||
				   context == grammarAccess.getColumnsRule() ||
				   context == grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()) {
					sequence_ColumnFull_Database_Schema_TableFull(context, (Database) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getDatabaseRule()) {
					sequence_Database(context, (Database) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getGroupByColumnFullRule() ||
				   context == grammarAccess.getGroupByColumnsRule() ||
				   context == grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()) {
					sequence_Database_GroupByColumnFull_Schema_TableFull(context, (Database) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getOrderByColumnFullRule() ||
				   context == grammarAccess.getOrderByColumnsRule() ||
				   context == grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()) {
					sequence_Database_OrderByColumnFull_Schema_TableFull(context, (Database) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getSchemaRule()) {
					sequence_Database_Schema(context, (Database) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTableFullRule() ||
				   context == grammarAccess.getTableOrAliasRule() ||
				   context == grammarAccess.getTablesRule() ||
				   context == grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()) {
					sequence_Database_Schema_TableFull(context, (Database) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.DATE_ARRAY_EXPRESSION:
				if(context == grammarAccess.getArrayExpressionRule() ||
				   context == grammarAccess.getDateArrayExpressionRule()) {
					sequence_DateArrayExpression(context, (DateArrayExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.DATE_EXPRESSION:
				if(context == grammarAccess.getDateExpressionRule() ||
				   context == grammarAccess.getExpressionRule()) {
					sequence_DateExpression(context, (DateExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.DOUBLE_ARRAY_EXPRESSION:
				if(context == grammarAccess.getArrayExpressionRule() ||
				   context == grammarAccess.getDoubleArrayExpressionRule()) {
					sequence_DoubleArrayExpression(context, (DoubleArrayExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.DOUBLE_EXPRESSION:
				if(context == grammarAccess.getDoubleExpressionRule() ||
				   context == grammarAccess.getExpressionRule()) {
					sequence_DoubleExpression(context, (DoubleExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.GROUP_BY_COLUMN_FULL:
				if(context == grammarAccess.getGroupByColumnFullRule() ||
				   context == grammarAccess.getGroupByColumnsRule() ||
				   context == grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()) {
					sequence_GroupByColumnFull(context, (GroupByColumnFull) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.LONG_ARRAY_EXPRESSION:
				if(context == grammarAccess.getArrayExpressionRule() ||
				   context == grammarAccess.getLongArrayExpressionRule()) {
					sequence_LongArrayExpression(context, (LongArrayExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.LONG_EXPRESSION:
				if(context == grammarAccess.getExpressionRule() ||
				   context == grammarAccess.getLongExpressionRule()) {
					sequence_LongExpression(context, (LongExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.MULTI_EXPRESSION_WHERE_ENTRY:
				if(context == grammarAccess.getAndHavingEntryRule() ||
				   context == grammarAccess.getAndHavingEntryAccess().getAndHavingEntryEntriesAction_1_0() ||
				   context == grammarAccess.getAndWhereEntryRule() ||
				   context == grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0() ||
				   context == grammarAccess.getConcreteHavingEntryRule() ||
				   context == grammarAccess.getConcreteWhereEntryRule() ||
				   context == grammarAccess.getExpressionWhereEntryRule() ||
				   context == grammarAccess.getHavingEntryRule() ||
				   context == grammarAccess.getHavingEntryAccess().getOrHavingEntryEntriesAction_1_0() ||
				   context == grammarAccess.getMultiExpressionWhereEntryRule() ||
				   context == grammarAccess.getParHavingEntryRule() ||
				   context == grammarAccess.getParWhereEntryRule() ||
				   context == grammarAccess.getWhereEntryRule() ||
				   context == grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0()) {
					sequence_MultiExpressionWhereEntry(context, (MultiExpressionWhereEntry) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.NULL_ARRAY_EXPRESSION:
				if(context == grammarAccess.getArrayExpressionRule() ||
				   context == grammarAccess.getNullArrayExpressionRule()) {
					sequence_NullArrayExpression(context, (NullArrayExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.NULL_EXPRESSION:
				if(context == grammarAccess.getExpressionRule() ||
				   context == grammarAccess.getNullExpressionRule()) {
					sequence_NullExpression(context, (NullExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.OR_COLUMN:
				if(context == grammarAccess.getColumnsRule()) {
					sequence_Columns(context, (OrColumn) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.OR_GROUP_BY_COLUMN:
				if(context == grammarAccess.getGroupByColumnsRule()) {
					sequence_GroupByColumns(context, (OrGroupByColumn) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.OR_HAVING_ENTRY:
				if(context == grammarAccess.getAndHavingEntryRule() ||
				   context == grammarAccess.getAndHavingEntryAccess().getAndHavingEntryEntriesAction_1_0() ||
				   context == grammarAccess.getConcreteHavingEntryRule() ||
				   context == grammarAccess.getHavingEntryRule() ||
				   context == grammarAccess.getHavingEntryAccess().getOrHavingEntryEntriesAction_1_0() ||
				   context == grammarAccess.getParHavingEntryRule()) {
					sequence_HavingEntry(context, (OrHavingEntry) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.OR_ORDER_BY_COLUMN:
				if(context == grammarAccess.getOrderByColumnsRule()) {
					sequence_OrderByColumns(context, (OrOrderByColumn) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.OR_TABLE:
				if(context == grammarAccess.getTablesRule()) {
					sequence_Tables(context, (OrTable) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.OR_WHERE_ENTRY:
				if(context == grammarAccess.getAndWhereEntryRule() ||
				   context == grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0() ||
				   context == grammarAccess.getConcreteWhereEntryRule() ||
				   context == grammarAccess.getParWhereEntryRule() ||
				   context == grammarAccess.getWhereEntryRule() ||
				   context == grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0()) {
					sequence_WhereEntry(context, (OrWhereEntry) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.ORDER_BY_COLUMN_FULL:
				if(context == grammarAccess.getOrderByColumnFullRule() ||
				   context == grammarAccess.getOrderByColumnsRule() ||
				   context == grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()) {
					sequence_OrderByColumnFull(context, (OrderByColumnFull) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.REPLACABLE_VALUE:
				if(context == grammarAccess.getExpressionRule() ||
				   context == grammarAccess.getReplacableValueRule()) {
					sequence_ReplacableValue(context, (ReplacableValue) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.SCHEMA:
				if(context == grammarAccess.getColumnFullRule() ||
				   context == grammarAccess.getColumnOrAliasRule() ||
				   context == grammarAccess.getColumnsRule() ||
				   context == grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()) {
					sequence_ColumnFull_Schema_TableFull(context, (Schema) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getGroupByColumnFullRule() ||
				   context == grammarAccess.getGroupByColumnsRule() ||
				   context == grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()) {
					sequence_GroupByColumnFull_Schema_TableFull(context, (Schema) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getOrderByColumnFullRule() ||
				   context == grammarAccess.getOrderByColumnsRule() ||
				   context == grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()) {
					sequence_OrderByColumnFull_Schema_TableFull(context, (Schema) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getSchemaRule()) {
					sequence_Schema(context, (Schema) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTableFullRule() ||
				   context == grammarAccess.getTableOrAliasRule() ||
				   context == grammarAccess.getTablesRule() ||
				   context == grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()) {
					sequence_Schema_TableFull(context, (Schema) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.SINGLE_EXPRESSION_WHERE_ENTRY:
				if(context == grammarAccess.getAndHavingEntryRule() ||
				   context == grammarAccess.getAndHavingEntryAccess().getAndHavingEntryEntriesAction_1_0() ||
				   context == grammarAccess.getAndWhereEntryRule() ||
				   context == grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0() ||
				   context == grammarAccess.getConcreteHavingEntryRule() ||
				   context == grammarAccess.getConcreteWhereEntryRule() ||
				   context == grammarAccess.getExpressionWhereEntryRule() ||
				   context == grammarAccess.getHavingEntryRule() ||
				   context == grammarAccess.getHavingEntryAccess().getOrHavingEntryEntriesAction_1_0() ||
				   context == grammarAccess.getParHavingEntryRule() ||
				   context == grammarAccess.getParWhereEntryRule() ||
				   context == grammarAccess.getSingleExpressionWhereEntryRule() ||
				   context == grammarAccess.getWhereEntryRule() ||
				   context == grammarAccess.getWhereEntryAccess().getOrWhereEntryEntriesAction_1_0()) {
					sequence_SingleExpressionWhereEntry(context, (SingleExpressionWhereEntry) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.STRING_ARRAY_EXPRESSION:
				if(context == grammarAccess.getArrayExpressionRule() ||
				   context == grammarAccess.getStringArrayExpressionRule()) {
					sequence_StringArrayExpression(context, (StringArrayExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.STRING_EXPRESSION:
				if(context == grammarAccess.getExpressionRule() ||
				   context == grammarAccess.getStringExpressionRule()) {
					sequence_StringExpression(context, (StringExpression) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.TABLE:
				if(context == grammarAccess.getTableRule()) {
					sequence_Table(context, (Table) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.TABLE_ALIAS:
				if(context == grammarAccess.getTableAliasRule()) {
					sequence_TableAlias(context, (TableAlias) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.TABLE_FULL:
				if(context == grammarAccess.getColumnFullRule() ||
				   context == grammarAccess.getColumnOrAliasRule() ||
				   context == grammarAccess.getColumnsRule() ||
				   context == grammarAccess.getColumnsAccess().getOrColumnEntriesAction_1_0()) {
					sequence_ColumnFull_TableFull(context, (TableFull) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getGroupByColumnFullRule() ||
				   context == grammarAccess.getGroupByColumnsRule() ||
				   context == grammarAccess.getGroupByColumnsAccess().getOrGroupByColumnEntriesAction_1_0()) {
					sequence_GroupByColumnFull_TableFull(context, (TableFull) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getOrderByColumnFullRule() ||
				   context == grammarAccess.getOrderByColumnsRule() ||
				   context == grammarAccess.getOrderByColumnsAccess().getOrOrderByColumnEntriesAction_1_0()) {
					sequence_OrderByColumnFull_TableFull(context, (TableFull) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTableFullRule() ||
				   context == grammarAccess.getTableOrAliasRule() ||
				   context == grammarAccess.getTablesRule() ||
				   context == grammarAccess.getTablesAccess().getOrTableEntriesAction_1_0()) {
					sequence_TableFull(context, (TableFull) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (entries+=AndHavingEntry_AndHavingEntry_1_0 entries+=ConcreteHavingEntry+)
	 */
	protected void sequence_AndHavingEntry(EObject context, AndHavingEntry semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (entries+=AndWhereEntry_AndWhereEntry_1_0 entries+=ConcreteWhereEntry+)
	 */
	protected void sequence_AndWhereEntry(EObject context, AndWhereEntry semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (values+=BOOL values+=BOOL*)
	 */
	protected void sequence_BooleanArrayExpression(EObject context, BooleanArrayExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (true='true' | true='false')
	 */
	protected void sequence_BooleanExpression(EObject context, BooleanExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     colAlias=ID
	 */
	protected void sequence_ColumnAlias(EObject context, ColumnAlias semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.COLUMN_ALIAS__COL_ALIAS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.COLUMN_ALIAS__COL_ALIAS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getColumnAliasAccess().getColAliasIDTerminalRuleCall_0(), semanticObject.getColAlias());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     colName=Column
	 */
	protected void sequence_ColumnFull(EObject context, ColumnFull semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (dbName=ID schem=ID tbl=Table colName=Column)
	 */
	protected void sequence_ColumnFull_Database_Schema_TableFull(EObject context, Database semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (schem=ID tbl=Table colName=Column)
	 */
	protected void sequence_ColumnFull_Schema_TableFull(EObject context, Schema semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (tbl=Table colName=Column)
	 */
	protected void sequence_ColumnFull_TableFull(EObject context, TableFull semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     colName=ID
	 */
	protected void sequence_Column(EObject context, Column semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.COLUMN__COL_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.COLUMN__COL_NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getColumnAccess().getColNameIDTerminalRuleCall_0(), semanticObject.getColName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (entries+=Columns_OrColumn_1_0 entries+=ColumnOrAlias+)
	 */
	protected void sequence_Columns(EObject context, OrColumn semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     dbName=ID
	 */
	protected void sequence_Database(EObject context, Database semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (dbName=ID schem=ID tbl=Table groupByColumn=Column)
	 */
	protected void sequence_Database_GroupByColumnFull_Schema_TableFull(EObject context, Database semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (dbName=ID schem=ID tbl=Table colOrder=Column)
	 */
	protected void sequence_Database_OrderByColumnFull_Schema_TableFull(EObject context, Database semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (dbName=ID schem=ID)
	 */
	protected void sequence_Database_Schema(EObject context, Database semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (dbName=ID schem=ID tbl=Table)
	 */
	protected void sequence_Database_Schema_TableFull(EObject context, Database semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (values+=DATE values+=DATE*)
	 */
	protected void sequence_DateArrayExpression(EObject context, DateArrayExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=DATE
	 */
	protected void sequence_DateExpression(EObject context, DateExpression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.DATE_EXPRESSION__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.DATE_EXPRESSION__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDateExpressionAccess().getValueDATETerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (values+=SIGNED_DOUBLE values+=SIGNED_DOUBLE*)
	 */
	protected void sequence_DoubleArrayExpression(EObject context, DoubleArrayExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=SIGNED_DOUBLE
	 */
	protected void sequence_DoubleExpression(EObject context, DoubleExpression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.DOUBLE_EXPRESSION__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.DOUBLE_EXPRESSION__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDoubleExpressionAccess().getValueSIGNED_DOUBLETerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     groupByColumn=Column
	 */
	protected void sequence_GroupByColumnFull(EObject context, GroupByColumnFull semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.GROUP_BY_COLUMN_FULL__GROUP_BY_COLUMN));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getGroupByColumnFullAccess().getGroupByColumnColumnParserRuleCall_0_0(), semanticObject.getGroupByColumn());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (schem=ID tbl=Table groupByColumn=Column)
	 */
	protected void sequence_GroupByColumnFull_Schema_TableFull(EObject context, Schema semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (tbl=Table groupByColumn=Column)
	 */
	protected void sequence_GroupByColumnFull_TableFull(EObject context, TableFull semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (entries+=GroupByColumns_OrGroupByColumn_1_0 entries+=GroupByColumnFull+)
	 */
	protected void sequence_GroupByColumns(EObject context, OrGroupByColumn semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (entries+=HavingEntry_OrHavingEntry_1_0 entries+=AndHavingEntry+)
	 */
	protected void sequence_HavingEntry(EObject context, OrHavingEntry semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (values+=SINGED_LONG values+=SINGED_LONG*)
	 */
	protected void sequence_LongArrayExpression(EObject context, LongArrayExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=SINGED_LONG
	 */
	protected void sequence_LongExpression(EObject context, LongExpression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.LONG_EXPRESSION__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.LONG_EXPRESSION__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLongExpressionAccess().getValueSINGED_LONGTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         col=Columns? 
	 *         tbl=Tables 
	 *         whereEntry=WhereEntry? 
	 *         groupByEntry=GroupByColumns? 
	 *         havingEntry=HavingEntry? 
	 *         orderByEntry=OrderByColumns?
	 *     )
	 */
	protected void sequence_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID operator=ArrayOperator rhs=ArrayExpression)
	 */
	protected void sequence_MultiExpressionWhereEntry(EObject context, MultiExpressionWhereEntry semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.EXPRESSION_WHERE_ENTRY__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.EXPRESSION_WHERE_ENTRY__NAME));
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.MULTI_EXPRESSION_WHERE_ENTRY__OPERATOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.MULTI_EXPRESSION_WHERE_ENTRY__OPERATOR));
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.MULTI_EXPRESSION_WHERE_ENTRY__RHS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.MULTI_EXPRESSION_WHERE_ENTRY__RHS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMultiExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getMultiExpressionWhereEntryAccess().getOperatorArrayOperatorEnumRuleCall_1_0(), semanticObject.getOperator());
		feeder.accept(grammarAccess.getMultiExpressionWhereEntryAccess().getRhsArrayExpressionParserRuleCall_2_0(), semanticObject.getRhs());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (values+='null' values+='null'*)
	 */
	protected void sequence_NullArrayExpression(EObject context, NullArrayExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value='null'
	 */
	protected void sequence_NullExpression(EObject context, NullExpression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.NULL_EXPRESSION__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.NULL_EXPRESSION__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getNullExpressionAccess().getValueNullKeyword_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     colOrder=Column
	 */
	protected void sequence_OrderByColumnFull(EObject context, OrderByColumnFull semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.ORDER_BY_COLUMN_FULL__COL_ORDER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.ORDER_BY_COLUMN_FULL__COL_ORDER));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOrderByColumnFullAccess().getColOrderColumnParserRuleCall_0_0(), semanticObject.getColOrder());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (schem=ID tbl=Table colOrder=Column)
	 */
	protected void sequence_OrderByColumnFull_Schema_TableFull(EObject context, Schema semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (tbl=Table colOrder=Column)
	 */
	protected void sequence_OrderByColumnFull_TableFull(EObject context, TableFull semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (entries+=OrderByColumns_OrOrderByColumn_1_0 entries+=OrderByColumnFull+)
	 */
	protected void sequence_OrderByColumns(EObject context, OrOrderByColumn semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value='?'
	 */
	protected void sequence_ReplacableValue(EObject context, ReplacableValue semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.REPLACABLE_VALUE__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.REPLACABLE_VALUE__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getReplacableValueAccess().getValueQuestionMarkKeyword_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     schem=ID
	 */
	protected void sequence_Schema(EObject context, Schema semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (schem=ID tbl=Table)
	 */
	protected void sequence_Schema_TableFull(EObject context, Schema semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID operator=Operator rhs=Expression)
	 */
	protected void sequence_SingleExpressionWhereEntry(EObject context, SingleExpressionWhereEntry semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.EXPRESSION_WHERE_ENTRY__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.EXPRESSION_WHERE_ENTRY__NAME));
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.SINGLE_EXPRESSION_WHERE_ENTRY__OPERATOR) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.SINGLE_EXPRESSION_WHERE_ENTRY__OPERATOR));
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.SINGLE_EXPRESSION_WHERE_ENTRY__RHS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.SINGLE_EXPRESSION_WHERE_ENTRY__RHS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSingleExpressionWhereEntryAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getSingleExpressionWhereEntryAccess().getOperatorOperatorEnumRuleCall_1_0(), semanticObject.getOperator());
		feeder.accept(grammarAccess.getSingleExpressionWhereEntryAccess().getRhsExpressionParserRuleCall_2_0(), semanticObject.getRhs());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (values+=STRING values+=STRING*)
	 */
	protected void sequence_StringArrayExpression(EObject context, StringArrayExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=STRING
	 */
	protected void sequence_StringExpression(EObject context, StringExpression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.STRING_EXPRESSION__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.STRING_EXPRESSION__VALUE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStringExpressionAccess().getValueSTRINGTerminalRuleCall_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     tblAlias=ID
	 */
	protected void sequence_TableAlias(EObject context, TableAlias semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.TABLE_ALIAS__TBL_ALIAS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.TABLE_ALIAS__TBL_ALIAS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTableAliasAccess().getTblAliasIDTerminalRuleCall_0(), semanticObject.getTblAlias());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     tbl=Table
	 */
	protected void sequence_TableFull(EObject context, TableFull semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     tbl=ID
	 */
	protected void sequence_Table(EObject context, Table semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.TABLE__TBL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.TABLE__TBL));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTableAccess().getTblIDTerminalRuleCall_0(), semanticObject.getTbl());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (entries+=Tables_OrTable_1_0 entries+=TableOrAlias+)
	 */
	protected void sequence_Tables(EObject context, OrTable semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (entries+=WhereEntry_OrWhereEntry_1_0 entries+=AndWhereEntry+)
	 */
	protected void sequence_WhereEntry(EObject context, OrWhereEntry semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
