package project.homelearn.config.security;

public abstract class JwtConstants {
    public static final String ACCESS_TOKEN_HEADER_NAME = "access";
//    public static final long ACCESS_TOKEN_EXPIRATION = 600000L; // 10분
    public static final long ACCESS_TOKEN_EXPIRATION = 86400000L; // 24시간

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh";
    public static final long REFRESH_TOKEN_EXPIRATION = 86400000L; // 24시간
}