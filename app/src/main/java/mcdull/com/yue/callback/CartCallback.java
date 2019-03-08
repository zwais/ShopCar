package mcdull.com.yue.callback;

public interface CartCallback {

    void notifyCartItem( boolean isChecked, int postion );
    void notifyNum();
}
