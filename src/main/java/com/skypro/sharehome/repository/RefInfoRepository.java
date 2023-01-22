package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.RefInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RefInfoRepository extends JpaRepository<RefInfo, Long> {

    @Query(value = "SELECT description FROM refinfo where id = :id and document_name = :name", nativeQuery = true)
    String getDescriptionByDocumentName(@Param("id") Integer id, @Param("name") String name);
}
