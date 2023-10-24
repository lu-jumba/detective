package com.example.detective.repository;



import com.example.detective.entities.Incident;
import com.example.detective.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public  interface UserRepository extends JpaRepository<User, Long> {
    
    public User findByUserId(Long userId);

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findByUsername(String username);

    @Query("SELECT i FROM Incident i WHERE i.username = ?1")
    List<Incident> findIncidentByUsername(Incident incident);
    
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findUserByUsername(String username);

    
}
