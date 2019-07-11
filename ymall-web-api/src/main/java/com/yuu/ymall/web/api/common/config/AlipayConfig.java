package com.yuu.ymall.web.api.common.config;

/**
 * 支付宝支付配置
 *
 * @author by Yuu
 * @classname AlipayConfig
 * @date 2019/7/6 17:14
 */
public class AlipayConfig {
    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public static String app_id = "2016092000557285";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQClzTiJxBfco9OdF0K9ymwJf9WEiUKTQWNP0F7R5lDX/LHUCwdw9HlLfy9cMnlLYYc3nUTe4DMUHx77fCHlAkPiOBIwpt0peBmm5TQ550q8yoLE1xjVJ6o3cq3RFwr4Gj4USWQAm0o3qFSKSzcEIwTC207kdkirV192Fvk7Qz5NcVQURBprJzOzsBKirscF/ZtMCLYU6OOkZHS6z1yXJljd1bKzt2H1n1n2+luwvgD4iuBjCI3RsNl9FZGp30NXbFMZvXeDlSSwOvR5qspiagEdUXGqg0+aY6sz4ERiR1MvU3Ko/hGUwyQx31RnwSmHZg7zzH/8Q77RepaGT5bM4LsRAgMBAAECggEAGTaB+IE3V7gZ//N/lLjpgPBNmd/9ncL9xS9cm/K4LIAi0qnysbybjV2D6DNMuqfUFiL2TCYv3kl1Pqz7woV7qTT5nyhgzWOUEwsbzApeFSN+i0xiXFexzMjFodwtMNlcw1z9thHnUVtpr7YMYbapjLAo6RkLp/Kbc6u+rsJlAQWSAJLxSJ0vhhJlJrdPeCGRxWUMN84S8l+72As1HG3VAFgoqcRlRK+j1anMRd1nw1Su6Ue96DHwgWVe0CSfbweIxDCN/JOzjYfj0Yqw2DOLnGnEEJ0Rdlre3Lzxzd8+8mxthWxByNcTZi+untMM2sP+sxZBTQLYn2jwPM/r16JhWQKBgQDdJRJ4P7Cbo31Ff9Pk7auT8wVqUhs3s8phKLWclUZBZL0fr5EXkqTpL6lf3lJygtgWBeTO16LhMIGX3zkjEgRrpRAMwYdCZDthXKarNItOqXXz3aJ2MuXGWXPA5KfcNh1+B6bcQfyE0Zzzboa6b5MCjcc2fUm+B1tYwJLFpPkd4wKBgQC/7x5E1VjlWZBFlMrMU5SCkh7XvCsnfAPnFgD1MH/Uhv0dRHGHIpUdxmaqWbKrKMdv17PsmV8SkZPQ9hM0krMsWzaIEFt01lAyv8ILRjYkP6AXCozZhZa6YFzUlyEP33z08Tvltz4dKQD1R3r4AuSh3vvF13Ci+QnTvdJgqCFVewKBgDhjhpUg8GCPTJcXJCnArLp6Akx9CTGn55fksIJv0c1E6bCVrPKM0v4eyBqI7w8B7CWZdSGE/KRdIP5fzgryQixMl44ZHus/8aA+5sTLDiaNsWTwg8eN7Ocp0glChMbzCYwkWVjafpBDfrjX2wTZBGxnSH/NbKtG5vT3jbVV/quvAoGBAJAeQsI42MlOWTCGK0kFsUvh4FrpphW930hTk2CIjFPPEJw7Wmu4IfP7l/dt5uTyd96ZPzb3axYMTcvoGj+tr/AshWPhtGqkyg9xQYkprVnGlLT4bQ6gaq+Dz/Cpkl/S1oct5GX3FCLdOST0CXfU5ExPnQnPXXpiOWjO7fs6A92lAoGAbUEfaFesEqzPo6apMg+fAt4l93sD7YdiOiI0VKUSY6Zx91GmITSpTE4KJ/DBeW8ahUD68ieuJLtT6DLu9XcBlgXb9+6TIL125fwVNF0eflw7BWBpOvnd1Au/37cBkgf2NsbXdry4SRta+aD9g4rR162bSXbAAnCG/koxCj3yz94=";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥
     */
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuWInnW+uOnGgcIF75a7UAWU4FXNUqf9aaLxiMKGlTmnsi7WhCSxOkKaWMPnqM2jWVj3+sNQQwMIk4a2wMhrbviYzQUR87zrZk2+rsRrv45pFONjZBOcLcVnH4lUdw4+WZUCpK5z2Sy+qVcoOzkLE6Z6eUpIBIHTaSf3VDvllHOnm45JMYRy8ps2FsM+Sq0Ev60vvykjNbpGecB3cTi2llVTS1273oEG4cwxqCTWoHNHLNltgn5h7XntgnxEF05K2tLOTPTRuk1vIyiSh4ejn+Xrq0EC6VbLSOQmonK7Yt2UXuRapT6Vu6Bk1acUmijFBqiEN2UD3VpAU+K8IZGqZuQIDAQAB";

    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String notify_url = "http://4bjxng.natappfree.cc/order/callback";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static String return_url = "http://localhost:8080/";

    /**
     * 签名方式
     */
    public static String sign_type = "RSA2";

    /**
     * 字符编码格式
     */
    public static String charset = "utf-8";

    /**
     * 支付宝网关
     */
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

}
