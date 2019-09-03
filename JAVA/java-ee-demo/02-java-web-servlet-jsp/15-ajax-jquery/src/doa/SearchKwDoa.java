package doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchKwDoa
    {

        public List<String> search(String kword) throws Exception {
//        注册驱动程序
            Class.forName("com.mysql.jdbc.Driver");

//   获取连接
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webstore", "root", "root");

            String sql = "SELECT * FROM keywords WHERE kw LIKE ? ORDER BY kw;";

//        预编译sql
            PreparedStatement st = connection.prepareStatement(sql);

//        设置参数
            st.setString(1, "%" + kword + "%");

//        执行
            ResultSet result = st.executeQuery();

            List<String> list = new ArrayList<String>();
            while (result.next()) {
                list.add(result.getString(1));
            }

            {
                result.close();
                st.close();
                connection.close();
            }
            return list;
        }

    }
