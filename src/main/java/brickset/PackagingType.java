package brickset;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum PackagingType {

    @JsonProperty("Box") BOX,
    @JsonProperty("Metal canister") METAL_CANISTER,
    @JsonProperty("None (loose parts)") NONE,
    @JsonProperty("Other") OTHER,
    @JsonProperty("Paper bag") PAPER_BAG,
    @JsonProperty("Plastic box") PLASTIC_BOX,
    @JsonProperty("Polybag") POLYBAG,
    @JsonProperty("Shrink-wrapped") SHRINK_WRAPPED,
    @JsonProperty("Tag") TAG,
    @JsonProperty("Tub") TUB,
    @JsonProperty("Zip-lock bag") ZIP_LOCK_BAG,
    @JsonProperty("{Not specified}") NOT_SPECIFIED

}
