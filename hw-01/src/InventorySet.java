import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Archie_Paredes
 * An Inventory implemented using a <code>HashMap&lt;Video,Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * @objecttype Mutable Collection of Records
 * @objectinvariant
 *   Every key and value in the map is non-<code>null</code>.
 * @objectinvariant
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 *
 */
final class InventorySet {
  /** @invariant <code>_data != null</code> */
  private final Map<VideoObj,Record> _data;

  InventorySet() {
    _data = new HashMap<VideoObj,Record>();
  }

  /**
   * @Return the number of Records.
   */
  public int size() {
    int size = 0;
    for (Record r : _data.values()){
      //size += r.numOwned;
      if(r != null) size++; // if there's a record, add 1 to size
    }
    return size;
  }

  /**
   * @Return a copy of the record for a given Video.
   */
  public Record get(VideoObj v) {
      if(_data.containsKey(v)){
          return _data.get(v).copy();
      } else {
          return null;
      }
  }

  /**
   * @Return a copy of the records as a collection.
   * Neither the underlying collection, nor the actual records are returned.
   */
  public Collection toCollection() {
    // Recall that an ArrayList is a Collection.
    ArrayList<Record> Record_Collection = new ArrayList<Record>(); // Array List of records
    for (Record r : _data.values()){
      Record_Collection.add(r.copy()); // acquires copies only
    }
    return Record_Collection;
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, <code>numOwned</code> is
   * modified using <code>change</code>.
   * If <code>change</code> brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @throws IllegalArgumentException if video null or change is zero
   * @postcondition changes the record for the video
   */
  public void addNumOwned(VideoObj video, int change) {
    if(!_data.containsKey(video) && change > 0){ // if record doesn't exists and change is positive, create new record
      Record newRecord = new Record(video, change, 0, 0);
      _data.put(video, newRecord);
      return;
    }
    else if(_data.containsKey(video)) { // if the record is present
        Record oldRecord = _data.get(video);
        Record newRecord = new Record(video, change, oldRecord.numOut, oldRecord.numRentals);
        _data.replace(video, oldRecord, newRecord); // numOwned is modified
    }

    if(_data.get(video).numOwned < 1){ // if the change make numOwned less than 1
        _data.remove(video);
      //_data.put(video, null); // deletes record
    }

  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   * @postcondition changes the record for the video
   */
  public void checkOut(VideoObj video) {
    if(!_data.containsKey(video)  || _data.get(video).numOut == _data.get(video).numOwned){
      throw new IllegalArgumentException("Record does not exist or inventory is rented out.");
    } else {
      _data.get(video).numOut++; // amount of videos currently out increases
      _data.get(video).numRentals++; // amount of rentals increase

    }
  }

  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   * @postcondition changes the record for the video
   */
  public void checkIn(VideoObj video) {
    if(!_data.containsKey(video) || _data.get(video).numOut <= 0){
      throw new IllegalArgumentException("Record does not exists or there are no videos checked out.");
    } else {
      _data.get(video).numOut--; // amount of videos currently out decreases
      // rental amount stays the same
    }
  }

  /**
   * Remove all records from the inventory.
   * @postcondition <code>size() == 0</code>
   */
  public void clear() {
      _data.clear();
//    for (VideoObj i : _data.keySet()){
//      _data.put(i, null); // sets records to null
//    }
  }

  /**
   * @Return the contents of the inventory as a string.
   */
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Database:\n");
    for (Record r : _data.values()) {
      buffer.append("  ");
      buffer.append(r);
      buffer.append("\n");
    }
    return buffer.toString();
  }
}
