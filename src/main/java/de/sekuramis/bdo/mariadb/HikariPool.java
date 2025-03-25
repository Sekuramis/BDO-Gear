package de.sekuramis.bdo.mariadb;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.sekuramis.bdo.utils.ConfigManager;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author Sekuramis | Jannik
 */
public class HikariPool
{
	private static HikariDataSource hikariDataSource;
	private final Map<HikariStatement, List<Object>> list = new HashMap();
	@Getter
	private static ConfigManager config = new ConfigManager("/opt/guilds/VERSUS/mysql.yml");

	public HikariPool()
	{
		final HikariConfig config = new HikariConfig();
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		config.setJdbcUrl("jdbc:mysql://localhost:3306/");
		config.setUsername(config.getUsername());
		config.setPassword(config.getPassword());
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setConnectionTimeout(5000);
		config.setPoolName("BDO Gear Pool");
		config.setMaximumPoolSize(10);

		hikariDataSource = new HikariDataSource(config);
	}

	public void runASyncUpdate(HikariStatement hikariStatement)
	{
		CompletableFuture.runAsync(() -> runUpdate(hikariStatement));
	}

	public void runSyncUpdate(HikariStatement hikariStatement)
	{
		runUpdate(hikariStatement);
	}

	private void runUpdate(HikariStatement hikariStatement)
	{
		try (Connection connection = hikariDataSource.getConnection();
				PreparedStatement ps = preparedStatement(connection, hikariStatement)) {
			ps.execute();
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		} finally
		{
			hikariStatement.clearPresetMap();
		}

	}

	private PreparedStatement preparedStatement(@NotNull Connection connection, @NotNull HikariStatement statement) throws SQLException
	{
		PreparedStatement ps = connection.prepareStatement(statement.getStatement());
		statement.getPresetMap().forEach((index, value) -> {
			try
			{
				ps.setObject(index, value);
			}
			catch (SQLException e)
			{
				throw new RuntimeException(e);
			}
		});
		return ps;
	}

	public ResultSet runAsyncQuery(HikariStatement statement) {
		Connection connection;

		PreparedStatement ps;
		ResultSet rs;

		try {
			connection = hikariDataSource.getConnection();
			list.put(statement, new ArrayList<>());
			list.get(statement).add(connection);

			ps = connection.prepareStatement(statement.getStatement());

			for (int i = 1; i <= statement.getPresetMap().size(); i++) {
				Object object = statement.getPresetMap().get(i);
				setObject(i, object, ps);
			}
			list.get(statement).add(ps);

			rs = ps.executeQuery();
			list.get(statement).add(rs);
			return rs;
		} catch (SQLException | NullPointerException e) {
			throw new RuntimeException(e);
		}
	}

