package com.bogado.sebastian.challenge.controller.rest.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PhoneRequest {
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    private String number;
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    private String cityCode;
    @NotNull(message = "es un campo requerido")
    @NotEmpty(message = "no puede ser vacio")
    @Size(min = 1, max = 255, message = "debe tener entre 1 y 255 caracteres")
    private String countryCode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
