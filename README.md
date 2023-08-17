**UnifiedApiClient SDK Comprehensive Documentation**

---

### Introduction

The `UnifiedApiClient` SDK is a robust interface designed to facilitate seamless interactions between developers and the target platform. This documentation provides an in-depth guide on how to utilize the SDK effectively, ensuring that developers can harness its full potential.

---

### Table of Contents

1. [Prerequisites](#prerequisites)
2. [Initialization](#initialization)
3. [Public Methods](#public-methods)
4. [Error Handling](#error-handling)
5. [Logging](#logging)
6. [Best Practices](#best-practices)
7. [Conclusion](#conclusion)

---

### Prerequisites

- Dependencies:
  - `com.fasterxml.jackson.core:jackson-databind`
  - `org.slf4j:slf4j-api`
  - Java's built-in `java.net.http.HttpClient`

---

### Initialization

To utilize the SDK, initialize the `UnifiedApiClient`:

```java
Logger customLogger = LoggerFactory.getLogger(YourClass.class);
UnifiedApiClient client = new UnifiedApiClient("https://api.targetplatform.com", customLogger);
```

Parameters:
- `host`: The base URL of the target platform.
- `customLogger` (optional): A custom logger instance. If not provided, the SDK will use its default logger.

---

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

---

### Error Handling

The SDK throws exceptions for various errors. Handle these exceptions to ensure smooth application operation. For instance, if an API is unavailable, the SDK will throw an exception with the message "API not available: [METHOD] [briefUri]".

Example:

```java
try {
    RegisterDeviceResponse response = client.registerDevice(boxUUID, reqId, boxRegKey);
} catch (Exception e) {
    System.err.println("Error: " + e.getMessage());
}
```

---

### Logging

The SDK uses SLF4J for logging. By default, it logs information about API requests and responses. If you provide a custom logger during initialization, the SDK will use that logger; otherwise, it will use its default logger.

Logs include:
- Timestamp
- Function name
- Request method, path, request ID, request body, and any additional headers
- Response status code and body

---

### Best Practices

1. **Initialization**: Always initialize the `UnifiedApiClient` with the correct host URL.
2. **Error Handling**: Implement comprehensive error handling to manage exceptions thrown by the SDK.
3. **Logging**: Utilize the logging feature to monitor API interactions and diagnose issues.
4. **Unit Testing**: Regularly run unit tests to ensure the SDK's functionality and catch potential issues early.

---

### Conclusion

The `UnifiedApiClient` SDK is a powerful tool for developers to interact with the target platform. By following this comprehensive guide, developers can ensure they're using the SDK to its fullest potential, leading to efficient and effective platform interactions.

---

**Note**: This documentation provides a detailed overview of the SDK. For even more in-depth details, always refer to the SDK's source code and accompanying Javadoc comments.

---

[End of Documentation]
