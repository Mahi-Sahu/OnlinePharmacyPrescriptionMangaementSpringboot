package com.cg.controller;

import com.cg.dto.request.TrackingRequestDto;
import com.cg.dto.response.TrackingResponseDto;
import com.cg.service.DeliveryTrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/track-delivery")
public class DeliveryTrackingController {
    private final DeliveryTrackingService trackingService;

    DeliveryTrackingController(DeliveryTrackingService trackingService) {
        this.trackingService = trackingService;
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity<TrackingResponseDto> getDeliveryTracking(@PathVariable Long trackingId){
        return new ResponseEntity<>(trackingService.getDeliveryTracking(trackingId), HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<TrackingResponseDto> getDeliveryTrackingByOrderId(@RequestParam Long orderId){
        return new ResponseEntity<>(trackingService.getDeliveryTracking(orderId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TrackingResponseDto> createDeliveryTracking(@RequestBody TrackingRequestDto trackingRequestDto, @RequestParam Long orderId){
        return new ResponseEntity<>(trackingService.createDeliveryTracking(orderId,trackingRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping("{trackingId}")
    public ResponseEntity<TrackingResponseDto> updateDeliveryTracking(@PathVariable Long trackingId, @RequestBody TrackingRequestDto trackingRequestDto){
        return new ResponseEntity<>(trackingService.updateDeliveryTracking(trackingId, trackingRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{trackingId}")
    public ResponseEntity<TrackingResponseDto> deleteDeliveryTracking(@PathVariable Long trackingId){
        trackingService.deleteDeliveryTracking(trackingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
