package shop.data;

import java.util.*;

import shop.command.Command;
import shop.command.UndoableCommand;
import shop.command.CommandHistory;
import shop.command.CommandHistoryFactory;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Implementation of Inventory interface.
 * @see Data
 */
final class InventorySet implements Inventory {
  private Map<Video,Record> _data;
  private final CommandHistory _history;
  private final Map<String, String> _cons;

  InventorySet() {
    _data = new HashMap<Video,Record>();
    _history = CommandHistoryFactory.newCommandHistory();
    _cons = new ConcurrentHashMap<>();
  }

  public String intern(String s) {
    String exist = _cons.putIfAbsent(s, s);
    return (exist == null) ? s : exist;
  }

  /**
   * If record is null, then delete record for video;
   * otherwise replace record for video.
   * @param record Record
   * @param video video
   */
  void replaceEntry(Video video, Record record) {
    _data.remove(video);
    if (record != null)
      _data.put(video,((RecordObj)record).copy());
  }

  /**
   * Overwrite the map.
   * @param data hashmap
   *
   */
  void replaceMap(Map<Video,Record> data) {
    _data = data;
  }

  /**
   *
   * @return size of hashmap _data
   */
  public int size() {
    return _data.size();
  }

  /**
   *
   * @param v video
   * @return Record from video
   */
  public Record get(Video v) {
    if(_data.containsKey(v)){
      return _data.get(v);
    } else {
      return null;
    }
  }

  /**
   *
   * @return Unmodifiable collection
   */
  public Iterator<Record> iterator() {
    return Collections.unmodifiableCollection(_data.values()).iterator();
  }

  /**
   *
   * @param comparator determines the order of the records returned.
   * @return Unmodifiable collection sorted according to comparator
   */
  public Iterator<Record> iterator(Comparator<Record> comparator) {
    ArrayList<Record> Record_Collection = new ArrayList<>(); // Array List of records
    for (Record r : _data.values()){
      Record_Collection.add(r); // acquires copies only
    }
    Record_Collection.sort(comparator);
    return Collections.unmodifiableCollection(Record_Collection).iterator();
  }

  /**
   * Add or remove copies of a video from the inventory.
   * If a video record is not already present (and change is
   * positive), a record is created. 
   * If a record is already present, numOwned is
   * modified using change
   * If change brings the number of copies to be less
   * than one, the record is removed from the inventory.
   * @param video the video to be added.
   * @param change the number of copies to add (or remove if negative).
   * @return A copy of the previous record for this video (if any)
   * @throws IllegalArgumentException if video null or change is zero
   */
  Record addNumOwned(Video video, int change) {
    if (video == null || change == 0) {
      throw new IllegalArgumentException();
      }

    RecordObj r = (RecordObj) _data.get(video);
    if (r == null && change < 1) {
      throw new IllegalArgumentException();
    } else if (r == null) {
      _data.put(video, new RecordObj(video, change, 0, 0));
    } else if (r.numOwned+change < r.numOut) {
      throw new IllegalArgumentException();
    } else if (r.numOwned+change < 1) {
      _data.remove(video);
    } else {
      _data.put(video, new RecordObj(video, r.numOwned + change, r.numOut, r.numRentals));
    }
    return r;

  }

  /**
   * Check out a video.
   * @param video the video to be checked out.
   * @return A copy of the previous record for this video
   * @throws IllegalArgumentException if video has no record or numOut
   * equals numOwned.
   */
  Record checkOut(Video video) {
      RecordObj rec = (RecordObj) _data.get(video);
      if(!_data.containsKey(video)  || rec.numOut == rec.numOwned){
          throw new IllegalArgumentException("Record does not exist or inventory is rented out.");
      } else {
//          rec.numOut++; // amount of videos currently out increases
//          rec.numRentals++; // amount of rentals increase
        RecordObj old = new RecordObj(video, rec.numOwned(), rec.numOut()+1 ,rec.numRentals()+1);
        replaceEntry(video, old);
      }
      return rec.copy();
  }
  
  /**
   * Check in a video.
   * @param video the video to be checked in.
   * @return A copy of the previous record for this video
   * @throws IllegalArgumentException if video has no record or numOut
   * non-positive.
   */
  Record checkIn(Video video) {
      RecordObj rec = (RecordObj) _data.get(video);
      if(!_data.containsKey(video) || rec.numOut <= 0){
          throw new IllegalArgumentException("Record does not exists or there are no videos checked out.");
      } else {
//          rec.numOut--; // amount of videos currently out decreases
//          // rental amount stays the same
        RecordObj old = new RecordObj(video, rec.numOwned(), rec.numOut()-1 ,rec.numRentals());
        replaceEntry(video, old);
      }
      return rec.copy();
  }
  
  /**
   * Remove all records from the inventory.
   * @return A copy of the previous inventory as a Map
   */
  Map clear() {
      Map<Video,Record> oldData = new HashMap<>(); // holds previous data
      oldData.putAll(_data);
      _data.clear();
      return oldData;
  }

  /**
   * @return a reference to the history.
   */
  CommandHistory getHistory() {
    return _history;
  }
  
  public String toString() {
    StringBuffer buffer = new StringBuffer();
    buffer.append("Database:\n");
    Iterator i = _data.values().iterator();
    while (i.hasNext()) {
      buffer.append("  ");
      buffer.append(i.next());
      buffer.append("\n");
    }
    return buffer.toString();
  }


  /**
   * Implementation of Record interface.
   *
   * This is a utility class for Inventory.  Fields are mutable and
   * package-private.
   *
   * Class Invariant: No two instances may reference the same Video.
   *
   * @see Record
   */
  private static final class RecordObj implements Record {
    Video video; // the video
    int numOwned;   // copies owned
    int numOut;     // copies currently rented
    int numRentals; // total times video has been rented
    
    RecordObj(Video video, int numOwned, int numOut, int numRentals) {
      this.video = video;
      this.numOwned = numOwned;
      this.numOut = numOut;
      this.numRentals = numRentals;
    }
    RecordObj copy() {
      return new RecordObj(video, numOwned, numOut, numRentals);
    }
    public Video video() {
      return video;
    }
    public int numOwned() {
      return numOwned;
    }
    public int numOut() {
      return numOut;
    }
    public int numRentals() {
      return numRentals;
    }
    public boolean equals(Object thatObject) {
      return video.equals(((Record)thatObject).video());
    }
    public int hashCode() {
      return video.hashCode();
    }
    public String toString() {
      StringBuffer buffer = new StringBuffer();
      buffer.append(video);
      buffer.append(" [");
      buffer.append(numOwned);
      buffer.append(",");
      buffer.append(numOut);
      buffer.append(",");
      buffer.append(numRentals);
      buffer.append("]");
      return buffer.toString();
    }
  }
}
