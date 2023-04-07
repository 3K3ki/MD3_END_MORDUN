package interface1;

import implement.User;

public interface ICartService extends IBread{
    boolean addToCart ();
    User getUserLogin();
}
