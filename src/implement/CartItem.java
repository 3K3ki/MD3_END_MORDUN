package implement;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Bread bread;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Bread bread, int quantity) {
        this.bread = bread;
        this.quantity = quantity;
    }

    public Bread getBread() {
        return bread;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
