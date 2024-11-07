package com.dcd.fileop.service;

import java.io.IOException;

import org.anarres.lzo.LzoAlgorithm;
import org.anarres.lzo.LzoCompressor;
import org.anarres.lzo.LzoDecompressor;
import org.anarres.lzo.LzoLibrary;
import org.anarres.lzo.lzo_uintp;
import org.springframework.stereotype.Service;

@Service
public class CompressionService {

    private final LzoCompressor compressor = LzoLibrary.getInstance().newCompressor(LzoAlgorithm.LZO1X, null);
    private final LzoDecompressor decompressor = LzoLibrary.getInstance().newDecompressor(LzoAlgorithm.LZO1X, null);

    public byte[] compress(byte[] data) throws IOException {

        System.out.println(data.length);

        int estimatedCompressedLength = data.length + (data.length / 4);
        byte[] compressedData = new byte[estimatedCompressedLength];

        lzo_uintp compressedLength = new lzo_uintp();
        compressor.compress(data, 0, data.length, compressedData, 0, compressedLength);

        byte[] result = new byte[compressedLength.value];
        System.arraycopy(compressedData, 0, result, 0, compressedLength.value);
        return result;
    }

    public byte[] decompress(byte[] compressedData, int originalLength) throws IOException {
        byte[] decompressedData = new byte[originalLength];

        lzo_uintp decompressedLength = new lzo_uintp();

        try {
            decompressor.decompress(compressedData, 0, compressedData.length, decompressedData, 0, decompressedLength);

            if (decompressedLength.value != originalLength) {
                throw new IOException("Decompressed length does not match original length. Actual: "
                        + decompressedLength.value + ", Expected: " + originalLength);
            }

        } catch (IOException e) {
            throw new IOException("Decompression failed: " + e.getMessage(), e);
        }
        return decompressedData;
    }

}
