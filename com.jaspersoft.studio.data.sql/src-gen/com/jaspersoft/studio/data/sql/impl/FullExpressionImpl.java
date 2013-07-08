/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Between;
import com.jaspersoft.studio.data.sql.Comparison;
import com.jaspersoft.studio.data.sql.FullExpression;
import com.jaspersoft.studio.data.sql.InOperator;
import com.jaspersoft.studio.data.sql.Operand;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Full Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FullExpressionImpl#getOp1 <em>Op1</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FullExpressionImpl#getIn <em>In</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FullExpressionImpl#getBetween <em>Between</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FullExpressionImpl#getLike <em>Like</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FullExpressionImpl#getComp <em>Comp</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FullExpressionImpl extends MinimalEObjectImpl.Container implements FullExpression
{
  /**
   * The cached value of the '{@link #getOp1() <em>Op1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp1()
   * @generated
   * @ordered
   */
  protected Operand op1;

  /**
   * The cached value of the '{@link #getIn() <em>In</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIn()
   * @generated
   * @ordered
   */
  protected InOperator in;

  /**
   * The cached value of the '{@link #getBetween() <em>Between</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBetween()
   * @generated
   * @ordered
   */
  protected Between between;

  /**
   * The default value of the '{@link #getLike() <em>Like</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLike()
   * @generated
   * @ordered
   */
  protected static final String LIKE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLike() <em>Like</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLike()
   * @generated
   * @ordered
   */
  protected String like = LIKE_EDEFAULT;

  /**
   * The cached value of the '{@link #getComp() <em>Comp</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComp()
   * @generated
   * @ordered
   */
  protected Comparison comp;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FullExpressionImpl()
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
    return SqlPackage.Literals.FULL_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Operand getOp1()
  {
    return op1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOp1(Operand newOp1, NotificationChain msgs)
  {
    Operand oldOp1 = op1;
    op1 = newOp1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__OP1, oldOp1, newOp1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp1(Operand newOp1)
  {
    if (newOp1 != op1)
    {
      NotificationChain msgs = null;
      if (op1 != null)
        msgs = ((InternalEObject)op1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FULL_EXPRESSION__OP1, null, msgs);
      if (newOp1 != null)
        msgs = ((InternalEObject)newOp1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FULL_EXPRESSION__OP1, null, msgs);
      msgs = basicSetOp1(newOp1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__OP1, newOp1, newOp1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InOperator getIn()
  {
    return in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIn(InOperator newIn, NotificationChain msgs)
  {
    InOperator oldIn = in;
    in = newIn;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__IN, oldIn, newIn);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIn(InOperator newIn)
  {
    if (newIn != in)
    {
      NotificationChain msgs = null;
      if (in != null)
        msgs = ((InternalEObject)in).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FULL_EXPRESSION__IN, null, msgs);
      if (newIn != null)
        msgs = ((InternalEObject)newIn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FULL_EXPRESSION__IN, null, msgs);
      msgs = basicSetIn(newIn, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__IN, newIn, newIn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Between getBetween()
  {
    return between;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBetween(Between newBetween, NotificationChain msgs)
  {
    Between oldBetween = between;
    between = newBetween;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__BETWEEN, oldBetween, newBetween);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBetween(Between newBetween)
  {
    if (newBetween != between)
    {
      NotificationChain msgs = null;
      if (between != null)
        msgs = ((InternalEObject)between).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FULL_EXPRESSION__BETWEEN, null, msgs);
      if (newBetween != null)
        msgs = ((InternalEObject)newBetween).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FULL_EXPRESSION__BETWEEN, null, msgs);
      msgs = basicSetBetween(newBetween, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__BETWEEN, newBetween, newBetween));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLike()
  {
    return like;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLike(String newLike)
  {
    String oldLike = like;
    like = newLike;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__LIKE, oldLike, like));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Comparison getComp()
  {
    return comp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComp(Comparison newComp, NotificationChain msgs)
  {
    Comparison oldComp = comp;
    comp = newComp;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__COMP, oldComp, newComp);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComp(Comparison newComp)
  {
    if (newComp != comp)
    {
      NotificationChain msgs = null;
      if (comp != null)
        msgs = ((InternalEObject)comp).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FULL_EXPRESSION__COMP, null, msgs);
      if (newComp != null)
        msgs = ((InternalEObject)newComp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FULL_EXPRESSION__COMP, null, msgs);
      msgs = basicSetComp(newComp, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FULL_EXPRESSION__COMP, newComp, newComp));
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
      case SqlPackage.FULL_EXPRESSION__OP1:
        return basicSetOp1(null, msgs);
      case SqlPackage.FULL_EXPRESSION__IN:
        return basicSetIn(null, msgs);
      case SqlPackage.FULL_EXPRESSION__BETWEEN:
        return basicSetBetween(null, msgs);
      case SqlPackage.FULL_EXPRESSION__COMP:
        return basicSetComp(null, msgs);
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
      case SqlPackage.FULL_EXPRESSION__OP1:
        return getOp1();
      case SqlPackage.FULL_EXPRESSION__IN:
        return getIn();
      case SqlPackage.FULL_EXPRESSION__BETWEEN:
        return getBetween();
      case SqlPackage.FULL_EXPRESSION__LIKE:
        return getLike();
      case SqlPackage.FULL_EXPRESSION__COMP:
        return getComp();
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
      case SqlPackage.FULL_EXPRESSION__OP1:
        setOp1((Operand)newValue);
        return;
      case SqlPackage.FULL_EXPRESSION__IN:
        setIn((InOperator)newValue);
        return;
      case SqlPackage.FULL_EXPRESSION__BETWEEN:
        setBetween((Between)newValue);
        return;
      case SqlPackage.FULL_EXPRESSION__LIKE:
        setLike((String)newValue);
        return;
      case SqlPackage.FULL_EXPRESSION__COMP:
        setComp((Comparison)newValue);
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
      case SqlPackage.FULL_EXPRESSION__OP1:
        setOp1((Operand)null);
        return;
      case SqlPackage.FULL_EXPRESSION__IN:
        setIn((InOperator)null);
        return;
      case SqlPackage.FULL_EXPRESSION__BETWEEN:
        setBetween((Between)null);
        return;
      case SqlPackage.FULL_EXPRESSION__LIKE:
        setLike(LIKE_EDEFAULT);
        return;
      case SqlPackage.FULL_EXPRESSION__COMP:
        setComp((Comparison)null);
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
      case SqlPackage.FULL_EXPRESSION__OP1:
        return op1 != null;
      case SqlPackage.FULL_EXPRESSION__IN:
        return in != null;
      case SqlPackage.FULL_EXPRESSION__BETWEEN:
        return between != null;
      case SqlPackage.FULL_EXPRESSION__LIKE:
        return LIKE_EDEFAULT == null ? like != null : !LIKE_EDEFAULT.equals(like);
      case SqlPackage.FULL_EXPRESSION__COMP:
        return comp != null;
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
    result.append(" (like: ");
    result.append(like);
    result.append(')');
    return result.toString();
  }

} //FullExpressionImpl
