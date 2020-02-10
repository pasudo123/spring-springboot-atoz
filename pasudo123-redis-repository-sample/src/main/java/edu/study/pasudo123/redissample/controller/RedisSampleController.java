package edu.study.pasudo123.redissample.controller;

import edu.study.pasudo123.redissample.atomics.Monitor;
import edu.study.pasudo123.redissample.atomics.MonitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisSampleController {

    private final MonitorService monitorService;

    @GetMapping("/reference-id/{referenceId}")
    public ResponseEntity<List<Monitor>> getDateRangeMonitor(@PathVariable("referenceId") String referenceId,
                                                             @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime fromDate){

        final List<Monitor> list = monitorService.getRangeMonitor(referenceId, fromDate);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public String getIndexRangeMonitor(@RequestParam("index") Integer index) {

        return "";
    }

    @PostMapping
    public String post() {
        return "";
    }

}
