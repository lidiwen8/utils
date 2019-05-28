package com.lidiwen.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: hdzt
 * @description: 生成二维码
 * @author: QuanQqqqq
 * @create: 2018-10-18 10:29
 */

public class QRCodeUtil {

    public static void getQRCode(String content, OutputStream os) throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 编码
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 边框大小
        hints.put(EncodeHintType.MARGIN, 0);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bm = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
        MatrixToImageWriter.writeToStream(bm, "png", os);
    }
}