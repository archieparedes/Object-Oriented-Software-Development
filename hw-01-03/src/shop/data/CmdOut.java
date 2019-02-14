package shop.data;
import shop.command.Command;

final class CmdOut implements Command{
    private InventorySet _inventory;
    private VideoObj _video;
    CmdOut(InventorySet inventory, VideoObj video){
        _inventory = inventory;
        _video = video;
    }

    @Override
    public boolean run() {
        try{
            _inventory.checkOut(_video);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
