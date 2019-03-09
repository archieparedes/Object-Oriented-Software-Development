package shop.main;

public enum StatesEnum {
    EXITED(0), EXIT(1), START(2), NUMSTATES(10);

    int state;

    StatesEnum(int n){ this.state = n; }

    int get(){ return state; }
}
