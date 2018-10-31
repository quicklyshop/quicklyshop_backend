package com.eci.cosw.project.quicklyshop.controller;

import com.eci.cosw.project.quicklyshop.model.Product;
import com.eci.cosw.project.quicklyshop.service.inventory.InventoryService;
import com.eci.cosw.project.quicklyshop.service.inventory.impl.ProductCsvReader;
import com.eci.cosw.project.quicklyshop.service.product.ProductService;
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
    private ProductService productService;

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getProductList() {
        return productService.getProductList();
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importCsvFileToInventory(@RequestParam(value = "file", required = true) MultipartFile file, RedirectAttributes redirectAttributes) throws Exception {
        logger.debug("New CSV file received {}", file.getOriginalFilename());

        if (!file.getContentType().equals("text/csv")) {
            return new ResponseEntity<>("El archivo no es  un archivo CSV", HttpStatus.BAD_REQUEST);
        }

        List<Product> products = null;
        try {
            products = ProductCsvReader.readCSV(toByteArray(file.getInputStream()));
        } catch (IOException e) {
            return new ResponseEntity<>("Error leyendo archivo", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        logger.debug("Importando CSV...");
        for (Product product : products) {
            logger.debug("Producto: {}", product.toString());
            inventoryService.addProduct(product, 1);
        }
        logger.debug("Importado de CSV completado");

        logger.info("Recibio archivo \'{}\' satisfactoriamente", file.getOriginalFilename());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public static byte[] toByteArray(InputStream in) throws IOException {

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
