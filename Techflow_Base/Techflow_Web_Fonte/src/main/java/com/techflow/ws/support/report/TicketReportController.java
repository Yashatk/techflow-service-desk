package com.techflow.ws.support.report;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techflow.ws.support.report.TicketReportCalculator.Report;
import com.techflow.ws.sys.domain.ServiceResponse;

@RestController
@RequestMapping("/api/support/report")
public class TicketReportController {

    private final TicketReportService reportService;

    public TicketReportController(TicketReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<Report>> report(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        ServiceResponse<Report> resp = reportService.report(startDate, endDate);
        return new ResponseEntity<>(resp, resp.getHttpStatus());
    }
}
