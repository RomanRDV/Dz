package project_dz.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import project_dz.entity.Tovar;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class TovarDao extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize(){
        setDataSource(dataSource);
    }
private Tovar hsqlToTovar(Map<String,Object> row){
    return Tovar.builder()
            .id((Long)row.get("id"))
            .name((String)row.get("name"))
            .create_date((Date) row.get("date"))
            .description((String)row.get("description"))
            .reserved((Boolean)row.get("reserved"))
            .place_storage( (Double) row.get("place_storage"))
            .build();
}
    public List<Tovar> getAllTovar(){
        String sql = "Select * from tovar";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

        List<Tovar> result = new ArrayList<Tovar>();
        for(Map<String, Object> row:rows){
            result.add(hsqlToTovar(row));
        }

        return result;
    }
    public Tovar getTovarById(long id){
        String sql="SELECT * FROM tovar WHERE id = ?";
        return (Tovar) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Tovar>() {
            public Tovar mapRow(ResultSet rs, int rwNumber) throws SQLException {
                return Tovar.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .create_date(rs.getDate("create_date"))
                        .place_storage(rs.getDouble("place_storage"))
                        .reserved(rs.getBoolean("reserved"))
                        .build();
            }
        });
    }
            public void insert(final Tovar tovar) {
                String sql = "INSERT INTO tovar " +
                        "(name, description, create_date, place_storage,reserved) VALUES (?, ?, ?, ?, ?)" ;

                getJdbcTemplate().update(sql, new Object[]{
                        tovar.getName(), tovar.getDescription(), tovar.getCreate_date(),tovar.getPlace_storage(),tovar.getReserved()
                });
            }

    public void update(Tovar tovar) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String sql = "Update tovar Set " +
                "name = " + "'"+tovar.getName()+"'" +
                ", description = " + "'"+ tovar.getDescription() + "'"+
                ", create_date = "+ "'"+dateFormat.format(tovar.getCreate_date())+ "'" +
                ", place_storage = "+ tovar.getPlace_storage() +
                ", reserved = " + tovar.getReserved()+
                " where id =" + tovar.getId();

        getJdbcTemplate().execute(sql);
    }
    public void delete(Long id) {
        String sql = "DELETE FROM tovar " + " where id =" + id;
        getJdbcTemplate().execute(sql);
    }
}
