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
   * @param ePackage the package in question.
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
      case SqlPackage.WITH_QUERY:
      {
        WithQuery withQuery = (WithQuery)theEObject;
        T result = caseWithQuery(withQuery);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.WITH_COLUMNS:
      {
        WithColumns withColumns = (WithColumns)theEObject;
        T result = caseWithColumns(withColumns);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FETCH_FIRST:
      {
        FetchFirst fetchFirst = (FetchFirst)theEObject;
        T result = caseFetchFirst(fetchFirst);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OFFSET:
      {
        Offset offset = (Offset)theEObject;
        T result = caseOffset(offset);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.LIMIT:
      {
        Limit limit = (Limit)theEObject;
        T result = caseLimit(limit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SELECT_QUERY:
      {
        SelectQuery selectQuery = (SelectQuery)theEObject;
        T result = caseSelectQuery(selectQuery);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SELECT_SUB_SET:
      {
        SelectSubSet selectSubSet = (SelectSubSet)theEObject;
        T result = caseSelectSubSet(selectSubSet);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SELECT:
      {
        Select select = (Select)theEObject;
        T result = caseSelect(select);
        if (result == null) result = caseSelectQuery(select);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_COLUMN:
      {
        OrColumn orColumn = (OrColumn)theEObject;
        T result = caseOrColumn(orColumn);
        if (result == null) result = casePivotForClause(orColumn);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMN_OR_ALIAS:
      {
        ColumnOrAlias columnOrAlias = (ColumnOrAlias)theEObject;
        T result = caseColumnOrAlias(columnOrAlias);
        if (result == null) result = caseOrColumn(columnOrAlias);
        if (result == null) result = casePivotForClause(columnOrAlias);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMN_FULL:
      {
        ColumnFull columnFull = (ColumnFull)theEObject;
        T result = caseColumnFull(columnFull);
        if (result == null) result = casePivotForClause(columnFull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_TABLE:
      {
        OrTable orTable = (OrTable)theEObject;
        T result = caseOrTable(orTable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FROM_TABLE:
      {
        FromTable fromTable = (FromTable)theEObject;
        T result = caseFromTable(fromTable);
        if (result == null) result = caseOrTable(fromTable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FROM_TABLE_JOIN:
      {
        FromTableJoin fromTableJoin = (FromTableJoin)theEObject;
        T result = caseFromTableJoin(fromTableJoin);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.JOIN_CONDITION:
      {
        JoinCondition joinCondition = (JoinCondition)theEObject;
        T result = caseJoinCondition(joinCondition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.USING_COLS:
      {
        UsingCols usingCols = (UsingCols)theEObject;
        T result = caseUsingCols(usingCols);
        if (result == null) result = caseWithColumns(usingCols);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.TABLE_OR_ALIAS:
      {
        TableOrAlias tableOrAlias = (TableOrAlias)theEObject;
        T result = caseTableOrAlias(tableOrAlias);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FROM_VALUES:
      {
        FromValues fromValues = (FromValues)theEObject;
        T result = caseFromValues(fromValues);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FROM_VALUES_COLUMNS:
      {
        FromValuesColumns fromValuesColumns = (FromValuesColumns)theEObject;
        T result = caseFromValuesColumns(fromValuesColumns);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FROM_VALUES_COLUMN_NAMES:
      {
        FromValuesColumnNames fromValuesColumnNames = (FromValuesColumnNames)theEObject;
        T result = caseFromValuesColumnNames(fromValuesColumnNames);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMN_NAMES:
      {
        ColumnNames columnNames = (ColumnNames)theEObject;
        T result = caseColumnNames(columnNames);
        if (result == null) result = caseFromValuesColumnNames(columnNames);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.VALUES:
      {
        Values values = (Values)theEObject;
        T result = caseValues(values);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ROWS:
      {
        Rows rows = (Rows)theEObject;
        T result = caseRows(rows);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ROW:
      {
        Row row = (Row)theEObject;
        T result = caseRow(row);
        if (result == null) result = caseRows(row);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ROW_VALUES:
      {
        RowValues rowValues = (RowValues)theEObject;
        T result = caseRowValues(rowValues);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ROW_VALUE:
      {
        RowValue rowValue = (RowValue)theEObject;
        T result = caseRowValue(rowValue);
        if (result == null) result = caseRowValues(rowValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PIVOT_TABLE:
      {
        PivotTable pivotTable = (PivotTable)theEObject;
        T result = casePivotTable(pivotTable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PIVOT_FUNCTIONS:
      {
        PivotFunctions pivotFunctions = (PivotFunctions)theEObject;
        T result = casePivotFunctions(pivotFunctions);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PIVOT_FUNCTION:
      {
        PivotFunction pivotFunction = (PivotFunction)theEObject;
        T result = casePivotFunction(pivotFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PIVOT_IN_CLAUSE:
      {
        PivotInClause pivotInClause = (PivotInClause)theEObject;
        T result = casePivotInClause(pivotInClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.UNPIVOT_TABLE:
      {
        UnpivotTable unpivotTable = (UnpivotTable)theEObject;
        T result = caseUnpivotTable(unpivotTable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.UNPIVOT_IN_CLAUSE:
      {
        UnpivotInClause unpivotInClause = (UnpivotInClause)theEObject;
        T result = caseUnpivotInClause(unpivotInClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARGS:
      {
        UnpivotInClauseArgs unpivotInClauseArgs = (UnpivotInClauseArgs)theEObject;
        T result = caseUnpivotInClauseArgs(unpivotInClauseArgs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.UNPIVOT_IN_CLAUSE_ARG:
      {
        UnpivotInClauseArg unpivotInClauseArg = (UnpivotInClauseArg)theEObject;
        T result = caseUnpivotInClauseArg(unpivotInClauseArg);
        if (result == null) result = caseUnpivotInClauseArgs(unpivotInClauseArg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PIVOT_FOR_CLAUSE:
      {
        PivotForClause pivotForClause = (PivotForClause)theEObject;
        T result = casePivotForClause(pivotForClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PIVOT_COLUMNS:
      {
        PivotColumns pivotColumns = (PivotColumns)theEObject;
        T result = casePivotColumns(pivotColumns);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PIVOTS:
      {
        Pivots pivots = (Pivots)theEObject;
        T result = casePivots(pivots);
        if (result == null) result = casePivotColumns(pivots);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PIVOT_COL:
      {
        PivotCol pivotCol = (PivotCol)theEObject;
        T result = casePivotCol(pivotCol);
        if (result == null) result = casePivotFunction(pivotCol);
        if (result == null) result = casePivots(pivotCol);
        if (result == null) result = casePivotColumns(pivotCol);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.TABLE_FULL:
      {
        TableFull tableFull = (TableFull)theEObject;
        T result = caseTableFull(tableFull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.DB_OBJECT_NAME_ALL:
      {
        DbObjectNameAll dbObjectNameAll = (DbObjectNameAll)theEObject;
        T result = caseDbObjectNameAll(dbObjectNameAll);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.DB_OBJECT_NAME:
      {
        DbObjectName dbObjectName = (DbObjectName)theEObject;
        T result = caseDbObjectName(dbObjectName);
        if (result == null) result = caseColumnFull(dbObjectName);
        if (result == null) result = caseUsingCols(dbObjectName);
        if (result == null) result = casePivotCol(dbObjectName);
        if (result == null) result = caseTableFull(dbObjectName);
        if (result == null) result = casePivotForClause(dbObjectName);
        if (result == null) result = caseWithColumns(dbObjectName);
        if (result == null) result = casePivotFunction(dbObjectName);
        if (result == null) result = casePivots(dbObjectName);
        if (result == null) result = casePivotColumns(dbObjectName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_ORDER_BY_COLUMN:
      {
        OrOrderByColumn orOrderByColumn = (OrOrderByColumn)theEObject;
        T result = caseOrOrderByColumn(orOrderByColumn);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ORDER_BY_COLUMN_FULL:
      {
        OrderByColumnFull orderByColumnFull = (OrderByColumnFull)theEObject;
        T result = caseOrderByColumnFull(orderByColumnFull);
        if (result == null) result = caseOrOrderByColumn(orderByColumnFull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_GROUP_BY_COLUMN:
      {
        OrGroupByColumn orGroupByColumn = (OrGroupByColumn)theEObject;
        T result = caseOrGroupByColumn(orGroupByColumn);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.GROUP_BY_COLUMN_FULL:
      {
        GroupByColumnFull groupByColumnFull = (GroupByColumnFull)theEObject;
        T result = caseGroupByColumnFull(groupByColumnFull);
        if (result == null) result = caseOrGroupByColumn(groupByColumnFull);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OR_EXPR:
      {
        OrExpr orExpr = (OrExpr)theEObject;
        T result = caseOrExpr(orExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FULL_EXPRESSION:
      {
        FullExpression fullExpression = (FullExpression)theEObject;
        T result = caseFullExpression(fullExpression);
        if (result == null) result = caseOrExpr(fullExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.EXPR_GROUP:
      {
        ExprGroup exprGroup = (ExprGroup)theEObject;
        T result = caseExprGroup(exprGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.XEXPR:
      {
        XExpr xExpr = (XExpr)theEObject;
        T result = caseXExpr(xExpr);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PRMS:
      {
        Prms prms = (Prms)theEObject;
        T result = casePrms(prms);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.JR_PARAMETER:
      {
        JRParameter jrParameter = (JRParameter)theEObject;
        T result = caseJRParameter(jrParameter);
        if (result == null) result = casePrms(jrParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COMPARISON:
      {
        Comparison comparison = (Comparison)theEObject;
        T result = caseComparison(comparison);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.LIKE:
      {
        Like like = (Like)theEObject;
        T result = caseLike(like);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.LIKE_OPERAND:
      {
        LikeOperand likeOperand = (LikeOperand)theEObject;
        T result = caseLikeOperand(likeOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.BETWEEN:
      {
        Between between = (Between)theEObject;
        T result = caseBetween(between);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.IN_OPER:
      {
        InOper inOper = (InOper)theEObject;
        T result = caseInOper(inOper);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.EXISTS_OPER:
      {
        ExistsOper existsOper = (ExistsOper)theEObject;
        T result = caseExistsOper(existsOper);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OPERAND_LIST_GROUP:
      {
        OperandListGroup operandListGroup = (OperandListGroup)theEObject;
        T result = caseOperandListGroup(operandListGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OPERAND_LIST:
      {
        OperandList operandList = (OperandList)theEObject;
        T result = caseOperandList(operandList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OPERANDS:
      {
        Operands operands = (Operands)theEObject;
        T result = caseOperands(operands);
        if (result == null) result = caseOpFunctionArgAgregate(operands);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OPERAND:
      {
        Operand operand = (Operand)theEObject;
        T result = caseOperand(operand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OP_FUNCTION:
      {
        OpFunction opFunction = (OpFunction)theEObject;
        T result = caseOpFunction(opFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FUNCTION_EXTRACT:
      {
        FunctionExtract functionExtract = (FunctionExtract)theEObject;
        T result = caseFunctionExtract(functionExtract);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.FUNCTION_ANALYTICAL:
      {
        FunctionAnalytical functionAnalytical = (FunctionAnalytical)theEObject;
        T result = caseFunctionAnalytical(functionAnalytical);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ANALYTIC_CLAUSE:
      {
        AnalyticClause analyticClause = (AnalyticClause)theEObject;
        T result = caseAnalyticClause(analyticClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.WINDOWING_CLAUSE:
      {
        WindowingClause windowingClause = (WindowingClause)theEObject;
        T result = caseWindowingClause(windowingClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.WINDOWING_CLAUSE_BETWEEN:
      {
        WindowingClauseBetween windowingClauseBetween = (WindowingClauseBetween)theEObject;
        T result = caseWindowingClauseBetween(windowingClauseBetween);
        if (result == null) result = caseWindowingClause(windowingClauseBetween);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.WINDOWING_CLAUSE_OPERAND_FOLLOWING:
      {
        WindowingClauseOperandFollowing windowingClauseOperandFollowing = (WindowingClauseOperandFollowing)theEObject;
        T result = caseWindowingClauseOperandFollowing(windowingClauseOperandFollowing);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.WINDOWING_CLAUSE_OPERAND_PRECEDING:
      {
        WindowingClauseOperandPreceding windowingClauseOperandPreceding = (WindowingClauseOperandPreceding)theEObject;
        T result = caseWindowingClauseOperandPreceding(windowingClauseOperandPreceding);
        if (result == null) result = caseWindowingClause(windowingClauseOperandPreceding);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ORDER_BY_CLAUSE:
      {
        OrderByClause orderByClause = (OrderByClause)theEObject;
        T result = caseOrderByClause(orderByClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ORDER_BY_CLAUSE_ARGS:
      {
        OrderByClauseArgs orderByClauseArgs = (OrderByClauseArgs)theEObject;
        T result = caseOrderByClauseArgs(orderByClauseArgs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ORDER_BY_CLAUSE_ARG:
      {
        OrderByClauseArg orderByClauseArg = (OrderByClauseArg)theEObject;
        T result = caseOrderByClauseArg(orderByClauseArg);
        if (result == null) result = caseOrderByClauseArgs(orderByClauseArg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.QUERY_PARTITION_CLAUSE:
      {
        QueryPartitionClause queryPartitionClause = (QueryPartitionClause)theEObject;
        T result = caseQueryPartitionClause(queryPartitionClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ANALYTIC_EXPR_ARGS:
      {
        AnalyticExprArgs analyticExprArgs = (AnalyticExprArgs)theEObject;
        T result = caseAnalyticExprArgs(analyticExprArgs);
        if (result == null) result = caseQueryPartitionClause(analyticExprArgs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ANALYTIC_EXPR_ARG:
      {
        AnalyticExprArg analyticExprArg = (AnalyticExprArg)theEObject;
        T result = caseAnalyticExprArg(analyticExprArg);
        if (result == null) result = caseAnalyticExprArgs(analyticExprArg);
        if (result == null) result = caseQueryPartitionClause(analyticExprArg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OP_FUNCTION_ARG:
      {
        OpFunctionArg opFunctionArg = (OpFunctionArg)theEObject;
        T result = caseOpFunctionArg(opFunctionArg);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OP_FUNCTION_ARG_OPERAND:
      {
        OpFunctionArgOperand opFunctionArgOperand = (OpFunctionArgOperand)theEObject;
        T result = caseOpFunctionArgOperand(opFunctionArgOperand);
        if (result == null) result = caseOpFunctionArg(opFunctionArgOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OP_FUNCTION_CAST:
      {
        OpFunctionCast opFunctionCast = (OpFunctionCast)theEObject;
        T result = caseOpFunctionCast(opFunctionCast);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OP_FUNCTION_ARG_AGREGATE:
      {
        OpFunctionArgAgregate opFunctionArgAgregate = (OpFunctionArgAgregate)theEObject;
        T result = caseOpFunctionArgAgregate(opFunctionArgAgregate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.POPERAND:
      {
        POperand pOperand = (POperand)theEObject;
        T result = casePOperand(pOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.EXP_OPERAND:
      {
        ExpOperand expOperand = (ExpOperand)theEObject;
        T result = caseExpOperand(expOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COLUMN_OPERAND:
      {
        ColumnOperand columnOperand = (ColumnOperand)theEObject;
        T result = caseColumnOperand(columnOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SUB_QUERY_OPERAND:
      {
        SubQueryOperand subQueryOperand = (SubQueryOperand)theEObject;
        T result = caseSubQueryOperand(subQueryOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SCALAR_OPERAND:
      {
        ScalarOperand scalarOperand = (ScalarOperand)theEObject;
        T result = caseScalarOperand(scalarOperand);
        if (result == null) result = caseRowValue(scalarOperand);
        if (result == null) result = caseOperandList(scalarOperand);
        if (result == null) result = caseRowValues(scalarOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SQL_CASE_OPERAND:
      {
        SQLCaseOperand sqlCaseOperand = (SQLCaseOperand)theEObject;
        T result = caseSQLCaseOperand(sqlCaseOperand);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SQL_CASE_WHENS:
      {
        SQLCaseWhens sqlCaseWhens = (SQLCaseWhens)theEObject;
        T result = caseSQLCaseWhens(sqlCaseWhens);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.SQL_CASE_WHEN:
      {
        SqlCaseWhen sqlCaseWhen = (SqlCaseWhen)theEObject;
        T result = caseSqlCaseWhen(sqlCaseWhen);
        if (result == null) result = caseSQLCaseWhens(sqlCaseWhen);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.INTEGER_VALUE:
      {
        IntegerValue integerValue = (IntegerValue)theEObject;
        T result = caseIntegerValue(integerValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.UNSIGNED_VALUE:
      {
        UnsignedValue unsignedValue = (UnsignedValue)theEObject;
        T result = caseUnsignedValue(unsignedValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.COL:
      {
        Col col = (Col)theEObject;
        T result = caseCol(col);
        if (result == null) result = caseColumnFull(col);
        if (result == null) result = casePivotForClause(col);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.ABC:
      {
        abc abc = (abc)theEObject;
        T result = caseabc(abc);
        if (result == null) result = caseFromValuesColumnNames(abc);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.UNIPIVOT_IN_CLAUSE:
      {
        UnipivotInClause unipivotInClause = (UnipivotInClause)theEObject;
        T result = caseUnipivotInClause(unipivotInClause);
        if (result == null) result = caseUnpivotInClause(unipivotInClause);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.UICARGS:
      {
        uicargs uicargs = (uicargs)theEObject;
        T result = caseuicargs(uicargs);
        if (result == null) result = caseUnpivotInClauseArgs(uicargs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PVCS:
      {
        pvcs pvcs = (pvcs)theEObject;
        T result = casepvcs(pvcs);
        if (result == null) result = casePivots(pvcs);
        if (result == null) result = casePivotColumns(pvcs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PCOLS:
      {
        pcols pcols = (pcols)theEObject;
        T result = casepcols(pcols);
        if (result == null) result = casePivotCol(pcols);
        if (result == null) result = casePivotFunction(pcols);
        if (result == null) result = casePivots(pcols);
        if (result == null) result = casePivotColumns(pcols);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.TBLS:
      {
        tbls tbls = (tbls)theEObject;
        T result = casetbls(tbls);
        if (result == null) result = caseTableFull(tbls);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OP_LIST:
      {
        OpList opList = (OpList)theEObject;
        T result = caseOpList(opList);
        if (result == null) result = caseOperandList(opList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.PLUS:
      {
        Plus plus = (Plus)theEObject;
        T result = casePlus(plus);
        if (result == null) result = caseOperands(plus);
        if (result == null) result = caseOpFunctionArgAgregate(plus);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.MINUS:
      {
        Minus minus = (Minus)theEObject;
        T result = caseMinus(minus);
        if (result == null) result = caseOperands(minus);
        if (result == null) result = caseOpFunctionArgAgregate(minus);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.CONCAT:
      {
        Concat concat = (Concat)theEObject;
        T result = caseConcat(concat);
        if (result == null) result = caseOperands(concat);
        if (result == null) result = caseOpFunctionArgAgregate(concat);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.MULTIPLY:
      {
        Multiply multiply = (Multiply)theEObject;
        T result = caseMultiply(multiply);
        if (result == null) result = caseOperands(multiply);
        if (result == null) result = caseOpFunctionArgAgregate(multiply);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.DIVISION:
      {
        Division division = (Division)theEObject;
        T result = caseDivision(division);
        if (result == null) result = caseOperands(division);
        if (result == null) result = caseOpFunctionArgAgregate(division);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OBC_ARGS:
      {
        OBCArgs obcArgs = (OBCArgs)theEObject;
        T result = caseOBCArgs(obcArgs);
        if (result == null) result = caseOrderByClauseArgs(obcArgs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.AEXP_ARGS:
      {
        AExpArgs aExpArgs = (AExpArgs)theEObject;
        T result = caseAExpArgs(aExpArgs);
        if (result == null) result = caseAnalyticExprArgs(aExpArgs);
        if (result == null) result = caseQueryPartitionClause(aExpArgs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.OP_FLIST:
      {
        OpFList opFList = (OpFList)theEObject;
        T result = caseOpFList(opFList);
        if (result == null) result = caseOpFunctionArg(opFList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case SqlPackage.WHEN_LIST:
      {
        WhenList whenList = (WhenList)theEObject;
        T result = caseWhenList(whenList);
        if (result == null) result = caseSQLCaseWhens(whenList);
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
   * Returns the result of interpreting the object as an instance of '<em>With Query</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>With Query</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWithQuery(WithQuery object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>With Columns</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>With Columns</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWithColumns(WithColumns object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fetch First</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fetch First</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFetchFirst(FetchFirst object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Offset</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Offset</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOffset(Offset object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Limit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Limit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLimit(Limit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Select Query</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Select Query</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSelectQuery(SelectQuery object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Select Sub Set</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Select Sub Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSelectSubSet(SelectSubSet object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Select</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Select</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSelect(Select object)
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
   * Returns the result of interpreting the object as an instance of '<em>From Table</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>From Table</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFromTable(FromTable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>From Table Join</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>From Table Join</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFromTableJoin(FromTableJoin object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Join Condition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Join Condition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseJoinCondition(JoinCondition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Using Cols</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Using Cols</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUsingCols(UsingCols object)
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
   * Returns the result of interpreting the object as an instance of '<em>From Values</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>From Values</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFromValues(FromValues object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>From Values Columns</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>From Values Columns</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFromValuesColumns(FromValuesColumns object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>From Values Column Names</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>From Values Column Names</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFromValuesColumnNames(FromValuesColumnNames object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Column Names</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Column Names</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseColumnNames(ColumnNames object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Values</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Values</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseValues(Values object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Rows</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Rows</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRows(Rows object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Row</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Row</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRow(Row object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Row Values</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Row Values</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRowValues(RowValues object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Row Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Row Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRowValue(RowValue object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pivot Table</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pivot Table</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePivotTable(PivotTable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pivot Functions</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pivot Functions</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePivotFunctions(PivotFunctions object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pivot Function</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pivot Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePivotFunction(PivotFunction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pivot In Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pivot In Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePivotInClause(PivotInClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unpivot Table</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unpivot Table</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnpivotTable(UnpivotTable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unpivot In Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unpivot In Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnpivotInClause(UnpivotInClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unpivot In Clause Args</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unpivot In Clause Args</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnpivotInClauseArgs(UnpivotInClauseArgs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unpivot In Clause Arg</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unpivot In Clause Arg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnpivotInClauseArg(UnpivotInClauseArg object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pivot For Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pivot For Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePivotForClause(PivotForClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pivot Columns</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pivot Columns</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePivotColumns(PivotColumns object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pivots</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pivots</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePivots(Pivots object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pivot Col</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pivot Col</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePivotCol(PivotCol object)
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
   * Returns the result of interpreting the object as an instance of '<em>Db Object Name All</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Db Object Name All</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDbObjectNameAll(DbObjectNameAll object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Db Object Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Db Object Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDbObjectName(DbObjectName object)
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
   * Returns the result of interpreting the object as an instance of '<em>Or Expr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Expr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrExpr(OrExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Full Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Full Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFullExpression(FullExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expr Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expr Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExprGroup(ExprGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XExpr</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XExpr</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXExpr(XExpr object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Prms</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Prms</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrms(Prms object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>JR Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>JR Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseJRParameter(JRParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Comparison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Comparison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseComparison(Comparison object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Like</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Like</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLike(Like object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Like Operand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Like Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLikeOperand(LikeOperand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Between</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Between</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBetween(Between object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>In Oper</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>In Oper</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInOper(InOper object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Exists Oper</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exists Oper</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExistsOper(ExistsOper object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operand List Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operand List Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperandListGroup(OperandListGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operand List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operand List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperandList(OperandList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operands</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operands</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperands(Operands object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperand(Operand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Op Function</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Op Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpFunction(OpFunction object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Function Extract</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Extract</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFunctionExtract(FunctionExtract object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Function Analytical</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Analytical</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFunctionAnalytical(FunctionAnalytical object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Analytic Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Analytic Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnalyticClause(AnalyticClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Windowing Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Windowing Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWindowingClause(WindowingClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Windowing Clause Between</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Windowing Clause Between</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWindowingClauseBetween(WindowingClauseBetween object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Windowing Clause Operand Following</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Windowing Clause Operand Following</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWindowingClauseOperandFollowing(WindowingClauseOperandFollowing object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Windowing Clause Operand Preceding</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Windowing Clause Operand Preceding</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWindowingClauseOperandPreceding(WindowingClauseOperandPreceding object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Order By Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Order By Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrderByClause(OrderByClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Order By Clause Args</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Order By Clause Args</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrderByClauseArgs(OrderByClauseArgs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Order By Clause Arg</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Order By Clause Arg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrderByClauseArg(OrderByClauseArg object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Query Partition Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Query Partition Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseQueryPartitionClause(QueryPartitionClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Analytic Expr Args</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Analytic Expr Args</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnalyticExprArgs(AnalyticExprArgs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Analytic Expr Arg</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Analytic Expr Arg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnalyticExprArg(AnalyticExprArg object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Op Function Arg</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Op Function Arg</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpFunctionArg(OpFunctionArg object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Op Function Arg Operand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Op Function Arg Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpFunctionArgOperand(OpFunctionArgOperand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Op Function Cast</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Op Function Cast</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpFunctionCast(OpFunctionCast object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Op Function Arg Agregate</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Op Function Arg Agregate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpFunctionArgAgregate(OpFunctionArgAgregate object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>POperand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>POperand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePOperand(POperand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Exp Operand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Exp Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpOperand(ExpOperand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Column Operand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Column Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseColumnOperand(ColumnOperand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Sub Query Operand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Sub Query Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSubQueryOperand(SubQueryOperand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Scalar Operand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Scalar Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseScalarOperand(ScalarOperand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>SQL Case Operand</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>SQL Case Operand</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSQLCaseOperand(SQLCaseOperand object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>SQL Case Whens</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>SQL Case Whens</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSQLCaseWhens(SQLCaseWhens object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Case When</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Case When</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSqlCaseWhen(SqlCaseWhen object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Integer Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntegerValue(IntegerValue object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unsigned Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unsigned Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnsignedValue(UnsignedValue object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Col</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Col</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCol(Col object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>abc</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>abc</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseabc(abc object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unipivot In Clause</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unipivot In Clause</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnipivotInClause(UnipivotInClause object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uicargs</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uicargs</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuicargs(uicargs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>pvcs</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>pvcs</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casepvcs(pvcs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>pcols</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>pcols</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casepcols(pcols object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>tbls</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>tbls</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetbls(tbls object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Op List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Op List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpList(OpList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Plus</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Plus</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePlus(Plus object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Minus</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Minus</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMinus(Minus object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Concat</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Concat</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConcat(Concat object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Multiply</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Multiply</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMultiply(Multiply object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Division</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Division</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDivision(Division object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>OBC Args</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>OBC Args</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOBCArgs(OBCArgs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>AExp Args</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>AExp Args</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAExpArgs(AExpArgs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Op FList</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Op FList</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOpFList(OpFList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>When List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>When List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWhenList(WhenList object)
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
