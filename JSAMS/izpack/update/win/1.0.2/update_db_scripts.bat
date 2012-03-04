@ECHO OFF
echo "stop the mysql service"
Rem for version 1.0.2 and after it must be with win between $INSTALL_PATH and mysql
$INSTALL_PATH\mysql-5.1.60-win32\bin\mysqld.exe" --uninstall
net stop MySQL
sc delete MySQL
echo "move mysql dir to new dir: win\mysql"
move "$INSTALL_PATH\mysql-5.1.60-win32" "$INSTALL_PATH\win\mysql-5.1.60-win32"
$INSTALL_PATH\win\mysql-5.1.60-win32\bin\mysqld.exe" --install
net stop MySQL
"$INSTALL_PATH\win\mysql-5.1.60-win32\bin\mysql.exe" --user=root --password=$DB_PASSWORD --database=$DB_SCHEMA < "$INSTALL_PATH\scripts\1.0.2\ALTER_TABLES_F_1_0_1_T_1_0_2.sql"
echo "delete all 1.0.1 files"
del "$INSTALL_PATH\script.bat"
del "$INSTALL_PATH\JSAMS-1.0.1-SNAPSHOT.jar"
