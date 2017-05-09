package cn.sunxyz.repository;

import cn.sunxyz.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yangrd on 2017/5/8.
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long> {
}
