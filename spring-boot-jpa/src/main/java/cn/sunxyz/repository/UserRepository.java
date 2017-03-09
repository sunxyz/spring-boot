package cn.sunxyz.repository;

import cn.sunxyz.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by sunxyz on 2017/3/9.
 */
public interface UserRepository extends CrudRepository<User, Long>, UserCustomRepository, JpaSpecificationExecutor<User> {

}
