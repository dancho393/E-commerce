package com.projects.ecommerce.api.model.base;

public interface Operation <O extends OperationResponse,I extends OperationRequest>{
    O process(I request);
}
