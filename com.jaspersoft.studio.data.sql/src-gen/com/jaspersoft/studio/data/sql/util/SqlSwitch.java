/**
 */
package com.jaspersoft.studio.data.sql.util;

import com.jaspersoft.studio.data.sql.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see com.jaspersoft.studio.data.sql.SqlPackage
 * @generated
 */
public class SqlSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SqlPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = SqlPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case SqlPackage.MODEL:
      {
        Model model = (Model)theEObject;
        T result = caseModel(model);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ORDER_BY_COLUMNS:
      {
        OrderByColumns orderByColumns = (OrderByColumns)theEObject;
        T result = caseOrderByColumns(orderByColumns);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ORDER_BY_COLUMN_FULL:
      {
        OrderByColumnFull orderByColumnFull = (OrderByColumnFull)theEObject;
        T result = caseOrderByColumnFull(orderByColumnFull);
        if (result == null) result = caseOrderByColumns(orderByColumnFull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.GROUP_BY_COLUMNS:
      {
        GroupByColumns groupByColumns = (GroupByColumns)theEObject;
        T result = caseGroupByColumns(groupByColumns);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.GROUP_BY_COLUMN_FULL:
      {
        GroupByColumnFull groupByColumnFull = (GroupByColumnFull)theEObject;
        T result = caseGroupByColumnFull(groupByColumnFull);
        if (result == null) result = caseGroupByColumns(groupByColumnFull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMNS:
      {
        Columns columns = (Columns)theEObject;
        T result = caseColumns(columns);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMN_OR_ALIAS:
      {
        ColumnOrAlias columnOrAlias = (ColumnOrAlias)theEObject;
        T result = caseColumnOrAlias(columnOrAlias);
        if (result == null) result = caseColumns(columnOrAlias);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMN_FULL:
      {
        ColumnFull columnFull = (ColumnFull)theEObject;
        T result = caseColumnFull(columnFull);
        if (result == null) result = caseColumnOrAlias(columnFull);
        if (result == null) result = caseColumns(columnFull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMN_ALIAS:
      {
        ColumnAlias columnAlias = (ColumnAlias)theEObject;
        T result = caseColumnAlias(columnAlias);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMN:
      {
        Column column = (Column)theEObject;
        T result = caseColumn(column);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.TABLES:
      {
        Tables tables = (Tables)theEObject;
        T result = caseTables(tables);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.TABLE_OR_ALIAS:
      {
        TableOrAlias tableOrAlias = (TableOrAlias)theEObject;
        T result = caseTableOrAlias(tableOrAlias);
        if (result == null) result = caseTables(tableOrAlias);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.TABLE_FULL:
      {
        TableFull tableFull = (TableFull)theEObject;
        T result = caseTableFull(tableFull);
        if (result == null) result = caseOrderByColumnFull(tableFull);
        if (result == null) result = caseGroupByColumnFull(tableFull);
        if (result == null) result = caseColumnFull(tableFull);
        if (result == null) result = caseTableOrAlias(tableFull);
        if (result == null) result = caseOrderByColumns(tableFull);
        if (result == null) result = caseGroupByColumns(tableFull);
        if (result == null) result = caseColumnOrAlias(tableFull);
        if (result == null) result = caseTables(tableFull);
        if (result == null) result = caseColumns(tableFull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.TABLE:
      {
        Table table = (Table)theEObject;
        T result = caseTable(table);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.TABLE_ALIAS:
      {
        TableAlias tableAlias = (TableAlias)theEObject;
        T result = caseTableAlias(tableAlias);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SCHEMA:
      {
        Schema schema = (Schema)theEObject;
        T result = caseSchema(schema);
        if (result == null) result = caseTableFull(schema);
        if (result == null) result = caseOrderByColumnFull(schema);
        if (result == null) result = caseGroupByColumnFull(schema);
        if (result == null) result = caseColumnFull(schema);
        if (result == null) result = caseTableOrAlias(schema);
        if (result == null) result = caseOrderByColumns(schema);
        if (result == null) result = caseGroupByColumns(schema);
        if (result == null) result = caseColumnOrAlias(schema);
        if (result == null) result = caseTables(schema);
        if (result == null) result = caseColumns(schema);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.DATABASE:
      {
        Database database = (Database)theEObject;
        T result = caseDatabase(database);
        if (result == null) result = caseSchema(database);
        if (result == null) result = caseTableFull(database);
        if (result == null) result = caseOrderByColumnFull(database);
        if (result == null) result = caseGroupByColumnFull(database);
        if (result == null) result = caseColumnFull(database);
        if (result == null) result = caseTableOrAlias(database);
        if (result == null) result = caseOrderByColumns(database);
        if (result == null) result = caseGroupByColumns(database);
        if (result == null) result = caseColumnOrAlias(database);
        if (result == null) result = caseTables(database);
        if (result == null) result = caseColumns(database);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.WHERE_ENTRY:
      {
        WhereEntry whereEntry = (WhereEntry)theEObject;
        T result = caseWhereEntry(whereEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.HAVING_ENTRY:
      {
        HavingEntry havingEntry = (HavingEntry)theEObject;
        T result = caseHavingEntry(havingEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.EXPRESSION_WHERE_ENTRY:
      {
        ExpressionWhereEntry expressionWhereEntry = (ExpressionWhereEntry)theEObject;
        T result = caseExpressionWhereEntry(expressionWhereEntry);
        if (result == null) result = caseWhereEntry(expressionWhereEntry);
        if (result == null) result = caseHavingEntry(expressionWhereEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SINGLE_EXPRESSION_WHERE_ENTRY:
      {
        SingleExpressionWhereEntry singleExpressionWhereEntry = (SingleExpressionWhereEntry)theEObject;
        T result = caseSingleExpressionWhereEntry(singleExpressionWhereEntry);
        if (result == null) result = caseExpressionWhereEntry(singleExpressionWhereEntry);
        if (result == null) result = caseWhereEntry(singleExpressionWhereEntry);
        if (result == null) result = caseHavingEntry(singleExpressionWhereEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.EXPRESSION:
      {
        Expression expression = (Expression)theEObject;
        T result = caseExpression(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.REPLACABLE_VALUE:
      {
        ReplacableValue replacableValue = (ReplacableValue)theEObject;
        T result = caseReplacableValue(replacableValue);
        if (result == null) result = caseExpression(replacableValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.DOUBLE_EXPRESSION:
      {
        DoubleExpression doubleExpression = (DoubleExpression)theEObject;
        T result = caseDoubleExpression(doubleExpression);
        if (result == null) result = caseExpression(doubleExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.LONG_EXPRESSION:
      {
        LongExpression longExpression = (LongExpression)theEObject;
        T result = caseLongExpression(longExpression);
        if (result == null) result = caseExpression(longExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.STRING_EXPRESSION:
      {
        StringExpression stringExpression = (StringExpression)theEObject;
        T result = caseStringExpression(stringExpression);
        if (result == null) result = caseExpression(stringExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.NULL_EXPRESSION:
      {
        NullExpression nullExpression = (NullExpression)theEObject;
        T result = caseNullExpression(nullExpression);
        if (result == null) result = caseExpression(nullExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.DATE_EXPRESSION:
      {
        DateExpression dateExpression = (DateExpression)theEObject;
        T result = caseDateExpression(dateExpression);
        if (result == null) result = caseExpression(dateExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.BOOLEAN_EXPRESSION:
      {
        BooleanExpression booleanExpression = (BooleanExpression)theEObject;
        T result = caseBooleanExpression(booleanExpression);
        if (result == null) result = caseExpression(booleanExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.MULTI_EXPRESSION_WHERE_ENTRY:
      {
        MultiExpressionWhereEntry multiExpressionWhereEntry = (MultiExpressionWhereEntry)theEObject;
        T result = caseMultiExpressionWhereEntry(multiExpressionWhereEntry);
        if (result == null) result = caseExpressionWhereEntry(multiExpressionWhereEntry);
        if (result == null) result = caseWhereEntry(multiExpressionWhereEntry);
        if (result == null) result = caseHavingEntry(multiExpressionWhereEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ARRAY_EXPRESSION:
      {
        ArrayExpression arrayExpression = (ArrayExpression)theEObject;
        T result = caseArrayExpression(arrayExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.DOUBLE_ARRAY_EXPRESSION:
      {
        DoubleArrayExpression doubleArrayExpression = (DoubleArrayExpression)theEObject;
        T result = caseDoubleArrayExpression(doubleArrayExpression);
        if (result == null) result = caseArrayExpression(doubleArrayExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.LONG_ARRAY_EXPRESSION:
      {
        LongArrayExpression longArrayExpression = (LongArrayExpression)theEObject;
        T result = caseLongArrayExpression(longArrayExpression);
        if (result == null) result = caseArrayExpression(longArrayExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.STRING_ARRAY_EXPRESSION:
      {
        StringArrayExpression stringArrayExpression = (StringArrayExpression)theEObject;
        T result = caseStringArrayExpression(stringArrayExpression);
        if (result == null) result = caseArrayExpression(stringArrayExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.NULL_ARRAY_EXPRESSION:
      {
        NullArrayExpression nullArrayExpression = (NullArrayExpression)theEObject;
        T result = caseNullArrayExpression(nullArrayExpression);
        if (result == null) result = caseArrayExpression(nullArrayExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.DATE_ARRAY_EXPRESSION:
      {
        DateArrayExpression dateArrayExpression = (DateArrayExpression)theEObject;
        T result = caseDateArrayExpression(dateArrayExpression);
        if (result == null) result = caseArrayExpression(dateArrayExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.BOOLEAN_ARRAY_EXPRESSION:
      {
        BooleanArrayExpression booleanArrayExpression = (BooleanArrayExpression)theEObject;
        T result = caseBooleanArrayExpression(booleanArrayExpression);
        if (result == null) result = caseArrayExpression(booleanArrayExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_ORDER_BY_COLUMN:
      {
        OrOrderByColumn orOrderByColumn = (OrOrderByColumn)theEObject;
        T result = caseOrOrderByColumn(orOrderByColumn);
        if (result == null) result = caseOrderByColumns(orOrderByColumn);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_GROUP_BY_COLUMN:
      {
        OrGroupByColumn orGroupByColumn = (OrGroupByColumn)theEObject;
        T result = caseOrGroupByColumn(orGroupByColumn);
        if (result == null) result = caseGroupByColumns(orGroupByColumn);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_COLUMN:
      {
        OrColumn orColumn = (OrColumn)theEObject;
        T result = caseOrColumn(orColumn);
        if (result == null) result = caseColumns(orColumn);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_TABLE:
      {
        OrTable orTable = (OrTable)theEObject;
        T result = caseOrTable(orTable);
        if (result == null) result = caseTables(orTable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_WHERE_ENTRY:
      {
        OrWhereEntry orWhereEntry = (OrWhereEntry)theEObject;
        T result = caseOrWhereEntry(orWhereEntry);
        if (result == null) result = caseWhereEntry(orWhereEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.AND_WHERE_ENTRY:
      {
        AndWhereEntry andWhereEntry = (AndWhereEntry)theEObject;
        T result = caseAndWhereEntry(andWhereEntry);
        if (result == null) result = caseWhereEntry(andWhereEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_HAVING_ENTRY:
      {
        OrHavingEntry orHavingEntry = (OrHavingEntry)theEObject;
        T result = caseOrHavingEntry(orHavingEntry);
        if (result == null) result = caseHavingEntry(orHavingEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.AND_HAVING_ENTRY:
      {
        AndHavingEntry andHavingEntry = (AndHavingEntry)theEObject;
        T result = caseAndHavingEntry(andHavingEntry);
        if (result == null) result = caseHavingEntry(andHavingEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModel(Model object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Order By Columns</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Order By Columns</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrderByColumns(OrderByColumns object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Order By Column Full</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Order By Column Full</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrderByColumnFull(OrderByColumnFull object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group By Columns</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group By Columns</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupByColumns(GroupByColumns object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group By Column Full</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group By Column Full</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupByColumnFull(GroupByColumnFull object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Columns</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Columns</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseColumns(Columns object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Column Or Alias</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Column Or Alias</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseColumnOrAlias(ColumnOrAlias object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Column Full</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Column Full</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseColumnFull(ColumnFull object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Column Alias</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Column Alias</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseColumnAlias(ColumnAlias object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Column</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Column</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseColumn(Column object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Tables</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Tables</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTables(Tables object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Table Or Alias</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Table Or Alias</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTableOrAlias(TableOrAlias object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Table Full</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Table Full</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTableFull(TableFull object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Table</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTable(Table object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Table Alias</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Table Alias</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTableAlias(TableAlias object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Schema</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Schema</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSchema(Schema object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Database</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Database</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDatabase(Database object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Where Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Where Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWhereEntry(WhereEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Having Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Having Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHavingEntry(HavingEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression Where Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression Where Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpressionWhereEntry(ExpressionWhereEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Single Expression Where Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Single Expression Where Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSingleExpressionWhereEntry(SingleExpressionWhereEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression(Expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Replacable Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Replacable Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReplacableValue(ReplacableValue object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Double Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Double Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDoubleExpression(DoubleExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Long Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Long Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLongExpression(LongExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringExpression(StringExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Null Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Null Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNullExpression(NullExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Date Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Date Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDateExpression(DateExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanExpression(BooleanExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Multi Expression Where Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Multi Expression Where Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMultiExpressionWhereEntry(MultiExpressionWhereEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Array Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Array Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArrayExpression(ArrayExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Double Array Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Double Array Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDoubleArrayExpression(DoubleArrayExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Long Array Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Long Array Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLongArrayExpression(LongArrayExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Array Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Array Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringArrayExpression(StringArrayExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Null Array Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Null Array Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNullArrayExpression(NullArrayExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Date Array Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Date Array Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDateArrayExpression(DateArrayExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Array Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Array Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanArrayExpression(BooleanArrayExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Order By Column</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Order By Column</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrOrderByColumn(OrOrderByColumn object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Group By Column</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Group By Column</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrGroupByColumn(OrGroupByColumn object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Column</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Column</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrColumn(OrColumn object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Table</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Table</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrTable(OrTable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Where Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Where Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrWhereEntry(OrWhereEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>And Where Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>And Where Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAndWhereEntry(AndWhereEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Having Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Having Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrHavingEntry(OrHavingEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>And Having Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>And Having Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAndHavingEntry(AndHavingEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //SqlSwitch
