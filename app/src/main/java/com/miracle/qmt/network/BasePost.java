package com.miracle.qmt.network;

/**
 * Created by WJQ on 2016/12/9.
 */
public class BasePost {
    public String fields;
    public String method;//接口名称
    public String version;//版本号 1.0
    public String ticket;//用户登录成功后获得的ticket 值，除非接口要求，否则不需要携带
}
