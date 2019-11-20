package com.rock.mybatis_demo.mapper;

import com.rock.mybatis_demo.model.People;
import com.rock.mybatis_demo.model.PeopleExample;
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

public interface PeopleMapper {
    @SelectProvider(type=PeopleSqlProvider.class, method="countByExample")
    int countByExample(PeopleExample example);

    @DeleteProvider(type=PeopleSqlProvider.class, method="deleteByExample")
    int deleteByExample(PeopleExample example);

    @Delete({
        "delete from people",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into people (NAME, age, ",
        "email, manager_id, ",
        "create_time, update_time, ",
        "version, deleted)",
        "values (#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER}, ",
        "#{email,jdbcType=VARCHAR}, #{managerId,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{version,jdbcType=INTEGER}, #{deleted,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(People record);

    @InsertProvider(type=PeopleSqlProvider.class, method="insertSelective")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insertSelective(People record);

    @SelectProvider(type=PeopleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="manager_id", property="managerId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    List<People> selectByExample(PeopleExample example);

    @Select({
        "select",
        "id, NAME, age, email, manager_id, create_time, update_time, version, deleted",
        "from people",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="manager_id", property="managerId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="version", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.INTEGER)
    })
    People selectByPrimaryKey(Long id);

    @UpdateProvider(type=PeopleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") People record, @Param("example") PeopleExample example);

    @UpdateProvider(type=PeopleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") People record, @Param("example") PeopleExample example);

    @UpdateProvider(type=PeopleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(People record);

    @Update({
        "update people",
        "set NAME = #{name,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "email = #{email,jdbcType=VARCHAR},",
          "manager_id = #{managerId,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "version = #{version,jdbcType=INTEGER},",
          "deleted = #{deleted,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(People record);


    @Select("select * from people")
//    @Results({
//            @Result(column = "cover_img",property = "coverImg"),
//            @Result(column = "create_time",property = "createTime")
//    })
    List<People> findAll();


    @Select("SELECT * FROM people WHERE id = #{id}")
    People findById(long id);


    @Update("UPDATE people SET name=#{name} WHERE id =#{id}")
    int update(People people);

    @Delete("DELETE FROM people WHERE id =#{id}")
    int delete(long id);


    @Insert("INSERT INTO `people` (`NAME`, `age`, `email`, `manager_id`, `create_time`, `update_time`, " +
            "`version`, `deleted`) " +
            "VALUES " +
            "(#{name}, #{age}, #{email}, #{managerId}, #{createTime}, #{updateTime}, #{version}, #{deleted});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int save(People people);
}