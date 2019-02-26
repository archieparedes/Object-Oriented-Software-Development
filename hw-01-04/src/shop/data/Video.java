package shop.data;

import java.lang.Comparable;

/**
 * An immutable video object.
 *
 * Comprises a triple: title, year, director.
 *
 * Object invariant:
 *
 *   title is non-null, no leading or final spaces, not empty string
 *   year is greater than 1800, less than 5000
 *   irector is non-null, no leading or final spaces, not empty string
 *
 * @see Data
 */
public interface Video extends Comparable {

  /**
   *  @return the value of the attribute.
   */
  public String director();

  /**
   *  @return the value of the attribute.
   */
  public String title();

  /**
   *  @return the value of the attribute.
   */
  public int year();

  /**
   * Compare the attributes of this object with those of thatObject.
   * @param thatObject the Object to be compared.
   * @return true if this object is the same as the obj argument;
   *  false otherwise.
   */
  public boolean equals(Object thatObject);

  /**
   * @return a hash code value for this object using the algorithm from Bloch:
   * fields are added in the following order: title, year, director.
   */
  public int hashCode();

  /**
   * Compares the attributes of this object with those of thatObject, in
   * the following order: title, year, director.
   * @param thatObject the Object to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *  object is less than, equal to, or greater thatObject.
   * @throws ClassCastException if thatObject has an incompatible type.
   */
  public int compareTo(Object thatObject);

  /**
   * @return a string representation of the object in the following format:
   * "title (year) : director"
   */
  public String toString();
}
