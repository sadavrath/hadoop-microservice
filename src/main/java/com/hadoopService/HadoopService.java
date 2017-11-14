package com.hadoopService;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("HadoopService")
public class HadoopService {
	

	public Configuration getConf() {
		Configuration configuration = HBaseConfiguration.create();

		configuration.set("hbase.zookeeper.property.clientPort","2181");
		configuration.set("hbase.zookeeper.quorum", "localhost:2181");
		configuration.set("zookeeper.znode.parent", "/hbase");
		return configuration;
	}
	

	private TableName tableName = TableName.valueOf("mock_data");
	
	public List<UserPOJO> getUsers() throws IOException {
		Configuration conf = getConf();
		System.out.println(conf);
		List<UserPOJO> names = new ArrayList<>();
		HTable table = new HTable(conf, tableName);
		
			Scan scan = new Scan();


		      // Getting the scan result
		      ResultScanner scanner = table.getScanner(scan);

		      for (Result r = scanner.next(); r != null; r = scanner.next())
		      {
		    	  UserPOJO user= new UserPOJO();

		    	  byte [] value0 = r.getRow();
		    byte [] value = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("first_name"));

		      byte [] value1 = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("last_name"));
		      
		      byte [] value2 = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("email"));
		      
		      byte [] value3 = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("gender"));
		      
		      byte [] value4 = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("ip_address"));
		      
		      String id = new String(value0, "UTF-8");
		      String firstName = new String(value, "UTF-8");
		     	String lastName = new String(value1, "UTF-8");
		     	String email = new String(value2, "UTF-8");
		     	String gender = new String(value3, "UTF-8");
		     	String ipAddress = new String(value4, "UTF-8");
		      
		      user.setId(id);
		      user.setFirstName(firstName);
		      user.setLastName(lastName);
		      user.setEmail(email);
		      user.setGender(gender);
		      user.setIpAddress(ipAddress);
		      
		      names.add(user);
		     
		      }  
	         
	    return names;
	}


	
	public void setUser(String id,String fname,String lname,String email,String gender,String ipadd) throws IOException {
		Configuration conf = getConf();
		HTable table = new HTable(conf, tableName);
		
		Put p = new Put(Bytes.toBytes(id));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("first_name"),Bytes.toBytes(fname));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("last_name"),Bytes.toBytes(lname));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("email"),Bytes.toBytes(email));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("gender"),Bytes.toBytes(gender));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("ip_address"),Bytes.toBytes(ipadd));
		table.put(p);
		table.close();
	}

	public String delete(String id) throws IOException {
		Configuration conf = getConf();
		HTable table = new HTable(conf, tableName);
		
		Delete delete=new Delete(Bytes.toBytes(id));
		
		table.delete(delete);
		table.close();
		
		return id+" deleted";
	}

	
	public UserPOJO getUser(String id) throws IOException {
		UserPOJO user=new UserPOJO();
		Configuration conf = getConf();
		HTable table = new HTable(conf, tableName);
		Result r = table.get(new Get(Bytes.toBytes(id)));
		byte [] value0 = r.getRow();
	
		
		byte [] value = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("first_name"));

	      byte [] value1 = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("last_name"));
	      
	      byte [] value2 = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("email"));
	      
	      byte [] value3 = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("gender"));
	      
	      byte [] value4 = r.getValue(Bytes.toBytes("details"),Bytes.toBytes("ip_address"));
	      
	      String id1 = new String(value0, "UTF-8");
	      String firstName = new String(value, "UTF-8");
	     	String lastName = new String(value1, "UTF-8");
	     	String email = new String(value2, "UTF-8");
	     	String gender = new String(value3, "UTF-8");
	     	String ipAddress = new String(value4, "UTF-8");
	      
	      user.setId(id1);
	      user.setFirstName(firstName);
	      user.setLastName(lastName);
	      user.setEmail(email);
	      user.setGender(gender);
	      user.setIpAddress(ipAddress);
	      
		return user;
	}

	public void updateUser(String id, String firstName, String lastName, String email, String gender,
			String ipAddress) throws IOException {
		Configuration conf = getConf();
		HTable table = new HTable(conf, tableName);
		Put p = new Put(Bytes.toBytes(id));
		
		p.add(Bytes.toBytes("details"), Bytes.toBytes("first_name"),Bytes.toBytes(firstName));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("last_name"),Bytes.toBytes(lastName));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("email"),Bytes.toBytes(email));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("gender"),Bytes.toBytes(gender));
		p.add(Bytes.toBytes("details"), Bytes.toBytes("ip_address"),Bytes.toBytes(ipAddress));
		table.put(p);
		table.close();
		
	}

}
