package com.mobile.mobilecustomer.Dao;
import java.util.List;
import com.mobile.mobilecustomer.*;
import com.mobile.mobilecustomer.entity.Mobile;

public interface IMobileDao {
    List<Mobile> getAllMobile();
    Mobile getMobileById(int mobileid );
    void createMobile(Mobile mobile);
    void updateMobile(Mobile mobile);
    void deleteMobile(int mobileid);
    boolean mobileExists(String namemobile,String decription,int cost,String image);
}
