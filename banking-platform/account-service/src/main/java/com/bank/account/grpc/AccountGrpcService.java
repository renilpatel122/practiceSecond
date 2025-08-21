package com.bank.account.grpc;

import com.bank.account.entity.Account;
import com.bank.account.service.AccountService;
import com.bank.common.dto.AccountRequest;
import com.bank.common.dto.AccountResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class AccountGrpcService extends AccountServiceGrpc.AccountServiceImplBase {

    private final AccountService accountService;

    @Override
    public void createAccount(AccountServiceOuterClass.AccountRequest request,
                              StreamObserver<AccountServiceOuterClass.AccountResponse> responseObserver) {
        Account account = Account.builder()
                .accountHolderName(request.getAccountHolderName())
                .accountNumber(request.getAccountNumber())
                .balance(request.getBalance())
                .build();

        Account saved = accountService.create(account);

        AccountServiceOuterClass.AccountResponse response = AccountServiceOuterClass.AccountResponse.newBuilder()
                .setId(saved.getId())
                .setAccountHolderName(saved.getAccountHolderName())
                .setAccountNumber(saved.getAccountNumber())
                .setBalance(saved.getBalance())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
