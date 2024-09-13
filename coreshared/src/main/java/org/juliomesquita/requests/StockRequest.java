package org.juliomesquita.requests;

import java.io.Serializable;

public record StockRequest(String codProduct, Integer qnt) implements Serializable {
}
