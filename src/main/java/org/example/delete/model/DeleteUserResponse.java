package org.example.delete.model;

import com.aliyuncs.AcsResponse;
import com.aliyuncs.transform.UnmarshallerContext;
import org.example.delete.transfer.DeleteUserResponseUnmarshaller;

public class DeleteUserResponse extends AcsResponse {

    @Override
    public DeleteUserResponse getInstance(UnmarshallerContext context) {
        return DeleteUserResponseUnmarshaller.unmarshall(this, context);
    }
}
