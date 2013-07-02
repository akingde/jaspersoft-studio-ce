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
      public Adapter caseGroupByColumnFull(GroupByColumnFull object)
      {
        return createGroupByColumnFullAdapter();
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
      public Adapter caseColumnAlias(ColumnAlias object)
      {
        return createColumnAliasAdapter();
      }
      @Override
      public Adapter caseColumn(Column object)
      {
        return createColumnAdapter();
      }
      @Override
      public Adapter caseTables(Tables object)
      {
        return createTablesAdapter();
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
      public Adapter caseTable(Table object)
      {
        return createTableAdapter();
      }
      @Override
      public Adapter caseTableAlias(TableAlias object)
      {
        return createTableAliasAdapter();
      }
      @Override
      public Adapter caseSchema(Schema object)
      {
        return createSchemaAdapter();
      }
      @Override
      public Adapter caseDatabase(Database object)
      {
        return createDatabaseAdapter();
      }
      @Override
      public Adapter caseWhereEntry(WhereEntry object)
      {
        return createWhereEntryAdapter();
      }
      @Override
      public Adapter caseHavingEntry(HavingEntry object)
      {
        return createHavingEntryAdapter();
      }
      @Override
      public Adapter caseExpressionWhereEntry(ExpressionWhereEntry object)
      {
        return createExpressionWhereEntryAdapter();
      }
      @Override
      public Adapter caseSingleExpressionWhereEntry(SingleExpressionWhereEntry object)
      {
        return createSingleExpressionWhereEntryAdapter();
      }
      @Override
      public Adapter caseExpression(Expression object)
      {
        return createExpressionAdapter();
      }
      @Override
      public Adapter caseReplacableValue(ReplacableValue object)
      {
        return createReplacableValueAdapter();
      }
      @Override
      public Adapter caseDoubleExpression(DoubleExpression object)
      {
        return createDoubleExpressionAdapter();
      }
      @Override
      public Adapter caseLongExpression(LongExpression object)
      {
        return createLongExpressionAdapter();
      }
      @Override
      public Adapter caseStringExpression(StringExpression object)
      {
        return createStringExpressionAdapter();
      }
      @Override
      public Adapter caseNullExpression(NullExpression object)
      {
        return createNullExpressionAdapter();
      }
      @Override
      public Adapter caseDateExpression(DateExpression object)
      {
        return createDateExpressionAdapter();
      }
      @Override
      public Adapter caseBooleanExpression(BooleanExpression object)
      {
        return createBooleanExpressionAdapter();
      }
      @Override
      public Adapter caseMultiExpressionWhereEntry(MultiExpressionWhereEntry object)
      {
        return createMultiExpressionWhereEntryAdapter();
      }
      @Override
      public Adapter caseArrayExpression(ArrayExpression object)
      {
        return createArrayExpressionAdapter();
      }
      @Override
      public Adapter caseDoubleArrayExpression(DoubleArrayExpression object)
      {
        return createDoubleArrayExpressionAdapter();
      }
      @Override
      public Adapter caseLongArrayExpression(LongArrayExpression object)
      {
        return createLongArrayExpressionAdapter();
      }
      @Override
      public Adapter caseStringArrayExpression(StringArrayExpression object)
      {
        return createStringArrayExpressionAdapter();
      }
      @Override
      public Adapter caseNullArrayExpression(NullArrayExpression object)
      {
        return createNullArrayExpressionAdapter();
      }
      @Override
      public Adapter caseDateArrayExpression(DateArrayExpression object)
      {
        return createDateArrayExpressionAdapter();
      }
      @Override
      public Adapter caseBooleanArrayExpression(BooleanArrayExpression object)
      {
        return createBooleanArrayExpressionAdapter();
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
      public Adapter caseOrColumn(OrColumn object)
      {
        return createOrColumnAdapter();
      }
      @Override
      public Adapter caseOrTable(OrTable object)
      {
        return createOrTableAdapter();
      }
      @Override
      public Adapter caseOrWhereEntry(OrWhereEntry object)
      {
        return createOrWhereEntryAdapter();
      }
      @Override
      public Adapter caseAndWhereEntry(AndWhereEntry object)
      {
        return createAndWhereEntryAdapter();
      }
      @Override
      public Adapter caseOrHavingEntry(OrHavingEntry object)
      {
        return createOrHavingEntryAdapter();
      }
      @Override
      public Adapter caseAndHavingEntry(AndHavingEntry object)
      {
        return createAndHavingEntryAdapter();
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ColumnAlias <em>Column Alias</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ColumnAlias
   * @generated
   */
  public Adapter createColumnAliasAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Column <em>Column</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Column
   * @generated
   */
  public Adapter createColumnAdapter()
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Table <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Table
   * @generated
   */
  public Adapter createTableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.TableAlias <em>Table Alias</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.TableAlias
   * @generated
   */
  public Adapter createTableAliasAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Schema <em>Schema</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Schema
   * @generated
   */
  public Adapter createSchemaAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Database <em>Database</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Database
   * @generated
   */
  public Adapter createDatabaseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.WhereEntry <em>Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.WhereEntry
   * @generated
   */
  public Adapter createWhereEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.HavingEntry <em>Having Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.HavingEntry
   * @generated
   */
  public Adapter createHavingEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ExpressionWhereEntry <em>Expression Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ExpressionWhereEntry
   * @generated
   */
  public Adapter createExpressionWhereEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry <em>Single Expression Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.SingleExpressionWhereEntry
   * @generated
   */
  public Adapter createSingleExpressionWhereEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.Expression
   * @generated
   */
  public Adapter createExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ReplacableValue <em>Replacable Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ReplacableValue
   * @generated
   */
  public Adapter createReplacableValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.DoubleExpression <em>Double Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.DoubleExpression
   * @generated
   */
  public Adapter createDoubleExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.LongExpression <em>Long Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.LongExpression
   * @generated
   */
  public Adapter createLongExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.StringExpression <em>String Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.StringExpression
   * @generated
   */
  public Adapter createStringExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.NullExpression <em>Null Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.NullExpression
   * @generated
   */
  public Adapter createNullExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.DateExpression <em>Date Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.DateExpression
   * @generated
   */
  public Adapter createDateExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.BooleanExpression <em>Boolean Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.BooleanExpression
   * @generated
   */
  public Adapter createBooleanExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry <em>Multi Expression Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.MultiExpressionWhereEntry
   * @generated
   */
  public Adapter createMultiExpressionWhereEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.ArrayExpression <em>Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.ArrayExpression
   * @generated
   */
  public Adapter createArrayExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.DoubleArrayExpression <em>Double Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.DoubleArrayExpression
   * @generated
   */
  public Adapter createDoubleArrayExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.LongArrayExpression <em>Long Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.LongArrayExpression
   * @generated
   */
  public Adapter createLongArrayExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.StringArrayExpression <em>String Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.StringArrayExpression
   * @generated
   */
  public Adapter createStringArrayExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.NullArrayExpression <em>Null Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.NullArrayExpression
   * @generated
   */
  public Adapter createNullArrayExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.DateArrayExpression <em>Date Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.DateArrayExpression
   * @generated
   */
  public Adapter createDateArrayExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.BooleanArrayExpression <em>Boolean Array Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.BooleanArrayExpression
   * @generated
   */
  public Adapter createBooleanArrayExpressionAdapter()
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
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrWhereEntry <em>Or Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrWhereEntry
   * @generated
   */
  public Adapter createOrWhereEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.AndWhereEntry <em>And Where Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.AndWhereEntry
   * @generated
   */
  public Adapter createAndWhereEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.OrHavingEntry <em>Or Having Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.OrHavingEntry
   * @generated
   */
  public Adapter createOrHavingEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link com.jaspersoft.studio.data.sql.AndHavingEntry <em>And Having Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see com.jaspersoft.studio.data.sql.AndHavingEntry
   * @generated
   */
  public Adapter createAndHavingEntryAdapter()
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
