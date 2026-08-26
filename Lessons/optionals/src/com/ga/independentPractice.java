package com.ga;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class independentPractice {

    private static class Television {

        private Optional<String> skuId;
        private Optional<Boolean> hdmiEnabled;
        private Optional<Boolean> fourKEnabled;
        private Optional<Integer> price;

        private Television(String skuId, boolean hdmiEnabled, boolean fourKEnabled, Integer price) {
            this.skuId = Optional.ofNullable(skuId);
            this.hdmiEnabled = Optional.ofNullable(hdmiEnabled);
            this.fourKEnabled = Optional.ofNullable(fourKEnabled);
            this.price = Optional.ofNullable(price);
        }

        public Optional<String> getSkuId() {
            return skuId;
        }

        public void setSkuId(Optional<String> skuId) {
            this.skuId = skuId;
        }

        public Optional<Boolean> getHdmiEnabled() {
            return hdmiEnabled;
        }

        public void setHdmiEnabled(Optional<Boolean> hdmiEnabled) {
            this.hdmiEnabled = hdmiEnabled;
        }

        public Optional<Boolean> getFourKEnabled() {
            return fourKEnabled;
        }

        public void setFourKEnabled(Optional<Boolean> fourKEnabled) {
            this.fourKEnabled = fourKEnabled;
        }

        public Optional<Integer> getPrice() {
            return price;
        }

        public void setPrice(Optional<Integer> price) {
            this.price = price;
        }
    }

    public static void main(String[] args) {

        Optional<Television> televisionOptional1 =
                Optional.of(new Television("DDDD", true, true, null));

        //TODO #1: Write a lambda using orElseGet to default the price to 500 if the price is missing. Print the output
        //to confirm the price.

        //TODO #2 Write a method that takes in a Television object and returns true if the price is greater than $999.
        
        //TODO #3: Call the method that you created in TODO #2 for the following Television objects and print out the
        //output.
        Television television1 =
                new Television("AAAA", true, true, 1500);

        Television television2 =
                new Television("BBBB", true, false, 1000);

        Television television3 =
                new Television("CCCC", false, false, 500);
    }

    
}
