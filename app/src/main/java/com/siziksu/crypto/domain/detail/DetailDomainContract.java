package com.siziksu.crypto.domain.detail;

import com.siziksu.crypto.domain.BaseDomainContract;
import com.siziksu.crypto.domain.model.CoinDomainModel;

public interface DetailDomainContract<D> extends BaseDomainContract<D> {

    void start(int coinId);

    void trade(CoinDomainModel coin, float amount);
}
