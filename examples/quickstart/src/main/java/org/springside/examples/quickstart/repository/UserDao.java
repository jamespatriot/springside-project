package org.springside.examples.quickstart.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springside.examples.quickstart.entity.User;

import javax.jws.soap.SOAPBinding;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface UserDao extends PagingAndSortingRepository<User, Long> {
	User findByLoginName(String loginName);


	@Query(value = "select count(*) from ss_task where  user_id=:userId", nativeQuery = true)
	BigInteger countTaskNum(@Param("userId") Long userId);

	@Query(value = "select count(*) from ss_task where  title in (:title)", nativeQuery = true)
	BigInteger countTaskNum2(@Param("title") List<String> userId);

	@Query(value = "select u.name,count(t.id) c from ss_User u,ss_Task t where t.user_id=u.id group by u.name",nativeQuery = true)
	List<Map<String, Object>> groupByRole();

	@Query("select u,count(t),key(u) from User u join u.tasks t where u.id in :idList group by u")
	List<Object> getUsers(@Param("idList")List<Long> idList);



}