package com.epam.kgd.victory.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.kgd.victory.bean.Lot;

import com.epam.kgd.victory.dao.LotDAO;
import com.epam.kgd.victory.dao.exception.DAOException;
import com.epam.kgd.victory.dao.pool.ConnectionPool;
import com.epam.kgd.victory.dao.pool.exception.ConnectionPoolException;

public class SQLLotDAO implements LotDAO {

	private static final String SQL_TAKE_ALL_LOTS = "SELECT `l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price` FROM `lot`";
	private static final String SQL_TAKE_LOT_BY_ID = "SELECT `l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price` FROM `lot` WHERE `l_id` = ?";
	private static final String SQL_TAKE_LOTS_BY_TYPE = "SELECT `l_id`, `l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`,`l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price` FROM `lot` WHERE `l_auction_type_id` = ?";
	private static final String SQL_ADD_LOT = "INSERT INTO `lot` (`l_user_id_buyer`, `l_user_id_seller`, `l_good_id`, `l_auction_type_id`, `l_name`, `l_good_amount`, `l_start_date`, `l_end_date`, `l_end_price`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_LOT = "DELETE FROM `lot` WHERE `l_id`=?";
	private static final String SQL_CHANGE_LOT = "UPDATE `lot` SET `l_user_id_buyer`=?, `l_user_id_seller`=?, `l_good_id`=?, `l_auction_type_id`=?, `l_name`=?, `l_good_amount`=?, `l_start_date`=?, `l_end_date`=?, `l_end_price`=? WHERE `l_id`=?";

	private static final ConnectionPool POOL = ConnectionPool.getInstance();

	@Override
	public List<Lot> getAllLots() throws DAOException {
		List<Lot> result = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = POOL.takeConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_TAKE_ALL_LOTS);

			while (resultSet.next()) {
				Lot lot = new Lot();
				lot.setLotId(resultSet.getInt(1));
				lot.setBuyerId(resultSet.getInt(2));
				lot.setSellerId(resultSet.getInt(3));
				lot.setGoodId(resultSet.getInt(4));
				lot.setAuctionTypeId(resultSet.getString(5));
				lot.setLotName(resultSet.getString(6));
				lot.setGoodAmount(resultSet.getInt(7));
				lot.setStartDate(resultSet.getString(8));
				lot.setEndDate(resultSet.getString(9));
				lot.setEndPrice(resultSet.getDouble(10));

				result.add(lot);

			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}

		return result;
	}

	@Override
	public Lot getLotById(int lotId) throws DAOException {
		Lot result = new Lot();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_LOT_BY_ID);
			preparedStatement.setInt(1, lotId);
			resultSet = preparedStatement.executeQuery();

			resultSet.next();

			result.setLotId(resultSet.getInt(1));
			result.setBuyerId(resultSet.getInt(2));
			result.setSellerId(resultSet.getInt(3));
			result.setGoodId(resultSet.getInt(4));
			result.setAuctionTypeId(resultSet.getString(5));
			result.setLotName(resultSet.getString(6));
			result.setGoodAmount(resultSet.getInt(7));
			result.setStartDate(resultSet.getString(8));
			result.setEndDate(resultSet.getString(9));
			result.setEndPrice(resultSet.getDouble(10));

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}
		return result;
	}

	@Override
	public List<Lot> getLotsByTypeId(int typeId) throws DAOException {
		List<Lot> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_TAKE_LOTS_BY_TYPE);
			preparedStatement.setInt(1, typeId);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Lot lot = new Lot();
				lot.setLotId(resultSet.getInt(1));
				lot.setBuyerId(resultSet.getInt(2));
				lot.setSellerId(resultSet.getInt(3));
				lot.setGoodId(resultSet.getInt(4));
				lot.setAuctionTypeId(resultSet.getString(5));
				lot.setLotName(resultSet.getString(6));
				lot.setGoodAmount(resultSet.getInt(7));
				lot.setStartDate(resultSet.getString(8));
				lot.setEndDate(resultSet.getString(9));
				lot.setEndPrice(resultSet.getDouble(10));

				result.add(lot);

			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}

		return result;
	}

	@Override
	public void addLot(Lot lot) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_ADD_LOT);
			preparedStatement.setInt(1, lot.getBuyerId());
			preparedStatement.setInt(2, lot.getSellerId());
			preparedStatement.setInt(3, lot.getGoodId());
			preparedStatement.setString(4, lot.getAuctionTypeId());
			preparedStatement.setString(5, lot.getLotName());
			preparedStatement.setInt(6, lot.getGoodAmount());
			preparedStatement.setString(7, lot.getStartDate());
			preparedStatement.setString(8, lot.getEndDate());
			preparedStatement.setDouble(9, lot.getEndPrice());

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}

	}

	@Override
	public void deleteLot(int lotId) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_DELETE_LOT);
			preparedStatement.setInt(1, lotId);
			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}

	}

	@Override
	public void changeLot(int lotId, Lot updatedLot) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = POOL.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_CHANGE_LOT);
			preparedStatement.setInt(1, updatedLot.getBuyerId());
			preparedStatement.setInt(2, updatedLot.getSellerId());
			preparedStatement.setInt(3, updatedLot.getGoodId());
			preparedStatement.setString(4, updatedLot.getAuctionTypeId());
			preparedStatement.setString(5, updatedLot.getLotName());
			preparedStatement.setInt(6, updatedLot.getGoodAmount());
			preparedStatement.setString(7, updatedLot.getStartDate());
			preparedStatement.setString(8, updatedLot.getEndDate());
			preparedStatement.setDouble(9, updatedLot.getEndPrice());
			preparedStatement.setInt(10, lotId);

			preparedStatement.executeUpdate();

		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
		}

	}

}
