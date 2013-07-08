/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ColumnOrAlias;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Or Alias</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ColumnOrAliasImpl#getAllCols <em>All Cols</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnOrAliasImpl extends ColumnsImpl implements ColumnOrAlias
{
  /**
   * The default value of the '{@link #getAllCols() <em>All Cols</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAllCols()
   * @generated
   * @ordered
   */
  protected static final String ALL_COLS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAllCols() <em>All Cols</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAllCols()
   * @generated
   * @ordered
   */
  protected String allCols = ALL_COLS_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ColumnOrAliasImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SqlPackage.Literals.COLUMN_OR_ALIAS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAllCols()
  {
    return allCols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAllCols(String newAllCols)
  {
    String oldAllCols = allCols;
    allCols = newAllCols;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.COLUMN_OR_ALIAS__ALL_COLS, oldAllCols, allCols));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SqlPackage.COLUMN_OR_ALIAS__ALL_COLS:
        return getAllCols();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SqlPackage.COLUMN_OR_ALIAS__ALL_COLS:
        setAllCols((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SqlPackage.COLUMN_OR_ALIAS__ALL_COLS:
        setAllCols(ALL_COLS_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SqlPackage.COLUMN_OR_ALIAS__ALL_COLS:
        return ALL_COLS_EDEFAULT == null ? allCols != null : !ALL_COLS_EDEFAULT.equals(allCols);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (allCols: ");
    result.append(allCols);
    result.append(')');
    return result.toString();
  }

} //ColumnOrAliasImpl
