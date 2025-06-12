package org.acme.services;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.entities.User;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    PgPool client;


    public Uni<List<User>> getAllUsers() {
        return User.listAll();
    }

    public Uni<User> createUser(User user) {
        return user.persist().replaceWith(user);
    }

    public Uni<List<String>> getAllUserNames() {
        return client
                .query("SELECT name FROM users")
                .execute()
                .onItem().transform(rowSet -> {
                    List<String> names = new ArrayList<>();
                    for (Row row : rowSet) {
                        names.add(row.getString("name"));
                    }
                    return names;
                });
    }

    public Uni<String> getUserName(Integer id) {
        return client
                .preparedQuery("SELECT name FROM users WHERE id = $1")
                .execute(Tuple.of(id))
                .onItem()
                .transform(rowSet -> {
                    if (!rowSet.iterator().hasNext()) {
                        return null;
                    }
                    return rowSet.iterator().next().getString("name");
                });
    }

    public Uni<String> changeUserRole(Integer id, String role) {
        return client
                .preparedQuery("UPDATE users SET role = $1 WHERE id = $2")
                .execute(Tuple.of(role, id))
                .onItem()
                .transform(rowSet -> {
                    if (rowSet.rowCount() == 0) {
                        return "User not found";
                    }
                    return "Success";
                });
    }
}
