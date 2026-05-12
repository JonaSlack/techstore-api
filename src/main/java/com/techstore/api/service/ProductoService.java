package com.techstore.api.service;

import com.techstore.api.dto.ProductoDTO;
import com.techstore.api.model.Producto;
import com.techstore.api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoDTO saveProducto(ProductoDTO productoDTO) {

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setActivo(productoDTO.getActivo() != null ? productoDTO.getActivo() : true);

        Producto productoGuardado = productoRepository.save(producto);

        return convertirADTO(productoGuardado);
    }

    public List<ProductoDTO> getAllProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ProductoDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return convertirADTO(producto);
    }

    public void eliminar(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setActivo(false);

        productoRepository.save(producto);
    }

    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());

        if (productoDTO.getActivo() != null) {
            producto.setActivo(productoDTO.getActivo());
        }

        Producto productoActualizado = productoRepository.save(producto);

        return convertirADTO(productoActualizado);
    }

    private ProductoDTO convertirADTO(Producto producto) {

        ProductoDTO dto = new ProductoDTO();

        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        dto.setActivo(producto.getActivo());

        return dto;
    }
}
