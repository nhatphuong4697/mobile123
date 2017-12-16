package com.mobile.mobilecustomer.service;

import java.util.List;


import com.mobile.mobilecustomer.entity.Mobile;

public interface IMobileService {
    List<Mobile> getAllMobile();
    Mobile getMobileByid(int mobileid);
    boolean createMobile(Mobile mobile);
    void updateMobile(Mobile mobile);
    void deleteMobile(int mobileid);
}
