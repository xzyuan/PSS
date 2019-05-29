package com.example.demo.controller;

import com.example.demo.model.Division;
import com.example.demo.service.DivisionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/division")
@CrossOrigin
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @ApiOperation(value = "获取所有的分组信息")
    @GetMapping("/getAll")
    public List<Division> getAll(){
        return divisionService.getAllDivision();
    }
}
