package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Component
public class UsersExtractor implements ResultSetExtractor<Map<Integer, User>> {
    @Override
    public Map<Integer, User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        int count = 0;
        Map<Integer, User> map = new LinkedHashMap<>();
        while (rs.next()) {
            count++;
            Integer id = rs.getInt("id");
            User user = map.get(id);
            if (user == null) {
                user = new BeanPropertyRowMapper<>(User.class).mapRow(rs, count);
                map.put(id, user);
            }
            Set<Role> roles = user.getRoles();
            if (roles == null) {
                roles = EnumSet.noneOf(Role.class);
            }
            roles.add(Role.valueOf(rs.getString("role")));
            user.setRoles(roles);
        }
        return map;
    }
}
