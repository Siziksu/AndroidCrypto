package com.siziksu.crypto.data;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.siziksu.crypto.data.client.CryptoClient;
import com.siziksu.crypto.data.mock.ConnectionManagerMock;
import com.siziksu.crypto.data.mock.CryptoServiceMock;
import com.siziksu.crypto.data.mock.PersistenceClientMock;
import com.siziksu.crypto.data.model.TradeDataModel;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RepositoryTest {

    private RepositoryContract repository;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        repository = new Repository(context, new ConnectionManagerMock(), new PersistenceClientMock(), new CryptoClient(new CryptoServiceMock()));
    }

    @Test
    public void testGetCoins() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.getCoins()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        coins -> {
                            if (!coins.isEmpty()) {
                                result[0] = true;
                            }
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }

    @Test
    public void testGetCoinsAndCheckSixthIsCardano() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.getCoins()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        coins -> {
                            assertEquals("Cardano", coins.get(5).name);
                            result[0] = true;
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }

    @Test
    public void testGetCoin() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.getCoin(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        coin -> {
                            result[0] = true;
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }

    @Test
    public void testGetCoinOneAndCheckName() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.getCoin(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        coin -> {
                            assertEquals("Bitcoin", coin.name);
                            result[0] = true;
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }

    @Test
    public void testGetHistorical() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.getCoinHistorical(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        historical -> {
                            result[0] = true;
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }

    @Test
    public void testGetHistoricalAndCheckFirstPrice() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.getCoinHistorical(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        historical -> {
                            assertEquals("11648.85258765", historical.get(0).priceUsd);
                            result[0] = true;
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }

    @Test
    public void testPortfolio() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.getPortfolio("", "")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        portfolioTrades -> {
                            result[0] = true;
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }

    @Test
    public void testPortfolioAndCheckPortfolioIsEmpty() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.getPortfolio("", "")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        portfolioTrades -> {
                            assertTrue(portfolioTrades.isEmpty());
                            result[0] = true;
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }

    @Test
    public void testAddCoinToPortfolio() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        repository.addCoinToPortfolio("", "", new TradeDataModel())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        () -> {
                            result[0] = true;
                            signal.countDown();
                        },
                        throwable -> signal.countDown()
                );
        signal.await();
        assertTrue(result[0]);
    }
}
