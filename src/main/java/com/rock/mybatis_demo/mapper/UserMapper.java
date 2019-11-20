package com.rock.mybatis_demo.mapper;

import com.rock.mybatis_demo.model.User;
import com.rock.mybatis_demo.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserMapper {
    @SelectProvider(type=UserSqlProvider.class, method="countByExample")
    int countByExample(UserExample example);

    @DeleteProvider(type=UserSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserExample example);

    @Delete({
        "delete from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into user (name, age, ",
        "email, deleted)",
        "values (#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, ",
        "#{email,jdbcType=VARCHAR}, #{deleted,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(User record);

    @SelectProvider(type=UserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    List<User> selectByExample(UserExample example);

    @Select({
        "select",
        "id, name, age, email, deleted",
        "from user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    User selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set name = #{name,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "email = #{email,jdbcType=VARCHAR},",
          "deleted = #{deleted,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);


    List<User> findAll();


    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(long id);


    @Update("UPDATE user SET name=#{name} WHERE id =#{id}")
    int update(User user);

    @Delete("DELETE FROM user WHERE id =#{id}")
    int delete(long id);


    @Insert("INSERT INTO `user` (`NAME`, `age`, `email`, `manager_id`, `create_time`, `update_time`, " +
            "`version`, `deleted`) " +
            "VALUES " +
            "(#{name}, #{age}, #{email}, #{managerId}, #{createTime}, #{updateTime}, #{version}, #{deleted});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(User user);
}