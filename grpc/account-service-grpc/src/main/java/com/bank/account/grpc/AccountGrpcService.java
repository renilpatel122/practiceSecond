package com.bank.account.grpc;

import com.bank.account.grpc.proto.AccountServiceGrpc;
import com.bank.account.grpc.proto.AccountRequest;
import com.bank.account.grpc.proto.AccountResponse;
import com.bank.account.service.AccountService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class AccountGrpcService extends AccountServiceGrpc.AccountServiceImplBase {

    private final AccountService accountService;

    public AccountGrpcService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void getAccountById(AccountRequest request, StreamObserver<AccountResponse> responseObserver) {
        var account = accountService.getAccountById(request.getAccountId());

        AccountResponse response = AccountResponse.newBuilder()
                .setAccountId(account.getId())
                .setName(account.getName())
                .setBalance(account.getBalance())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}