package com.uolHost.uolHost_backend_challenge.dto;

import jakarta.validation.constraints.NotBlank;

public record SavePlayerDto(
        @NotBlank String name,
        @NotBlank String email,
        String phone,
        String heroGroup
) {
}
