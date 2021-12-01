package eth;

import eth.encoders.PrivateKeyGenerator;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class EthGenerator {

    private final static int RRS = 100;

    public static void main(String[] args) throws IOException {
        System.out.println("Connecting to Ethereum ...");

        Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/cd601df89a60461f9d744777d13769a5"));
        System.out.println("Successfully connected to Ethereum");
        System.out.println("Load your MONEY: ");

        PrivateKeyGenerator pr = new PrivateKeyGenerator();
        Set<Object> set = new HashSet<>();
        for (int i = 0; i < RRS; i++) {

            set.add(pr.ethKeyGenerator(64));
        }

        for (Object o : set) {
            Credentials credentials = Credentials.create(o.toString());

            EthGetBalance balance = web3.ethGetBalance(credentials.getAddress().toLowerCase(Locale.ROOT), DefaultBlockParameterName.LATEST).send();
            BigDecimal balanceInEther = Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER);

            BigDecimal u = BigDecimal.valueOf(0.00001);
            if (balanceInEther.compareTo(u) > 0) {
                System.out.println("Balance: ");
                System.out.println(balanceInEther);

                System.out.println("Array public keys: ");
                System.out.println(credentials.getAddress().toLowerCase(Locale.ROOT));
                System.out.println("Array private keys: ");
                System.out.println(o);
            }
        }
        System.out.println("NO MONEY YET");
        System.exit(0);

    }
}