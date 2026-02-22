package com.tekstil.textile_management_system.controller;

import com.tekstil.textile_management_system.entity.Model;
import com.tekstil.textile_management_system.entity.ModelAccessories;
import com.tekstil.textile_management_system.service.ModelAccessoriesService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/{accessories}")
@AllArgsConstructor
public class ModelAccessoiresController {

    private final ModelAccessoriesService modelAccessoriesService;

    @PostMapping()
    public ResponseEntity <ModelAccessories> createAccessory(@RequestBody ModelAccessories modelAccessories){
           ModelAccessories accessory = modelAccessoriesService.createAccessory(modelAccessories);
           return ResponseEntity.status(HttpStatus.CREATED).body(accessory);
        }

    @GetMapping()
    public ResponseEntity <List<ModelAccessories>> findAllAccessories(){
        List<ModelAccessories> accessories = modelAccessoriesService.findAllAccessories();
        return ResponseEntity.ok(accessories);
    }
    @GetMapping("/supplier/{supplier}")
    public ResponseEntity<List<ModelAccessories>> findBySupplier(@PathVariable String supplier){
        List<ModelAccessories> accessories = modelAccessoriesService.findBySupplier(supplier);
        return ResponseEntity.ok(accessories);
    }
    @GetMapping("/total-required/{totalRequired}")
    private ResponseEntity<List<ModelAccessories>> findByTotalRequired(@PathVariable Integer totalRequired){
        List<ModelAccessories> accessories = modelAccessoriesService.findByTotalRequired(totalRequired);
        return ResponseEntity.ok(accessories);
    }
    @GetMapping("/quantity-perUnit/{quantityPerUnit}")
    public ResponseEntity<List<ModelAccessories>> findAllByOrderByQuantityPerUnitAsc(@PathVariable Integer quantityPerUnit){
        List<ModelAccessories> accessories = modelAccessoriesService.findAllByOrderByQuantityPerUnitAsc(quantityPerUnit);
        return ResponseEntity.ok(accessories);
    }

    @GetMapping("/color/{color}")
        public ResponseEntity<List<ModelAccessories>> findByColor(@PathVariable String color) {
        List<ModelAccessories> accessories = modelAccessoriesService.findByColor(color);
        return ResponseEntity.ok(accessories);
    }
    @GetMapping("/accessory-type/{accessoryType}")
    public ResponseEntity<List<ModelAccessories>> findByAccessoryType(@PathVariable String accessoryType){
        List<ModelAccessories> accessories = modelAccessoriesService.findByAccessoryType(accessoryType);
        return ResponseEntity.ok(accessories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelAccessories> updateAccessory(@PathVariable Long id, @RequestBody ModelAccessories modelAccessories){
            ModelAccessories updated = modelAccessoriesService.updateAccessory(id,modelAccessories);
                return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccessory(@PathVariable Long id){
         modelAccessoriesService.deleteById(id);
         return ResponseEntity.noContent().build();
    }


}
