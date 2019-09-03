package services;

import java.sql.SQLException;
import java.util.List;

import doa.ProductDoa;
import domain.PageBean;
import domain.Product;

public class ProductServices
    {

        public List<Product> findAll() throws SQLException {
            // TODO Auto-generated method stub
            return new ProductDoa().findAll();
        }

        public void addProduct(Product p) throws SQLException {
            new ProductDoa().addProduct(p);
        }

        public Product getProductByPid(String pid) throws SQLException {
            return new ProductDoa().getProductByPid(pid);
        }

        public void updateProductById(Product p) throws SQLException {
            new ProductDoa().updateProductById(p);
        }

        public void deleteProductById(String pid) throws SQLException {
            new ProductDoa().deleteProductById(pid);
        }

        public void deleteProductByIds(String[] ids) throws SQLException {
            new ProductDoa().deleteProductByIds(ids);
        }

        /**
         * 搜索商品
         * 
         * @param name
         * @param kword
         * @return
         * @throws SQLException
         */
        public List<Product> searchProduct(String name, String kword) throws SQLException {
            return new ProductDoa().searchProduct(name, kword);
        }

        public PageBean<Product> findCurrPageProducts(int currentPage) throws SQLException {
            ProductDoa doa=new ProductDoa();
            
            //查询商品信息
            List<Product> list =doa.findCurrPageProducts(currentPage,PageBean.getPageSize()); 
            
            //获取记录条数
            int totalRecords=doa.getTotalRecords();
            
            return new PageBean<Product>(list, currentPage, totalRecords);
        }

    }
