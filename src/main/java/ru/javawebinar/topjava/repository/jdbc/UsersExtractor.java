package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class UsersExtractor implements ResultSetExtractor<Map<Integer, User>> {
    @Override
    public Map<Integer, User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, User> map = new LinkedHashMap<>();
        while (rs.next()){
            Integer id = rs.getInt("id");
            User user = map.get(id);
            if(user == null){
                user = new User();
                user.setId(id);
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setCaloriesPerDay(rs.getInt("calories_per_day"));
                user.setEnabled(rs.getBoolean("enabled"));
                map.put(id,user);
            }
            Set<Role> roles = user.getRoles();
            if(roles == null){
                roles = new HashSet<>();
            }
            roles.add(Role.valueOf(rs.getString("role")));
            user.setRoles(roles);
        }
        return map;
    }
}
