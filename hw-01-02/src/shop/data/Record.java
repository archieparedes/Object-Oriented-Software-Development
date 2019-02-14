package shop.data;

public interface Record {
    /**
     * returns the video.
     *
     */
    public Video video();
    /**
     * returns the number of copies of the video that are in the inventory.
     *
     */
    public int numOwned();
    /**
     * returns the number of copies of the video that are currently checked out.
     *
     */
    public int numOut();
    /**
     * returns the total number of times this video has ever been checked out.
     *
     */
    public int numRentals();
    /**
     *  Return a string representation of the object in the following format:
     * "video [numOwned,numOut,numRentals]"
     */
    public String toString();
}
