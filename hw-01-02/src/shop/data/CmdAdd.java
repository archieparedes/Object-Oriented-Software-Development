package shop.data;
import shop.command.Command;

final class CmdAdd implements Command{
    private InventorySet _inventory;
    private VideoObj _video;
    private int _change;
    CmdAdd(InventorySet inventory, VideoObj video, int change){
        _inventory = inventory;
        _video = video;
        _change = change;
    }

    @Override
    public boolean run() {
        try {
            _inventory.addNumOwned(_video, _change);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
