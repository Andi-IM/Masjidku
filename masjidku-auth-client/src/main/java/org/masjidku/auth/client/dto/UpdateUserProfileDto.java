package org.masjidku.auth.client.dto;

public record UpdateUserProfileDto(
        String userid,
        String notelp,
        String alamat
) {
}
