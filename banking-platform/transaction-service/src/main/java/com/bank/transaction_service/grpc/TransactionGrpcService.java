package com.bank.transaction.grpc;

import com.bank.common.proto.TransactionRequest;
import com.bank.common.proto.TransactionResponse;
import com.bank.common.proto.TransactionServiceGrpc;
import com.bank.transaction.service.TransactionService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class TransactionGrpcService extends TransactionServiceGrpc.TransactionServiceImplBase {
    private final TransactionService service;

    public TransactionGrpcService(TransactionService service) {
        this.service = service;
    }

    @Override
    public void createTransaction(TransactionRequest request, StreamObserver<TransactionResponse> responseObserver) {
        var tx = service.createTransaction(request.getAccountId(), request.getAmount(), request.getType());

        TransactionResponse response = TransactionResponse.newBuilder()
                .setId(tx.getId())
                .setAccountId(tx.getAccountId())
                .setAmount(tx.getAmount())
                .setType(tx.getType())
                .setTimestamp(tx.getTimestamp().toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
