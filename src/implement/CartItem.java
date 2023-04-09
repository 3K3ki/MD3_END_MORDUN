package implement;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Bread bread;
    private int cartId;
    private int quantity;

    public CartItem() {
    }

    public CartItem(Bread bread, int cartId, int quantity) {
        this.bread = bread;
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public CartItem(Bread bread, int quantity) {
    }

    public Bread getBread() {
        return bread;
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
}
