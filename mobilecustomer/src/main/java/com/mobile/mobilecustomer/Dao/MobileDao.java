package com.mobile.mobilecustomer.Dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mobile.mobilecustomer.*;
import com.mobile.mobilecustomer.entity.Mobile;
@Transactional
@Repository
public class MobileDao implements IMobileDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Mobile> getAllMobile(){
        String hql = " FROM Mobile as mobile ORDER BY mobile.mobileid ASC";
        return (List<Mobile>) entityManager.createQuery(hql).getResultList();
    }
    @Override
    public Mobile getMobileById(int mobileid ) {
        return entityManager.find(Mobile.class, mobileid);
    }
    @SuppressWarnings("unchecked")
    @Override
    public void createMobile(Mobile mobile){
        entityManager.persist(mobile);
    }
    @Override
    public void updateMobile(Mobile mobile){
        Mobile mob = getMobileById(mobile.getMobileid());
        mob.setNamemobile(mobile.getNamemobile());
        mob.setCost(mobile.getCost());
        mob.setDecription(mobile.getDecription());
        mob.setImage(mobile.getImage());
        entityManager.flush();
    }
    @Override
    public void deleteMobile(int mobileid) {
        entityManager.remove(getMobileById(mobileid));
    }
    @Override
    public boolean mobileExists(String namemobile,String decription,int cost,String image) {
        String hql = "FROM Mobile as mobile WHERE mobile.namemobile = ? and mobile.decription = ? and mobile.Cost = ? and mobile.Image = ?";
        int count = entityManager.createQuery(hql).setParameter(1, namemobile)
                .setParameter(2, decription).getResultList().size();
        return count > 0 ? true : false;
    }

}
