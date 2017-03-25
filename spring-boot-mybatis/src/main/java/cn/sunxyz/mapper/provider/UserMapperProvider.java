package cn.sunxyz.mapper.provider;

import cn.sunxyz.domain.User;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class UserMapperProvider {

    // 动态生成sql
    public String findByName(String name) {
        String sql = "SELECT * FROM user";
        if (StringUtils.isEmpty(name)) {
            return sql;
        }
        sql += " WHERE name LIKE '%" + name + "%'";
        return sql;
    }

    // 使用工具类来准备相同的 SQL 语句
    public String findByNameLike(String name) {
        return new SQL() {
            {
                SELECT("id, name, password");
                FROM("user");
                WHERE("name LIKE '%" + name + "%'");
            }
        }.toString();
    }

    public String findByNameAndPassword(Map<String, Object> map) {

        String name = (String) map.get("param1");
        String password = (String) map.get("param2");

        return new SQL() {
            {
                SELECT("id, name, password");
                FROM("user");
                WHERE("name = " + name);
                AND();
                WHERE("password = " + password);
            }
        }.toString();

    }

    public String update(User user) {
        return new SQL() {
            {

                if (!StringUtils.isEmpty(user.getId())) {
                    UPDATE("user");
                    if (!StringUtils.isEmpty(user.getName())) {
                        SET("name = #{name}");
                    }
                    if (user.getPassword() != null) {
                        SET("password = #{password}");
                    }
                    WHERE("id = #{id}");
                }
            }
        }.toString();
    }

    public String insert(User user) {
        return new SQL() {
            {
                INSERT_INTO("user");
                VALUES("name", "#{name}");
                VALUES("password", "#{password}");

            }
        }.toString();
    }

    public String delete(Integer id) {
        return new SQL() {
            {
                DELETE_FROM("user");
                WHERE("id = #{id}");
            }
        }.toString();
    }

}
