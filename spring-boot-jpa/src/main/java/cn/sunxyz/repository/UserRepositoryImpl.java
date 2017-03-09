package cn.sunxyz.repository;

import cn.sunxyz.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by sunxyz on 2017/3/9.
 */
public class UserRepositoryImpl implements UserCustomRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<User> search(Integer age, String AddressCode, User.Sex sex, Pageable pageRequest) {
        String querySql = "select t ";
        String countSql = "select count(t) ";
        StringBuffer sqlBuffer = new StringBuffer("from User t where 1=1");

        if (null != age) {
            sqlBuffer.append(" and t.age = :age");
        }
        if (null != AddressCode) {
            sqlBuffer.append(" and t.AddressCode = :address");
        }
        if (null != sex) {
            sqlBuffer.append(" and t.sex = :sex");
        }

        querySql += sqlBuffer.toString();
        countSql += sqlBuffer.toString();

        Query dataQuery = em.createQuery(querySql);
        Query countQuery = em.createQuery(countSql);

        if (null != age) {
            dataQuery.setParameter("age", age);
            countQuery.setParameter("age", age);
        }
        if (null != AddressCode) {
            dataQuery.setParameter("address", AddressCode);
            countQuery.setParameter("address", AddressCode);
        }
        if (null != sex) {
            dataQuery.setParameter("sex", sex);
            countQuery.setParameter("sex", sex);
        }

        Page<User> page = (pageRequest == null ? new PageImpl(dataQuery.getResultList()) : this.readPage(dataQuery, countQuery, pageRequest));
        return page;
    }

    private Page<User> readPage(Query dataQuery, Query countQuery, Pageable pageable) {
        dataQuery.setFirstResult(pageable.getOffset());
        dataQuery.setMaxResults(pageable.getPageSize());
        long totalSize = (long) countQuery.getSingleResult();
        List<User> content = totalSize > (long) pageable.getOffset() ? dataQuery.getResultList() : Collections.emptyList();
        return new PageImpl(content, pageable, totalSize);
    }


}
