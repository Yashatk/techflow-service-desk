package com.techflow.ws.support.report;

import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techflow.ws.support.entity.Ticket;
import com.techflow.ws.support.repository.TicketRepository;
import com.techflow.ws.support.report.TicketReportCalculator.Report;
import com.techflow.ws.support.report.TicketReportCalculator.TicketSnapshot;
import com.techflow.ws.sys.domain.ServiceResponse;
import com.techflow.ws.sys.domain.Utils;

@Service
public class TicketReportService {

    private static final Logger log = LogManager.getLogger(TicketReportService.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final Duration DEFAULT_PERIOD = Duration.ofDays(30);

    private final TicketRepository ticketRepository;

    public TicketReportService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Transactional(readOnly = true)
    public ServiceResponse<Report> report(String startDate, String endDate) {
        ServiceResponse<Report> resp = new ServiceResponse<>();
        try {
            Instant end = endDate == null || endDate.isEmpty() ? Instant.now() : Utils.parseToInstant(endDate, DATE_FORMAT);
            Instant start = startDate == null || startDate.isEmpty() ? end.minus(DEFAULT_PERIOD) : Utils.parseToInstant(startDate, DATE_FORMAT);
            if (start.isAfter(end)) {
                resp.setMessage("A data inicial deve ser anterior à data final.");
                return resp;
            }
            List<TicketSnapshot> snapshots = ticketRepository.findByCreateDateBetween(start, end).stream()
                    .map(TicketReportService::toSnapshot)
                    .toList();
            resp.setObj(TicketReportCalculator.calculate(snapshots));
            resp.setOK();
        } catch (DateTimeParseException e) {
            resp.setMessage("Período inválido.");
        } catch (Exception e) {
            log.error("Erro ao gerar relatório de chamados", e);
            resp.setMessage("Erro ao gerar relatório.");
        }
        return resp;
    }

    private static TicketSnapshot toSnapshot(Ticket t) {
        return new TicketSnapshot(
                t.getStatus() == null ? 0 : t.getStatus().getId(),
                t.getStatus() == null ? null : t.getStatus().getStatus(),
                t.getPriority() == null ? null : t.getPriority().getPriority(),
                t.getCategory() == null ? null : t.getCategory().getCategory(),
                t.getCreateDate(), t.getUpdateDate());
    }
}
