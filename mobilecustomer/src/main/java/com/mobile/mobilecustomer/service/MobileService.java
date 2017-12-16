package com.mobile.mobilecustomer.service;
 import com.mobile.mobilecustomer.service.IMobileService;

 import java.util.List;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import com.mobile.mobilecustomer.Dao.IMobileDao;
 import com.mobile.mobilecustomer.entity.Mobile;
@Service
public class MobileService implements IMobileService{
    @Autowired
    private IMobileDao mobiledao;
    @Override
    public Mobile getMobileByid(int mobileid) {
        Mobile obj = mobiledao.getMobileById(mobileid);
        return obj;
    }
    @Override
    public List<Mobile> getAllMobile(){
        return mobiledao.getAllMobile();
    }

    @Override
    public synchronized boolean createMobile(Mobile mobile){
        if (mobiledao.mobileExists(mobile.getNamemobile(),mobile.getDecription(),mobile.getCost(),mobile.getImage()) ){
            return false;
        } else {
            mobiledao.createMobile(mobile);
            return true;
        }
    }
    @Override
    public void updateMobile(Mobile mobile) {
        mobiledao.updateMobile(mobile);
    }
    @Override
    public void  deleteMobile(int mobileid) {
        mobiledao.deleteMobile(mobileid);
    }
}
