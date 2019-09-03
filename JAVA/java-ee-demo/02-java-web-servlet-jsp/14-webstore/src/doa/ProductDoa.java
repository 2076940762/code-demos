package doa;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import domain.Product;

public class ProductDoa
    {

        public List<Product> findAll() throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            String sql = "SELECT * FROM product;";

            return qr.query(sql, new BeanListHandler<Product>(Product.class));
        }

        public void addProduct(Product p) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

//            pid          
//            pname       
//            market_price

//            shop_price  
//            pdate       
//            pdesc       

            String sql = "insert into product (pid,pname,market_price,     shop_price,pdate,pdesc) values(?,?,?,  ?,?,?)";
            qr.update(sql, p.getPid(), p.getPname(), p.getMarket_price(), p.getShop_price(), p.getPdate(),
                    p.getPdesc());

        }

        public Product getProductByPid(String pid) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            String sql = "select * from product where pid = ? ";

            Product p = qr.query(sql, new BeanHandler<>(Product.class), pid);
            return p;
        }

        public void updateProductById(Product p) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            String sql = "UPDATE product SET pname = ?, market_price=?,     "
                    + "     shop_price=?, pdesc=?  WHERE pid=?";
            qr.update(sql, p.getPname(), p.getMarket_price(), p.getShop_price(), p.getPdesc(), p.getPid());

        }

        public void deleteProductById(String pid) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            String sql = "delete from product where pid = ? ; ";

            qr.update(sql, pid);
        }

        public void deleteProductByIds(String[] ids) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            String sql = "delete from product where pid in (";
            for (String id : ids) {
                sql += ("\'" + id + "\',");
            }
            sql = sql.substring(0, sql.length() - 1) + ");";

            qr.update(sql);
        }

        public List<Product> searchProduct(String name, String kword) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            String sql = "select * from product where 1=1 ";

//            if (   (name!=null&& !name.trim().equals("")  ) || (kword!=null && !kword.trim().equals(""))  ) {
//                sql+=" where ";
//            }

            if (name != null && !name.trim().equals("")) {
                sql += (" and pname like \'%" + name + "%\'  ");
            }

            if (kword != null && !kword.trim().equals("")) {
                sql += (" and pdesc like \'%" + kword + "%\' ");
            }

            sql += " ; ";

            return qr.query(sql, new BeanListHandler<Product>(Product.class));
        }

        public List<Product> findCurrPageProducts(int currentPage, int pageSize) throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            String sql = "select * from product limit ";
            sql += currentPage * pageSize;
            sql += (" , " + pageSize);

            return qr.query(sql, new BeanListHandler<Product>(Product.class));
        }

        public int getTotalRecords() throws SQLException {
            QueryRunner qr = new QueryRunner(new ComboPooledDataSource());

            String sql = "select count(*) from product ; ";

            return ((Long) qr.query(sql, new ScalarHandler())).intValue();
        }

    }
