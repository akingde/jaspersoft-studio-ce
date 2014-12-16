/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.OperandList;
import com.jaspersoft.studio.data.sql.OperandListGroup;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operand List Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.OperandListGroupImpl#getOpGroup <em>Op Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperandListGroupImpl extends MinimalEObjectImpl.Container implements OperandListGroup
{
  /**
   * The cached value of the '{@link #getOpGroup() <em>Op Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOpGroup()
   * @generated
   * @ordered
   */
  protected OperandList opGroup;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected OperandListGroupImpl()
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
    return SqlPackage.Literals.OPERAND_LIST_GROUP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OperandList getOpGroup()
  {
    return opGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOpGroup(OperandList newOpGroup, NotificationChain msgs)
  {
    OperandList oldOpGroup = opGroup;
    opGroup = newOpGroup;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.OPERAND_LIST_GROUP__OP_GROUP, oldOpGroup, newOpGroup);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOpGroup(OperandList newOpGroup)
  {
    if (newOpGroup != opGroup)
    {
      NotificationChain msgs = null;
      if (opGroup != null)
        msgs = ((InternalEObject)opGroup).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.OPERAND_LIST_GROUP__OP_GROUP, null, msgs);
      if (newOpGroup != null)
        msgs = ((InternalEObject)newOpGroup).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.OPERAND_LIST_GROUP__OP_GROUP, null, msgs);
      msgs = basicSetOpGroup(newOpGroup, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.OPERAND_LIST_GROUP__OP_GROUP, newOpGroup, newOpGroup));
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
      case SqlPackage.OPERAND_LIST_GROUP__OP_GROUP:
        return basicSetOpGroup(null, msgs);
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
      case SqlPackage.OPERAND_LIST_GROUP__OP_GROUP:
        return getOpGroup();
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
      case SqlPackage.OPERAND_LIST_GROUP__OP_GROUP:
        setOpGroup((OperandList)newValue);
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
      case SqlPackage.OPERAND_LIST_GROUP__OP_GROUP:
        setOpGroup((OperandList)null);
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
      case SqlPackage.OPERAND_LIST_GROUP__OP_GROUP:
        return opGroup != null;
    }
    return super.eIsSet(featureID);
  }

} //OperandListGroupImpl
