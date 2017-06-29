/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.JoinCondition;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.UsingCols;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Join Condition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.JoinConditionImpl#getUseCols <em>Use Cols</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JoinConditionImpl extends MinimalEObjectImpl.Container implements JoinCondition
{
  /**
   * The cached value of the '{@link #getUseCols() <em>Use Cols</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUseCols()
   * @generated
   * @ordered
   */
  protected UsingCols useCols;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JoinConditionImpl()
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
    return SqlPackage.Literals.JOIN_CONDITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UsingCols getUseCols()
  {
    return useCols;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUseCols(UsingCols newUseCols, NotificationChain msgs)
  {
    UsingCols oldUseCols = useCols;
    useCols = newUseCols;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.JOIN_CONDITION__USE_COLS, oldUseCols, newUseCols);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUseCols(UsingCols newUseCols)
  {
    if (newUseCols != useCols)
    {
      NotificationChain msgs = null;
      if (useCols != null)
        msgs = ((InternalEObject)useCols).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.JOIN_CONDITION__USE_COLS, null, msgs);
      if (newUseCols != null)
        msgs = ((InternalEObject)newUseCols).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.JOIN_CONDITION__USE_COLS, null, msgs);
      msgs = basicSetUseCols(newUseCols, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.JOIN_CONDITION__USE_COLS, newUseCols, newUseCols));
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
      case SqlPackage.JOIN_CONDITION__USE_COLS:
        return basicSetUseCols(null, msgs);
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
      case SqlPackage.JOIN_CONDITION__USE_COLS:
        return getUseCols();
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
      case SqlPackage.JOIN_CONDITION__USE_COLS:
        setUseCols((UsingCols)newValue);
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
      case SqlPackage.JOIN_CONDITION__USE_COLS:
        setUseCols((UsingCols)null);
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
      case SqlPackage.JOIN_CONDITION__USE_COLS:
        return useCols != null;
    }
    return super.eIsSet(featureID);
  }

} //JoinConditionImpl
