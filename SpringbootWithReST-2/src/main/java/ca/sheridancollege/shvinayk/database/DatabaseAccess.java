package ca.sheridancollege.shvinayk.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.shvinayk.bean.Item;


@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public List<Item> findAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Item";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Item>(Item.class));
	}

	public List<Item> findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM Item WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters,

				new BeanPropertyRowMapper<Item>(Item.class));
	}

	public Long save(Item Item) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		String query = "INSERT INTO Item(name,quantity,price) VALUES(:name,:quantity,:price)";
		namedParameters.addValue("name", Item.getName());
		namedParameters.addValue("quantity", Item.getQuantity());
		namedParameters.addValue("price", Item.getPrice());
		jdbc.update(query, namedParameters, generatedKeyHolder);
		return (Long) generatedKeyHolder.getKey();
	}

	public String updateItem(Long id, Item Item) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "UPDATE Item SET name=:name,quantity=:quantity,price=:price where id=:id";
		namedParameters.addValue("id", id);
		namedParameters.addValue("name", Item.getName());
		namedParameters.addValue("quantity", Item.getQuantity());
		namedParameters.addValue("price", Item.getPrice());
		jdbc.update(query, namedParameters);
		return "Item info updated";
	}

	public void deleteAll() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM Item";
		jdbc.update(query, namedParameters);
	}

	public void deleteItem(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		String query = "DELETE FROM Item where id = :id";
		jdbc.update(query, namedParameters);
	}

	public Long count() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT count(*) FROM Item";
		return jdbc.queryForObject(query, namedParameters, Long.TYPE);
	}

	public void saveAll(List<Item> ItemList) {
		for (Item s : ItemList) {
			save(s);
		}
	}
}

