package com.skypro.sharehome.controller;

import com.skypro.sharehome.entity.ShareHome;
import com.skypro.sharehome.service.ShareHomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sharehome")
public class ShareHomeController {

    private final ShareHomeService shareHomeService;

    public ShareHomeController(ShareHomeService shareHomeService) {
        this.shareHomeService = shareHomeService;
    }

    @PostMapping
    public ShareHome addShareHome(@RequestParam String nameShareHome, @RequestParam String addressShareHome, @RequestParam String securityShareHome, @RequestParam String typeAnimal) {
        return shareHomeService.addShareHome(nameShareHome, addressShareHome, typeAnimal, securityShareHome);
    }

    @DeleteMapping("{id}")  //DELETE http://localhost:8080/student/23
    public ResponseEntity<ShareHome> deleteShareHome(@PathVariable Long id) {
        shareHomeService.deleteShareHome(id);
        return ResponseEntity.ok().build();
    }

}
