package com.eci.cosw.project.quicklyshop.controller;

import com.eci.cosw.project.quicklyshop.model.Product;
import com.eci.cosw.project.quicklyshop.service.inventory.InventoryService;
import com.eci.cosw.project.quicklyshop.service.inventory.exceptions.InventoryServiceException;
import com.eci.cosw.project.quicklyshop.service.inventory.impl.ProductCsvReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/products")
    public List<Product> getProductList() {
        return inventoryService.getAllProducts();
    }


    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            inventoryService.addProduct(product, 1);
        } catch (InventoryServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/products/{quantity}")
    public ResponseEntity<?> addProduct(@RequestBody Product product, @PathVariable("quantity") int quantity) {
        try {
            inventoryService.addProduct(product, quantity);
        } catch (InventoryServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(inventoryService.getProductById(id), HttpStatus.OK);
        } catch (InventoryServiceException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/import")
    public ResponseEntity<?> importCsvFileToInventory(@RequestParam(value = "file", required = true) MultipartFile file, RedirectAttributes redirectAttributes) {
        logger.debug("New CSV file received {}", file.getOriginalFilename());

        if (!file.getContentType().equals("text/csv")) {
            return new ResponseEntity<>("El archivo no parece ser un archivo CSV", HttpStatus.BAD_REQUEST);
        }

        List<Product> products = null;
        try {
            products = ProductCsvReader.readCSV(toByteArray(file.getInputStream()));
        } catch (IOException e) {
            return new ResponseEntity<>("Error leyendo archivo", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.debug("Importando CSV...");
        try {
            for (Product product : products) {
                logger.debug("Producto: {}", product.toString());
                inventoryService.addProduct(product, 1);
            }
        } catch (InventoryServiceException ex) {
            logger.error(ex.getMessage());
            return new ResponseEntity<>("Error importando informacion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        logger.debug("Importado de CSV completado");

        logger.info("Recibio archivo \'{}\' satisfactoriamente", file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    private static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;

        // read bytes from the input stream and store them in buffer
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }

        return os.toByteArray();
    }

}
