package com.example.demo.controller;

import com.example.demo.model.Staff;
import com.example.demo.model.StaffInfo;
import com.example.demo.service.StaffService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService staffService;
//
//    @ApiOperation("插入新用户")
//    @PostMapping("/insert/{card_id}/{name}/{division_id}")
//    public int test(@PathVariable("card_id") int card_id,
//                           @PathVariable("name") String name,
//                           @PathVariable("division_id") int division_id) {
//       return staffService.insertStaff(card_id, name, division_id);
//    }

    @ApiOperation("插入新用户")
    @PostMapping("")
    public int insert(@RequestBody Staff staff) {
        System.out.println("1  " + staff.getCard_id());
        System.out.println("2  " + staff.getDivision_id());
        System.out.println("3  " + staff.getName());
        return staffService.insertStaff(staff.getCard_id(),staff.getName(), staff.getDivision_id());
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getAllInfo")
    public List<StaffInfo> getAllInfo(){
        return staffService.getAllStaffInfo();
    }

    @ApiOperation("删除某个用户")
    @DeleteMapping("/{card_id}")
    public int deleteStaff(@PathVariable("card_id") int card_id){
        return staffService.deleteStaffByCardId(card_id);
    }

}