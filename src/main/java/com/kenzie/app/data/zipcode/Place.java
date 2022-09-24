package com.kenzie.app.data.zipcode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty(value = "place name")
    private String placeName;
    @JsonProperty(value = "post code")
    private String postCode;

    public String getPlaceName() {
        return placeName;
    }
    public void setPlace_name(String place_name) {
        this.placeName = placeName;
    }
    public String getPostCode() {
        return postCode;
    }
    public void setPost_code(String post_code) {
        this.postCode = postCode;
    }

}
