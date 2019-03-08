package mcdull.com.yue.contract;

import java.util.HashMap;
import java.util.List;

import mcdull.com.yue.entity.CartBean;
import mcdull.com.yue.model.ICartmodelCallback;

public interface CartContract {

    public abstract class CartPresenter {

        public abstract void getCarts(HashMap<String, String> params);

    }

    interface ICartModel {

        void getCarts( HashMap<String, String> params, ICartmodelCallback callback );


    }

    interface ICartView {

        void success( List<CartBean.Cart> list );

        void failure( String msg );

    }
}
