package com.bogado.sebastian.challenge.mapper;

import com.bogado.sebastian.challenge.controller.rest.request.PhoneRequest;
import com.bogado.sebastian.challenge.model.entity.PhoneEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class PhoneMapper {
    private PhoneMapper() {
    }

    public static PhoneEntity fromPhoneRequestToEntity(PhoneRequest phone) {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setNumber(phone.getNumber());
        phoneEntity.setCityCode(phone.getCityCode());
        phoneEntity.setCountryCode(phone.getCountryCode());
        return phoneEntity;
    }

    public static List<PhoneEntity> fromPhoneRequestListToEntityList(List<PhoneRequest> phones) {
        if (phones == null) {
            return null;
        }
        return phones.stream().map(PhoneMapper::fromPhoneRequestToEntity).collect(Collectors.toList());
    }

}
