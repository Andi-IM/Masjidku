package org.masjidku.auth.client.dto;

public record UpdateUserStatusDto(
        String userid,
        String jabatan,
        String status
) {
}
