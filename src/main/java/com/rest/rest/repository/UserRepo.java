package com.rest.rest.repository;

import com.rest.rest.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    @Query(value = "SELECT a.* FROM users a",nativeQuery = true)
    List<Map<String,Object>> getAllUser();

    @Query(value = "select a.* FROM users a where a.user_id = ?1",nativeQuery = true)
    Map<String,Object> getUserById(long id);

    @Query(value = "select a.* FROM users a where a.user_name = :username",nativeQuery = true)
    Users findByUserName(@Param("username") String username);
}
