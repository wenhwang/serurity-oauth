## 授权方式

### 授权码

+ 认证 

```properties
authroization_code

## 请求 /oauth/authorize?grant_type=authorization_code&response_type=code&client_id=authcode
Get /oauth/authorize
    grant_type=authorization_code
    response_type=code
    client_id=
    state=自定义参数

## 响应
Status Code: 302 
Location: http://localhost:8080/oauth/login/client?code=l8QjbR&state=自定义参数
```



+  授权

```properties
## 请求
# /oauth/token?grant_type=authroization_code&code=l8QjbR&client_id=authcode&client_secret=secret

post /oauth/token
    grant_type=authorization_code
    code=l8QjbR
    client_id=authcode
    client_secret=secret
         
# 响应
{
  "access_token": "9abb8b60-25c5-4876-8058-13f8762301c4",
  "token_type": "bearer",
  "refresh_token": "5a29e37f-89dd-4bee-bd75-a9a14cf5ba71",
  "expires_in": 33390,
  "scope": "order-service"
}	
```



+ 校验token

```properties
## 请求
GET /oauth/check_token/?token=9abb8b60-25c5-4876-8058-13f8762301c4


# 响应
{
    "aud": [
        "order"
    ],
    "user_name": "wangwh",
    "scope": [
        "order-service"
    ],
    "active": true,
    "exp": 1589918926,
    "authorities": [
        "RWX"
    ],
    "client_id": "authcode"
}
```



+ 刷新Token

```properties
POST /oauth/token?grant_type=refresh_token&refresh_token=fbde81ee-f419-42b1-1234-9191f1f95be9&client_id=demoClientId&client_secret=demoClientSecret

    grant_type=refresh_token
    refresh_token=
    client_id=
    client_secret
```



