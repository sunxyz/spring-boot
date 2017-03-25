package cn.sunxyz.mapper;

import cn.sunxyz.domain.User;
import cn.sunxyz.mapper.provider.UserMapperProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by yangrd on 2017/3/25.
 */
@Mapper
public interface UserMapper {

    int insertUserXml(User user);

    // 获取主键
    @Insert("INSERT INTO user(name,password) VALUES (#{name}, #{password}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Delete("DELETE FROM user WHERE id　= #{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE user SET name = #{name}, password = #{password} WHERE id = #{id}")
    int update(User user);

    @Select("SELECT id, name, password FROM user WHERE id = #{id}")
    @Results(id = "userMap", value = { @Result(column = "id", property = "id", javaType = Integer.class),
            @Result(column = "name", property = "name", javaType = String.class),
            @Result(column = "password", property = "password", javaType = String.class) })
    User findById(Integer id);

    @Select("SELECT * FROM user")
    @ResultMap("userMap")
    List<User> fingAll();

    // 动态生成sql
    @SelectProvider(type = UserMapperProvider.class, method = "findByNameLike")
    List<User> findByNameLike(String name);

    //多参使用map
    @SelectProvider(type = UserMapperProvider.class, method = "findByNameAndPassword")
    List<User> findByNameAndPassword(String name, String password);

    @InsertProvider(type = UserMapperProvider.class, method = "insert")
    int insertUser(User user);

    @DeleteProvider(type = UserMapperProvider.class, method = "delete")
    int deleteUser(Integer id);
}
