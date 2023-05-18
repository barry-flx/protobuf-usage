package com.flx.web3j;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;

public class EthLogExample {

    public static void main(String[] args) {
        Web3j web3j = Web3j.build(new HttpService());

        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST, "0x00000");

        web3j.ethLogFlowable(filter).subscribe(log -> {
            System.out.println(log.getLogIndex());
        });
    }

}
