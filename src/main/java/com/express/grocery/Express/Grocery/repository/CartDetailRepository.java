package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    @Modifying
    @Query("DELETE FROM cart_detail t WHERE t.cd_id = :pid")
    void deleteByPid(@Param("pid") Integer theId);
}
