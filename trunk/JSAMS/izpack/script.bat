@ECHO OFF
echo "$INSTALL_PATH"

echo "Execution of SQL Queries"
echo "Mysql daemon install"
"$INSTALL_PATH\mysql-5.1.60-win32\bin\mysqld.exe" --install
echo "Mysql daemon starting"
net start MySQL
echo "Change root password"
"$INSTALL_PATH\mysql-5.1.60-win32\bin\mysqladmin.exe" --user=root password $DB_PASSWORD
echo "Create database"
"$INSTALL_PATH\mysql-5.1.60-win32\bin\mysqladmin.exe" --user=root --password=$DB_PASSWORD CREATE $DB_SCHEMA
echo "Reload MySQL server"
"$INSTALL_PATH\mysql-5.1.60-win32\bin\mysqladmin.exe" --user=root --password=$DB_PASSWORD reload
echo "Execute db.sql"
"$INSTALL_PATH\mysql-5.1.60-win32\bin\mysql.exe" --user=root --password=$DB_PASSWORD --database=$DB_SCHEMA < "$INSTALL_PATH\db\db_win.sql"
echo "END Execution of SQL Queries"
