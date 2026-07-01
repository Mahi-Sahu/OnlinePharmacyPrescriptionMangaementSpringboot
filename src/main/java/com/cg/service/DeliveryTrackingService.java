package com.cg.service;

import com.cg.dto.request.TrackingRequestDto;
import com.cg.dto.response.TrackingResponseDto;

public interface DeliveryTrackingService {
    TrackingResponseDto getDeliveryTracking(Long trackingId);
    TrackingResponseDto createDeliveryTracking(Long orderId,TrackingRequestDto trackingRequestDto);
    TrackingResponseDto updateDeliveryTracking(Long trackingId, TrackingRequestDto trackingRequestDto);
    void deleteDeliveryTracking(Long trackingId);
    TrackingResponseDto getTrackingDetailsOfOrder(Long orderId);
}
