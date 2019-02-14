package shop.data;
import shop.command.Command;
import shop.command.UndoableCommand;

/**
 * @author Archie_Paredes
 * This is list of commands a renter can use to check in/out videos from Inventory.
 * Shop owners can make a new Inventory, add new videos to inventory, and clear the inventory.
 */
public class Data{
    /**
     *
     * @param inventory Video, Record Inventory
     * @param video Video
     * @param change Change in video stock
     * @return Returns a command to add or remove copies of video
     */
    public static Command newAddCmd(Inventory inventory, Video video, int change) {
        return new CmdAdd((InventorySet)inventory, (VideoObj)video, change);
    }

    /**
     *
     * @param inventory Video, Record Inventory
     * @return Returns a command to remove every record in inventory
     */
    public static Command newClearCmd(Inventory inventory){
        return new CmdClear((InventorySet)inventory);
    }

    /**
     *
     * @param inventory Video, Record Inventory
     * @param video Video
     * @return Returns a command to check in video from inventory
     */
    public static Command newInCmd(Inventory inventory, Video video) {
        return new CmdIn((InventorySet)inventory, (VideoObj)video);
    }

    /**
     *
     * @return new inventory
     */
    public static Inventory newInventory() {
        return new InventorySet();
    }

    /**
     *
     * @param inventory Video, Record Inventory
     * @param video Video
     * @return Return a command to check out video from inventory
     */
    public static Command newOutCmd(Inventory inventory, Video video) {
        return new CmdOut((InventorySet) inventory, (VideoObj)video);
    }

    /**
     *
     * @param title Title of video
     * @param year  Year Made
     * @param director Director name
     * @return Factory Method of Video Objects
     */
    public static Video newVideo(String title, int year, String director){
        return new VideoObj(title, year, director);
    }

    public static void newUndoCmd(Inventory inventory){

    }

    public static void newRedoCmd(Inventory inventory){

    }
}
