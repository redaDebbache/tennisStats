package com.example.tennisstats.infra.dto;

import com.example.tennisstats.infra.hatoasLink.ResourceLink;

public record PlayerDto(Long id, String firstName, String lastName, String shortName, String sex, String country, Integer rank, Long points, ResourceLink fullInfos) {}
