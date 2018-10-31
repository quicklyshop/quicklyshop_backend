package com.eci.cosw.project.quicklyshop.security.service.inventory.impl;

import com.eci.cosw.project.quicklyshop.security.model.Product;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ProductCsvReader {

    public static final long FILE_MAX_BYTE_SIZE = 15 * 1024 * 1024; // 15MB

    public static List<Product> readCSV(byte[] file) throws RuntimeException {
//        if (!file.canRead()) {
//            throw new RuntimeException("No es posible leer el archivo CSV");
//        }

//        if (!file.getName().endsWith(".csv")) {
//            throw new RuntimeException("El archivo debe ser de extension .csv");
//        }

        if (file.length > FILE_MAX_BYTE_SIZE) {
            throw new RuntimeException("El tamano excede el tamano maximo para un archivo: " + FILE_MAX_BYTE_SIZE / 1024 / 1024 + "MB");
        }

        try {
            MappingIterator<Product> productIter = new CsvMapper().readerWithTypedSchemaFor(Product.class).readValues(file);
            return productIter.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
