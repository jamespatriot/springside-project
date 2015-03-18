package org.springside.examples.quickstart.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springside.examples.quickstart.entity.User;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);


	@Query(value = "select count(*) from ss_task where  user_id=:userId",nativeQuery = true)
	Long countTaskNum(@Param("userId")Long userId);
}
