/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.PivotFunctions;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pivot Functions</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.PivotFunctionsImpl#getAbc <em>Abc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PivotFunctionsImpl extends MinimalEObjectImpl.Container implements PivotFunctions
{
  /**
   * The default value of the '{@link #getAbc() <em>Abc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAbc()
   * @generated
   * @ordered
   */
  protected static final String ABC_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAbc() <em>Abc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAbc()
   * @generated
   * @ordered
   */
  protected String abc = ABC_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PivotFunctionsImpl()
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
    return SqlPackage.Literals.PIVOT_FUNCTIONS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAbc()
  {
    return abc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAbc(String newAbc)
  {
    String oldAbc = abc;
    abc = newAbc;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_FUNCTIONS__ABC, oldAbc, abc));
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
      case SqlPackage.PIVOT_FUNCTIONS__ABC:
        return getAbc();
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
      case SqlPackage.PIVOT_FUNCTIONS__ABC:
        setAbc((String)newValue);
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
      case SqlPackage.PIVOT_FUNCTIONS__ABC:
        setAbc(ABC_EDEFAULT);
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
      case SqlPackage.PIVOT_FUNCTIONS__ABC:
        return ABC_EDEFAULT == null ? abc != null : !ABC_EDEFAULT.equals(abc);
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
    result.append(" (abc: ");
    result.append(abc);
    result.append(')');
    return result.toString();
  }

} //PivotFunctionsImpl
