package com.dcd.fileop.dao;

public  class DecompressRequest {
    private String compressedData;
    private int originalLength;

    // Getters and setters
    public String getCompressedData() {
        return compressedData;
    }

    public void setCompressedData(String compressedData) {
        this.compressedData = compressedData;
    }

    public int getOriginalLength() {
        return originalLength;
    }

    public void setOriginalLength(int originalLength) {
        this.originalLength = originalLength;
    }
}
