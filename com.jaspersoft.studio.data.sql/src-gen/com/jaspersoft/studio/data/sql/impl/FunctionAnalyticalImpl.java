/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.AnalyticClause;
import com.jaspersoft.studio.data.sql.FunctionAnalytical;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Function Analytical</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.FunctionAnalyticalImpl#getAnClause <em>An Clause</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionAnalyticalImpl extends MinimalEObjectImpl.Container implements FunctionAnalytical
{
  /**
   * The cached value of the '{@link #getAnClause() <em>An Clause</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnClause()
   * @generated
   * @ordered
   */
  protected AnalyticClause anClause;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FunctionAnalyticalImpl()
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
    return SqlPackage.Literals.FUNCTION_ANALYTICAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalyticClause getAnClause()
  {
    return anClause;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnClause(AnalyticClause newAnClause, NotificationChain msgs)
  {
    AnalyticClause oldAnClause = anClause;
    anClause = newAnClause;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE, oldAnClause, newAnClause);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnClause(AnalyticClause newAnClause)
  {
    if (newAnClause != anClause)
    {
      NotificationChain msgs = null;
      if (anClause != null)
        msgs = ((InternalEObject)anClause).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE, null, msgs);
      if (newAnClause != null)
        msgs = ((InternalEObject)newAnClause).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE, null, msgs);
      msgs = basicSetAnClause(newAnClause, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE, newAnClause, newAnClause));
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
      case SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE:
        return basicSetAnClause(null, msgs);
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
      case SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE:
        return getAnClause();
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
      case SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE:
        setAnClause((AnalyticClause)newValue);
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
      case SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE:
        setAnClause((AnalyticClause)null);
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
      case SqlPackage.FUNCTION_ANALYTICAL__AN_CLAUSE:
        return anClause != null;
    }
    return super.eIsSet(featureID);
  }

} //FunctionAnalyticalImpl
