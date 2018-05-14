package br.com.bilac.sisdist.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardData {

    @JsonProperty("name")
    private String name;

    @JsonProperty("card_type")
    private String kind;

    @JsonProperty("property")
    private String property;

    @JsonProperty("family")
    private String family;

    @JsonProperty("type")
    private String type;

    @JsonProperty("price_data")
    private SetData setData;
}