package com.example.demo.controller;

import com.example.demo.dao.EventDao;
import com.example.demo.model.FilterPara;
import com.example.demo.model.PersonnelCount;
import com.example.demo.model.Record;
import com.example.demo.service.EventService;
import com.example.demo.service.PCService;
import com.example.demo.util.Config;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
@CrossOrigin
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private PCService pcService;

    @Autowired
    private EventDao eventDao;

    @ApiOperation(value = "根据时间查询")
    @GetMapping("/getByTime/{startTime}/{endTime}")
    public List<Record> getEventByTime(@PathVariable("startTime")long startTime,
                                       @PathVariable("endTime")long endTime){
        return eventService.getEventByTime(startTime,endTime);
    }

    @ApiOperation(value = "查询event")
    @PostMapping("")
    public List<Record> getRecord(@RequestBody FilterPara filterPara) {
        String readerName1 = null;
        String readerName2 = null;
        if(filterPara.getDoor() != null) {
            readerName1 = filterPara.getDoor() + ":Inner";
            readerName2 = filterPara.getDoor() + ":Outer";
        }
        return eventDao.selectRecord(filterPara.getCardId(), filterPara.getDivisionId(), readerName1, readerName2, filterPara.getResult(), filterPara.getStartTime(), filterPara.getEndTime());
    }

    @ApiOperation(value = "根据cardID查询记录")
    @GetMapping("/getByCardIdAndTime/{card_id}/{startTime}/{endTime}")
    public List<Record> getEventByCardIdAndTime(@PathVariable("card_id") int card_id, @PathVariable("startTime")long startTime, @PathVariable("endTime")long endTime){
        return eventService.getEventByCardIdAndTime(card_id, startTime, endTime);
    }

    @ApiOperation(value = "根据部门查询记录")
    @GetMapping("/getByDivisionIdAndTime/{division_id}/{startTime}/{endTime}")
    public List<Record> getEventByDivisionIdAndTime(@PathVariable("division_id") int division_id, @PathVariable("startTime")long startTime, @PathVariable("endTime")long endTime){
        return eventService.getEventByDivisionIdAndTime(division_id, startTime, endTime);
    }

    @ApiOperation(value = "根据出入口读卡器查询记录")
    @GetMapping("/getByDoorAndTime/{doorName}/{startTime}/{endTime}")
    public List<Record> getEventByDoorAndTime(@PathVariable("doorName") String doorName, @PathVariable("startTime")long startTime, @PathVariable("endTime")long endTime) {
        return eventService.getEventByDoorAndTime(doorName, startTime, endTime);
    }

    @ApiOperation(value = "获取当前人数统计数据")
    @GetMapping("/getCurrentPersonnelCount")
    public Map<String, Integer> getPersonnelCount(){
        return pcService.getCurrentPC();
    }

    @ApiOperation(value = "获取环厅内某个地点的历史人数数据")
    @GetMapping("/getPersonnelCount/{position}/{startTime}/{endTime}")
    public List<PersonnelCount> getPCByPosition(@PathVariable("position") String position,
                                                @PathVariable("startTime")long startTime,
                                                @PathVariable("endTime")long endTime){
        return pcService.getPCByPosition(position, startTime, endTime);
    }

    @ApiOperation(value = "生成人员历史记录文件")
    @GetMapping("/downloadPersonnelCount/{nameList}/{startTime}/{endTime}")
    public String download(@PathVariable("nameList") String nameList,
                           @PathVariable("startTime")long startTime,
                           @PathVariable("endTime")long endTime) throws IOException {
        if(nameList == null || nameList.length() == 0) {
            return null;
        }
        String[] strArr = nameList.split("@");
        return pcService.writeToFile(strArr,startTime,endTime);
    }

    @ApiOperation(value = "根据文件名进行下载")
    @GetMapping(value = "/media/{name}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable(value = "name",required = true) String name)
            throws IOException {
        FileSystemResource file = new FileSystemResource(Config.DEFAULT_PATH + "/records/"+name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", name);
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
