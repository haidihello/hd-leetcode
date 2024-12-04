package com.leetcode.editor.java.baiwang.openplat;


import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.leetcode.editor.java.baiwang.FileToBase64Converter;
import com.leetcode.editor.java.baiwang.HttpClientUtil;
import com.leetcode.editor.util.ReadExcelUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;

/**
 * 生产环境请求示例
 *
 * @author HaiDi
 * @since 2023-07-07 14:26
 */
public class OpenPrd {
    /**
     * 接入参数  请填写实际测试参数
     */
//    private static String url = "https://open.yinshuitong.com/api";
    private static String url = "https://open.yinshuitong.com/api";
    private static String appKey = "10000892";
    private static String appSecret = "7d7c6e14-3fe1-42db-8d93-a6539f8f7d4b";
    private static String userName = "27169189";
    private static String password = "a12345";
    private static String orgNo = "44ad8fcde176b454636e";
    private static String version = "3.0";
    /**
     * 访问客户端 注意修改缓存处理
     */
    public static FinanceHttpClient financeHttpClient = new FinanceHttpClient(url, appKey, appSecret, userName, password, version);

    public static void main(String[] args) {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91420102MA4K39R136");
        param.put("creditCode", "91420102MA4K39R136");
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.tradeLoopReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环：" + url);
        System.out.println("关联交易求环：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环：" + jsonObject.toJSONString());


    }

    /**
     * 发票核验接口 返回response对象
     *
     * @param
     * @return
     */
    @Test
    public void invoiceCheck() {
        Map param = new HashMap();
        param.put("orgNo", "44ad8fcde176b454636e");
        param.put("taxNo", "91440604325112561B");
        param.put("creditCode", "91440604325112561B");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.tradeLoopReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环：" + url);
        System.out.println("关联交易求环：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环：" + jsonObject.toJSONString());
    }

    /**
     * 统一信用报告
     */
    @Test
    public void creditReport() throws IOException {
        Map param = new HashMap();
        param.put("requestId", "44ad8fcde176b454636e44444");
        param.put("orgNo", "44ad8fcde176b454636e");
        param.put("taxNo", "91110113MA01DFUN91");
        param.put("creditCode", "91110113MA01DFUN91");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.creditReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("统一信用报告：" + url);
        System.out.println("统一信用报告：" + body);
        // 创建URL对象
        URL obj = new URL(url);

        // 打开HTTP连接
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        // 设置请求方法
        conn.setRequestMethod("POST");

        // 设置请求头
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("z-real-ip", "192.168.126.13");

        // 请求体内容
        String requestBody = body;

        // 启用输出流，将请求体内容写入请求
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(requestBody.getBytes());
        os.flush();
        os.close();

        // 发起请求
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // 读取响应内容
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // 打印响应内容
        System.out.println("Response: " + response.toString());
//        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
//        System.out.println("统一信用报告："+jsonObject.toJSONString());

    }

    /**
     * 发票核验接口 返回response对象
     *
     * @param
     * @return
     */
    @Test
    public void VINmonitor() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91420102MA4K39R136");
        param.put("creditCode", "91420102MA4K39R136");

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.vehicle.batchImportMonitorVin";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("VIN码监控：" + url);
        System.out.println("VIN码监控：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("VIN码监控返回：" + jsonObject.toJSONString());
    }

