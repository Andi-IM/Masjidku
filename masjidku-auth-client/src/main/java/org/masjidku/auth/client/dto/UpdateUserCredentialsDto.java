package org.masjidku.auth.client.dto;

public record UpdateUserCredentialsDto(
        String userid,
        String username,
        String password
) {
}
