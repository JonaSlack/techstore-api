package com.techstore.api.controller;

import com.techstore.api.dto.ProductoDTO;
import com.techstore.api.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/productos")
    public ProductoDTO saveProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.saveProducto(productoDTO);
    }

    @GetMapping("/productos")
    public List<ProductoDTO> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/productos/{id}")
    public ProductoDTO getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/productos/{id}")
    public ProductoDTO updateProducto(@PathVariable Long id,
                                      @RequestBody ProductoDTO productoDTO) {

        return productoService.updateProducto(id, productoDTO);
    }
}