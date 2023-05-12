package com.debbache.tennisstats.infra.dto.player;

import com.debbache.tennisstats.infra.hatoasLink.ResourceLink;

public record PlayerDto(Long id, String firstName, String lastName, String shortName, String sex, String country,
                        Integer rank, Long points, ResourceLink fullInfos) {
}
