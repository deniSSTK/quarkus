package org.acme.dto.request;

import io.smallrye.common.constraint.NotNull;

public class UserRequest {

    @NotNull
    public String name;

    @NotNull
    public String email;

    @NotNull
    public String password;
}