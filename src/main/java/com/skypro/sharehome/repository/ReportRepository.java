package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findReportByDayReport(LocalDate dayReport);
}
