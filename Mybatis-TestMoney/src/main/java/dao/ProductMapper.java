package dao;


import entity.Product;
import handler.MyMoneyTypeHandler;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ProductMapper {

    @Insert("INSERT INTO product (`name`,price) VALUES" +
            "(#{name},#{price,typeHandler=handler.MyMoneyTypeHandler})") // 指定自定义Hnadler
    void insert(Product product);

    @Select("select * from product")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            // 指定自定义Hnadler
            @Result(column = "price", property = "price", typeHandler = MyMoneyTypeHandler.class)
    })
    List<Product> list();

}
