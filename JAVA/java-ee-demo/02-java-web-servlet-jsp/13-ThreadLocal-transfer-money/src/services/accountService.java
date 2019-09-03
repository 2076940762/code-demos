package services;

import java.sql.Connection;
import java.sql.DriverManager;

import doa.AccountDoa;
import utils.DataSourceUtils;

public class accountService
    {
        /**
         * 
         * @param fromUser
         * @param toUser
         * @param money
         * @throws Exception 
         */
        public void transfer(String fromUser, String toUser, String money) throws Exception {
            AccountDoa doa = new AccountDoa();
            try {

                DataSourceUtils.stratTransaction();

                doa.transferOut(fromUser, money);

                doa.transferIn(toUser, money);

                DataSourceUtils.closeAndCommit();

            } catch (Exception e) {
                e.printStackTrace();
                DataSourceUtils.rollbackAndClose();
                throw e;
            }

        }

    }
