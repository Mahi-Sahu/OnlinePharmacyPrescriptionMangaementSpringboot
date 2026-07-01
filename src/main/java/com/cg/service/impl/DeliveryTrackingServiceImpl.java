package com.cg.service.impl;

import com.cg.dto.request.TrackingRequestDto;
import com.cg.dto.response.TrackingResponseDto;
import com.cg.entity.DeliveryTracking;
import com.cg.entity.Order;
import com.cg.repository.DeliveryTrackingRepository;
import com.cg.repository.OrderRepository;
import com.cg.service.DeliveryTrackingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTrackingServiceImpl implements DeliveryTrackingService {
    private DeliveryTrackingRepository deliveryTrackingRepository;
    private ModelMapper modelMapper;
    private OrderRepository orderRepository;

    DeliveryTrackingServiceImpl(DeliveryTrackingRepository deliveryTrackingRepository, ModelMapper modelMapper,
                                OrderRepository orderRepository) {
        this.deliveryTrackingRepository = deliveryTrackingRepository;
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
    }

    @Override
    public TrackingResponseDto getDeliveryTracking(Long trackingId) {
        DeliveryTracking deliveryDetails= deliveryTrackingRepository.findById(trackingId)
                .orElseThrow(()->new RuntimeException("Delivery Tracking Id Not Found"));
        return modelMapper.map(deliveryDetails, TrackingResponseDto.class);
    }

    @Override
    public TrackingResponseDto getTrackingDetailsOfOrder(Long orderId) {
        DeliveryTracking deliveryDetails= deliveryTrackingRepository.findByOrderId(orderId);
        if(deliveryDetails==null){
            throw new RuntimeException("No details found");
        }
        return modelMapper.map(deliveryDetails, TrackingResponseDto.class);
    }

    @Override
    public TrackingResponseDto createDeliveryTracking(Long orderId, TrackingRequestDto trackingRequestDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("Order Id Not Found"));
            DeliveryTracking deliveryDetails=new DeliveryTracking();
            deliveryDetails.setOrder(order);
            deliveryDetails.setDeliveryNotes(trackingRequestDto.getDeliveryNotes());
            deliveryDetails.setDeliveryPartner(trackingRequestDto.getDeliveryPartner());
            deliveryDetails.setDeliveryStatus(trackingRequestDto.getDeliveryStatus());
            deliveryDetails.setTrackingNumber(trackingRequestDto.getTrackingNumber());
            deliveryDetails.setDispatchedAt(trackingRequestDto.getDispatchedAt());
            deliveryDetails.setDeliveredAt(trackingRequestDto.getDeliveredAt());
            deliveryDetails.setEstimatedDelDate(trackingRequestDto.getEstimatedDelDate());
            deliveryTrackingRepository.save(deliveryDetails);
            return modelMapper.map(deliveryDetails, TrackingResponseDto.class);
    }

    @Override
    public TrackingResponseDto updateDeliveryTracking(Long trackingId, TrackingRequestDto trackingRequestDto) {
        DeliveryTracking deliveryTracking=deliveryTrackingRepository.findById(trackingId)
                .orElseThrow(()->new RuntimeException("Delivery Tracking Id Not Found"));
        if(trackingRequestDto.getDeliveryStatus()!=null){
            deliveryTracking.setDeliveryStatus(deliveryTracking.getDeliveryStatus());
        }
        if(trackingRequestDto.getDeliveredAt()!=null){
            deliveryTracking.setDeliveredAt(deliveryTracking.getDeliveredAt());
        }
        if(trackingRequestDto.getEstimatedDelDate()!=null){
            deliveryTracking.setEstimatedDelDate(deliveryTracking.getEstimatedDelDate());
        }
        if(trackingRequestDto.getDispatchedAt()!=null){
            deliveryTracking.setDispatchedAt(deliveryTracking.getDispatchedAt());
        }
        if(trackingRequestDto.getOrderId()!=null){
            deliveryTracking.setOrder(orderRepository.findById(trackingRequestDto.getOrderId()).get());
        }
        if(trackingRequestDto.getDeliveryPartner()!=null){
            deliveryTracking.setDeliveryPartner(trackingRequestDto.getDeliveryPartner());
        }
        return modelMapper.map(deliveryTrackingRepository.save(deliveryTracking), TrackingResponseDto.class);
    }

    @Override
    public void deleteDeliveryTracking(Long trackingId) {
        DeliveryTracking deliveryTracking=deliveryTrackingRepository.findById(trackingId)
                .orElseThrow(()->new RuntimeException("Delivery Tracking Id Not Found"));
        deliveryTrackingRepository.delete(deliveryTracking);
    }
}
