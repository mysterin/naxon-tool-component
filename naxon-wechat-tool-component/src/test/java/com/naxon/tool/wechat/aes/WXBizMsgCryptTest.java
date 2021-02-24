package com.naxon.tool.wechat.aes;

import com.naxon.tool.wechat.WechatUtil;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author linxiaobin
 * @Description
 * @date 2021/2/24 11:34
 */
public class WXBizMsgCryptTest {

    @Test
    public void decryptMsg() throws AesException, IOException, SAXException, ParserConfigurationException, DocumentException {
        String token = "naxon";
        String aesKey = "uDtsDjE5Ddx2idzNBMTKNQ8e9tn45JM21whwmwSNaXe";
        String appId = "wxa130f378f17b1626";
        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, aesKey, appId);
        String signature = "96fb3b3ea8a5ec8384c6d16abe24ab99ac15b4f0";
        String nonce = "296432508";
        String timestamp = "1614137093";
        String encryptMsg = "<xml>\n" +
                "    <ToUserName><![CDATA[gh_bd74d9636d92]]></ToUserName>\n" +
                "    <Encrypt><![CDATA[sjGqGijSdf1OQ7EfS9iwfdCZGpJdRcGUU9NCCqcIppBxeloXUshT4d+Q4tYkpg2XdurtUPUxyV0rGKkslGJQrmdqswYcwslqyVNKbTXWCYyGfln0tVVdPGBsAASlIifxBu01mkCx6pr9i8FNW6pIFBecq6Cbnu068hi1THHlFdft/7fDoTa3GdBfiSdPWmye4eH25rFyvAzetdh7r/K0pUalIbK58b1lyTUPa405Ed08vAJfAYmmrd6HVlptfkOPg063FcXLJGVf8hi71S/zoo8OptmjHJyWyi5/OTkXByQ8bfpnzo0XuSc5YHq00NksBenlyEp6F3S+V/F2A96Unf8CMT8jv2j/QWKIJ4pyawwoY1sNN/O4nE2dkNJTAFuyx0x5M9Ps3M8DS1T/0fAIfaP9phZ2+vymHKkQ7rsSvxv00wabfRncZ6LOsjs6HrQaVo2W+nnW1wao+JkDev4Isg==]]></Encrypt>\n" +
                "</xml>";
        String decryptMsg = wxBizMsgCrypt.decryptMsg(signature, timestamp, nonce, encryptMsg);
        System.out.println(decryptMsg);
        System.out.println(WechatUtil.parseXmlMsg(decryptMsg));
    }
}