    /**
     * 单张核验
     */
    @Test
    public void single() {
        Map param = new HashMap();
        param.put("orgNo", "44ad8fcde176b454636e");
        Map inMap = new HashMap();
        inMap.put("billingDate", "2022-11-23");
        inMap.put("totalAmount", "130.74");
        inMap.put("invoiceNumber", "55663678");
        inMap.put("invoiceCode", "011002200411");
        inMap.put("checkCode", "274466");
        param.put("invoice", inMap);

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.invoiceCheckv2";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("调用生产核验：" + url);
        System.out.println("调用生产核验：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("调用生产核验：" + jsonObject.toJSONString());
    }

    /**
     * 价税指数 分页查询
     */
    @Test
    public void priceIndex() {
        Map param = new HashMap();
        param.put("orgNo", "44ad8fcde176b454636e");
        param.put("invoiceMonth", "202310");
        param.put("userSign", "123445");
        param.put("pageNum", 1);
        param.put("pageSize", 2);
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.vehicle.carAssoQueryPriceIndex";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("查询税局状态url：" + url);
        System.out.println("查询税局状态body：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("查询税局状态：" + jsonObject.toJSONString());
    }

    @Test
    public void xwCreditresult() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("taxNo", "91450900697633085P");
        param.put("creditCode", "91450900697633085P");
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.report.xwCreditReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("查询税局状态url：" + url);
        System.out.println("查询税局状态body：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("查询税局状态：" + jsonObject.toJSONString());
    }

    @Test
    public void checkV4() {
        Map newMap = new HashMap();
        newMap.put("billingDate", "2022-11-23");
        newMap.put("totalAmount", "259.81");
        newMap.put("invoiceNumber", "55663678");
        newMap.put("invoiceCode", "011002200411");
        newMap.put("checkCode", "474466");
        Map param = new HashMap();
        param.put("orgNo", "44ad8fcde176b454636e");
        param.put("invoice", newMap);
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.invoiceCheckv4";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("资产核验：" + url);
        System.out.println("资产核验：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("资产核验：" + jsonObject.toJSONString());
    }

    /**
     * 全电发票
     */
    @Test
    public void checkV4alldian() {
        Map newMap = new HashMap();
        newMap.put("billingDate", "2023-12-05");
        newMap.put("totalAmount", "873282.63");
        newMap.put("invoiceNumber", "23332000000068236285");
        newMap.put("invoiceCode", "");
        Map param = new HashMap();
        param.put("orgNo", "44ad8fcde176b454636e");
        param.put("invoice", newMap);
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.invoiceCheckv4";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("资产核验：" + url);
        System.out.println("资产核验：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("资产核验：" + jsonObject.toJSONString());
    }

    @Test
    public void creatReport() {
        Map param = new HashMap();
        param.put("orgNo", "44ad8fcde176b454636e");
        param.put("taxNo", "91420102MA4K39R136");
        param.put("creditCode", "91420102MA4K39R136");
        param.put("reportType", "winLending.finance.report.tradeLoopReport");
        param.put("serialNo", System.currentTimeMillis());

        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.notify.createReport";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环：" + url);
        System.out.println("关联交易求环：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环：" + jsonObject.toJSONString());


    }

    @Test
    public void ocrcheckv4() {
        Map param = new HashMap();
        param.put("orgNo", "4b9f82fa3273f1b94a96");
        param.put("invoiceType", "");
        param.put("invoiceImgData", "UEsDBBQAAAAIACZNIVHhRxI5HgIAAMgEAAAHAAAAT0ZELnhtbJWUy27TQBSG930Ka/axz4wdjx0lrkqjiIsAiaSwRK49TSLFnsiXpt21G8gCUSpVsCmiBVWqQBQkKqEEHifXt2BqhwQIINcLW+fM/39z5nhmiqs7XkvaZkHY5H4JYRmQxHyHu02/XkIbtUrOQKvWSpFvuYX7lbIk1H5YEFEJNaKoXVCUTqcjizhsM0fmQV0hgHUklblT222zEhImJD1c8DGyEpgQ3ODu7jy45W/xRVC2VIduEkej+qaLNQzYdqnuqJQ6JoCh6mpRWWgT21ocNXhgrTeavi3V7J1UMMsmivWA2ZEoo2xHzCJAIAdmDnAq/G0wlcdhxD0R2+GfCeme7Ym1Rcxrt4Q+N+sfskT/ZAIyaITMsHPPPxi+mHWb5doBd2OHBciqxh3WalV44FXLdzJCfrqXC9FBzcgYHhyOz84H396NT/aRBaoKhACAiq8HGB58TQHpg7OaX3QnF6fj8+fTt69F9ZgYqkyvZ54+PUzMhk5NKtN8VvP3PVH26NXZ6PgNSrbFsHcJ5ui4C1hkM1JGJ6fT98+SlWMMeUOiugpUAs0AQ8prOHMXJ5cfB73Po5e9cf+LaMeg3598ejLsfhB9RZaJr34LJtQ084BFpGekTo/2hkcX/6WCeneN3KSPNrT87WWqsnQilL8d3AecR5b4PgZFvGOP+ZEsroy5OBlPxNVmXWz9OGDhTH+VCJVFeuH7RToHpXeHMruWrJUfUEsDBBQAAAAIACZNIVGaaMAomAAAANQAAAAaAAAARG9jXzAvU2lnbnMvU2lnbmF0dXJlcy54bWxVjkELgjAcxe9+ivG/t//0ECFuQkgg1Kk6h2xrDmoTZ+nHb1oQXh68x/s9XlFOzwd56z5Y7ziklAHRTnplneFwvRw2OyhFUvi7ys/WuGZ49TqQCLmQx5BDOwxdjjiOI40+dFpS3xvMWLoFsXCnZprRWomswHWw3iV1xSEDsm+CPnrJASsvbwznQlj0Z5Y2jScAxXfy/00kH1BLAwQUAAAACAAmTSFRMluT87MDAACACQAAIAAAAERvY18wL1NpZ25zL1NpZ25fMC9TaWduYXR1cmUueG1srZZbc6o6FMff96dweHUOV290qnvkVq2IqKjYlw5CxCAkyEWET39ArafdY2efGfuyyApZyY//YiV5/n0K/NoRRDHEqEswJE3UALKxA5HbJRaG8k+H+N379Yy3ztMcushK0gjUyhgUP5V9XWKXJOETRWVZRpZ+HAKbxJFLsTTTInq3MOAM0RZffD3CR+iAqPbR0KwAdAkXBxZE7yAGlk/Ulh9EbEUk4iC0UP4xiKB6X4HGINlhp8eQLMk0WyRDM3ybZMgmzTxT9wZ+jZasBBgwAD2WZmmap8vwBsNwZKfRePsj/jb0PMMMbEFUqgXimrgD9v4ye6XiV44GzRB/RNQU6IPS6xKUhO13muojhJOY0i0XfHhWUipAllpfg89rLC0/BT1rm/Hq1B/kFFTCtXtQTDYWlitZDf2wbg3TeuoIOMuMIhl2L5/wKfjScUP5G5mebnxoz0B8H4XpJ+u4ngmSmo6ZPJ9xk0bBy4bQLJQjH8aGvnDelsjmOBQ/jFLaNAAo+RamYY5XfXuzbs3QShiCviCP7DVqA8gfuFG4nOfuK2z2JUMc7x/XpUzVLWEiRkkJdp9KSU0DsW2hrvNFHdb5iNmKUqRJ7qoz6DimpO2lgBvyHvYWD1MZoR9X5m9Mksx6png02tOjrG40PAemmBzN9ta1M6++iVAkhZw01NLO+sfSdp8EmU3EiVPc9PLIc14Px5HxxjWXu90LShEfmn5Ln6enQlAV+XF1LDemxDROcFA17wN1Np1i7/AbrY5Xq5x/gcvGzDHbWMin9kJvaAveZIWDLFGz8Q8DfVPsQWv3onkY+SAPm5rOzAe8Yu/QQCleda0vTsZvjXSw7S+Zx4u9nySWvYuvzypp34ikr/LUWzsbTVKCmVJXXheNAxOfeC6S+WChtF2Hnrai5NgXHy/7stwpGFSF1qFJb8Pe2YT05Xqn6AcHS1bdHxf6DArCYL+awGOinVpqmBWFkeyB42Y/JhGOoAuR5b9DdMTQBvd14ppbL9nHlt83LMqDIVTbBWhM3wB2zEw8WK6erqAq5HY8fZzscob8d3h8k7v0BCat5skYIFEwRmr8spzj8YRhArllItHnRi/pdBDslVVBP8A0UaRvfh3dmKlwG4xlWqiL7EoO4rY9mU5UuiWkSy0ZDQJG9sIdxeP/sfxXP76e7YkVhGcdakOpPJCJWrVPn7nKtoBT5FhReZ/gaZKma53KcOcmW9nqfkHdu7pc/AvIVfOqKz7bq3N9TzpW8nmSz/S360Tv179QSwMEFAAAAAgAJk0hUV/+dpJ/DgAA9hgAACIAAABEb2NfMC9TaWducy9TaWduXzAvU2lnbmVkVmFsdWUuZGF07ZgHVFPZ1oDTi4aaEOlVpMNNQlcUMXTBWEAEkRJCk04M5T0GCRqKUUGCoqDSFBVhKFIEASkDCIoIgg4qNgI2RIqDIOp/ERv6Zt78/5r/rfXWmpuVe+7e99y9zz73nH3udwC2+ATAxvNhUARYJABsQT4gRISZbwYVRJTlBru11vZEQQoFIJPIwMJBAthIcRgUjlvOz6zjF1eOnDnAT84Z3l3LL93NL44bLs/lV7MXlKAGBoUCbEQFgo0oBcsi8L8pGw6DwmBoKhwCgSwrAnAojHqc9HUHaPwuJARwIi0FsCi4AwKFh62zJ0kBEvPCUpzoj85ImoD6/E04TmlBPcKr4BelPPk5buji6ZHinJEUzlB1Eb8hXnWzHVkNkF0mQDIikQDDhTCclwlQdMFgSJ9EIK5ysWtRQHhewOCwwxVHh0tPjmTFfdahcdgvkZK0AI153ZI/1x8kBUBuISJxfn0c2Er+Ie5IQepw8m7+wVR+aTo/f89ni3+yh4FtAB6FVudYdZnAoJ/7kq0FN4MgnrRP71JsuijlA6WsrhGuK7ttE/JLc4HnESXTN3uS2Nzy9uXxOEbcDTKS+6Fo1x5RNUe1sfeDlQo8LmNqcxx24nIeG5YPsGHZgCDYHhkhKPQDAgGHwd+A7wyU8fMyDIB8DEhGCSEOEHcTqkuOuaOfIatKqkY6zVgktSNirHJAZr6CIIKIIFzTy65bcbto7tkOxARcz+MJioKfBKgoLNh8zrQAFCuEMAD0QHeYBQUcBoNBdn8jIz7KaBRSQxAOQwNLvtxAA3Fz817kEHFTQNw4EJ0dmc3iMH2ZzBBjHR2mR6Sfh3aQX5CPZ7A23dcvyAPUaPsEs7TpQcZkiqGRDj3IhB4WoKsLUDSDdy1cfrrQpJvQg9bQGWFMP28/ugeTsYnBCgZLv+Cg9X7hTE31NZ4e4Yw1Xw0A4dmh2cGcwAAvj6++/9+dWqAwGigoEomGQhHG4CDX/ywDUI76n+kGkr6eoaEOoIRaAj6HgJ5d54XEIMRweICi9XHW65HmpwrFwEiXvKgS9vcqiX6tBGYTBA6mRwJHAlLdcSkahiCAM/rHoUxcuA1HCOCWkuazzqecI4vCuHOsoJw367AIUZzw/KQFg144jPQoi3MI3BICUGHyMkcP+D2rUJ5Y68WuLdg4nsA8Wx+s3v7W66WRQyJUsaoMJi8xnukbz1hNKxqItpEZkLbUZXazmRPMiKse9kLdFg7LxYXIwKKU8UVhQNL/oiBTAD2y4ScFwMZyifBgby8EGxtDs4UjCGCSw0Dy96XRnjH/sfUZKOVAIRBRsKQG090AHfC8K5ARxNSODAxgpNzxbwZwnLFJgsX0vuMAC5PUOQCoBgCt0a6rbtkKJDl4Rljfr7cPf/K0qXX21IfyfGa/o/LM1oo+X3KiGYoqkSQVmIxzXDMmpAM8Xpscm7nc8uLPvNAWF67dJV5j/0VXM79e3ZyDo/ZXhJ+zWzWjYbLCYWTtknPdQ01J9pItG24Uc1mdWW0H1eocuxzccmnBFZLcvGoTJy1zo7bDlCYr6ZnJ2bubtdNMEgbjo6++r7o54bJtdV2/5lSzwAE1GW7K87WnUaN2zV5H3zrAji4tXE3zDlHPvVfnzYxrndFiyUFNytE0OW7hcKPI4p6RatGYawIlWbBnxL70DG2XZ4AffRMj3A2Y7xyHAxsFG02FOfePe5LHajCK+/sQCqIiaoLdN8+sqCl/GsQLN4ynRIZJhQreiaYFf5j2ea9DwWn/iu3iZfLSWypvmcmLTa52SGq5x+qXdgf8cTMl0+vID4rTx2WsVzkXxigk576SlrIrbYtSNC5iyXlISziHad7YH3ITcgZHceznbaE7rVdmiXRcSiYfelO/Zsd1qWT/Fe9Zff5NKl0GxslnX85WpJcmlNT5+ywdoU/b+187NoH1OSamphFKm/4g0eDg1Cq0OGjpbNNDakgIZAkRDPxr0B4+jPCPZ1BYFxzE/DQ0fslw2SllSWi7P6nGibnu+3IZXY2qzzEzg7PuTIUGBgZWnPMasguYVlMj2Mg5z2Yl7MgWVkXYKCDMGuxkYve/92FVVFVjW8ryC24cvXfUqMFo5Gosa7Lr11v4CcPMk1yf3p+a1Gyt9E0y9rf2Nv80cWWwK/DIXYm7A1kxpIHIFFlvxMENG/RfVZ2U5s+MW/wcER1tM/5hyHBldvmMxwYg6FCjltkj24iRbZLvebFy0sdPcoV6roSw9N33iFxJdM+sC9oaWXvrnyb1l/Qyh3/VDcZF6JhcHx7KG/1J8VDTdtYLhs9M21s3b3TUi3SsRJesneXTuUsnnc7hxre4NOkx7Fc+xcjebnzyjqrsFTFnx4GO87babH18WGmMq5JW9KuXvsrJ02mWD9cWPcRUmz9/GRoNybOPPj7dlFzpncdd9UKlSiJ9h9Vz/ERURhK2MNCsstz6xGocKo9XcYwnsK7mcE0qTvvEu/DxR3z+pJzOT29Gr6XdWhZrotatteJ89dSA++AvnbpR2GeGpbz4TQ8uVEQ0nLwMPe8WCdtqaq1q3Z1ik9JNR25FphVm+/9EoSH5253O2pyx0BgxefPP6blZ0wB8IruAuvGFGT6RznaR3e1bUE2Rzyl6DeQcpFRFy98Waa4gKgt3Vrjro66+jqtmWTTaWjNWml3i4UKF2a7keOa+nMZ9tI2H9rY+S5XfydNTFtVKJ5qGzcweea40brdcOjPoMrVQH79T2cpdZnu5on71BLlpvKum28kE//JyTruUfbp+T63Xq7FCZYJolzNtyxmXQPmuo4lxJJXkqbWFVnJjdZj9epC1GYejmktxNyn51zas0YlDB48DN8Pln245JLxcAdB1a0meqFySmj55ec7Mb1tPiCxDUGKyTfSc+jItyRPOHDdN5dzNNnuSFBSUDUpzMW2etyPFbDokM3yfvlBkaB5Z73mi06cTk3rYSYRts7fZIkJISjt0llNu0Tlxs/REm3woLurxrd0tSn4dPR1nads7U/TvN2yH4fs8c1X3oAhJo6qi9YlY1xMtZ86voqJnU64/d/esPlLcfpw2kE9yD5sZ+dnWPTdzvXR6d/mhY/ZRfebRNISqeuD647kO0+/k8KduXgNSU2zqO2QK0wVCJx49Fc3I6JKQptbnloUPnYFBqsq4aWUO00VZWKFNXqkrBWtSPXINYj/UI1ZojXaEDmRcpkbAkCcsGa0XtOGnAvpiNBKaMnYgbKqXqz1nn4h9PSqaXV2pnWOPVG11RZ7y2tjOM3/8jhZkkwW8fJaZJ8qGLimOtd3d41hC5KvLtkF6L841RjS0Z0YOb9l/uxylaubkY6uqLo2glVQLu8kl+2o4JZRtM714rt9Yo0NUg56yZX9ZsIz7hZTTNXiKTMW7hJjlkSnDlnZkrRxRAubxILXxXOp5lKjgIyJ7ejDyWIy+LN/ywa3XYQYnr9d3DI4VRHder337dvLFuOG18husYwXxb6mc5MYYK361ODodCHDWq+jY5p14+Rcu5YAAIWdrCN9MZ7DUrldYEV5F6dwyeRHICi8mbQ/0v+oiofeb6brq/ni9BNXh85rNrQPq9FEzvX6x+sNuV42dnKVcxh6sNKg8VWmYbrxH4LBSuoGZsMfZy6dUDWVdVBOL7Czu3h/SX4oP4thLPDqDNcizKiIrkvY+bFKcCvHG7CukqjIejnj2K8YqIHfeSRFXPz9ezeyzIMrs5FYCusPbMwhLTuEx+/2zyhIMhgvXN7c2qdPr5vxLqu7N7RbQPj9O8Xu3sWL2QE3kiPDMB7nFSXZ8TFigA5TWgysLGiw3WFAXFtqNS5vkCXvvT2u0TA9X+nfcxvkTTMQ21rWGOa+Nopc7flht6x+/gzcW+dJoe++Nx2EWEdv6VLolBN9BlM4ieSox7a+8TUZJqx7tWHJuPbFXeltVMd5WYrVkuB3G71hr1Xa3U69yjFNUMXRuZaBn4sWEbhUFNIkzMTPseHQn0v6pr5GQ6h3LLWffhwgLZBxa38rmmIts1iPwXm/uia7tTbMhotRXuxi3HXFVmdF451gMwVVNa8v/dsk1X7/+3m8G41HTxiJB+ITfdlZPw2i2UBgB/vsfGZ+PyjjIv/zk+P7575fir89LQn9nYf7exPcL21cTQrB/t8x9b+v79/fVFhP95W3SbJGoeSVi/gdWpGPmJRhUFgYlAESAgFry8dsQhYaBMAqFIOBkbRLIoxYgi5oBbPjwJx6FzvMoseE/zKPAwofjRx4lk78RAcZiz3+CGykAacG/+o+NW2gLf3/JSGE2P7FyqC5rpLx2pPbkH9Ajosva9aUwtCfE2ByFbuouWRGQR3wcqJJvHjp1+kns24TS2jXomgvO7vHqCZW4iagtKgXm7Uem8k/LlBg1xJ4/ug+kx2SQHjl/HT325PgktVHOxAT4QyRbZourDgrhI/8qenz9iR5HgbhnnwluETb9AcGBPPKZ4ADS/4Xg5g38b7Hxr3G65htsBMcPoPMNNir9Yfz/jhcBEolEJgOGFLLB7/Pit5V+4EWSESDymRcxONTCQF7EiMA3x79gxM+bOyQyiKOLGdEKApjD5E0RaStsQsg2rl17przeeSxTq6H1pQrwc69gCNvaVZEVMAXI84fsx1VHaogPEgfaA63H7woKipcEWLk9T6216O1Tck35avWTTSTKZibMnlq2jFrSsM5XFnk2jxoiM2Aq2fugCyPm9/YBaLNZ4al1eOYVmLGkMU9JpFiwkpl1cewWForvl1jeTue2f8RKkGznt6d0SSSKM1wBMqiU32wYcXzytpZkyKU7j2/Iqv0DLZE/NKh1odMIM6SnQFTQWcitm/18gsI/nj8JHsxdYYz5RPn3Jtzfm3B/b8L9vQn3X7UJ90N+FV5TWypJMr6R2aNE7+4VNxu+J56qgrJsn9CK6LK4YZ4G5tdLZPhpZG4kJ8nPnVlcI/ZoInnvpcPuO2pHvIdWonsKrP4HUEsDBBQAAAAIACRNIVFctEn32AAAADUBAAAiAAAARG9jXzAvQW5ub3RzL1BhZ2VfMC9Bbm5vdGF0aW9uLnhtbE3PUUvDMBQF4Pf9inDfTZq5iZYmQ5HBXsZg81liexcD7U1I09b9e6MbZU8598AHJ9Xmp2vZiLF3nhRIXgBDqn3jyCr4OG0fnmGjF5U/N+XBWHwl8ollQ32ZOwXfKYVSiGmaeL77gDX30YplIZ9A/7MrOV0CKjgm0wVgu3cFL4/AjsNXuvbOkklDxB1tXYtZHkw0HaY87C6zfX4VnAPfm+RG/GOgfXTWkWk/HY3e1ViJGei73N/mhIC5ohrZmx+oMfGiYMXXTBYrJuWaLQsQ2c3Tb3n+vV78AlBLAwQUAAAACAAkTSFRFBopcPUAAADPAQAAEwAAAERvY18wL1B1YmxpY1Jlcy54bWxtkUFOwzAQRfc9hTX7xk0RUYniVGpQEQJVCMIBrHRaLCWeyDaEnoElZ2DLlhW3Ac6BKUFKGnb+z35/ZDuZP1Yle0BjFWkBYTABhrqgtdJbAbf5cjyDeTpKaLOOr9Eyf1rb2CcBd87VMedN0wQ+2xqLgMyWTydhBGwhLV5SIcBLkO71jEoyN7Us0B4Cdn4q4BhYvqvRK2cLX6CcvUKTUVWTRu0EzICnCf+3aUnadZb7uukJsJ+wkpXv/Hp5+3h/9kRWqtz1GD8Qo7ArXkiVq773iwbaUVf7fH0azGvZQIy6Ykb3RqFhK2z6dnfj7yHae/P2d9LRN1BLAwQUAAAACAAkTSFRaXrldPwAAADAAQAAFQAAAERvY18wL0RvY3VtZW50UmVzLnhtbI2QQU/CQBCF7/yKydxtS7WkadiSIClpAokR0CNZ2rGsbrvN7mL137uWRiNcPH5v35t9M9PZRy3hnbQRqmE49gIEagpViqZiuNtmNzHO0tFUvZTJIxlw7sYkjhgerW0T3++6znNsWio8pSs/DMYThDk3tFIFQxfCtI8vNO8euOa1uWDIFwzvEFaioWdR2iPDwAujIZYJKe+VVBqeuDyR6xhNIA7hNkLo9U3LCydH6J8DG6vVG/074v/pcslD1/VJWrGmUvAroW8fu7NtP1s3M695RQiZ0jW3DJfzfBkOm/R2tw6l4tu0jwPv9RCeP/x9G/hn/pVgBsVdNh19AVBLAwQUAAAACAAkTSFRY4dfwqIGAACxGgAAHgAAAERvY18wL1BhZ2VzL1BhZ2VfMC9Db250ZW50LnhtbM1Z609URxT/3r9ichP9AGF23jNXZY1gbG0lmlZr7bcVblfalTXL+uqnYhSpD8RCaglE4yuYpsU2FQVsNenfwt2F/6Jn7mMfl4XCZUkLubtnZu6c+ztnzvPugYNXzhfQJa80PFgc6nYoJg7yhvqLA4ND+W7n1MkjXcY5mP3gQPGrgX0ncnkPwe1Dw/tg2O2cK5cv7MtkLl++jGE8fMHrx8VSPsMIVU422HHSO3+hkCt7KCaOHu52mIO+PF4a8ErdTk+u/5t8qXhxaMDJhDt6i0Nlb6gcDo7lrnolZPcoUuN4pXz87NdefzmcB2Y9dn+udBVGLtJIM6Sx0sJ10BFgBrPUQZ8NfusBhTVhJuJ0ZLBQ6C0WiiX0ea5wEVapVMgwxGWMxT6rtzjgoS+6HVDLmW5HYi21dtBhr1DOwWweUYIUlszVTtafH6n88cR/8tD/7s/qi/Hq1IL/68TK4mR16oV/7371+YsDmUau2fowFKi1gKJRQKoEPI4hQZCsicdj8TimWjobYReYGFhuxE4RxdJOZgnnhDFCCKc8FUzZCJMrxAnoEksksdk+UJEESlC0ZW3mRmVszP9tqjJ7d/Xpjcr43Oq1x5XZ79emJ/wbv/j3FlOB10nwMgbPa+BVDF5gxjnfBL1W9uYG9BoBM+FkXWq1TJl2XUkojFQqtCaBllKGdTu1zUJtozxikYHUJuAkYpOpLExXfn9UnR2J7H7xWmXhTWVm2r+z7E+88udfr755abh/7w3tco0WUhtmUsnrrpNXY7eN8pq6dKomXSjU2uTr1cd3QrkqUy+BBh+hhAauojkTSqQRSZOkU1MKR9ger9Z1pw7/aCqItBEiY4gJBLoXeNsIwTiZ5hvEHaW0SwhzlWQ6Fcym+E8ZcGbgoDb8tEGVLA471fnH1YlR//5PqTDypAETsQvuGtts9fb8yuKSf+tnf2zGn39Snbu/+mpxpyFSr0tDjLXNYhmr2QOlRBqkFScaEWGIQVLQdClJy2bTUJgjTpGGaEy2EdU5Vi7VEXIlZTPyILSjLqVxEC63MIy2n4m2E+tSaDPS+rErZAfdWyjvJ118b768n3UoxrW2pMupNHZJ2g97CcIEFbQTtsES6cxw4jLQuWsXjejkXR2WChhZgtGMZly4ItOZ6SS0kzFJgD8jLg/uETwD6aozYA9DDWQ+JGMOOpPqgFTCLwTBcjfyWDKHsUSUb0xda9cf+uPv/WdzayOTq8/fV+YXGKQwIrXogiTGJJR/qWRNlhiUmFhY0SDs1qsMSZXepMogvO8Q+0ifPiXkx6kAm3UpSret8BTJI1lXZ9SPiIEs/tIr4lZmxwitPHieSppkDSHULpSniQpidBbqhaiOiMtWf2zUJpJn17krmLG1hLApGvxvS2IdPQ8tWINcBrJ078k+SLIE2f/wq1FWa2UcLkaCsPepN1y8WOoP+jBD4kbnRK58rpEri7gSLGTANyYaOUsb1VyNIURbmzg2OOSdHhwon7PbYu0dOnu25F0ahMZv4HCunMv2ga4whHYJlop6ogHHNh/aD2oXOHz12ElL0HCV1naEN4arsNtEOwKyJ+ISDELetYnomX3gJDy4jsGaDq5wzlLhnB2F6k/CD2fr2mppa6Yp3SuOXDAy5ILBbdfSZGhJLXN9dfzHlcWblbt3Vt4+Wpt5DUTl9jv/6ZI/O7p6bXR17oe16VEwtjTOYhK5PgDPNWoRpTg2hqhNBGDMQGf8dyqnNXLnOOAsmZWnDqapUo6Wsy5UGhSLVP2YaUpjlAt72tS6HWTxVlBbHzl0AMYWF2fsLWEESak23R48HNsEXYeTaDFcArsMlO8aa5kKZ3OG0fK/1pvbHjxb0Ru07AbuS9WZuSTRmf3/Oh63qXnUIh1ELHcXZFOYk7YYUEiS1i0uw4YxtqEioUlQlNYxQiyh0OXaSifcaUsDSFPxNKtP12bjR3RUl5erb2+uLM/5kyMdjJB85jwcMiF7qu9uVV7O0A5a/etBdXkpldByXQoHqaG3T9HYQ9Dl6wqhWtmmmKGyCxqmVPWy2xxUFQRvC5RucDwbAYVtAcSWaBu6Tr4nFcimSBvWk8rm6G1hhHqSstYYY3yffJgKXnOA5aGJY64JlINmeyBp8Cq0pRrr75t2kg3cRPRVuw12JyEYnLEJLQ0PfgOYDFPbq7XAiYlSIkLKia1T6khtI0GkEci+oAxIHs1Y59JQ0ULfoAzRXLJ0MjQFaTcKgBsJsUm625KLcfbvZhFMBL/5RHTtx6Co7s572Q/+AVBLAwQUAAAACAAkTSFRFL5NZVMFAAB6NwAAHAAAAERvY18wL1RwbHMvVHBsXzAvQ29udGVudC54bWzlm11P21YYx+/7KY58v4PP8Tl+QSTVBkKaxLRKdFu5NMSDbGmMglvaXVGpArbxskq0UzfYYKMD7QWQ2q2DZvsyw0m44ivsOXYKHBsm5mzIjm8Ctuzk/H/5+zn/89jpu37vdgXddWpTZbdaUAhWFeRUx9xSuTpeUN67OfiGqVwvXutzPyz13rDHHQSHV6d6YbOgTHjeZG9Pz/T0NIbtqUlnDLu18R6qEl0pBmf0u1XPqXrhxpB936mhgZo9fcOu2bcLClPQ2wMFRWsffMP2Jt4d/cgZ84LduoLecu9US3btPmyYmCNiYBMZGlIxnDlUrjoflEveREFRMeXt9xgsVyr9bsWtofftyh0HBHEdmRRpXEHB/uFJewx2c6UnPGHYq7kfO//qlDdHR2vO3bLtOaUB27OL7yAVhkTRUDg2ivp6zjss3Hsq8lzRRly02fWizbOiGWimFmgGG6VXdDi4jlRbUdWc5EA1UaOyLZ4H2SQqm6haHnTTmG6aC92arFtDmnh3hqy0aQadKmgWfy3WkWR2VjJVeS5E87OiCT3VTGl6NdPOvC0lM0KsXIg25C+a5UK0GXM3UVkoWk2xaLUj0Vbc3t2vmqpxf+dAtRTLuA6iOQ008xRf1Lyji5pKkcykp5pZiqdp1tE0TaU4ZrFcaGZyHcuHaDmPafkQLQcyPR+i5UBm8K4UfdO5550VLQUyLlplDBEdaRgsMOhWPTgE4stw+RMYkYaByhUKF2Ptd0sOuiWYw8w6AqPBhgbfxoBT8exb7SGh2KtSbKwe+E+3m/Un/vLL4/pXIYzXb1g83fwHNpqcYJhonhqi4dAFePzlR81n24evvm+uP0iKh8TxEK2L6IBzOqBDz6Fjdot76jPAp/Hls8bqt0n5aHE+2So9DJs6ghfjpOSsbxz9uNCBZaR8pWNAQbEJH0GIuFNF00ZFRKORYBC8TWVEDFvjSLwoxdaLXw739xpP9hPBiOQupBEAQjXRJUmfQcTiLu4QAvOpcAcLrpkvFptbe0mtoUdp6KLnnSkaF1QTubI0D543t5cODw5au7P+/E8dTN5GhBijovGQKWIMc4bEeCnn6LT4ru75azN/zTxorvza2l1LyseM8jGwkTE+l3IUzFWN+ZetjQV/+bPWix86cJTcxqMwLVBRoXl2K7S/OwvTlb9wkIQHk+IxDW44g3ksTGDBlDYSlzSQUjza2G9+vRMW60RUpFSsayEVomEDqGCeWS6trYeN9br/zedwASXiIjcJAyoG1qyMQYFrZvHx4R+LiRDIPUPxGI6whopFjOQZqblQOCDpPt47mltOxIBFiqhAYIUEsgGACwDCBK+SXQdyrmVMtN/BBSQ7BHRsQJ2ce3T03VoiApHmoh4SMHDWCiSk1e2l5tJcIghyPDXbsychoqOXFQo6towAQlInSBmUUmQRMAExIYOmNUBomJ9Z31GxrJtv7WwkUi/nSbGgs4IuiB5Eyox44BIZ/HWEgIoJXgmBHdfn/c0tf/bpcf3TJPC43JvlgpkVdiCDlknq6F2uwaYUBZi95Q7AkGgTiajiLnRm1yhHKzP+yk7CLhKnkVUuUTlmKqDI1kr3v+skcS1KBAIoNUMiVIcinBkoV9tQ4iwGjmFDh5UMzZCR/s+WEo+2bMWjToxnrWt7tV0lrp/TVSKqcdJWYhkp2UQFKptzjefbiTBIedhAhLbvRafQNhfcDdIN8ZXpgUfEGnnlt8bPf0LhSWoMM9ZICqFAUrQygyV4ZjtYMW8uNtZ/T8pCjsoqP4GRTodcUHpNYRCz7Y/wHuol/RHsCH7u1v7/5Hdw7Yc8xp3itb8BUEsDBBQAAAAIACRNIVH1rhgnLAEAAIcCAAASAAAARG9jXzAvRG9jdW1lbnQueG1sZVFRa4MwEH7vrwh5n4kyxhBNaSuDwgZl2OeRpZkKmoiJ0/37JaZbYvty3H33ffnuLtl27lrwzQfVSJHDOMIQcMHkpRFVDs/ly8Mz3JJNJr8uaSHZ2HGhgZEIlRooh7XWfYrQNE2RqVXPWSSHCiU4foJkUR1k10lRUE1d/Ubns2j0sSAxTjK0hhZGybu+pZqfaMXBschhAsGeKv4qWQ7LvlXIhA+MDlJoM05kxoHISa1kN/Cr1an+UQ2j7V7OBAMMkhiD+BE707B5RVbiv23fuSJBbt0cPSQ4u/GzbZgt/zPP9k1X357FmiufLnvHwd5LH9l4vzm6eWAnhNRUmx9VZMkVCiA/U8hzQq0pq+1SRrjkRumxQBkQ3S+PSsuupJUiNiBfe1HAWV+QbH4BUEsDBBQAAAAIACRNIVHs+F3WhAAAAMIAAAAZAAAARG9jXzAvVGFncy9DdXN0b21UYWdzLnhtbLOxr8jNUShLLSrOzM+zVTLUM1BSSM1Lzk/JzEu3VQoNcdO1ULK347LJT0uxci4tLsnPDUlML1YAasortgIK2ipllJQUWOnrl5eX6wH5xQWpyXr5Ren6RgaGZkp2qPoUQioLUj1dbJWgEm6ZOak++cl2cAV6QHNt9JGlIDy4AnR+sR0XAFBLAwQUAAAACAAkTSFRfgXOdOkBAAA2CQAAGAAAAERvY18wL1RhZ3MvQ3VzdG9tVGFnLnhtbJWW23KCMBCG7/sUDvct2oOogzhtbTte9Kh9gDSsmhaTDgmOvn0joKIsi70xG5Lvz5L8G/QHq0XUWEKshZJ9p3XRdBoguQqFnPWdz8njeccZBGd+D0ZyqQSHhp0vda/vOIGfP7pXIQS+moa9169v4OYDpo03NgPbWkUnaF/77sFo4LsH6F2yhjhvXtiCFrspixXANJyw1WhIingVIjmZxrdhGE8gonS8doXOjk17j0IyyQWLbjlXiTSkZLdCsizi5vs2hijatyek3e6U1ziCs+5/Em8jiVepZM/rjtq7qlLMyCyuPWwPOewD1N1uYG7KF0XKNSvdvAGfGZ8LWafRKmsUwG1x2KR+SJnL6sLK2G1vDvynrkw9okz3vN2zeyVNrKJaPaRSj+mR1gkMmaGFELsWwM0pSh4lWixhooy12aLOq53yqxKT8ffA17QDD6t/JlO+RIjJiJkr17QDp+ZQ3mJiMlLpx0u9sTWQh9pFyiiHUrdt6pGgkQLaYU9KhXokp0oXYus1AwtSE/N/yox/gYup4MzYjyQpgVjlCLYbNeZzoG++LvJdKYDPwHQS21CaobC/ui4txDS4Rr1TukgxFvx2ggDunp1xYnvfUHyriTkno94TJo0wa5pHvLMH3YJj3KKT3N1foODsD1BLAwQUAAAACAAkTSFRwLUM788AAAAyAQAAHQAAAERvY18wL0F0dGFjaHMvQXR0YWNobWVudHMueG1sZY5dS8MwFIbv9yvCuW+ahipbaDrEURDEG6e3kmVZdyBNRhJW8dd7ZANlXr5fPG+3/pw8O7uUMQYNDRfAXLBxj2HU8LYdqiWs+0UXD3v1UIqxx8mFkhmtQlbkajiWclJ1Pc8zJ51PzvKYxlqK5h76myF72hBESGAvZnIaYsIRg/EfGM4RrQM2xDSZooEAwB6TM4WObUyhshRSVGJViWYrVqptlFgCe8Wvn4jfybYF9o4Zd56Mg/HZXfEDevccbX8L48To6r+Ni/q9+8/I/eIbUEsDBBQAAAAIACRNIVFtPaGrYAEAAH4BAAAWAAAARG9jXzAvUmVzL2ltYWdlXzgwLmpiMpvu5WTEyyXFxcjAwADCDAZgShiIU6AYDBghpBqIwWiELsvAwfz/73+mf//+sWgccqz9WKAUmfte59c9qeU1Tls/Si2MLY14/M29+XTY+XuXfRZz/j4ZdED63/Q7t6PuhQROUNlUHL7701m+Ypfdf5k6FNYx6lyf9cTMycBwQqldgPWF+L1mr8X97KJuMmkKz0l/Vcg/57yXVv3kYnEe94tCyeqLRdXCtHtjpq2YuuzFjNTDv2xEpkx6mKAuuj3dKM/MtmhNf8iT92aKziHsXwt/xC68n+elt35t/o5FE2zmR9S+P8m61ZXltoemyPqAHg1j+xpliUefcixkF33RjrxRzvDJfmpYt1bcBWuWR2cneHCtMP3HYXF34wrNFt14rah76kWfclkitnE/81Nornt64MSz8J6Y0PQKd/s7C6flTXLftehoxsLy/1p3Hbii/f+vAYYRkyE0FBkYmI0hTABQSwMEFAAAAAgAJE0hUWk+96tQBQAAGQoAACIAAABEb2NfMC9BdHRhY2hzL29yaWdpbmFsX2ludm9pY2UueG1sdVZbT9tIFH5H4j8gpPaBhHhmbMd2C67sBNIUAg0E2Dy6zpC4OHZIbAK8UbVdttvtRa3UrUC7arcV0mpFd7W9UFa7/TN1Av9iZzy5OEDzYJ3rN8efzzmZiWubVXtkA9cblutMjsIEGB3BjumWLKc8Oep7q+Py6DV1aAJnnQ3XMvEICXcaV1Zrk6MVz6td4bhms5nApXo14dbLCdPhGmYFVw0Oj1ssg0MAKqMjy9Ej1InV2pW0a2bTKuAnuJ5CzZ2DUm4JEycPEAIA8JCFRZ2R4DlXBewHo2HETIMKW7UOXOjsqSFAo+HjtOFhFQEEgs/vgdLa3wWw9eItQ+r5o7VVsLkWQkAIRFlK8kACggxkUYBnCu1F0vScYVYsh5aVTEoKAEhJikgK4/uusGJjM+U6Xt21Wd1AEcQxeNn2roJx/nLZu4rGkoiXJCoqPBRl6hLpY3iIPgWABCjAGEkkThDjeKAgIEsKdcrDQ0KMHx+jcghGBQQ5CfGCInAxLgZgDCFxeAiQUxBQ+DBK4DmROGgwVSUilpnYxZC44SHG8GD59I10fwvX+9KcUcXq6d691u5u8Ofz1v5PJ7/daz06OLnzqrX/w+nLJ8G9P4LHRyFYP76XTfBJsyiQdgdEkqKQwoiW7MeziF6CVirVC9hWg8Od1t+vg6M7rQ+fWnsvg4fHp3d/CR59Cd4cnO48O3n7pXX4AQWPPwFREsZlSUCiKEl91C5MD3facgzHtAxbM03Xdzw1uL9/8uohO6b3esHu/faT+8Gbu7wiIJn0KBBoqxK++9DnkPou1hG1DYfZmESERWzbHS8TQ5LY2e0fD78efQ4e/B7s7gWHr9sHT0/eH52jNpLWR4mSC/ichq5LK0uCeCOS0WeX6V1eWh9etv76tb2/c57n4Mn74PDjyad3Mk/4heMKYVeUZCRHYKP0Mst5fhmzzz4Slhl26/k7IiPKKgxXhcQjISlEYM9T251rA6r9IadKT0ZRR0eJ8E0JcEzbb1gbuOB6BLoaIitkB8CEkOxOwYVBFGDOpRuF60mRjUHOWFPbh69oyzz9eWCZhC4ae9PYwvhMELNRb7h0SKWD/q61U3+0IgiRzCck2C37bLXENLV5wZvIZItJCUns5l0YRAEydaNWYasMxhGIR/d6vLu54124OF3FQCGWi9ZrXE/pcjw8sg8bIXAxq3U/a9QwoKOzAYg1nVV2DM+vYzWXzep6vjgz406Z23Apo+XzKW09pSu3yeHNrK4VpuZzWjOTSq3r13PKslbMLeh5rZnWzuQUMvr2dkrf1miOfjs3Xd5MbWs39PLcsq4VC9ra3HxuodHM5Ivp5Xx+Nq3P2lWJt+v1W856VrKt9evV8uyG3Shqm7NpbZ3l5XLZGHZv+yszLsZLHl7x1uatjTJeFGs+9oktX/EXhTpe4cpblUKhkK1p5dKU1tQ0rWDDb9Sd7dStl/mlaU1byOSm8qlsZmF+JXULo8Xl1cySXP/OVtIF39PqKbs4vzl3Y9lDt/zkQk5f4W5qlu4WK2VuW/YLNc/Oxfj8egNOL+U2HV666buCZQmF2VI6ZlRiZnNykg1Vj/KwT1y31Mg6q25jUGWfz8NVdax9fNz+5/uvxwfBs50x0iZlropGIACX2v89aL3bg2Ow/e+L9vFn9oFpSvhla9i0Vi3T8MgdREVJJENxPEm2OytiwBv+U2OjQYqqYsdLW+RJry7qTIb9VV/kC6eyTpqJgEsJ0q8AJGWyi0TEhjN00aC8bzie5W2pPGJz0zNQ70WDNTiIi/R6RS4e/KXu1HUsHf83RroLwg3Syp0hneve89T/AVBLAwQUAAAACAAkTSFRkoPt/owAAADDAAAAHAAAAERvY18wL0Fubm90cy9Bbm5vdGF0aW9ucy54bWyzsa/IzVEoSy0qzszPs1Uy1DNQUkjNS85PycxLt1UKDXHTtVCyt+OyyU9LsXLMy8svSSwBKixWAOrKK7YCitoqZZSUFFjp65eXl+sB+cUFqcl6+UXp+kYGhmZKdmCNAYnpqQogwtMFaAVU0C0zJ9UnP9kOJB5voI8wXA9oto0+shIID6QQykRyiR0XAFBLAQIAABQAAAAIACRNIVFctEn32AAAADUBAAAiAAAAAAAAAAAAAAAAAMMVAABEb2NfMC9Bbm5vdHMvUGFnZV8wL0Fubm90YXRpb24ueG1sUEsBAgAAFAAAAAgAJE0hURQaKXD1AAAAzwEAABMAAAAAAAAAAAAAAAAA2xYAAERvY18wL1B1YmxpY1Jlcy54bWxQSwECAAAUAAAACAAkTSFRaXrldPwAAADAAQAAFQAAAAAAAAAAAAAAAAABGAAARG9jXzAvRG9jdW1lbnRSZXMueG1sUEsBAgAAFAAAAAgAJE0hUWOHX8KiBgAAsRoAAB4AAAAAAAAAAAAAAAAAMBkAAERvY18wL1BhZ2VzL1BhZ2VfMC9Db250ZW50LnhtbFBLAQIAABQAAAAIACRNIVEUvk1lUwUAAHo3AAAcAAAAAAAAAAAAAAAAAA4gAABEb2NfMC9UcGxzL1RwbF8wL0NvbnRlbnQueG1sUEsBAgAAFAAAAAgAJE0hUfWuGCcsAQAAhwIAABIAAAAAAAAAAAAAAAAAmyUAAERvY18wL0RvY3VtZW50LnhtbFBLAQIAABQAAAAIACRNIVHs+F3WhAAAAMIAAAAZAAAAAAAAAAAAAAAAAPcmAABEb2NfMC9UYWdzL0N1c3RvbVRhZ3MueG1sUEsBAgAAFAAAAAgAJE0hUX4FznTpAQAANgkAABgAAAAAAAAAAAAAAAAAsicAAERvY18wL1RhZ3MvQ3VzdG9tVGFnLnhtbFBLAQIAABQAAAAIACRNIVHAtQzvzwAAADIBAAAdAAAAAAAAAAAAAAAAANEpAABEb2NfMC9BdHRhY2hzL0F0dGFjaG1lbnRzLnhtbFBLAQIAABQAAAAIACRNIVFtPaGrYAEAAH4BAAAWAAAAAAAAAAAAAAAAANsqAABEb2NfMC9SZXMvaW1hZ2VfODAuamIyUEsBAgAAFAAAAAgAJE0hUWk+96tQBQAAGQoAACIAAAAAAAAAAAAAAAAAbywAAERvY18wL0F0dGFjaHMvb3JpZ2luYWxfaW52b2ljZS54bWxQSwECAAAUAAAACAAmTSFR4UcSOR4CAADIBAAABwAAAAAAAAAAAAAAAAAAAAAAT0ZELnhtbFBLAQIAABQAAAAIACRNIVGSg+3+jAAAAMMAAAAcAAAAAAAAAAAAAAAAAP8xAABEb2NfMC9Bbm5vdHMvQW5ub3RhdGlvbnMueG1sUEsBAhQDFAAAAAgAJk0hUZpowCiYAAAA1AAAABoAAAAAAAAAAAAAALaBQwIAAERvY18wL1NpZ25zL1NpZ25hdHVyZXMueG1sUEsBAhQDFAAAAAgAJk0hUTJbk/OzAwAAgAkAACAAAAAAAAAAAAAAALaBEwMAAERvY18wL1NpZ25zL1NpZ25fMC9TaWduYXR1cmUueG1sUEsBAhQDFAAAAAgAJk0hUV/+dpJ/DgAA9hgAACIAAAAAAAAAAAAAALaBBAcAAERvY18wL1NpZ25zL1NpZ25fMC9TaWduZWRWYWx1ZS5kYXRQSwUGAAAAABAAEAB7BAAAxTIAAAAA");
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.ocrInvoiceCheckv4";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("关联交易求环：" + url);
        System.out.println("关联交易求环：" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("关联交易求环：" + jsonObject.toJSONString());


    }

    /**
     * 遍历本地文件夹并
     */
    @Test
    public void tempOcrcheckv4() {
        List<String> list = FileToBase64Converter.convertFilesToBase64();
//        for (String s : list) {
        Map param = new HashMap();
        param.put("orgNo", "44ad8fcde176b454636e");
        param.put("invoiceType", "");
//            param.put("invoiceImgData", s);
        param.put("invoiceImgData", list.get(0));
        String body = JSON.toJSONString(param);
        String apiName = "winLending.finance.assets.ocrInvoiceCheckv4";
        String url = financeHttpClient.getAPIRequestURL(apiName, body);
        System.out.println("请求url" + url);
        System.out.println("请求体" + body);
        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
        System.out.println("返回值" + jsonObject.toJSONString());
//        }


    }

    /**
     * 调域名
     */
    @Test
    public void ystempOcrcheckv4() throws InterruptedException {
        List<String> list = FileToBase64Converter.convertFilesToBase64();
        String url = "http://open.prd.yinshuitong.com/checkApi/api/serialNet/assets/ocrInvoiceCheck/v4";
        int i = 0;
        List<String> writeList = new ArrayList();
        for (String s : list) {
            Map param = new HashMap();
            param.put("orgNo", "44ad8fcde176b454636e");
            param.put("invoiceType", "");
            param.put("invoiceImgData", s);
            param.put("requestId", "20241028zhd");
            String reqString = JSON.toJSONString(param);
            String response = HttpRequest.post(url).body(reqString).timeout(60000).execute().body();
            System.out.println(response);
            JSONObject jsonObject = JSONObject.parseObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            Map param1 = new HashMap();
            param1.put("invoiceCode", data.getString("invoiceCode"));
            param1.put("invoiceNumber", data.getString("invoiceNumber"));
            param1.put("billingDate", data.getString("billingDate"));
            param1.put("totalAmount", data.getString("amountTax"));
            if (StringUtils.isNotBlank(data.getString("checkCode"))) {
                //截取后六位
                String checkCode = data.getString("checkCode");
                param1.put("checkCode", checkCode.substring(checkCode.length() - 6));
            }
            System.out.println(JSONObject.toJSONString(param1));
            writeList.add(JSON.toJSONString(param1));
            i++;
            Thread.sleep(2000);
        }
        System.out.println("处理结束总数量：" + i);
        writeToFile(writeList, "D:\\测试发票\\param.txt");


    }

    private static void writeToFile(List<String> list, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : list) {
                writer.write(line);
                writer.newLine(); // 添加换行符
            }
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int processDirectory(File dir) {
        int count = 0;
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".png") ||
                name.toLowerCase().endsWith(".jpeg") ||
                name.toLowerCase().endsWith(".pdf"));

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 如果是子目录，递归调用
                    count += processDirectory(file);
                } else {
                    // 处理文件并转换为 Base64
                    String base64 = ReadExcelUtil.encodeImageToBase64(file); // 请确保该方法存在
                    System.out.println("Converted: " + file.getName());
                    count++;
                }
            }
        }
        return count;
    }

    public void processDirectory(File dir, List<String> base64List) {
        File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".jpg") ||
                name.toLowerCase().endsWith(".png") ||
                name.toLowerCase().endsWith(".jpeg") ||
                name.toLowerCase().endsWith(".pdf"));

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 递归处理子目录
                    processDirectory(file, base64List);
                } else {
                    // 处理文件并转换为 Base64
                    try {
                        String base64 = ReadExcelUtil.encodeFileToBase64(file);
                        base64List.add(base64);
                        System.out.println("Converted: " + file.getName());
                    } catch (IOException e) {
                        System.err.println("Error converting file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //图片转base64
    @Test
    public void ocrcheckv4new() {

        String directoryPath = "C:\\Users\\PC\\Desktop\\工作文档\\生产核验一批发票 2024-10-28\\测试发票";
        File dir = new File(directoryPath);

        List<String> base64List = new ArrayList<>();

        if (dir.isDirectory()) {
            processDirectory(dir, base64List);
        } else {
            System.out.println("The specified path is not a directory.");
        }

        // 输出每个文件的 Base64 编码
        for (String base64 : base64List) {
            System.out.println(base64);
        }
        System.out.println("Total files converted: " + base64List.size());
//        File file = new File("C:\\Users\\PC\\Desktop\\temp\\【飞猪】订单5058861830812-机票款凭证 发票.pdf");
//        String base64 = ReadExcelUtil.encodeImageToBase64(file);
//        Map param = new HashMap();
//        param.put("orgNo", "4b9f82fa3273f1b94a96");
//        param.put("invoiceType", "");
//        param.put("invoiceImgData", base64);
//        String body = JSON.toJSONString(param);
//        String apiName = "winLending.finance.assets.ocrInvoiceCheckv4";
//        String url = financeHttpClient.getAPIRequestURL(apiName, body);
//        System.out.println("ocrcheckv4："+url);
//        System.out.println("ocrcheckv4："+body);
//        JSONObject jsonObject = financeHttpClient.executePostJson(url, body);
//        System.out.println("ocrcheckv4："+jsonObject.toJSONString());


    }

    @Test
    public void readTxt() throws IOException, InterruptedException {
        String path = "D:\\测试发票\\param.txt";
        LineIterator it = FileUtils.lineIterator(new File(path), "UTF-8");
        List<String> list = new ArrayList<>();
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                list.add(line);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
//        System.out.println("list大小" + list.size());
//        System.out.println("读取耗时：" + (System.currentTimeMillis() - start));
        String url = "open.prd.yinshuitong.com/checkApi/api/serialNet/assets/invoiceCheck/v4";
        int i = 1;
        List<String> resultList = new ArrayList<>();
        for (String s : list) {
            Map param = new HashMap();
            param.put("requestId", "1234567890");
            param.put("orgNo", "44ad8fcde176b454636e");
            param.put("invoice", JSON.parseObject(s));
            String reqString = JSON.toJSONString(param);
            long start = System.currentTimeMillis();
            String response = HttpRequest.post(url).body(reqString).timeout(60000).execute().body();
            String resultRes = "响应耗时:" + (System.currentTimeMillis() - start) + "ms 发票信息:" + s + " 响应结果:" + response;
            resultList.add(resultRes);
            System.out.println(resultRes);
            i++;
            Thread.sleep(100);
        }
        writeToFile(resultList,"D:\\测试发票\\193张发票核验返回结果及耗时.txt");
    }
}

