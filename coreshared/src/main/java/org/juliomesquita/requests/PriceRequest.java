package org.juliomesquita.requests;

import java.io.Serializable;

public record PriceRequest(String codProduct, Double price) implements Serializable {
}
