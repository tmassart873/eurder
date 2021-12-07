package com.switchfully.eurder.domain.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

@JsonDeserialize(builder = Address.AddressBuilder.class)
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

    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
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

        public Address build() {
            return new Address(this);
        }

        public AddressBuilder withStreetName(@JsonProperty("streetName") String streetName) {
            this.streetName = streetName;
            return this;
        }

        public AddressBuilder withStreetNumber(@JsonProperty("streetNumber") String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public AddressBuilder withPostCode(@JsonProperty("postCode") String postCode) {
            this.postCode = postCode;
            return this;
        }

        public AddressBuilder withCity(@JsonProperty("city") String city) {
            this.city = city;
            return this;
        }
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetName, address.streetName) && Objects.equals(streetNumber, address.streetNumber) && Objects.equals(postCode, address.postCode) && Objects.equals(city, address.city);
    }

}
