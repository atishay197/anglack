package com.example.crynoz.temp;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AdditionalInformation {

    @SerializedName("age")
    @Expose
    private AdditionalInformation.Age age;

    /**
     *
     * @return
     * The age
     */
    public AdditionalInformation.Age getAge() {
        return age;
    }

    /**
     *
     * @param age
     * The age
     */
    public void setAge(AdditionalInformation.Age age) {
        this.age = age;
    }


    public static enum Age {

        @SerializedName("child")
        CHILD("child"),
        @SerializedName("youth")
        YOUTH("youth"),
        @SerializedName("adult")
        ADULT("adult"),
        @SerializedName("elderly")
        ELDERLY("elderly");
        private final String value;
        private final static Map<String, AdditionalInformation.Age> CONSTANTS = new HashMap<String, AdditionalInformation.Age>();

        static {
            for (AdditionalInformation.Age c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Age(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static AdditionalInformation.Age fromValue(String value) {
            AdditionalInformation.Age constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}