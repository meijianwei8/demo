package com.example.demo.util;

import cn.org.bjca.client.exceptions.*;
import cn.org.bjca.client.security.SecurityEngineDeal;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 * @author meijianwei
 * @ClassName: CAUtil
 * @Description: CA工具类
 * @Copyright: Copyright (c) 2017
 * @Company:成都信通网易医疗科技发展有限公司
 * @date 2019/6/24
 */
public final class CAUtil {
    private static SecurityEngineDeal dsvsSed = null;

    private static SecurityEngineDeal tssSed = null;

    private CAUtil() {
    }


    private static synchronized String getPropertiesPath() {
        String currentPath = System.getProperty("user.dir");
        InputStream propertiesInputStream = CAUtil.class.getClassLoader().getResourceAsStream("SVSClient.properties");
        if (propertiesInputStream == null) {
            throw new RuntimeException("CA SVSClient.properties file dose not exist");
        }
        Path bjcaPath = Paths.get(currentPath, "bjca","SVSClient.properties");
        try {
            if (!Files.exists(bjcaPath.getParent())) {
                Files.createDirectory(bjcaPath.getParent());
            }
            Files.copy(propertiesInputStream,bjcaPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bjcaPath.getParent().toString();
    }

    //获取并验证DSVS地址
    private static SecurityEngineDeal getDsvsSed() throws ApplicationNotFoundException, InitException, SVSConnectException {
        if (dsvsSed == null) {
            synchronized (CAUtil.class) {
                if (dsvsSed == null) {
                    SecurityEngineDeal.setProfilePath(getPropertiesPath());
                    dsvsSed = SecurityEngineDeal.getInstance("SVSDefault");
                }
            }
        }
        return dsvsSed;
    }

    //获取并验证TSS地址
    private static SecurityEngineDeal getTssSed() throws ApplicationNotFoundException, InitException, SVSConnectException {
        if (tssSed == null) {
            synchronized (CAUtil.class) {
                SecurityEngineDeal.setProfilePath(getPropertiesPath());
                tssSed = SecurityEngineDeal.getInstance("TSSDefault");
            }
        }
        return tssSed;
    }

    public static String createTS(String dataVal) throws ApplicationNotFoundException, InitException, SVSConnectException, ParameterTooLongException, ParameterInvalidException {
        tssSed = getTssSed();
        byte[] bytes = dataVal.getBytes();

        String tSRequest = tssSed.createTSRequest(bytes, false);

        return tssSed.createTS(tSRequest);

    }

    public static int verifyTS(String tssData, String dataVal) throws ApplicationNotFoundException, InitException, SVSConnectException, ParameterTooLongException, ParameterInvalidException {
        tssSed = getTssSed();
        byte[] bytes = null;
        if ((dataVal != null) && (dataVal != "")) {
            bytes = dataVal.getBytes();
        }
        return tssSed.verifyTS(tssData, bytes);
    }

    //数据签名
    public static String signData(String inData) throws ApplicationNotFoundException, InitException, SVSConnectException, ParameterTooLongException {
        dsvsSed = getDsvsSed();
        return dsvsSed.signData(inData);
    }

    public static String signData(byte[] inData) throws ApplicationNotFoundException, InitException, SVSConnectException, ParameterTooLongException {
        dsvsSed = getDsvsSed();
        return dsvsSed.signData(inData);
    }

    //验证数字签名, 格式为 Pkcs1
 /*  base64EncodeCert：[IN] base64 编码的签名证书。
   inData：[IN] 待验证的原文。
   signValue：[IN] 签名值。*/
    public static boolean verifySignedData(String cert, String dataVal, String signData) throws ApplicationNotFoundException, InitException, SVSConnectException, UnkownException, ParameterInvalidException, ParameterTooLongException {
        dsvsSed = getDsvsSed();
        return dsvsSed.verifySignedData(cert, dataVal, signData);

    }

    public static boolean verifySignedFile(String cert, String filePath, String signfile) throws ParameterTooLongException, SVSConnectException, ApplicationNotFoundException, InitException, ParameterInvalidException, UnkownException {
        dsvsSed = getDsvsSed();

        return dsvsSed.verifySignedFile(cert, filePath, signfile);

    }

    public static String getServerCertificate() throws ApplicationNotFoundException, InitException, SVSConnectException {
        dsvsSed = getDsvsSed();
        return dsvsSed.getServerCertificate();

    }

    public static String getCertInfo(String cert, int type) throws ApplicationNotFoundException, InitException, SVSConnectException, ParameterInvalidException, ParameterOutRangeException, ParameterTooLongException {
        dsvsSed = getDsvsSed();

        return dsvsSed.getCertInfo(cert, type);

    }

    public static String getCertInfoByOid(String cert, String oid) throws ApplicationNotFoundException, InitException, SVSConnectException, ParameterInvalidException, ParameterOutRangeException, ParameterTooLongException {
        dsvsSed = getDsvsSed();
        return dsvsSed.getCertInfoByOid(cert, oid);

    }

    public static int validateCert(String cert) throws ApplicationNotFoundException, InitException, SVSConnectException, ParameterInvalidException, ParameterOutRangeException, ParameterTooLongException {
        dsvsSed = getDsvsSed();
        return dsvsSed.validateCert(cert);

    }

    public static String genRandom(int len) throws ApplicationNotFoundException, InitException, SVSConnectException, ParameterOutRangeException {
        dsvsSed = getDsvsSed();
        return dsvsSed.genRandom(len);
    }

    public static void test(){
        try {
            System.out.println(System.getProperty("user.dir"));
            System.out.println(getDsvsSed());
            System.out.println("22222222");
            System.out.println(getTssSed());
        } catch (ApplicationNotFoundException e) {
            e.printStackTrace();
        } catch (InitException e) {
            e.printStackTrace();
        } catch (SVSConnectException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ApplicationNotFoundException, InitException, SVSConnectException, UnsupportedEncodingException {
//        System.out.println(DateUtils.getCurrentTimeInString());
//        System.out.println(DateUtils.str2Date(DateUtils.getCurrentTimeInString()));
        System.out.println(getDsvsSed());
        System.out.println("22222222");
        System.out.println(getTssSed());
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(getPropertiesPath());
    }
}