package br.com.bilac.sisdist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

	private String name;
	private double lowerValue;
	private double higherValue;
	private double avgValue;
}
