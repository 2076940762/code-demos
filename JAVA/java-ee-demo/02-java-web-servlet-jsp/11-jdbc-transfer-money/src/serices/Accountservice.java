package serices;

import doa.AccountDoa;

public class Accountservice
    {

        public boolean  transfer(String fromusr, String tousr, String money) throws NumberFormatException, Exception {
            AccountDoa doa = new AccountDoa();

            // 转出
            int i = doa.transferOut(fromusr, Double.parseDouble(money));

            // 转入
            int j = doa.transferIn(tousr, Double.parseDouble(money));
            
            if (i!=j) {
                return false;
            }

            return true;
        }

    }
