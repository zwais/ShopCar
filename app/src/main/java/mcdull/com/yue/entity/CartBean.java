package mcdull.com.yue.entity;

import java.util.List;

public class CartBean {
    public String code;
    public String msg;
    public List<Cart> data;

    public class Cart {

        public boolean isChecked;

        public String sellerName;
        public String sellerid;
        public List<Product> list;

        public class Product {
            public boolean isProductChecked;
            public String title;
            public String images;
            public double price;
            public String pid;
            public int pos;
            public int productNum =1;
        }


    }
}
