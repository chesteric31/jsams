@ECHO OFF
echo "Change root password"
"$INSTALL_PATH\win\mysql-5.1.60-win32\bin\mysqladmin.exe" --user=root password $DB_PASSWORD
echo "Create database"
"$INSTALL_PATH\win\mysql-5.1.60-win32\bin\mysqladmin.exe" --user=root --password=$DB_PASSWORD CREATE $DB_SCHEMA
echo "Reload MySQL server"
"$INSTALL_PATH\win\mysql-5.1.60-win32\bin\mysqladmin.exe" --user=root --password=$DB_PASSWORD reload
