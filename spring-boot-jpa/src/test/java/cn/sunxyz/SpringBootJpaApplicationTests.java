package cn.sunxyz;

import cn.sunxyz.domain.User;
import cn.sunxyz.repository.UserRepository;
import com.alibaba.fastjson.JSON;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringBootJpaApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "name" + i;
            Integer age = 19 + (i + 1) % 5;
            String addressCode = "address";
            User.Sex sex = i % 2 == 0 ? User.Sex.MAN : User.Sex.WOMAN;
            User user = new User(name, age, addressCode, sex);
            System.out.println(JSON.toJSON(user));
            userList.add(user);
        }
        userRepository.save(userList);
    }

    @Test
    public void search() {
        int page = 0;
        int size = 2;
        Pageable pageable = new PageRequest(page, size);
        Page<User> pageUser = userRepository.search(null, null, null, pageable);
//        System.out.println(JSON.toJSON(pageUser));
        pageUser = userRepository.search(19, null, User.Sex.WOMAN, pageable);
        System.out.println(JSON.toJSON(pageUser));
//        pageUser = userRepository.search(null, null, null, pageable);
//        System.out.println(JSON.toJSON(pageUser));
//        pageUser = userRepository.search(null, null, null, pageable);
//        System.out.println(JSON.toJSON(pageUser));
    }

    @Test
    public void findAll() {
        int page = 0;
        int size = 2;
        Pageable pageable = new PageRequest(page, size);
        Page<User> pageUser = search(19, null, User.Sex.WOMAN, pageable);
        System.out.println(JSON.toJSON(pageUser));
    }

    public Page<User> search(final Integer age, final String AddressCode, final User.Sex sex, Pageable pageRequest) {
/*      第一种查询方式
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            //创建查询条件
            List<Predicate> predicates = new ArrayList<>();
            if (age != null) {
                predicates.add(cb.equal(root.<Integer>get("age"), age));
            }
            if (AddressCode != null) {
                predicates.add(cb.equal(root.get("AddressCode").as(String.class), AddressCode));
            }
            if (sex != null) {
                predicates.add(cb.equal(root.get("sex").as(User.Sex.class), sex));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };

*/
        //第二种
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            //创建查询条件
            List<Predicate> predicates = new ArrayList<>();
            if (age != null) {
                predicates.add(cb.equal(root.<Integer>get("age"), age));
            }
            if (AddressCode != null) {
                predicates.add(cb.equal(root.get("AddressCode").as(String.class), AddressCode));
            }
            if (sex != null) {
                predicates.add(cb.equal(root.get("sex").as(User.Sex.class), sex));
            }
            Predicate[] assetTradingArray = new Predicate[predicates.size()];
            predicates.toArray(assetTradingArray);
            query.where(assetTradingArray);//这种方式使用JPA的API设置了查询条件，所以不需要再返回查询条件Predicate给Spring Data Jpa，故最后return null;即可。
            return null;
        };
        return userRepository.findAll(specification, pageRequest);
    }

}
