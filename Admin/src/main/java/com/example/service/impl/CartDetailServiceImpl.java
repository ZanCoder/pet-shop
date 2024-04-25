package com.example.service.impl;

import com.example.dto.CartDetailDTO;
import com.example.entity.CartDetail;
import com.example.repository.CartDetailRepository;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartDetailServiceImpl implements CartDetailService {
    private CartDetailRepository cartDetailRepository;
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @Autowired
    public CartDetailServiceImpl(CartDetailRepository cartDetailRepository, CartRepository cartRepository, ProductRepository productRepository) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<CartDetailDTO> getAllCartDetails() {
        List<CartDetail> cartDetails = cartDetailRepository.findAll();
        List<CartDetailDTO> cartDetailDTOS = cartDetails.stream().map(cartDetail -> mapToDTO(cartDetail)).collect(Collectors.toList());
        return cartDetailDTOS;
    }

    @Override
    public CartDetailDTO getCartDetailById(Long id) {
        return mapToDTO(cartDetailRepository.findById(id).orElse(null));
    }

    public CartDetail mapToEntity(CartDetailDTO cartDetailDTO) {
        CartDetail cartDetail = new CartDetail();
        cartDetail.setId(cartDetailDTO.getId());
        cartDetail.setProductCode(cartDetailDTO.getProductCode());
        cartDetail.setVoucher(cartDetailDTO.getVoucher());
        cartDetail.setTransportationCosts(cartDetailDTO.getTransportationCosts());
        cartDetail.setTax(cartDetailDTO.getTax());
        cartDetail.setTotalProduct(cartDetailDTO.getTotalProduct());
        cartDetail.setProduct(productRepository.findById(cartDetailDTO.getProductId()).get());
        cartDetail.setCart(cartRepository.findById(cartDetailDTO.getCartId()).get());
        return cartDetail;
    }

    public CartDetailDTO mapToDTO(CartDetail cartDetail) {
        CartDetailDTO cartDetailDTO = new CartDetailDTO();
        cartDetailDTO.setId(cartDetail.getId());
        cartDetailDTO.setProductCode(cartDetail.getProductCode());
        cartDetailDTO.setVoucher(cartDetail.getVoucher());
        cartDetailDTO.setTransportationCosts(cartDetail.getTransportationCosts());
        cartDetailDTO.setTax(cartDetail.getTax());
        cartDetailDTO.setTotalProduct(cartDetail.getTotalProduct());
        cartDetailDTO.setProductId(cartDetail.getProduct().getId());
        cartDetailDTO.setCartId(cartDetail.getCart().getId());
        return cartDetailDTO;
    }
}
