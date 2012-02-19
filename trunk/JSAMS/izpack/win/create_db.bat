@ECHO OFF
echo "Change root password"
"$INSTALL_PATH\mysql-5.0.95-win32\bin\mysqladmin.exe" --user=root password $DB_PASSWORD
echo "Create database"
"$INSTALL_PATH\mysql-5.0.95-win32\bin\mysqladmin.exe" --user=root --password=$DB_PASSWORD CREATE $DB_SCHEMA
echo "Reload MySQL server"
"$INSTALL_PATH\mysql-5.0.95-win32\bin\mysqladmin.exe" --user=root --password=$DB_PASSWORD reload
