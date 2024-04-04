package services.shop.services.contract;

import services.shop.Dtos.EntitiesDto.ProductsDto.NewProductDto;
import services.shop.Dtos.EntitiesDto.ShiphingDto.NewShiphingDetails;
import services.shop.Dtos.EntitiesDto.ShiphingDto.ShiphingDto;

import java.util.List;

public interface IShiphingDetailsService {
    public List<ShiphingDto> getAllShiphing();
    public ShiphingDto findById(long Id);
    public List<ShiphingDto> findByOrderId(long orderId);
    //public ShiphingDto getShiphingByID(long id);
    // public List<ShiphingDto> getShiphingstack();
    //public ShiphingDto addShiphing(NewShiphingDetails shiphingDetails);
    //public List<ShiphingDto> getShiphingForName(String name);
    //ShiphingDto updateShiphing(long id, NewShiphingDetails shiphingDetails);
    //ShiphingDto deleteShiphing(long id);
}
