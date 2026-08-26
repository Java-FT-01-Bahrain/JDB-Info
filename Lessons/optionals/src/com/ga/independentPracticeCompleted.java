package com.ga;

import java.util.Optional;

public class independentPracticeCompleted {

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

        //TODO #1: Write a Supplier lambda using orElseGet to default the price to 500 if the price is missing.
        //Print the output to confirm the price.
        //Hint: You'll also need flatMap
        Integer price = televisionOptional1.flatMap(Television::getPrice).orElseGet(() -> 500);
        System.out.println(price);

        //TODO #3: Call the method that you created in TODO #2 for the following Television objects and print out the
        //output.
        Television television1 =
            new Television("AAAA", true, true, 1500);
        System.out.println(isExpensive(television1));

        Television television2 =
            new Television("BBBB", true, false, 1000);
        System.out.println(isExpensive(television2));

        Television television3 =
            new Television("CCCC", false, false, 500);
        System.out.println(isExpensive(television3));
    }

    //TODO #2 Write a method called "isExpensive" that takes in a
    // Television object and returns true if the price is greater than $999.
    //Hint: Use flatMap and filter.
    public static boolean isExpensive(Television television) {

        return Optional.ofNullable(television)
                .flatMap(Television::getPrice)
                .filter(price -> price > 999)
                .isPresent();
    }
}
