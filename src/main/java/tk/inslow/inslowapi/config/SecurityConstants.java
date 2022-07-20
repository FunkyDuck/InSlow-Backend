package tk.inslow.inslowapi.config;

public class SecurityConstants {
    public static final String JWT_KEY = "InSl0w-S3cur1tyK3y!";
    public static final long EXPIRATION_TIME = 86_400_000; // 1 Jour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_KEY = "Authorization";
    public static final String LOGIN_URL = "/user/connect";
}
