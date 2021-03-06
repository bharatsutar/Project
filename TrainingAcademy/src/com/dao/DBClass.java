package com.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.RowSet;
import javax.sql.rowset.RowSetProvider;

public class DBClass {
	
	Connection cn;
	public void connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3307/trainingacademy","root","root");
		
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void closeDB() {
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Get Data
    public ResultSet getData(String sql,Object ... args){
        ResultSet rs=null;
        try{
            connectDB();
            PreparedStatement ps=cn.prepareStatement(sql);
            int i=1;
            for(Object obj : args){
                ps.setObject(i, obj);
                i++;
            }
            rs=ps.executeQuery();
            //close(); //Dont close connection
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rs;
        
    }
    public RowSet getAll(String query,Object ... args){
    		         RowSet rs=null;
    		         try{
    		             rs=RowSetProvider.newFactory().createJdbcRowSet();
    		             rs.setUrl("jdbc:mysql://localhost:3307/trainingacademy");
    		             rs.setUsername("root");
    		             rs.setPassword("root");
    		             rs.setCommand(query);
    		             for(int i=0;i<args.length;i++)
    		             {
    		                 rs.setObject(i+1, args[i]);
    		             }
    		             rs.execute();
    		         } catch (SQLException ex) {
    		            Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
    		        }
    		         return rs;
    		     }
    
    
    public int updateDB(String query,Object ... args)throws Exception{
        int row_affeceted=-1;
        connectDB();
       //try {
           PreparedStatement ps=cn.prepareStatement(query);
           for(int i=0;i<args.length;i++)
           {
               if(args[i] instanceof File){
                   FileInputStream in=new FileInputStream((File)args[i]);                
                   ps.setBinaryStream(i+1, in);
                   
               }else{
                   ps.setObject(i+1, args[i]);
               }
           }
           row_affeceted=ps.executeUpdate();
//       } catch (Exception ex) {
//           Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
//       }
        closeDB();
        return row_affeceted;
    }
    
    
    
//    public int updateDBAndReturnId(String query, Object ... args){
//        int row_affected=-1;
//        connect();
//        try{
//            PreparedStatement ps=cn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
//            for(int i=0;i<args.length;i++){
//                if(args[i] instanceof File){
//                    FileInputStream in=new FileInputStream((File)args[i]);                
//                    ps.setBinaryStream(i+1, in);
//                    
//                }else{
//                ps.setObject(i+1, args[i]);
//                }
//            }
//            row_affected=ps.executeUpdate();
//            ResultSet rs=ps.getGeneratedKeys();
//            rs.next();
//            return rs.getInt(1); //returns generated key
//        
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        closeDB();
//        return row_affected;
//    }
    
    
    
    
    
//    public void fillTable(JTable tab1,String sql){
//        try{
//            RowSet rs=getAll(sql);
//            ResultSetMetaData rsd=rs.getMetaData();
//            DefaultTableModel dt=new DefaultTableModel(){
//                ImageIcon im=new ImageIcon();
//                @Override
//                public Class<?> getColumnClass(int columnIndex) {
//                    try {
//                        if(rsd.getColumnTypeName(columnIndex+1).equals("MEDIUMBLOB")){
//                            tab1.setRowHeight(50);
//                            return ImageIcon.class; //Returns an object of Class<?>
//                        }
//                        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
//                    } catch (SQLException ex) {
//                        Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    return im.getClass();
//                }
//                
//            };
//            tab1.setModel(dt);
//            dt.setRowCount(0);
//            
//            int colCount=rsd.getColumnCount();
//            String cols[]=new String[colCount];
//            for(int i=1;i<=colCount;i++){
//                
//                //System.out.println(rsd.getColumnTypeName(i));
//                cols[i-1]=rsd.getColumnName(i);
//            }
//            dt.setColumnIdentifiers(cols);
//            Object[] row=new Object[colCount];
//            while(rs.next()){                
//                for(int i=1;i<=colCount;i++){
//                    if(rsd.getColumnTypeName(i).equals("MEDIUMBLOB")){
//                    InputStream in=rs.getBinaryStream(i);
//                    byte b[]=new byte[in.available()];
//                    in.read(b); //read b.length data and store in byte array b
//                    ImageIcon img=new ImageIcon(b);
//                    Image im=img.getImage().getScaledInstance(50, 50 ,Image.SCALE_SMOOTH);        
//                    img.setImage(im);
//                    row[i-1]=img;
//                    }else{
//                    row[i-1]=rs.getObject(i);
//                    }
//                }
//                dt.addRow(row);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(DBClass.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//    }
    
}