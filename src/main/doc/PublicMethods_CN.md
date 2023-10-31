### 公共方法

#### 1. `obtainBoxRegKey`

- **描述**: 为特定的盒子检索注册密钥。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `serviceIds`: 服务ID列表。
    - `sign`: 请求的签名。
    - `reqId`: 唯一的请求ID。
- **返回**: 包含注册密钥和其他相关信息的 `ObtainBoxRegKeyResponse`。
- **用法**:

```java
ObtainBoxRegKeyResponse response = client.obtainBoxRegKey(boxUUID, serviceIds, sign, reqId);
```

#### 2. `registerDevice`

- **描述**: 在平台上注册设备。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 包含已注册设备的详细信息的 `RegisterDeviceResponse`。
- **用法**:

```java
RegisterDeviceResponse response = client.registerDevice(boxUUID, reqId, boxRegKey);
```

#### 3. `registerClient`

- **描述**: 在平台上注册客户端。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `userId`: 用户ID。
    - `clientUUID`: 客户端UUID。
    - `clientType`: 客户端类型。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 包含已注册客户端的详细信息的 `RegisterClientResponse`。
- **用法**:

```java
RegisterClientResponse response = client.registerClient(boxUUID, userId, clientUUID, clientType, reqId, boxRegKey);
```

#### 4. `registerUser`

- **描述**: 在平台上注册用户。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `userId`: 用户ID。
    - `subdomain`: 用户的子域。
    - `userType`: 用户类型。
    - `clientUUID`: 客户端UUID。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 包含已注册用户的详细信息的 `RegisterUserResponse`。
- **用法**:

```java
RegisterUserResponse response = client.registerUser(boxUUID, userId, subdomain, userType, clientUUID, reqId, boxRegKey);
```

#### 5. `deleteDevice`

- **描述**: 从平台删除已注册的设备。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 无返回值。
- **用法**:

```java
client.deleteDevice(boxUUID, reqId, boxRegKey);
```

#### 6. `deleteUser`

- **描述**: 从平台删除已注册的用户。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `userId`: 用户ID。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 无返回值。
- **用法**:

```java
client.deleteUser(boxUUID, userId, reqId, boxRegKey);
```

#### 7. `deleteClient`

- **描述**: 从平台删除已注册的客户端。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `userId`: 用户ID。
    - `clientUUID`: 客户端UUID。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 无返回值。
- **用法**:

```java
client.deleteClient(boxUUID, userId, clientUUID, reqId, boxRegKey);
```

#### 8. `modifyUserDomainName`

- **描述**: 修改已注册用户的域名。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `userId`: 用户ID。
    - `subdomain`: 用户的新子域。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 包含修改后的用户域名详细信息的 `ModifyUserDomainNameResponse`。
- **用法**:

```java
ModifyUserDomainNameResponse response = client.modifyUserDomainName(boxUUID, userId, subdomain, reqId, boxRegKey);
```

#### 9. `migrateSpacePlatform`

- **描述**: 迁移空间平台。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `networkClientId`: 网络客户端ID。
    - `userInfos`: 用户迁移信息列表。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 包含迁移后的空间平台详细信息的 `SpacePlatformMigrationResponse`。
- **用法**:

```java
SpacePlatformMigrationResponse response = client.migrateSpacePlatform(boxUUID, networkClientId, userInfos, reqId, boxRegKey);
```

#### 10. `migrateSpacePlatformOut`

- **描述**: 迁出空间平台。
- **参数**:
    - `boxUUID`: 盒子的唯一标识符。
    - `userDomainRouteInfos`: 用户域路由信息列表。
    - `reqId`: 唯一的请求ID。
    - `boxRegKey`: 从 `obtainBoxRegKey` 获取的注册密钥。
- **返回**: 包含迁出过程的详细信息的 `SpacePlatformMigrationOutResponse`。
- 用法:
```java
SpacePlatformMigrationOutResponse response = client.migrateSpacePlatformOut(boxUUID, userDomainRouteInfos, reqId, boxRegKey);
```