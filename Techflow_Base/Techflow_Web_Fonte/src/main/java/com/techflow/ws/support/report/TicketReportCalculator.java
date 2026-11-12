package com.techflow.ws.support.report;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Pure report calculation (no framework, no database): turns ticket snapshots into
 * the performance indicators of US09.
 */
public final class TicketReportCalculator {

    public static final int STATUS_OPEN = 1;
    public static final int STATUS_IN_PROGRESS = 2;
    public static final int STATUS_CANCELLED = 3;
    public static final int STATUS_RESOLVED = 4;

    /** Minimal view of a ticket needed by the report. */
    public record TicketSnapshot(int statusId, String status, String priority, String category,
            Instant createDate, Instant updateDate) {
    }

    public record Count(String label, long total) {
    }

    public record Report(long total, long open, long inProgress, long resolved, long cancelled,
            Double avgResolutionHours, List<Count> byStatus, List<Count> byPriority, List<Count> byCategory) {
    }

    private TicketReportCalculator() {
    }

    public static Report calculate(List<TicketSnapshot> tickets) {
        List<TicketSnapshot> list = tickets == null ? List.of() : tickets;
        return new Report(
                list.size(),
                countStatus(list, STATUS_OPEN),
                countStatus(list, STATUS_IN_PROGRESS),
                countStatus(list, STATUS_RESOLVED),
                countStatus(list, STATUS_CANCELLED),
                averageResolutionHours(list),
                group(list, TicketSnapshot::status),
                group(list, TicketSnapshot::priority),
                group(list, TicketSnapshot::category));
    }

    private static long countStatus(List<TicketSnapshot> list, int statusId) {
        return list.stream().filter(t -> t.statusId() == statusId).count();
    }

    /** Hours between opening and the last update of resolved tickets; null when none is resolved. */
    static Double averageResolutionHours(List<TicketSnapshot> list) {
        List<Duration> durations = list.stream()
                .filter(t -> t.statusId() == STATUS_RESOLVED && t.createDate() != null && t.updateDate() != null)
                .map(t -> Duration.between(t.createDate(), t.updateDate()))
                .filter(d -> !d.isNegative())
                .toList();
        if (durations.isEmpty()) return null;
        double avgMinutes = durations.stream().mapToLong(Duration::toMinutes).average().orElse(0);
        return Math.round(avgMinutes / 60.0 * 10) / 10.0;
    }

    /** Counts per label, largest first; missing labels are grouped as "Sem informação". */
    private static List<Count> group(List<TicketSnapshot> list, Function<TicketSnapshot, String> key) {
        Map<String, Long> counts = list.stream()
                .collect(Collectors.groupingBy(t -> Objects.requireNonNullElse(key.apply(t), "Sem informação"),
                        LinkedHashMap::new, Collectors.counting()));
        return counts.entrySet().stream()
                .map(e -> new Count(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingLong(Count::total).reversed().thenComparing(Count::label))
                .toList();
    }
}
