package service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import doa.ProductDoa;
import doa.impl.ProductDoaImpl;
import domain.ProductPageBean;
import domain.Product;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> getNewProduct() throws SQLException {
		ProductDoa doa = new ProductDoaImpl();
		return doa.getNewProduct();

	}

	@Override
	public List<Product> getHotProduct() throws Exception {
		ProductDoa doa = new ProductDoaImpl();
		return doa.getHotProduct();
	}

	@Override
	public Product geProductById(String pid) throws Exception {
		ProductDoa doa = new ProductDoaImpl();
		return doa.geProductById(pid);

	}

	@Override
	public ProductPageBean findByPageid(String cid, int currPage, Integer pageSize) throws Exception {
		ProductDoa doa = new ProductDoaImpl();

		// 查询当前页的商品信息
		List<Product> list = doa.findByPageid(cid, currPage, pageSize);

		// 获取总记录数
		int totalRecodes = doa.getTotalRecodes(cid);

		return new ProductPageBean(list, currPage, totalRecodes);
	}

	/**
	 * 查询所有商品信息
	 */
	@Override
	public List<Product> findAll() throws Exception {
		ProductDoa doa = new ProductDoaImpl();
		return doa.findAll();
	}

	/**
	 * 添加新商品
	 */
	@Override
	public void add(Product p) throws Exception {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "insert  into `product`(`pid`,`pname`,`market_price`,    `shop_price`,`pimage`,`pdate`,          `is_hot`,`pdesc`,`pflag`   ,`cid`) "
				+ "values ( ? , ? , ? , ? ,             ? , ? , ? , ? ,           ? , ? )";
		runner.update(sql,   p.getPid(),p.getPname(),p.getMarket_price(),   p.getShop_price(), p.getPimage() ,p.getPdate().toLocaleString(), 
				p.getIs_hot(), p.getPdesc(), p.getPflag(), p.getCategory().getCid() );
	}

}
