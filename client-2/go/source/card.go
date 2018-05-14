package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
)

type Card struct {
	Name        string
	LowerValue  float32
	HigherValue float32
	AvgValue    float32
}

func main() {
	fmt.Println("Digite o código da carta:")

	var card string
	fmt.Scanf("%s", &card)

	api := fmt.Sprintf("%s%s", "http://api:7000/rest/cards/", card)

	resp, _ := http.Get(api)
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		panic(err)
	}

	pureJSON := string(body)

	var cardData Card
	erro := json.Unmarshal([]byte(pureJSON), &cardData)
	if erro != nil {
		fmt.Println("error:", erro)
	}

	fmt.Printf("Name: %s\nHigher Price: %f\nAverage Price: %f\nLower Price: %f\n",
		cardData.Name,
		cardData.HigherValue,
		cardData.AvgValue,
		cardData.LowerValue)

}
