package shop.data;
import shop.command.Command;

final class CmdIn implements Command{
    private InventorySet _inventory;
    private VideoObj _video;
    CmdIn(InventorySet inventory, VideoObj video){
        _inventory = inventory;
        _video = video;
    }

    @Override
    public boolean run() {
        try{
            _inventory.checkIn(_video);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
