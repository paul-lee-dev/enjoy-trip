//package com.ssafy.enjoyTrip.user.dao;
//
//import com.ssafy.enjoyTrip.user.model.dto.CreateUserReq;
//import com.ssafy.enjoyTrip.user.model.dto.GetUserRes;
//import com.ssafy.enjoyTrip.user.model.dto.ModifyPwdReq;
//import com.ssafy.enjoyTrip.user.model.dto.ModifyUserReq;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//
//@Repository
//public class JdbcTemplateUserDao implements UserDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public void setDataSource(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    public int createUser(CreateUserReq createUserReq) {
//        String query = "insert into user(email_id, password, nickname) values(?, ?, ?);";
//        Object[] params = new Object[3];
//        int paramIdx = 0;
//
//        params[paramIdx++] = createUserReq.getEmail();
//        params[paramIdx++] = createUserReq.getPassword();
//        params[paramIdx++] = createUserReq.getNickname();
//
//        return jdbcTemplate.update(query, params);
//    }
//
//    public GetUserRes findById(int userId) {
//        String query = "select * from user where user_id = " + userId;
//
//        return jdbcTemplate.queryForObject(query, getUserRowMapper());
//    }
//
//    public GetUserRes findByEmail(String email) {
//        String query = "select * from user where email_id = " + email;
//
//        return jdbcTemplate.queryForObject(query, getUserRowMapper());
//    }
//
//    public void deleteUser(int userId) {
//        String query = "update user set status = 'deleted' where user_id = " + userId + ";";
//        jdbcTemplate.update(query);
//    }
//
//    public void modifyUser(ModifyUserReq modifyUserReq) {
//        String query =
//                "update user set email_id = ?, first_name = ?, last_name = ?, nickname = ?, phone_number = ? where user_id = ?;";
//        Object[] params = new Object[5];
//        int idx = 0;
//
//        params[idx++] = modifyUserReq.getEmail();
//        params[idx++] = modifyUserReq.getFirstName();
//        params[idx++] = modifyUserReq.getLastName();
//        params[idx++] = modifyUserReq.getNickname();
//        params[idx] = modifyUserReq.getUserId();
//
//        jdbcTemplate.update(query, params);
//    }
//
//    public void modifyPassword(ModifyPwdReq modifyPwdReq) {
//        String query =
//                "update user set password = ? where user_id = ?;";
//        Object[] params = new Object[2];
//        int idx = 0;
//
//        params[idx++] = modifyPwdReq.getPassword();
//        params[idx] = modifyPwdReq.getUserId();
//
//        jdbcTemplate.update(query, params);
//    }
//
//    private RowMapper<GetUserRes> getUserRowMapper() {
//        return (rs, rowNum) -> GetUserRes.builder()
//                .userId(rs.getInt("user_id"))
//                .email(rs.getString("email_id"))
//                .password(rs.getString("password"))
//                .firstName(rs.getString("first_name"))
//                .lastName(rs.getString("last_name"))
//                .nickname(rs.getString("nickname"))
//                .phoneNumber(rs.getString("phone_number"))
////                .createdAt(rs.getTimestamp())
//                .build();
//    }
//
//
//}
