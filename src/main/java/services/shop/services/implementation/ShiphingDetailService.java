package services.shop.services.implementation;


import org.springframework.stereotype.Service;
import services.shop.Dtos.EntitiesDto.ShiphingDto.ShiphingDto;
import services.shop.Dtos.MapperDto.IshiphingMapper;
import services.shop.entities.Product;
import services.shop.entities.ShippingDetail;
import services.shop.repositories.IShippingDetailRepository;
import services.shop.services.contract.IShiphingDetailsService;

import java.util.List;

@Service
public class ShiphingDetailService implements IShiphingDetailsService {
    private final IShippingDetailRepository _shippingDetailRepository;
    private final IshiphingMapper _shiphingMapper;

    public ShiphingDetailService(IShippingDetailRepository shippingDetailRepository, IshiphingMapper shiphingMapper) {
        _shippingDetailRepository = shippingDetailRepository;
        _shiphingMapper = shiphingMapper;
    }

    @Override
    public List<ShiphingDto> getAllShiphing() {
        List<ShippingDetail> shippingDetails = _shippingDetailRepository.findAll();
        return _shiphingMapper.ShiPhingsToShiphingsDto(shippingDetails);
    }

    public ShiphingDto findById(long id){
        ShippingDetail shippingDetail = _shippingDetailRepository.getReferenceById(id);
        return _shiphingMapper.shiphingToshiphingDTO(shippingDetail);
    }

    public List<ShiphingDto> findByOrderId(long orderId){
        List<ShippingDetail>  shippingDetails =_shippingDetailRepository.findByOrderId(orderId);
        return _shiphingMapper.ShiPhingsToShiphingsDto(shippingDetails);
    }


}