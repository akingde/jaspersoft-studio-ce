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
      public Adapter caseSelect(Select object)
      {
        return createSelectAdapter();
      }
      @Override
      public Adapter caseColumns(Columns object)
      {
        return createColumnsAdapter();
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
      public Adapter caseTables(Tables object)
      {
        return createTablesAdapter();
      }
      @Override
      public Adapter caseFromTable(FromTable object)
      {
        return createFromTableAdapter();
      }
      @Override
      public Adapter caseTableOrAlias(TableOrAlias object)
      {
        return createTableOrAliasAdapter();
      }
      @Override
      public Adapter caseTableFull(TableFull object)
      {
        return createTableFullAdapter();
      }
      @Override
      public Adapter caseDbObjectName(DbObjectName object)
      {
        return createDbObjectNameAdapter();
      }
      @Override
      public Adapter caseOrderByColumns(OrderByColumns object)
      {
        return createOrderByColumnsAdapter();
      }
      @Override
      public Adapter caseOrderByColumnFull(OrderByColumnFull object)
      {
        return createOrderByColumnFullAdapter();
      }
      @Override
      public Adapter caseGroupByColumns(GroupByColumns object)
      {
        return createGroupByColumnsAdapter();
      }
      @Override
      public Adapter caseFullExpression(FullExpression object)
      {
        return createFullExpressionAdapter();
      }
      @Override
      public Adapter caseComparison(Comparison object)
      {
        return createComparisonAdapter();
      }
      @Override
      public Adapter caseBetween(Between object)
      {
        return createBetweenAdapter();
      }
      @Override
      public Adapter caseInOperator(InOperator object)
      {
        return createInOperatorAdapter();
      }
      @Override
      public Adapter caseOperand(Operand object)
      {
        return createOperandAdapter();
      }
      @Override
      public Adapter caseOrSelect(OrSelect object)
      {
        return createOrSelectAdapter();
      }
      @Override
      public Adapter caseOrColumn(OrColumn object)
      {
        return createOrColumnAdapter();
      }
      @Override
      public Adapter caseCol(Col object)
      {
        return createColAdapter();
      }
      @Override
      public Adapter caseOrTable(OrTable object)
      {
        return createOrTableAdapter();
      }
      @Override
      public Adapter casetbls(tbls object)
      {
        return createtblsAdapter();
      }
      @Override
      public Adapter caseOrOrderByColumn(OrOrderByColumn object)
      {
        return createOrOrderByColumnAdapter();
      }
      @Override
      public Adapter caseOrGroupByColumn(OrGroupByColumn object)
      {
        return createOrGroupByColumnAdapter();
      }
      @Override
      public Adapter casefexpr(fexpr object)
      {
        return createfexprAdapter();
      }
      @Override
      public Adapter caseexprGroup(exprGroup object)
      {
        return createexprGroupAdapter();
      }
      @Override
      public Adapter casexexpr(xexpr object)
      {
        return createxexprAdapter();
      }
      @Override
      public Adapter caseinop(inop object)
      {
        return createinopAdapter();
      }
      @Override
      public Adapter casexop(xop object)
      {
        return createxopAdapter();
      }
      @Override
      public Adapter caseoperands(operands object)
      {
        return createoperandsAdapter();
      }
      @Override
      public Adapter caseopGroup(opGroup object)
      {
        return createopGroupAdapter();
      }
      @Override
      public Adapter casepoperand(poperand object)
      {
        return createpoperandAdapter();
      }
      @Override
      public Adapter caseexpoperand(expoperand object)
      {
        return createexpoperandAdapter();
      }
      @Override
      public Adapter casesubquery(subquery object)
      {
        return createsubqueryAdapter();
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Columns <em>Columns</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Columns
   * @generated
   */
  public Adapter createColumnsAdapter()
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Tables <em>Tables</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Tables
   * @generated
   */
  public Adapter createTablesAdapter()
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrderByColumns <em>Order By Columns</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrderByColumns
   * @generated
   */
  public Adapter createOrderByColumnsAdapter()
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.GroupByColumns <em>Group By Columns</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.GroupByColumns
   * @generated
   */
  public Adapter createGroupByColumnsAdapter()
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.InOperator <em>In Operator</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.InOperator
   * @generated
   */
  public Adapter createInOperatorAdapter()
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrSelect <em>Or Select</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrSelect
   * @generated
   */
  public Adapter createOrSelectAdapter()
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.fexpr <em>fexpr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.fexpr
   * @generated
   */
  public Adapter createfexprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.exprGroup <em>expr Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.exprGroup
   * @generated
   */
  public Adapter createexprGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.xexpr <em>xexpr</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.xexpr
   * @generated
   */
  public Adapter createxexprAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.inop <em>inop</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.inop
   * @generated
   */
  public Adapter createinopAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.xop <em>xop</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.xop
   * @generated
   */
  public Adapter createxopAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.operands <em>operands</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.operands
   * @generated
   */
  public Adapter createoperandsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.opGroup <em>op Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.opGroup
   * @generated
   */
  public Adapter createopGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.poperand <em>poperand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.poperand
   * @generated
   */
  public Adapter createpoperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.expoperand <em>expoperand</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.expoperand
   * @generated
   */
  public Adapter createexpoperandAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.subquery <em>subquery</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.subquery
   * @generated
   */
  public Adapter createsubqueryAdapter()
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
