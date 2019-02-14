package shop.data;
import shop.command.Command;

final class CmdClear implements Command{
    private InventorySet _inventory;
    CmdClear(InventorySet inventory){
        _inventory = inventory;
    }

    @Override
    public boolean run() {
        try{
            _inventory.clear();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
