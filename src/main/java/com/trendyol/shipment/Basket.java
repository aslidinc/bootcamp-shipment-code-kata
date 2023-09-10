package com.trendyol.shipment;

import java.util.List;

public class Basket {

    private List<Product> products;

    public ShipmentSize getShipmentSize() {

        if ( products.isEmpty() ) { return null; }

        int[] sizeInstances = countSizeInstances();

        if ( products.size() >= 3  ) {
            for ( int i = 0 ; i < sizeInstances.length ; i++ ) {
                if ( sizeInstances[ i ] >= 3 ) {
                    return productValueToSize( i + 1 );
                }
            }
        }
        return productValueToSize( findMaxSizedProduct() );
    }

    public int[] countSizeInstances() {
        int[] sizeInstances = {0, 0, 0, 0};

        for ( int i = 0; i < products.size(); i++ ) {
            sizeInstances[productSizeToValue(products.get( i ).getSize())] =
                    sizeInstances[productSizeToValue(products.get( i ).getSize())] + 1;
        }
        return sizeInstances;
    }

    public int findMaxSizedProduct() {
        int maxSize = 0;

        for ( int i = 0; i < products.size(); i++ ) {
            int currentProductSizeValue = productSizeToValue( getProducts().get( i ).getSize() );
            if(currentProductSizeValue > maxSize) {
                maxSize = currentProductSizeValue;
            }
        }
        return maxSize;
    }

    public int productSizeToValue(ShipmentSize shipmentSize) {
        if ( shipmentSize == ShipmentSize.SMALL ) {
            return 0;
        } else if ( shipmentSize == ShipmentSize.MEDIUM ) {
            return 1;
        } else if ( shipmentSize == ShipmentSize.LARGE ) {
            return 2;
        } else {
            return 3;
        }
    }

    public ShipmentSize productValueToSize(int productValue) {
        if ( productValue == 0 ) {
            return ShipmentSize.SMALL;
        } else if ( productValue == 1 ) {
            return ShipmentSize.MEDIUM ;
        } else if ( productValue == 2 ) {
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
