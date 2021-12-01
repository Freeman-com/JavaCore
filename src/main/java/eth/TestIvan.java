package eth;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestIvan {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        Set<String> stringArrayList = new HashSet<>(100000);
        for (int i = 0; i < 100000L; i++) {
            stringArrayList.add(random(64));
        }
        Map<String, Future<BigDecimal>> futures = getEtheriumBalanceAsync(new ArrayList<>(stringArrayList));
        for (Map.Entry<String, Future<BigDecimal>> entry : futures.entrySet()) {

            BigDecimal x = BigDecimal.valueOf(0);
            BigDecimal balanceInEither = entry.getValue().get();
            if (!balanceInEither.equals(x)) {
                System.out.println("ID " + entry.getKey() + " Balance: " + balanceInEither);
            }

        }

        System.out.println((System.currentTimeMillis() - startTime) / 1000);
        System.exit(1);
    }

    public static String random(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(random.nextInt()));
        }
        return sb.toString().substring(0, length);
    }

    public static Map<String, Future<BigDecimal>> getEtheriumBalanceAsync(List<String> etheriumIDsList) throws InterruptedException {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            Map<String, Future<BigDecimal>> futures = etheriumIDsList.stream()
                    .collect(Collectors.toMap(
                            Function.identity(),
                            privateKey -> executor.submit(() -> {
                                Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/083836b2784f48e19e03487eb3209923"));
                                Credentials credentials = Credentials.create(privateKey);
                                EthGetBalance balanceWei = web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
                                BigDecimal balanceInEither = Convert.fromWei(balanceWei.getBalance().toString(), Convert.Unit.ETHER);
                                return balanceInEither;
                            })));
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.DAYS);
            return futures;
        } catch (InterruptedException e) {
            System.out.format("Can't retrieve Etherium balances. Cause: %s%n", e.getMessage());
            throw e;
        }
    }
}
