package com.mobile.mobilecustomer.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.mobile.mobilecustomer.entity.Mobile;
import com.mobile.mobilecustomer.service.IMobileService;

@Controller
@RequestMapping("Mobile")
public class MobileController {
    @Autowired
    private IMobileService mobileservice;
    @GetMapping("mobile")
    public ResponseEntity<Mobile> getMobileById(@RequestParam("id") String id) {
        Mobile mobile = mobileservice.getMobileByid(Integer.parseInt(id));
        return new ResponseEntity<Mobile>(mobile, HttpStatus.OK);
    }
    @GetMapping("all-Mobile")
    public ResponseEntity<List<Mobile>> getAllMobile() {
        List<Mobile> list = mobileservice.getAllMobile();
        return new ResponseEntity<List<Mobile>>(list, HttpStatus.OK);
    }
    @PostMapping("mobile")
    public ResponseEntity<Void> createMobile(@RequestBody Mobile mobile, UriComponentsBuilder builder) {
        boolean flag = mobileservice.createMobile(mobile);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/mobile?id={id}").buildAndExpand(mobile.getMobileid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    @PutMapping("mobile")
    public ResponseEntity<Mobile> updateMobile(@RequestBody Mobile mobile) {
        mobileservice.updateMobile(mobile);
        return new ResponseEntity<Mobile>(mobile, HttpStatus.OK);
    }
    @DeleteMapping("mobile")
    public ResponseEntity<Void> deleteMobile(@RequestParam("id") String id) {
        mobileservice.deleteMobile(Integer.parseInt(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
