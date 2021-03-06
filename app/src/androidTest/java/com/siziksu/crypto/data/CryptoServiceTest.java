package com.siziksu.crypto.data;

import android.support.test.runner.AndroidJUnit4;

import com.siziksu.crypto.data.client.model.TradeRequest;
import com.siziksu.crypto.data.client.service.CryptoService;
import com.siziksu.crypto.data.mock.CryptoServiceMock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class CryptoServiceTest {

    private CryptoService cryptoServiceMock;

    @Before
    public void setUp() {
        cryptoServiceMock = new CryptoServiceMock();
    }

    @Test
    public void testGetCoins() throws Exception {
        final CountDownLatch signal = new CountDownLatch(1);
        final boolean[] result = {false};
        cryptoServiceMock.getCoins()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        coins -> {
                            if (!coins.coins.data.isEmpty()) {
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
        cryptoServiceMock.getCoins()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        coins -> {
                            assertEquals("Cardano", coins.coins.data.get(5).name);
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
        cryptoServiceMock.getCoin(1)
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
        cryptoServiceMock.getCoin(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        coin -> {
                            assertEquals("Bitcoin", coin.coin.name);
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
        cryptoServiceMock.getCoinHistorical(1)
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
        cryptoServiceMock.getCoinHistorical(1)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        historical -> {
                            assertEquals("11648.85258765", historical.historical.get(0).priceUsd);
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
        cryptoServiceMock.getPortfolio("")
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
        cryptoServiceMock.getPortfolio("")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(
                        portfolioTrades -> {
                            assertTrue(portfolioTrades.coins.isEmpty());
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
        cryptoServiceMock.addCoinToPortfolio("", new TradeRequest())
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
