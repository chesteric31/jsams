@ECHO OFF
echo "Execute db.sql"
Rem for version 1.0.2 and after it must be with win between $INSTALL_PATH and mysql
"$INSTALL_PATH\mysql-5.1.60-win32\bin\mysql.exe" --user=root --password=$DB_PASSWORD --database=$DB_SCHEMA < "$INSTALL_PATH\scripts\1.0.2\ALTER_TABLES_F_1_0_1_T_1_0_2.sql"
