package com.makein.client.models;

import java.util.List;

public class LoginRes {
    public boolean error;
    public List<Data> data;
    public String message;

    public class Data {

       public int id;
       public String first_name;
       public String last_name;
       public String profile_img;
       public String gender;
       public String dob;
       public String email_id;
       public String address_one;
       public String address_two;
       public String Landmark;
       public String pincode;
       public String mobile_no;
       public String createdby;
       public String created_datetime;
       public String updated_datetime;
    }
}
