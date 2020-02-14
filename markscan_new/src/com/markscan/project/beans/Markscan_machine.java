/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */

public class Markscan_machine {

	/**
	 * 
	 */
	public Markscan_machine() {
		// TODO Auto-generated constructor stub
	}

	int id;
	String name, ip_address, db_name, user_name, password, port, bot_version;
	public String getBot_version() {
		return bot_version;
	}

	public void setBot_version(String bot_version) {
		this.bot_version = bot_version;
	}

	public int getBot_status() {
		return bot_status;
	}

	public void setBot_status(int bot_status) {
		this.bot_status = bot_status;
	}

	int status, bot_status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
