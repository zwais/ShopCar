package mcdull.com.yue.presenter;

import com.google.gson.Gson;

import java.util.HashMap;

import mcdull.com.yue.contract.CartContract;
import mcdull.com.yue.entity.CartBean;
import mcdull.com.yue.model.CartModel;
import mcdull.com.yue.model.ICartmodelCallback;

public class CartPresenter extends CartContract.CartPresenter {
    private CartModel cartModel;

    public CartPresenter(CartContract.ICartView iCartView) {
        cartModel = new CartModel();
        this.iCartView = iCartView;
    }

    private CartContract.ICartView iCartView;



    @Override
    public void getCarts(HashMap<String, String> params) {

        cartModel.getCarts(params, new ICartmodelCallback() {
            @Override
            public void success(String result) {

                CartBean cartBean = new Gson().fromJson(result,CartBean.class);

                iCartView.success(cartBean.data);
            }

            @Override
            public void failure(String msg) {

                iCartView.failure(msg);

            }
        });

    }
}
