/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Limit;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Limit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.LimitImpl#getL1 <em>L1</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.LimitImpl#getL2 <em>L2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LimitImpl extends MinimalEObjectImpl.Container implements Limit
{
  /**
   * The default value of the '{@link #getL1() <em>L1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getL1()
   * @generated
   * @ordered
   */
  protected static final Long L1_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getL1() <em>L1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getL1()
   * @generated
   * @ordered
   */
  protected Long l1 = L1_EDEFAULT;

  /**
   * The default value of the '{@link #getL2() <em>L2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getL2()
   * @generated
   * @ordered
   */
  protected static final Long L2_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getL2() <em>L2</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getL2()
   * @generated
   * @ordered
   */
  protected Long l2 = L2_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LimitImpl()
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
    return SqlPackage.Literals.LIMIT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Long getL1()
  {
    return l1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setL1(Long newL1)
  {
    Long oldL1 = l1;
    l1 = newL1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.LIMIT__L1, oldL1, l1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Long getL2()
  {
    return l2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setL2(Long newL2)
  {
    Long oldL2 = l2;
    l2 = newL2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.LIMIT__L2, oldL2, l2));
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
      case SqlPackage.LIMIT__L1:
        return getL1();
      case SqlPackage.LIMIT__L2:
        return getL2();
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
      case SqlPackage.LIMIT__L1:
        setL1((Long)newValue);
        return;
      case SqlPackage.LIMIT__L2:
        setL2((Long)newValue);
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
      case SqlPackage.LIMIT__L1:
        setL1(L1_EDEFAULT);
        return;
      case SqlPackage.LIMIT__L2:
        setL2(L2_EDEFAULT);
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
      case SqlPackage.LIMIT__L1:
        return L1_EDEFAULT == null ? l1 != null : !L1_EDEFAULT.equals(l1);
      case SqlPackage.LIMIT__L2:
        return L2_EDEFAULT == null ? l2 != null : !L2_EDEFAULT.equals(l2);
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
    result.append(" (l1: ");
    result.append(l1);
    result.append(", l2: ");
    result.append(l2);
    result.append(')');
    return result.toString();
  }

} //LimitImpl
