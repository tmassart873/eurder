package com.switchfully.eurder.domain.address;

public class Address {
    private final String streetName;
    private final String streetNumber;
    private final String postCode;
    private final String city;

    public Address(AddressBuilder addressBuilder) {
        streetName = addressBuilder.streetName;
        streetNumber = addressBuilder.streetNumber;
        postCode = addressBuilder.postCode;
        city = addressBuilder.city;
    }

    public static final class AddressBuilder {
        private String streetName;
        private String streetNumber;
        private String postCode;
        private String city;

        private AddressBuilder() {
        }

        public static AddressBuilder addressBuilder() {
            return new AddressBuilder();
        }

        public AddressBuilder withStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder withStreetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public AddressBuilder withPostCode(String postCode) {
            this.postCode = postCode;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
