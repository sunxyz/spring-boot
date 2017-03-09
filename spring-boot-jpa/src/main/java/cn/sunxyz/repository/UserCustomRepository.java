package cn.sunxyz.repository;

import cn.sunxyz.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by sunxyz on 2017/3/9.
 */
public interface UserCustomRepository {

    Page<User> search(Integer age, String AddressCode, User.Sex sex, Pageable pageRequest);

}
