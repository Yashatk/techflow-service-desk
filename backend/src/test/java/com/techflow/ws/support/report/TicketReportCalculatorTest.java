package com.techflow.ws.support.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.techflow.ws.support.report.TicketReportCalculator.Count;
import com.techflow.ws.support.report.TicketReportCalculator.Report;
import com.techflow.ws.support.report.TicketReportCalculator.TicketSnapshot;

class TicketReportCalculatorTest {

    private static final Instant T0 = Instant.parse("2026-11-01T10:00:00Z");

    private static TicketSnapshot ticket(int statusId, String status, String priority, String category, long hoursToUpdate) {
        return new TicketSnapshot(statusId, status, priority, category, T0, T0.plusSeconds(hoursToUpdate * 3600));
    }

    @Test
    void emptyPeriodYieldsZeroedReport() {
        Report r = TicketReportCalculator.calculate(List.of());
        assertEquals(0, r.total());
        assertNull(r.avgResolutionHours());
        assertEquals(List.of(), r.byStatus());
    }

    @Test
    void countsTicketsPerStatus() {
        Report r = TicketReportCalculator.calculate(List.of(
                ticket(1, "Aberto", "ALTA", "TI", 1),
                ticket(1, "Aberto", "BAIXA", "RH", 1),
                ticket(2, "Em Andamento", "ALTA", "TI", 1),
                ticket(3, "Cancelado", "MEDIA", "TI", 1),
                ticket(4, "Resolvido", "ALTA", "TI", 4)));
        assertEquals(5, r.total());
        assertEquals(2, r.open());
        assertEquals(1, r.inProgress());
        assertEquals(1, r.cancelled());
        assertEquals(1, r.resolved());
    }

    @Test
    void averagesResolutionTimeOnlyOverResolvedTickets() {
        Report r = TicketReportCalculator.calculate(List.of(
                ticket(4, "Resolvido", "ALTA", "TI", 2),
                ticket(4, "Resolvido", "ALTA", "TI", 5),
                ticket(1, "Aberto", "ALTA", "TI", 100)));
        assertEquals(3.5, r.avgResolutionHours());
    }

    @Test
    void groupsLargestFirstAndLabelsMissingValues() {
        Report r = TicketReportCalculator.calculate(List.of(
                ticket(1, "Aberto", "ALTA", "TI", 1),
                ticket(1, "Aberto", null, "TI", 1),
                ticket(1, "Aberto", "ALTA", "RH", 1)));
        assertEquals(List.of(new Count("TI", 2), new Count("RH", 1)), r.byCategory());
        assertEquals(List.of(new Count("ALTA", 2), new Count("Sem informação", 1)), r.byPriority());
    }
}
