package mcdull.com.yue.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

import mcdull.com.yue.R;
import mcdull.com.yue.adapter.CartAdapter;
import mcdull.com.yue.callback.CartUICallback;
import mcdull.com.yue.contract.CartContract;
import mcdull.com.yue.entity.CartBean;
import mcdull.com.yue.presenter.CartPresenter;


public class TwoFragment extends Fragment implements CartContract.ICartView, CartUICallback,XRecyclerView.LoadingListener {
    private XRecyclerView xRecyclerView;
    private CheckBox checkBox;

    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private List<CartBean.Cart> carts;
    private int page = 1;//页码
    private HashMap<String,String> params;


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View mview=inflater.inflate(R.layout.fragment_two, container,false);

        return mview;

    }

    @Override
    public void onViewCreated( @NonNull View view, @Nullable Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        xRecyclerView = view.findViewById(R.id.rv);
        checkBox =view.findViewById(R.id.checkbox);
        initView(view);
        initData();
    }

    private void initData() {

        cartPresenter = new CartPresenter(this);
        carts = new ArrayList<>();

        requstData();

    }

    private void requstData() {
        params = new HashMap<>();
        params.put("uid","51");
        params.put("page",page+"");

        cartPresenter.getCarts(params);
    }

    private void initView(View mview ) {


        xRecyclerView.setLoadingListener(this);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置全选反选按钮
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {


                    for (CartBean.Cart cart : carts) {
                        cart.isChecked = true;//设置一级商家选中
                        for (CartBean.Cart.Product product : cart.list) {
                            product.isProductChecked = true;//设置二级商品选中
                        }
                    }

                } else {//未选中

                    for (CartBean.Cart cart : carts) {
                        cart.isChecked = false;//设置一级商家未选中
                        for (CartBean.Cart.Product product : cart.list) {
                            product.isProductChecked = false;//设置二级商品未选中
                        }
                    }
                }

                //通知刷新适配器
                cartAdapter.notifyDataSetChanged();

                getTotalPrice();

            }
        });


    }


    @Override
    public void success(List<CartBean.Cart> list) {

        if (list != null) {

            carts = list;

            for (CartBean.Cart cart : carts) {
                for (CartBean.Cart.Product product : cart.list) {
                    product.productNum = 1;
                }
            }

            if (page==1){
                xRecyclerView.refreshComplete();
                cartAdapter = new CartAdapter(getActivity(), carts);
                cartAdapter.setCartCallback(this);
                xRecyclerView.setAdapter(cartAdapter);

            }else{
                if (cartAdapter==null){
                    cartAdapter = new CartAdapter(getActivity(), carts);
                    cartAdapter.setCartCallback(this);
                    xRecyclerView.setAdapter(cartAdapter);
                }else{
                    cartAdapter.addData(list);
                }
                xRecyclerView.loadMoreComplete();
            }




        }

    }

    @Override
    public void failure(String msg) {

    }

    private void getTotalPrice() {
        double totalprice = 0;
        for (CartBean.Cart cart : cartAdapter.getCarts()) {

            for (CartBean.Cart.Product product : cart.list) {

                if (product.isProductChecked) {
                    System.out.println("num:"+product.productNum);
                    totalprice += product.price*product.productNum;
                }

            }
        }
        checkBox.setText("¥：" + totalprice);


    }

    @Override
    public void notifyCart() {
        getTotalPrice();
    }
    @Override
    public void onRefresh() {
        page = 1;
        requstData();

    }
    @Override
    public void onLoadMore() {

        page++;
        requstData();
    }
}
