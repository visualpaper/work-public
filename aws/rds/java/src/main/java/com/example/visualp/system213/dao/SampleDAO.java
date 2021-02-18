package com.example.visualp.system213.dao;

import com.example.visualp.system213.config.Const;
import com.example.visualp.system213.dto.SampleDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SampleDAO {

  @Nonnull
  private Connection getConnection() throws SQLException, NamingException {

    if (System.getProperty("os.name").toLowerCase().contains("linux")) {
      Context context = new InitialContext();

      DataSource ds = (DataSource)context.lookup(Const.JNDI_NAME);
      return ds.getConnection();
    } else {
      return DriverManager.getConnection(
          "jdbc:mysql://" + Const.MYSQL_ENDPOINT + "/" + Const.DB_NAME,
          Const.USER,
          Const.PASSWORD
      );
    }
  }

  @Nullable
  public SampleDTO read(int id) throws Exception {
    Class.forName("com.mysql.jdbc.Driver");

    try (Connection con = getConnection()) {
      String sqlStr = "SELECT * FROM sample WHERE id = ?";
      try (PreparedStatement st = con.prepareStatement(sqlStr)) {
        st.setInt(1, id);
        try (ResultSet result = st.executeQuery()) {
          if (!result.next()) {
            return null;
          }
          return new SampleDTO(
              result.getInt("id"),
              result.getString("value")
          );
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
