package ru.itk.objectmapper.util.exception;

import java.util.UUID;

public class NotEnoughProductInStock extends RuntimeException {

    public NotEnoughProductInStock(UUID productId) {
      super("Not enough product in stock: " + productId);
    }
}
