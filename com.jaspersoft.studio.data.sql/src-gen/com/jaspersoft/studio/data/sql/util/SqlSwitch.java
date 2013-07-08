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
      case SqlPackage.SELECT:
      {
        Select select = (Select)theEObject;
        T result = caseSelect(select);
        if (result == null) result = caseModel(select);
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
      case SqlPackage.DB_OBJECT_NAME:
      {
        DbObjectName dbObjectName = (DbObjectName)theEObject;
        T result = caseDbObjectName(dbObjectName);
        if (result == null) result = caseColumnFull(dbObjectName);
        if (result == null) result = caseColumnOrAlias(dbObjectName);
        if (result == null) result = caseColumns(dbObjectName);
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
      case SqlPackage.OR_SELECT:
      {
        OrSelect orSelect = (OrSelect)theEObject;
        T result = caseOrSelect(orSelect);
        if (result == null) result = caseModel(orSelect);
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
      case SqlPackage.COL:
      {
        Col col = (Col)theEObject;
        T result = caseCol(col);
        if (result == null) result = caseColumnFull(col);
        if (result == null) result = caseColumnOrAlias(col);
        if (result == null) result = caseColumns(col);
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
   * Returns the result of interpreting the object as an instance of '<em>Or Select</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Select</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrSelect(OrSelect object)
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
