package com.ssafy.enjoyTrip.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@Getter
@NoArgsConstructor
//@AllArgsConstructor
public enum Scope {
    PUBLIC,
    PRIVATE;

    @JsonValue
    public Scope getValue() {
        return this;
    }
}
