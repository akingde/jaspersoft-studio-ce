/**
 */
package com.jaspersoft.studio.data.sql;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Join Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getJoinType()
 * @model
 * @generated
 */
public enum JoinType implements Enumerator
{
  /**
   * The '<em><b>Inner Join</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INNER_JOIN_VALUE
   * @generated
   * @ordered
   */
  INNER_JOIN(0, "innerJoin", "INNER JOIN"),

  /**
   * The '<em><b>Left Outer Join</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LEFT_OUTER_JOIN_VALUE
   * @generated
   * @ordered
   */
  LEFT_OUTER_JOIN(1, "leftOuterJoin", "LEFT OUTER JOIN"),

  /**
   * The '<em><b>Right Outer Join</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #RIGHT_OUTER_JOIN_VALUE
   * @generated
   * @ordered
   */
  RIGHT_OUTER_JOIN(2, "rightOuterJoin", "RIGHT OUTER JOIN"),

  /**
   * The '<em><b>Full Outer Join</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FULL_OUTER_JOIN_VALUE
   * @generated
   * @ordered
   */
  FULL_OUTER_JOIN(3, "fullOuterJoin", "FULL OUTER JOIN"),

  /**
   * The '<em><b>Cross Join</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CROSS_JOIN_VALUE
   * @generated
   * @ordered
   */
  CROSS_JOIN(4, "crossJoin", "CROSS JOIN");

  /**
   * The '<em><b>Inner Join</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Inner Join</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INNER_JOIN
   * @model name="innerJoin" literal="INNER JOIN"
   * @generated
   * @ordered
   */
  public static final int INNER_JOIN_VALUE = 0;

  /**
   * The '<em><b>Left Outer Join</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Left Outer Join</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #LEFT_OUTER_JOIN
   * @model name="leftOuterJoin" literal="LEFT OUTER JOIN"
   * @generated
   * @ordered
   */
  public static final int LEFT_OUTER_JOIN_VALUE = 1;

  /**
   * The '<em><b>Right Outer Join</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Right Outer Join</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #RIGHT_OUTER_JOIN
   * @model name="rightOuterJoin" literal="RIGHT OUTER JOIN"
   * @generated
   * @ordered
   */
  public static final int RIGHT_OUTER_JOIN_VALUE = 2;

  /**
   * The '<em><b>Full Outer Join</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Full Outer Join</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FULL_OUTER_JOIN
   * @model name="fullOuterJoin" literal="FULL OUTER JOIN"
   * @generated
   * @ordered
   */
  public static final int FULL_OUTER_JOIN_VALUE = 3;

  /**
   * The '<em><b>Cross Join</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Cross Join</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CROSS_JOIN
   * @model name="crossJoin" literal="CROSS JOIN"
   * @generated
   * @ordered
   */
  public static final int CROSS_JOIN_VALUE = 4;

  /**
   * An array of all the '<em><b>Join Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final JoinType[] VALUES_ARRAY =
    new JoinType[]
    {
      INNER_JOIN,
      LEFT_OUTER_JOIN,
      RIGHT_OUTER_JOIN,
      FULL_OUTER_JOIN,
      CROSS_JOIN,
    };

  /**
   * A public read-only list of all the '<em><b>Join Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<JoinType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Join Type</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static JoinType get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      JoinType result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Join Type</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static JoinType getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      JoinType result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Join Type</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static JoinType get(int value)
  {
    switch (value)
    {
      case INNER_JOIN_VALUE: return INNER_JOIN;
      case LEFT_OUTER_JOIN_VALUE: return LEFT_OUTER_JOIN;
      case RIGHT_OUTER_JOIN_VALUE: return RIGHT_OUTER_JOIN;
      case FULL_OUTER_JOIN_VALUE: return FULL_OUTER_JOIN;
      case CROSS_JOIN_VALUE: return CROSS_JOIN;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private JoinType(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //JoinType
