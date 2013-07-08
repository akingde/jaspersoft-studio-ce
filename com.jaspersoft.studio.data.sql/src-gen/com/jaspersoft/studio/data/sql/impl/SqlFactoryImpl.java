/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SqlFactoryImpl extends EFactoryImpl implements SqlFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SqlFactory init()
  {
    try
    {
      SqlFactory theSqlFactory = (SqlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.com.jaspersoft.studio.data.Sql"); 
      if (theSqlFactory != null)
      {
        return theSqlFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SqlFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case SqlPackage.MODEL: return createModel();
      case SqlPackage.SELECT: return createSelect();
      case SqlPackage.COLUMNS: return createColumns();
      case SqlPackage.COLUMN_OR_ALIAS: return createColumnOrAlias();
      case SqlPackage.COLUMN_FULL: return createColumnFull();
      case SqlPackage.DB_OBJECT_NAME: return createDbObjectName();
      case SqlPackage.ORDER_BY_COLUMNS: return createOrderByColumns();
      case SqlPackage.ORDER_BY_COLUMN_FULL: return createOrderByColumnFull();
      case SqlPackage.OR_SELECT: return createOrSelect();
      case SqlPackage.OR_COLUMN: return createOrColumn();
      case SqlPackage.COL: return createCol();
      case SqlPackage.OR_ORDER_BY_COLUMN: return createOrOrderByColumn();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Model createModel()
  {
    ModelImpl model = new ModelImpl();
    return model;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Select createSelect()
  {
    SelectImpl select = new SelectImpl();
    return select;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Columns createColumns()
  {
    ColumnsImpl columns = new ColumnsImpl();
    return columns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnOrAlias createColumnOrAlias()
  {
    ColumnOrAliasImpl columnOrAlias = new ColumnOrAliasImpl();
    return columnOrAlias;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ColumnFull createColumnFull()
  {
    ColumnFullImpl columnFull = new ColumnFullImpl();
    return columnFull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbObjectName createDbObjectName()
  {
    DbObjectNameImpl dbObjectName = new DbObjectNameImpl();
    return dbObjectName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByColumns createOrderByColumns()
  {
    OrderByColumnsImpl orderByColumns = new OrderByColumnsImpl();
    return orderByColumns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrderByColumnFull createOrderByColumnFull()
  {
    OrderByColumnFullImpl orderByColumnFull = new OrderByColumnFullImpl();
    return orderByColumnFull;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrSelect createOrSelect()
  {
    OrSelectImpl orSelect = new OrSelectImpl();
    return orSelect;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrColumn createOrColumn()
  {
    OrColumnImpl orColumn = new OrColumnImpl();
    return orColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Col createCol()
  {
    ColImpl col = new ColImpl();
    return col;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrOrderByColumn createOrOrderByColumn()
  {
    OrOrderByColumnImpl orOrderByColumn = new OrOrderByColumnImpl();
    return orOrderByColumn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SqlPackage getSqlPackage()
  {
    return (SqlPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SqlPackage getPackage()
  {
    return SqlPackage.eINSTANCE;
  }

} //SqlFactoryImpl
