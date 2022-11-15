package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.ShareHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareHomeRepository extends JpaRepository<ShareHome, Long> {

}
