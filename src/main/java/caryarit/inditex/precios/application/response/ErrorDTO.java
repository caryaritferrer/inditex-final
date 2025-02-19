package caryarit.inditex.precios.application.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ErrorDTO implements Serializable {

    @JsonProperty("error")
    private String error;

    public ErrorDTO() {
    }

    public ErrorDTO(String error) {
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
