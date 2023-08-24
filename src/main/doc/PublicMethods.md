### Public Methods

#### 1. `obtainBoxRegKey`

- **Description**: Retrieves a registration key for a specific box.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `serviceIds`: List of service IDs.
    - `sign`: Signature for the request.
    - `reqId`: Unique request ID.
- **Returns**: `ObtainBoxRegKeyResponse` containing the registration key and other related information.
- **Usage**:

```java
ObtainBoxRegKeyResponse response = client.obtainBoxRegKey(boxUUID, serviceIds, sign, reqId);
```

#### 2. `registerDevice`

- **Description**: Registers a device with the platform.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: `RegisterDeviceResponse` containing details of the registered device.
- **Usage**:

```java
RegisterDeviceResponse response = client.registerDevice(boxUUID, reqId, boxRegKey);
```

#### 3. `registerClient`

- **Description**: Registers a client with the platform.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `userId`: User ID.
    - `clientUUID`: Client UUID.
    - `clientType`: Type of the client.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: `RegisterClientResponse` containing details of the registered client.
- **Usage**:

```java
RegisterClientResponse response = client.registerClient(boxUUID, userId, clientUUID, clientType, reqId, boxRegKey);
```

#### 4. `registerUser`

- **Description**: Registers a user with the platform.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `userId`: User ID.
    - `subdomain`: Subdomain for the user.
    - `userType`: Type of the user.
    - `clientUUID`: Client UUID.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: `RegisterUserResponse` containing details of the registered user.
- **Usage**:

```java
RegisterUserResponse response = client.registerUser(boxUUID, userId, subdomain, userType, clientUUID, reqId, boxRegKey);
```

#### 5. `deleteDevice`

- **Description**: Deletes a registered device from the platform.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: No return value.
- **Usage**:

```java
client.deleteDevice(boxUUID, reqId, boxRegKey);
```

#### 6. `deleteUser`

- **Description**: Deletes a registered user from the platform.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `userId`: User ID.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: No return value.
- **Usage**:

```java
client.deleteUser(boxUUID, userId, reqId, boxRegKey);
```

#### 7. `deleteClient`

- **Description**: Deletes a registered client from the platform.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `userId`: User ID.
    - `clientUUID`: Client UUID.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: No return value.
- **Usage**:

```java
client.deleteClient(boxUUID, userId, clientUUID, reqId, boxRegKey);
```

#### 8. `modifyUserDomainName`

- **Description**: Modifies the domain name of a registered user.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `userId`: User ID.
    - `subdomain`: New subdomain for the user.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: `ModifyUserDomainNameResponse` containing details of the modified user domain name.
- **Usage**:

```java
ModifyUserDomainNameResponse response = client.modifyUserDomainName(boxUUID, userId, subdomain, reqId, boxRegKey);
```

#### 9. `migrateSpacePlatform`

- **Description**: Migrates a space platform.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `networkClientId`: Network client ID.
    - `userInfos`: List of user migration information.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: `SpacePlatformMigrationResponse` containing details of the migrated space platform.
- **Usage**:

```java
SpacePlatformMigrationResponse response = client.migrateSpacePlatform(boxUUID, networkClientId, userInfos, reqId, boxRegKey);
```

#### 10. `migrateSpacePlatformOut`

- **Description**: Migrates out of a space platform.
- **Parameters**:
    - `boxUUID`: Unique identifier for the box.
    - `userDomainRouteInfos`: List of user domain route information.
    - `reqId`: Unique request ID.
    - `boxRegKey`: Registration key obtained from `obtainBoxRegKey`.
- **Returns**: `SpacePlatformMigrationOutResponse` containing details of the migration out process.
- **Usage**:

```java
SpacePlatformMigrationOutResponse response = client.migrateSpacePlatformOut(boxUUID, userDomainRouteInfos, reqId, boxRegKey);
```
