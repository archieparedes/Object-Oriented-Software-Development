package shop.data;

/**
 * Public view of an inventory record.
 *
 * Records are mutable, but cannot be changed outside the package.
 * 
 * This interface should not be implemented outside the package.
 * 
 * equals and hashCode delegate to the
 * underlying Video object.
 * @see Data
 */
public interface Record {
  /**
   * @return  the video.
   * Invariant: video() != null
   */
  public Video video();
  /**
   * @return the number of copies of the video that are in the inventory.
   * Invariant: numOwned() greater than 0
   */
  public int numOwned();
  /**
   * @return the number of copies of the video that are currently checked out.
   * Invariant:numOut() less than or equal to numOwned()
   */
  public int numOut();
  /**
   * @return the total number of times this video has ever been checked out.
   * Invariant:numRentals() greater than or equal to numOut()
   */
  public int numRentals();
  /**
   * Delegates to video.
   * @return false if thatObject is not a Record created by this package.
   */
  public boolean equals(Object thatObject);
  /**
   * Delegates to video.
   * @return hashcode
   */
  public int hashCode();
  /**
   *  @return a string representation of the object in the following format:
   * "video [numOwned,numOut,numRentals]"
   */
  public String toString();
}
