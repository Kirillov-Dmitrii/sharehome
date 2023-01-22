package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.SchemeRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeRunRepository extends JpaRepository<SchemeRun, Long> {
    @Query(value = "SELECT * FROM scheme_run WHERE share_home_id = :sharehome", nativeQuery = true)
    SchemeRun findSchemeRunByShareHome(@Param("sharehome") Integer shareHome);
}
