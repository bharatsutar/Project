package com.dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.configuration.ConnectionFactory;
import com.model.Batch;


public class BatchDaoImplementation implements BatchDao {
	public BatchDaoImplementation() {
		
	}
	

	/*	@Override
	public int addBook(Book book) {
		System.out.println(book);
		int status = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement("insert into book values(?,?,?,?)");
			preparedStatement.setInt(1, book.getBookId());
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setInt(4, book.getPrice());
			status=preparedStatement.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
				try {
					if(preparedStatement!=null)
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(connection!=null)
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		// TODO Auto-generated method stub
		return status;
	}

*/
	@Override
	public List<Batch> getAllBatchs() {
		// TODO Auto-generated method stub
		ArrayList<Batch> batchList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		final String QUERY = "select * from Batch";
		try {
			connection = ConnectionFactory.getConnection();
			preparedStatement = connection.prepareStatement(QUERY);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Batch batch = new Batch();
				batch.setBatch_no(resultSet.getInt("batch_no"));
				batch.setBatch_time(resultSet.getString("batch_time"));
			//	bookList.add(batch);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return batchList;

		//return null;
	}


	@Override
	public int addBatch(Batch batch) {
		// TODO Auto-generated method stub
		return 0;
	}



}
