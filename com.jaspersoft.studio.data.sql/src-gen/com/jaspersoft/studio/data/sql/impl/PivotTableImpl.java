/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.PivotForClause;
import com.jaspersoft.studio.data.sql.PivotFunctions;
import com.jaspersoft.studio.data.sql.PivotInClause;
import com.jaspersoft.studio.data.sql.PivotTable;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pivot Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.PivotTableImpl#getPfun <em>Pfun</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.PivotTableImpl#getPfor <em>Pfor</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.PivotTableImpl#getPin <em>Pin</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PivotTableImpl extends MinimalEObjectImpl.Container implements PivotTable
{
  /**
   * The cached value of the '{@link #getPfun() <em>Pfun</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPfun()
   * @generated
   * @ordered
   */
  protected PivotFunctions pfun;

  /**
   * The cached value of the '{@link #getPfor() <em>Pfor</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPfor()
   * @generated
   * @ordered
   */
  protected PivotForClause pfor;

  /**
   * The cached value of the '{@link #getPin() <em>Pin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPin()
   * @generated
   * @ordered
   */
  protected PivotInClause pin;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PivotTableImpl()
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
    return SqlPackage.Literals.PIVOT_TABLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotFunctions getPfun()
  {
    return pfun;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPfun(PivotFunctions newPfun, NotificationChain msgs)
  {
    PivotFunctions oldPfun = pfun;
    pfun = newPfun;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_TABLE__PFUN, oldPfun, newPfun);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPfun(PivotFunctions newPfun)
  {
    if (newPfun != pfun)
    {
      NotificationChain msgs = null;
      if (pfun != null)
        msgs = ((InternalEObject)pfun).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_TABLE__PFUN, null, msgs);
      if (newPfun != null)
        msgs = ((InternalEObject)newPfun).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_TABLE__PFUN, null, msgs);
      msgs = basicSetPfun(newPfun, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_TABLE__PFUN, newPfun, newPfun));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotForClause getPfor()
  {
    return pfor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPfor(PivotForClause newPfor, NotificationChain msgs)
  {
    PivotForClause oldPfor = pfor;
    pfor = newPfor;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_TABLE__PFOR, oldPfor, newPfor);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPfor(PivotForClause newPfor)
  {
    if (newPfor != pfor)
    {
      NotificationChain msgs = null;
      if (pfor != null)
        msgs = ((InternalEObject)pfor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_TABLE__PFOR, null, msgs);
      if (newPfor != null)
        msgs = ((InternalEObject)newPfor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_TABLE__PFOR, null, msgs);
      msgs = basicSetPfor(newPfor, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_TABLE__PFOR, newPfor, newPfor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PivotInClause getPin()
  {
    return pin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPin(PivotInClause newPin, NotificationChain msgs)
  {
    PivotInClause oldPin = pin;
    pin = newPin;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_TABLE__PIN, oldPin, newPin);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPin(PivotInClause newPin)
  {
    if (newPin != pin)
    {
      NotificationChain msgs = null;
      if (pin != null)
        msgs = ((InternalEObject)pin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_TABLE__PIN, null, msgs);
      if (newPin != null)
        msgs = ((InternalEObject)newPin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.PIVOT_TABLE__PIN, null, msgs);
      msgs = basicSetPin(newPin, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.PIVOT_TABLE__PIN, newPin, newPin));
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
      case SqlPackage.PIVOT_TABLE__PFUN:
        return basicSetPfun(null, msgs);
      case SqlPackage.PIVOT_TABLE__PFOR:
        return basicSetPfor(null, msgs);
      case SqlPackage.PIVOT_TABLE__PIN:
        return basicSetPin(null, msgs);
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
      case SqlPackage.PIVOT_TABLE__PFUN:
        return getPfun();
      case SqlPackage.PIVOT_TABLE__PFOR:
        return getPfor();
      case SqlPackage.PIVOT_TABLE__PIN:
        return getPin();
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
      case SqlPackage.PIVOT_TABLE__PFUN:
        setPfun((PivotFunctions)newValue);
        return;
      case SqlPackage.PIVOT_TABLE__PFOR:
        setPfor((PivotForClause)newValue);
        return;
      case SqlPackage.PIVOT_TABLE__PIN:
        setPin((PivotInClause)newValue);
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
      case SqlPackage.PIVOT_TABLE__PFUN:
        setPfun((PivotFunctions)null);
        return;
      case SqlPackage.PIVOT_TABLE__PFOR:
        setPfor((PivotForClause)null);
        return;
      case SqlPackage.PIVOT_TABLE__PIN:
        setPin((PivotInClause)null);
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
      case SqlPackage.PIVOT_TABLE__PFUN:
        return pfun != null;
      case SqlPackage.PIVOT_TABLE__PFOR:
        return pfor != null;
      case SqlPackage.PIVOT_TABLE__PIN:
        return pin != null;
    }
    return super.eIsSet(featureID);
  }

} //PivotTableImpl
