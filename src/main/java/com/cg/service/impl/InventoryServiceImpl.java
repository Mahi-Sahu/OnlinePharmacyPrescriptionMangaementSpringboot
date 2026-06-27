package com.cg.service.impl;

import com.cg.dto.request.InventoryRequestDto;
import com.cg.dto.response.InventoryResponseDto;
import com.cg.entity.Inventory;
import com.cg.entity.Medicine;
import com.cg.entity.User;
import com.cg.enums.StockStatus;
import com.cg.repository.InventoryRepository;
import com.cg.repository.MedicineRepository;
import com.cg.repository.UserRepository;
import com.cg.service.InventoryService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;

    InventoryServiceImpl(InventoryRepository inventoryRepository, ModelMapper modelMapper,
                         MedicineRepository medicineRepository,
                         UserRepository userRepository) {
        this.inventoryRepository = inventoryRepository;
        this.modelMapper = modelMapper;
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Inventory> getAllInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        if(inventoryList.isEmpty()){
            return null;
        }
        return inventoryList;
    }

    @Override
    public List<Inventory> getInventoryByStockStatus(StockStatus stockStatus) {
        List<Inventory> inventoryList = inventoryRepository.findInventoryByStockStatus(stockStatus);
        if(inventoryList.isEmpty()){
            return null;
        }
        return inventoryList;
    }

    @Override
    public Inventory getInventoryByMedicineId(Long medicineId) {
        Optional<Inventory> inventory = inventoryRepository.findInventoryByMedicineId(medicineId);
        if(inventory.isPresent()){
            return inventory.get();
        }else {
            return null;
        }
    }

    @Override
    public Inventory getInventoryByInventoryId(Long inventoryId) {
        Optional<Inventory> inventory=inventoryRepository.findInventoryByInventoryId(inventoryId);
        if(inventory.isPresent()){
            return inventory.get();
        }else  {
            return null;
        }
    }

    @Override
    public InventoryResponseDto createInventory(InventoryRequestDto inventoryRequestDto) {
        Medicine medicine=medicineRepository.findById(inventoryRequestDto.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
        User updatedBy=userRepository.findById(inventoryRequestDto.getUpdatedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Inventory inventory=new Inventory();
        inventory.setMedicine(medicine);
        inventory.setUpdatedBy(updatedBy);
        inventory.setStockStatus(inventoryRequestDto.getStockStatus());
        inventory.setAvailableQuantity(inventoryRequestDto.getAvailableQuantity());
        inventory.setReorderLevel(inventoryRequestDto.getReorderLevel());
        inventory.setReservedQuantity(inventoryRequestDto.getReservedQuantity());
        inventory.setLastUpdatedAt(LocalDateTime.now());

        return modelMapper.map(inventoryRepository.saveAndFlush(inventory), InventoryResponseDto.class);
    }

    @Override
    public InventoryResponseDto updateInventory(Long inventoryId, InventoryRequestDto inventoryRequestDto) {
        Medicine medicine=medicineRepository.findById(inventoryRequestDto.getMedicineId())
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
        User updatedBy=userRepository.findById(inventoryRequestDto.getUpdatedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Inventory inventory=getInventoryByInventoryId(inventoryId);
        inventory.setStockStatus(inventoryRequestDto.getStockStatus());
        inventory.setAvailableQuantity(inventoryRequestDto.getAvailableQuantity());
        inventory.setReservedQuantity(inventoryRequestDto.getReservedQuantity());
        inventory.setLastUpdatedAt(LocalDateTime.now());
        inventory.setUpdatedBy(updatedBy);
        inventory.setMedicine(medicine);
        inventory.setReorderLevel(inventoryRequestDto.getReorderLevel());

        return modelMapper.map(inventoryRepository.saveAndFlush(inventory), InventoryResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteInventory(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        Medicine medicine = inventory.getMedicine();

        if (medicine != null) {
            medicine.setInventory(null);
        }

        inventory.setMedicine(null);

        inventoryRepository.delete(inventory);
    }
}
