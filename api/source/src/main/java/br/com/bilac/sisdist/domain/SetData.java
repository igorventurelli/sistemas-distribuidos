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
public class SetData {

    @JsonProperty("name")
    private String name;

    @JsonProperty("print_tag")
    private String printTag;

    @JsonProperty("rarity")
    private String rarity;

    @JsonProperty("price_data")
    private PriceData priceData;
}