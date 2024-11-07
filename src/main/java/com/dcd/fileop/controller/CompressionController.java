package com.dcd.fileop.controller;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcd.fileop.dao.DecompressRequest;
import com.dcd.fileop.service.CompressionService;

@RestController
@RequestMapping("/file")
public class CompressionController {

    @Autowired
    private CompressionService compressionService;

    @PostMapping("/compress")
    public String compress(@RequestBody String data) throws IOException {
        byte[] compressedData = compressionService.compress(data.getBytes());
        return Base64.getEncoder().encodeToString(compressedData);
    }

    @PostMapping("/decompress")
    public String decompress(@RequestBody DecompressRequest request) throws IOException {
        byte[] compressedData = Base64.getDecoder().decode(request.getCompressedData());
        byte[] decompressedData = compressionService.decompress(compressedData, request.getOriginalLength());
        return new String(decompressedData);
    }
    
}