	public @NotNull Boolean isInDatabase(@NotNull HikariStatement statement) {
		Connection connection = null;

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = hikariDataSource.getConnection();

			ps = connection.prepareStatement(statement.getStatement());
			for (int i = 1; i <= statement.getPresetMap().size(); i++) {
				Object object = statement.getPresetMap().get(i);
				setObject(i, object, ps);
			}

			rs = ps.executeQuery();

			return rs.next();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(rs, ps, connection);
			statement.clearPresetMap();
		}
	}

	public int getInt(HikariStatement statement, String columntitle) {
		final Integer value = getValue(statement, columntitle, ResultSet::getInt);
		return value == null ? 0 : value;
	}

	public <E extends Enum<E>> E getEnum(HikariStatement statement, String columnTitle, Class<E> enumClass) {
		String value = getValue(statement, columnTitle, ResultSet::getString);
		return value == null ? null : Enum.valueOf(enumClass, value.toUpperCase());
	}

	public List<UUID> getUUIDList(HikariStatement statement, String columnTitle) {
		return getList(statement, columnTitle, ResultSet::getString)
				.stream().map(value -> value == null ? null : UUID.fromString(value))
				.collect(Collectors.toList());
	}

	public double getDouble(HikariStatement statement, String columntitle) {
		final Double value = getValue(statement, columntitle, ResultSet::getDouble);
		return value == null ? 0D : value;
	}

	public float getFloat(HikariStatement statement, String columntitle) {
		final Float value = getValue(statement, columntitle, ResultSet::getFloat);
		return value == null ? 0F : value;
	}

	public long getLong(HikariStatement statement, String columntitle) {
		final Long value = getValue(statement, columntitle, ResultSet::getLong);
		return value == null ? 0L : value;
	}

	public List<Long> getLongList(HikariStatement statement, String columnTitle) {
		return getList(statement, columnTitle, ResultSet::getLong)
				.stream().map(value -> value == null ? 0L : value)
				.collect(Collectors.toList());
	}


	private <T> List<T> getList(HikariStatement statement, String columnTitle, ResultSetExtractor<T> extractor) {
		List<T> values = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = hikariDataSource.getConnection();
			ps = connection.prepareStatement(statement.getStatement());
			for (int i = 1; i <= statement.getPresetMap().size(); i++) {
				Object object = statement.getPresetMap().get(i);
				setObject(i, object, ps);
			}
			rs = ps.executeQuery();

			while (rs.next()) {
				values.add(extractor.extract(rs, columnTitle));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(rs, ps, connection);
			statement.clearPresetMap();
		}
		return values;
	}


	public byte getByte(HikariStatement statement, String columntitle) {
		final Byte value = getValue(statement, columntitle, ResultSet::getByte);
		return value == null ? (byte) 0 : value;
	}

	public byte[] getBytes(HikariStatement statement, String columntitle) {
		return getValue(statement, columntitle, ResultSet::getBytes);
	}

	public @NotNull String getString(HikariStatement statement, String columntitle) {
		final String value = getValue(statement, columntitle, ResultSet::getString);
		return value == null ? "" : value;
	}

	public boolean getBoolean(HikariStatement statement, String columntitle) {
		final Boolean value = getValue(statement, columntitle, ResultSet::getBoolean);
		return value != null && value;
	}

	public @NotNull Timestamp getTimestamp(HikariStatement statement, String columntitle) {
		final Timestamp value = getValue(statement, columntitle, ResultSet::getTimestamp);
		return value != null ? value : new Timestamp(0);
	}

	private <T> T getValue(@NotNull HikariStatement statement, String columntitle, ResultSetExtractor<T> extractor) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		T result = null;

		try {
			connection = hikariDataSource.getConnection();
			ps = connection.prepareStatement(statement.getStatement());
			for (int i = 1; i <= statement.getPresetMap().size(); i++) {
				Object object = statement.getPresetMap().get(i);
				setObject(i, object, ps);
			}
			rs = ps.executeQuery();

			if (rs.next()) {
				result = extractor.extract(rs, columntitle);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			closeResources(rs, ps, connection);
			statement.clearPresetMap();
		}

		return result;
	}

	@FunctionalInterface
	interface ResultSetExtractor<T> {
		T extract(ResultSet rs, String columntitle) throws SQLException;
	}

	private void setObject(int index, Object object, PreparedStatement ps) throws SQLException {
		if (object instanceof Integer) {
			ps.setInt(index, (Integer) object);
		} else if (object instanceof Double) {
			ps.setDouble(index, (Double) object);
		} else if (object instanceof Float) {
			ps.setFloat(index, (Float) object);
		} else if (object instanceof Long) {
			ps.setLong(index, (Long) object);
		} else if (object instanceof Byte) {
			ps.setByte(index, (Byte) object);
		} else if (object instanceof Byte[]) {
			ps.setBytes(index, (byte[]) object);
		} else if (object instanceof String) {
			ps.setString(index, (String) object);
		} else if (object instanceof Boolean) {
			ps.setBoolean(index, (Boolean) object);
		}
	}

	public static void closeConnectionToDatabase() {
		if (hikariDataSource == null) return;
		hikariDataSource.close();
	}

	public void closeConnections(HikariStatement statement) {
		if (list.containsKey(statement)) {
			List<Object> resources = list.get(statement);
			closeResources((ResultSet) resources.get(2), (PreparedStatement) resources.get(1), (Connection) resources.get(0));
			list.remove(statement);
		}
	}

	private void closeResources(ResultSet rs, PreparedStatement ps, Connection connection) {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (connection != null && !connection.isClosed()) connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
