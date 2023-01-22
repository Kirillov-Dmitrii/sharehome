package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.Shedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheduleRepository extends JpaRepository<Shedule, Long> {

    @Query(value = "SELECT day_of_week ||': '||time_begin||'-'||time_end FROM shedule WHERE share_home_id = :sharehome", nativeQuery = true)
    List<String> getShedulesByShareHome(@Param("sharehome") Integer sharehome);
}
