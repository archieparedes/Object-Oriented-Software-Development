package shop.data;
import com.sun.prism.impl.Disposer;
import java.util.*;


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

final class InventorySet implements Inventory{
    /** @invariant <code>_data != null</code> */
    private final Map<Video, Record> _data;

    InventorySet() {
        _data = new HashMap<Video, Record>();
    }

    /**
     * @Return the number of Records.
     */
    public int size() {
        int size = 0;
        for (Record r : _data.values()){
            if(r != null) size++; // if there's a record, add 1 to size
        }
        return size;
    }

    /**
     * @Return a copy of the record for a given Video.
     */
    public Record get(Video v) {
        if(_data.containsKey(v)){
            return _data.get(v);
        } else {
            return null;
        }
    }

    /**
     * @Return an iterator over Records in the Inventory
     * Neither the underlying collection, nor the actual records are returned.
     */
    public Iterator<Record> iterator() {
        Collection a = Collections.unmodifiableCollection(_data.values());
        return a.iterator();
    }

    /**
     *
     * @param comparator
     * @return Returns an iterator over the inventory, sorted according the comparatror
     */
    public Iterator<Record> iterator(Comparator<Record> comparator) {
        // Recall that an ArrayList is a Collection.
        ArrayList<Record> Record_Collection = new ArrayList<>(); // Array List of records
        for (Record r : _data.values()){
            Record_Collection.add(r); // acquires copies only
        }
        Record_Collection.sort(comparator);
        return Record_Collection.iterator();
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
    void addNumOwned(Video video, int change) {
        if(video == null || change == 0)   throw new IllegalArgumentException("Invalid parameters");
        Record newRecord;
        RecordObj oldRecord = (RecordObj) _data.get(video);

        if(!_data.containsKey(video) && change > 0){ // if record doesn't exists and change is positive, create new record if change > 0
            newRecord = new RecordObj(video, change, 0, 0);
            _data.put(video, newRecord);
            return;
        }
        else if(_data.containsKey(video) && change >= 1) { // if the record is present and change >= 1, update record
            newRecord = new RecordObj(video, oldRecord.numOwned += change, oldRecord.numOut, oldRecord.numRentals);
            _data.replace(video, _data.get(video), newRecord); // numOwned is modified
            return;
        }
        else if (_data.containsKey(video) && change < 1){
            if (oldRecord.numOwned + change < oldRecord.numOut) throw new IllegalArgumentException("Videos are rented out"); // can't remove if videos are out
            if(oldRecord.numOwned + change <= 0){
                _data.remove(video);
            }
            newRecord = new RecordObj(video, oldRecord.numOwned += change, oldRecord.numOut, oldRecord.numRentals);
            _data.replace(video, oldRecord, newRecord);
            return;
        }
        else if(!_data.containsKey(video) && change < 1){
            throw new IllegalArgumentException("Video doesn't exist and change is less than 1");
        }
    }

    /**
     * Check out a video.
     * @param video the video to be checked out.
     * @throws IllegalArgumentException if video has no record or numOut
     * equals numOwned.
     * @postcondition changes the record for the video
     */
    void checkOut(Video video) {
        RecordObj rec = (RecordObj) _data.get(video);
        if(!_data.containsKey(video)  || rec.numOut == rec.numOwned){
            throw new IllegalArgumentException("Record does not exist or inventory is rented out.");
        } else {
            rec.numOut++; // amount of videos currently out increases
            rec.numRentals++; // amount of rentals increase
        }
    }

    /**
     * Check in a video.
     * @param video the video to be checked in.
     * @throws IllegalArgumentException if video has no record or numOut
     * non-positive.
     * @postcondition changes the record for the video
     */
    void checkIn(Video video) {
        RecordObj rec = (RecordObj) _data.get(video);
        if(!_data.containsKey(video) || rec.numOut <= 0){
            throw new IllegalArgumentException("Record does not exists or there are no videos checked out.");
        } else {
            rec.numOut--; // amount of videos currently out decreases
            // rental amount stays the same
        }
    }

    /**
     * Remove all records from the inventory.
     * @throw IllegalArgumentException if there is nothing to clear or Videos are rented out
     * @postcondition <code>size() == 0</code>
     */

    void clear() {
        // iterate through _data first to check if any are rented out. If there are, throw error
//        RecordObj rec;
//        for(Video v : _data.keySet()){
//            rec = (RecordObj) _data.get(v);
//            //assert(rec.numOut == 0);
//            if(rec.numOut > 0)  throw new IllegalArgumentException("Videos are rented out");
//        }
//        if(size() <= 0){
//            throw new IllegalArgumentException("Nothing to clear");
//        }
        _data.clear();
        assert(size() == 0);
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

    ////////////////////////Record////////////////////////////////
    private static final class RecordObj implements Record {
        /**
         * The video.
         * @invariant <code>video != null</code>
         */
        Video video;
        /**
         * The number of copies of the video that are in the inventory.
         * @invariant <code>numOwned > 0</code>
         */
        int numOwned;
        /**
         * The number of copies of the video that are currently checked out.
         * @invariant <code>numOut <= numOwned</code>
         */
        int numOut;
        /**
         * The total number of times this video has ever been checked out.
         * @invariant <code>numRentals >= numOut</code>
         */
        int numRentals;

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

        /**
         * Initialize all object attributes.
         */
        RecordObj(Video video, int numOwned, int numOut, int numRentals) {
            this.video = video;
            this.numOwned = numOwned;
            this.numOut = numOut;
            this.numRentals = numRentals;
        }
        /**
         * Return a shallow copy of this record.
         */
        public RecordObj copy() {
            return new RecordObj(video,numOwned,numOut,numRentals);
        }
        /**
         * Return a string representation of the object in the following format:
         * <code>"video [numOwned,numOut,numRentals]"</code>.
         */
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



