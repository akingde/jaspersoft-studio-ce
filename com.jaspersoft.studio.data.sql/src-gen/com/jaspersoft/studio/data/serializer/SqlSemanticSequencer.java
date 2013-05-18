package com.jaspersoft.studio.data.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.jaspersoft.studio.data.services.SqlGrammarAccess;
import com.jaspersoft.studio.data.sql.AndWhereEntry;
import com.jaspersoft.studio.data.sql.BooleanArrayExpression;
import com.jaspersoft.studio.data.sql.BooleanExpression;
import com.jaspersoft.studio.data.sql.Column;
import com.jaspersoft.studio.data.sql.Database;
import com.jaspersoft.studio.data.sql.DateArrayExpression;
import com.jaspersoft.studio.data.sql.DateExpression;
import com.jaspersoft.studio.data.sql.DoubleArrayExpression;
import com.jaspersoft.studio.data.sql.DoubleExpression;
import com.jaspersoft.studio.data.sql.LongArrayExpression;
import com.jaspersoft.studio.data.sql.LongExpression;
import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry;
import com.jaspersoft.studio.data.sql.NullArrayExpression;
import com.jaspersoft.studio.data.sql.NullExpression;
import com.jaspersoft.studio.data.sql.OrWhereEntry;
import com.jaspersoft.studio.data.sql.ReplacableValue;
import com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.StringArrayExpression;
import com.jaspersoft.studio.data.sql.StringExpression;
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
			case SqlPackage.DATABASE:
				if(context == grammarAccess.getDatabaseRule()) {
					sequence_Database(context, (Database) semanticObject); 
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
				if(context == grammarAccess.getAndWhereEntryRule() ||
				   context == grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0() ||
				   context == grammarAccess.getConcreteWhereEntryRule() ||
				   context == grammarAccess.getExpressionWhereEntryRule() ||
				   context == grammarAccess.getMultiExpressionWhereEntryRule() ||
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
			case SqlPackage.REPLACABLE_VALUE:
				if(context == grammarAccess.getExpressionRule() ||
				   context == grammarAccess.getReplacableValueRule()) {
					sequence_ReplacableValue(context, (ReplacableValue) semanticObject); 
					return; 
				}
				else break;
			case SqlPackage.SINGLE_EXPRESSION_WHERE_ENTRY:
				if(context == grammarAccess.getAndWhereEntryRule() ||
				   context == grammarAccess.getAndWhereEntryAccess().getAndWhereEntryEntriesAction_1_0() ||
				   context == grammarAccess.getConcreteWhereEntryRule() ||
				   context == grammarAccess.getExpressionWhereEntryRule() ||
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
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
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
	 *     col=ColumnOrAlias
	 */
	protected void sequence_Column(EObject context, Column semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.COLUMN__COL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.COLUMN__COL));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getColumnAccess().getColColumnOrAliasParserRuleCall_0(), semanticObject.getCol());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     dbName=ID
	 */
	protected void sequence_Database(EObject context, Database semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, SqlPackage.Literals.DATABASE__DB_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, SqlPackage.Literals.DATABASE__DB_NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDatabaseAccess().getDbNameIDTerminalRuleCall_0(), semanticObject.getDbName());
		feeder.finish();
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
	 *     (col=Column db=Database whereEntry=WhereEntry?)
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
	 *     (entries+=WhereEntry_OrWhereEntry_1_0 entries+=AndWhereEntry+)
	 */
	protected void sequence_WhereEntry(EObject context, OrWhereEntry semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
