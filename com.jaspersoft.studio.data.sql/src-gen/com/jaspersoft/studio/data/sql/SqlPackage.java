/**
 */
package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.jaspersoft.studio.data.sql.SqlFactory
 * @model kind="package"
 * @generated
 */
public interface SqlPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "sql";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.com.jaspersoft.studio.data.Sql";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "sql";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SqlPackage eINSTANCE = com.jaspersoft.studio.data.sql.impl.SqlPackageImpl.init();

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ModelImpl <em>Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ModelImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getModel()
   * @generated
   */
  int MODEL = 0;

  /**
   * The feature id for the '<em><b>Col</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__COL = 0;

  /**
   * The feature id for the '<em><b>Db</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__DB = 1;

  /**
   * The feature id for the '<em><b>Where Entry</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL__WHERE_ENTRY = 2;

  /**
   * The number of structural features of the '<em>Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.DatabaseImpl <em>Database</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.DatabaseImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDatabase()
   * @generated
   */
  int DATABASE = 1;

  /**
   * The feature id for the '<em><b>Db Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE__DB_NAME = 0;

  /**
   * The number of structural features of the '<em>Database</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATABASE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ColumnImpl <em>Column</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ColumnImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getColumn()
   * @generated
   */
  int COLUMN = 2;

  /**
   * The feature id for the '<em><b>Col</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN__COL = 0;

  /**
   * The number of structural features of the '<em>Column</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLUMN_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.WhereEntryImpl <em>Where Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.WhereEntryImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getWhereEntry()
   * @generated
   */
  int WHERE_ENTRY = 3;

  /**
   * The number of structural features of the '<em>Where Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WHERE_ENTRY_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ExpressionWhereEntryImpl <em>Expression Where Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ExpressionWhereEntryImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getExpressionWhereEntry()
   * @generated
   */
  int EXPRESSION_WHERE_ENTRY = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_WHERE_ENTRY__NAME = WHERE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Expression Where Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_WHERE_ENTRY_FEATURE_COUNT = WHERE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.SingleExpressionWhereEntryImpl <em>Single Expression Where Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.SingleExpressionWhereEntryImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getSingleExpressionWhereEntry()
   * @generated
   */
  int SINGLE_EXPRESSION_WHERE_ENTRY = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_EXPRESSION_WHERE_ENTRY__NAME = EXPRESSION_WHERE_ENTRY__NAME;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_EXPRESSION_WHERE_ENTRY__OPERATOR = EXPRESSION_WHERE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Rhs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_EXPRESSION_WHERE_ENTRY__RHS = EXPRESSION_WHERE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Single Expression Where Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_EXPRESSION_WHERE_ENTRY_FEATURE_COUNT = EXPRESSION_WHERE_ENTRY_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 6;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ReplacableValueImpl <em>Replacable Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ReplacableValueImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getReplacableValue()
   * @generated
   */
  int REPLACABLE_VALUE = 7;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPLACABLE_VALUE__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Replacable Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REPLACABLE_VALUE_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.DoubleExpressionImpl <em>Double Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.DoubleExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDoubleExpression()
   * @generated
   */
  int DOUBLE_EXPRESSION = 8;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Double Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.LongExpressionImpl <em>Long Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.LongExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getLongExpression()
   * @generated
   */
  int LONG_EXPRESSION = 9;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LONG_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Long Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LONG_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.StringExpressionImpl <em>String Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.StringExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getStringExpression()
   * @generated
   */
  int STRING_EXPRESSION = 10;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.NullExpressionImpl <em>Null Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.NullExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getNullExpression()
   * @generated
   */
  int NULL_EXPRESSION = 11;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Null Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.DateExpressionImpl <em>Date Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.DateExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDateExpression()
   * @generated
   */
  int DATE_EXPRESSION = 12;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATE_EXPRESSION__VALUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Date Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATE_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.BooleanExpressionImpl <em>Boolean Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.BooleanExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getBooleanExpression()
   * @generated
   */
  int BOOLEAN_EXPRESSION = 13;

  /**
   * The feature id for the '<em><b>True</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION__TRUE = EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Boolean Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.MultiExpressionWhereEntryImpl <em>Multi Expression Where Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.MultiExpressionWhereEntryImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getMultiExpressionWhereEntry()
   * @generated
   */
  int MULTI_EXPRESSION_WHERE_ENTRY = 14;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTI_EXPRESSION_WHERE_ENTRY__NAME = EXPRESSION_WHERE_ENTRY__NAME;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTI_EXPRESSION_WHERE_ENTRY__OPERATOR = EXPRESSION_WHERE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Rhs</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTI_EXPRESSION_WHERE_ENTRY__RHS = EXPRESSION_WHERE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Multi Expression Where Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTI_EXPRESSION_WHERE_ENTRY_FEATURE_COUNT = EXPRESSION_WHERE_ENTRY_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.ArrayExpressionImpl <em>Array Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.ArrayExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getArrayExpression()
   * @generated
   */
  int ARRAY_EXPRESSION = 15;

  /**
   * The number of structural features of the '<em>Array Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARRAY_EXPRESSION_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.DoubleArrayExpressionImpl <em>Double Array Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.DoubleArrayExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDoubleArrayExpression()
   * @generated
   */
  int DOUBLE_ARRAY_EXPRESSION = 16;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_ARRAY_EXPRESSION__VALUES = ARRAY_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Double Array Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOUBLE_ARRAY_EXPRESSION_FEATURE_COUNT = ARRAY_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.LongArrayExpressionImpl <em>Long Array Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.LongArrayExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getLongArrayExpression()
   * @generated
   */
  int LONG_ARRAY_EXPRESSION = 17;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LONG_ARRAY_EXPRESSION__VALUES = ARRAY_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Long Array Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LONG_ARRAY_EXPRESSION_FEATURE_COUNT = ARRAY_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.StringArrayExpressionImpl <em>String Array Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.StringArrayExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getStringArrayExpression()
   * @generated
   */
  int STRING_ARRAY_EXPRESSION = 18;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_ARRAY_EXPRESSION__VALUES = ARRAY_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Array Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_ARRAY_EXPRESSION_FEATURE_COUNT = ARRAY_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.NullArrayExpressionImpl <em>Null Array Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.NullArrayExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getNullArrayExpression()
   * @generated
   */
  int NULL_ARRAY_EXPRESSION = 19;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_ARRAY_EXPRESSION__VALUES = ARRAY_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Null Array Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NULL_ARRAY_EXPRESSION_FEATURE_COUNT = ARRAY_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.DateArrayExpressionImpl <em>Date Array Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.DateArrayExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDateArrayExpression()
   * @generated
   */
  int DATE_ARRAY_EXPRESSION = 20;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATE_ARRAY_EXPRESSION__VALUES = ARRAY_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Date Array Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATE_ARRAY_EXPRESSION_FEATURE_COUNT = ARRAY_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.BooleanArrayExpressionImpl <em>Boolean Array Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.BooleanArrayExpressionImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getBooleanArrayExpression()
   * @generated
   */
  int BOOLEAN_ARRAY_EXPRESSION = 21;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_ARRAY_EXPRESSION__VALUES = ARRAY_EXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Boolean Array Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_ARRAY_EXPRESSION_FEATURE_COUNT = ARRAY_EXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.OrWhereEntryImpl <em>Or Where Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.OrWhereEntryImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrWhereEntry()
   * @generated
   */
  int OR_WHERE_ENTRY = 22;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_WHERE_ENTRY__ENTRIES = WHERE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Or Where Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_WHERE_ENTRY_FEATURE_COUNT = WHERE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.impl.AndWhereEntryImpl <em>And Where Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.impl.AndWhereEntryImpl
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getAndWhereEntry()
   * @generated
   */
  int AND_WHERE_ENTRY = 23;

  /**
   * The feature id for the '<em><b>Entries</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_WHERE_ENTRY__ENTRIES = WHERE_ENTRY_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>And Where Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_WHERE_ENTRY_FEATURE_COUNT = WHERE_ENTRY_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.ArrayOperator <em>Array Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.ArrayOperator
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getArrayOperator()
   * @generated
   */
  int ARRAY_OPERATOR = 24;

  /**
   * The meta object id for the '{@link com.jaspersoft.studio.data.sql.Operator <em>Operator</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.jaspersoft.studio.data.sql.Operator
   * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOperator()
   * @generated
   */
  int OPERATOR = 25;


  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model</em>'.
   * @see com.jaspersoft.studio.data.sql.Model
   * @generated
   */
  EClass getModel();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Model#getCol <em>Col</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Col</em>'.
   * @see com.jaspersoft.studio.data.sql.Model#getCol()
   * @see #getModel()
   * @generated
   */
  EReference getModel_Col();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Model#getDb <em>Db</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Db</em>'.
   * @see com.jaspersoft.studio.data.sql.Model#getDb()
   * @see #getModel()
   * @generated
   */
  EReference getModel_Db();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.Model#getWhereEntry <em>Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Where Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.Model#getWhereEntry()
   * @see #getModel()
   * @generated
   */
  EReference getModel_WhereEntry();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Database <em>Database</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Database</em>'.
   * @see com.jaspersoft.studio.data.sql.Database
   * @generated
   */
  EClass getDatabase();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.Database#getDbName <em>Db Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Db Name</em>'.
   * @see com.jaspersoft.studio.data.sql.Database#getDbName()
   * @see #getDatabase()
   * @generated
   */
  EAttribute getDatabase_DbName();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Column <em>Column</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Column</em>'.
   * @see com.jaspersoft.studio.data.sql.Column
   * @generated
   */
  EClass getColumn();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.Column#getCol <em>Col</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Col</em>'.
   * @see com.jaspersoft.studio.data.sql.Column#getCol()
   * @see #getColumn()
   * @generated
   */
  EAttribute getColumn_Col();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.WhereEntry <em>Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Where Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.WhereEntry
   * @generated
   */
  EClass getWhereEntry();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.ExpressionWhereEntry <em>Expression Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression Where Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.ExpressionWhereEntry
   * @generated
   */
  EClass getExpressionWhereEntry();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.ExpressionWhereEntry#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see com.jaspersoft.studio.data.sql.ExpressionWhereEntry#getName()
   * @see #getExpressionWhereEntry()
   * @generated
   */
  EAttribute getExpressionWhereEntry_Name();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry <em>Single Expression Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Single Expression Where Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry
   * @generated
   */
  EClass getSingleExpressionWhereEntry();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry#getOperator()
   * @see #getSingleExpressionWhereEntry()
   * @generated
   */
  EAttribute getSingleExpressionWhereEntry_Operator();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry#getRhs <em>Rhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rhs</em>'.
   * @see com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry#getRhs()
   * @see #getSingleExpressionWhereEntry()
   * @generated
   */
  EReference getSingleExpressionWhereEntry_Rhs();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.ReplacableValue <em>Replacable Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Replacable Value</em>'.
   * @see com.jaspersoft.studio.data.sql.ReplacableValue
   * @generated
   */
  EClass getReplacableValue();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.ReplacableValue#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.jaspersoft.studio.data.sql.ReplacableValue#getValue()
   * @see #getReplacableValue()
   * @generated
   */
  EAttribute getReplacableValue_Value();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.DoubleExpression <em>Double Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Double Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.DoubleExpression
   * @generated
   */
  EClass getDoubleExpression();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.DoubleExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.jaspersoft.studio.data.sql.DoubleExpression#getValue()
   * @see #getDoubleExpression()
   * @generated
   */
  EAttribute getDoubleExpression_Value();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.LongExpression <em>Long Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Long Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.LongExpression
   * @generated
   */
  EClass getLongExpression();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.LongExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.jaspersoft.studio.data.sql.LongExpression#getValue()
   * @see #getLongExpression()
   * @generated
   */
  EAttribute getLongExpression_Value();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.StringExpression <em>String Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.StringExpression
   * @generated
   */
  EClass getStringExpression();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.StringExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.jaspersoft.studio.data.sql.StringExpression#getValue()
   * @see #getStringExpression()
   * @generated
   */
  EAttribute getStringExpression_Value();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.NullExpression <em>Null Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Null Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.NullExpression
   * @generated
   */
  EClass getNullExpression();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.NullExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.jaspersoft.studio.data.sql.NullExpression#getValue()
   * @see #getNullExpression()
   * @generated
   */
  EAttribute getNullExpression_Value();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.DateExpression <em>Date Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Date Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.DateExpression
   * @generated
   */
  EClass getDateExpression();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.DateExpression#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see com.jaspersoft.studio.data.sql.DateExpression#getValue()
   * @see #getDateExpression()
   * @generated
   */
  EAttribute getDateExpression_Value();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.BooleanExpression <em>Boolean Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.BooleanExpression
   * @generated
   */
  EClass getBooleanExpression();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.BooleanExpression#getTrue <em>True</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>True</em>'.
   * @see com.jaspersoft.studio.data.sql.BooleanExpression#getTrue()
   * @see #getBooleanExpression()
   * @generated
   */
  EAttribute getBooleanExpression_True();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry <em>Multi Expression Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Multi Expression Where Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry
   * @generated
   */
  EClass getMultiExpressionWhereEntry();

  /**
   * Returns the meta object for the attribute '{@link com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry#getOperator()
   * @see #getMultiExpressionWhereEntry()
   * @generated
   */
  EAttribute getMultiExpressionWhereEntry_Operator();

  /**
   * Returns the meta object for the containment reference '{@link com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry#getRhs <em>Rhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rhs</em>'.
   * @see com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry#getRhs()
   * @see #getMultiExpressionWhereEntry()
   * @generated
   */
  EReference getMultiExpressionWhereEntry_Rhs();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.ArrayExpression <em>Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Array Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.ArrayExpression
   * @generated
   */
  EClass getArrayExpression();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.DoubleArrayExpression <em>Double Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Double Array Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.DoubleArrayExpression
   * @generated
   */
  EClass getDoubleArrayExpression();

  /**
   * Returns the meta object for the attribute list '{@link com.jaspersoft.studio.data.sql.DoubleArrayExpression#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see com.jaspersoft.studio.data.sql.DoubleArrayExpression#getValues()
   * @see #getDoubleArrayExpression()
   * @generated
   */
  EAttribute getDoubleArrayExpression_Values();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.LongArrayExpression <em>Long Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Long Array Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.LongArrayExpression
   * @generated
   */
  EClass getLongArrayExpression();

  /**
   * Returns the meta object for the attribute list '{@link com.jaspersoft.studio.data.sql.LongArrayExpression#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see com.jaspersoft.studio.data.sql.LongArrayExpression#getValues()
   * @see #getLongArrayExpression()
   * @generated
   */
  EAttribute getLongArrayExpression_Values();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.StringArrayExpression <em>String Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Array Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.StringArrayExpression
   * @generated
   */
  EClass getStringArrayExpression();

  /**
   * Returns the meta object for the attribute list '{@link com.jaspersoft.studio.data.sql.StringArrayExpression#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see com.jaspersoft.studio.data.sql.StringArrayExpression#getValues()
   * @see #getStringArrayExpression()
   * @generated
   */
  EAttribute getStringArrayExpression_Values();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.NullArrayExpression <em>Null Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Null Array Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.NullArrayExpression
   * @generated
   */
  EClass getNullArrayExpression();

  /**
   * Returns the meta object for the attribute list '{@link com.jaspersoft.studio.data.sql.NullArrayExpression#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see com.jaspersoft.studio.data.sql.NullArrayExpression#getValues()
   * @see #getNullArrayExpression()
   * @generated
   */
  EAttribute getNullArrayExpression_Values();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.DateArrayExpression <em>Date Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Date Array Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.DateArrayExpression
   * @generated
   */
  EClass getDateArrayExpression();

  /**
   * Returns the meta object for the attribute list '{@link com.jaspersoft.studio.data.sql.DateArrayExpression#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see com.jaspersoft.studio.data.sql.DateArrayExpression#getValues()
   * @see #getDateArrayExpression()
   * @generated
   */
  EAttribute getDateArrayExpression_Values();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.BooleanArrayExpression <em>Boolean Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Array Expression</em>'.
   * @see com.jaspersoft.studio.data.sql.BooleanArrayExpression
   * @generated
   */
  EClass getBooleanArrayExpression();

  /**
   * Returns the meta object for the attribute list '{@link com.jaspersoft.studio.data.sql.BooleanArrayExpression#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see com.jaspersoft.studio.data.sql.BooleanArrayExpression#getValues()
   * @see #getBooleanArrayExpression()
   * @generated
   */
  EAttribute getBooleanArrayExpression_Values();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.OrWhereEntry <em>Or Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Where Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.OrWhereEntry
   * @generated
   */
  EClass getOrWhereEntry();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.OrWhereEntry#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.OrWhereEntry#getEntries()
   * @see #getOrWhereEntry()
   * @generated
   */
  EReference getOrWhereEntry_Entries();

  /**
   * Returns the meta object for class '{@link com.jaspersoft.studio.data.sql.AndWhereEntry <em>And Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>And Where Entry</em>'.
   * @see com.jaspersoft.studio.data.sql.AndWhereEntry
   * @generated
   */
  EClass getAndWhereEntry();

  /**
   * Returns the meta object for the containment reference list '{@link com.jaspersoft.studio.data.sql.AndWhereEntry#getEntries <em>Entries</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Entries</em>'.
   * @see com.jaspersoft.studio.data.sql.AndWhereEntry#getEntries()
   * @see #getAndWhereEntry()
   * @generated
   */
  EReference getAndWhereEntry_Entries();

  /**
   * Returns the meta object for enum '{@link com.jaspersoft.studio.data.sql.ArrayOperator <em>Array Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Array Operator</em>'.
   * @see com.jaspersoft.studio.data.sql.ArrayOperator
   * @generated
   */
  EEnum getArrayOperator();

  /**
   * Returns the meta object for enum '{@link com.jaspersoft.studio.data.sql.Operator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Operator</em>'.
   * @see com.jaspersoft.studio.data.sql.Operator
   * @generated
   */
  EEnum getOperator();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SqlFactory getSqlFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ModelImpl <em>Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ModelImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getModel()
     * @generated
     */
    EClass MODEL = eINSTANCE.getModel();

    /**
     * The meta object literal for the '<em><b>Col</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__COL = eINSTANCE.getModel_Col();

    /**
     * The meta object literal for the '<em><b>Db</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__DB = eINSTANCE.getModel_Db();

    /**
     * The meta object literal for the '<em><b>Where Entry</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL__WHERE_ENTRY = eINSTANCE.getModel_WhereEntry();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.DatabaseImpl <em>Database</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.DatabaseImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDatabase()
     * @generated
     */
    EClass DATABASE = eINSTANCE.getDatabase();

    /**
     * The meta object literal for the '<em><b>Db Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATABASE__DB_NAME = eINSTANCE.getDatabase_DbName();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ColumnImpl <em>Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ColumnImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getColumn()
     * @generated
     */
    EClass COLUMN = eINSTANCE.getColumn();

    /**
     * The meta object literal for the '<em><b>Col</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLUMN__COL = eINSTANCE.getColumn_Col();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.WhereEntryImpl <em>Where Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.WhereEntryImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getWhereEntry()
     * @generated
     */
    EClass WHERE_ENTRY = eINSTANCE.getWhereEntry();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ExpressionWhereEntryImpl <em>Expression Where Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ExpressionWhereEntryImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getExpressionWhereEntry()
     * @generated
     */
    EClass EXPRESSION_WHERE_ENTRY = eINSTANCE.getExpressionWhereEntry();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION_WHERE_ENTRY__NAME = eINSTANCE.getExpressionWhereEntry_Name();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.SingleExpressionWhereEntryImpl <em>Single Expression Where Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.SingleExpressionWhereEntryImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getSingleExpressionWhereEntry()
     * @generated
     */
    EClass SINGLE_EXPRESSION_WHERE_ENTRY = eINSTANCE.getSingleExpressionWhereEntry();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SINGLE_EXPRESSION_WHERE_ENTRY__OPERATOR = eINSTANCE.getSingleExpressionWhereEntry_Operator();

    /**
     * The meta object literal for the '<em><b>Rhs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SINGLE_EXPRESSION_WHERE_ENTRY__RHS = eINSTANCE.getSingleExpressionWhereEntry_Rhs();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ReplacableValueImpl <em>Replacable Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ReplacableValueImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getReplacableValue()
     * @generated
     */
    EClass REPLACABLE_VALUE = eINSTANCE.getReplacableValue();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REPLACABLE_VALUE__VALUE = eINSTANCE.getReplacableValue_Value();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.DoubleExpressionImpl <em>Double Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.DoubleExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDoubleExpression()
     * @generated
     */
    EClass DOUBLE_EXPRESSION = eINSTANCE.getDoubleExpression();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOUBLE_EXPRESSION__VALUE = eINSTANCE.getDoubleExpression_Value();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.LongExpressionImpl <em>Long Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.LongExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getLongExpression()
     * @generated
     */
    EClass LONG_EXPRESSION = eINSTANCE.getLongExpression();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LONG_EXPRESSION__VALUE = eINSTANCE.getLongExpression_Value();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.StringExpressionImpl <em>String Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.StringExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getStringExpression()
     * @generated
     */
    EClass STRING_EXPRESSION = eINSTANCE.getStringExpression();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRING_EXPRESSION__VALUE = eINSTANCE.getStringExpression_Value();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.NullExpressionImpl <em>Null Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.NullExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getNullExpression()
     * @generated
     */
    EClass NULL_EXPRESSION = eINSTANCE.getNullExpression();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NULL_EXPRESSION__VALUE = eINSTANCE.getNullExpression_Value();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.DateExpressionImpl <em>Date Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.DateExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDateExpression()
     * @generated
     */
    EClass DATE_EXPRESSION = eINSTANCE.getDateExpression();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATE_EXPRESSION__VALUE = eINSTANCE.getDateExpression_Value();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.BooleanExpressionImpl <em>Boolean Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.BooleanExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getBooleanExpression()
     * @generated
     */
    EClass BOOLEAN_EXPRESSION = eINSTANCE.getBooleanExpression();

    /**
     * The meta object literal for the '<em><b>True</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN_EXPRESSION__TRUE = eINSTANCE.getBooleanExpression_True();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.MultiExpressionWhereEntryImpl <em>Multi Expression Where Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.MultiExpressionWhereEntryImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getMultiExpressionWhereEntry()
     * @generated
     */
    EClass MULTI_EXPRESSION_WHERE_ENTRY = eINSTANCE.getMultiExpressionWhereEntry();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MULTI_EXPRESSION_WHERE_ENTRY__OPERATOR = eINSTANCE.getMultiExpressionWhereEntry_Operator();

    /**
     * The meta object literal for the '<em><b>Rhs</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MULTI_EXPRESSION_WHERE_ENTRY__RHS = eINSTANCE.getMultiExpressionWhereEntry_Rhs();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.ArrayExpressionImpl <em>Array Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.ArrayExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getArrayExpression()
     * @generated
     */
    EClass ARRAY_EXPRESSION = eINSTANCE.getArrayExpression();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.DoubleArrayExpressionImpl <em>Double Array Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.DoubleArrayExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDoubleArrayExpression()
     * @generated
     */
    EClass DOUBLE_ARRAY_EXPRESSION = eINSTANCE.getDoubleArrayExpression();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DOUBLE_ARRAY_EXPRESSION__VALUES = eINSTANCE.getDoubleArrayExpression_Values();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.LongArrayExpressionImpl <em>Long Array Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.LongArrayExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getLongArrayExpression()
     * @generated
     */
    EClass LONG_ARRAY_EXPRESSION = eINSTANCE.getLongArrayExpression();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LONG_ARRAY_EXPRESSION__VALUES = eINSTANCE.getLongArrayExpression_Values();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.StringArrayExpressionImpl <em>String Array Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.StringArrayExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getStringArrayExpression()
     * @generated
     */
    EClass STRING_ARRAY_EXPRESSION = eINSTANCE.getStringArrayExpression();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRING_ARRAY_EXPRESSION__VALUES = eINSTANCE.getStringArrayExpression_Values();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.NullArrayExpressionImpl <em>Null Array Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.NullArrayExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getNullArrayExpression()
     * @generated
     */
    EClass NULL_ARRAY_EXPRESSION = eINSTANCE.getNullArrayExpression();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NULL_ARRAY_EXPRESSION__VALUES = eINSTANCE.getNullArrayExpression_Values();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.DateArrayExpressionImpl <em>Date Array Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.DateArrayExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getDateArrayExpression()
     * @generated
     */
    EClass DATE_ARRAY_EXPRESSION = eINSTANCE.getDateArrayExpression();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATE_ARRAY_EXPRESSION__VALUES = eINSTANCE.getDateArrayExpression_Values();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.BooleanArrayExpressionImpl <em>Boolean Array Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.BooleanArrayExpressionImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getBooleanArrayExpression()
     * @generated
     */
    EClass BOOLEAN_ARRAY_EXPRESSION = eINSTANCE.getBooleanArrayExpression();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN_ARRAY_EXPRESSION__VALUES = eINSTANCE.getBooleanArrayExpression_Values();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.OrWhereEntryImpl <em>Or Where Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.OrWhereEntryImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOrWhereEntry()
     * @generated
     */
    EClass OR_WHERE_ENTRY = eINSTANCE.getOrWhereEntry();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_WHERE_ENTRY__ENTRIES = eINSTANCE.getOrWhereEntry_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.impl.AndWhereEntryImpl <em>And Where Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.impl.AndWhereEntryImpl
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getAndWhereEntry()
     * @generated
     */
    EClass AND_WHERE_ENTRY = eINSTANCE.getAndWhereEntry();

    /**
     * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AND_WHERE_ENTRY__ENTRIES = eINSTANCE.getAndWhereEntry_Entries();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.ArrayOperator <em>Array Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.ArrayOperator
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getArrayOperator()
     * @generated
     */
    EEnum ARRAY_OPERATOR = eINSTANCE.getArrayOperator();

    /**
     * The meta object literal for the '{@link com.jaspersoft.studio.data.sql.Operator <em>Operator</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see com.jaspersoft.studio.data.sql.Operator
     * @see com.jaspersoft.studio.data.sql.impl.SqlPackageImpl#getOperator()
     * @generated
     */
    EEnum OPERATOR = eINSTANCE.getOperator();

  }

} //SqlPackage
