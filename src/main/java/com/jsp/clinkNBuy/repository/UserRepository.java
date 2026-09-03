package com.jsp.clinkNBuy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsp.clinkNBuy.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	boolean existsByMobile(Long mobile);

	Optional<User> findByEmail(String email);
	
//	boolean existsEmailOrMobile(String email,Long mobile);
	 @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END " +
	           "FROM User u WHERE u.email = :email OR u.mobile = :mobile")
	    boolean existsEmailOrMobile(@Param("email") String email,
	                                @Param("mobile") Long mobile);

	boolean existsByEmailorMobile(String email, Long mobile);

}
