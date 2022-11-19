package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.ShareHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareHomeRepository extends JpaRepository<ShareHome, Long> {

    @Query(value = "SELECT description FROM share_home where id = :id", nativeQuery = true)
    String getDescriptionShareHome(@Param("id") Long id);

    @Query(value = "SELECT name FROM share_home where id = :id", nativeQuery = true)
    String getNameShareHome(@Param("id") Long id);

    @Query(value = "SELECT address FROM share_home where id = :id", nativeQuery = true)
    String getAddressShareHome(@Param("id") Long id);
}
