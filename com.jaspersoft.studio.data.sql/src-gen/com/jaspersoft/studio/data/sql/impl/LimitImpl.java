/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.IntegerValue;
import com.jaspersoft.studio.data.sql.Limit;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Limit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.LimitImpl#getL1 <em>L1</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.LimitImpl#getL2 <em>L2</em>}</li>
 * </ul>
 * </p>
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
  protected static final int L1_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getL1() <em>L1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getL1()
   * @generated
   * @ordered
   */
  protected int l1 = L1_EDEFAULT;

  /**
   * The cached value of the '{@link #getL2() <em>L2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getL2()
   * @generated
   * @ordered
   */
  protected IntegerValue l2;

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
  public int getL1()
  {
    return l1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setL1(int newL1)
  {
    int oldL1 = l1;
    l1 = newL1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.LIMIT__L1, oldL1, l1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerValue getL2()
  {
    return l2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetL2(IntegerValue newL2, NotificationChain msgs)
  {
    IntegerValue oldL2 = l2;
    l2 = newL2;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.LIMIT__L2, oldL2, newL2);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setL2(IntegerValue newL2)
  {
    if (newL2 != l2)
    {
      NotificationChain msgs = null;
      if (l2 != null)
        msgs = ((InternalEObject)l2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.LIMIT__L2, null, msgs);
      if (newL2 != null)
        msgs = ((InternalEObject)newL2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.LIMIT__L2, null, msgs);
      msgs = basicSetL2(newL2, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.LIMIT__L2, newL2, newL2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SqlPackage.LIMIT__L2:
        return basicSetL2(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
        setL1((Integer)newValue);
        return;
      case SqlPackage.LIMIT__L2:
        setL2((IntegerValue)newValue);
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
        setL2((IntegerValue)null);
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
        return l1 != L1_EDEFAULT;
      case SqlPackage.LIMIT__L2:
        return l2 != null;
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
    result.append(')');
    return result.toString();
  }

} //LimitImpl
