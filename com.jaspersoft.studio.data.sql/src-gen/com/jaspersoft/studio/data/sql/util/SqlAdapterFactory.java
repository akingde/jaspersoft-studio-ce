/**
 */
package com.jaspersoft.studio.data.sql.util;

import com.jaspersoft.studio.data.sql.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see com.jaspersoft.studio.data.sql.SqlPackage
 * @generated
 */
public class SqlAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static SqlPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = SqlPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SqlSwitch<Adapter> modelSwitch =
    new SqlSwitch<Adapter>()
    {
      @Override
      public Adapter caseModel(Model object)
      {
        return createModelAdapter();
      }
      @Override
      public Adapter caseWithQuery(WithQuery object)
      {
        return createWithQueryAdapter();
      }
      @Override
      public Adapter caseWithColumns(WithColumns object)
      {
        return createWithColumnsAdapter();
      }
      @Override
      public Adapter caseFetchFirst(FetchFirst object)
      {
        return createFetchFirstAdapter();
      }
      @Override
      public Adapter caseOffset(Offset object)
      {
        return createOffsetAdapter();
      }
      @Override
      public Adapter caseLimit(Limit object)
      {
        return createLimitAdapter();
      }
      @Override
      public Adapter caseSelectQuery(SelectQuery object)
      {
        return createSelectQueryAdapter();
      }
      @Override
      public Adapter caseSelectSubSet(SelectSubSet object)
      {
        return createSelectSubSetAdapter();
      }
      @Override
      public Adapter caseSelect(Select object)
      {
        return createSelectAdapter();
      }
      @Override
      public Adapter caseOrColumn(OrColumn object)
      {
        return createOrColumnAdapter();
      }
      @Override
      public Adapter caseColumnOrAlias(ColumnOrAlias object)
      {
        return createColumnOrAliasAdapter();
      }
      @Override
      public Adapter caseColumnFull(ColumnFull object)
      {
        return createColumnFullAdapter();
      }
      @Override
      public Adapter caseOrTable(OrTable object)
      {
        return createOrTableAdapter();
      }
      @Override
      public Adapter caseFromTable(FromTable object)
      {
        return createFromTableAdapter();
      }
      @Override
      public Adapter caseFromTableJoin(FromTableJoin object)
      {
        return createFromTableJoinAdapter();
      }
      @Override
      public Adapter caseJoinCondition(JoinCondition object)
      {
        return createJoinConditionAdapter();
      }
      @Override
      public Adapter caseUsingCols(UsingCols object)
      {
        return createUsingColsAdapter();
      }
      @Override
      public Adapter caseTableOrAlias(TableOrAlias object)
      {
        return createTableOrAliasAdapter();
      }
      @Override
      public Adapter caseFromValues(FromValues object)
      {
        return createFromValuesAdapter();
      }
      @Override
      public Adapter caseFromValuesColumns(FromValuesColumns object)
      {
        return createFromValuesColumnsAdapter();
      }
      @Override
      public Adapter caseFromValuesColumnNames(FromValuesColumnNames object)
      {
        return createFromValuesColumnNamesAdapter();
      }
      @Override
      public Adapter caseColumnNames(ColumnNames object)
      {
        return createColumnNamesAdapter();
      }
      @Override
      public Adapter caseValues(Values object)
      {
        return createValuesAdapter();
      }
      @Override
      public Adapter caseRows(Rows object)
      {
        return createRowsAdapter();
      }
      @Override
      public Adapter caseRow(Row object)
      {
        return createRowAdapter();
      }
      @Override
      public Adapter caseRowValues(RowValues object)
      {
        return createRowValuesAdapter();
      }
      @Override
      public Adapter caseRowValue(RowValue object)
      {
        return createRowValueAdapter();
      }
      @Override
      public Adapter casePivotTable(PivotTable object)
      {
        return createPivotTableAdapter();
      }
      @Override
      public Adapter casePivotFunctions(PivotFunctions object)
      {
        return createPivotFunctionsAdapter();
      }
      @Override
      public Adapter casePivotFunction(PivotFunction object)
      {
        return createPivotFunctionAdapter();
      }
      @Override
      public Adapter casePivotInClause(PivotInClause object)
      {
        return createPivotInClauseAdapter();
      }
      @Override
      public Adapter caseUnpivotTable(UnpivotTable object)
      {
        return createUnpivotTableAdapter();
      }
      @Override
      public Adapter caseUnpivotInClause(UnpivotInClause object)
      {
        return createUnpivotInClauseAdapter();
      }
      @Override
      public Adapter caseUnpivotInClauseArgs(UnpivotInClauseArgs object)
      {
        return createUnpivotInClauseArgsAdapter();
      }
      @Override
      public Adapter caseUnpivotInClauseArg(UnpivotInClauseArg object)
      {
        return createUnpivotInClauseArgAdapter();
      }
      @Override
      public Adapter casePivotForClause(PivotForClause object)
      {
        return createPivotForClauseAdapter();
      }
      @Override
      public Adapter casePivotColumns(PivotColumns object)
      {
        return createPivotColumnsAdapter();
      }
      @Override
      public Adapter casePivots(Pivots object)
      {
        return createPivotsAdapter();
      }
      @Override
      public Adapter casePivotCol(PivotCol object)
      {
        return createPivotColAdapter();
      }
      @Override
      public Adapter caseTableFull(TableFull object)
      {
        return createTableFullAdapter();
      }
      @Override
      public Adapter caseDbObjectNameAll(DbObjectNameAll object)
      {
        return createDbObjectNameAllAdapter();
      }
      @Override
      public Adapter caseDbObjectName(DbObjectName object)
      {
        return createDbObjectNameAdapter();
      }
      @Override
      public Adapter caseOrOrderByColumn(OrOrderByColumn object)
      {
        return createOrOrderByColumnAdapter();
      }
      @Override
      public Adapter caseOrderByColumnFull(OrderByColumnFull object)
      {
        return createOrderByColumnFullAdapter();
      }
      @Override
      public Adapter caseOrGroupByColumn(OrGroupByColumn object)
      {
        return createOrGroupByColumnAdapter();
      }
      @Override
      public Adapter caseGroupByColumnFull(GroupByColumnFull object)
      {
        return createGroupByColumnFullAdapter();
      }
      @Override
      public Adapter caseOrExpr(OrExpr object)
      {
        return createOrExprAdapter();
      }
      @Override
      public Adapter caseFullExpression(FullExpression object)
      {
        return createFullExpressionAdapter();
      }
      @Override
      public Adapter caseExprGroup(ExprGroup object)
      {
        return createExprGroupAdapter();
      }
      @Override
      public Adapter caseXExpr(XExpr object)
      {
        return createXExprAdapter();
      }
      @Override
      public Adapter casePrms(Prms object)
      {
        return createPrmsAdapter();
      }
      @Override
      public Adapter caseJRParameter(JRParameter object)
      {
        return createJRParameterAdapter();
      }
      @Override
      public Adapter caseComparison(Comparison object)
      {
        return createComparisonAdapter();
      }
      @Override
      public Adapter caseLike(Like object)
      {
        return createLikeAdapter();
      }
      @Override
      public Adapter caseLikeOperand(LikeOperand object)
      {
        return createLikeOperandAdapter();
      }
      @Override
      public Adapter caseBetween(Between object)
      {
        return createBetweenAdapter();
      }
      @Override
      public Adapter caseInOper(InOper object)
      {
        return createInOperAdapter();
      }
      @Override
      public Adapter caseExistsOper(ExistsOper object)
      {
        return createExistsOperAdapter();
      }
      @Override
      public Adapter caseOperandListGroup(OperandListGroup object)
      {
        return createOperandListGroupAdapter();
      }
      @Override
      public Adapter caseOperandList(OperandList object)
      {
        return createOperandListAdapter();
      }
      @Override
      public Adapter caseOperands(Operands object)
      {
        return createOperandsAdapter();
      }
      @Override
      public Adapter caseOperand(Operand object)
      {
        return createOperandAdapter();
      }
      @Override
      public Adapter caseOpFunction(OpFunction object)
      {
        return createOpFunctionAdapter();
      }
      @Override
      public Adapter caseFunctionExtract(FunctionExtract object)
      {
        return createFunctionExtractAdapter();
      }
      @Override
      public Adapter caseFunctionAnalytical(FunctionAnalytical object)
      {
        return createFunctionAnalyticalAdapter();
      }
      @Override
      public Adapter caseAnalyticClause(AnalyticClause object)
      {
        return createAnalyticClauseAdapter();
      }
      @Override
      public Adapter caseWindowingClause(WindowingClause object)
      {
        return createWindowingClauseAdapter();
      }
      @Override
      public Adapter caseWindowingClauseBetween(WindowingClauseBetween object)
      {
        return createWindowingClauseBetweenAdapter();
      }
      @Override
      public Adapter caseWindowingClauseOperandFollowing(WindowingClauseOperandFollowing object)
      {
        return createWindowingClauseOperandFollowingAdapter();
      }
      @Override
      public Adapter caseWindowingClauseOperandPreceding(WindowingClauseOperandPreceding object)
      {
        return createWindowingClauseOperandPrecedingAdapter();
      }
      @Override
      public Adapter caseOrderByClause(OrderByClause object)
      {
        return createOrderByClauseAdapter();
      }
      @Override
      public Adapter caseOrderByClauseArgs(OrderByClauseArgs object)
      {
        return createOrderByClauseArgsAdapter();
      }
      @Override
      public Adapter caseOrderByClauseArg(OrderByClauseArg object)
      {
        return createOrderByClauseArgAdapter();
      }
      @Override
      public Adapter caseQueryPartitionClause(QueryPartitionClause object)
      {
        return createQueryPartitionClauseAdapter();
      }
      @Override
      public Adapter caseAnalyticExprArgs(AnalyticExprArgs object)
      {
        return createAnalyticExprArgsAdapter();
      }
      @Override
      public Adapter caseAnalyticExprArg(AnalyticExprArg object)
      {
        return createAnalyticExprArgAdapter();
      }
      @Override
      public Adapter caseOpFunctionArg(OpFunctionArg object)
      {
        return createOpFunctionArgAdapter();
      }
      @Override
      public Adapter caseOpFunctionArgOperand(OpFunctionArgOperand object)
      {
        return createOpFunctionArgOperandAdapter();
      }
      @Override
      public Adapter caseOpFunctionCast(OpFunctionCast object)
      {
        return createOpFunctionCastAdapter();
      }
      @Override
      public Adapter caseOpFunctionArgAgregate(OpFunctionArgAgregate object)
      {
        return createOpFunctionArgAgregateAdapter();
      }
      @Override
      public Adapter casePOperand(POperand object)
      {
        return createPOperandAdapter();
      }
      @Override
      public Adapter caseExpOperand(ExpOperand object)
      {
        return createExpOperandAdapter();
      }
      @Override
      public Adapter caseColumnOperand(ColumnOperand object)
      {
        return createColumnOperandAdapter();
      }
      @Override
      public Adapter caseSubQueryOperand(SubQueryOperand object)
      {
        return createSubQueryOperandAdapter();
      }
      @Override
      public Adapter caseScalarOperand(ScalarOperand object)
      {
        return createScalarOperandAdapter();
      }
      @Override
      public Adapter caseSQLCaseOperand(SQLCaseOperand object)
      {
        return createSQLCaseOperandAdapter();
      }
      @Override
      public Adapter caseSQLCaseWhens(SQLCaseWhens object)
      {
        return createSQLCaseWhensAdapter();
      }
      @Override
      public Adapter caseSqlCaseWhen(SqlCaseWhen object)
      {
        return createSqlCaseWhenAdapter();
      }
      @Override
      public Adapter caseIntegerValue(IntegerValue object)
      {
        return createIntegerValueAdapter();
      }
      @Override
      public Adapter caseUnsignedValue(UnsignedValue object)
      {
        return createUnsignedValueAdapter();
      }
      @Override
      public Adapter caseCol(Col object)
      {
        return createColAdapter();
      }
      @Override
      public Adapter caseabc(abc object)
      {
        return createabcAdapter();
      }
      @Override
      public Adapter caseUnipivotInClause(UnipivotInClause object)
      {
        return createUnipivotInClauseAdapter();
      }
      @Override
      public Adapter caseuicargs(uicargs object)
      {
        return createuicargsAdapter();
      }
      @Override
      public Adapter casepvcs(pvcs object)
      {
        return createpvcsAdapter();
      }
      @Override
      public Adapter casepcols(pcols object)
      {
        return createpcolsAdapter();
      }
      @Override
      public Adapter casetbls(tbls object)
      {
        return createtblsAdapter();
      }
      @Override
      public Adapter caseOpList(OpList object)
      {
        return createOpListAdapter();
      }
      @Override
      public Adapter casePlus(Plus object)
      {
        return createPlusAdapter();
      }
      @Override
      public Adapter caseMinus(Minus object)
      {
        return createMinusAdapter();
      }
      @Override
      public Adapter caseConcat(Concat object)
      {
        return createConcatAdapter();
      }
      @Override
      public Adapter caseMultiply(Multiply object)
      {
        return createMultiplyAdapter();
      }
      @Override
      public Adapter caseDivision(Division object)
      {
        return createDivisionAdapter();
      }
      @Override
      public Adapter caseOBCArgs(OBCArgs object)
      {
        return createOBCArgsAdapter();
      }
      @Override
      public Adapter caseAExpArgs(AExpArgs object)
      {
        return createAExpArgsAdapter();
      }
      @Override
      public Adapter caseOpFList(OpFList object)
      {
        return createOpFListAdapter();
      }
      @Override
      public Adapter caseWhenList(WhenList object)
      {
        return createWhenListAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Model <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Model
   * @generated
   */
  public Adapter createModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.WithQuery <em>With Query</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.WithQuery
   * @generated
   */
  public Adapter createWithQueryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.WithColumns <em>With Columns</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.WithColumns
   * @generated
   */
  public Adapter createWithColumnsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FetchFirst <em>Fetch First</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FetchFirst
   * @generated
   */
  public Adapter createFetchFirstAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Offset <em>Offset</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Offset
   * @generated
   */
  public Adapter createOffsetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Limit <em>Limit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Limit
   * @generated
   */
  public Adapter createLimitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.SelectQuery <em>Select Query</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.SelectQuery
   * @generated
   */
  public Adapter createSelectQueryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.SelectSubSet <em>Select Sub Set</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.SelectSubSet
   * @generated
   */
  public Adapter createSelectSubSetAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Select <em>Select</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Select
   * @generated
   */
  public Adapter createSelectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrColumn <em>Or Column</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrColumn
   * @generated
   */
  public Adapter createOrColumnAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ColumnOrAlias <em>Column Or Alias</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ColumnOrAlias
   * @generated
   */
  public Adapter createColumnOrAliasAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ColumnFull <em>Column Full</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ColumnFull
   * @generated
   */
  public Adapter createColumnFullAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrTable <em>Or Table</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrTable
   * @generated
   */
  public Adapter createOrTableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FromTable <em>From Table</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FromTable
   * @generated
   */
  public Adapter createFromTableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FromTableJoin <em>From Table Join</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FromTableJoin
   * @generated
   */
  public Adapter createFromTableJoinAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.JoinCondition <em>Join Condition</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.JoinCondition
   * @generated
   */
  public Adapter createJoinConditionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.UsingCols <em>Using Cols</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.UsingCols
   * @generated
   */
  public Adapter createUsingColsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.TableOrAlias <em>Table Or Alias</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.TableOrAlias
   * @generated
   */
  public Adapter createTableOrAliasAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FromValues <em>From Values</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FromValues
   * @generated
   */
  public Adapter createFromValuesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FromValuesColumns <em>From Values Columns</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FromValuesColumns
   * @generated
   */
  public Adapter createFromValuesColumnsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FromValuesColumnNames <em>From Values Column Names</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FromValuesColumnNames
   * @generated
   */
  public Adapter createFromValuesColumnNamesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ColumnNames <em>Column Names</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ColumnNames
   * @generated
   */
  public Adapter createColumnNamesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Values <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Values
   * @generated
   */
  public Adapter createValuesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Rows <em>Rows</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Rows
   * @generated
   */
  public Adapter createRowsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Row <em>Row</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Row
   * @generated
   */
  public Adapter createRowAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.RowValues <em>Row Values</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.RowValues
   * @generated
   */
  public Adapter createRowValuesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.RowValue <em>Row Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.RowValue
   * @generated
   */
  public Adapter createRowValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.PivotTable <em>Pivot Table</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.PivotTable
   * @generated
   */
  public Adapter createPivotTableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.PivotFunctions <em>Pivot Functions</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.PivotFunctions
   * @generated
   */
  public Adapter createPivotFunctionsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.PivotFunction <em>Pivot Function</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.PivotFunction
   * @generated
   */
  public Adapter createPivotFunctionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.PivotInClause <em>Pivot In Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.PivotInClause
   * @generated
   */
  public Adapter createPivotInClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.UnpivotTable <em>Unpivot Table</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.UnpivotTable
   * @generated
   */
  public Adapter createUnpivotTableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.UnpivotInClause <em>Unpivot In Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.UnpivotInClause
   * @generated
   */
  public Adapter createUnpivotInClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.UnpivotInClauseArgs <em>Unpivot In Clause Args</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.UnpivotInClauseArgs
   * @generated
   */
  public Adapter createUnpivotInClauseArgsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.UnpivotInClauseArg <em>Unpivot In Clause Arg</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.UnpivotInClauseArg
   * @generated
   */
  public Adapter createUnpivotInClauseArgAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.PivotForClause <em>Pivot For Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.PivotForClause
   * @generated
   */
  public Adapter createPivotForClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.PivotColumns <em>Pivot Columns</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.PivotColumns
   * @generated
   */
  public Adapter createPivotColumnsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Pivots <em>Pivots</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Pivots
   * @generated
   */
  public Adapter createPivotsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.PivotCol <em>Pivot Col</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.PivotCol
   * @generated
   */
  public Adapter createPivotColAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.TableFull <em>Table Full</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.TableFull
   * @generated
   */
  public Adapter createTableFullAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.DbObjectNameAll <em>Db Object Name All</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.DbObjectNameAll
   * @generated
   */
  public Adapter createDbObjectNameAllAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.DbObjectName <em>Db Object Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.DbObjectName
   * @generated
   */
  public Adapter createDbObjectNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrOrderByColumn <em>Or Order By Column</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrOrderByColumn
   * @generated
   */
  public Adapter createOrOrderByColumnAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrderByColumnFull <em>Order By Column Full</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrderByColumnFull
   * @generated
   */
  public Adapter createOrderByColumnFullAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrGroupByColumn <em>Or Group By Column</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrGroupByColumn
   * @generated
   */
  public Adapter createOrGroupByColumnAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.GroupByColumnFull <em>Group By Column Full</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.GroupByColumnFull
   * @generated
   */
  public Adapter createGroupByColumnFullAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrExpr <em>Or Expr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrExpr
   * @generated
   */
  public Adapter createOrExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FullExpression <em>Full Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FullExpression
   * @generated
   */
  public Adapter createFullExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ExprGroup <em>Expr Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ExprGroup
   * @generated
   */
  public Adapter createExprGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.XExpr <em>XExpr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.XExpr
   * @generated
   */
  public Adapter createXExprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Prms <em>Prms</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Prms
   * @generated
   */
  public Adapter createPrmsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.JRParameter <em>JR Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.JRParameter
   * @generated
   */
  public Adapter createJRParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Comparison <em>Comparison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Comparison
   * @generated
   */
  public Adapter createComparisonAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Like <em>Like</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Like
   * @generated
   */
  public Adapter createLikeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.LikeOperand <em>Like Operand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.LikeOperand
   * @generated
   */
  public Adapter createLikeOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Between <em>Between</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Between
   * @generated
   */
  public Adapter createBetweenAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.InOper <em>In Oper</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.InOper
   * @generated
   */
  public Adapter createInOperAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ExistsOper <em>Exists Oper</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ExistsOper
   * @generated
   */
  public Adapter createExistsOperAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OperandListGroup <em>Operand List Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OperandListGroup
   * @generated
   */
  public Adapter createOperandListGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OperandList <em>Operand List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OperandList
   * @generated
   */
  public Adapter createOperandListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Operands <em>Operands</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Operands
   * @generated
   */
  public Adapter createOperandsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Operand <em>Operand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Operand
   * @generated
   */
  public Adapter createOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OpFunction <em>Op Function</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OpFunction
   * @generated
   */
  public Adapter createOpFunctionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FunctionExtract <em>Function Extract</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FunctionExtract
   * @generated
   */
  public Adapter createFunctionExtractAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.FunctionAnalytical <em>Function Analytical</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.FunctionAnalytical
   * @generated
   */
  public Adapter createFunctionAnalyticalAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.AnalyticClause <em>Analytic Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.AnalyticClause
   * @generated
   */
  public Adapter createAnalyticClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.WindowingClause <em>Windowing Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.WindowingClause
   * @generated
   */
  public Adapter createWindowingClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.WindowingClauseBetween <em>Windowing Clause Between</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.WindowingClauseBetween
   * @generated
   */
  public Adapter createWindowingClauseBetweenAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.WindowingClauseOperandFollowing <em>Windowing Clause Operand Following</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.WindowingClauseOperandFollowing
   * @generated
   */
  public Adapter createWindowingClauseOperandFollowingAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.WindowingClauseOperandPreceding <em>Windowing Clause Operand Preceding</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.WindowingClauseOperandPreceding
   * @generated
   */
  public Adapter createWindowingClauseOperandPrecedingAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrderByClause <em>Order By Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrderByClause
   * @generated
   */
  public Adapter createOrderByClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrderByClauseArgs <em>Order By Clause Args</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrderByClauseArgs
   * @generated
   */
  public Adapter createOrderByClauseArgsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrderByClauseArg <em>Order By Clause Arg</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrderByClauseArg
   * @generated
   */
  public Adapter createOrderByClauseArgAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.QueryPartitionClause <em>Query Partition Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.QueryPartitionClause
   * @generated
   */
  public Adapter createQueryPartitionClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.AnalyticExprArgs <em>Analytic Expr Args</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.AnalyticExprArgs
   * @generated
   */
  public Adapter createAnalyticExprArgsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.AnalyticExprArg <em>Analytic Expr Arg</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.AnalyticExprArg
   * @generated
   */
  public Adapter createAnalyticExprArgAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OpFunctionArg <em>Op Function Arg</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OpFunctionArg
   * @generated
   */
  public Adapter createOpFunctionArgAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OpFunctionArgOperand <em>Op Function Arg Operand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OpFunctionArgOperand
   * @generated
   */
  public Adapter createOpFunctionArgOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OpFunctionCast <em>Op Function Cast</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OpFunctionCast
   * @generated
   */
  public Adapter createOpFunctionCastAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OpFunctionArgAgregate <em>Op Function Arg Agregate</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OpFunctionArgAgregate
   * @generated
   */
  public Adapter createOpFunctionArgAgregateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.POperand <em>POperand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.POperand
   * @generated
   */
  public Adapter createPOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ExpOperand <em>Exp Operand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ExpOperand
   * @generated
   */
  public Adapter createExpOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ColumnOperand <em>Column Operand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ColumnOperand
   * @generated
   */
  public Adapter createColumnOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.SubQueryOperand <em>Sub Query Operand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.SubQueryOperand
   * @generated
   */
  public Adapter createSubQueryOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ScalarOperand <em>Scalar Operand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ScalarOperand
   * @generated
   */
  public Adapter createScalarOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.SQLCaseOperand <em>SQL Case Operand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.SQLCaseOperand
   * @generated
   */
  public Adapter createSQLCaseOperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.SQLCaseWhens <em>SQL Case Whens</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.SQLCaseWhens
   * @generated
   */
  public Adapter createSQLCaseWhensAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.SqlCaseWhen <em>Case When</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.SqlCaseWhen
   * @generated
   */
  public Adapter createSqlCaseWhenAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.IntegerValue <em>Integer Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.IntegerValue
   * @generated
   */
  public Adapter createIntegerValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.UnsignedValue <em>Unsigned Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.UnsignedValue
   * @generated
   */
  public Adapter createUnsignedValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Col <em>Col</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Col
   * @generated
   */
  public Adapter createColAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.abc <em>abc</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.abc
   * @generated
   */
  public Adapter createabcAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.UnipivotInClause <em>Unipivot In Clause</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.UnipivotInClause
   * @generated
   */
  public Adapter createUnipivotInClauseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.uicargs <em>uicargs</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.uicargs
   * @generated
   */
  public Adapter createuicargsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.pvcs <em>pvcs</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.pvcs
   * @generated
   */
  public Adapter createpvcsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.pcols <em>pcols</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.pcols
   * @generated
   */
  public Adapter createpcolsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.tbls <em>tbls</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.tbls
   * @generated
   */
  public Adapter createtblsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OpList <em>Op List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OpList
   * @generated
   */
  public Adapter createOpListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Plus <em>Plus</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Plus
   * @generated
   */
  public Adapter createPlusAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Minus <em>Minus</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Minus
   * @generated
   */
  public Adapter createMinusAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Concat <em>Concat</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Concat
   * @generated
   */
  public Adapter createConcatAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Multiply <em>Multiply</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Multiply
   * @generated
   */
  public Adapter createMultiplyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Division <em>Division</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Division
   * @generated
   */
  public Adapter createDivisionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OBCArgs <em>OBC Args</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OBCArgs
   * @generated
   */
  public Adapter createOBCArgsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.AExpArgs <em>AExp Args</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.AExpArgs
   * @generated
   */
  public Adapter createAExpArgsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OpFList <em>Op FList</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OpFList
   * @generated
   */
  public Adapter createOpFListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.WhenList <em>When List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.WhenList
   * @generated
   */
  public Adapter createWhenListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //SqlAdapterFactory
