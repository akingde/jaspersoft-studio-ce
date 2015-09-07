/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.ExistsOper;
import com.jaspersoft.studio.data.sql.OperandListGroup;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.SubQueryOperand;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exists Oper</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ExistsOperImpl#getOp <em>Op</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ExistsOperImpl#getSubquery <em>Subquery</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ExistsOperImpl#getOpList <em>Op List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExistsOperImpl extends MinimalEObjectImpl.Container implements ExistsOper
{
  /**
   * The default value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected static final String OP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOp() <em>Op</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected String op = OP_EDEFAULT;

  /**
   * The cached value of the '{@link #getSubquery() <em>Subquery</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubquery()
   * @generated
   * @ordered
   */
  protected SubQueryOperand subquery;

  /**
   * The cached value of the '{@link #getOpList() <em>Op List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOpList()
   * @generated
   * @ordered
   */
  protected OperandListGroup opList;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExistsOperImpl()
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
    return SqlPackage.Literals.EXISTS_OPER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOp()
  {
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp(String newOp)
  {
    String oldOp = op;
    op = newOp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.EXISTS_OPER__OP, oldOp, op));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SubQueryOperand getSubquery()
  {
    return subquery;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSubquery(SubQueryOperand newSubquery, NotificationChain msgs)
  {
    SubQueryOperand oldSubquery = subquery;
    subquery = newSubquery;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.EXISTS_OPER__SUBQUERY, oldSubquery, newSubquery);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubquery(SubQueryOperand newSubquery)
  {
    if (newSubquery != subquery)
    {
      NotificationChain msgs = null;
      if (subquery != null)
        msgs = ((InternalEObject)subquery).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.EXISTS_OPER__SUBQUERY, null, msgs);
      if (newSubquery != null)
        msgs = ((InternalEObject)newSubquery).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.EXISTS_OPER__SUBQUERY, null, msgs);
      msgs = basicSetSubquery(newSubquery, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.EXISTS_OPER__SUBQUERY, newSubquery, newSubquery));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OperandListGroup getOpList()
  {
    return opList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOpList(OperandListGroup newOpList, NotificationChain msgs)
  {
    OperandListGroup oldOpList = opList;
    opList = newOpList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.EXISTS_OPER__OP_LIST, oldOpList, newOpList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOpList(OperandListGroup newOpList)
  {
    if (newOpList != opList)
    {
      NotificationChain msgs = null;
      if (opList != null)
        msgs = ((InternalEObject)opList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.EXISTS_OPER__OP_LIST, null, msgs);
      if (newOpList != null)
        msgs = ((InternalEObject)newOpList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.EXISTS_OPER__OP_LIST, null, msgs);
      msgs = basicSetOpList(newOpList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.EXISTS_OPER__OP_LIST, newOpList, newOpList));
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
      case SqlPackage.EXISTS_OPER__SUBQUERY:
        return basicSetSubquery(null, msgs);
      case SqlPackage.EXISTS_OPER__OP_LIST:
        return basicSetOpList(null, msgs);
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
      case SqlPackage.EXISTS_OPER__OP:
        return getOp();
      case SqlPackage.EXISTS_OPER__SUBQUERY:
        return getSubquery();
      case SqlPackage.EXISTS_OPER__OP_LIST:
        return getOpList();
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
      case SqlPackage.EXISTS_OPER__OP:
        setOp((String)newValue);
        return;
      case SqlPackage.EXISTS_OPER__SUBQUERY:
        setSubquery((SubQueryOperand)newValue);
        return;
      case SqlPackage.EXISTS_OPER__OP_LIST:
        setOpList((OperandListGroup)newValue);
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
      case SqlPackage.EXISTS_OPER__OP:
        setOp(OP_EDEFAULT);
        return;
      case SqlPackage.EXISTS_OPER__SUBQUERY:
        setSubquery((SubQueryOperand)null);
        return;
      case SqlPackage.EXISTS_OPER__OP_LIST:
        setOpList((OperandListGroup)null);
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
      case SqlPackage.EXISTS_OPER__OP:
        return OP_EDEFAULT == null ? op != null : !OP_EDEFAULT.equals(op);
      case SqlPackage.EXISTS_OPER__SUBQUERY:
        return subquery != null;
      case SqlPackage.EXISTS_OPER__OP_LIST:
        return opList != null;
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
    result.append(" (op: ");
    result.append(op);
    result.append(')');
    return result.toString();
  }

} //ExistsOperImpl
