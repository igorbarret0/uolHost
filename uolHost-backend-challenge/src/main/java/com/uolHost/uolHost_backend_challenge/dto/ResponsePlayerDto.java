package com.uolHost.uolHost_backend_challenge.dto;

import com.uolHost.uolHost_backend_challenge.entity.enums.HeroGroup;

public record ResponsePlayerDto(
        String name,
        String codename,
        HeroGroup heroGroup
) {
}
