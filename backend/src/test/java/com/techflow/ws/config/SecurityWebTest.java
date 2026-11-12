package com.techflow.ws.config;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.techflow.ws.support.report.TicketReportCalculator;
import com.techflow.ws.support.report.TicketReportController;
import com.techflow.ws.support.report.TicketReportService;
import com.techflow.ws.sys.domain.ServiceResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@WebMvcTest(controllers = TicketReportController.class)
@Import({ SecurityConfig.class, JwtAuthFilter.class, CorsConfig.class, CorrelationIdFilter.class, GlobalExceptionHandler.class })
class SecurityWebTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private TicketReportService reportService;

    private static String token(String subject) {
        return Jwts.builder().setSubject(subject).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60_000))
                .signWith(Keys.hmacShaKeyFor(JwtAuthFilter.JWT_SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    void rejectsRequestsWithoutToken() throws Exception {
        mvc.perform(get("/api/support/report")).andExpect(status().isUnauthorized());
    }

    @Test
    void rejectsForgedToken() throws Exception {
        mvc.perform(get("/api/support/report").header("Authorization", "Bearer forged.token.value"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void acceptsValidTokenAndTagsCorrelationId() throws Exception {
        when(userDetailsService.loadUserByUsername("demo"))
                .thenReturn(User.withUsername("demo").password("x").authorities("USER").build());
        ServiceResponse<TicketReportCalculator.Report> ok = new ServiceResponse<>();
        ok.setObj(TicketReportCalculator.calculate(List.of()));
        ok.setOK();
        when(reportService.report(any(), any())).thenReturn(ok);

        mvc.perform(get("/api/support/report").header("Authorization", "Bearer " + token("demo"))
                        .header(CorrelationIdFilter.HEADER, "abc-123"))
                .andExpect(status().isOk())
                .andExpect(header().string(CorrelationIdFilter.HEADER, "abc-123"))
                .andExpect(jsonPath("$.obj.total").value(0));
    }

    @Test
    void unexpectedErrorsBecomeProblemDetails() throws Exception {
        when(userDetailsService.loadUserByUsername("demo"))
                .thenReturn(User.withUsername("demo").password("x").authorities("USER").build());
        when(reportService.report(any(), any())).thenThrow(new IllegalStateException("db down"));

        mvc.perform(get("/api/support/report").header("Authorization", "Bearer " + token("demo")))
                .andExpect(status().isInternalServerError())
                .andExpect(header().string("Content-Type", "application/problem+json"))
                .andExpect(jsonPath("$.title").value("Erro interno"))
                .andExpect(jsonPath("$.detail").exists());
    }

    @Test
    void corsRejectsUnknownOrigin() throws Exception {
        mvc.perform(options("/api/support/report")
                        .header("Origin", "https://evil.example")
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isForbidden());
    }
}
