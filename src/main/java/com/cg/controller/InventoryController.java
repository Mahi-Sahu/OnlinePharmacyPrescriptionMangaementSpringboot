package com.cg.controller;

import com.cg.dto.request.InventoryRequestDto;
import com.cg.dto.response.InventoryResponseDto;
import com.cg.enums.StockStatus;
import com.cg.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventory")
public class InventoryController {
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    InventoryController(ModelMapper modelMapper, InventoryService inventoryService) {
        this.modelMapper = modelMapper;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDto>> getAllInventory() {
        List<InventoryResponseDto> inventoryResponseDtoList=inventoryService.getAllInventory().stream()
                .map(i-> modelMapper.map(i, InventoryResponseDto.class)).toList();
        return new ResponseEntity<>(inventoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("stock-status/{stockStatus}")
    public ResponseEntity<List<InventoryResponseDto>> getInventoryByStockStatus(@PathVariable("stockStatus") StockStatus stockStatus) {
        List<InventoryResponseDto> inventoryResponseDtoList=inventoryService.getInventoryByStockStatus(stockStatus).stream()
                .map(i-> modelMapper.map(i, InventoryResponseDto.class)).toList();
        return new ResponseEntity<>(inventoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("{medicineId}/medicine")
    public ResponseEntity<InventoryResponseDto> getInventoryByMedicineId(@PathVariable("medicineId") Long medicineId) {
        InventoryResponseDto inventory=modelMapper.map(inventoryService.getInventoryByMedicineId(medicineId), InventoryResponseDto.class);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("{inventoryId}")
    public ResponseEntity<InventoryResponseDto> getInventoryByInventoryId(@PathVariable("inventoryId") Long invenntoryId) {
        InventoryResponseDto inventory= modelMapper.map(inventoryService.getInventoryByInventoryId(invenntoryId), InventoryResponseDto.class);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InventoryResponseDto> createInventory(@RequestBody InventoryRequestDto inventoryRequestDto) {
        InventoryResponseDto inventory=inventoryService.createInventory(inventoryRequestDto);
        return new ResponseEntity<>(inventory,HttpStatus.CREATED);
    }

    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponseDto> updateInventory(@PathVariable Long inventoryId,
                                                                @RequestBody InventoryRequestDto inventoryRequestDto) {
        InventoryResponseDto inventory=inventoryService.updateInventory(inventoryId, inventoryRequestDto);
        return new ResponseEntity<>(inventory,HttpStatus.OK);
    }

    @DeleteMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponseDto> deleteInventory(@PathVariable Long inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
