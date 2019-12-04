package com.makein.client.ServerHit;

import com.makein.client.models.*;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Nataraj Bingi on 19/10/2019.
 */

public interface Api {

    //the base URL for our API
    //make sure you are not using localhost
    //find the ip usinc ipconfig command
//    String BASE_URL = "http://192.168.0.107:80/makein/";
//    String BASE_URL = "http://192.168.0.107:80/makein/";
    String BASE_URL = "http://snsproduction.com/makeasy/";


    //this is our multipart request
    //we have two parameters on is name and other one is description
//    @POST("Api.php?apicall=upload")
    @Multipart
    @POST("apis.php?apicall=upload")
    Call<MyResponse> uploadImage(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file, @Part("desc") RequestBody desc);

    /*@Multipart
    @POST("apis.php?apicall=addcategory")
    Call<MyResponse> addcategory(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                 @Part("name") RequestBody name,
                                 @Part("description") RequestBody description,
                                 @Part("created_by") RequestBody created_by
    );*/

    /*@Multipart
    @POST("apis.php?apicall=addsubcategory")
    Call<MyResponse> addsubcategory(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                    @Part("name") RequestBody name,
                                    @Part("description") RequestBody description,
                                    @Part("prod_id") RequestBody prod_id,
                                    @Part("pur_cost") RequestBody pur_cost,
                                    @Part("sell_cost") RequestBody sell_cost,
                                    @Part("created_by") RequestBody created_by
    );*/

    @Multipart
    @POST("apis.php?apicall=userregister")
    Call<MyResponse> userregister(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                  @Part("first_name") RequestBody first_name,
                                  @Part("last_name") RequestBody last_name,
                                  @Part("gender") RequestBody gender,
                                  @Part("dob") RequestBody dob,
                                  @Part("email_id") RequestBody email_id,
                                  @Part("passwd") RequestBody passwd,
                                  @Part("address_one") RequestBody address_one,
                                  @Part("address_two") RequestBody address_two,
                                  @Part("Landmark") RequestBody Landmark,
                                  @Part("mobile_no") RequestBody mobile_no,
                                  @Part("pincode") RequestBody pincode,
                                  @Part("createdby") RequestBody created_by
    );

    @Multipart
    @POST("apis.php?apicall=userupdate")
    Call<LoginRes> userupdate(@Part("first_name") RequestBody first_name,
                              @Part("last_name") RequestBody last_name,
                              @Part("gender") RequestBody gender,
                              @Part("id") RequestBody id,
                              @Part("dob") RequestBody dob,
                              @Part("address_one") RequestBody address_one,
                              @Part("address_two") RequestBody address_two,
                              @Part("Landmark") RequestBody Landmark,
                              @Part("mobile_no") RequestBody mobile_no,
                              @Part("pincode") RequestBody pincode,
                              @Part("createdby") RequestBody createdby
    );

    @Multipart
    @POST("apis.php?apicall=userprofpicupdate")
    Call<MyResponse> userprofpicupdate(@Part("image\"; filename=\"myfile.jpg\" ") RequestBody file,
                                       @Part("id") RequestBody id,
                                       @Part("createdby") RequestBody created_by
    );

    @Multipart
    @POST("apis.php?apicall=updateuserpas")
    Call<MyResponse> updateuserpas(@Part("id") RequestBody id,
                                   @Part("pwd") RequestBody pwd,
                                   @Part("email_id") RequestBody email_id,
                                   @Part("createdby") RequestBody created_by
    );

    @Multipart
    @POST("apis.php?apicall=getallprodsubs")
    Call<MyResponse> getallprodsubs(@Part("created_by") RequestBody created_by);

    @Multipart
    @POST("apis.php?apicall=login")
    Call<LoginRes> login(@Part("username") RequestBody username,
                         @Part("pwd") RequestBody pwd,
                         @Part("registrationID") RequestBody registrationID,
                         @Part("imeiNumber") RequestBody imeiNumber,
                         @Part("deviceName") RequestBody deviceName,
                         @Part("appVersion") RequestBody appVersion
    );

//    public String registrationID;
//    public String deviceName;
//    public String imeiNumber;
//    public String appVersion;
}