package com.ssafy.enjoyTrip.user;

import com.ssafy.enjoyTrip.user.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createUser(UserDto userDto) {
        String query = "insert into user(email_id, password, nickname) values(?, ?, ?);";
        Object[] params = new Object[3];
        int paramIdx = 0;

        params[paramIdx++] = userDto.getEmailId();
        params[paramIdx++] = userDto.getPassword();
        params[paramIdx++] = userDto.getNickname();

        this.jdbcTemplate.update(query, params);

        String lastIdQuery = "select user_id from user where user_id = " + userDto.getEmailId();

        return this.jdbcTemplate.queryForObject(lastIdQuery, int.class);
    }

    public UserDto findById(int userId) {
        String query = "select * from user where user_id = " + userId;

        return this.jdbcTemplate.queryForObject(query, userRowMapper());
    }

    public int deleteUser(int userId) {
        String query = "update user set status = 'deleted' where user_id = " + userId + ";";
        this.jdbcTemplate.update(query);

        return userId;
    }

    public int modifyUser(UserDto userDto) {
        String query = "update user set email_id = ?, password = ?, nickname = ? where user_id = ?;";
        Object[] params = new Object[4];
        int idx = 0;

        params[idx++] = userDto.getEmailId();
        params[idx++] = userDto.getPassword();
        params[idx++] = userDto.getNickname();
        params[idx] = userDto.getUserId();

        this.jdbcTemplate.update(query, params);

        return userDto.getUserId();
    }

    private RowMapper<UserDto> userRowMapper() {
        return (rs, rowNum) -> UserDto.builder()
                .emailId(rs.getString("email_id"))
                .password(rs.getString("password"))
                .nickname(rs.getString("nickname"))
                .build();
    }


}
