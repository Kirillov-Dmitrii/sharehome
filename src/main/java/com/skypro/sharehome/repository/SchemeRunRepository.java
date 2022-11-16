package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.SchemeRun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchemeRunRepository extends JpaRepository<SchemeRun, Long> {
    SchemeRun findSchemeRunByShareHome(Long shareHome);
}
