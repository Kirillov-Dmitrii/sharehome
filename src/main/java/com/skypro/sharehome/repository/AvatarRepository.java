package com.skypro.sharehome.repository;

import com.skypro.sharehome.entity.Avatar;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends PagingAndSortingRepository <Avatar, Long> {

}
