package com.kbtg.bootcamp.posttest.userTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketRepository  extends JpaRepository<UserTicket, String> {
    @Query(value = "SELECT * FROM user_ticket ut WHERE ut.user_id = :id", nativeQuery = true)
    List<UserTicket> findTicketsByUserid(@Param("id") String id);
}
