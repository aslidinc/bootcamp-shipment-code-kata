package com.trendyol.shipment;

import java.util.List;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {

        if (products.isEmpty()) { throw new IllegalArgumentException("There is no product in the basket!"); }

        int[] sizeQuantities = countSizeQuantities();

        if (products.size() >= 3) {
            for (int i = 0 ; i < sizeQuantities.length ; i++) {
                if (sizeQuantities[ i ] >= 3) {
                    return shipmentNumericValueToSize(i + 1);
                }
            }
        }
        return findMaxSized(sizeQuantities);
    }

    private int[] countSizeQuantities () {
        int[] sizeQuantities = new int[ShipmentSize.values().length];

        for (int i = 0; i < products.size(); i++) {
            int shipmentValue = shipmentSizeToNumericValue(products.get(i).getSize());
            sizeQuantities[shipmentValue]++;
        }
        return sizeQuantities;
    }

    private ShipmentSize findMaxSized (int[] sizeQuantities) {
        int maxShipmentNumericValue = 0;

        for (int i = sizeQuantities.length - 1 ; i >= 0; i--) {
            if(sizeQuantities[i] > 0) {
                maxShipmentNumericValue = i;
                break;
            }
        }
        return shipmentNumericValueToSize(maxShipmentNumericValue);
    }

    private int shipmentSizeToNumericValue (ShipmentSize shipmentSize) {
        if (shipmentSize == ShipmentSize.SMALL) {
            return 0;
        } else if (shipmentSize == ShipmentSize.MEDIUM) {
            return 1;
        } else if (shipmentSize == ShipmentSize.LARGE) {
            return 2;
        } else {
            return 3;
        }
    }

    private ShipmentSize shipmentNumericValueToSize (int shipmentNumericValue) {
        if (shipmentNumericValue == 0) {
            return ShipmentSize.SMALL;
        } else if (shipmentNumericValue == 1) {
            return ShipmentSize.MEDIUM ;
        } else if (shipmentNumericValue == 2) {
            return ShipmentSize.LARGE;
        } else {
            return ShipmentSize.X_LARGE;
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
