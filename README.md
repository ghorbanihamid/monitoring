# monitoring web app
With This application is useful for a company who wants to monitor its IBM mainframe servers like CPU usage, ram usage and etc. It connects to RMF of mainframe for fetching data and stores this data for creating charts and if for example CPU usage is more than the specified threshold, it can send Message to administrator of server which registered in application.
For using this application follow this steps:
1)	run the SQL file inside DB folder to create the database with name of “monitoringdb”
2)	create database user and set this username and password in “jdbc.properties”, the default user is : “monitoringUser”
3)	Use java 1.7+ and apache tomcat or other application servers to run the application.
after running the application:
4)	 register employees of company in this application
5)	 register employees working hours as their shift work.
6)	register company’s server’s info (IP Address, ...) to monitor them
7)	set threshold for monitoring parameters to make alarm.


This application uses the following technologies:
- Struts-2.3.16.3
- Struts2-jquery-3.7.1
- Tiles-2.0.6
- Spring-3.1.1
- Hibernate-3.6.5
- Spering-security-3.1.0
- jasperreport-5.0.0
- log4j-1.2
- Mysql-5.1


