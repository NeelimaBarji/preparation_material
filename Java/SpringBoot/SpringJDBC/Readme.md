SPring JDBC:

Add JDBC API , h2 starters while creating app


Execute vs executeUpdate

Rowmapper: is functional interface which returns one row of data at a time from resultset and row number. It has mapRow method which needs to be overridden to set
resultset entries to your class object ( example: student)

How ot use potgres:
configure dependency in pom.xml
specify connection props  - url, driverclass name, username, pwd
