package com.makein.client.models;

import java.io.Serializable;
import java.util.List;

public class MyResponse implements Serializable {
    public boolean error;
    public String message;
    public List<Data> data;

    public class Data implements Serializable {
        public String id;
        public String name;
        public String description;
        public String img_url;
        public String created_datetime;
        public List<SubProds> subProds;
    }

    public class SubProds implements Serializable {
        public String id;
        public String name;
        public String description;
        public String prod_id;
        public List<String> img_urls;
        public String pur_cost;
        public String sell_cost;
        public String createdby;
        public String created_datetime;
        public String updated_datetime;
    }
}
